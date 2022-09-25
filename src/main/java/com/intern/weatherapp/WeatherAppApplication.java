package com.intern.weatherapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.util.Objects;

@SpringBootApplication
public class WeatherAppApplication {

    @Autowired
    private Environment env;

    @Bean
    public WebClient localApiClient() {
        WebClient WC = WebClient.builder().baseUrl(Objects.requireNonNull(env.getProperty("external.api.uri")))
                .clientConnector(new ReactorClientHttpConnector(
                        HttpClient.create()
                       .followRedirect(true)

                ))


                //.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_HTML_VALUE)
                .build();


        return WC;
    }

    public static void main(String[] args) {
        SpringApplication.run(WeatherAppApplication.class, args);
    }

}
