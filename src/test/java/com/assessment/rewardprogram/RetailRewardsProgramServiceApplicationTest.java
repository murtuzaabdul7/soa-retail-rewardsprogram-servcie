package com.assessment.rewardprogram;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.client.RestTemplate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RetailRewardsProgramServiceApplicationTest {
	
	@InjectMocks
	RetailRewardsProgramServiceApplication retailRewardsProgramServiceApplication;
	
	@Mock
	RestTemplate restTemplate;
	
	@Test
	public void testMain() {
		Mockito.mockStatic(SpringApplication.class);
		ConfigurableApplicationContext configurableApplicationContextMock = Mockito.mock(ConfigurableApplicationContext.class);
		when(SpringApplication.run((Class<?>)any(), any())).thenReturn(configurableApplicationContextMock);
		RetailRewardsProgramServiceApplication.main(new String[1]);
	}

}
