package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.repo.UserRepo;

@RestController
public class UserController {

	public static Double sumvalue(Double a, Double b) {
		return a+b;
	}
	
	@Autowired
	private UserRepo repo;
	
	@GetMapping("/alluser")
	public List<User> getAllUser(){
		List<User> users= repo.findAll();
		System.out.println(users);
//		System.out.println(repo.findAll());
		return repo.findAll();
	}
	
	@GetMapping("/sortbyage")
	public List<User> sortByAge(){
		
//		UserController cont = new UserController();
//		List<User> finalList = null;
		
		List<User> finalList=new ArrayList<User>();
		
//		List<User> userList = cont.getAllUser();
		
		List<User> userList = repo.findAll();
		System.out.println("All User: " + userList);
		
		for (User user : userList) {
			
			System.out.println("user list: " + user);
			System.out.println("user age: " +user.getAge());
			
			if (user.getAge()<30) {
				finalList.add(user);
			}
			
			System.out.println("final list: " + finalList);
		}
		
		return finalList;
	}
	
	@GetMapping("/addSn")
	public List<Object> addSn(){
		
		List<User> allUser = repo.findAll();
		List<Object> finalList = new ArrayList<>();
		int i =1;
		
		for (User user : allUser) {
			if(user.getGender().equalsIgnoreCase("female")) {
				Map <String, Object> finalMap = new HashMap<String, Object>();

				System.out.println("each user: " + user);
				finalMap.put("Sn", i);
				System.out.println("1st amandement in map: " + finalMap);
				i++;
				finalMap.put("User", user);
				System.out.println("2nd amandement in map: " + finalMap);
				finalList.add(finalMap);
				System.out.println("list after each iteration: " + finalList);
				System.out.println(" ");
			}
//			finalList.add(finalMap);
		}
		
//		return finalList;
		return finalList;
	}
	
	@GetMapping("/addSN2")
	public List<?> getAddSn(){
		
//		allUser = repo.findAll();
		
		List<Object> finalList = new ArrayList<>();
		int i =1;
		
		
		for (User user : repo.findAll()) {
			if(user.getGender().equalsIgnoreCase("male")) {
				Map <String, Object> finalMap = new HashMap<String, Object>();
			
				Long id = user.getId();
				String name = user.getName();
				int age = user.getAge();
				String gender = user.getGender();
				double salary = user.getSalary();
				
//				Map<String, Comparable> finalMap = new HashMap<String, Comparable>();
				finalMap.put("Sn", i);
				i++;
				finalMap.put("id", id);
				finalMap.put("name",name);
				finalMap.put("age",age);
				finalMap.put("gender",gender);
				finalMap.put("salary",salary);
				finalList.add(finalMap);
				
				
				
			}
		}
		
		return finalList;
	}
	
