package com.verramobility.discountcalculator.constants;

public class ResponseData {
    public static final String INPUT_SUCCESS = "Enter the quantity of items to be purchased: " + System.lineSeparator()
            + "Enter the list of items to be purchased: " + System.lineSeparator()
            + "Receipt:" + System.lineSeparator()
            + "4 cloth at 80.40" + System.lineSeparator()
            + "2 book at 190.71" + System.lineSeparator()
            + "1 clearance food at 8.55" + System.lineSeparator()
            + "1 drink at 17.00" + System.lineSeparator()
            + "Total: 296.66" + System.lineSeparator()
            + "You saved: 34.59" + System.lineSeparator();
    public static final String INPUT_ITEM_INVALID = "Number of items must be greater than zero" + System.lineSeparator()
            + "Receipt generation aborted" + System.lineSeparator();
    public static final String INPUT_ITEM_INVALID_FORMAT = "Invalid item format: 4 cloth 100.50" + System.lineSeparator()
            + "Receipt generation aborted" + System.lineSeparator();
    public static final String INPUT_ITEM_EXCEPTION = "Invalid input" + System.lineSeparator()
            + "Receipt generation aborted" + System.lineSeparator();

    private ResponseData() {
    }
}