package com.volunteer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.volunteer.cache.manager.CacheManager;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VolunteerProjectApplicationTests {

	@Autowired
	CacheManager cacheManager;
	
	@Test
	public void contextLoads() {
	}
	
	@Test
	public void redisTest() {
		cacheManager.setValue("test", "redis-test");
	}
	
	@Test
	public void redisGetValueTest() {
		String value = cacheManager.getValue("test");
		System.out.println(value);
	}

}
