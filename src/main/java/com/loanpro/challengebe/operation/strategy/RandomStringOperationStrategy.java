package com.loanpro.challengebe.operation.strategy;

import com.loanpro.challengebe.operation.OperationType;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Component
public class RandomStringOperationStrategy implements OperationStrategy {

    private String RANDOM_STRING_URL = "https://www.random.org/clients";

    private final RestTemplate restTemplate;

    public RandomStringOperationStrategy(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void validateNumbers(BigDecimal... numbers) {
        // Does not apply
    }

    @Override
    public String run(BigDecimal... numbers) {

        // Call random string url and return value
        ResponseEntity<String> response = restTemplate.exchange(
                RANDOM_STRING_URL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<String>() {}
        );

        return response.getBody();
    }

    @Override
    public OperationType getName() {
        return OperationType.RANDOM_STRING;
    }
}
