package com.verramobility.discountcalculator.controller;

import com.verramobility.discountcalculator.service.DiscountCalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

/**
 * DiscountCalculatorController used to calculate the discount and generate the receipt for the respective items
 * implements CommandLineRunner for invoking receipt generation on application startup
 */
@Controller
public class DiscountCalculatorController implements CommandLineRunner {

    private final DiscountCalculatorService discountCalculatorService;

    /**
     * Constructor for DiscountCalculatorController.
     *
     * @param discountCalculatorService the service which handles the discount calculation and receipt generation logic.
     */
    @Autowired
    public DiscountCalculatorController(DiscountCalculatorService discountCalculatorService) {
        this.discountCalculatorService = discountCalculatorService;
    }

    /**
     * run method is invoked on application startup and calling the generateReceipt method from the service
     *
     * @param args command line arguments
     * @throws Exception if there is an exception during receipt generation.
     */
    @Override
    public void run(String... args) throws Exception {
        discountCalculatorService.generateReceipt();
    }
}
