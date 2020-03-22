
--
-- Name: adresses; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.adresses (
    address_id bigint NOT NULL,
    city character varying(35) NOT NULL,
    flat_number integer,
    house_number integer NOT NULL,
    postcode character varying(6) NOT NULL,
    street character varying(35)
);


--
-- Name: complaints; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.complaints (
    complaint_id bigint NOT NULL,
    complaint_status character varying(20) NOT NULL,
    description character varying(200) NOT NULL,
    notification_date timestamp without time zone NOT NULL,
    order_id bigint,
    product_id bigint
);


--
-- Name: customers; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.customers (
    customer_id bigint NOT NULL,
    name character varying(25) NOT NULL,
    phone_number character varying(9) NOT NULL,
    surname character varying(25) NOT NULL,
    address_id bigint,
    user_email character varying(35)
);


--
-- Name: employees; Type: TABLE; Schema: public; Owner: -
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


--
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: orders; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.orders (
    order_id bigint NOT NULL,
    order_status character varying(20),
    purchase_date timestamp without time zone NOT NULL,
    shipment_date timestamp without time zone,
    customer_id bigint,
    payment_id bigint
);


--
-- Name: orders_products; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.orders_products (
    order_id bigint NOT NULL,
    product_id bigint NOT NULL
);


--
-- Name: payments; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.payments (
    payment_id bigint NOT NULL,
    amount double precision NOT NULL,
    payment_date timestamp without time zone NOT NULL,
    payment_method character varying(25) NOT NULL
);


--
-- Name: products; Type: TABLE; Schema: public; Owner: -
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


--
-- Name: roles; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.roles (
    name character varying(25) NOT NULL
);


--
-- Name: users; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.users (
    email character varying(35) NOT NULL,
    enabled boolean NOT NULL,
    password character varying(60) NOT NULL,
    userid bigint NOT NULL
);


--
-- Name: users_roles; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.users_roles (
    user_email character varying(35) NOT NULL,
    role_name character varying(25) NOT NULL
);


--
-- Name: users_userid_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.users_userid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: users_userid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.users_userid_seq OWNED BY public.users.userid;


--
-- Name: users userid; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.users ALTER COLUMN userid SET DEFAULT nextval('public.users_userid_seq'::regclass);


--
-- Data for Name: adresses; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.adresses (address_id, city, flat_number, house_number, postcode, street) VALUES (91, 'Gdynia', NULL, 42, '81-081', 'Marszewska');
INSERT INTO public.adresses (address_id, city, flat_number, house_number, postcode, street) VALUES (93, 'Ruda Śląska', 12, 100, '41-700', 'Wolności');


--
-- Data for Name: complaints; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- Data for Name: customers; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.customers (customer_id, name, phone_number, surname, address_id, user_email) VALUES (92, 'Martyna', '603771069', 'Grabowska', 91, 'klient@klient.pl');


--
-- Data for Name: employees; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.employees (employee_id, base_pay, employment_date, extra_pay, name, "position", surname, address_id, user_email) VALUES (94, 1400, '2020-03-19', NULL, 'Elżbieta', 'Pracownik obsługi', 'Michalska', 93, 'pracownik@pracownik.pl');


