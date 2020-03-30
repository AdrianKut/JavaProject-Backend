SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_table_access_method = heap;

--
-- TOC entry 202 (class 1259 OID 51492)
-- Name: adresses; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.adresses (
    address_id bigint NOT NULL,
    city character varying(35) NOT NULL,
    flat_number integer,
    house_number integer NOT NULL,
    postcode character varying(6) NOT NULL,
    street character varying(35)
);


ALTER TABLE public.adresses OWNER TO postgres;

--
-- TOC entry 203 (class 1259 OID 51495)
-- Name: complaints; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.complaints (
    complaint_id bigint NOT NULL,
    complaint_status character varying(20) NOT NULL,
    description character varying(200) NOT NULL,
    notification_date timestamp without time zone NOT NULL,
    order_id bigint,
    product_id bigint
);


ALTER TABLE public.complaints OWNER TO postgres;

--
-- TOC entry 204 (class 1259 OID 51498)
-- Name: customers; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.customers (
    customer_id bigint NOT NULL,
    name character varying(25) NOT NULL,
    phone_number character varying(9) NOT NULL,
    surname character varying(25) NOT NULL,
    address_id bigint,
    user_email character varying(35)
);


ALTER TABLE public.customers OWNER TO postgres;

--
-- TOC entry 205 (class 1259 OID 51501)
-- Name: employees; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.employees (
    employee_id bigint NOT NULL,
    base_pay double precision NOT NULL,
    employment_date date NOT NULL,
    extra_pay double precision,
    name character varying(25) NOT NULL,
    "position" character varying(25) NOT NULL,
    surname character varying(25) NOT NULL,
    address_id bigint,
    user_email character varying(35)
);


ALTER TABLE public.employees OWNER TO postgres;

--
-- TOC entry 206 (class 1259 OID 51504)
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.hibernate_sequence OWNER TO postgres;

--
-- TOC entry 207 (class 1259 OID 51506)
-- Name: orders; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.orders (
    order_id bigint NOT NULL,
    order_status character varying(20),
    purchase_date timestamp without time zone NOT NULL,
    shipment_date timestamp without time zone,
    customer_id bigint,
    payment_id bigint
);


ALTER TABLE public.orders OWNER TO postgres;

--
-- TOC entry 208 (class 1259 OID 51509)
-- Name: orders_products; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.orders_products (
    order_id bigint NOT NULL,
    product_id bigint NOT NULL
);


ALTER TABLE public.orders_products OWNER TO postgres;

--
-- TOC entry 209 (class 1259 OID 51512)
-- Name: payments; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.payments (
    payment_id bigint NOT NULL,
    amount double precision NOT NULL,
    payment_date timestamp without time zone NOT NULL,
    payment_method character varying(25) NOT NULL
);


ALTER TABLE public.payments OWNER TO postgres;

--
-- TOC entry 210 (class 1259 OID 51515)
-- Name: products; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.products (
    product_id bigint NOT NULL,
    description character varying(115) NOT NULL,
    name character varying(35) NOT NULL,
    photo character varying(250) NOT NULL,
    price double precision NOT NULL,
    quantity integer NOT NULL,
    category character varying(50)
);


ALTER TABLE public.products OWNER TO postgres;

--
-- TOC entry 211 (class 1259 OID 51518)
-- Name: roles; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.roles (
    name character varying(25) NOT NULL
);


ALTER TABLE public.roles OWNER TO postgres;

--
-- TOC entry 212 (class 1259 OID 51521)
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    email character varying(35) NOT NULL,
    enabled boolean NOT NULL,
    password character varying(60) NOT NULL,
    userid bigint NOT NULL
);


ALTER TABLE public.users OWNER TO postgres;

--
-- TOC entry 213 (class 1259 OID 51524)
-- Name: users_roles; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users_roles (
    user_email character varying(35) NOT NULL,
    role_name character varying(25) NOT NULL
);


ALTER TABLE public.users_roles OWNER TO postgres;

--
-- TOC entry 214 (class 1259 OID 51527)
-- Name: users_userid_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.users_userid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.users_userid_seq OWNER TO postgres;

--
-- TOC entry 2908 (class 0 OID 0)
-- Dependencies: 214
-- Name: users_userid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.users_userid_seq OWNED BY public.users.userid;


--
-- TOC entry 2729 (class 2604 OID 51529)
-- Name: users userid; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users ALTER COLUMN userid SET DEFAULT nextval('public.users_userid_seq'::regclass);


--
-- TOC entry 2890 (class 0 OID 51492)
-- Dependencies: 202
-- Data for Name: adresses; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.adresses (address_id, city, flat_number, house_number, postcode, street) VALUES (91, 'Gdynia', NULL, 42, '81-081', 'Marszewska');
INSERT INTO public.adresses (address_id, city, flat_number, house_number, postcode, street) VALUES (93, 'Ruda Œl¹ska', 12, 100, '41-700', 'Wolnoœci');
INSERT INTO public.adresses (address_id, city, flat_number, house_number, postcode, street) VALUES (120, 'Kraków', 23, 44, '30-417', '£agiewnicka 60');
INSERT INTO public.adresses (address_id, city, flat_number, house_number, postcode, street) VALUES (124, 'Kraków', 2, 44, '30-417', '£agiewnicka 60');
INSERT INTO public.adresses (address_id, city, flat_number, house_number, postcode, street) VALUES (129, 'Brzesko', 23, 56, '39-230', 'Krakowska 60');
INSERT INTO public.adresses (address_id, city, flat_number, house_number, postcode, street) VALUES (131, 'Rzeszów', 56, 23, '45-590', 'Rzeszowska');
INSERT INTO public.adresses (address_id, city, flat_number, house_number, postcode, street) VALUES (133, 'Warszawa', NULL, 67, '56-786', 'Wroc³awska');
INSERT INTO public.adresses (address_id, city, flat_number, house_number, postcode, street) VALUES (135, 'Krosno', 7, 56, '67-453', 'Kolejowa');
INSERT INTO public.adresses (address_id, city, flat_number, house_number, postcode, street) VALUES (137, 'Lublin', 19, 45, '34-423', 'Powstanców');
INSERT INTO public.adresses (address_id, city, flat_number, house_number, postcode, street) VALUES (139, 'Warszawa', 3, 6, '56-786', 'Mazowieckiego');
INSERT INTO public.adresses (address_id, city, flat_number, house_number, postcode, street) VALUES (143, 'Krosno', 5, 26, '67-453', 'Nowaka');
INSERT INTO public.adresses (address_id, city, flat_number, house_number, postcode, street) VALUES (148, 'Brzesko', 56, 23, '39-230', 'Krakowska 60, 56');
INSERT INTO public.adresses (address_id, city, flat_number, house_number, postcode, street) VALUES (153, '£êki', 123, 23, '12-323', 'Powstanców');
INSERT INTO public.adresses (address_id, city, flat_number, house_number, postcode, street) VALUES (158, 'Krosno', 2, 34, '38-400', 'Rynek1');
INSERT INTO public.adresses (address_id, city, flat_number, house_number, postcode, street) VALUES (162, '¯aków', 1, 67, '45-689', 'Hetmañska');
INSERT INTO public.adresses (address_id, city, flat_number, house_number, postcode, street) VALUES (166, 'Brzesko', 2, 56, '39-230', 'Konrada');
INSERT INTO public.adresses (address_id, city, flat_number, house_number, postcode, street) VALUES (170, 'Krosno', NULL, 234, '67-453', 'Grunwaldzka');
INSERT INTO public.adresses (address_id, city, flat_number, house_number, postcode, street) VALUES (174, 'Rzeszów', 2, 67, '45-678', 'Ch³odna');
INSERT INTO public.adresses (address_id, city, flat_number, house_number, postcode, street) VALUES (179, 'Kraków', 2, 23, '30-417', '£agiewnicka 65');
INSERT INTO public.adresses (address_id, city, flat_number, house_number, postcode, street) VALUES (182, 'Kraków', NULL, 23, '30-417', '£agiewnicka 69');
INSERT INTO public.adresses (address_id, city, flat_number, house_number, postcode, street) VALUES (186, 'Krosno', 123, 234, '67-453', 'Nowaka');
INSERT INTO public.adresses (address_id, city, flat_number, house_number, postcode, street) VALUES (190, 'Rzeszów', 121, 234, '45-590', 'Rzeszowska, 23');
INSERT INTO public.adresses (address_id, city, flat_number, house_number, postcode, street) VALUES (194, 'Rzeszów', NULL, 45, '45-590', 'Rzeszowska');
INSERT INTO public.adresses (address_id, city, flat_number, house_number, postcode, street) VALUES (198, 'Krosno', 23, 2321, '67-453', 'Grunwaldzka');
INSERT INTO public.adresses (address_id, city, flat_number, house_number, postcode, street) VALUES (202, 'Gdynia', 45, 12, '56-679', 'Piastów');
INSERT INTO public.adresses (address_id, city, flat_number, house_number, postcode, street) VALUES (206, 'Brzesko', 2, 45, '39-230', 'Krakowska 60, 43');
INSERT INTO public.adresses (address_id, city, flat_number, house_number, postcode, street) VALUES (213, 'Brzesko', 67, 56, '39-230', 'Krakowska 60');
INSERT INTO public.adresses (address_id, city, flat_number, house_number, postcode, street) VALUES (217, '£ódz', NULL, 234, '67-454', 'Nowakowskiego');
INSERT INTO public.adresses (address_id, city, flat_number, house_number, postcode, street) VALUES (221, 'Krosno', 2, 21, '67-453', 'Grunwaldzka');
INSERT INTO public.adresses (address_id, city, flat_number, house_number, postcode, street) VALUES (226, 'Krosno', NULL, 23, '67-413', 'Kolejowa, 56');


