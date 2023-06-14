package com.loanpro.challengebe.operation.initiator;

import com.loanpro.challengebe.operation.Operation;
import com.loanpro.challengebe.operation.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static com.loanpro.challengebe.operation.OperationType.*;

@Component
public class OperationInitiator {

    private final OperationRepository repository;

    @Autowired
    public OperationInitiator(OperationRepository repository) {
        this.repository = repository;
    }

    @PostConstruct
    public void initiate() {
        Operation addition = new Operation(ADDITION, new BigDecimal(1));
        Operation subtraction = new Operation(SUBTRACTION, new BigDecimal(1));
        Operation multiplication = new Operation(MULTIPLICATION, new BigDecimal(1));
        Operation division = new Operation(DIVISION, new BigDecimal(1));
        Operation squareFoot = new Operation(SQUARE_ROOT, new BigDecimal(2.5));
        Operation randomString = new Operation(RANDOM_STRING, new BigDecimal(2));

        List<Operation> allOps = Arrays.asList(addition, subtraction, multiplication,
                division, squareFoot, randomString);

        repository.saveAll(allOps);
    }
}
