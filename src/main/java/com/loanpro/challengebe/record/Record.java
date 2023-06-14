package com.loanpro.challengebe.record;

import com.loanpro.challengebe.operation.Operation;
import com.loanpro.challengebe.operation.OperationResponse;
import com.loanpro.challengebe.user.User;
import com.sun.istack.NotNull;
import lombok.Getter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "record")
@Getter
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "operationId")
    private Operation operation;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @NotNull
    private BigDecimal amount;

    @NotNull
    private BigDecimal userBalance;

    @NotNull
    private String response;

    @NotNull
    private LocalDateTime date;
    @NotNull
    private boolean active;

    public Record(Operation operation, User user, BigDecimal amount, BigDecimal userBalance, String response) {
        this.operation = operation;
        this.user = user;
        this.amount = amount;
        this.userBalance = userBalance;
        this.response = response;
        this.date = LocalDateTime.now();
        this.active = true;
    }
    /*
    * ○id
○ operation_id
○ user_id
○ amount
○ user_balance
○ operation_response ○ date
*/
}
