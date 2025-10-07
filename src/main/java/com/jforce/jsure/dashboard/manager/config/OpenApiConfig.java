package com.jforce.jsure.dashboard.manager.config;

import com.jforce.jsure.base.util.VersionUtil;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

	
	@Value("${jsure.api-manager-url}")
	private String apiUrl;
	
	@Value("${server.servlet.context-path}")
	private String serverContextPath;
	
	@Autowired
	private VersionUtil versionUtil;
	
	private static final String SECURITY_SCHEMA_NAME="bearerAuth";
	
	@Bean
	public OpenAPI customizeOpenAPI() {
		OpenAPI openApi = new OpenAPI().addSecurityItem(new SecurityRequirement().addList(SECURITY_SCHEMA_NAME))
				.components(new Components().addSecuritySchemes(SECURITY_SCHEMA_NAME, new io.swagger.v3.oas.models.security.SecurityScheme().name(SECURITY_SCHEMA_NAME).type(io.swagger.v3.oas.models.security.SecurityScheme.Type.HTTP).scheme("bearer")));
		 
		openApi.info(createInfo()); 
		
		openApi.addServersItem(createServer());
		return openApi;
	}
	
	private Server createServer() {
		Server server=new Server();
		server.setUrl(apiUrl+serverContextPath);
		
		return server;
	}
	
	private Info createInfo() {
		Info info = new Info();
		info.setTitle(versionUtil.getCompanyName());
		
		info.setTermsOfService(versionUtil.getCompanyURL());
		info.setVersion(versionUtil.getFullVersion());
		return info;
	}

}
