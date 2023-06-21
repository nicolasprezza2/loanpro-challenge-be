package com.loanpro.challengebe.record;

import com.loanpro.challengebe.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;

public interface RecordService {

    Page<Record> findActiveRecordsForCurrentUser(Pageable pageable);

    BigDecimal getBalanceForUser(User user);

    Record save(Record record);

    void delete(Long recordId);
}
