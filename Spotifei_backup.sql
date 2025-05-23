PGDMP  5    5                }            Spotifei    17.5    17.5 3    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                           false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                           false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                           false            �           1262    16551    Spotifei    DATABASE     �   CREATE DATABASE "Spotifei" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Portuguese_Brazil.utf8';
    DROP DATABASE "Spotifei";
                     postgres    false            �            1259    16655    curtidas    TABLE     �   CREATE TABLE public.curtidas (
    id_usuario integer NOT NULL,
    id_musica integer NOT NULL,
    curtida boolean NOT NULL
);
    DROP TABLE public.curtidas;
       public         heap r       postgres    false            �            1259    16766    historico_buscas    TABLE     �   CREATE TABLE public.historico_buscas (
    id integer NOT NULL,
    id_usuario integer NOT NULL,
    termo_busca character varying(255) NOT NULL
);
 $   DROP TABLE public.historico_buscas;
       public         heap r       postgres    false            �            1259    16765    historico_buscas_id_seq    SEQUENCE     �   CREATE SEQUENCE public.historico_buscas_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 .   DROP SEQUENCE public.historico_buscas_id_seq;
       public               postgres    false    226            �           0    0    historico_buscas_id_seq    SEQUENCE OWNED BY     S   ALTER SEQUENCE public.historico_buscas_id_seq OWNED BY public.historico_buscas.id;
          public               postgres    false    225            �            1259    16773    historico_curtidas    TABLE     �   CREATE TABLE public.historico_curtidas (
    id integer NOT NULL,
    id_usuario integer NOT NULL,
    id_musica integer NOT NULL,
    curtida boolean NOT NULL
);
 &   DROP TABLE public.historico_curtidas;
       public         heap r       postgres    false            �            1259    16772    historico_curtidas_id_seq    SEQUENCE     �   CREATE SEQUENCE public.historico_curtidas_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 0   DROP SEQUENCE public.historico_curtidas_id_seq;
       public               postgres    false    228            �           0    0    historico_curtidas_id_seq    SEQUENCE OWNED BY     W   ALTER SEQUENCE public.historico_curtidas_id_seq OWNED BY public.historico_curtidas.id;
          public               postgres    false    227            �            1259    16668    musicas    TABLE     �   CREATE TABLE public.musicas (
    id integer NOT NULL,
    nome character varying(255) NOT NULL,
    artista character varying(255) NOT NULL,
    genero character varying(100),
    duracao integer
);
    DROP TABLE public.musicas;
       public         heap r       postgres    false            �            1259    16673    musicas_id_seq    SEQUENCE     �   CREATE SEQUENCE public.musicas_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.musicas_id_seq;
       public               postgres    false    218            �           0    0    musicas_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public.musicas_id_seq OWNED BY public.musicas.id;
          public               postgres    false    219            �            1259    16674    playlist_musicas    TABLE     �   CREATE TABLE public.playlist_musicas (
    playlist_id integer NOT NULL,
    musica_id integer NOT NULL,
    usuario_id integer
);
 $   DROP TABLE public.playlist_musicas;
       public         heap r       postgres    false            �            1259    16677 	   playlists    TABLE     �   CREATE TABLE public.playlists (
    id integer NOT NULL,
    nome character varying(255) NOT NULL,
    usuario_id integer NOT NULL
);
    DROP TABLE public.playlists;
       public         heap r       postgres    false            �            1259    16680    playlists_id_seq    SEQUENCE     �   CREATE SEQUENCE public.playlists_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE public.playlists_id_seq;
       public               postgres    false    221            �           0    0    playlists_id_seq    SEQUENCE OWNED BY     E   ALTER SEQUENCE public.playlists_id_seq OWNED BY public.playlists.id;
          public               postgres    false    222            �            1259    16681    usuarios    TABLE     �   CREATE TABLE public.usuarios (
    id integer NOT NULL,
    nome character varying(100),
    email character varying(100),
    senha character varying(255),
    tipo_usuario character varying(50)
);
    DROP TABLE public.usuarios;
       public         heap r       postgres    false            �            1259    16686    usuarios_id_seq    SEQUENCE     �   CREATE SEQUENCE public.usuarios_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.usuarios_id_seq;
       public               postgres    false    223            �           0    0    usuarios_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public.usuarios_id_seq OWNED BY public.usuarios.id;
          public               postgres    false    224            @           2604    16769    historico_buscas id    DEFAULT     z   ALTER TABLE ONLY public.historico_buscas ALTER COLUMN id SET DEFAULT nextval('public.historico_buscas_id_seq'::regclass);
 B   ALTER TABLE public.historico_buscas ALTER COLUMN id DROP DEFAULT;
       public               postgres    false    225    226    226            A           2604    16776    historico_curtidas id    DEFAULT     ~   ALTER TABLE ONLY public.historico_curtidas ALTER COLUMN id SET DEFAULT nextval('public.historico_curtidas_id_seq'::regclass);
 D   ALTER TABLE public.historico_curtidas ALTER COLUMN id DROP DEFAULT;
       public               postgres    false    227    228    228            =           2604    16689 
   musicas id    DEFAULT     h   ALTER TABLE ONLY public.musicas ALTER COLUMN id SET DEFAULT nextval('public.musicas_id_seq'::regclass);
 9   ALTER TABLE public.musicas ALTER COLUMN id DROP DEFAULT;
       public               postgres    false    219    218            >           2604    16690    playlists id    DEFAULT     l   ALTER TABLE ONLY public.playlists ALTER COLUMN id SET DEFAULT nextval('public.playlists_id_seq'::regclass);
 ;   ALTER TABLE public.playlists ALTER COLUMN id DROP DEFAULT;
       public               postgres    false    222    221            ?           2604    16691    usuarios id    DEFAULT     j   ALTER TABLE ONLY public.usuarios ALTER COLUMN id SET DEFAULT nextval('public.usuarios_id_seq'::regclass);
 :   ALTER TABLE public.usuarios ALTER COLUMN id DROP DEFAULT;
       public               postgres    false    224    223            �          0    16655    curtidas 
   TABLE DATA           B   COPY public.curtidas (id_usuario, id_musica, curtida) FROM stdin;
    public               postgres    false    217   �;       �          0    16766    historico_buscas 
   TABLE DATA           G   COPY public.historico_buscas (id, id_usuario, termo_busca) FROM stdin;
    public               postgres    false    226   �;       �          0    16773    historico_curtidas 
   TABLE DATA           P   COPY public.historico_curtidas (id, id_usuario, id_musica, curtida) FROM stdin;
    public               postgres    false    228   &<       �          0    16668    musicas 
   TABLE DATA           E   COPY public.musicas (id, nome, artista, genero, duracao) FROM stdin;
    public               postgres    false    218   �<       �          0    16674    playlist_musicas 
   TABLE DATA           N   COPY public.playlist_musicas (playlist_id, musica_id, usuario_id) FROM stdin;
    public               postgres    false    220   �>       �          0    16677 	   playlists 
   TABLE DATA           9   COPY public.playlists (id, nome, usuario_id) FROM stdin;
    public               postgres    false    221   �>       �          0    16681    usuarios 
   TABLE DATA           H   COPY public.usuarios (id, nome, email, senha, tipo_usuario) FROM stdin;
    public               postgres    false    223   3?       �           0    0    historico_buscas_id_seq    SEQUENCE SET     F   SELECT pg_catalog.setval('public.historico_buscas_id_seq', 10, true);
          public               postgres    false    225                        0    0    historico_curtidas_id_seq    SEQUENCE SET     H   SELECT pg_catalog.setval('public.historico_curtidas_id_seq', 22, true);
          public               postgres    false    227                       0    0    musicas_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.musicas_id_seq', 20, true);
          public               postgres    false    219                       0    0    playlists_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.playlists_id_seq', 9, true);
          public               postgres    false    222                       0    0    usuarios_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.usuarios_id_seq', 5, true);
          public               postgres    false    224            C           2606    16693    curtidas curtidas_pkey 
   CONSTRAINT     g   ALTER TABLE ONLY public.curtidas
    ADD CONSTRAINT curtidas_pkey PRIMARY KEY (id_usuario, id_musica);
 @   ALTER TABLE ONLY public.curtidas DROP CONSTRAINT curtidas_pkey;
       public                 postgres    false    217    217            O           2606    16771 &   historico_buscas historico_buscas_pkey 
   CONSTRAINT     d   ALTER TABLE ONLY public.historico_buscas
    ADD CONSTRAINT historico_buscas_pkey PRIMARY KEY (id);
 P   ALTER TABLE ONLY public.historico_buscas DROP CONSTRAINT historico_buscas_pkey;
       public                 postgres    false    226            Q           2606    16778 *   historico_curtidas historico_curtidas_pkey 
   CONSTRAINT     h   ALTER TABLE ONLY public.historico_curtidas
    ADD CONSTRAINT historico_curtidas_pkey PRIMARY KEY (id);
 T   ALTER TABLE ONLY public.historico_curtidas DROP CONSTRAINT historico_curtidas_pkey;
       public                 postgres    false    228            E           2606    16699    musicas musicas_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.musicas
    ADD CONSTRAINT musicas_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.musicas DROP CONSTRAINT musicas_pkey;
       public                 postgres    false    218            G           2606    16701 &   playlist_musicas playlist_musicas_pkey 
   CONSTRAINT     x   ALTER TABLE ONLY public.playlist_musicas
    ADD CONSTRAINT playlist_musicas_pkey PRIMARY KEY (playlist_id, musica_id);
 P   ALTER TABLE ONLY public.playlist_musicas DROP CONSTRAINT playlist_musicas_pkey;
       public                 postgres    false    220    220            I           2606    16703    playlists playlists_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.playlists
    ADD CONSTRAINT playlists_pkey PRIMARY KEY (id);
 B   ALTER TABLE ONLY public.playlists DROP CONSTRAINT playlists_pkey;
       public                 postgres    false    221            K           2606    16705    usuarios usuarios_email_key 
   CONSTRAINT     W   ALTER TABLE ONLY public.usuarios
    ADD CONSTRAINT usuarios_email_key UNIQUE (email);
 E   ALTER TABLE ONLY public.usuarios DROP CONSTRAINT usuarios_email_key;
       public                 postgres    false    223            M           2606    16707    usuarios usuarios_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.usuarios
    ADD CONSTRAINT usuarios_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.usuarios DROP CONSTRAINT usuarios_pkey;
       public                 postgres    false    223            R           2606    16708     curtidas curtidas_id_musica_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.curtidas
    ADD CONSTRAINT curtidas_id_musica_fkey FOREIGN KEY (id_musica) REFERENCES public.musicas(id) ON DELETE CASCADE;
 J   ALTER TABLE ONLY public.curtidas DROP CONSTRAINT curtidas_id_musica_fkey;
       public               postgres    false    218    217    4677            S           2606    16713 !   curtidas curtidas_id_usuario_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.curtidas
    ADD CONSTRAINT curtidas_id_usuario_fkey FOREIGN KEY (id_usuario) REFERENCES public.usuarios(id) ON DELETE CASCADE;
 K   ALTER TABLE ONLY public.curtidas DROP CONSTRAINT curtidas_id_usuario_fkey;
       public               postgres    false    217    223    4685            T           2606    16733 0   playlist_musicas playlist_musicas_musica_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.playlist_musicas
    ADD CONSTRAINT playlist_musicas_musica_id_fkey FOREIGN KEY (musica_id) REFERENCES public.musicas(id) ON DELETE CASCADE;
 Z   ALTER TABLE ONLY public.playlist_musicas DROP CONSTRAINT playlist_musicas_musica_id_fkey;
       public               postgres    false    4677    218    220            U           2606    16738 2   playlist_musicas playlist_musicas_playlist_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.playlist_musicas
    ADD CONSTRAINT playlist_musicas_playlist_id_fkey FOREIGN KEY (playlist_id) REFERENCES public.playlists(id) ON DELETE CASCADE;
 \   ALTER TABLE ONLY public.playlist_musicas DROP CONSTRAINT playlist_musicas_playlist_id_fkey;
       public               postgres    false    221    220    4681            V           2606    16743 #   playlists playlists_usuario_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.playlists
    ADD CONSTRAINT playlists_usuario_id_fkey FOREIGN KEY (usuario_id) REFERENCES public.usuarios(id) ON DELETE CASCADE;
 M   ALTER TABLE ONLY public.playlists DROP CONSTRAINT playlists_usuario_id_fkey;
       public               postgres    false    221    223    4685            �   /   x�3�4�,�2�42 S���BY@(Se�L��M8!l��=... �Lq      �   K   x�3�4�,�/�2�I9�y)�y�
