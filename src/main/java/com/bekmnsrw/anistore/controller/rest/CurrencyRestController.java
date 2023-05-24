package com.bekmnsrw.anistore.controller.rest;

import com.bekmnsrw.anistore.response.Root;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/convert")
@RequiredArgsConstructor
public class CurrencyRestController {

    private static final String BASE = "base";
    private static final String SYMBOLS = "symbols";
    private static final String AMOUNT = "amount";
    private static final String EUR = "EUR";
    private static final String RUB = "RUB";
    private static final String USD = "USD";
    private static final String SCHEME = "https";
    private static final String HOST = "api.exchangerate.host";
    private static final String PATH_SEGMENT = "2023-05-24";
    private static final String HEADER_NAME = "accept";
    private static final String HEADER_VALUE = "application/json";
    private static final String EUR_SYMBOL = " €";
    private static final String USD_SYMBOL = " $";

    @GetMapping
    public ResponseEntity<String> convert(
            @RequestParam("symbols") String symbols,
            @RequestParam("amount") String amount
    ) throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();

        HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme(SCHEME)
                .host(HOST)
                .addPathSegment(PATH_SEGMENT)
                .addQueryParameter(BASE, RUB)
                .addQueryParameter(SYMBOLS, symbols)
                .addQueryParameter(AMOUNT, amount)
                .build();

        Request request = new Request.Builder()
                .addHeader(HEADER_NAME, HEADER_VALUE)
                .url(httpUrl)
                .build();

        Gson gson = new Gson();
        String responseData = okHttpClient.newCall(request).execute().body().string();
        Root root = gson.fromJson(responseData, Root.class);

        String result = "";

        switch (symbols) {
            case EUR -> result = root.getRates().EUR + EUR_SYMBOL;
            case USD -> result = root.getRates().USD + USD_SYMBOL;
        }

        return ResponseEntity.ok(result);
    }
}
