package com.customer.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class TestRest {
	
	@RequestMapping("test")
	public String display() {
		System.out.println("tefsccbc");
		return "working again customer ms ......";
	}

}
