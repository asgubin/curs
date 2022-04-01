package ru.asgubin.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.asgubin.test.entity.Balance;

public interface BalanceRepository extends JpaRepository<Balance, Long> {

}