package com.benjamin.api;

public class ApiConfig {
    public static String HOST = "";
    public static int DEFAULT_TIMEOUT = 60;

    public ApiConfig() {
    }

    public static void init(String host) {
        HOST = host;
    }
}