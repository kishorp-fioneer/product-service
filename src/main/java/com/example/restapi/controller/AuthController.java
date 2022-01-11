package com.example.restapi.controller;

import java.util.UUID;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.restapi.model.UserCredentials;

import io.swagger.annotations.Api;

@RestController
@RequestMapping(path = "/api/")
@Api(value = "AuthControllerAPI", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthController {

	@PostMapping("/login")
	public String login(@RequestBody UserCredentials userCredentials) {
		// simulate call to auth system to authenticate and get access_token for the user
		// UserInfo userInfo = authServer.login(userCredentials.getUsername(),
		// userCredentials.getPassword());
		// String access_token = userInfo.get("access_token");
		// simulate network lag
		try {
			Thread.sleep(40);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// generate UUID as api_key
		UUID uuid = UUID.randomUUID();

		return String.format("{\"user\": \"%s\", \"access_token\": \"%s\"}", userCredentials.getUsername(), uuid);
	}

	@GetMapping("/logout")
	public void logout() {
		// simulate logout
		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		UserCredentials credentials = new UserCredentials();
		credentials.setUsername("user1");
		credentials.setPassword("password");
		System.out.println(new AuthController().login(credentials));
	}

}