9��%�\�@1.N�S �e$�r��S����A�P�P�� ʈ���� %�_      �   k   x�5�� !�7sD�^�m�����k'XM�X��$�"B-m(@'D��$0�
`0��p�%�g�`p�q*����Y�ޙ�V/Ԥ�~��[�>��S�       �     x�m�Kn1���)�
Z )�����c7�����@7����H�F�d��5z�����vM�⧏�pa�3�
���GM�+��HZ-����i��`B!4����Aw{���𶁌J�J<�+S��@Ǉ:Zu`�9�p����ٳy�&�Èl�c��`�%�3[v����W>�d��{}Յ�?K)�?���0�����wᔗ�s~)H*n��\F�yc���Z=�ޗƝ�`|`~r���mi�01���Q�>�ㅠ�5��� �Ŭ &:q�m����`�t��lw�?�́�Q@OiS��U�)-��7T�W�B�'�Ȫސt�t)�RY�Y�-�V�ORH�m��0㭉�[ø&��gJ�#!j��{���v�'�ajDb8���9�w�n��¹σY�C��]H�q�L�I�"V�x�]랜���ƀ��������}�cZrm�xNQj�∸� ��"ջ���@ �@�C��;��5��o�������T(D���$�~[}��������      �   #   x�3�4�4�2�4��@�H� I# ���� R)m      �   2   x�3�(��M�,J�4�2�NM/�K��8CR��������&\1z\\\ f��      �   Q   x�3�I-.IUHIT��)K�,qS�A��������\�pqj^F"g���24�.#�6cNC02M8��Ȍ���� ��*�     