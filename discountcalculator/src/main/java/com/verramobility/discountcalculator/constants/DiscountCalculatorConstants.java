package com.verramobility.discountcalculator.constants;

public class DiscountCalculatorConstants {
    public static final String ENTER_QUANTITY = "Enter the quantity of items to be purchased: ";
    public static final String INVALID_INPUT = "Invalid input";
    public static final String INVALID_NUMBER_OF_ITEM = "Number of items must be greater than zero";
    public static final String ENTER_LIST_OF_ITEMS = "Enter the list of items to be purchased: ";
    public static final String INVALID_ITEM_FORMAT = "Invalid item format: ";
    public static final String ERROR_MESSAGE = "Error while processing input";
    public static final String RECEIPT_GENERATION_ABORTED = "Receipt generation aborted";
    public static final String VALID_ITEM_FORMAT = "\\d+\\s+.+\\s+at\\s+\\d+(\\.\\d{2})?";
    public static final String IS_CLEARANCE = "clearance";
    public static final String RECEIPT_MESSAGE = "Receipt:";
    public static final String ITEM_DETAILS_FORMAT = "%d %s at %.2f%n";
    public static final String TOTAL_COST = "Total: %.2f%n";
    public static final String TOTAL_SAVED = "You saved: %.2f%n";
    public static final CharSequence EMPTY_VALUE = "";
    public static final String CLEARANCE_REMOVAL = "clearance ";
    public static final String BOOK = "book";
    public static final String FOOD = "food";
    public static final String DRINK = "drink";
    public static final String CLOTH = "cloth";
    public static final String DEFAULT = "default";
    public static final double HUNDRED_VALUE = 100.0;
    public static final String AT_VALUE = " at ";
    public static final String SPACE_VALUE = " ";

    private DiscountCalculatorConstants() {
    }
}