--
-- TOC entry 2891 (class 0 OID 51495)
-- Dependencies: 203
-- Data for Name: complaints; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.complaints (complaint_id, complaint_status, description, notification_date, order_id, product_id) VALUES (128, 'Przyjêta', 'Laptop uszkodziny widoczne rysy i otarcia', '2020-03-23 11:01:26.460441', 127, 67);
INSERT INTO public.complaints (complaint_id, complaint_status, description, notification_date, order_id, product_id) VALUES (147, 'Przyjêta', 'Ekran zniszczony porysowany ', '2020-03-23 11:26:36.98909', 146, 6);
INSERT INTO public.complaints (complaint_id, complaint_status, description, notification_date, order_id, product_id) VALUES (152, 'Przyjêta', 'Z³amana wejscie HDMI', '2020-03-23 11:29:15.95179', 151, 117);
INSERT INTO public.complaints (complaint_id, complaint_status, description, notification_date, order_id, product_id) VALUES (157, 'Przyjêta', 'P³yty nie ma w paczce', '2020-03-23 11:34:04.08974', 156, 113);
INSERT INTO public.complaints (complaint_id, complaint_status, description, notification_date, order_id, product_id) VALUES (178, 'Przyjêta', 'Uszkoczona paczka', '2020-03-23 11:43:50.943206', 177, 100);
INSERT INTO public.complaints (complaint_id, complaint_status, description, notification_date, order_id, product_id) VALUES (210, 'Przyjêta', 'Widoczne œlady uszkodzeñ', '2020-03-23 14:37:07.790487', 209, 97);
INSERT INTO public.complaints (complaint_id, complaint_status, description, notification_date, order_id, product_id) VALUES (225, 'Przyjêta', 'Rysy oraz zniszczony wyœwietlacz', '2020-03-23 14:57:43.738513', 224, 68);
INSERT INTO public.complaints (complaint_id, complaint_status, description, notification_date, order_id, product_id) VALUES (232, 'Przyjêta', 'Porysowana p³yta widoczne œlady zadrapañ i otaræ', '2020-03-29 13:57:33.829762', 231, 74);


--
-- TOC entry 2892 (class 0 OID 51498)
-- Dependencies: 204
-- Data for Name: customers; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.customers (customer_id, name, phone_number, surname, address_id, user_email) VALUES (92, 'Martyna', '603771069', 'Grabowska', 91, 'klient@klient.pl');
INSERT INTO public.customers (customer_id, name, phone_number, surname, address_id, user_email) VALUES (121, 'Micha³', '568975432', 'Zydek', 120, NULL);
INSERT INTO public.customers (customer_id, name, phone_number, surname, address_id, user_email) VALUES (125, 'Micha³', '456732123', 'Zydek', 124, 'mzydek11.98@o2.pl');
INSERT INTO public.customers (customer_id, name, phone_number, surname, address_id, user_email) VALUES (140, 'Tomek', '563765987', 'Gil', 139, NULL);
INSERT INTO public.customers (customer_id, name, phone_number, surname, address_id, user_email) VALUES (144, 'Kinga', '456276252', 'Mazur', 143, 'kinga@o2.pl');
INSERT INTO public.customers (customer_id, name, phone_number, surname, address_id, user_email) VALUES (149, 'Marcela', '673541252', 'Knap', 148, 'pomarancza@o2.pl');
INSERT INTO public.customers (customer_id, name, phone_number, surname, address_id, user_email) VALUES (154, 'Marek', '345123098', 'Gliwinski', 153, 'maraton@onet.pl');
INSERT INTO public.customers (customer_id, name, phone_number, surname, address_id, user_email) VALUES (159, 'Konrad', '242765098', 'Kalin', 158, NULL);
INSERT INTO public.customers (customer_id, name, phone_number, surname, address_id, user_email) VALUES (163, 'Józef', '234467421', 'Stanek', 162, NULL);
INSERT INTO public.customers (customer_id, name, phone_number, surname, address_id, user_email) VALUES (167, 'Marcel', '234121314', '¯uk', 166, NULL);
INSERT INTO public.customers (customer_id, name, phone_number, surname, address_id, user_email) VALUES (171, 'Piotr', '456276252', 'Mazur', 170, NULL);
INSERT INTO public.customers (customer_id, name, phone_number, surname, address_id, user_email) VALUES (175, 'Maciej', '657536451', 'Kowalski', 174, 'maciej@onet.pl');
INSERT INTO public.customers (customer_id, name, phone_number, surname, address_id, user_email) VALUES (180, 'Malika', '234785643', 'Mazur', 179, 'malma@onet.pl');
INSERT INTO public.customers (customer_id, name, phone_number, surname, address_id, user_email) VALUES (183, 'Micha³', '456273252', 'Mazur', 182, NULL);
INSERT INTO public.customers (customer_id, name, phone_number, surname, address_id, user_email) VALUES (187, 'Kinga', '456276252', 'Mazur', 186, NULL);
INSERT INTO public.customers (customer_id, name, phone_number, surname, address_id, user_email) VALUES (191, 'Agata', '25631231', 'Mak', 190, 'agata@interia.pl');
INSERT INTO public.customers (customer_id, name, phone_number, surname, address_id, user_email) VALUES (195, 'Agnieszka', '256312231', 'Mak', 194, NULL);
INSERT INTO public.customers (customer_id, name, phone_number, surname, address_id, user_email) VALUES (199, 'Marcel', '456276221', 'Mazur', 198, NULL);
INSERT INTO public.customers (customer_id, name, phone_number, surname, address_id, user_email) VALUES (203, 'Micha³', '123123123', 'Markowski', 202, 'micha³@o2.pl');
INSERT INTO public.customers (customer_id, name, phone_number, surname, address_id, user_email) VALUES (207, 'Miko³aj', '674523876', 'Kowalski', 206, 'Miko³aj@interia.pl');
INSERT INTO public.customers (customer_id, name, phone_number, surname, address_id, user_email) VALUES (214, 'Piotr', '456276252', 'Mazur', 213, NULL);
INSERT INTO public.customers (customer_id, name, phone_number, surname, address_id, user_email) VALUES (218, 'Kinga', '456276252', 'Mazur', 217, NULL);
INSERT INTO public.customers (customer_id, name, phone_number, surname, address_id, user_email) VALUES (222, 'Marcin', '546372543', 'Kula', 221, 'marcin@interia.pl');
INSERT INTO public.customers (customer_id, name, phone_number, surname, address_id, user_email) VALUES (227, 'Karolina', '675987453', 'Król', 226, 'karolina@o2.pl');


