package com.example;

import static org.assertj.core.api.Assertions.registerCustomDateFormat;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	@Autowired
	private CustomerSearchDao customerSearchDao;
	
	@RequestMapping("/")
	public String saveUser(){
		
		Users user1 = new Users("Emon",15);
		customerSearchDao.saveUser(user1);
		Users user2 = new Users("Anik",20);
		customerSearchDao.saveUser(user2);
		Users user3 = new Users("Sawon",20);		
		customerSearchDao.saveUser(user3);
		
		List<Users> users = new ArrayList<Users>();
		users.add(user1);
		users.add(user2);
		users.add(user3);
		return users.toString();
	}
	
	@RequestMapping("/show")
	public String showUser(){
		List<Users> user = customerSearchDao.search("7");
		if(user.isEmpty())
			System.out.println("No result");
		for(Users usr : user){
			System.out.println(usr.getName()+" "+usr.getAge());
		}
		return user.toString();
	}
	
}
