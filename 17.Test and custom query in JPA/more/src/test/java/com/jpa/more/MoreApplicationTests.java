package com.jpa.more;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MoreApplicationTests {
	//introduction to test and custom query in JPA
	//unit testing 
	@Test 
	 public void testUser(){
		if(3>2){
			throw new RuntimeException("this is error");
		}
	}

	// @Test
	// void contextLoads() {
	// }
}