--
-- TOC entry 2893 (class 0 OID 51501)
-- Dependencies: 205
-- Data for Name: employees; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.employees (employee_id, base_pay, employment_date, extra_pay, name, "position", surname, address_id, user_email) VALUES (94, 1400, '2020-03-19', NULL, 'El¿bieta', 'Pracownik obs³ugi', 'Michalska', 93, 'pracownik@pracownik.pl');
INSERT INTO public.employees (employee_id, base_pay, employment_date, extra_pay, name, "position", surname, address_id, user_email) VALUES (130, 2500, '1997-02-13', NULL, 'Marek', 'Kierownik', 'Wójcik', 129, 'marekWojcik@interia.pl');
INSERT INTO public.employees (employee_id, base_pay, employment_date, extra_pay, name, "position", surname, address_id, user_email) VALUES (132, 2300, '1967-05-23', NULL, 'Iza', 'Pracownik obs³ugi', 'Kalina', 131, 'malina23@interia.pl');
INSERT INTO public.employees (employee_id, base_pay, employment_date, extra_pay, name, "position", surname, address_id, user_email) VALUES (134, 2000, '1959-06-05', NULL, 'Kamil', 'Magazynier', 'Kruk', 133, 'kolos123@onet.pl');
INSERT INTO public.employees (employee_id, base_pay, employment_date, extra_pay, name, "position", surname, address_id, user_email) VALUES (136, 2000, '1999-05-03', NULL, 'Karolina', 'Pracownik obs³ugi', 'Gil', 135, 'karolinap@o2.pl');
INSERT INTO public.employees (employee_id, base_pay, employment_date, extra_pay, name, "position", surname, address_id, user_email) VALUES (138, 1990, '1999-02-23', NULL, 'Marek', 'Pracownik obs³ugi', 'Michoñski', 137, 'marek123@interia.pl');
INSERT INTO public.employees (employee_id, base_pay, employment_date, extra_pay, name, "position", surname, address_id, user_email) VALUES (181, 1600, '1998-04-23', NULL, 'Marek', 'Pracownik obs³ugi', 'Mazur', 179, 'malma@onet.pl');


--
-- TOC entry 2895 (class 0 OID 51506)
-- Dependencies: 207
-- Data for Name: orders; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.orders (order_id, order_status, purchase_date, shipment_date, customer_id, payment_id) VALUES (146, 'Przyjête', '2020-03-23 11:26:11.411902', NULL, 144, 145);
INSERT INTO public.orders (order_id, order_status, purchase_date, shipment_date, customer_id, payment_id) VALUES (151, 'Przyjête', '2020-03-23 11:28:55.602586', NULL, 149, 150);
INSERT INTO public.orders (order_id, order_status, purchase_date, shipment_date, customer_id, payment_id) VALUES (161, 'Przyjête', '2020-03-23 11:35:32.119874', NULL, 159, 160);
INSERT INTO public.orders (order_id, order_status, purchase_date, shipment_date, customer_id, payment_id) VALUES (165, 'Przyjête', '2020-03-23 11:36:48.810941', NULL, 163, 164);
INSERT INTO public.orders (order_id, order_status, purchase_date, shipment_date, customer_id, payment_id) VALUES (169, 'Przyjête', '2020-03-23 11:38:56.704882', NULL, 167, 168);
INSERT INTO public.orders (order_id, order_status, purchase_date, shipment_date, customer_id, payment_id) VALUES (173, 'Przyjête', '2020-03-23 11:39:44.513775', NULL, 171, 172);
INSERT INTO public.orders (order_id, order_status, purchase_date, shipment_date, customer_id, payment_id) VALUES (177, 'Przyjête', '2020-03-23 11:43:21.38522', NULL, 175, 176);
INSERT INTO public.orders (order_id, order_status, purchase_date, shipment_date, customer_id, payment_id) VALUES (127, 'Wys³ane', '2020-03-23 11:00:51.679446', '2020-02-12 13:03:00', 125, 126);
INSERT INTO public.orders (order_id, order_status, purchase_date, shipment_date, customer_id, payment_id) VALUES (142, 'W trakcie realizacji', '2020-03-23 11:22:34.810732', '1999-02-23 23:22:00', 140, 141);
INSERT INTO public.orders (order_id, order_status, purchase_date, shipment_date, customer_id, payment_id) VALUES (156, 'Wys³ane', '2020-03-23 11:33:31.361868', '1999-02-23 23:23:00', 154, 155);
INSERT INTO public.orders (order_id, order_status, purchase_date, shipment_date, customer_id, payment_id) VALUES (185, 'Przyjête', '2020-03-23 11:50:56.722703', NULL, 183, 184);
INSERT INTO public.orders (order_id, order_status, purchase_date, shipment_date, customer_id, payment_id) VALUES (189, 'Przyjête', '2020-03-23 11:54:47.035723', NULL, 187, 188);
INSERT INTO public.orders (order_id, order_status, purchase_date, shipment_date, customer_id, payment_id) VALUES (193, 'Przyjête', '2020-03-23 11:55:52.953464', NULL, 191, 192);
INSERT INTO public.orders (order_id, order_status, purchase_date, shipment_date, customer_id, payment_id) VALUES (197, 'Przyjête', '2020-03-23 11:58:58.415679', NULL, 195, 196);
INSERT INTO public.orders (order_id, order_status, purchase_date, shipment_date, customer_id, payment_id) VALUES (201, 'Przyjête', '2020-03-23 12:06:47.657483', NULL, 199, 200);
INSERT INTO public.orders (order_id, order_status, purchase_date, shipment_date, customer_id, payment_id) VALUES (205, 'Przyjête', '2020-03-23 14:34:34.965706', NULL, 203, 204);
INSERT INTO public.orders (order_id, order_status, purchase_date, shipment_date, customer_id, payment_id) VALUES (209, 'Przyjête', '2020-03-23 14:36:00.012502', NULL, 207, 208);
INSERT INTO public.orders (order_id, order_status, purchase_date, shipment_date, customer_id, payment_id) VALUES (212, 'Przyjête', '2020-03-23 14:38:56.392303', NULL, 207, 211);
INSERT INTO public.orders (order_id, order_status, purchase_date, shipment_date, customer_id, payment_id) VALUES (216, 'Przyjête', '2020-03-23 14:42:33.687526', NULL, 214, 215);
INSERT INTO public.orders (order_id, order_status, purchase_date, shipment_date, customer_id, payment_id) VALUES (220, 'Przyjête', '2020-03-23 14:49:22.868913', NULL, 218, 219);
INSERT INTO public.orders (order_id, order_status, purchase_date, shipment_date, customer_id, payment_id) VALUES (224, 'Przyjête', '2020-03-23 14:57:25.672474', NULL, 222, 223);
INSERT INTO public.orders (order_id, order_status, purchase_date, shipment_date, customer_id, payment_id) VALUES (229, 'Przyjête', '2020-03-23 15:08:16.580313', NULL, 227, 228);
INSERT INTO public.orders (order_id, order_status, purchase_date, shipment_date, customer_id, payment_id) VALUES (231, 'Przyjête', '2020-03-29 13:56:44.25319', NULL, 180, 230);


