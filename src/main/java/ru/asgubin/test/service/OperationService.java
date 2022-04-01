package ru.asgubin.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.asgubin.test.entity.Balance;
import ru.asgubin.test.entity.Operations;
import ru.asgubin.test.repository.OperationsRepository;

import java.util.Date;
import java.util.List;

@Component
public class OperationService extends AbstractService<Operations, Long, OperationsRepository> {

    @Autowired
    public OperationService(OperationsRepository repository) {

        super(repository);
    }

    public List<Operations> findByCreate_dateBetween(
            Date createDateStart, Date createDateEnd) {

        return repository.findAllByCreateDateBetween(
                createDateStart, createDateEnd);
    }

    public List<Operations> findAllByBalanceId(Balance balance) {

        return  repository.findAllByBalanceId(balance);
    }
}
