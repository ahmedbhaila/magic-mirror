package com.bhailaverse;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.bhailaverse.khabarnama.KhabarnamaApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = KhabarnamaApplication.class)
@WebAppConfiguration
public class KhabarnamaApplicationTests {

	@Test
	public void contextLoads() {
	}

}
