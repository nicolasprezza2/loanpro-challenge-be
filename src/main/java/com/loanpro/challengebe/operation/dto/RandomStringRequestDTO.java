package com.loanpro.challengebe.operation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Random;


@Getter
@Builder
@AllArgsConstructor
public class RandomStringRequestDTO {

    private String jsonrpc;
    private String method;
    private RequestParams params;
    private Long id;

    public static RandomStringRequestDTO generateBasicRequest(String apiKey) {
        RequestParams p = RequestParams.builder()
                .apiKey(apiKey)
                .n(1)
                .length(10)
                .characters("abcdefghijklmnopqrstuvwxyz")
                .replacement(true)
                .build();
        RandomStringRequestDTO dto = RandomStringRequestDTO.builder()
                .params(p)
                .jsonrpc("2.0")
                .method("generateStrings")
                .id(new Random().nextLong())
                .build();
        return dto;
    }


    @AllArgsConstructor
    @Builder
    @Getter
    static class RequestParams {
        private String apiKey;
        private Integer n;
        private Integer length;
        private String characters;
        private boolean replacement;
    }

}

