package com.loanpro.challengebe.record;

import com.loanpro.challengebe.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecordRepository extends JpaRepository<Record, Long> {
    Page<Record> findByUserAndActive(User user, boolean active, Pageable pageable);

    @Query(value = "SELECT TOP 1 * " +
            " FROM Record r" +
            " WHERE r.user_id = :userId " +
            "   AND r.active = true " +
            " ORDER BY r.id DESC",
            nativeQuery = true)
    Optional<Record> findLastActiveRecordByUser(@Param("userId") Long userId);

    @Modifying
    @Query("UPDATE Record r " +
            "SET r.active = false " +
            "WHERE r.id = :recordId")
    void softDeleteById(@Param("recordId") Long recordId);
}
