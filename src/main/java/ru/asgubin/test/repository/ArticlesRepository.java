package ru.asgubin.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.asgubin.test.entity.Articles;

public interface ArticlesRepository extends JpaRepository<Articles, Long> {

}