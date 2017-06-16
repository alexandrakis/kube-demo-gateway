package com.example.kubedemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by aalexandrakis on 11/06/2017.
 */
@RestController
public class TestController {

    @Autowired
    Environment env;

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("/test/gateway")
    public Object testController(){
        System.out.println("Calling test gateway controller");
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://kube-demo/test";
        System.out.println("Send request to " + url);
        return restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<Object>() {}).getBody();
    }
}

class Response {
    private String message;
    private String anotherMessage;

    public Response(String message, String anotherMessage) {
        this.message = message;
        this.anotherMessage = anotherMessage;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAnotherMessage() {
        return anotherMessage;
    }

    public void setAnotherMessage(String anotherMessage) {
        this.anotherMessage = anotherMessage;
    }
}