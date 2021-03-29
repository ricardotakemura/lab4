package com.sensedia.poc.test;

import java.util.Properties;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class PropertiesTest {

	@Test
	public void testSetPropertyAndGet() {
		Properties properties = new Properties();
		properties.setProperty("name", "My name");
		Assert.assertEquals("My name", properties.get("name"));
	}

	@Test
	public void testPutAndGetProperty() {
		Properties properties = new Properties();
		properties.put("id", 1);
		Assert.assertEquals(String.valueOf(1), properties.getProperty("id"));
	}

	@Test
	public void testPutAndGet() {
		Properties properties = new Properties();
		properties.put("id", 1);
		Assert.assertEquals(1, properties.get("id"));
	}

	@Test
	public void testSetPropertyAndGetProperty() {
		Properties properties = new Properties();
		properties.setProperty("name", "My name");
		Assert.assertEquals("My name", properties.getProperty("name"));
	}
}
