package com.intern.weatherapp.service;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;


import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.*;
import java.util.Objects;
import java.util.zip.GZIPInputStream;


@Service
public class DataService {


    private final WebClient localApiClient;

    public static int PRETTY_PRINT_INDENT_FACTOR = 4;

    @Autowired
    public DataService(WebClient localApiClient) {
        this.localApiClient =localApiClient;
    }

    public static String decompress(byte[] bytes) throws Exception {

        GZIPInputStream gis = new GZIPInputStream(new ByteArrayInputStream(bytes));
        BufferedReader bf = new BufferedReader(new InputStreamReader(gis, "UTF-8"));
        StringBuilder outStr = new StringBuilder();
        String line;
        while ((line=bf.readLine())!=null) {
            outStr.append(line);
        }
        //System.out.println("Output String lenght : " + outStr.length());
        return outStr.toString();
    }


    public String getData() throws Exception {



        WebClient.ResponseSpec response = localApiClient
                .get()
                .accept(MediaType.valueOf(MediaType.TEXT_PLAIN_VALUE))
                .header(HttpHeaders.ACCEPT_ENCODING,"gzip")
                .header(HttpHeaders.CONNECTION,"close")
                .retrieve();

        ResponseEntity<byte[]> res = response.toEntity(byte[].class).block();





        String a = decompress(Objects.requireNonNull(res.getBody()));


        String jsonPrettyPrintString = "";
        try {


            String TEST_XML_STRING = a;

            JSONObject xmlJSONObj = XML.toJSONObject(TEST_XML_STRING);
             jsonPrettyPrintString = xmlJSONObj.toString(PRETTY_PRINT_INDENT_FACTOR);
            //System.out.println(jsonPrettyPrintString);
        } catch (JSONException je) {
            System.out.println(je);
        }

        return jsonPrettyPrintString;

    }


}
