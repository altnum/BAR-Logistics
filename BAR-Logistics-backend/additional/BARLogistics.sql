PGDMP                         x            BARLogistics    12.4    12.4 <    b           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            c           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            d           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            e           1262    16448    BARLogistics    DATABASE     �   CREATE DATABASE "BARLogistics" WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Bulgarian_Bulgaria.1251' LC_CTYPE = 'Bulgarian_Bulgaria.1251';
    DROP DATABASE "BARLogistics";
                postgres    false            �            1259    16490    capitals    TABLE     �   CREATE TABLE public.capitals (
    name character varying NOT NULL,
    distance_from_bar integer,
    import_tax double precision
);
    DROP TABLE public.capitals;
       public         heap    postgres    false            �            1259    16518    orders    TABLE     �   CREATE TABLE public.orders (
    order_id integer NOT NULL,
    user_id integer,
    order_date date,
    ship_date date,
    status character varying
);
    DROP TABLE public.orders;
       public         heap    postgres    false            �            1259    16516    orders_order_id_seq    SEQUENCE     �   CREATE SEQUENCE public.orders_order_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.orders_order_id_seq;
       public          postgres    false    206            f           0    0    orders_order_id_seq    SEQUENCE OWNED BY     K   ALTER SEQUENCE public.orders_order_id_seq OWNED BY public.orders.order_id;
          public          postgres    false    205            �            1259    16527    orders_parts    TABLE     �   CREATE TABLE public.orders_parts (
    order_id integer NOT NULL,
    part_id bigint NOT NULL,
    quantity integer NOT NULL
);
     DROP TABLE public.orders_parts;
       public         heap    postgres    false            �            1259    16594    parts    TABLE     �   CREATE TABLE public.parts (
    part_num integer NOT NULL,
    part_name character varying,
    location character varying,
    price double precision,
    volume integer
);
    DROP TABLE public.parts;
       public         heap    postgres    false            �            1259    16471    parts_locations    TABLE     l   CREATE TABLE public.parts_locations (
    name character varying NOT NULL,
    distance_from_bar integer
);
 #   DROP TABLE public.parts_locations;
       public         heap    postgres    false            �            1259    16592    parts_parts_num_seq    SEQUENCE     �   CREATE SEQUENCE public.parts_parts_num_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.parts_parts_num_seq;
       public          postgres    false    210            g           0    0    parts_parts_num_seq    SEQUENCE OWNED BY     J   ALTER SEQUENCE public.parts_parts_num_seq OWNED BY public.parts.part_num;
          public          postgres    false    209            �            1259    16620    roles    TABLE     W   CREATE TABLE public.roles (
    id integer NOT NULL,
    name character varying(20)
);
    DROP TABLE public.roles;
       public         heap    postgres    false            �            1259    16618    roles_id_seq    SEQUENCE     �   CREATE SEQUENCE public.roles_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.roles_id_seq;
       public          postgres    false    212            h           0    0    roles_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.roles_id_seq OWNED BY public.roles.id;
          public          postgres    false    211            �            1259    16626 
   user_roles    TABLE     ^   CREATE TABLE public.user_roles (
    user_id bigint NOT NULL,
    role_id integer NOT NULL
);
    DROP TABLE public.user_roles;
       public         heap    postgres    false            �            1259    16647    users    TABLE     �   CREATE TABLE public.users (
    id integer NOT NULL,
    username character varying,
    password character varying,
    first_name character varying,
    last_name character varying,
    address character varying,
    email character varying(50)
);
    DROP TABLE public.users;
       public         heap    postgres    false            �            1259    16645    users_id_seq    SEQUENCE     �   CREATE SEQUENCE public.users_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.users_id_seq;
       public          postgres    false    215            i           0    0    users_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;
          public          postgres    false    214            �            1259    16508    vehicle_info    TABLE        CREATE TABLE public.vehicle_info (
    type character varying NOT NULL,
    fuel_consumption integer,
    avg_speed integer
);
     DROP TABLE public.vehicle_info;
       public         heap    postgres    false            �            1259    16567    vehicle_inventory    TABLE     y   CREATE TABLE public.vehicle_inventory (
    id integer NOT NULL,
    type character varying,
    availability boolean
);
 %   DROP TABLE public.vehicle_inventory;
       public         heap    postgres    false            �
           2604    16521    orders order_id    DEFAULT     r   ALTER TABLE ONLY public.orders ALTER COLUMN order_id SET DEFAULT nextval('public.orders_order_id_seq'::regclass);
 >   ALTER TABLE public.orders ALTER COLUMN order_id DROP DEFAULT;
       public          postgres    false    205    206    206            �
           2604    16597    parts part_num    DEFAULT     q   ALTER TABLE ONLY public.parts ALTER COLUMN part_num SET DEFAULT nextval('public.parts_parts_num_seq'::regclass);
 =   ALTER TABLE public.parts ALTER COLUMN part_num DROP DEFAULT;
       public          postgres    false    210    209    210            �
           2604    16623    roles id    DEFAULT     d   ALTER TABLE ONLY public.roles ALTER COLUMN id SET DEFAULT nextval('public.roles_id_seq'::regclass);
 7   ALTER TABLE public.roles ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    212    211    212            �
           2604    16650    users id    DEFAULT     d   ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);
 7   ALTER TABLE public.users ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    214    215    215            S          0    16490    capitals 
   TABLE DATA           G   COPY public.capitals (name, distance_from_bar, import_tax) FROM stdin;
    public          postgres    false    203   D       V          0    16518    orders 
   TABLE DATA           R   COPY public.orders (order_id, user_id, order_date, ship_date, status) FROM stdin;
    public          postgres    false    206   �O       W          0    16527    orders_parts 
   TABLE DATA           C   COPY public.orders_parts (order_id, part_id, quantity) FROM stdin;
    public          postgres    false    207   �O       Z          0    16594    parts 
   TABLE DATA           M   COPY public.parts (part_num, part_name, location, price, volume) FROM stdin;
    public          postgres    false    210   P       R          0    16471    parts_locations 
   TABLE DATA           B   COPY public.parts_locations (name, distance_from_bar) FROM stdin;
    public          postgres    false    202   NV       \          0    16620    roles 
   TABLE DATA           )   COPY public.roles (id, name) FROM stdin;
    public          postgres    false    212   hW       ]          0    16626 
   user_roles 
   TABLE DATA           6   COPY public.user_roles (user_id, role_id) FROM stdin;
    public          postgres    false    213   �W       _          0    16647    users 
   TABLE DATA           ^   COPY public.users (id, username, password, first_name, last_name, address, email) FROM stdin;
    public          postgres    false    215   �W       T          0    16508    vehicle_info 
   TABLE DATA           I   COPY public.vehicle_info (type, fuel_consumption, avg_speed) FROM stdin;
    public          postgres    false    204   Y       X          0    16567    vehicle_inventory 
   TABLE DATA           C   COPY public.vehicle_inventory (id, type, availability) FROM stdin;
    public          postgres    false    208   FY       j           0    0    orders_order_id_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public.orders_order_id_seq', 1, false);
          public          postgres    false    205            k           0    0    parts_parts_num_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('public.parts_parts_num_seq', 1283, true);
          public          postgres    false    209            l           0    0    roles_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.roles_id_seq', 1, false);
          public          postgres    false    211            m           0    0    users_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.users_id_seq', 5, true);
          public          postgres    false    214            �
           2606    16497    capitals capitals_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.capitals
    ADD CONSTRAINT capitals_pkey PRIMARY KEY (name);
 @   ALTER TABLE ONLY public.capitals DROP CONSTRAINT capitals_pkey;
       public            postgres    false    203            �
           2606    16548     orders_parts order_products_pkey 
   CONSTRAINT     m   ALTER TABLE ONLY public.orders_parts
    ADD CONSTRAINT order_products_pkey PRIMARY KEY (order_id, part_id);
 J   ALTER TABLE ONLY public.orders_parts DROP CONSTRAINT order_products_pkey;
       public            postgres    false    207    207            �
           2606    16523    orders orders_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.orders
    ADD CONSTRAINT orders_pkey PRIMARY KEY (order_id);
 <   ALTER TABLE ONLY public.orders DROP CONSTRAINT orders_pkey;
       public            postgres    false    206            �
           2606    16478 $   parts_locations parts_locations_pkey 
   CONSTRAINT     d   ALTER TABLE ONLY public.parts_locations
    ADD CONSTRAINT parts_locations_pkey PRIMARY KEY (name);
 N   ALTER TABLE ONLY public.parts_locations DROP CONSTRAINT parts_locations_pkey;
       public            postgres    false    202            �
           2606    16602    parts parts_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.parts
    ADD CONSTRAINT parts_pkey PRIMARY KEY (part_num);
 :   ALTER TABLE ONLY public.parts DROP CONSTRAINT parts_pkey;
       public            postgres    false    210            �
           2606    16625    roles roles_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.roles
    ADD CONSTRAINT roles_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.roles DROP CONSTRAINT roles_pkey;
       public            postgres    false    212            �
           2606    16669 !   users uk6dotkott2kjsp8vw4d0m25fb7 
   CONSTRAINT     ]   ALTER TABLE ONLY public.users
    ADD CONSTRAINT uk6dotkott2kjsp8vw4d0m25fb7 UNIQUE (email);
 K   ALTER TABLE ONLY public.users DROP CONSTRAINT uk6dotkott2kjsp8vw4d0m25fb7;
       public            postgres    false    215            �
           2606    16667 !   users ukr43af9ap4edm43mmtq01oddj6 
   CONSTRAINT     `   ALTER TABLE ONLY public.users
    ADD CONSTRAINT ukr43af9ap4edm43mmtq01oddj6 UNIQUE (username);
 K   ALTER TABLE ONLY public.users DROP CONSTRAINT ukr43af9ap4edm43mmtq01oddj6;
       public            postgres    false    215            �
           2606    16630    user_roles user_roles_pkey 
   CONSTRAINT     f   ALTER TABLE ONLY public.user_roles
    ADD CONSTRAINT user_roles_pkey PRIMARY KEY (user_id, role_id);
 D   ALTER TABLE ONLY public.user_roles DROP CONSTRAINT user_roles_pkey;
       public            postgres    false    213    213            �
           2606    16655    users users_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.users DROP CONSTRAINT users_pkey;
       public            postgres    false    215            �
           2606    16515    vehicle_info vehicle_info_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY public.vehicle_info
    ADD CONSTRAINT vehicle_info_pkey PRIMARY KEY (type);
 H   ALTER TABLE ONLY public.vehicle_info DROP CONSTRAINT vehicle_info_pkey;
       public            postgres    false    204            �
           2606    16574 (   vehicle_inventory vehicle_inventory_pkey 
   CONSTRAINT     f   ALTER TABLE ONLY public.vehicle_inventory
    ADD CONSTRAINT vehicle_inventory_pkey PRIMARY KEY (id);
 R   ALTER TABLE ONLY public.vehicle_inventory DROP CONSTRAINT vehicle_inventory_pkey;
       public            postgres    false    208            �
           2606    16608 #   orders_parts fk_orders_parts_orders    FK CONSTRAINT     �   ALTER TABLE ONLY public.orders_parts
    ADD CONSTRAINT fk_orders_parts_orders FOREIGN KEY (order_id) REFERENCES public.orders(order_id) NOT VALID;
 M   ALTER TABLE ONLY public.orders_parts DROP CONSTRAINT fk_orders_parts_orders;
       public          postgres    false    207    2747    206            �
           2606    16613 "   orders_parts fk_orders_parts_parts    FK CONSTRAINT     �   ALTER TABLE ONLY public.orders_parts
    ADD CONSTRAINT fk_orders_parts_parts FOREIGN KEY (part_id) REFERENCES public.parts(part_num) NOT VALID;
 L   ALTER TABLE ONLY public.orders_parts DROP CONSTRAINT fk_orders_parts_parts;
       public          postgres    false    2753    207    210            �
           2606    16690    orders fk_orders_users    FK CONSTRAINT        ALTER TABLE ONLY public.orders
    ADD CONSTRAINT fk_orders_users FOREIGN KEY (user_id) REFERENCES public.users(id) NOT VALID;
 @   ALTER TABLE ONLY public.orders DROP CONSTRAINT fk_orders_users;
       public          postgres    false    2763    206    215            �
           2606    16603    parts fk_parts_parts_locations    FK CONSTRAINT     �   ALTER TABLE ONLY public.parts
    ADD CONSTRAINT fk_parts_parts_locations FOREIGN KEY (location) REFERENCES public.parts_locations(name) NOT VALID;
 H   ALTER TABLE ONLY public.parts DROP CONSTRAINT fk_parts_parts_locations;
       public          postgres    false    202    210    2741            �
           2606    16661    users fk_users_capitals    FK CONSTRAINT     �   ALTER TABLE ONLY public.users
    ADD CONSTRAINT fk_users_capitals FOREIGN KEY (address) REFERENCES public.capitals(name) NOT VALID;
 A   ALTER TABLE ONLY public.users DROP CONSTRAINT fk_users_capitals;
       public          postgres    false    2743    215    203            �
           2606    16585 3   vehicle_inventory fk_vehicle_inventory_vehicle_info    FK CONSTRAINT     �   ALTER TABLE ONLY public.vehicle_inventory
    ADD CONSTRAINT fk_vehicle_inventory_vehicle_info FOREIGN KEY (type) REFERENCES public.vehicle_info(type) NOT VALID;
 ]   ALTER TABLE ONLY public.vehicle_inventory DROP CONSTRAINT fk_vehicle_inventory_vehicle_info;
       public          postgres    false    2745    208    204            �
           2606    16675 &   user_roles fkh8ciramu9cc9q3qcqiv4ue8a6    FK CONSTRAINT     �   ALTER TABLE ONLY public.user_roles
    ADD CONSTRAINT fkh8ciramu9cc9q3qcqiv4ue8a6 FOREIGN KEY (role_id) REFERENCES public.roles(id);
 P   ALTER TABLE ONLY public.user_roles DROP CONSTRAINT fkh8ciramu9cc9q3qcqiv4ue8a6;
       public          postgres    false    212    213    2755            �
           2606    16680 &   user_roles fkhfh9dx7w3ubf1co1vdev94g3f    FK CONSTRAINT     �   ALTER TABLE ONLY public.user_roles
    ADD CONSTRAINT fkhfh9dx7w3ubf1co1vdev94g3f FOREIGN KEY (user_id) REFERENCES public.users(id);
 P   ALTER TABLE ONLY public.user_roles DROP CONSTRAINT fkhfh9dx7w3ubf1co1vdev94g3f;
       public          postgres    false    2763    215    213            S   k  x�uX�r۸]3_�]6�-�M�R~$v�G<��Ԥ�	h@�����n�3�[^X�A���9ݺQ��X��n;e�8)e�4��廵��*��\*���g}k���j{R�%;l�W�^-�t�ӓb����i�mP�a{�zK��+e���:�����i�Z`��\�Mh��4�3�mݸ83^�J�j,��*�c��O�����4�:ɖ99.��h�0N^���y��|2�ړ�˥�N��G�7�<#6I��]��8*��U�5FU]�d�C��v�KEiU$d��ԎͶ�U��.*�$�WxӴzr���V5�e����q��cWE-τq��Ȟք!J�o�������5l�{����{���gq���������.���`rV��kX]o^(&q����Y�>nD�9Q��u�^(y����ygy�i|U���\��h���:�>��d�8*�/V�,�U�|�6XSJ����d�[E����"v�ut��w8��]T�)���Lؐ��6&*�s��L�|��p}��<�9��u��Aێ���5�����������9 '�|�&��V�v˴>]�f�,���f���Q�æ7�(K����_>cO*ȋN5Q�U�ŮZGf�#�X/����ؖ�^8ٵn���ݰ�N�sG�����ƨHs.��[�F��Rn��d�o�[�ߋ��x����-�ǣz1}�P���1� )dL�x��a/�\�98��;O7t�^4￾8@=ʪ���K�^o��GSQQqd��3�Eب��j>ؽٺ�{�3x!��t���v���v��H�������.���}��f���>Ei-�yą�	.�`,]���A�rb������wU	P�f�ۀ���u&�e(���~W��)�+տ�R���/p�Y"$ԣ���+��!��*�U����l�����L�4�8fk���" ���{�q8hw@�V�W�4*��Oy�4�L����ǃ�2ɅD^`���D03��kP��;Cv�{�u&P��j_ <[�w),xk6 u�0" ���R�$_W��;_Ƣ\pˈ����-�@,�>��#��=P~�����q�Iu.z�
