package com.loanpro.challengebe.operation;

import com.sun.istack.NotNull;
import lombok.Getter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "operation")
@Getter
public class Operation {
    /**
     * ○id
     * ○ type (addition, subtraction, multiplication, division, square_root, random_string) ○
     * cost
     */

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
