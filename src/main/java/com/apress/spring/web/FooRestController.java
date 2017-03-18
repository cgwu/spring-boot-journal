package com.apress.spring.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/foo")
public class FooRestController {
	@RequestMapping("/hi")
	public String hi() {
		return "foo你好";
	}
}
