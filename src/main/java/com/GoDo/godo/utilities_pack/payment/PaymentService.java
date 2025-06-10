package com.GoDo.godo.utilities_pack.payment;

import com.GoDo.godo.user_pack.deliverybooking.DeliveryBookingModel;
import com.GoDo.godo.user_pack.deliverybooking.DeliveryBookingRepo;
import com.GoDo.godo.user_pack.travelbooking.TravelBookingModel;
import com.GoDo.godo.user_pack.travelbooking.TravelBookingRepo;
import com.GoDo.godo.user_pack.travelroute.TravelRouteModel;
import com.GoDo.godo.user_pack.travelroute.TravelRouteRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.Utils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;
import java.util.Optional;


@Service
public class PaymentService {

    @Autowired
    private TravelBookingRepo travelBookingRepo;

    @Autowired
    private TravelRouteRepo travelRouteRepo;

    @Autowired
    private DeliveryBookingRepo deliveryBookingRepo;

    @Value("${razorpay.key_id}")
    private String keyId;

    @Value("${razorpay.key_secret}")
    private String keySecret;

    private final PaymentRepo paymentRepo;

    public PaymentService(PaymentRepo paymentRepository) {
        this.paymentRepo = paymentRepository;
    }

    public Order createOrder(PaymentRequestDto request) throws Exception {
        RazorpayClient razorpay = new RazorpayClient(keyId, keySecret);

        JSONObject options = new JSONObject();
        options.put("amount",(int)(request.getAmount() * 100));
        options.put("currency", "INR");
        options.put("receipt", "receipt_" + System.currentTimeMillis());

        Order order = razorpay.orders.create(options);

        PaymentModel payment = new PaymentModel();
        payment.setCustomerId(request.getCustomerId());
        payment.setOwnerId(request.getOwnerId());
        payment.setRouteId(request.getRouteId());
        payment.setBookingId(request.getBookingId());
        payment.setAmount(request.getAmount());
        payment.setPaymentDate(LocalDateTime.now());
        payment.setRazorpayOrderId(order.get("id"));
        payment.setStatus("CREATED");

        paymentRepo.save(payment);

        return order;
    }

    public boolean verifySignature(String orderId, String paymentId, String signature) throws Exception {
        String data = orderId + "|" + paymentId;
        return Utils.verifySignature(data, signature, keySecret);
    }

    public void updatePaymentStatus(String orderId, String paymentId, String signature, boolean isValid) {
        PaymentModel payment = paymentRepo.findByRazorpayOrderId(orderId);
        if (payment != null) {
            payment.setRazorpay_payment_id(paymentId);
            payment.setRazorpay_signature(signature);
            payment.setStatus(isValid ? "SUCCESS" : "FAILED");

            if (isValid) {

                Optional<TravelBookingModel> optionalTravelBookingModel= travelBookingRepo.findById(payment.getBookingId());
                if(optionalTravelBookingModel.isPresent()){
                    TravelBookingModel travelBookingModel=optionalTravelBookingModel.get();
                    travelBookingModel.setPaymentStatus(4);
                    travelBookingModel.setStatus(4);
                    travelBookingModel.setPaymentId(payment.getPaymentId());
                    travelBookingRepo.save(travelBookingModel);
                }
                Optional<DeliveryBookingModel> optionalDeliveryBookingModel= deliveryBookingRepo.findById(payment.getBookingId());
                if(optionalDeliveryBookingModel.isPresent()){
                    DeliveryBookingModel deliveryBookingModel=optionalDeliveryBookingModel.get();
                    deliveryBookingModel.setPaymentStatus(4);
                    deliveryBookingModel.setStatus(4);
                    deliveryBookingModel.setPaymentId(payment.getPaymentId());
                    deliveryBookingRepo.save(deliveryBookingModel);
                }
                Optional<TravelRouteModel> optionalTravelRouteModel= travelRouteRepo.findById(payment.getRouteId());
                if(optionalTravelRouteModel.isPresent()){
                    TravelRouteModel travelRouteModel=optionalTravelRouteModel.get();
                    travelRouteModel.setPaymentStatus(4);
                    travelRouteModel.setStatus(4);
                    travelRouteModel.setPaymentId(payment.getPaymentId());
                    travelRouteRepo.save(travelRouteModel);
                }

            }

            paymentRepo.save(payment);
        }
    }

}