--
-- Data for Name: orders; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- Data for Name: orders_products; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- Data for Name: payments; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- Data for Name: products; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.products (product_id, description, name, photo, price, quantity, category) VALUES (60, 'Po podłączeniu do złącza USB 3.0 dysk umożliwia nawet trzykrotnie szybszy dostęp do plików i ich zapisywanie', 'Kingston 64GB DataTraveler 100 G3', 'https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,pr_2017_3_29_9_38_34_965.jpg', 29, 100, 'Akcesoria');
INSERT INTO public.products (product_id, description, name, photo, price, quantity, category) VALUES (66, 'Wyraź siebie tak, jak chcesz, dzięki nowemu urządzeniu Surface Laptop 3', 'Microsoft Surface Laptop 3', 'https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2019/10/pr_2019_10_14_10_2_57_379_03.jpg', 5799, 100, 'Laptopy i komputery');
INSERT INTO public.products (product_id, description, name, photo, price, quantity, category) VALUES (67, 'Ten 15,6-calowy laptop zapewnia dokładnie to, czego potrzebujesz do jeszcze bardziej wciągającej i płynniejszej gry', 'Lenovo Legion Y540-15', 'https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2018/7/pr_2018_7_30_22_9_28_870_04.jpg', 4299, 100, 'Laptopy i komputery');
INSERT INTO public.products (product_id, description, name, photo, price, quantity, category) VALUES (68, 'Laptop Huawei MateBook D 14 został zaprojektowany z myślą o Twojej wygodzie', 'Huawei MateBook D 14', 'https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2020/1/pr_2020_1_20_9_13_5_0_06.jpg', 2849, 100, 'Laptopy i komputery');
INSERT INTO public.products (product_id, description, name, photo, price, quantity, category) VALUES (69, 'Z Apple MacBook Air będziesz mógł pracować czy też oglądać filmy przez cały dzień, dzięki wydajnej baterii', 'Apple MacBook Air', 'https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,pr_2016_4_28_12_39_43_998.jpg', 3699, 100, 'Laptopy i komputery');
INSERT INTO public.products (product_id, description, name, photo, price, quantity, category) VALUES (70, 'Dell Inspiron 3593 to laptop, który sprawdzi się na wielu frontach.', 'Dell Inspiron 3593', 'https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2019/11/pr_2019_11_22_9_54_50_507_03.jpg', 2549, 100, 'Laptopy i komputery');
INSERT INTO public.products (product_id, description, name, photo, price, quantity, category) VALUES (71, 'Elegancja do pary z wygodą użytkowania? To właśnie oferuje ultramobilny laptop Acer Swift 3', 'Acer Swift 3 i5-1035G1', 'https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2019/10/pr_2019_10_21_10_31_48_706_08.jpg', 2999, 100, 'Laptopy i komputery');
INSERT INTO public.products (product_id, description, name, photo, price, quantity, category) VALUES (74, 'Windows 10 jest tak dobrze znany i łatwy w użyciu, że poczujesz się jak ekspert', 'Microsoft Windows 10 Home PL 64bit', 'https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,pr_2015_7_14_12_58_48_135.jpg', 549, 100, 'Oprogramowanie');
INSERT INTO public.products (product_id, description, name, photo, price, quantity, category) VALUES (7, 'Seria M8V wyposażona jest w wysokiej jakości kontroler i 64-warstwowe układy 3D NAND', 'Plextor 512GB 2,5" SATA SSD', 'https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2018/3/pr_2018_3_16_11_19_10_369_00.jpg', 289, 99, 'Podzespoły komputerowe');
INSERT INTO public.products (product_id, description, name, photo, price, quantity, category) VALUES (6, 'Wyposażony w matrycę OLED oraz rozdzielczość 4K', 'Philips 65OLED854', 'https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2019/7/pr_2019_7_11_9_48_34_68_00.jpg', 9999, 98, 'Urządzenia peryferyjne');
INSERT INTO public.products (product_id, description, name, photo, price, quantity, category) VALUES (100, 'Czerpiąc z nowatorskiej architektury Zen 2, procesor AMD Ryzen 7 3700X dysponuje olbrzymią mocą obliczeniową', 'AMD Ryzen 7 3700X', 'https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2019/7/pr_2019_7_19_9_47_50_591_00.jpg', 1379, 100, 'Podzespoły komputerowe');
INSERT INTO public.products (product_id, description, name, photo, price, quantity, category) VALUES (101, 'Intel Core i5-9400F to zaawansowany procesor 9-tej generacji pozbawiony zintegrowanego układu graficznego', 'Intel Core i5-9400F', 'https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2019/7/pr_2019_7_18_12_17_33_694_00.jpg', 679, 100, 'Podzespoły komputerowe');
INSERT INTO public.products (product_id, description, name, photo, price, quantity, category) VALUES (62, 'To wszechstronny gimbal ręczny zaprojektowany do współpracy z szeroką gamą sprzętu video', 'Feiyu-Tech G6 Plus z adapterem', 'https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2019/8/pr_2019_8_20_8_48_45_871_00.jpg', 799, 3, 'Akcesoria');
INSERT INTO public.products (product_id, description, name, photo, price, quantity, category) VALUES (72, 'Poznaj HP 15s, serię laptopów stworzonych z myślą o Twojej wygodzie', 'HP 15s i3-1005G1', 'https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2020/1/pr_2020_1_16_15_14_55_404_04.jpg', 2149, 100, 'Laptopy i komputery');
INSERT INTO public.products (product_id, description, name, photo, price, quantity, category) VALUES (95, 'W tej karcie graficznej RTX 2060 GAMING Z gamingowy design został połączony z nowatorską architekturą RTX', 'MSI GeForce RTX 2060 GAMING Z', 'https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2019/1/pr_2019_1_16_13_22_34_23_00.jpg', 1749, 100, 'Podzespoły komputerowe');
INSERT INTO public.products (product_id, description, name, photo, price, quantity, category) VALUES (96, 'Wykorzystaj rewolucyjną architekturę NVIDIA Turing, aby od tej pory napędzała Twoją rozgrywkę', 'Gigabyte GeForce GTX 1660', 'https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2019/3/pr_2019_3_13_19_21_35_352_06.jpg', 1099, 100, 'Podzespoły komputerowe');
INSERT INTO public.products (product_id, description, name, photo, price, quantity, category) VALUES (97, 'Przygotuj się na rozgrywkę na zupełnie nowym poziomie z MSI GeForce GTX 1660 SUPER GAMING X', 'MSI GeForce GTX 1660 SUPER', 'https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2019/10/pr_2019_10_28_12_21_7_731_00.jpg', 1399, 100, 'Podzespoły komputerowe');
INSERT INTO public.products (product_id, description, name, photo, price, quantity, category) VALUES (98, 'Dołącz do użytkowników technologii SSD, wykorzystując dysk SSD Crucial MX500 stworzony przez firmę Micron', 'Crucial 500GB 2,5" SATA SSD MX500', 'https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2018/1/pr_2018_1_3_14_2_15_279_01.jpg', 359, 100, 'Podzespoły komputerowe');
INSERT INTO public.products (product_id, description, name, photo, price, quantity, category) VALUES (99, 'Szybszy od poprzednika, dysk wykorzystuje najnowszą technologię V-NAND i zoptymalizowane oprogramowanie', 'Samsung 250GB M.2 PCIe NVMe 970 EVO', 'https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2019/2/pr_2019_2_6_13_29_4_497_03.jpg', 369, 100, 'Podzespoły komputerowe');
INSERT INTO public.products (product_id, description, name, photo, price, quantity, category) VALUES (102, 'Działaj szybciej i sprawniej z procesorem Intel Core i3-8100, należącym do generacji Coffee Lake', 'Intel Core i3-8100', 'https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2019/7/pr_2019_7_18_12_29_12_453_00.jpg', 549, 100, 'Podzespoły komputerowe');
INSERT INTO public.products (product_id, description, name, photo, price, quantity, category) VALUES (103, 'Wybierz sprzęt nie wymuszający żadnych kompromisów i dołącz do elitarnego grona posiadaczy sprzętu AORUS', 'Gigabyte B450 AORUS ELITE', 'https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2018/9/pr_2018_9_7_7_30_44_651_02.jpg', 469, 100, 'Podzespoły komputerowe');
INSERT INTO public.products (product_id, description, name, photo, price, quantity, category) VALUES (104, 'Wyposaż się w broń, która zapewni maksymalną wydajność i stabilność każdej rozgrywki', 'MSI B450 TOMAHAWK MAX', 'https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2019/8/pr_2019_8_2_12_56_4_381_00.jpg', 559, 100, 'Podzespoły komputerowe');
INSERT INTO public.products (product_id, description, name, photo, price, quantity, category) VALUES (105, 'Płyty główna Gigabyte z serii Ultra Durable zwiększoną wytrzymałość na potrzeby pracy pod dużym obciążeniem', 'Gigabyte B450M DS3H', 'https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2018/7/pr_2018_7_14_14_1_33_976_01.jpg', 339, 100, 'Podzespoły komputerowe');
INSERT INTO public.products (product_id, description, name, photo, price, quantity, category) VALUES (106, 'Poznaj panoramiczny, niezwykle smukły monitor SF354, który oferuje szerokie kąty widzenia ułatwiające pracę', 'Samsung S24F354FHUX', 'https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2019/9/pr_2019_9_20_12_14_14_854_07.jpg', 419, 100, 'Urządzenia peryferyjne');
INSERT INTO public.products (product_id, description, name, photo, price, quantity, category) VALUES (107, 'Solidna konstrukcja połączona z panelem IPS WQHD oferuje najlepsze doznania z gry w każdym calu', 'LG 27GL850-B NanoIPS HDR10', 'https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2019/10/pr_2019_10_1_14_27_40_152_00.jpg', 2149, 100, 'Urządzenia peryferyjne');
INSERT INTO public.products (product_id, description, name, photo, price, quantity, category) VALUES (108, 'Podnieś poziom rozgrywki z gamingową myszką MSI Clutch GM11', 'MSI Clutch GM11', 'https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2019/8/pr_2019_8_16_12_49_7_276_01.jpg', 99, 100, 'Urządzenia peryferyjne');
INSERT INTO public.products (product_id, description, name, photo, price, quantity, category) VALUES (109, 'Logitech G305 LIGHTSPEED to bezprzewodowa mysz dla graczy w przystępnej cenie', 'Logitech G305 LIGHTSPEED', 'https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2018/6/pr_2018_6_11_12_23_9_232_00.jpg', 199, 100, 'Urządzenia peryferyjne');
INSERT INTO public.products (product_id, description, name, photo, price, quantity, category) VALUES (110, 'Logitech M185 to mysz, która oferuje niezawodną łączność bezprzewodową 2.4 GHz', 'Logitech M185 czerwona', 'https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2018/7/pr_2018_7_24_14_15_53_513_00.jpg', 59.990000000000002, 100, 'Urządzenia peryferyjne');
INSERT INTO public.products (product_id, description, name, photo, price, quantity, category) VALUES (111, 'Stworzone we współpracy z profesjonalnymi graczami, co zaowocowało optymalnym kształtem i sposobem wykonania', 'Razer Kraken Essential', 'https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2018/11/pr_2018_11_15_14_21_26_881_00.jpg', 159, 100, 'Urządzenia peryferyjne');
INSERT INTO public.products (product_id, description, name, photo, price, quantity, category) VALUES (112, 'HD9999 od firmy ISK to słuchawki zamknięte przeznaczone do profesjonalnego monitoringu', 'ISK HD9999 czarny', 'https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2018/8/pr_2018_8_31_15_47_5_197_00.jpg', 269, 100, 'Urządzenia peryferyjne');
INSERT INTO public.products (product_id, description, name, photo, price, quantity, category) VALUES (113, 'Zwiększ wydajność swojej firmy dzięki niezbędnym narzędziom Office z możliwością pracy w chmurze', 'Microsoft Office 365 Business', 'https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2018/10/pr_2018_10_4_7_59_56_509_00.jpg', 649, 60, 'Oprogramowanie');
INSERT INTO public.products (product_id, description, name, photo, price, quantity, category) VALUES (114, 'Można dostosować menu Start, przypinając do niego niemal wszystko, w tym aplikacje i kontakty', 'Microsoft Windows 10 PRO PL', 'https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,pr_2015_7_14_13_0_34_103.jpg', 679, 30, 'Oprogramowanie');
INSERT INTO public.products (product_id, description, name, photo, price, quantity, category) VALUES (115, 'Z zainstalowanym oprogramowaniem ESET NOD32 Antivirus możesz korzystać z komputera bez obaw', 'Eset NOD32 Antivirus 1st. (24m.)', 'https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2019/10/pr_2019_10_23_10_39_36_213_00.jpg', 159, 4, 'Oprogramowanie');
INSERT INTO public.products (product_id, description, name, photo, price, quantity, category) VALUES (116, 'Kabel HDMI Silver Monkey to dynamiczna i krystalicznie czysta projekcja w jakości 4K', 'Kabel HDMI 2.0 - HDMI 3m', 'https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2018/11/pr_2018_11_19_9_54_45_630_00.jpg', 25, 3, 'Akcesoria');
INSERT INTO public.products (product_id, description, name, photo, price, quantity, category) VALUES (117, 'Przewód marki Unitek LUX HDMI wykonano w technologii HDMI 2.0', 'Unitek Kabel HDMI 2.0 - HDMI 2m', 'https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2017/11/pr_2017_11_30_12_44_22_95_00.jpg', 45, 11, 'Akcesoria');
INSERT INTO public.products (product_id, description, name, photo, price, quantity, category) VALUES (118, 'Czytnik kart i-tek to przydatne urządzenie, jeżeli dysponujemy szeroką gamą kart pamięci', 'i-tec USB 2.0 All-in-One', 'https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2019/10/pr_2019_10_2_16_53_12_412_00.jpg', 29, 8, 'Akcesoria');
INSERT INTO public.products (product_id, description, name, photo, price, quantity, category) VALUES (119, 'Zachowaj więcej wspomnień, Karta microSDXC Evo Plus o największej pojemności', 'Samsung 128GB microSDXC Evo Plus', 'https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,pr_2017_4_12_7_54_12_773.jpg', 109, 4, 'Akcesoria');