--
-- TOC entry 2896 (class 0 OID 51509)
-- Dependencies: 208
-- Data for Name: orders_products; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.orders_products (order_id, product_id) VALUES (127, 67);
INSERT INTO public.orders_products (order_id, product_id) VALUES (127, 60);
INSERT INTO public.orders_products (order_id, product_id) VALUES (142, 66);
INSERT INTO public.orders_products (order_id, product_id) VALUES (146, 6);
INSERT INTO public.orders_products (order_id, product_id) VALUES (146, 69);
INSERT INTO public.orders_products (order_id, product_id) VALUES (151, 117);
INSERT INTO public.orders_products (order_id, product_id) VALUES (156, 113);
INSERT INTO public.orders_products (order_id, product_id) VALUES (156, 114);
INSERT INTO public.orders_products (order_id, product_id) VALUES (161, 70);
INSERT INTO public.orders_products (order_id, product_id) VALUES (165, 107);
INSERT INTO public.orders_products (order_id, product_id) VALUES (169, 74);
INSERT INTO public.orders_products (order_id, product_id) VALUES (169, 71);
INSERT INTO public.orders_products (order_id, product_id) VALUES (173, 109);
INSERT INTO public.orders_products (order_id, product_id) VALUES (177, 116);
INSERT INTO public.orders_products (order_id, product_id) VALUES (177, 100);
INSERT INTO public.orders_products (order_id, product_id) VALUES (185, 101);
INSERT INTO public.orders_products (order_id, product_id) VALUES (189, 72);
INSERT INTO public.orders_products (order_id, product_id) VALUES (193, 74);
INSERT INTO public.orders_products (order_id, product_id) VALUES (193, 106);
INSERT INTO public.orders_products (order_id, product_id) VALUES (197, 7);
INSERT INTO public.orders_products (order_id, product_id) VALUES (197, 109);
INSERT INTO public.orders_products (order_id, product_id) VALUES (201, 62);
INSERT INTO public.orders_products (order_id, product_id) VALUES (205, 74);
INSERT INTO public.orders_products (order_id, product_id) VALUES (205, 95);
INSERT INTO public.orders_products (order_id, product_id) VALUES (209, 96);
INSERT INTO public.orders_products (order_id, product_id) VALUES (209, 97);
INSERT INTO public.orders_products (order_id, product_id) VALUES (209, 99);
INSERT INTO public.orders_products (order_id, product_id) VALUES (212, 98);
INSERT INTO public.orders_products (order_id, product_id) VALUES (212, 102);
INSERT INTO public.orders_products (order_id, product_id) VALUES (216, 67);
INSERT INTO public.orders_products (order_id, product_id) VALUES (220, 104);
INSERT INTO public.orders_products (order_id, product_id) VALUES (224, 103);
INSERT INTO public.orders_products (order_id, product_id) VALUES (224, 68);
INSERT INTO public.orders_products (order_id, product_id) VALUES (229, 115);
INSERT INTO public.orders_products (order_id, product_id) VALUES (229, 105);
INSERT INTO public.orders_products (order_id, product_id) VALUES (231, 74);
INSERT INTO public.orders_products (order_id, product_id) VALUES (231, 100);
INSERT INTO public.orders_products (order_id, product_id) VALUES (231, 104);


--
-- TOC entry 2897 (class 0 OID 51512)
-- Dependencies: 209
-- Data for Name: payments; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.payments (payment_id, amount, payment_date, payment_method) VALUES (122, 228, '2020-03-23 10:59:28.902228', 'P³atnoœæ przy obiorze');
INSERT INTO public.payments (payment_id, amount, payment_date, payment_method) VALUES (126, 4328, '2020-03-23 11:00:51.679446', 'Przelew');
INSERT INTO public.payments (payment_id, amount, payment_date, payment_method) VALUES (141, 5799, '2020-03-23 11:22:34.810732', 'Karta kredytowa');
INSERT INTO public.payments (payment_id, amount, payment_date, payment_method) VALUES (145, 13698, '2020-03-23 11:26:11.411902', 'Przelew');
INSERT INTO public.payments (payment_id, amount, payment_date, payment_method) VALUES (150, 45, '2020-03-23 11:28:55.602586', 'P³atnoœæ przy obiorze');
INSERT INTO public.payments (payment_id, amount, payment_date, payment_method) VALUES (155, 1328, '2020-03-23 11:33:31.361868', 'Karta kredytowa');
INSERT INTO public.payments (payment_id, amount, payment_date, payment_method) VALUES (160, 2549, '2020-03-23 11:35:32.119874', 'Przelew');
INSERT INTO public.payments (payment_id, amount, payment_date, payment_method) VALUES (164, 2149, '2020-03-23 11:36:48.810941', 'Karta kredytowa');
INSERT INTO public.payments (payment_id, amount, payment_date, payment_method) VALUES (168, 3548, '2020-03-23 11:38:56.704882', 'P³atnoœæ przy obiorze');
INSERT INTO public.payments (payment_id, amount, payment_date, payment_method) VALUES (172, 199, '2020-03-23 11:39:44.513775', 'P³atnoœæ przy obiorze');
INSERT INTO public.payments (payment_id, amount, payment_date, payment_method) VALUES (176, 1404, '2020-03-23 11:43:21.38522', 'P³atnoœæ przy obiorze');
INSERT INTO public.payments (payment_id, amount, payment_date, payment_method) VALUES (184, 679, '2020-03-23 11:50:56.722703', 'Przelew');
INSERT INTO public.payments (payment_id, amount, payment_date, payment_method) VALUES (188, 2149, '2020-03-23 11:54:47.035723', 'Karta kredytowa');
INSERT INTO public.payments (payment_id, amount, payment_date, payment_method) VALUES (192, 968, '2020-03-23 11:55:52.953464', 'P³atnoœæ przy obiorze');
INSERT INTO public.payments (payment_id, amount, payment_date, payment_method) VALUES (196, 488, '2020-03-23 11:58:58.415679', 'Karta kredytowa');
INSERT INTO public.payments (payment_id, amount, payment_date, payment_method) VALUES (200, 799, '2020-03-23 12:06:47.657483', 'Karta kredytowa');
INSERT INTO public.payments (payment_id, amount, payment_date, payment_method) VALUES (204, 2298, '2020-03-23 14:34:34.965706', 'Przelew');
INSERT INTO public.payments (payment_id, amount, payment_date, payment_method) VALUES (208, 2867, '2020-03-23 14:36:00.012502', 'P³atnoœæ przy obiorze');
INSERT INTO public.payments (payment_id, amount, payment_date, payment_method) VALUES (211, 908, '2020-03-23 14:38:56.392303', 'P³atnoœæ przy obiorze');
INSERT INTO public.payments (payment_id, amount, payment_date, payment_method) VALUES (215, 4299, '2020-03-23 14:42:33.687526', 'Przelew');
INSERT INTO public.payments (payment_id, amount, payment_date, payment_method) VALUES (219, 559, '2020-03-23 14:49:22.868913', 'Przelew');
INSERT INTO public.payments (payment_id, amount, payment_date, payment_method) VALUES (223, 3318, '2020-03-23 14:57:25.672474', 'Karta kredytowa');
INSERT INTO public.payments (payment_id, amount, payment_date, payment_method) VALUES (228, 498, '2020-03-23 15:08:16.580313', 'P³atnoœæ przy obiorze');
INSERT INTO public.payments (payment_id, amount, payment_date, payment_method) VALUES (230, 2487, '2020-03-29 13:56:44.25319', 'Przelew');


