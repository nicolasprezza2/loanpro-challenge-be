package com.loanpro.challengebe.record;

import com.loanpro.challengebe.operation.Operation;
import com.loanpro.challengebe.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class RecordServiceImpl implements RecordService {

    private static final BigDecimal USER_STARTING_BALANCE = new BigDecimal(100);

    private final RecordRepository repository;

    @Autowired
    public RecordServiceImpl(RecordRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Record> getAllForCurrentUser() {

        // find  current user
        // return all by id
        return null;
    }

    @Override
    public BigDecimal getBalanceForUser(User user) {
        Optional<Record> record = this.repository.findTopByUser(user, Sort.by("user").descending());

        if(record.isPresent()) {
            return record.get().getUserBalance();
        }

        return USER_STARTING_BALANCE;
//        if (record == null) {
//            return USER_STARTING_BALANCE;
//        }
//
//        return record.getUserBalance();
    }

    @Override
    public Record add(Operation operation, User user, BigDecimal cost, BigDecimal newBalance, String operationResponse) {
        Record record = new Record(operation, user, cost, newBalance, operationResponse);

        return this.repository.save(record);
    }

}
