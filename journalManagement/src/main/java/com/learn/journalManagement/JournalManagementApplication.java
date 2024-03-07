package com.learn.journalManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class JournalManagementApplication {

	public static void main(String[] args) {

		SpringApplication.run(JournalManagementApplication.class, args);

	}

	@Bean
	public PlatformTransactionManager platformTransactionManagerBean(MongoDatabaseFactory dbFactory){
		return new MongoTransactionManager(dbFactory);
	}


}