	@GetMapping("/salarytax")
	public List<?> salaryTax(){
		
		List<Object> finalList = new ArrayList<>();
		double totalTax =0;
		double totalSalary=0;
		for (User user: repo.findAll()) {
			Map<String, Object> finalMap = new HashMap();
			
			if(user.getSalary()<=30000) {
				double finalSalary = user.getSalary() - 0.1 * user.getSalary();
				double tax = user.getSalary() * 0.1;
				totalTax = totalTax + tax;
				totalSalary = totalSalary + finalSalary;
				finalMap.put("tax", tax);
				finalMap.put("Range", "L");
				finalMap.put("salary", finalSalary);
			}
			else if(user.getSalary()>30000 && user.getSalary()<60000)  {
				double finalSalary = user.getSalary() - 0.2 * user.getSalary();
				double tax = user.getSalary() * 0.2;
				finalMap.put("tax", tax);
				finalMap.put("Range", "M");
				finalMap.put("salary", finalSalary);
				totalTax = totalTax + tax;
				totalSalary = totalSalary + finalSalary;
			}
			else {
				double finalSalary = user.getSalary() - 0.1 * user.getSalary();
				double tax = user.getSalary() * 0.3;
				finalMap.put("tax", tax);
				finalMap.put("Range", "L");
				finalMap.put("salary", finalSalary);
				totalTax = totalTax + tax;
				totalSalary = totalSalary + finalSalary;
			}
			
			finalMap.put("name",user.getName());
			finalMap.put("id",user.getId());
			finalMap.put("gender", user.getGender());
			finalMap.put("age", user.getAge());
			finalMap.put("CTC", user.getSalary());
			
			finalList.add(finalMap);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("Total Salary", totalSalary);
		map.put("Total Tax", totalTax);
		
		finalList.add(map);
		
		return finalList;
	}
	
	@GetMapping("/sortByAgeWithFunction")
	public List<User> sortByAgeWithFunction(){
		List<User> user = new ArrayList<User>();
		user = repo.findAll();
//		for (User u : repo.findAll()) {
//			user.add(new User(u.getId(), u.getName(), u.getAge(), u.getGender(), u.getSalary()));
//		}
		
		
		
		Comparator<User> comp = new Comparator<User>() {
			
			@Override
			public int compare(User u1, User u2) {
				
				if(u1.getAge()>u2.getAge())
					return 1;
				else
					return -1;
				
			}
		};
		
		Collections.sort(user, comp);
		
		
		System.out.println(user);
		return user;
	}
	
	@GetMapping("/sortBySalary")
	public List<User> sortBySalary(){
		List<User> user = new ArrayList<User>();
		user = repo.findAll();
		
		Comparator<User> comp = new Comparator<User>() {
			
			@Override
			public int compare(User u1, User u2) {
				
				if(u1.getSalary()>u2.getSalary())
					return 1;
				else
					return -1;
			}
		};
		
		Collections.sort(user, comp);
		
		
		System.out.println(user);
		return user;
	}
	
	class NameCompare implements Comparator<User>
	{
	    public int compare(User m1, User m2)
	    {
	        return m1.getName().compareTo(m2.getName());
	    }
	}
	
	@GetMapping("/sortByName")
	public List<User> sortByName(){
//		List<User> user = new ArrayList<User>();
		List<User> user = repo.findAll();
		NameCompare nameCompare = new NameCompare();
		
		
		Collections.sort(user, nameCompare);
		System.out.println(user);
		return user;
	}
	
	@GetMapping("/filterByAgeUsingStream")
	public void filterByAgeUsingStream( @RequestParam int age){
		List<User> user = repo.findAll();
		List<User> result = user.stream().filter(s->s.getAge()>age).collect(Collectors.toList());
		 
		System.out.println(result);
//		return result;
	}
	
	@GetMapping("/sumOfSalary")
	public double sumOfSalary() {
		List<User> users = repo.findAll();
//		List<Double> list = new ArrayList<>();
//		for (User user : repo.findAll()) {
//			list.add(user.getSalary());
//		}
//		System.out.println(list);
//		double sum = 0;
//		double sum=0; 
//		user.stream().map(s->sum+s.getSalary()).forEach(y-> System.out.println(y.));
//		double sum = list.stream().filter(x->x>0).reduce((double) 0,(ans,i)-> ans+i);
		
//		int com =8;
//		double sum = users.stream().filter(o -> o.getSalary() > 0).mapToDouble(o -> o.getSalary()).sum();
		double sum = users.stream().mapToDouble(o -> o.getSalary()).sum();
		System.out.println(sum);
		return sum;
	}
	
//	@GetMapping("/steamIfCondition")
//	public List steamIfCondition() {
//		
//		List<KeyType> myList=animalMap.entrySet().stream()
//			    .filter(pair -> pair.getValue() == null)
//			    .map(Map.Entry::getKey)
//			    .collect(Collectors.toList());
//
//			animalMap.keySet().removeAll(myList);
//		
//		
//		return finalList;
//	}
	
	
	@GetMapping("/test")
	public List<String> test(){
		 String match = "e";
		    List<String> myList = Arrays.asList("one", "two", "three", null, "abc");
		    List<String> filtered_result = myList.stream()
		    		.filter(e -> e!= null)
		            .filter(e -> e.indexOf(match) != -1) 
		            .collect(Collectors.toList());
		    System.out.println(filtered_result);
		    return filtered_result;
	}
	
	@GetMapping("/test1")
	public List<Integer> test1(){
		 int sum = 10;
		  int a =10;
		    List<Integer> myList = Arrays.asList(10,12,14,16);
		    List<Integer> filtered_result = myList.stream() 
		            .map( e ->
                     
		            e + sum
		            ) 
		            .collect(Collectors.toList());
		    System.out.println(filtered_result);
		    return filtered_result;
	}
	
//	@GetMapping("/test2")
//	public Integer sum() {
//		List<User> users = repo.findAll();
////		Optional<User> sum = users.stream()
////				  .reduce((a, b) -> ( a.getSalary() + b.getSalary()));
//		UserController.sumvalue(null, null);
//	return users.stream()
//                .reduce(0, (s,ob)-> s +  ob.getSalary() ,  (a, b) ->  a+b ); 
//	}
	
	@GetMapping("/test2")
	public List<User> testUser(List<User> users){
//		Map<String, String> uMap = new HashMap();
		List<User> usr = repo.findAll();
		
		
		
		List<User> u = usr.stream().map(this::mapNewDetails)
		.collect(Collectors.toList());
		return u;
	}
	
	public User  mapNewDetails(User user) {
//		UserController uCon = new UserController();
		Map<Object, Object> uMap = new HashMap<Object, Object>();
		uMap.put("Name", user.getName());
		return (User) uMap;
	}
	
	@GetMapping("/test4")
	public User mapListToJsonArray(List<User> user) {
	    List<User> jsonObjects = user
	            .stream()
	            .map(u -> {
	                User json = new User();
	               ((Map<String, Object>) json).put("name", ((User) user).getName());
	                ((Map<String, Object>) json).put("salary", ((User) user).getSalary());
	                return json;
	            })
	            .collect(Collectors.toList());
	    return (User) jsonObjects;
	}
	
	
	@GetMapping("/search")
//	@ResponseStatus(HttpStatus.FOUND)
	public List<User> search(@RequestParam(required = true) String search) {
//		System.out.println("search value = " + search);
//		System.out.println(repo.search(search));
//		return repo.search(search);
		
		if(search.isBlank() != true) {
			System.out.println(search.length());
			System.out.println("search value = " + search);
			return repo.search(search);
		}
		else
			return null;
	}
	
	@GetMapping("/search1")
	public ResponseEntity<List<User>> search1(@RequestParam String search1){
		if(search1.isBlank() != true && search1.isEmpty()!=true) {
			System.out.println(search1.length());
			System.out.println("search value = " + search1);
//			return repo.search(search1);
			List<User> lis = repo.search(search1);
			Map<String, User> mapLis = new HashMap();
			for (User user : lis) {
				mapLis.put(user.getName(), user);
			}
//			return ResponseEntity<String>(HttpStatus.FOUND);
			return ResponseEntity.status(HttpStatus.FOUND).body(lis);
		}
		else
//			return ResponseEntity<String>(HttpStatus.BAD_REQUEST);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	}
}


