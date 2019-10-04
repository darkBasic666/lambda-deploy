package com.lambda.demo;

import com.amazonaws.serverless.proxy.internal.testutils.AwsProxyRequestBuilder;
import com.amazonaws.serverless.proxy.internal.testutils.MockLambdaContext;
import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;
import com.amazonaws.serverless.proxy.spring.SpringBootLambdaContainerHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambda.demo.controllers.PetController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DemoApplication.class,StreamLambdaHandler.class})
@WebAppConfiguration
public class StreamLambdaTest {
	private MockLambdaContext lambdaContext;
	private SpringBootLambdaContainerHandler<AwsProxyRequest, AwsProxyResponse> handler;
	@Autowired
	StreamLambdaHandler streamLambdaHandler;

	@Autowired
	private ObjectMapper mapper;

	public StreamLambdaTest() {
		lambdaContext = new MockLambdaContext();
		this.handler = StreamLambdaHandler.handler;
	}

	@Test
	public void testGet() {
		AwsProxyRequest request = new AwsProxyRequestBuilder("/cat", "GET").build();

		AwsProxyResponse response = handler.proxy(request, lambdaContext);
		assertThat(response.getStatusCode(), equalTo(200));
		assertThat(response.getBody(), equalTo("Prrrrrr"));
	}

}
