package com.loanpro.challengebe.operation.dto;

import lombok.Data;

@Data
public class RandomStringResponseDTO {
    private RandomStringResult result;

    public String getOneString() {
        return this.result.random.data[0];
    }

    @Data
    private class RandomStringResult {
        private RandomStringData random;

        @Data
        private class RandomStringData {
            private String[] data;
        }
    }
}
