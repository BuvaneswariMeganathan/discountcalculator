package com.verramobility.discountcalculator.service;

import com.verramobility.discountcalculator.constants.MockConfiguration;
import com.verramobility.discountcalculator.constants.RequestData;
import com.verramobility.discountcalculator.constants.ResponseData;
import com.verramobility.discountcalculator.service.impl.DiscountCalculatorServiceImpl;
import com.verramobility.discountcalculator.utils.ConfigPropertiesUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DiscountCalculatorServiceTest {

    @Mock
    private ConfigPropertiesUtils configPropertiesUtils;

    @InjectMocks
    private DiscountCalculatorServiceImpl discountCalculatorService;

    private final InputStream savedSystemIn = System.in;
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    @BeforeEach
    public void redirectSystemIn() {
        MockitoAnnotations.openMocks(this);
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    public void restoreSystemIn() {
        System.setIn(savedSystemIn);
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    void generateReceiptSuccess() {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(RequestData.INPUT_SUCCESS.getBytes());
        System.setIn(inputStream);
        new MockConfiguration().mockData(configPropertiesUtils);
        discountCalculatorService.generateReceipt();
        Assertions.assertEquals(ResponseData.INPUT_SUCCESS, outContent.toString());
    }

    @Test
    void generateReceiptInvalidInputItem() {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(RequestData.INPUT_ITEM_INVALID.getBytes());
        System.setIn(inputStream);
        new MockConfiguration().mockData(configPropertiesUtils);
        discountCalculatorService.generateReceipt();
        assertEquals(ResponseData.INPUT_ITEM_INVALID, errContent.toString());
    }


    @Test
    void generateReceiptInvalidInputItemFormat() {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(RequestData.INPUT_ITEM_INVALID_FORMAT.getBytes());
        System.setIn(inputStream);
        new MockConfiguration().mockData(configPropertiesUtils);
        discountCalculatorService.generateReceipt();
        assertEquals(ResponseData.INPUT_ITEM_INVALID_FORMAT, errContent.toString());
    }

    @Test
    void generateReceiptInvalidInputNumberOfItems() {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(RequestData.INPUT_ITEM_EXCEPTION.getBytes());
        System.setIn(inputStream);
        new MockConfiguration().mockData(configPropertiesUtils);
        discountCalculatorService.generateReceipt();
        assertEquals(ResponseData.INPUT_ITEM_EXCEPTION, errContent.toString());
    }
}