�8͕��)ŢU��~/^Fy5�b�d`qa�����J�j���Y�c��s#�"���V�W�P\������T��7�ۇŵ2��zYq9�u��U�W�v�	 _8r��s��^�|��P`.�G}�=���[�yJK	�~F�!�j�|ʛ}���I���E(fI��$��_=������հ�/R1�%თ�k����}� ����a�#�UR,3‫�T�W3��T�ߠ�N��=k�;82C+�:��0g�@��|k<��>����O�QG��Kz�t�@�h{�@���/Ҁ��\�Y"�$����F�1ʜ/}^���j�Z�<�+��i$�����Qڴ8�N����y���eK<�ݭ�X�󪘕���V� R��(cDl�>��s(�Jp﬇��Ff��^��ko��؁{��R:)Մ#A��3ith(29joM�=u���8͖���!��C����s'BXWƟ7�w�p���Ge�Kkizhͫ&g�^����JX��>xq�*�J�"�Ƽh���s�ˎ���z���@AU7�Z�T=ϋ=Գ�_G*[`,-���wA����&Z��|�,���u��O܈RT�����oH�\�4B�80;C73[?#�!Z�v DK�r��ƽ�ۄ��V���< ���+t�JMʳ]:�l"���kZG-��\�z��GA�t9�[옔U%�ه�_t$��+�G�b�a0�z�l6P2c��Oc��Q�߫��#�s� �]\jӷ��̡�b��o���n|�����R�~6���u?'����I�Z�
�_Z�.�o}=fxqj �o������0���:ۄg5Y%N��E��#�H��Z\J���?��Nmu#:\���ޱ�U�d),w�����x�`R	�ICT��;��#�~��!1h�O�K��\j�fP�[�"�zܰw���5��,�r6�5�,��P��,�4=hJZ\�3��i�M����ճ>���B��c)q�5�rEG
P6ԛե��T�Q�H�q�ʆ��K�B`0nvD��A��!��=��0e���Ѕ*��H��3M#�?��Ohl�	�BQ}҄*�n��4?/��鸸�H��6��YZ
�fn����+�Ȋ����a���u��F�[����|�I��G0{��u)�9���U�1�Ն�r�/I��#�#�xF�V��^���A�֧��O %5�d?l;��85��|A5_��$�aAP�*W��;���#�9;M�AIS3���d;"��g��< ����R��C�݊F g�af� �aj��Z3��r9�>]7�kQ�������S|3��ѷ�y{j���h7�l�y�=�:���°�M�0`������[ew���N7(��0�l��"Y6����xuT�wr�]�zz�U�%���Oy5�QX@���ecKr�:��"���k��<� A�+�?���x63��e��ke���<�>���ӯ;5�u&�g�k�	�#ڒ	�^�Q�3�e��xKZI���T�~�K@�����R�9�d�a4��5�A�,i0���d���c�U�~��]���]+�Gv��I�����K��7όk�@=w^�J�&����)e��9�w���r#�dq�)j1X3�1��<`�J3��[G-��"b�*!-�O5v'����&ڀBs6h��Z�x���h��f/e�Z+jF-���ѧD�"��'��'e1�н�X؁~N O����f���W[�<ǀ�?��L��~!@�;����΂	ܿ����H@���bL���~�\Xd�4�؞��0��j`J~���޽{�?��$�      V   D   x�3�4�4202�54�50E0�8R�R2�ҹ��U� +.(�ON-.)2�4¡(-3/�8#5�+F��� ,      W   '   x�3�4N#.C��01�9���ANC�=... {��      Z      x�uX���8��~�Z=������L�X��� �n��Du��L��G�I)'��$IH���כ�����}P�GB*�X��>j���ͺ�x�G�Y9��3o�׾�/Hq ��ѿw���$=j?]�U@Ʒ�`�u�>�S�x�����{�x}7+�U��Oެ� V)�}s�We��f�h�z^'�����/����m_��ކM�W����wI^Sc�v�V�7�BS���l�2M��iF����v~�ӥ9�i1QMj����d5�M���C ��qƜ���
�,��d���"���vܷv��YN�s��o���nf��q��^��nWs��y7^0WԾ�����<�Κ)g曝���79n�(����x����3꜓�%����1��������5�������I\�_μ��9Q�t�8j�zb^�����-Z�b�E���B&}(��sԘQdZQS?�a"#.c����Y��	�����T�{=��Q��T��$PD7�߮�Ut1bϾW%v�-�f�����J�f�G�m����l�t%��Ͽ9��v/�j���.A���QO'����_g4 *o��S~|�g�s��.8 ^�պ&{���|gTqLv^7�O��hѳ��g�E6�I�yU��#��"�I��z�smkf��e�+p�
����K
u$�+ O�l�j<���n�%t�翉�OR�.�E%M�{х�xڻ���+ }��@��4뇽����)}�br�`�VA���U��+tx��~�����[�^o(�YW����{r���x%�
:|��Rj�J�׉'��=��iA�����l���gT����[E�)�q��a�3zEPxS������O+��l�T{�i�~�z
J}%=*K����+��ll����v�����Q��`�yV�Z�o�1�R<2ty���B�?��f0�<�u$(�qA�>G���B�7�����]޴oGc�EB(���w�ǵ"�B�6?]Wd�[�`���L0��}9��oS�**��g.J
��SU������R��#z�颃��)=�x!���X+D�B�ϐ0��4GO3J�(k:�M�Tq������7���h��~=��#��w�`��&����e�� �H~.$��vb��)��K���� G�=�b���?tX���h��¡Ќ�H��b��:4N��c��_��cy���c��_E)��thJDM�W-�S�X %�&�
��:���~G���Re't�3�c��:�A"�t��O y�S �@֛P���,���	}N&S;5�J����uE�{�-��S�Vha��nޅ�&t��q"�*�4��irM�s��
`2��J��DXU�T.#�TIW��TE�]7�^`�?'��hs���K`?̑�xG,��p�9�S#��&���s�g��2=5�G1,x��<���[l��o�A��4�>7g��<��+�C`w�z�+��+$pL�X+d������=��?�K�c����N�hٱ�������9�f�ٵ9�6�f8˝�? ��	���?~���|�      R   
  x��MN�0��oN� ��$[�RA�	�敘Ī;�D�Ƃ#q�lG�f������<���=I!p�ā�Z��81#�Y�Na�R��)٠s��H6]�91I)��ǸB�>���Tv���ޑ�쾗4۩w�,j<�l'M��0�\6����6۲p���qr'Ҧ�!�.���/�k��8,ɒZ�]�c��5Z�Z�h��M��A>�@;s��[�/��
Eo٥q.]��#��j�*��r�Vmk����GR*���<�����!�d�      \   !   x�3���q�v�2��]|=��b���� t��      ]      x�3�4�2�4�2bS ;F��� ��      _   3  x�M��n�@ ����]�l&\��;���fБK�AaP��m�M��;��Zr~ ���ɭ�GM�x�ϖ����z�1/6!�VXM��*^�Zs
��=�1Nz�����',��\���=!�D�v� �J��-����ކ�~��7�~P�?m	ಫY���d�	�3^�&cڲ�6�EŨ���-�@Cbc�WEˌm@3��LP�i��h�� P���y��c(~�_��dc��H�i�����6��ʛ���L����� i��5�hC{0�u���7WI���ڎ���C�� �z�      T   0   x�K��4�443���L.�w*-�4�441�
��44�4������ ǫ�      X   5   x�3�K��,�2���Pڄ�73�(ߩ��1E�!s�9C<��������� ���     