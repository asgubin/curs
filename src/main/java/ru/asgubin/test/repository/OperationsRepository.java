package ru.asgubin.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.asgubin.test.entity.Balance;
import ru.asgubin.test.entity.Operations;

import java.util.Date;
import java.util.List;

public interface OperationsRepository extends JpaRepository<Operations, Long> {

    List<Operations> findAllByCreateDateBetween(
            Date createDateStart, Date createDateEnd);

    List<Operations> findAllByBalanceId(Balance balance);
}