package com.harsh.rest.webservices.restful_web_services.helloworld;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//Rest API
@RestController
public class HelloWorldController
{

    private MessageSource messageSource;

    public HelloWorldController(MessageSource messageSource)
    {
        this.messageSource = messageSource;
    }   

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

    //Path parameters
    // /user/{id}/todos/{id} -> /users/2/todos/200
    // /hello-world/path-variable/{name}
    // /hello-world/path-variable/harsh

    @GetMapping("/hello-world/path-variable/{name}")
    public HelloWorldBean helloWorldPathVariable(@PathVariable String name) 
    {
        return new HelloWorldBean(String.format("Hello World, %s", name));
    }

    // @GetMapping("/hello-world-internationalized")
    // public  String helloWorldInternationalized()
    // {
    //     Locale locale = LocaleContextHolder.getLocale();
    //     messageSource.getMessage("good.morning.message", null, "Default Message", locale);   

    //     return "hello world";
    // }

    @GetMapping(path = "/hello-world-internationalized")
	public String helloWorldInternationalized() 
    {
		Locale locale = LocaleContextHolder.getLocale();

		return messageSource.getMessage("good.morning.message", null, "Default Message", locale );
		
		//return "Hello World V2"; 
		
//		- Example: `en` - English (Good Morning)
//		- Example: `nl` - Dutch (Goedemorgen)
//		- Example: `fr` - French (Bonjour)
//		- Example: `de` - Deutsch (Guten Morgen)

	}

   
}
