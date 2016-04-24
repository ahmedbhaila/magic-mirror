package com.bhailaverse;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.bhailaverse.mausam.MausamApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MausamApplication.class)
@WebAppConfiguration
public class MausamApplicationTests {

	@Test
	public void contextLoads() {
	}

}