--
-- TOC entry 2898 (class 0 OID 51515)
-- Dependencies: 210
-- Data for Name: products; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.products (product_id, description, name, photo, price, quantity, category) VALUES (108, 'Podnieœ poziom rozgrywki z gamingow¹ myszk¹ MSI Clutch GM11', 'MSI Clutch GM11', 'https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2019/8/pr_2019_8_16_12_49_7_276_01.jpg', 99, 100, 'Urz¹dzenia peryferyjne');
INSERT INTO public.products (product_id, description, name, photo, price, quantity, category) VALUES (104, 'Wyposa¿ siê w broñ, która zapewni maksymaln¹ wydajnoœæ i stabilnoœæ ka¿dej rozgrywki', 'MSI B450 TOMAHAWK MAX', 'https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2019/8/pr_2019_8_2_12_56_4_381_00.jpg', 559, 98, 'Podzespo³y komputerowe');
INSERT INTO public.products (product_id, description, name, photo, price, quantity, category) VALUES (66, 'WyraŸ siebie tak, jak chcesz, dziêki nowemu urz¹dzeniu Surface Laptop 3', 'Microsoft Surface Laptop 3', 'https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2019/10/pr_2019_10_14_10_2_57_379_03.jpg', 5799, 99, 'Laptopy i komputery');
INSERT INTO public.products (product_id, description, name, photo, price, quantity, category) VALUES (6, 'Wyposa¿ony w matrycê OLED oraz rozdzielczoœæ 4K', 'Philips 65OLED854', 'https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2019/7/pr_2019_7_11_9_48_34_68_00.jpg', 9999, 97, 'Urz¹dzenia peryferyjne');
INSERT INTO public.products (product_id, description, name, photo, price, quantity, category) VALUES (69, 'Z Apple MacBook Air bêdziesz móg³ pracowaæ czy te¿ ogl¹daæ filmy przez ca³y dzieñ, dziêki wydajnej baterii', 'Apple MacBook Air', 'https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,pr_2016_4_28_12_39_43_998.jpg', 3699, 99, 'Laptopy i komputery');
INSERT INTO public.products (product_id, description, name, photo, price, quantity, category) VALUES (70, 'Dell Inspiron 3593 to laptop, który sprawdzi siê na wielu frontach.', 'Dell Inspiron 3593', 'https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2019/11/pr_2019_11_22_9_54_50_507_03.jpg', 2549, 99, 'Laptopy i komputery');
INSERT INTO public.products (product_id, description, name, photo, price, quantity, category) VALUES (107, 'Solidna konstrukcja po³¹czona z panelem IPS WQHD oferuje najlepsze doznania z gry w ka¿dym calu', 'LG 27GL850-B NanoIPS HDR10', 'https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2019/10/pr_2019_10_1_14_27_40_152_00.jpg', 2149, 99, 'Urz¹dzenia peryferyjne');
INSERT INTO public.products (product_id, description, name, photo, price, quantity, category) VALUES (106, 'Poznaj panoramiczny, niezwykle smuk³y monitor SF354, który oferuje szerokie k¹ty widzenia u³atwiaj¹ce pracê', 'Samsung S24F354FHUX', 'https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2019/9/pr_2019_9_20_12_14_14_854_07.jpg', 419, 99, 'Urz¹dzenia peryferyjne');
INSERT INTO public.products (product_id, description, name, photo, price, quantity, category) VALUES (71, 'Elegancja do pary z wygod¹ u¿ytkowania? To w³aœnie oferuje ultramobilny laptop Acer Swift 3', 'Acer Swift 3 i5-1035G1', 'https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2019/10/pr_2019_10_21_10_31_48_706_08.jpg', 2999, 99, 'Laptopy i komputery');
INSERT INTO public.products (product_id, description, name, photo, price, quantity, category) VALUES (62, 'To wszechstronny gimbal rêczny zaprojektowany do wspó³pracy z szerok¹ gam¹ sprzêtu video', 'Feiyu-Tech G6 Plus z adapterem', 'https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2019/8/pr_2019_8_20_8_48_45_871_00.jpg', 799, 2, 'Akcesoria');
INSERT INTO public.products (product_id, description, name, photo, price, quantity, category) VALUES (101, 'Intel Core i5-9400F to zaawansowany procesor 9-tej generacji pozbawiony zintegrowanego uk³adu graficznego', 'Intel Core i5-9400F', 'https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2019/7/pr_2019_7_18_12_17_33_694_00.jpg', 679, 99, 'Podzespo³y komputerowe');
INSERT INTO public.products (product_id, description, name, photo, price, quantity, category) VALUES (72, 'Poznaj HP 15s, seriê laptopów stworzonych z myœl¹ o Twojej wygodzie', 'HP 15s i3-1005G1', 'https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2020/1/pr_2020_1_16_15_14_55_404_04.jpg', 2149, 99, 'Laptopy i komputery');
INSERT INTO public.products (product_id, description, name, photo, price, quantity, category) VALUES (95, 'W tej karcie graficznej RTX 2060 GAMING Z gamingowy design zosta³ po³¹czony z nowatorsk¹ architektur¹ RTX', 'MSI GeForce RTX 2060 GAMING Z', 'https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2019/1/pr_2019_1_16_13_22_34_23_00.jpg', 1749, 99, 'Podzespo³y komputerowe');
INSERT INTO public.products (product_id, description, name, photo, price, quantity, category) VALUES (7, 'Seria M8V wyposa¿ona jest w wysokiej jakoœci kontroler i 64-warstwowe uk³ady 3D NAND', 'Plextor 512GB 2,5" SATA SSD', 'https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2018/3/pr_2018_3_16_11_19_10_369_00.jpg', 289, 98, 'Podzespo³y komputerowe');
INSERT INTO public.products (product_id, description, name, photo, price, quantity, category) VALUES (109, 'Logitech G305 LIGHTSPEED to bezprzewodowa mysz dla graczy w przystêpnej cenie', 'Logitech G305 LIGHTSPEED', 'https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2018/6/pr_2018_6_11_12_23_9_232_00.jpg', 199, 97, 'Urz¹dzenia peryferyjne');
INSERT INTO public.products (product_id, description, name, photo, price, quantity, category) VALUES (100, 'Czerpi¹c z nowatorskiej architektury Zen 2, procesor AMD Ryzen 7 3700X dysponuje olbrzymi¹ moc¹ obliczeniow¹', 'AMD Ryzen 7 3700X', 'https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2019/7/pr_2019_7_19_9_47_50_591_00.jpg', 1379, 98, 'Podzespo³y komputerowe');
INSERT INTO public.products (product_id, description, name, photo, price, quantity, category) VALUES (96, 'Wykorzystaj rewolucyjn¹ architekturê NVIDIA Turing, aby od tej pory napêdza³a Twoj¹ rozgrywkê', 'Gigabyte GeForce GTX 1660', 'https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2019/3/pr_2019_3_13_19_21_35_352_06.jpg', 1099, 99, 'Podzespo³y komputerowe');
INSERT INTO public.products (product_id, description, name, photo, price, quantity, category) VALUES (97, 'Przygotuj siê na rozgrywkê na zupe³nie nowym poziomie z MSI GeForce GTX 1660 SUPER GAMING X', 'MSI GeForce GTX 1660 SUPER', 'https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2019/10/pr_2019_10_28_12_21_7_731_00.jpg', 1399, 99, 'Podzespo³y komputerowe');
INSERT INTO public.products (product_id, description, name, photo, price, quantity, category) VALUES (99, 'Szybszy od poprzednika, dysk wykorzystuje najnowsz¹ technologiê V-NAND i zoptymalizowane oprogramowanie', 'Samsung 250GB M.2 PCIe NVMe 970 EVO', 'https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2019/2/pr_2019_2_6_13_29_4_497_03.jpg', 369, 99, 'Podzespo³y komputerowe');
INSERT INTO public.products (product_id, description, name, photo, price, quantity, category) VALUES (98, 'Do³¹cz do u¿ytkowników technologii SSD, wykorzystuj¹c dysk SSD Crucial MX500 stworzony przez firmê Micron', 'Crucial 500GB 2,5" SATA SSD MX500', 'https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2018/1/pr_2018_1_3_14_2_15_279_01.jpg', 359, 99, 'Podzespo³y komputerowe');
INSERT INTO public.products (product_id, description, name, photo, price, quantity, category) VALUES (102, 'Dzia³aj szybciej i sprawniej z procesorem Intel Core i3-8100, nale¿¹cym do generacji Coffee Lake', 'Intel Core i3-8100', 'https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2019/7/pr_2019_7_18_12_29_12_453_00.jpg', 549, 99, 'Podzespo³y komputerowe');
INSERT INTO public.products (product_id, description, name, photo, price, quantity, category) VALUES (67, 'Ten 15,6-calowy laptop zapewnia dok³adnie to, czego potrzebujesz do jeszcze bardziej wci¹gaj¹cej i p³ynniejszej gry', 'Lenovo Legion Y540-15', 'https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2018/7/pr_2018_7_30_22_9_28_870_04.jpg', 4299, 98, 'Laptopy i komputery');
INSERT INTO public.products (product_id, description, name, photo, price, quantity, category) VALUES (103, 'Wybierz sprzêt nie wymuszaj¹cy ¿adnych kompromisów i do³¹cz do elitarnego grona posiadaczy sprzêtu AORUS', 'Gigabyte B450 AORUS ELITE', 'https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2018/9/pr_2018_9_7_7_30_44_651_02.jpg', 469, 99, 'Podzespo³y komputerowe');
INSERT INTO public.products (product_id, description, name, photo, price, quantity, category) VALUES (68, 'Laptop Huawei MateBook D 14 zosta³ zaprojektowany z myœl¹ o Twojej wygodzie', 'Huawei MateBook D 14', 'https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2020/1/pr_2020_1_20_9_13_5_0_06.jpg', 2849, 99, 'Laptopy i komputery');
INSERT INTO public.products (product_id, description, name, photo, price, quantity, category) VALUES (105, 'P³yty g³ówna Gigabyte z serii Ultra Durable zwiêkszon¹ wytrzyma³oœæ na potrzeby pracy pod du¿ym obci¹¿eniem', 'Gigabyte B450M DS3H', 'https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2018/7/pr_2018_7_14_14_1_33_976_01.jpg', 339, 99, 'Podzespo³y komputerowe');
INSERT INTO public.products (product_id, description, name, photo, price, quantity, category) VALUES (74, 'Windows 10 jest tak dobrze znany i ³atwy w u¿yciu, ¿e poczujesz siê jak ekspert', 'Microsoft Windows 10 Home PL 64bit', 'https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,pr_2015_7_14_12_58_48_135.jpg', 549, 96, 'Oprogramowanie');
INSERT INTO public.products (product_id, description, name, photo, price, quantity, category) VALUES (110, 'Logitech M185 to mysz, która oferuje niezawodn¹ ³¹cznoœæ bezprzewodow¹ 2.4 GHz', 'Logitech M185 czerwona', 'https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2018/7/pr_2018_7_24_14_15_53_513_00.jpg', 59.99, 100, 'Urz¹dzenia peryferyjne');
INSERT INTO public.products (product_id, description, name, photo, price, quantity, category) VALUES (111, 'Stworzone we wspó³pracy z profesjonalnymi graczami, co zaowocowa³o optymalnym kszta³tem i sposobem wykonania', 'Razer Kraken Essential', 'https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2018/11/pr_2018_11_15_14_21_26_881_00.jpg', 159, 100, 'Urz¹dzenia peryferyjne');
INSERT INTO public.products (product_id, description, name, photo, price, quantity, category) VALUES (112, 'HD9999 od firmy ISK to s³uchawki zamkniête przeznaczone do profesjonalnego monitoringu', 'ISK HD9999 czarny', 'https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2018/8/pr_2018_8_31_15_47_5_197_00.jpg', 269, 100, 'Urz¹dzenia peryferyjne');
INSERT INTO public.products (product_id, description, name, photo, price, quantity, category) VALUES (118, 'Czytnik kart i-tek to przydatne urz¹dzenie, je¿eli dysponujemy szerok¹ gam¹ kart pamiêci', 'i-tec USB 2.0 All-in-One', 'https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2019/10/pr_2019_10_2_16_53_12_412_00.jpg', 29, 8, 'Akcesoria');
INSERT INTO public.products (product_id, description, name, photo, price, quantity, category) VALUES (119, 'Zachowaj wiêcej wspomnieñ, Karta microSDXC Evo Plus o najwiêkszej pojemnoœci', 'Samsung 128GB microSDXC Evo Plus', 'https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,pr_2017_4_12_7_54_12_773.jpg', 109, 4, 'Akcesoria');
INSERT INTO public.products (product_id, description, name, photo, price, quantity, category) VALUES (60, 'Po pod³¹czeniu do z³¹cza USB 3.0 dysk umo¿liwia nawet trzykrotnie szybszy dostêp do plików i ich zapisywanie', 'Kingston 64GB DataTraveler 100 G3', 'https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,pr_2017_3_29_9_38_34_965.jpg', 29, 98, 'Akcesoria');
INSERT INTO public.products (product_id, description, name, photo, price, quantity, category) VALUES (117, 'Przewód marki Unitek LUX HDMI wykonano w technologii HDMI 2.0', 'Unitek Kabel HDMI 2.0 - HDMI 2m', 'https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2017/11/pr_2017_11_30_12_44_22_95_00.jpg', 45, 10, 'Akcesoria');
INSERT INTO public.products (product_id, description, name, photo, price, quantity, category) VALUES (113, 'Zwiêksz wydajnoœæ swojej firmy dziêki niezbêdnym narzêdziom Office z mo¿liwoœci¹ pracy w chmurze', 'Microsoft Office 365 Business', 'https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2018/10/pr_2018_10_4_7_59_56_509_00.jpg', 649, 59, 'Oprogramowanie');
INSERT INTO public.products (product_id, description, name, photo, price, quantity, category) VALUES (114, 'Mo¿na dostosowaæ menu Start, przypinaj¹c do niego niemal wszystko, w tym aplikacje i kontakty', 'Microsoft Windows 10 PRO PL', 'https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,pr_2015_7_14_13_0_34_103.jpg', 679, 29, 'Oprogramowanie');
INSERT INTO public.products (product_id, description, name, photo, price, quantity, category) VALUES (116, 'Kabel HDMI Silver Monkey to dynamiczna i krystalicznie czysta projekcja w jakoœci 4K', 'Kabel HDMI 2.0 - HDMI 3m', 'https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2018/11/pr_2018_11_19_9_54_45_630_00.jpg', 25, 2, 'Akcesoria');
INSERT INTO public.products (product_id, description, name, photo, price, quantity, category) VALUES (115, 'Z zainstalowanym oprogramowaniem ESET NOD32 Antivirus mo¿esz korzystaæ z komputera bez obaw', 'Eset NOD32 Antivirus 1st. (24m.)', 'https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2019/10/pr_2019_10_23_10_39_36_213_00.jpg', 159, 3, 'Oprogramowanie');


