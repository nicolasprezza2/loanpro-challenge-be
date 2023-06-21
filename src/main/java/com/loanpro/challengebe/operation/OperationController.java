package com.loanpro.challengebe.operation;

import com.loanpro.challengebe.operation.dto.OperationRequestDTO;
import com.loanpro.challengebe.operation.dto.OperationResponseDTO;
import com.loanpro.challengebe.record.Record;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/v1/operation")
@RequiredArgsConstructor
public class OperationController {

    private final OperationService service;

    @PostMapping
    public ResponseEntity<OperationResponseDTO> execute(@RequestBody OperationRequestDTO dto) {
        OperationType opType = OperationType.valueOf(dto.getType());
        Record record = service.execute(opType, dto.getFirstNumber(), dto.getSecondNumber());

        return ResponseEntity.ok().body(OperationResponseDTO.fromRecord(record));
    }
}