--
-- Data for Name: roles; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.roles (name) VALUES ('admin');
INSERT INTO public.roles (name) VALUES ('klient');
INSERT INTO public.roles (name) VALUES ('pracownik');


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.users (email, enabled, password, userid) VALUES ('admin@admin.pl', true, '$2a$10$E.4ZOb3yk1tqKvdKKcAuYONugvfDjlkejl47b7uKABagUjamB68By', 7);
INSERT INTO public.users (email, enabled, password, userid) VALUES ('pracownik@pracownik.pl', true, '$2a$10$lcbDAngs.TdsO8Vrqo5A9OxCsYkGebCntjOlILc58WOu6RiQSfBb.', 8);
INSERT INTO public.users (email, enabled, password, userid) VALUES ('klient@klient.pl', true, '$2a$10$czZiLrBTSVJcbAE4KCPXYuXubnwbC.cxrvsT03rwduEcNpleb6XUe', 9);


--
-- Data for Name: users_roles; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.users_roles (user_email, role_name) VALUES ('admin@admin.pl', 'admin');
INSERT INTO public.users_roles (user_email, role_name) VALUES ('pracownik@pracownik.pl', 'pracownik');
INSERT INTO public.users_roles (user_email, role_name) VALUES ('klient@klient.pl', 'klient');


