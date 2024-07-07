package com.verramobility.discountcalculator.constants;

public class RequestData {
    public static final String INPUT_SUCCESS = "4" + System.lineSeparator()
            + "4 cloth at 100.50" + System.lineSeparator()
            + "2 book at 200.75" + System.lineSeparator()
            + "1 clearance food at 10.00" + System.lineSeparator()
            + "1 drink at 20.00";
    public static final String INPUT_ITEM_INVALID = "0" + System.lineSeparator()
            + "4 cloth at 100.50" + System.lineSeparator()
            + "2 book at 200.75";
    public static final String INPUT_ITEM_INVALID_FORMAT = "2" + System.lineSeparator()
            + "4 cloth 100.50" + System.lineSeparator()
            + "2 book 200.75";
    public static final String INPUT_ITEM_EXCEPTION = "Item" + System.lineSeparator()
            + "4 cloth 100.50" + System.lineSeparator()
            + "2 book 200.75";

    private RequestData() {
    }
}