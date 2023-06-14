package com.loanpro.challengebe.record;

import com.loanpro.challengebe.operation.Operation;
import com.loanpro.challengebe.user.User;

import java.math.BigDecimal;
import java.util.List;

public interface RecordService {

    List<Record> getAllForCurrentUser();

    BigDecimal getBalanceForUser(User user);

    Record add(Operation operation, User user, BigDecimal cost, BigDecimal newBalance, String operationResponse);
}
