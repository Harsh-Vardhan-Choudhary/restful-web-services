package com.harsh.rest.webservices.restful_web_services.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//Rest API
@RestController
public class HelloWorldController
{
    // similarly we can use postmapping and another annotations for mapping urls
    @GetMapping(path = "/hello-world")
    public String helloWorld()
    {
        return "Hello World";
    }

    // @RequestMapping(method = RequestMethod.GET, path = "/hello-world")
    // public String helloWorld()
    // {
    //     return "Hello World";
    // }


    // returning a bean
    @GetMapping(path = "/hello-world-bean")
    public HelloWorldBean helloWorldBean()
    {
        return new HelloWorldBean("Hello World");
    }
}
