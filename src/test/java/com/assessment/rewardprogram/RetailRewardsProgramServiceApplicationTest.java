package com.assessment.rewardprogram;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.client.RestTemplate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RetailRewardsProgramServiceApplicationTest {
	
	@InjectMocks
	RetailRewardsProgramServiceApplication retailRewardsProgramServiceApplication;
	
	@Mock
	RestTemplate restTemplate;

    @Test
    void testMain() {
		Mockito.mockStatic(SpringApplication.class);
		ConfigurableApplicationContext configurableApplicationContextMock = Mockito.mock(ConfigurableApplicationContext.class);
		when(SpringApplication.run((Class<?>)any(), any())).thenReturn(configurableApplicationContextMock);
		RetailRewardsProgramServiceApplication.main(new String[1]);
	}

}
