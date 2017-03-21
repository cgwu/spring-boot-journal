package com.apress.spring;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.apress.spring.domain.Testjson2;
import com.apress.spring.repository.Testjson2Repository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestJsonRepositoryTest {

	@Autowired
	Testjson2Repository repo;
	
	@Test
	public void test() {
		repo.save(new Testjson2("{\"name\":\"吴cg\",\"age\":130}", new BigDecimal(3.1415926),
				Arrays.asList("好答案", "aa", "BB", "c#")));
		repo.save(new Testjson2("[1,2,3,4,5,987]", new BigDecimal(12345), Arrays.asList("好答案1")));
		repo.save(new Testjson2("{}", new BigDecimal(12)));
		repo.save(new Testjson2("[]", BigDecimal.TEN, Arrays.asList("好答案", "aa", "BB", "c#!@#$")));
		repo.save(new Testjson2(null));
	}

}
