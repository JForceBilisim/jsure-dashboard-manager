package com.jforce.jsure.dashboard.manager.starter;

import com.jforce.jsure.base.configs.YamlPropertySourceFactory;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.envers.repository.support.EnversRevisionRepositoryFactoryBean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@Slf4j
@SpringBootApplication()
@ComponentScan(basePackages = { "com.jforce.jsure.*" } )
@EntityScan(basePackages = { "com.jforce.jsure.*" })
@PropertySource(value = "classpath:api-definitions.yml", factory = YamlPropertySourceFactory.class)
@EnableEncryptableProperties()
@EnableJpaRepositories(basePackages = { "com.jforce.jsure.*" })
@EnableScheduling
@EnableCaching
public class JsureDashboardManagerStarter {
    public static void main(String[] args) {
        log.info("{} application is starting", JsureDashboardManagerStarter.class.getCanonicalName());
        SpringApplication.run(JsureDashboardManagerStarter.class, args);
        log.info("{} application is started", JsureDashboardManagerStarter.class.getCanonicalName());
    }
}