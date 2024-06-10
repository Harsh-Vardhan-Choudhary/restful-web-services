package com.harsh.rest.webservices.restful_web_services.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.net.URI;
import java.util.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.harsh.rest.webservices.restful_web_services.jpa.UserRepository;

import jakarta.validation.Valid;

@RestController
public class UserJpaResource 
{
    private UserDaoService service;

	private UserRepository repository;

	public UserJpaResource(UserDaoService service, UserRepository repository) 
    {
		this.service = service;
		this.repository = repository;
	}

	// GET /users
	@GetMapping("/jpa/users")
	public List<User> retrieveAllUsers() 
    {
		return repository.findAll();
	}

	//http://localhost:8080/users

	//to make use of HATEOAS-
	//Entity model
	//WebMvcLinkBuilder


	// GET /users/{id}
	@GetMapping("/jpa/users/{id}")
	public EntityModel<User> retrieveUser(@PathVariable int id) 
    {
        Optional<User> user = repository.findById(id);

        // is user not found UserNotFoundException.class extends RuntimeException -> Error 404 with id 
        if(user.isEmpty())
        {
            throw new UserNotFoundException("id"+id);
        }

		EntityModel<User> entityModel = EntityModel.of(user.get());

		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		entityModel.add(link.withRel("all-users"));
		
		return entityModel;
	}

	// // GET /users/{id}
	// @GetMapping("/users/{id}")
	// public User retrieveUser(@PathVariable int id) 
    // {
    //     User user = service.findOne(id);

    //     // is user not found UserNotFoundException.class extends RuntimeException -> Error 404 with id 
    //     if(user == null)
    //     {
    //         throw new UserNotFoundException("id"+id);
    //     }

	// 	return user;
	// }

	//-------------------------------


    //we want to send something which has same structure as User
    //That's why the content is passed as user body
    //POST /users

    // @PostMapping("/users")
	// public void createUser(@RequestBody User user) 
    // {
	// 	service.save(user);
	// }

    //Getting the response status - 201
    @PostMapping("/jpa/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) 
    {	
		User savedUser = repository.save(user);

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
    @DeleteMapping("/jpa/users/{id}")
	public void deleteUser(@PathVariable int id) 
    {
		repository.deleteById(id);
	}
}
