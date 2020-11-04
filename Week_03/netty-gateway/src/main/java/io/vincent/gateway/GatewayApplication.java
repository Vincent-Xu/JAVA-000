package io.vincent.gateway;

import io.vincent.gateway.inbound.HttpInboundServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.Arrays;


/**
 *@Author: Vincent Xu
 *@Description:
 *@CreatedTime:17:15 2020/10/31
 */
@ComponentScan(basePackages = {"io.vincent.gateway.*"})
@SpringBootApplication
public class GatewayApplication implements CommandLineRunner {
	public final static String GATEWAY_NAME = "NIOGateway";
	public final static String GATEWAY_VERSION = "1.0.0";

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
		String proxyServer = System.getProperty("proxyServer","http://localhost:8808");
		String proxyPort = System.getProperty("proxyPort","8888");

		int port = Integer.parseInt(proxyPort);
		System.out.println(GATEWAY_NAME + " " + GATEWAY_VERSION +" starting...");
		HttpInboundServer server = new HttpInboundServer(port, proxyServer);
		System.out.println(GATEWAY_NAME + " " + GATEWAY_VERSION +" started at http://localhost:" + port + " for server:" + proxyServer);
		try {
			server.run();
		}catch (Exception ex){
			ex.printStackTrace();
		}
	}

	@Autowired
	private ApplicationContext appContext;

	@Override
	public void run(String... args) throws Exception
	{

		String[] beans = appContext.getBeanDefinitionNames();
		Arrays.sort(beans);
		for (String bean : beans)
		{
			System.out.println(bean + " of Type :: " + appContext.getBean(bean).getClass());
		}
	}
}
