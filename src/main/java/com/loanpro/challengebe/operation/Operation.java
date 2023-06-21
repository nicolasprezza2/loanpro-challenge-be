package com.loanpro.challengebe.operation;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "operation")
@NoArgsConstructor(force = true)
@Getter
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private OperationType type;
    @NotNull
    private BigDecimal cost;

    public Operation(OperationType type, BigDecimal cost) {
        this.type = type;
        this.cost = cost;
    }
}
