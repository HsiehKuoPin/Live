package com.benjamin.api;

import android.util.Log;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import okhttp3.logging.HttpLoggingInterceptor.Logger;
import retrofit2.Retrofit;
import retrofit2.converter.fastjson.FastJsonConverterFactory;

public class API {
    private Disposable disposable;
    private final String TAG = "API";
    private Retrofit retrofit;
    public static Map<String, API> apiInstanceMap = new HashMap();

    public static <T> T getInstance(Class<T> clazz) {
        return getInstance(clazz, ApiConfig.HOST);
    }

    public static <T> T getInstance(Class<T> clazz, String host) {
        if(apiInstanceMap.get(host) == null) {
            API api = new API(host);
            apiInstanceMap.put(host, api);
        }

        return ((API)apiInstanceMap.get(host)).create(clazz);
    }

    private API(String host) {
        try {
            TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                }

                public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                }

                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }
            }};
            SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init((KeyManager[])null, trustAllCerts, new SecureRandom());
            SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
            Builder okHttpClientBuilder = (new OkHttpClient()).newBuilder();
            okHttpClientBuilder.connectTimeout((long)ApiConfig.DEFAULT_TIMEOUT, TimeUnit.SECONDS);
            okHttpClientBuilder.readTimeout((long)ApiConfig.DEFAULT_TIMEOUT, TimeUnit.SECONDS);
            okHttpClientBuilder.writeTimeout((long)ApiConfig.DEFAULT_TIMEOUT, TimeUnit.SECONDS);
            okHttpClientBuilder.addInterceptor(this.getHttpLoggingInterceptor());
            okHttpClientBuilder.sslSocketFactory(sslSocketFactory);
            okHttpClientBuilder.hostnameVerifier(new HostnameVerifier() {
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
            okHttpClientBuilder.retryOnConnectionFailure(true);
            this.retrofit = (new retrofit2.Retrofit.Builder()).baseUrl(host).client(okHttpClientBuilder.build()).addConverterFactory(FastJsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io())).build();
        } catch (Exception var6) {
            throw new RuntimeException(var6);
        }
    }

    private <T> T create(Class<T> clazz) {
        return this.retrofit.create(clazz);
    }

    private HttpLoggingInterceptor getHttpLoggingInterceptor() {
        Level level = Level.BODY;
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new Logger() {
            public void log(String message) {
                Log.d("API", message);
            }
        });
        loggingInterceptor.setLevel(level);
        return loggingInterceptor;
    }
}
