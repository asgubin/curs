package ru.asgubin.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.asgubin.test.entity.Balance;
import ru.asgubin.test.repository.BalanceRepository;

@Component
public class BalanceService extends AbstractService<Balance, Long, BalanceRepository> {

    @Autowired
    public BalanceService(BalanceRepository repository) {

        super(repository);
    }
}