--
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.hibernate_sequence', 119, true);


--
-- Name: users_userid_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.users_userid_seq', 9, true);


--
-- Name: adresses adresses_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.adresses
    ADD CONSTRAINT adresses_pkey PRIMARY KEY (address_id);


--
-- Name: complaints complaints_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.complaints
    ADD CONSTRAINT complaints_pkey PRIMARY KEY (complaint_id);


--
-- Name: customers customers_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.customers
    ADD CONSTRAINT customers_pkey PRIMARY KEY (customer_id);


--
-- Name: employees employees_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.employees
    ADD CONSTRAINT employees_pkey PRIMARY KEY (employee_id);


--
-- Name: orders orders_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT orders_pkey PRIMARY KEY (order_id);


--
-- Name: payments payments_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.payments
    ADD CONSTRAINT payments_pkey PRIMARY KEY (payment_id);


--
-- Name: products products_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.products
    ADD CONSTRAINT products_pkey PRIMARY KEY (product_id);


--
-- Name: roles roles_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.roles
    ADD CONSTRAINT roles_pkey PRIMARY KEY (name);


--
-- Name: customers uk_nf0pi09q5695yw9sbh7cwxva; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.customers
    ADD CONSTRAINT uk_nf0pi09q5695yw9sbh7cwxva UNIQUE (user_email);


