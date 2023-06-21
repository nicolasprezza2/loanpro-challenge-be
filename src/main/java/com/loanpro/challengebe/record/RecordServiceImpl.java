package com.loanpro.challengebe.record;

import com.loanpro.challengebe.user.User;
import com.loanpro.challengebe.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecordServiceImpl implements RecordService {

    private static final BigDecimal USER_STARTING_BALANCE = new BigDecimal(100);

    private final RecordRepository repository;

    @Override
    public Page<Record> findActiveRecordsForCurrentUser(Pageable pageable) {
        User user = SecurityUtils.getCurrentUser();
        Page<Record> records = repository.findByUserAndActive(user, true, pageable);
        return records;
    }

    @Override
    public BigDecimal getBalanceForUser(User user) {
        Optional<Record> record = this.repository.findLastActiveRecordByUser(user.getId());
        if (record.isPresent()) {
            return record.get().getUserBalance();
        }

        return USER_STARTING_BALANCE;
    }

    @Override
    public Record save(Record record) {
        return this.repository.save(record);
    }

    @Override
    @Transactional
    public void delete(Long recordId) {
        this.repository.softDeleteById(recordId);
    }

}
