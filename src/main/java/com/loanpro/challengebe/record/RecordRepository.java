package com.loanpro.challengebe.record;

import com.loanpro.challengebe.user.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecordRepository extends JpaRepository<Record, Long> {

    List<Record> findByUser(User user);

    Optional<Record> findTopByUser(User user, Sort sort);
}
