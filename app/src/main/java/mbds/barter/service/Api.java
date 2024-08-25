package mbds.barter.service;

import mbds.barter.utils.DateDeserializer;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import okhttp3.logging.HttpLoggingInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.util.Date;

public class Api {

    private static Retrofit retrofit = null;
    private static String authToken = null;

    // Method to set the auth token
    public static void setAuthToken(String token) {
        authToken = token;
    }

    public static Retrofit getClient() {
        if (retrofit == null) {
            synchronized (Api.class) {
                if (retrofit == null) {
                    // Create a logging interceptor
                    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
                    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

                    // Create an interceptor to add the x-auth-token header
                    Interceptor headerInterceptor = new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            Request original = chain.request();
                            Request.Builder requestBuilder = original.newBuilder()
                                    .header("x-auth-token", authToken != null ? authToken : "");
                            Request request = requestBuilder.build();
                            return chain.proceed(request);
                        }
                    };

                    // Add interceptors to the OkHttpClient
                    OkHttpClient client = new OkHttpClient.Builder()
                            .addInterceptor(interceptor)
                            .addInterceptor(headerInterceptor)
                            .build();

                    // Configure Gson for date deserialization
                    Gson gson = new GsonBuilder()
                            .registerTypeAdapter(Date.class, new DateDeserializer())
                            .create();

                    // Build the Retrofit instance
                    retrofit = new Retrofit.Builder()
                            .baseUrl("https://tpt-barter-backend.onrender.com/")
                            .addConverterFactory(GsonConverterFactory.create(gson))
                            .client(client)
                            .build();
                }
            }
        }
        return retrofit;
    }
}
