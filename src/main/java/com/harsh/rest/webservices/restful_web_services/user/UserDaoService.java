package com.harsh.rest.webservices.restful_web_services.user;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService 
{
    //UserDaoService > Static List -> (JPA/Hibernate) > Database
    //public List<User> findAll()
    //public User save(User user)
    //public User findOne(int id)

    private static List<User> users = new ArrayList<>();

    private static int usersCount = 0;

    static
    {
        users.add(new User(++usersCount, "Adam", LocalDate.now().minusYears(30)));
        users.add(new User(++usersCount, "Eve", LocalDate.now().minusYears(25)));
        users.add(new User(++usersCount, "Adivya", LocalDate.now().minusYears(21)));
    }

    public List<User> findAll()
    {
        return users;
    }

    public User findOne(int id) 
    {
        //return users.get(id-1);

		Predicate<? super User> predicate = users -> users.getId().equals(id); 

		//if we pass any index(id) not declared it will provide us - Internal Server Error(500)
        //return users.stream().filter(predicate).findFirst().get();

        //if we pass any index(id) not declared it will provide us a blank page
        return users.stream().filter(predicate).findFirst().orElse(null);
	}

    public User save(User user) 
    {
		user.setId(++usersCount);
		users.add(user);
		return user;
	}

    public void deleteById(int id) 
    {
		Predicate<? super User> predicate = user -> user.getId().equals(id);
		users.removeIf(predicate);
	}

    
}
