package com.GoDo.godo.utilities_pack.payment;
import com.razorpay.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
@CrossOrigin(origins = {"http://localhost:8081","http://172.20.4.53:8081/","http://192.168.1.10:8081/","http://localhost:5173"})

@RestController
@RequestMapping("/api/godo/payment")
public class PaymentController {

    @Autowired
    private PaymentRepo paymentRepo;

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/create-order")
    public String createOrder(@RequestBody PaymentRequestDto request) throws Exception {
        Order order = paymentService.createOrder(request);
        return order.toString(); // returns JSON order details
    }

    @PostMapping("/verify")
    public String verify(@RequestBody Map<String, String> payload) throws Exception {
        String orderId = payload.get("razorpay_order_id");
        String paymentId = payload.get("razorpay_payment_id");
        String signature = payload.get("razorpay_signature");

        boolean isValid = paymentService.verifySignature(orderId, paymentId, signature);
        paymentService.updatePaymentStatus(orderId, paymentId, signature, isValid);

        return isValid ? "Payment Verified" : "Payment Verification Failed";
    }

    @GetMapping("/history")
    public List<PaymentModel> getAllPaymentHistory() {
        return paymentRepo.findAllByOrderByPaymentDateDesc();
    }
}