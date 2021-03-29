package com.sensedia.poc.test;

import java.util.Stack;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class StackTest {

	@Test
	public void testPushAndPop() {
		Stack<String> stack = new Stack<>();
		for (int i = 0; i < 10; i++) {
			stack.push("name_" + i);
		}
		Assert.assertEquals("name_9", stack.pop());
	}

	@Test
	public void testAddAndPop() {
		Stack<String> stack = new Stack<>();
		for (int i = 0; i < 10; i++) {
			stack.add("name_" + i);
		}
		Assert.assertEquals("name_9", stack.pop());
	}

	@Test
	public void testInsertAndPop() {
		Stack<String> stack = new Stack<>();
		for (int i = 0; i < 10; i++) {
			stack.insertElementAt("name_" + i, 0);
		}
		Assert.assertEquals("name_0", stack.pop());
	}

	@Test
	public void testPushAndGet() {
		Stack<String> stack = new Stack<>();
		for (int i = 0; i < 10; i++) {
			stack.push("name_" + i);
		}
		Assert.assertEquals("name_7", stack.get(7));
	}

	@Test
	public void testPushAndRemove() {
		Stack<String> stack = new Stack<>();
		for (int i = 0; i < 10; i++) {
			stack.push("name_" + i);
		}
		Assert.assertEquals("name_7", stack.remove(7));
	}
}
