package ru.asgubin.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import ru.asgubin.test.entity.Articles;
import ru.asgubin.test.service.ArticleService;
import ru.asgubin.test.service.BalanceService;
import ru.asgubin.test.service.OperationService;

import java.util.Optional;

@SpringBootApplication
public class TestApplication {

	@Autowired
	private ArticleService articleService;
	@Autowired
	private BalanceService balanceService;
	@Autowired
	private OperationService operationService;

	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	private void testJpa() {

		System.out.println();
		operationService.findAll().forEach(System.out::println);

		System.out.println();
		articleService.findAll().forEach(System.out::println);

		System.out.println();
		balanceService.findAll().forEach(System.out::println);

		System.out.println();
		Optional<Articles> article = articleService.findById(1L);
		article.ifPresent(System.out::println);
	}
}
