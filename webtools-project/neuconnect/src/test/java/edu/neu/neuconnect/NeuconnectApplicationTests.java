package edu.neu.neuconnect;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class NeuconnectApplicationTests {

	@Test
	public void testContextLoads() {
		int num1 = 1;
		int num2 = 1;

		// Perform addition
		int result = num1 + num2;

		// Assert that the result is equal to 2
		assertEquals(2, result);
	}

}