--
-- TOC entry 2899 (class 0 OID 51518)
-- Dependencies: 211
-- Data for Name: roles; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.roles (name) VALUES ('admin');
INSERT INTO public.roles (name) VALUES ('klient');
INSERT INTO public.roles (name) VALUES ('pracownik');


--
-- TOC entry 2900 (class 0 OID 51521)
-- Dependencies: 212
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.users (email, enabled, password, userid) VALUES ('admin@admin.pl', true, '$2a$10$E.4ZOb3yk1tqKvdKKcAuYONugvfDjlkejl47b7uKABagUjamB68By', 7);
INSERT INTO public.users (email, enabled, password, userid) VALUES ('pracownik@pracownik.pl', true, '$2a$10$lcbDAngs.TdsO8Vrqo5A9OxCsYkGebCntjOlILc58WOu6RiQSfBb.', 8);
INSERT INTO public.users (email, enabled, password, userid) VALUES ('klient@klient.pl', true, '$2a$10$czZiLrBTSVJcbAE4KCPXYuXubnwbC.cxrvsT03rwduEcNpleb6XUe', 9);
INSERT INTO public.users (email, enabled, password, userid) VALUES ('mzydek11.98@o2.pl', true, '$2a$10$uwquLqp0xpbxB2LWsfOiduSflaflJKSOoXRd/sizBOYMfUit1VAle', 10);
INSERT INTO public.users (email, enabled, password, userid) VALUES ('mzydek11.98@interia.pl', true, '$2a$10$GKlJFebSSoGN6NfFY7lydeEK/9S8foI2bZTzlUX7l9Vrklt/8PfFq', 11);
INSERT INTO public.users (email, enabled, password, userid) VALUES ('marek123@interia.pl', true, '$2a$10$8zTQQszrP5O/voiScJBEPul6/SuwftglqhyzZWNUYWNBg.lZCYXR2', 12);
INSERT INTO public.users (email, enabled, password, userid) VALUES ('karolinap@o2.pl', true, '$2a$10$BzUoLOe.7AwI8qq5ONVZRelDu5t/uHXOHWymmfymlv6.f89Cuy8ii', 13);
INSERT INTO public.users (email, enabled, password, userid) VALUES ('marekWojcik@interia.pl', true, '$2a$10$gZ0RVe.yKTSRkN.9MDWXvuDQFh2Y5s1PMTYsNTW24e0fxYq0z1FBi', 14);
INSERT INTO public.users (email, enabled, password, userid) VALUES ('malina23@interia.pl', true, '$2a$10$U2NPvAerxXxZyf7atfJ.Ee.o1AX8atkfX8zXKevL6VoYT9usTTaAK', 15);
INSERT INTO public.users (email, enabled, password, userid) VALUES ('kolos123@onet.pl', true, '$2a$10$DQzOr9kY9sRgLIpsmY3fJuoR/b11NLjjPoyzQfq.YxvYPb5lu7LAq', 16);
INSERT INTO public.users (email, enabled, password, userid) VALUES ('kinga@o2.pl', true, '$2a$10$6hfEYQntJnex/3hTA5iSpuTAYNMzTLeeXph.hv9dihFlucO7VLPI2', 18);
INSERT INTO public.users (email, enabled, password, userid) VALUES ('zunnowak@onet.pl', true, '$2a$10$BV94ph4ClBv5ojxLNgyYsuQKXyhN5xnMNG.XVv.KiA1wj0JVKv0VG', 19);
INSERT INTO public.users (email, enabled, password, userid) VALUES ('pomarancza@o2.pl', true, '$2a$10$y/v3j1tXGYBQvpCmLFAijukGJe89D4bGT2bU7VdsC3sYmPVq9hRTi', 20);
INSERT INTO public.users (email, enabled, password, userid) VALUES ('hola@o2.pl', false, '$2a$10$znaNOO0JjKeQvar3c3OVOOcEzwWr5yFspxL76SuN60IQ03fSMJSlC', 17);
INSERT INTO public.users (email, enabled, password, userid) VALUES ('maraton@onet.pl', true, '$2a$10$mDX/m/ewMBO2aXAaeGWV1uviLXvJMaFy.Rsq1IzBf/DYaRs4NLuDe', 21);
INSERT INTO public.users (email, enabled, password, userid) VALUES ('maciej@onet.pl', true, '$2a$10$bITcWoFP9hIgD/3bUFaQXO/Uvh8ExastXecM3hvu8Wp8D7RqAMlfG', 22);
INSERT INTO public.users (email, enabled, password, userid) VALUES ('malma@onet.pl', true, '$2a$10$CX7iwgE2lXJFgxllBqIDR.Et9Ls1GdUrDjnggkWnzu0IjV.Z9pRF2', 23);
INSERT INTO public.users (email, enabled, password, userid) VALUES ('golem@o2.pl', true, '$2a$10$Xd.3JbsifoVwBefJtDombejE4qyHcDPARpKsECJueSLG3U/VRSCB6', 24);
INSERT INTO public.users (email, enabled, password, userid) VALUES ('agata@interia.pl', true, '$2a$10$GDp9.D25vxmi.ToQogTkJe8fW/OrdaloeU22QVNASSiVBAguaR6vm', 25);
INSERT INTO public.users (email, enabled, password, userid) VALUES ('micha³@o2.pl', true, '$2a$10$oijBqZu6.cZKj5J1shK1Ouyv8Nq1tKN9/KKAXbzwQ89jsYy0JQewi', 26);
INSERT INTO public.users (email, enabled, password, userid) VALUES ('Miko³aj@interia.pl', true, '$2a$10$hxG4iFiifZC2nDDEbFUz/eojtbft9r03gflno5uhtvGkG9QizE/Yu', 27);
INSERT INTO public.users (email, enabled, password, userid) VALUES ('piotr@onet.pl', true, '$2a$10$./n37qIECevkO09b8rwqXuJVjXRsFbRv6Qv3cv4t1yYKZZw6Vqc9G', 28);
INSERT INTO public.users (email, enabled, password, userid) VALUES ('marcin@interia.pl', true, '$2a$10$nQ.XbeHVfMHnJBSdMkHg8.C05NA/ewngHVDRPeELoYACg8jZuuIgy', 29);
INSERT INTO public.users (email, enabled, password, userid) VALUES ('karolina@o2.pl', true, '$2a$10$uKofshnLQ62WMcdPL0ZYoe5p8Vh6BIJzA21e.Xhh/tWDmUivDd1r2', 30);


