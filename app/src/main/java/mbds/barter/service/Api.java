package mbds.barter.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import mbds.barter.utils.DateDeserializer;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {
    private static volatile Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            synchronized (Api.class) {
                if (retrofit == null) {
                    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
                    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                    OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

                    Gson gson = new GsonBuilder()
                            .registerTypeAdapter(Date.class, new DateDeserializer())
                            .create();

                    retrofit = new Retrofit.Builder()
                            .baseUrl("http://192.168.1.18:3000")
                            .addConverterFactory(GsonConverterFactory.create(gson))
                            .client(client)
                            .build();
                }
            }
        }
        return retrofit;
    }
}
