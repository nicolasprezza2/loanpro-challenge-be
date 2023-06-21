package com.loanpro.challengebe.operation.strategy;

import com.loanpro.challengebe.operation.OperationType;
import com.loanpro.challengebe.operation.dto.RandomStringRequestDTO;
import com.loanpro.challengebe.operation.dto.RandomStringResponseDTO;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Component
public class RandomStringOperationStrategy implements OperationStrategy {


    private final String RANDOM_STRING_URL = "https://api.random.org/json-rpc/4/invoke";
    private String apiKey = "957808a5-1d39-42a7-9267-dea2c8717850"; // TODO: move to properties file
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

        RandomStringRequestDTO dto = RandomStringRequestDTO.generateBasicRequest(apiKey);
        HttpEntity<RandomStringRequestDTO> request = new HttpEntity<>(dto);


        // Call random string url and return value
        ResponseEntity<RandomStringResponseDTO> response = restTemplate.exchange(
                RANDOM_STRING_URL,
                HttpMethod.POST,
                request,
                new ParameterizedTypeReference<RandomStringResponseDTO>() {}
        );

        return response.getBody().getOneString();
    }

    @Override
    public OperationType getName() {
        return OperationType.RANDOM_STRING;
    }
}