--
-- TOC entry 2901 (class 0 OID 51524)
-- Dependencies: 213
-- Data for Name: users_roles; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.users_roles (user_email, role_name) VALUES ('admin@admin.pl', 'admin');
INSERT INTO public.users_roles (user_email, role_name) VALUES ('pracownik@pracownik.pl', 'pracownik');
INSERT INTO public.users_roles (user_email, role_name) VALUES ('klient@klient.pl', 'klient');
INSERT INTO public.users_roles (user_email, role_name) VALUES ('mzydek11.98@o2.pl', 'klient');
INSERT INTO public.users_roles (user_email, role_name) VALUES ('mzydek11.98@interia.pl', 'klient');
INSERT INTO public.users_roles (user_email, role_name) VALUES ('marek123@interia.pl', 'klient');
INSERT INTO public.users_roles (user_email, role_name) VALUES ('karolinap@o2.pl', 'klient');
INSERT INTO public.users_roles (user_email, role_name) VALUES ('marekWojcik@interia.pl', 'klient');
INSERT INTO public.users_roles (user_email, role_name) VALUES ('malina23@interia.pl', 'klient');
INSERT INTO public.users_roles (user_email, role_name) VALUES ('kolos123@onet.pl', 'klient');
INSERT INTO public.users_roles (user_email, role_name) VALUES ('kinga@o2.pl', 'klient');
INSERT INTO public.users_roles (user_email, role_name) VALUES ('zunnowak@onet.pl', 'klient');
INSERT INTO public.users_roles (user_email, role_name) VALUES ('pomarancza@o2.pl', 'klient');
INSERT INTO public.users_roles (user_email, role_name) VALUES ('maraton@onet.pl', 'klient');
INSERT INTO public.users_roles (user_email, role_name) VALUES ('maciej@onet.pl', 'klient');
INSERT INTO public.users_roles (user_email, role_name) VALUES ('malma@onet.pl', 'klient');
INSERT INTO public.users_roles (user_email, role_name) VALUES ('golem@o2.pl', 'klient');
INSERT INTO public.users_roles (user_email, role_name) VALUES ('agata@interia.pl', 'klient');
INSERT INTO public.users_roles (user_email, role_name) VALUES ('micha³@o2.pl', 'klient');
INSERT INTO public.users_roles (user_email, role_name) VALUES ('Miko³aj@interia.pl', 'klient');
INSERT INTO public.users_roles (user_email, role_name) VALUES ('piotr@onet.pl', 'klient');
INSERT INTO public.users_roles (user_email, role_name) VALUES ('marcin@interia.pl', 'klient');
INSERT INTO public.users_roles (user_email, role_name) VALUES ('karolina@o2.pl', 'klient');


