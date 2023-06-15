package org.example.service;
import okhttp3.*;

import java.io.IOException;

class ErrorInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);

        if (response.code() == 403) {
            System.out.println("Wykonywanie operacji po błędzie 403...");
        }

        return response;
    }
}