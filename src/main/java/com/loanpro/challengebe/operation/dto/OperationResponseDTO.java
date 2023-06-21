package com.loanpro.challengebe.operation.dto;


import com.loanpro.challengebe.record.Record;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class OperationResponseDTO {

    @NotNull
    private String result;

    public static OperationResponseDTO fromRecord(Record record) {
        return new OperationResponseDTO(record.getResponse());
    }

}
