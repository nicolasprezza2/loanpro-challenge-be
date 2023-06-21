package com.loanpro.challengebe.operation;

import com.loanpro.challengebe.operation.initiator.OperationInitiator;
import com.loanpro.challengebe.operation.strategy.AdditionOperationStrategy;
import com.loanpro.challengebe.operation.strategy.OperationStrategyFactory;
import com.loanpro.challengebe.record.Record;
import com.loanpro.challengebe.record.RecordService;
import com.loanpro.challengebe.user.User;
import com.loanpro.challengebe.utils.SecurityUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.mockito.Mockito.*;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class OperationServiceTest {

    private RecordService recordServiceMock;
    private OperationStrategyFactory strategyFactoryMock;
    @Captor
    ArgumentCaptor<Record> recordArgumentCaptor;
    private User mockedUser;
    private MockedStatic<OperationInitiator> initiatorMock;
    private MockedStatic<SecurityUtils> securityUtilsMock;
    private OperationService target;

    @BeforeEach
    void init(){
        recordServiceMock = Mockito.mock(RecordService.class);
        strategyFactoryMock = Mockito.mock(OperationStrategyFactory.class);
        target = new OperationServiceImpl(this.recordServiceMock, this.strategyFactoryMock);
        securityUtilsMock = Mockito.mockStatic(SecurityUtils.class);
        mockedUser = new User("test@test.com", "password");
        securityUtilsMock.when(() -> SecurityUtils.getCurrentUser()).thenReturn(mockedUser);
        initiatorMock = Mockito.mockStatic(OperationInitiator.class);
    }

    @AfterEach
    public void close() {
        securityUtilsMock.close();
        initiatorMock.close();
        Mockito.reset(recordServiceMock, strategyFactoryMock);
    }

    @Test
    public void givenUserWithBalance_shouldExecuteOk(){
        OperationType operationType = OperationType.ADDITION;
        BigDecimal firstNumber = BigDecimal.TEN;
        BigDecimal secondNumber = BigDecimal.TEN;
        Operation additionOp = new Operation(OperationType.ADDITION, BigDecimal.valueOf(1l));
        initiatorMock.when(() -> OperationInitiator.getByType(eq(OperationType.ADDITION)))
                .thenReturn(additionOp);
        when(strategyFactoryMock.findOperationStrategy(eq(OperationType.ADDITION)))
                .thenReturn(new AdditionOperationStrategy());
        when(recordServiceMock.getBalanceForUser(any())).thenReturn(BigDecimal.TEN);

        this.target.execute(operationType, firstNumber, secondNumber);

        verify(recordServiceMock).getBalanceForUser(any(User.class));
        verify(recordServiceMock).save(recordArgumentCaptor.capture());

        Record record = recordArgumentCaptor.getValue();

        assertNotNull("Result should not be null", record);
        assertEquals("Operations should be equal",record.getOperation(), additionOp);
        assertEquals("User balances should be equal",record.getUserBalance(), new BigDecimal(9l));
        assertEquals("Users should be equal", record.getUser(), mockedUser);
        assertEquals("Amounts/cost should be equal", record.getAmount(), BigDecimal.valueOf(1l));
        assertEquals("Operations should be equal", record.getResponse(), "20");
        assertNotNull("Date should not be null", record.getDate());
    }

    @Test
    public void givenUserWithNotEnoughBalance_shouldPersistRecordWithNotEnoughBalance() {
        OperationType operationType = OperationType.SUBTRACTION;
        BigDecimal firstNumber = BigDecimal.TEN;
        BigDecimal secondNumber = BigDecimal.TEN;
        Operation subtractionOp = new Operation(OperationType.SUBTRACTION, BigDecimal.valueOf(2l));
        initiatorMock.when(() -> OperationInitiator.getByType(eq(OperationType.SUBTRACTION)))
                .thenReturn(subtractionOp);
        when(strategyFactoryMock.findOperationStrategy(eq(OperationType.SUBTRACTION)))
                .thenReturn(new AdditionOperationStrategy());
        when(recordServiceMock.getBalanceForUser(any())).thenReturn(BigDecimal.ONE);

        this.target.execute(operationType, firstNumber, secondNumber);

        verify(recordServiceMock).getBalanceForUser(any(User.class));
        verify(recordServiceMock).save(recordArgumentCaptor.capture());

        Record record = recordArgumentCaptor.getValue();

        assertNotNull("Result should not be null", record);
        assertEquals("Operations should be equal",record.getOperation(), subtractionOp);
        assertEquals("User balances should be equal",record.getUserBalance(), BigDecimal.ONE);
        assertEquals("Users should be equal", record.getUser(), mockedUser);
        assertEquals("Amounts/cost should be equal", record.getAmount(), BigDecimal.ZERO);
        assertEquals("Operations should be equal", record.getResponse(), "Not enough balance");
        assertNotNull("Date should not be null", record.getDate());
    }
}
