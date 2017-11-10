package com.jinshwgames.rxjava;

import android.content.Context;
import android.util.Log;

import com.jinshwgames.BuildConfig;

import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * 业务名:Retrofit+OkHttp3实现网络请求
 * 功能说明:
 * 编写日期:2017/10/19 下午6:25;
 * 作者:zhangbo;
 * <p/>
 * 历史记录
 * 1、修改日期：
 * 修改人：
 * 修改内容：
 */

public class RxAppClient {

    private static final int TIME_OUT = 120;
    private static Retrofit mRetrofit;
    private static Context mContext;
    private static final String CACHE_NAME = "cache";

    /**
     * @Author: zhangbo;
     * @MethodDescription:服务器请求
     * @CreateDate: 2017/10/19 下午6:29;
     * @Version: 1.0;
     */
    public static Retrofit retrofit() {
        if (mRetrofit == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            if (BuildConfig.DEBUG) {
                // Log信息拦截器
                HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                    @Override
                    public void log(String message) {
                        Log.e("url", message);
                    }
                });
                loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                //设置 Debug Log 模式
                builder.addInterceptor(loggingInterceptor);
                //增加网络缓存
//                builder.addInterceptor(new CaheInterceptor(mContext));
//                builder.addNetworkInterceptor(new CaheInterceptor(mContext));
                builder.readTimeout(TIME_OUT, TimeUnit.SECONDS);
                builder.connectTimeout(TIME_OUT, TimeUnit.SECONDS);
                builder.writeTimeout(TIME_OUT, TimeUnit.SECONDS);
                builder.sslSocketFactory(getUnsafeOkHttpClient());
                builder.hostnameVerifier(org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER).build();
            }
            OkHttpClient okHttpClient = builder.build();
            mRetrofit = new Retrofit.Builder()
                    .baseUrl("http://47.93.122.100:8888/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
        return mRetrofit;
    }


    /**
     * @Author: zhangbo;
     * @MethodDescription:需要https加密用到的话 就放开  builder.sslSocketFactory(getUnsafeOkHttpClient());  的注释
     * @CreateDate: 2017/10/19 下午6:28;
     * @Version: 1.0;
     */
    public static SSLSocketFactory getUnsafeOkHttpClient() {

        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                @Override
                public void checkClientTrusted(
                        java.security.cert.X509Certificate[] chain,
                        String authType) throws CertificateException {
                }

                @Override
                public void checkServerTrusted(
                        java.security.cert.X509Certificate[] chain,
                        String authType) throws CertificateException {
                }

                @Override
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return new java.security.cert.X509Certificate[0];
                }
            }};

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustAllCerts,
                    new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext
                    .getSocketFactory();
            return sslSocketFactory;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * @Author: zhangbo;
     * @MethodDescription:服务器有证书时
     * @CreateDate: 2017/10/19 下午6:29;
     * @Version: 1.0;
     */
    public static Retrofit retrofitReadCer(Context context) {
        if (mRetrofit == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            mContext = context;
            X509TrustManager trustManager;
            SSLSocketFactory sslSocketFactory;
            final InputStream inputStream;
            try {
                inputStream = mContext.getAssets().open("server.cer"); // 得到证书的输入流
                try {
                    trustManager = trustManagerForCertificates(inputStream);//以流的方式读入证书
                    SSLContext sslContext = SSLContext.getInstance("TLS");
                    sslContext.init(null, new TrustManager[]{trustManager}, null);
                    sslSocketFactory = sslContext.getSocketFactory();

                } catch (GeneralSecurityException e) {
                    throw new RuntimeException(e);
                }
                if (BuildConfig.DEBUG) {
                    // Log信息拦截器
                    HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                        @Override
                        public void log(String message) {
                            Log.e("url", message);
                        }
                    });
                    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                    //设置 Debug Log 模式
                    builder.addInterceptor(loggingInterceptor);
                    builder.readTimeout(TIME_OUT, TimeUnit.SECONDS);
//                builder.addNetworkInterceptor(new NetInterceptor());
                    builder.connectTimeout(TIME_OUT, TimeUnit.SECONDS);
                    builder.writeTimeout(TIME_OUT, TimeUnit.SECONDS);
                    builder.sslSocketFactory(sslSocketFactory);
//                    builder.hostnameVerifier(org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER).build();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            OkHttpClient okHttpClient = builder.build();
            mRetrofit = new Retrofit.Builder()
                    .baseUrl("")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
        return mRetrofit;
    }

    /**
     * @Author: zhangbo;
     * @MethodDescription:读取X509证书
     * @CreateDate: 2017/10/19 下午6:29;
     * @Version: 1.0;
     */
    private static X509TrustManager trustManagerForCertificates(InputStream in)
            throws GeneralSecurityException {
        CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
        Collection<? extends Certificate> certificates = certificateFactory.generateCertificates(in);
        if (certificates.isEmpty()) {
            throw new IllegalArgumentException("expected non-empty set of trusted certificates");
        }

        // Put the certificates a key store.
        char[] password = "password".toCharArray(); // Any password will work.
        KeyStore keyStore = newEmptyKeyStore(password);
        int index = 0;
        for (Certificate certificate : certificates) {
            String certificateAlias = Integer.toString(index++);
            keyStore.setCertificateEntry(certificateAlias, certificate);
        }

        // Use it to build an X509 trust manager.
        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(
                KeyManagerFactory.getDefaultAlgorithm());
        keyManagerFactory.init(keyStore, password);
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(
                TrustManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init(keyStore);
        TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
        if (trustManagers.length != 1 || !(trustManagers[0] instanceof X509TrustManager)) {
            throw new IllegalStateException("Unexpected default trust managers:"
                    + Arrays.toString(trustManagers));
        }
        return (X509TrustManager) trustManagers[0];
    }

    /**
     * @Author: zhangbo;
     * @MethodDescription:添加password
     * @CreateDate: 2017/10/19 下午6:29;
     * @Version: 1.0;
     */
    private static KeyStore newEmptyKeyStore(char[] password) throws GeneralSecurityException {
        try {
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType()); // 这里添加自定义的密码，默认
            InputStream in = null; // By convention, 'null' creates an empty key store.
            keyStore.load(in, password);
            return keyStore;
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }
}
