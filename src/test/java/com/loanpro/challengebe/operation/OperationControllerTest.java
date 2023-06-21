package com.loanpro.challengebe.operation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.loanpro.challengebe.operation.dto.OperationRequestDTO;
import com.loanpro.challengebe.record.Record;
import com.loanpro.challengebe.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = OperationController.class)
@Import(OperationController.class)
@RunWith(SpringRunner.class)
public class OperationControllerTest {

    //TODO: Move this config out of this file or find a better way to disable security for tests
    @Configuration
    @EnableWebSecurity
    static class SecurityConfig extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .csrf().disable()
                    .authorizeRequests().anyRequest().anonymous();
        }
    }

    @MockBean
    private OperationService operationService;
    @Autowired
    private MockMvc mvc;

    private Record record;

    @BeforeEach
    public void init() {
        record = new Record(new Operation(), new User(), BigDecimal.ONE, BigDecimal.TEN, "10");
        Mockito.when(operationService.execute(any(), any(), any()))
                .thenReturn(record);
    }

    @Test
    public void executesOperation_ok() throws Exception {
        OperationRequestDTO dto = new OperationRequestDTO("ADDITION", BigDecimal.TEN, BigDecimal.ONE);
        ObjectMapper mapper = new ObjectMapper();
        String dtoAsJSon = mapper.writeValueAsString(dto);
        mvc.perform(post("/v1/operation")
                .contentType(MediaType.APPLICATION_JSON).content(dtoAsJSon))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", is(record.getResponse())));
    }

}
