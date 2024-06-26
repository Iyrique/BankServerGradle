PGDMP         5            
    {            bank.db    14.5    14.5 V    E           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            F           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            G           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            H           1262    24893    bank.db    DATABASE     f   CREATE DATABASE "bank.db" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'Russian_Russia.1251';
    DROP DATABASE "bank.db";
                postgres    false            �            1259    24894    account    TABLE     y   CREATE TABLE public.account (
    account_id integer NOT NULL,
    balance text,
    card_account_id integer NOT NULL
);
    DROP TABLE public.account;
       public         heap    postgres    false            �            1259    24899    Account_account_id_seq    SEQUENCE     �   CREATE SEQUENCE public."Account_account_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 /   DROP SEQUENCE public."Account_account_id_seq";
       public          postgres    false    209            I           0    0    Account_account_id_seq    SEQUENCE OWNED BY     S   ALTER SEQUENCE public."Account_account_id_seq" OWNED BY public.account.account_id;
          public          postgres    false    210            �            1259    24900    Account_card_account_id_seq    SEQUENCE     �   CREATE SEQUENCE public."Account_card_account_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 4   DROP SEQUENCE public."Account_card_account_id_seq";
       public          postgres    false    209            J           0    0    Account_card_account_id_seq    SEQUENCE OWNED BY     ]   ALTER SEQUENCE public."Account_card_account_id_seq" OWNED BY public.account.card_account_id;
          public          postgres    false    211            �            1259    24901    bank    TABLE     O   CREATE TABLE public.bank (
    bank_id integer NOT NULL,
    bank_name text
);
    DROP TABLE public.bank;
       public         heap    postgres    false            �            1259    24906    Bank_bank_id_seq    SEQUENCE     �   CREATE SEQUENCE public."Bank_bank_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public."Bank_bank_id_seq";
       public          postgres    false    212            K           0    0    Bank_bank_id_seq    SEQUENCE OWNED BY     G   ALTER SEQUENCE public."Bank_bank_id_seq" OWNED BY public.bank.bank_id;
          public          postgres    false    213            �            1259    24907    card_accounts    TABLE     �   CREATE TABLE public.card_accounts (
    account_id integer NOT NULL,
    client_id integer NOT NULL,
    acc_number text,
    code_word text
);
 !   DROP TABLE public.card_accounts;
       public         heap    postgres    false            �            1259    24912    CardAccount_account_id_seq    SEQUENCE     �   CREATE SEQUENCE public."CardAccount_account_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 3   DROP SEQUENCE public."CardAccount_account_id_seq";
       public          postgres    false    214            L           0    0    CardAccount_account_id_seq    SEQUENCE OWNED BY     ]   ALTER SEQUENCE public."CardAccount_account_id_seq" OWNED BY public.card_accounts.account_id;
          public          postgres    false    215            �            1259    24913    CardAccount_client_id_seq    SEQUENCE     �   CREATE SEQUENCE public."CardAccount_client_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 2   DROP SEQUENCE public."CardAccount_client_id_seq";
       public          postgres    false    214            M           0    0    CardAccount_client_id_seq    SEQUENCE OWNED BY     [   ALTER SEQUENCE public."CardAccount_client_id_seq" OWNED BY public.card_accounts.client_id;
          public          postgres    false    216            �            1259    24914    cards    TABLE     �   CREATE TABLE public.cards (
    card_id integer NOT NULL,
    client_id integer NOT NULL,
    card_number text,
    card_period text,
    person_name text,
    cvv text,
    code_for_cvv text,
    pin text
);
    DROP TABLE public.cards;
       public         heap    postgres    false            �            1259    24919    Card_card_id_seq    SEQUENCE     �   CREATE SEQUENCE public."Card_card_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public."Card_card_id_seq";
       public          postgres    false    217            N           0    0    Card_card_id_seq    SEQUENCE OWNED BY     H   ALTER SEQUENCE public."Card_card_id_seq" OWNED BY public.cards.card_id;
          public          postgres    false    218            �            1259    24920    Card_client_id_seq    SEQUENCE     �   CREATE SEQUENCE public."Card_client_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE public."Card_client_id_seq";
       public          postgres    false    217            O           0    0    Card_client_id_seq    SEQUENCE OWNED BY     L   ALTER SEQUENCE public."Card_client_id_seq" OWNED BY public.cards.client_id;
          public          postgres    false    219            �            1259    24921    clients    TABLE     �   CREATE TABLE public.clients (
    client_id integer NOT NULL,
    bank_id integer NOT NULL,
    client_name text,
    birthday text
);
    DROP TABLE public.clients;
       public         heap    postgres    false            �            1259    24926    Clients_bank_id_seq    SEQUENCE     �   CREATE SEQUENCE public."Clients_bank_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ,   DROP SEQUENCE public."Clients_bank_id_seq";
       public          postgres    false    220            P           0    0    Clients_bank_id_seq    SEQUENCE OWNED BY     M   ALTER SEQUENCE public."Clients_bank_id_seq" OWNED BY public.clients.bank_id;
          public          postgres    false    221            �            1259    24927    credits    TABLE     �   CREATE TABLE public.credits (
    credit_id integer NOT NULL,
    client_id integer NOT NULL,
    credit_sum text,
    credit_percent text,
    payment text,
    period text,
    requisites text
);
    DROP TABLE public.credits;
       public         heap    postgres    false            �            1259    24932    Credits_client_id_seq    SEQUENCE     �   CREATE SEQUENCE public."Credits_client_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 .   DROP SEQUENCE public."Credits_client_id_seq";
       public          postgres    false    222            Q           0    0    Credits_client_id_seq    SEQUENCE OWNED BY     Q   ALTER SEQUENCE public."Credits_client_id_seq" OWNED BY public.credits.client_id;
          public          postgres    false    223            �            1259    24933    Credits_credit_id_seq    SEQUENCE     �   CREATE SEQUENCE public."Credits_credit_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 .   DROP SEQUENCE public."Credits_credit_id_seq";
       public          postgres    false    222            R           0    0    Credits_credit_id_seq    SEQUENCE OWNED BY     Q   ALTER SEQUENCE public."Credits_credit_id_seq" OWNED BY public.credits.credit_id;
          public          postgres    false    224            �            1259    24934    deposits    TABLE     �   CREATE TABLE public.deposits (
    dep_id integer NOT NULL,
    cl_id integer NOT NULL,
    sum text,
    percent text,
    period text,
    requisites text,
    top_up boolean,
    withdraw boolean
);
    DROP TABLE public.deposits;
       public         heap    postgres    false            �            1259    24939    Deposit_cl_id_seq    SEQUENCE     �   CREATE SEQUENCE public."Deposit_cl_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public."Deposit_cl_id_seq";
       public          postgres    false    225            S           0    0    Deposit_cl_id_seq    SEQUENCE OWNED BY     J   ALTER SEQUENCE public."Deposit_cl_id_seq" OWNED BY public.deposits.cl_id;
          public          postgres    false    226            �            1259    24940    Deposit_dep_id_seq    SEQUENCE     �   CREATE SEQUENCE public."Deposit_dep_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE public."Deposit_dep_id_seq";
       public          postgres    false    225            T           0    0    Deposit_dep_id_seq    SEQUENCE OWNED BY     L   ALTER SEQUENCE public."Deposit_dep_id_seq" OWNED BY public.deposits.dep_id;
          public          postgres    false    227            �            1259    24941    clients_client_id_seq    SEQUENCE     �   CREATE SEQUENCE public.clients_client_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ,   DROP SEQUENCE public.clients_client_id_seq;
       public          postgres    false    220            U           0    0    clients_client_id_seq    SEQUENCE OWNED BY     O   ALTER SEQUENCE public.clients_client_id_seq OWNED BY public.clients.client_id;
          public          postgres    false    228            �           2604    25021    account account_id    DEFAULT     z   ALTER TABLE ONLY public.account ALTER COLUMN account_id SET DEFAULT nextval('public."Account_account_id_seq"'::regclass);
 A   ALTER TABLE public.account ALTER COLUMN account_id DROP DEFAULT;
       public          postgres    false    210    209            �           2604    25022    account card_account_id    DEFAULT     �   ALTER TABLE ONLY public.account ALTER COLUMN card_account_id SET DEFAULT nextval('public."Account_card_account_id_seq"'::regclass);
 F   ALTER TABLE public.account ALTER COLUMN card_account_id DROP DEFAULT;
       public          postgres    false    211    209            �           2604    25023    bank bank_id    DEFAULT     n   ALTER TABLE ONLY public.bank ALTER COLUMN bank_id SET DEFAULT nextval('public."Bank_bank_id_seq"'::regclass);
 ;   ALTER TABLE public.bank ALTER COLUMN bank_id DROP DEFAULT;
       public          postgres    false    213    212            �           2604    25024    card_accounts account_id    DEFAULT     �   ALTER TABLE ONLY public.card_accounts ALTER COLUMN account_id SET DEFAULT nextval('public."CardAccount_account_id_seq"'::regclass);
 G   ALTER TABLE public.card_accounts ALTER COLUMN account_id DROP DEFAULT;
       public          postgres    false    215    214            �           2604    25025    card_accounts client_id    DEFAULT     �   ALTER TABLE ONLY public.card_accounts ALTER COLUMN client_id SET DEFAULT nextval('public."CardAccount_client_id_seq"'::regclass);
 F   ALTER TABLE public.card_accounts ALTER COLUMN client_id DROP DEFAULT;
       public          postgres    false    216    214            �           2604    25026    cards card_id    DEFAULT     o   ALTER TABLE ONLY public.cards ALTER COLUMN card_id SET DEFAULT nextval('public."Card_card_id_seq"'::regclass);
 <   ALTER TABLE public.cards ALTER COLUMN card_id DROP DEFAULT;
       public          postgres    false    218    217            �           2604    25027    cards client_id    DEFAULT     s   ALTER TABLE ONLY public.cards ALTER COLUMN client_id SET DEFAULT nextval('public."Card_client_id_seq"'::regclass);
 >   ALTER TABLE public.cards ALTER COLUMN client_id DROP DEFAULT;
       public          postgres    false    219    217            �           2604    25028    clients client_id    DEFAULT     v   ALTER TABLE ONLY public.clients ALTER COLUMN client_id SET DEFAULT nextval('public.clients_client_id_seq'::regclass);
 @   ALTER TABLE public.clients ALTER COLUMN client_id DROP DEFAULT;
       public          postgres    false    228    220            �           2604    25029    clients bank_id    DEFAULT     t   ALTER TABLE ONLY public.clients ALTER COLUMN bank_id SET DEFAULT nextval('public."Clients_bank_id_seq"'::regclass);
 >   ALTER TABLE public.clients ALTER COLUMN bank_id DROP DEFAULT;
       public          postgres    false    221    220            �           2604    25030    credits credit_id    DEFAULT     x   ALTER TABLE ONLY public.credits ALTER COLUMN credit_id SET DEFAULT nextval('public."Credits_credit_id_seq"'::regclass);
 @   ALTER TABLE public.credits ALTER COLUMN credit_id DROP DEFAULT;
       public          postgres    false    224    222            �           2604    25031    credits client_id    DEFAULT     x   ALTER TABLE ONLY public.credits ALTER COLUMN client_id SET DEFAULT nextval('public."Credits_client_id_seq"'::regclass);
 @   ALTER TABLE public.credits ALTER COLUMN client_id DROP DEFAULT;
       public          postgres    false    223    222            �           2604    25032    deposits dep_id    DEFAULT     s   ALTER TABLE ONLY public.deposits ALTER COLUMN dep_id SET DEFAULT nextval('public."Deposit_dep_id_seq"'::regclass);
 >   ALTER TABLE public.deposits ALTER COLUMN dep_id DROP DEFAULT;
       public          postgres    false    227    225            �           2604    25033    deposits cl_id    DEFAULT     q   ALTER TABLE ONLY public.deposits ALTER COLUMN cl_id SET DEFAULT nextval('public."Deposit_cl_id_seq"'::regclass);
 =   ALTER TABLE public.deposits ALTER COLUMN cl_id DROP DEFAULT;
       public          postgres    false    226    225            /          0    24894    account 
   TABLE DATA           G   COPY public.account (account_id, balance, card_account_id) FROM stdin;
    public          postgres    false    209   Qa       2          0    24901    bank 
   TABLE DATA           2   COPY public.bank (bank_id, bank_name) FROM stdin;
    public          postgres    false    212   na       4          0    24907    card_accounts 
   TABLE DATA           U   COPY public.card_accounts (account_id, client_id, acc_number, code_word) FROM stdin;
    public          postgres    false    214   �a       7          0    24914    cards 
   TABLE DATA           r   COPY public.cards (card_id, client_id, card_number, card_period, person_name, cvv, code_for_cvv, pin) FROM stdin;
    public          postgres    false    217   �a       :          0    24921    clients 
   TABLE DATA           L   COPY public.clients (client_id, bank_id, client_name, birthday) FROM stdin;
    public          postgres    false    220   �a       <          0    24927    credits 
   TABLE DATA           p   COPY public.credits (credit_id, client_id, credit_sum, credit_percent, payment, period, requisites) FROM stdin;
    public          postgres    false    222   �a       ?          0    24934    deposits 
   TABLE DATA           e   COPY public.deposits (dep_id, cl_id, sum, percent, period, requisites, top_up, withdraw) FROM stdin;
    public          postgres    false    225   �a       V           0    0    Account_account_id_seq    SEQUENCE SET     G   SELECT pg_catalog.setval('public."Account_account_id_seq"', 1, false);
          public          postgres    false    210            W           0    0    Account_card_account_id_seq    SEQUENCE SET     L   SELECT pg_catalog.setval('public."Account_card_account_id_seq"', 1, false);
          public          postgres    false    211            X           0    0    Bank_bank_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public."Bank_bank_id_seq"', 28, true);
          public          postgres    false    213            Y           0    0    CardAccount_account_id_seq    SEQUENCE SET     K   SELECT pg_catalog.setval('public."CardAccount_account_id_seq"', 1, false);
          public          postgres    false    215            Z           0    0    CardAccount_client_id_seq    SEQUENCE SET     J   SELECT pg_catalog.setval('public."CardAccount_client_id_seq"', 1, false);
          public          postgres    false    216            [           0    0    Card_card_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public."Card_card_id_seq"', 1, false);
          public          postgres    false    218            \           0    0    Card_client_id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public."Card_client_id_seq"', 1, false);
          public          postgres    false    219            ]           0    0    Clients_bank_id_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('public."Clients_bank_id_seq"', 1, false);
          public          postgres    false    221            ^           0    0    Credits_client_id_seq    SEQUENCE SET     F   SELECT pg_catalog.setval('public."Credits_client_id_seq"', 1, false);
          public          postgres    false    223            _           0    0    Credits_credit_id_seq    SEQUENCE SET     F   SELECT pg_catalog.setval('public."Credits_credit_id_seq"', 1, false);
          public          postgres    false    224            `           0    0    Deposit_cl_id_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public."Deposit_cl_id_seq"', 1, false);
          public          postgres    false    226            a           0    0    Deposit_dep_id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public."Deposit_dep_id_seq"', 1, false);
          public          postgres    false    227            b           0    0    clients_client_id_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('public.clients_client_id_seq', 1, false);
          public          postgres    false    228            �           2606    24956    account Account_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.account
    ADD CONSTRAINT "Account_pkey" PRIMARY KEY (account_id);
 @   ALTER TABLE ONLY public.account DROP CONSTRAINT "Account_pkey";
       public            postgres    false    209            �           2606    24958    bank Bank_pkey 
   CONSTRAINT     S   ALTER TABLE ONLY public.bank
    ADD CONSTRAINT "Bank_pkey" PRIMARY KEY (bank_id);
 :   ALTER TABLE ONLY public.bank DROP CONSTRAINT "Bank_pkey";
       public            postgres    false    212            �           2606    24960    card_accounts CardAccount_pkey 
   CONSTRAINT     f   ALTER TABLE ONLY public.card_accounts
    ADD CONSTRAINT "CardAccount_pkey" PRIMARY KEY (account_id);
 J   ALTER TABLE ONLY public.card_accounts DROP CONSTRAINT "CardAccount_pkey";
       public            postgres    false    214            �           2606    24962    cards Card_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.cards
    ADD CONSTRAINT "Card_pkey" PRIMARY KEY (card_id);
 ;   ALTER TABLE ONLY public.cards DROP CONSTRAINT "Card_pkey";
       public            postgres    false    217            �           2606    24964    clients Clients_pkey 
   CONSTRAINT     [   ALTER TABLE ONLY public.clients
    ADD CONSTRAINT "Clients_pkey" PRIMARY KEY (client_id);
 @   ALTER TABLE ONLY public.clients DROP CONSTRAINT "Clients_pkey";
       public            postgres    false    220            �           2606    24966    credits Credits_pkey 
   CONSTRAINT     [   ALTER TABLE ONLY public.credits
    ADD CONSTRAINT "Credits_pkey" PRIMARY KEY (credit_id);
 @   ALTER TABLE ONLY public.credits DROP CONSTRAINT "Credits_pkey";
       public            postgres    false    222            �           2606    24968    deposits Deposit_pkey 
   CONSTRAINT     Y   ALTER TABLE ONLY public.deposits
    ADD CONSTRAINT "Deposit_pkey" PRIMARY KEY (dep_id);
 A   ALTER TABLE ONLY public.deposits DROP CONSTRAINT "Deposit_pkey";
       public            postgres    false    225            �           1259    24969    bank_bank_name_uindex    INDEX     R   CREATE UNIQUE INDEX bank_bank_name_uindex ON public.bank USING btree (bank_name);
 )   DROP INDEX public.bank_bank_name_uindex;
       public            postgres    false    212            �           1259    24970    fki_bank_id    INDEX     B   CREATE INDEX fki_bank_id ON public.clients USING btree (bank_id);
    DROP INDEX public.fki_bank_id;
       public            postgres    false    220            �           1259    24971    fki_client_id_fkey    INDEX     Q   CREATE INDEX fki_client_id_fkey ON public.card_accounts USING btree (client_id);
 &   DROP INDEX public.fki_client_id_fkey;
       public            postgres    false    214            �           2606    24972 $   account Account_card_account_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.account
    ADD CONSTRAINT "Account_card_account_id_fkey" FOREIGN KEY (card_account_id) REFERENCES public.card_accounts(account_id) ON UPDATE RESTRICT ON DELETE RESTRICT NOT VALID;
 P   ALTER TABLE ONLY public.account DROP CONSTRAINT "Account_card_account_id_fkey";
       public          postgres    false    214    3219    209            �           2606    24977    credits Credits_client_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.credits
    ADD CONSTRAINT "Credits_client_id_fkey" FOREIGN KEY (client_id) REFERENCES public.clients(client_id) ON UPDATE RESTRICT ON DELETE RESTRICT;
 J   ALTER TABLE ONLY public.credits DROP CONSTRAINT "Credits_client_id_fkey";
       public          postgres    false    222    220    3224            �           2606    24982    deposits Deposit_cl_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.deposits
    ADD CONSTRAINT "Deposit_cl_id_fkey" FOREIGN KEY (cl_id) REFERENCES public.clients(client_id) ON UPDATE RESTRICT ON DELETE RESTRICT;
 G   ALTER TABLE ONLY public.deposits DROP CONSTRAINT "Deposit_cl_id_fkey";
       public          postgres    false    220    225    3224            �           2606    24987    clients bank_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.clients
    ADD CONSTRAINT bank_id FOREIGN KEY (bank_id) REFERENCES public.bank(bank_id) ON UPDATE RESTRICT ON DELETE RESTRICT NOT VALID;
 9   ALTER TABLE ONLY public.clients DROP CONSTRAINT bank_id;
       public          postgres    false    3216    220    212            �           2606    24992    card_accounts client_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.card_accounts
    ADD CONSTRAINT client_id_fkey FOREIGN KEY (client_id) REFERENCES public.clients(client_id) ON UPDATE RESTRICT ON DELETE RESTRICT NOT VALID;
 F   ALTER TABLE ONLY public.card_accounts DROP CONSTRAINT client_id_fkey;
       public          postgres    false    3224    220    214            �           2606    24997    cards client_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.cards
    ADD CONSTRAINT client_id_fkey FOREIGN KEY (client_id) REFERENCES public.clients(client_id) ON UPDATE RESTRICT ON DELETE RESTRICT NOT VALID;
 >   ALTER TABLE ONLY public.cards DROP CONSTRAINT client_id_fkey;
       public          postgres    false    217    220    3224            /      x������ � �      2      x������ � �      4      x������ � �      7      x������ � �      :      x������ � �      <      x������ � �      ?      x������ � �     