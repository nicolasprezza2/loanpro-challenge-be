package com.loanpro.challengebe.operation;

import com.loanpro.challengebe.operation.dto.OperationRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/operation")
public class OperationController {

    private final OperationService service;

    public OperationController(OperationService operationService) {
        this.service = operationService;
    }

    @PostMapping
    public ResponseEntity execute(@RequestBody OperationRequestDTO dto) {
        // Receives =
        OperationType opType = OperationType.valueOf(dto.getType());
        service.execute(opType, dto.getFirstNumber(), dto.getSecondNumber());

        return null;
    }
}