--
-- TOC entry 2909 (class 0 OID 0)
-- Dependencies: 206
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.hibernate_sequence', 232, true);


--
-- TOC entry 2910 (class 0 OID 0)
-- Dependencies: 214
-- Name: users_userid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.users_userid_seq', 30, true);


--
-- TOC entry 2731 (class 2606 OID 51531)
-- Name: adresses adresses_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.adresses
    ADD CONSTRAINT adresses_pkey PRIMARY KEY (address_id);


--
-- TOC entry 2733 (class 2606 OID 51533)
-- Name: complaints complaints_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.complaints
    ADD CONSTRAINT complaints_pkey PRIMARY KEY (complaint_id);


--
-- TOC entry 2735 (class 2606 OID 51535)
-- Name: customers customers_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.customers
    ADD CONSTRAINT customers_pkey PRIMARY KEY (customer_id);


--
-- TOC entry 2739 (class 2606 OID 51537)
-- Name: employees employees_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employees
    ADD CONSTRAINT employees_pkey PRIMARY KEY (employee_id);


--
-- TOC entry 2743 (class 2606 OID 51539)
-- Name: orders orders_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT orders_pkey PRIMARY KEY (order_id);


--
-- TOC entry 2745 (class 2606 OID 51541)
-- Name: payments payments_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.payments
    ADD CONSTRAINT payments_pkey PRIMARY KEY (payment_id);


--
-- TOC entry 2747 (class 2606 OID 51543)
-- Name: products products_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.products
    ADD CONSTRAINT products_pkey PRIMARY KEY (product_id);


--
-- TOC entry 2749 (class 2606 OID 51545)
-- Name: roles roles_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.roles
    ADD CONSTRAINT roles_pkey PRIMARY KEY (name);


--
-- TOC entry 2737 (class 2606 OID 51547)
-- Name: customers uk_nf0pi09q5695yw9sbh7cwxva; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.customers
    ADD CONSTRAINT uk_nf0pi09q5695yw9sbh7cwxva UNIQUE (user_email);


--
-- TOC entry 2741 (class 2606 OID 51549)
-- Name: employees uk_nxhji9ei6u5acb3i0p57w7r4k; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employees
    ADD CONSTRAINT uk_nxhji9ei6u5acb3i0p57w7r4k UNIQUE (user_email);


--
-- TOC entry 2751 (class 2606 OID 51551)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (email);


--
-- TOC entry 2760 (class 2606 OID 51552)
-- Name: orders_products fk43vke5jd6eyasd92t3k24kdxq; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders_products
    ADD CONSTRAINT fk43vke5jd6eyasd92t3k24kdxq FOREIGN KEY (product_id) REFERENCES public.products(product_id);


--
-- TOC entry 2756 (class 2606 OID 51557)
-- Name: employees fk5lk10ox0a0lu6p6oeo1p634oo; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employees
    ADD CONSTRAINT fk5lk10ox0a0lu6p6oeo1p634oo FOREIGN KEY (address_id) REFERENCES public.adresses(address_id);


--
-- TOC entry 2762 (class 2606 OID 51562)
-- Name: users_roles fk7bxo93wyiradrtsqi2wugy9k8; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users_roles
    ADD CONSTRAINT fk7bxo93wyiradrtsqi2wugy9k8 FOREIGN KEY (user_email) REFERENCES public.users(email);


--
-- TOC entry 2758 (class 2606 OID 51567)
-- Name: orders fk8aol9f99s97mtyhij0tvfj41f; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT fk8aol9f99s97mtyhij0tvfj41f FOREIGN KEY (payment_id) REFERENCES public.payments(payment_id);


--
-- TOC entry 2761 (class 2606 OID 51572)
-- Name: orders_products fke4y1sseio787e4o5hrml7omt5; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders_products
    ADD CONSTRAINT fke4y1sseio787e4o5hrml7omt5 FOREIGN KEY (order_id) REFERENCES public.orders(order_id);


--
-- TOC entry 2763 (class 2606 OID 51577)
-- Name: users_roles fkfddtbwrqg5sal9y57yyol7579; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users_roles
    ADD CONSTRAINT fkfddtbwrqg5sal9y57yyol7579 FOREIGN KEY (role_name) REFERENCES public.roles(name);


--
-- TOC entry 2754 (class 2606 OID 51582)
-- Name: customers fkl20dxxkbb2qbwc6vr2d4rrt2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.customers
    ADD CONSTRAINT fkl20dxxkbb2qbwc6vr2d4rrt2 FOREIGN KEY (address_id) REFERENCES public.adresses(address_id);


--
-- TOC entry 2755 (class 2606 OID 51587)
-- Name: customers fklof5kjv17j4xn2bwhmn6q8ww2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.customers
    ADD CONSTRAINT fklof5kjv17j4xn2bwhmn6q8ww2 FOREIGN KEY (user_email) REFERENCES public.users(email);


--
-- TOC entry 2752 (class 2606 OID 51592)
-- Name: complaints fkpjlrneuql6bxtl982ec4w6g3l; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.complaints
    ADD CONSTRAINT fkpjlrneuql6bxtl982ec4w6g3l FOREIGN KEY (product_id) REFERENCES public.products(product_id);


--
-- TOC entry 2759 (class 2606 OID 51597)
-- Name: orders fkpxtb8awmi0dk6smoh2vp1litg; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT fkpxtb8awmi0dk6smoh2vp1litg FOREIGN KEY (customer_id) REFERENCES public.customers(customer_id);


--
-- TOC entry 2753 (class 2606 OID 51602)
-- Name: complaints fkr32fabkp363nyst1byc5txf9u; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.complaints
    ADD CONSTRAINT fkr32fabkp363nyst1byc5txf9u FOREIGN KEY (order_id) REFERENCES public.orders(order_id);


--
-- TOC entry 2757 (class 2606 OID 51607)
-- Name: employees fktp45l8mm4u3w491bgx0osboy9; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employees
    ADD CONSTRAINT fktp45l8mm4u3w491bgx0osboy9 FOREIGN KEY (user_email) REFERENCES public.users(email);


-- Completed on 2020-03-30 11:49:00

--
-- PostgreSQL database dump complete
--

