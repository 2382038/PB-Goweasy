package com.example.final_goweasyapp;

public class konfigurasi {

    // PENTING! Ganti IP sesuai dengan IP komputer/server kamu
    public static final String URL_BASE = "http://10.8.9.134/Android/goweasy/";

    // URL Endpoints
    public static final String URL_LOGIN = URL_BASE + "login.php";
    public static final String URL_REGISTER = URL_BASE + "register.php";
    public static final String URL_TAMBAH_SEPEDA = URL_BASE + "tambahSepeda.php";
    public static final String URL_TAMBAH_HISTORI = URL_BASE + "tambahHistori.php";
    public static final String URL_TAMPIL_SEPEDA = URL_BASE + "tampilSepeda.php";
    public static final String URL_TAMPIL_HISTORI_PENGGUNA = URL_BASE + "tampilHistoriPengguna.php";

    // Keys untuk mengirim data ke server
    public static final String KEY_USER_ID = "user_id"; // Untuk login, histori
    public static final String KEY_USERNAME = "username"; // Untuk login/register
    public static final String KEY_PASSWORD = "password"; // Untuk login/register

    public static final String KEY_SEPEDA_ID = "sepeda_id"; // Untuk sepeda
    public static final String KEY_SEPEDA_JENIS = "jenis_sepeda";
    public static final String KEY_SEPEDA_HARGA = "harga_total";

    public static final String KEY_HISTORI_ID = "histori_id"; // Untuk histori
    public static final String KEY_TOTAL_HARGA = "harga_total";

    // JSON Tags (agar parsing JSON lebih mudah)
    public static final String TAG_JSON_ARRAY = "result";
    public static final String TAG_SUCCESS = "success";
    public static final String TAG_MESSAGE = "message";
}
