PGDMP  /                    }            godo_app_db    16.4    16.4 6    K           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            L           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            M           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            N           1262    24938    godo_app_db    DATABASE     ~   CREATE DATABASE godo_app_db WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'English_India.1252';
    DROP DATABASE godo_app_db;
                postgres    false            �            1259    25993    gender_type    TABLE     l   CREATE TABLE public.gender_type (
    gender_id integer NOT NULL,
    gender_name character varying(255)
);
    DROP TABLE public.gender_type;
       public         heap    postgres    false            �            1259    26260    godo_delivery_booking    TABLE     B  CREATE TABLE public.godo_delivery_booking (
    booking_id character varying(255) NOT NULL,
    boarding_time timestamp(6) without time zone NOT NULL,
    booking_time timestamp(6) without time zone NOT NULL,
    destination character varying(255) NOT NULL,
    payment_id character varying(255),
    payment_status integer NOT NULL,
    phone_number character varying(255) NOT NULL,
    route_id character varying(255) NOT NULL,
    start character varying(255) NOT NULL,
    status integer NOT NULL,
    user_id character varying(255) NOT NULL,
    weight integer NOT NULL
);
 )   DROP TABLE public.godo_delivery_booking;
       public         heap    postgres    false            �            1259    26361    godo_history    TABLE     �  CREATE TABLE public.godo_history (
    travel_id character varying(255) NOT NULL,
    amount double precision NOT NULL,
    boarding_time timestamp(6) without time zone NOT NULL,
    booked integer,
    customer_id character varying(255) NOT NULL,
    delivery_customer_id character varying(255),
    destination character varying(255) NOT NULL,
    distance bigint NOT NULL,
    owner_id character varying(255) NOT NULL,
    owner_phone_number character varying(255) NOT NULL,
    payment_id character varying(255),
    start character varying(255) NOT NULL,
    status integer NOT NULL,
    vacancy integer,
    vehicle_id character varying(255)
);
     DROP TABLE public.godo_history;
       public         heap    postgres    false            �            1259    25443 
   godo_login    TABLE       CREATE TABLE public.godo_login (
    user_id character varying(255) NOT NULL,
    date_created timestamp(6) without time zone,
    date_updated timestamp(6) without time zone,
    phone_number character varying(255) NOT NULL,
    session_id character varying(255)
);
    DROP TABLE public.godo_login;
       public         heap    postgres    false            �            1259    26267    godo_payment    TABLE     �  CREATE TABLE public.godo_payment (
    payment_id character varying(255) NOT NULL,
    amount double precision,
    booking_id character varying(255),
    customer_id character varying(255),
    owner_id character varying(255),
    payment_date timestamp(6) without time zone,
    razorpay_order_id character varying(255),
    razorpay_payment_id character varying(255),
    razorpay_signature character varying(255),
    route_id character varying(255),
    status character varying(255)
);
     DROP TABLE public.godo_payment;
       public         heap    postgres    false            �            1259    26018    godo_profile    TABLE     q  CREATE TABLE public.godo_profile (
    phone_number character varying(255) NOT NULL,
    aadhar character varying(255),
    address character varying(255),
    age integer,
    driving_license character varying(255),
    email character varying(255),
    gender character varying(255),
    last_update timestamp(6) without time zone,
    name character varying(255)
);
     DROP TABLE public.godo_profile;
       public         heap    postgres    false            �            1259    26274    godo_status    TABLE     l   CREATE TABLE public.godo_status (
    status_id integer NOT NULL,
    status_name character varying(255)
);
    DROP TABLE public.godo_status;
       public         heap    postgres    false            �            1259    26335    godo_travel_booking    TABLE     �  CREATE TABLE public.godo_travel_booking (
    booking_id character varying(255) NOT NULL,
    amount double precision NOT NULL,
    boarding_time timestamp(6) without time zone NOT NULL,
    booking_time timestamp(6) without time zone NOT NULL,
    destination character varying(255) NOT NULL,
    distance bigint NOT NULL,
    owner_id character varying(255) NOT NULL,
    passenger_count integer NOT NULL,
    payment_id character varying(255),
    payment_status integer NOT NULL,
    phone_number character varying(255) NOT NULL,
    route_id character varying(255) NOT NULL,
    start character varying(255) NOT NULL,
    status integer NOT NULL,
    user_id character varying(255) NOT NULL
);
 '   DROP TABLE public.godo_travel_booking;
       public         heap    postgres    false            �            1259    26342    godo_travel_route    TABLE     �  CREATE TABLE public.godo_travel_route (
    route_id character varying(255) NOT NULL,
    amount double precision NOT NULL,
    boarding_time timestamp(6) without time zone NOT NULL,
    booked integer,
    capacity integer NOT NULL,
    delivery integer,
    destination character varying(255) NOT NULL,
    distance bigint NOT NULL,
    payment_id character varying(255),
    payment_status integer NOT NULL,
    phone_number character varying(255) NOT NULL,
    start character varying(255) NOT NULL,
    status integer NOT NULL,
    total_weight integer,
    user_id character varying(255) NOT NULL,
    vacancy integer,
    vehicle_id character varying(255)
);
 %   DROP TABLE public.godo_travel_route;
       public         heap    postgres    false            �            1259    26005    godo_vehicle    TABLE     �  CREATE TABLE public.godo_vehicle (
    vehicle_id character varying(255) NOT NULL,
    capacity integer,
    chassis_number character varying(255),
    phone_number character varying(255),
    rc_book character varying(255) NOT NULL,
    registration_number character varying(255),
    status character varying(255),
    user_id character varying(255),
    vehicle_name character varying(255),
    vehicle_type character varying(255)
);
     DROP TABLE public.godo_vehicle;
       public         heap    postgres    false            �            1259    25976    vehicle_status    TABLE     o   CREATE TABLE public.vehicle_status (
    status_id integer NOT NULL,
    status_name character varying(255)
);
 "   DROP TABLE public.vehicle_status;
       public         heap    postgres    false            �            1259    25985    vehicle_type    TABLE     y   CREATE TABLE public.vehicle_type (
    vehicle_type_id integer NOT NULL,
    vehicle_type_name character varying(255)
);
     DROP TABLE public.vehicle_type;
       public         heap    postgres    false            �            1259    34526    waypoint_cache    TABLE     �   CREATE TABLE public.waypoint_cache (
    id bigint NOT NULL,
    lat double precision NOT NULL,
    lon double precision NOT NULL,
    place_name character varying(255) NOT NULL
);
 "   DROP TABLE public.waypoint_cache;
       public         heap    postgres    false            �            1259    34533    waypoint_cache_seq    SEQUENCE     |   CREATE SEQUENCE public.waypoint_cache_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.waypoint_cache_seq;
       public          postgres    false            >          0    25993    gender_type 
   TABLE DATA           =   COPY public.gender_type (gender_id, gender_name) FROM stdin;
    public          postgres    false    218   pM       A          0    26260    godo_delivery_booking 
   TABLE DATA           �   COPY public.godo_delivery_booking (booking_id, boarding_time, booking_time, destination, payment_id, payment_status, phone_number, route_id, start, status, user_id, weight) FROM stdin;
    public          postgres    false    221   �M       F          0    26361    godo_history 
   TABLE DATA           �   COPY public.godo_history (travel_id, amount, boarding_time, booked, customer_id, delivery_customer_id, destination, distance, owner_id, owner_phone_number, payment_id, start, status, vacancy, vehicle_id) FROM stdin;
    public          postgres    false    226   �M       ;          0    25443 
   godo_login 
   TABLE DATA           c   COPY public.godo_login (user_id, date_created, date_updated, phone_number, session_id) FROM stdin;
    public          postgres    false    215   ON       B          0    26267    godo_payment 
   TABLE DATA           �   COPY public.godo_payment (payment_id, amount, booking_id, customer_id, owner_id, payment_date, razorpay_order_id, razorpay_payment_id, razorpay_signature, route_id, status) FROM stdin;
    public          postgres    false    222   �P       @          0    26018    godo_profile 
   TABLE DATA           }   COPY public.godo_profile (phone_number, aadhar, address, age, driving_license, email, gender, last_update, name) FROM stdin;
    public          postgres    false    220   �Q       C          0    26274    godo_status 
   TABLE DATA           =   COPY public.godo_status (status_id, status_name) FROM stdin;
    public          postgres    false    223   �R       D          0    26335    godo_travel_booking 
   TABLE DATA           �   COPY public.godo_travel_booking (booking_id, amount, boarding_time, booking_time, destination, distance, owner_id, passenger_count, payment_id, payment_status, phone_number, route_id, start, status, user_id) FROM stdin;
    public          postgres    false    224   BS       E          0    26342    godo_travel_route 
   TABLE DATA           �   COPY public.godo_travel_route (route_id, amount, boarding_time, booked, capacity, delivery, destination, distance, payment_id, payment_status, phone_number, start, status, total_weight, user_id, vacancy, vehicle_id) FROM stdin;
    public          postgres    false    225   _S       ?          0    26005    godo_vehicle 
   TABLE DATA           �   COPY public.godo_vehicle (vehicle_id, capacity, chassis_number, phone_number, rc_book, registration_number, status, user_id, vehicle_name, vehicle_type) FROM stdin;
    public          postgres    false    219   |S       <          0    25976    vehicle_status 
   TABLE DATA           @   COPY public.vehicle_status (status_id, status_name) FROM stdin;
    public          postgres    false    216   �U       =          0    25985    vehicle_type 
   TABLE DATA           J   COPY public.vehicle_type (vehicle_type_id, vehicle_type_name) FROM stdin;
    public          postgres    false    217   V       G          0    34526    waypoint_cache 
   TABLE DATA           B   COPY public.waypoint_cache (id, lat, lon, place_name) FROM stdin;
    public          postgres    false    227   6V       O           0    0    waypoint_cache_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.waypoint_cache_seq', 1051, true);
          public          postgres    false    228            �           2606    25997    gender_type gender_type_pkey 
   CONSTRAINT     a   ALTER TABLE ONLY public.gender_type
    ADD CONSTRAINT gender_type_pkey PRIMARY KEY (gender_id);
 F   ALTER TABLE ONLY public.gender_type DROP CONSTRAINT gender_type_pkey;
       public            postgres    false    218            �           2606    26266 0   godo_delivery_booking godo_delivery_booking_pkey 
   CONSTRAINT     v   ALTER TABLE ONLY public.godo_delivery_booking
    ADD CONSTRAINT godo_delivery_booking_pkey PRIMARY KEY (booking_id);
 Z   ALTER TABLE ONLY public.godo_delivery_booking DROP CONSTRAINT godo_delivery_booking_pkey;
       public            postgres    false    221            �           2606    26367    godo_history godo_history_pkey 
   CONSTRAINT     c   ALTER TABLE ONLY public.godo_history
    ADD CONSTRAINT godo_history_pkey PRIMARY KEY (travel_id);
 H   ALTER TABLE ONLY public.godo_history DROP CONSTRAINT godo_history_pkey;
       public            postgres    false    226            �           2606    25449    godo_login godo_login_pkey 
   CONSTRAINT     ]   ALTER TABLE ONLY public.godo_login
    ADD CONSTRAINT godo_login_pkey PRIMARY KEY (user_id);
 D   ALTER TABLE ONLY public.godo_login DROP CONSTRAINT godo_login_pkey;
       public            postgres    false    215            �           2606    26273    godo_payment godo_payment_pkey 
   CONSTRAINT     d   ALTER TABLE ONLY public.godo_payment
    ADD CONSTRAINT godo_payment_pkey PRIMARY KEY (payment_id);
 H   ALTER TABLE ONLY public.godo_payment DROP CONSTRAINT godo_payment_pkey;
       public            postgres    false    222            �           2606    26024    godo_profile godo_profile_pkey 
   CONSTRAINT     f   ALTER TABLE ONLY public.godo_profile
    ADD CONSTRAINT godo_profile_pkey PRIMARY KEY (phone_number);
 H   ALTER TABLE ONLY public.godo_profile DROP CONSTRAINT godo_profile_pkey;
       public            postgres    false    220            �           2606    26278    godo_status godo_status_pkey 
   CONSTRAINT     a   ALTER TABLE ONLY public.godo_status
    ADD CONSTRAINT godo_status_pkey PRIMARY KEY (status_id);
 F   ALTER TABLE ONLY public.godo_status DROP CONSTRAINT godo_status_pkey;
       public            postgres    false    223            �           2606    26341 ,   godo_travel_booking godo_travel_booking_pkey 
   CONSTRAINT     r   ALTER TABLE ONLY public.godo_travel_booking
    ADD CONSTRAINT godo_travel_booking_pkey PRIMARY KEY (booking_id);
 V   ALTER TABLE ONLY public.godo_travel_booking DROP CONSTRAINT godo_travel_booking_pkey;
       public            postgres    false    224            �           2606    26348 (   godo_travel_route godo_travel_route_pkey 
   CONSTRAINT     l   ALTER TABLE ONLY public.godo_travel_route
    ADD CONSTRAINT godo_travel_route_pkey PRIMARY KEY (route_id);
 R   ALTER TABLE ONLY public.godo_travel_route DROP CONSTRAINT godo_travel_route_pkey;
       public            postgres    false    225            �           2606    26011    godo_vehicle godo_vehicle_pkey 
   CONSTRAINT     d   ALTER TABLE ONLY public.godo_vehicle
    ADD CONSTRAINT godo_vehicle_pkey PRIMARY KEY (vehicle_id);
 H   ALTER TABLE ONLY public.godo_vehicle DROP CONSTRAINT godo_vehicle_pkey;
       public            postgres    false    219            �           2606    34532 *   waypoint_cache uk416sq5mmcp87i65cxm4iqpopu 
   CONSTRAINT     i   ALTER TABLE ONLY public.waypoint_cache
    ADD CONSTRAINT uk416sq5mmcp87i65cxm4iqpopu UNIQUE (lat, lon);
 T   ALTER TABLE ONLY public.waypoint_cache DROP CONSTRAINT uk416sq5mmcp87i65cxm4iqpopu;
       public            postgres    false    227    227            �           2606    26015 (   godo_vehicle uk8f6fmorwyw6emplt7kic8u5x9 
   CONSTRAINT     m   ALTER TABLE ONLY public.godo_vehicle
    ADD CONSTRAINT uk8f6fmorwyw6emplt7kic8u5x9 UNIQUE (chassis_number);
 R   ALTER TABLE ONLY public.godo_vehicle DROP CONSTRAINT uk8f6fmorwyw6emplt7kic8u5x9;
       public            postgres    false    219            �           2606    25991 (   vehicle_type ukd8qd4wmsbgree3l8kecvlx37s 
   CONSTRAINT     p   ALTER TABLE ONLY public.vehicle_type
    ADD CONSTRAINT ukd8qd4wmsbgree3l8kecvlx37s UNIQUE (vehicle_type_name);
 R   ALTER TABLE ONLY public.vehicle_type DROP CONSTRAINT ukd8qd4wmsbgree3l8kecvlx37s;
       public            postgres    false    217            �           2606    25984 *   vehicle_status ukdqwog18xfmdcmt0r2q7e2e5a2 
   CONSTRAINT     l   ALTER TABLE ONLY public.vehicle_status
    ADD CONSTRAINT ukdqwog18xfmdcmt0r2q7e2e5a2 UNIQUE (status_name);
 T   ALTER TABLE ONLY public.vehicle_status DROP CONSTRAINT ukdqwog18xfmdcmt0r2q7e2e5a2;
       public            postgres    false    216            �           2606    25483 &   godo_login ukhbttvxf96vt2odn15dxcvxmfb 
   CONSTRAINT     g   ALTER TABLE ONLY public.godo_login
    ADD CONSTRAINT ukhbttvxf96vt2odn15dxcvxmfb UNIQUE (session_id);
 P   ALTER TABLE ONLY public.godo_login DROP CONSTRAINT ukhbttvxf96vt2odn15dxcvxmfb;
       public            postgres    false    215            �           2606    26013 '   gender_type ukhh2xrg0d88t5vu2gkmije6r7b 
   CONSTRAINT     i   ALTER TABLE ONLY public.gender_type
    ADD CONSTRAINT ukhh2xrg0d88t5vu2gkmije6r7b UNIQUE (gender_name);
 Q   ALTER TABLE ONLY public.gender_type DROP CONSTRAINT ukhh2xrg0d88t5vu2gkmije6r7b;
       public            postgres    false    218            �           2606    26017 (   godo_vehicle ukiputppixfcbtag9499w4dkpn8 
   CONSTRAINT     r   ALTER TABLE ONLY public.godo_vehicle
    ADD CONSTRAINT ukiputppixfcbtag9499w4dkpn8 UNIQUE (registration_number);
 R   ALTER TABLE ONLY public.godo_vehicle DROP CONSTRAINT ukiputppixfcbtag9499w4dkpn8;
       public            postgres    false    219            �           2606    25481 &   godo_login ukknoe7mwimrklkwmv8dq0ofn08 
   CONSTRAINT     i   ALTER TABLE ONLY public.godo_login
    ADD CONSTRAINT ukknoe7mwimrklkwmv8dq0ofn08 UNIQUE (phone_number);
 P   ALTER TABLE ONLY public.godo_login DROP CONSTRAINT ukknoe7mwimrklkwmv8dq0ofn08;
       public            postgres    false    215            �           2606    26294 '   godo_status ukn2h9r59bfytcggqbob3ct9o04 
   CONSTRAINT     i   ALTER TABLE ONLY public.godo_status
    ADD CONSTRAINT ukn2h9r59bfytcggqbob3ct9o04 UNIQUE (status_name);
 Q   ALTER TABLE ONLY public.godo_status DROP CONSTRAINT ukn2h9r59bfytcggqbob3ct9o04;
       public            postgres    false    223            �           2606    25980 "   vehicle_status vehicle_status_pkey 
   CONSTRAINT     g   ALTER TABLE ONLY public.vehicle_status
    ADD CONSTRAINT vehicle_status_pkey PRIMARY KEY (status_id);
 L   ALTER TABLE ONLY public.vehicle_status DROP CONSTRAINT vehicle_status_pkey;
       public            postgres    false    216            �           2606    25989    vehicle_type vehicle_type_pkey 
   CONSTRAINT     i   ALTER TABLE ONLY public.vehicle_type
    ADD CONSTRAINT vehicle_type_pkey PRIMARY KEY (vehicle_type_id);
 H   ALTER TABLE ONLY public.vehicle_type DROP CONSTRAINT vehicle_type_pkey;
       public            postgres    false    217            �           2606    34530 "   waypoint_cache waypoint_cache_pkey 
   CONSTRAINT     `   ALTER TABLE ONLY public.waypoint_cache
    ADD CONSTRAINT waypoint_cache_pkey PRIMARY KEY (id);
 L   ALTER TABLE ONLY public.waypoint_cache DROP CONSTRAINT waypoint_cache_pkey;
       public            postgres    false    227            >   #   x�3��M�I�2�tK�1�9�K2R���b���� |k�      A      x������ � �      F      x�E�;�0��S�#��e���Y!�Q�"$@J���W�T�of�r(^H��k��s b����*ްv:"0,�����>�%���U'D�|<a�!6�bN���K�b|(�i�sS���5��s_�#�      ;   R  x�}TɊ�F<?}E�M���16^�`�g������?��63�n��D)�ȈT���?� �_��C|��Hy}W�V��p��9���n@^%0�9YE�"�~�������88w,�o@��^�9���k��W�.��juY�:��ez~���T�0ۋD˟AK�\�5X��Dl�u��M��ޮY֤'ͳ������y
 �0���n�e���̹4L��cԤ��R:�	mHC皔�,=�����v��xIv"~��jUƒ��v�"��t&Cӹ�S�yZO�-�����p�!r��켲y'� �=Wf���\�⣄;s�t^��*SMOh�$z`���_?���[ԝ$���W�z���8T^��2���H[�@M���ҡ�`��(�0��B��?�����z;�跁{��n���5���!t0��O��\�Cq�m}A?���R���#��@�& 1��lڧ���bmި�~M��ZW���ض���[ozX.�-�t�㌕�=B
�&�ɫ����#L^}�����M��uփWv���i�q/��!���Ad��]�b�����{�I��k�<��ڸb-�m��۶����      B   �   x�E��jA Ͻ_�إ���=ތYBА�"*B�c#FP����q�ZU�z�ZB���ƨ����~�xs�F��aQx��h�yz�?�b���yߡ%� ����}}z��̻���1)\���������-h�F+X��:J�R�X�V'6L�(:�g.YH%GO)�I�yX�>��&�a�.�04o]�4?�:�      @   R  x���;o�P����8}����pE�$����hl�Nl'�}H����V��ж3�ff�
E��)b&������������1�i\���g����>��v�r�%w��P�%�(�c�7W�J�h̖Dc.�M
V��n\}�p8����'3�a���i�����0_r�Yg�ai)[�/�3|ެ=!1�h1��Ŕam8��o��ֆ5�|5�W�{=7^��N��$)���p��_}	2rf��¾�c�b�3��2�Ԁ�R8v,�jKY�|��/O�z��s��Z�Æz�w�y���㩎Lg��������d�ےs'��TPk������m�4o����      C   O   x�3�H�K��K�2�(�O/J-.�2�t��-�I-IM�2�.MN��r�%f� ��8��Ss@lsN���l #F��� ��>      D      x������ � �      E      x������ � �      ?   B  x�u�KOA���_�{4��Lw��qmłD$� ���y�8��Z/���1����/U�U9�8o�s$bD�V|���iT�����6���uN��D,CHJ"�qEN&|r��U��m�'vkqzf���E���c읽���M����nn�H���T���fL�����s�,�A�H2*�e%[�$_�Ӑn����˓]w����;�Y_� f�9cܷ�����^;{���M���[V�U��Z��L.9��F�ayy�}�ۍ�a]ƺ�%h�y�1�ĸ	в���u~�LA|q���i+�n]�|\5s�,�b�}�KZ�Uo�d�ɀAb�Iz6IR
!"��Z�E`��XM��'��N%핱Jѳ'�$~�F��؊����X��O�f1�����X�qڲr��K�#P%����I��3�C�J�����_�����y��~\�E۾@A��{M{UA@��j\DS g�9Wqk�t`�L�Rl]���m�K������w�iC7�ۧ�^�>>��M{Ҝ� oh`&vo�1b���+S[�*��ZU]��Q1,s�o��k�fZы~m_ż�ϻ��G ד�h�M��q      <   &   x�3�H�K��K�2�tL.�,K�2���K�0c���� ��	�      =   "   x�3�t��N�2�tN,�2��HM,������ U]      G      x�}}ˎG����!ޏe�!�^�jF@7��͙&1�)�J�f���c�����Zr;������mƒˇ�~̩��?����o�o_nI�c�Y)���E�U�f*�1������,5S�Z�����Ŕ(��o*.1S\B�mw�V8�<�XG>D��{�Le.��2��{��Tg7i��wŨ��Gܾ+*k�w�}|����׷?��3 �[M3V�G� e�L@��T7�r�ɨ����&W�J��|M�?�\�˭4��R�>D�/�8�	y��Z�6��J_׍�b��00�~�=�������|~|HJa��� BOcG(��	�\Jc�1��Xh@i-�l��.�1��9�i�)~�'�q{�}|{HBd�-�:�d�w�'̦�ZB$v���ׇ4U�km&m�~�˗�￿	�Yx��)����q�<G�CqRq(ӟ>0_�X���#��A�����o��MX�����9�_�uhJ\��_-K���7�!|��˗�Y�+�@p���� z�ڻ`���S0-����(�9��]��W������ 朋�l���ry�\�L�2>v�+�d�緹�uQB���g�1n��>�*�'��XTy�~����^޾}~(ÞPMo�SӎP&c�5L�Ѱ"VDU#4�=���>����������CUc��UkƗm%ڼ�C7��T���0��(ȗ珷秷/������ QFC���Lޅ)�8>�W"��/o_�ܞ�n_���`D'P�ʻ��01yŋ|��f�}� g�O���h�>�"oA�a�.����	�e({�c�>��I�Ϸׇ��b�2��`�+ + ��=�Tw+�( ��.� nra�t�p#:O���b���-@��$v���P{�v ,��Jc������ߕE3v`����וD��'1b�/r�6���L��^�ͺ2�r�>|���X�k��� �*V��jBrq^�J^�q����R�Q}zGZ�JT�1y3�#Wڰ��=Jp�⡴��)l!�E�����ތ�,b嬄�?3�׷�����uC)�#�����|8�J�ۚ3��2LC0F�y�"V�h���[(;�\m!gT�����&��#��]Ŷ�f���UOO�4�ZM-����R�"���ץ6��j���B�oK-e�-�]��۷obP<L.5X,��9p�*��l���Nq��M1��eo�=L2�b�F�3�ݓC�8��!T�`9���Y�Ơd���w�Fډ3&����t{� 5��졣[��kQ��Ж�S��/ ͧP�m�ټ�T�n��/6!σ�L�@o�xJy���BZ�v3gD�i�I {u��/B%k�.r��`�9 �*f�1��.����`���,t9FsO0�S��T�nqK/@}�)֗k��6�:3��Z`0�뛐w��0�m¡б�W�K��9����u����m��#����b�ܰ?>��S�28���d�7:�=],�)1���:0����z�3P��ǧ��������_w0�� ��vJ�J��[MݭC�nFO-قoQ��w,(%�T ��4ܕ�vB��g��KfC({X�F���3�@(s�0 �B9�ڳE��T�˗��i;F�<�n{��zA(��+�Y I�Q:k>��QN������E�utlE[�����n>����C�����o	�xA(��}��81������^���H׵��G'�lhu(�
�s�'���?>�P���fw;�N�6�Ҋ��|����ԉ��[0��� ��ތ�Z�؇��Y�5ac��u@p�D[�8��Q���4���]ͨ�Qv�������͙�9�u�L�J'�xs.�b���m%V��?������۷̪�}��[
&O���Bk�w���^^�E����Wz�w[;�kt�T��u��k���;���B��&��8�v��Z[��2��^Jk�Z���9u��
�0۲]6T��o�;$_Ǫ��-ć��r�(�b���k��W]�.I�e��۵�z%=�`�'�̺kD�K`J�xy���z论�E��s�Su�+ kaC(�)����r�tL8�],��)����T��5˩��I��4��'�)Va<��Q�(�C��	��`Z��ڗ��ս�
���4O��S�)U���Ea+,�m����f��=���&��C=n�hfϘ}�.� ��N?���\����yq��M�ZD��R���[4�Q�Hl�c���`ݶ��|7�K�|��c��I�����,[� ۨ~��`3�6��x�����'@tJ<� j9��󛹚qrW�n����gS��x� ���! }��~>�!��_ojAC�~|H��b�I;��`�# �*W�44�$��A�����S�@Y!��3pWAc�+��z���ٚ��x�~��ԥ�m��#0w�.P�.���џ����������k0gm�Am�`���]�z��-bw�<l�I��#�$�C�u�3O���{��|��O�?_�9�%OE�n�_�,�O��xy��X�g�:4�&Wk�t�qI�$�=5D[��j�(%&\J92B0Hv����_R%��!�G<��V0��P&%�O�x�;@��B6g�Ɣ�@M�N�Q)]�BJf;�g����B8gq��bv�2�)Z^a�>ݳK�ih��
S�i�(�!�ߘ`L�/8a^�vBbf6W�B�SL��`sz�
^��db*f@�MW�9��q�X,�'�a�31�k=�Pe�C1��P⣎U��x�G"JܑE��.G"-���̿�Y:���U�Y�gr�Z��30ES@�9݁��=��zV�o)��g�8��;�R
��'WS/W��� v��IL���KGo���bsci��Yh�cf%�� ,%3Ӳ8�'XF#; s�'ʓy�	�@ܲ>�	��n$��.e��s���!Lp�j!��{(��T���~s}�ޅ1���J�=���h�C	hz�D,)Ok���a�������B�XU��L�$�m$�� ���M��K,_�ı�=3�]���x�7^�*����{\7������m�k���}�xy#�Pf�b���Rb����$)6C�p�܌}�!��#���6�epf�TN��X�ռO��
`t^�=�������)��4ڒMIL�4L�'K��\������=���ʁ���J��T�k��T�r�f73J����R1��ha�+���ɢ�ɕ@X��!	�/r�O�yl�6��=g�,�3��'�V����Ӝ#�ʕ>Y��)��"��Gτ��w��Q�k��%��>g��F�z�f�/�j4	�'��Ǭ�,S�Y�Ѓ�^ߞ޾}���V���FW5�?�*Ԛ+�/���^��}��S�,������f֖�D �f ɓm����(�qd��F��I� �\������UL�=��BZJ!����q�V�r��dZ}y��X�Q����O�)EV~��:6�2��y%[؜�n��=�Ȃ�do<�@�u��Txz{�G����qUm�7�	���T�lr���-�d%/#P/hD�4$1��'(�aT�~`G��	�c(^�c'�c`��5���ul ��pU�k�;��a�4;)X���I����w�9�+�c�%;��+�N:,"�60иB�7��!E�;�ٌ��>����a:��QG���
Q'=��#�P��e>���� �y�"'}��Ϩ9n &3�;�H�*WVap��Rӳșj�n��PB{HG�B"�+@���7��lJF�ǘ�o`jN��#��D�����@����!�L!J�1o erV�a�*���W� "t����M�HYƆН������7��B؉�"�1˞��ٱ;Lkbb�#6�n��UHE	 ���P�����t����� ��9Y}m�0-v�����()\ѨY�9�P�G���Il!o�uZ��_j(��qG����� cF�)ԫ-�PCOv�ff�t)����%�ق"!�⡰��`��/�J㺰� �~8@ܣ� ����`}�hQ��u����b�]���Z4)���8�kY�p��@5���	-g���'桰AB �9��\�2�VW���	mV#W�q�*    '}��'��g(��՜��D�]�r��
�krᐲmz�Aϧ��Ԍ��~�YTR��3�T���}(c#zb�0Ϸ��U'\�C=��嫩xى��R������**��I���qJ��>�vU��k���� ���?�i�)�]z�^w"e����.�3�CJ~�YNfAz�yn
������S��qAІ���Ql�9v�nd�.f~�!�ǆPm��k�)'��6^$�*-�䇇F�6�jC=~
�w��U���n�*�z�������;z\ <b���X�R,K>-.��������y
���4,�;��3�Z�o �9��5w1K$�X��I�#�'��hw$7�SV���-y"WN����)G�F�A+@M�2G7�F�?��P�~Xc�nO�������,\�V�����Onތ���tƣ�[��
��C.�=E9mD���lG����abK�n0'ٽ�e+JeN�jr�oM	�ƌ�y��&}+��#�E�r�%E�{e��m f|z��K����|Ji ����J�p��1�3�*GP�hjL��l�l���-J���t��&�P�IgH~tf}z�ś�7�Y�����X呐)��J��ܤlC(����jB�v+a>4���)i-{�|�P6������#��4�'�f� �N�޻�[*>���%�XVyO��ɗZjV,�x��q)��)���m�8Cy��u)�������Ϩ��bPK����y�$v_��v�vQ�c�t�Nz]����]f�Ke�"��9ޛ��F^�0�_��R]?ټ$g�?�*U�5QΆ��=�h`����9[sNE�k�6����r60�ҏ��kϏ�*��>���FE#mVT����W��)���BKw]Hq��:�����@���+8RF嚝�y��y4Tz�Ҫ�~�tt�KF��4�|��I�i�0�3]ײ�k�0���N���ԏ��U[� ���k���rG0��Dw���	öo������e�y��P"�i�7]/�]�C���#ͧi��x��T=W-���5(E�;��UQ��wplx
GER7ci5�g$���H,n���+�H���_�T�?��-E�h�z$R$z����=�)��0��#�)U`K.���T�w�4I�� zJ�/b�or:�#{��a��_���*)iKU�LҒ胐���t4S����C�G�K҂�;Q�S���uL�o�ou�l�(�5�Z ��/S�E
`W�5�̣�<���l-)�Ӳ����W��?%/!�	�q���8,)����?f�C��hq��ØlSw��u��f�r0~�8��y���<�C-֙��6���<�֚2m^����ã �xk�=���0A-lN	X������M������)d���.�O��)����Z%p
��^&����O�u=2����2�.�lL���i[FTٙ�ᐞ�4�ZgJ�G� /�ePcT'b�|�~eR���T[ʻ��>��%��m�@��ʥ�����Tux$�c�,Ĺ?A=��.�s-o &wJo���k�hmL�hM��m��zj���3$�c�Ҍ=nO���X���g���<��pX����VmL��R�ʣ�M�,��Τ��+@��BJ���g� %R��̔�m0��Ij�� F��E�f�wzGr��H.g(�P1ޣ�$+�!�<t"�ؑ2����CI�E���#�.���)[-N9L�2W�r=��.9-�\���֭�	Y��B�mf�K'����0�^�0�0f&�ĸ�
�sB�����m V�E뵗e���ט�S��/��쾑�!Ks����E�7��oO�����>b'����$J���"A3Q�B�Bj�����J���:8F��=��\k�ĳ$������?�a���N!}W+�5�E�� ICُ<@�b��Xi-�S��V�A�i8H�ħ���$��������T���%�|d-���04|��/�:U�x�b_����߰ ' !r��$uy����<@*��Cj��d�A0�d��.v�SKM��񑟲���)U�{�9צ�Cfa#3L!m)�RvH����A
��GZ�
J/�K�)��.��2-+��"���X@a�Rq������o�>�uֳ�%@�r��NQ�`�m�� H��줈���T��=Y��Vn@� �NѬm�H��&gw�+y ����|��/�~;>'2�c�w�֩�eP��� ��4�e��\#@����E�4�m:Ⰹh����'��R�-[%�'XvWV�V˿�Bϼ��[ceB��*Q��߁1��iS?w�cP�6�Nv�ߡX�0�?L���Q,T�)�~�V���#T�O�����-�0va3JІ�{�6b�ء�wP���n��)A�{+�Q�
=�C\,c��n3i��R�J�擢���uU�������Ec[v��&��0��%�P�}n�F�G���ĶT����"�&%�+�:�D`����o�ª�kd���'3�ڞPt�~�@�mc(�>Ӳu�r7W+d2g���
2��Y��]d2y�G�Nu1&�;E;*nϟ>�~}{�	/$�HPK�cA�hE�î��A�N��S0���;�j��'I��
��c͋�ajjO�J<]��cCJ*� �HBu's$M�� �I��mh3��+ϘAʼ� �̭Y0e�x?l��VGu���R���������h@H����>M���h_Jg��a�ܓ\�f
3J�!e�Ql�N�1唒�T�����^���b���%�=��ZL6Im�K�=��,���r9�����.#��l���Y��12�ϥL0�d���C&��AK/��RV{��%�A6K�����ir�v�����z1�W@��̢�b�}�v�G%��ba�tD7�Xp���[k���ʞ֒�õ)�},\ckn:J3�aY���\�_�3K������m�f�O�L>W7�a��w�a-�n[��	rׁ�X�|��H���e���$�gF���ATg��H��4�,1;>z}6-��J�=�k�V޽1E��K�K��f����i�x�
�ǌ���Z��ʥ��Zz����5vUN�:����p
R�s5�1�[a�)�P�<�c��y�0�k5�1����_�\����fX��r��B>�q<�S�9zq����;3�^Qڤ�����3X�Ò�p���o�]�Q��1�Ҽ��R!N.m;J)���T����tQ;ߟ^^>�Å�Y{�Jf�� �O���Gq4~���ۣ�m����^�ҙ.��S<(.�łk)]9�X��S�(�˯��ث#���Nq�8/A��S�(���$7Nq�g�Ȥ�¢���o�_�ܵF��D�~ؼ��f�;i��S��I_z���>z~��χ�ȝ�q�!*�x���4�3ilK~��R*{��C��z�K��)�ñ����)�X�u)Ӟ"Ʉ{�R;r����+��{�R,Q��\V����0�yh�LaU��1Q�k�e�>L<�~~X�4���s.+L��)f��d�c<c�z��k��Mu��매�m��a��1�����1�����{H ~��g�wȇ�S���27'`����.�	��H@�X�+ `�9���y���굍5Z ��v�%J�j��E�n�n�P�k���\n���˳���P�Ⱦ��~�B&�V���*�-�_Q��JN�k�].��H,t�1�{\'�u��|�;�뤹�j�l�� ���Y���D2����^�0v�nî3LrGZ�`Hx� �Zy��_�I��V����*���F�I����B���!�cVہM/f�1d|Fk�Mp�z�`�a���(�3�a�E�a:s�`�a��(�)i��cm� H���p��
�1$y�bɒ��!$y+qM� }�(��a�]�^1��d}�I*=% ���SorŬB���`�IH���T�����2ҒV��E v���t
���KrQk�������&��oWP&'i.0~�T��hc���zU��-x��v^1d:vk�N�M0�����k)⢗&�{�N1{��_Qd\&��Q��$=e��$>ܡ�:��ވ3� `  �;i�nfh0�U(�E޵wXA�v(��Ә� �餩��;�g���$_z�"����O�s��"�����A����gi�2T��/jS	�uhT$�%S(�z�"���R��;��K���XC�#���c�I��A�����M.�-�⁛���w 2/��pXq�wP�P�TG��
"�z�5AEB�W���TG��ܡȼ�"R/J_ؼ���K߫������|�f�'��]Qd���F�Pd^2�͞%v�E�a!%�$��(R߼�F���=��Ï�C$J���_�?>�>||��		(�X&T����(r#ǌ�%MsE�{Q�6�AΎ*CY�^"LW�缾��^���=�;���߿��cɚ�EZ/�(r�a�!%�d~���ޓt�}��h�ܧ�0�"��ȼ����pЉ�=˙����?z�[⁖:�d��������?>?�c�Ka����W����'iW����/��I�	*ʱF�?�
+��j��8�>�;�N��{�\�x�JDU�?O`1��	%8�m K��u2D��D|~��aǄ�J̴b����S��ؖd�e&�xq���4ԗ�ۏ�������}�{���H� $�F'πZ!�����K�G�@H���
�H�o��mX6��^}�A�s�'��`�}���i(��I�!�/��cQiG����&II��Bn�˦�d��[��>X���O&��+��d�����S(�M�V� �vL˂�&{����r{R���{{���Bjgj���nR�D' ��!�Ajq(ھ�z#�i�s�#M|�Bn��,<L6�Qna���`���'O"�E�pK�
���g�م=0玈DT�@r�����Y��A�D�6H6�Or�KL7H1���Ti�� JmN��T6����ͨ-U
�7H'$�a/�K�w�M��v �
!�ڰ��?��B�TJ����֤,����(Ui�ѥ�iVn�M�AȮ���@��.eֻ�	�^�BvK��D`ń�ѣ̥ەΑ��m�+�7jS������7��a�У�r��:���cHo�v�#����@�Qʍ�4�t.n ��Z	��|���%�0X�:A�H�IJД)ܣ��K?'�5��b�Fj�@j��h J$�"Ȭ�� �a{�Śa�$^��sH,��ݣ�O;���v�j�E^�>#�I+���.2v�+-��Ȓ�����mH����j�ZX�U9Mu�����9v}<y�ɟ00R^�R	�<
�p�)��z�~{}�<�d'�B�[���L_�A�-��������ɓ�7�G1��
�X<9boy�y�,�[R�� �:v�(
	�- 2�e6�	-� ��	 ��@6K���$�Z�\2���(�@�Tb3���kZ$R����!%�+�DV�|� I�/r�K�&�T0��#�H�$@/\[ $��-k
�Z�@"����_$R���.���b�@"��� ��ENG4�/�~� �(W����7[, �8�UB�
 �r?�؆���0����ÓgX���Z�ҫ�h���b�7CH����_�K�����&O��Ɨ�d�ȕɊ�`�	a�\p�̖���?B� ��*��,�v��ݓ�V�?+ʔ�zyB�|F�i��>B��a�y�g�˥��
e�ʟ`�t)���׷���r�Ӫ�,;��);�d�hW��j���!�U�t)1� dT�8�,k�!�-�M}PU�o�*z��"��AV[�� ��!���HH��ͨ�hImO��ئ�P����K�JIoB>1�Ȫ#f�D��!�𸺽GnC��6���$�������zv`p��>���6�!�r���Ŵ��J[4wn��!�r�ƚ���C�D���;<D�����A������A�����Rzѻ�o�Ob��+iN�@"!ei�� �����Y���0����M�hw�Q~�-|�ș�c*1���0�f�1dXn��U%��;�$�����C��v��^E�a��$���������AKi����Y�;��R�]��4�6�e�C�3� j���$K��p̼b�a2cQ�L6;-�f�~)ݺ�Ə19i�C��umy�v�۝WI�A9d�c��F'C�a��˘�l8�m"�f�y����&N���B�˨���ct��Ǐ�:��     