--
-- Name: employees uk_nxhji9ei6u5acb3i0p57w7r4k; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.employees
    ADD CONSTRAINT uk_nxhji9ei6u5acb3i0p57w7r4k UNIQUE (user_email);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (email);


--
-- Name: orders_products fk43vke5jd6eyasd92t3k24kdxq; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.orders_products
    ADD CONSTRAINT fk43vke5jd6eyasd92t3k24kdxq FOREIGN KEY (product_id) REFERENCES public.products(product_id);


--
-- Name: employees fk5lk10ox0a0lu6p6oeo1p634oo; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.employees
    ADD CONSTRAINT fk5lk10ox0a0lu6p6oeo1p634oo FOREIGN KEY (address_id) REFERENCES public.adresses(address_id);


--
-- Name: users_roles fk7bxo93wyiradrtsqi2wugy9k8; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.users_roles
    ADD CONSTRAINT fk7bxo93wyiradrtsqi2wugy9k8 FOREIGN KEY (user_email) REFERENCES public.users(email);


--
-- Name: orders fk8aol9f99s97mtyhij0tvfj41f; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT fk8aol9f99s97mtyhij0tvfj41f FOREIGN KEY (payment_id) REFERENCES public.payments(payment_id);


--
-- Name: orders_products fke4y1sseio787e4o5hrml7omt5; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.orders_products
    ADD CONSTRAINT fke4y1sseio787e4o5hrml7omt5 FOREIGN KEY (order_id) REFERENCES public.orders(order_id);


