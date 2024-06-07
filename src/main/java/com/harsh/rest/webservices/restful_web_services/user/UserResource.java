package com.harsh.rest.webservices.restful_web_services.user;

import java.net.URI;
import java.util.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;

@RestController
public class UserResource 
{
    private UserDaoService service;

	public UserResource(UserDaoService service) 
    {
		this.service = service;
	}

	// GET /users
	@GetMapping("/users")
	public List<User> retrieveAllUsers() 
    {
		return service.findAll();
	}

	// GET /users/{id}
	@GetMapping("/users/{id}")
	public User retrieveUser(@PathVariable int id) 
    {
        User user = service.findOne(id);

        // is user not found UserNotFoundException.class extends RuntimeException -> Error 404 with id 
        if(user == null)
        {
            throw new UserNotFoundException("id"+id);
        }

		return user;
	}

    //we want to send something which has same structure as User
    //That's why the content is passed as user body
    //POST /users

    // @PostMapping("/users")
	// public void createUser(@RequestBody User user) 
    // {
	// 	service.save(user);
	// }

    //Getting the response status - 201
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) 
    {	
		User savedUser = service.save(user);

        //return ResponseEntity.created(null).build();

        //To get the URI of the use we use location
        //      /users/4 => /users/{id},        user.getID

		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
						.path("/{id}")
						.buildAndExpand(savedUser.getId())
						.toUri();   
		
		return ResponseEntity.created(location).build();
	}

    // Delete /users/{id}
    @DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) 
    {
		service.deleteById(id);
	}
}
