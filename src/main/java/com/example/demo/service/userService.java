package com.example.demo.service;

import java.util.Comparator;

import com.example.demo.model.User;

public class userService {

	Comparator<User> comp = new Comparator<User>() {

		

		@Override
		public int compare(User u1, User u2) {
			
			if(u1.getAge()>u2.getAge())
				return 1;
			else
				return -1;
			
		}
	};
}