--
-- Name: users_roles fkfddtbwrqg5sal9y57yyol7579; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.users_roles
    ADD CONSTRAINT fkfddtbwrqg5sal9y57yyol7579 FOREIGN KEY (role_name) REFERENCES public.roles(name);


--
-- Name: customers fkl20dxxkbb2qbwc6vr2d4rrt2; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.customers
    ADD CONSTRAINT fkl20dxxkbb2qbwc6vr2d4rrt2 FOREIGN KEY (address_id) REFERENCES public.adresses(address_id);


--
-- Name: customers fklof5kjv17j4xn2bwhmn6q8ww2; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.customers
    ADD CONSTRAINT fklof5kjv17j4xn2bwhmn6q8ww2 FOREIGN KEY (user_email) REFERENCES public.users(email);


--
-- Name: complaints fkpjlrneuql6bxtl982ec4w6g3l; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.complaints
    ADD CONSTRAINT fkpjlrneuql6bxtl982ec4w6g3l FOREIGN KEY (product_id) REFERENCES public.products(product_id);


--
-- Name: orders fkpxtb8awmi0dk6smoh2vp1litg; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT fkpxtb8awmi0dk6smoh2vp1litg FOREIGN KEY (customer_id) REFERENCES public.customers(customer_id);


--
-- Name: complaints fkr32fabkp363nyst1byc5txf9u; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.complaints
    ADD CONSTRAINT fkr32fabkp363nyst1byc5txf9u FOREIGN KEY (order_id) REFERENCES public.orders(order_id);


--
-- Name: employees fktp45l8mm4u3w491bgx0osboy9; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.employees
    ADD CONSTRAINT fktp45l8mm4u3w491bgx0osboy9 FOREIGN KEY (user_email) REFERENCES public.users(email);


--
-- PostgreSQL database dump complete
--

