package com.example.SSM;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SsmApplication {

	public static void main(String[] args) {
		SpringApplication.run(SsmApplication.class, args);
	}
}

//@SuppressWarnings("deprecation")
//@Bean
//public CommandLineRunner preloadDatabase(JobRepository repository) {
//	return (args) -> {
//		repository.save(new Job("text1", new Date(92, 0, 7, 15, 23, 32)));
//		repository.save(new Job("text2", new Date(92, 0, 7, 16, 56, 42)));
//		repository.save(new Job("text3", new Date(92, 0, 7, 9, 10, 11)));
//		repository.save(new Job("text4", new Date(92, 0, 7, 13, 32, 54)));
//		repository.save(new Job("text5", new Date(92, 0, 7, 23, 54, 9)));
//	};
//}