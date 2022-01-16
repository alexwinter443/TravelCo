package com.gcu;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

import com.gcu.business.ProductBusinessService;
import com.gcu.business.ProductBusinessServiceInterface;
import com.gcu.business.SecurityService;
import com.gcu.business.SecurityServiceInterface;

/*
 * Kacey morris and Alex vergara
 * Milestone
 * 10/22/2021
 */
@Configuration
public class SpringConfig {
	
	@Bean(name="securityService")
	public SecurityServiceInterface getSecurityService()
	{
		
		return new SecurityService();
		
	}
	
	@Bean(name="productBusinessService", initMethod="init", destroyMethod="destroy")
	public ProductBusinessServiceInterface getOrdersBusiness() {
		return new ProductBusinessService();
	}

}
