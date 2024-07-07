package com.verramobility.discountcalculator.service.impl;

import com.verramobility.discountcalculator.constants.DiscountCalculatorConstants;
import com.verramobility.discountcalculator.dto.request.Item;
import com.verramobility.discountcalculator.service.DiscountCalculatorService;
import com.verramobility.discountcalculator.utils.ConfigPropertiesUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Scanner;

/**
 * Implementation of DiscountCalculatorService which handles the business logic
 * for generating receipts with discounted price for each item, total cost and savings
 */
@Service
public class DiscountCalculatorServiceImpl implements DiscountCalculatorService {
    private final ConfigPropertiesUtils configPropertiesUtils;

    /**
     * Constructor for DiscountCalculatorServiceImpl.
     *
     * @param configPropertiesUtils for accessing configuration properties
     */
    @Autowired
    public DiscountCalculatorServiceImpl(ConfigPropertiesUtils configPropertiesUtils) {
        this.configPropertiesUtils = configPropertiesUtils;
    }

    /**
     * generateReceipt method used to generate the receipt with discounted price for item, total cost and savings
     */
    @Override
    public void generateReceipt() {
        try (Scanner scanner = new Scanner(System.in)) {
            // retrieving item details from console
            Item[] items = retrieveItemsFromScanner(scanner);
            if (items.length > 0) {
                // Print the generated receipt
                printReceipt(items);
            } else {
                // aborted receipt generation
                System.err.println(DiscountCalculatorConstants.RECEIPT_GENERATION_ABORTED);
            }
        } catch (Exception e) {
            System.err.println(DiscountCalculatorConstants.ERROR_MESSAGE);
        }
    }

    /**
     * retrieving items form command line using Scanner
     *
     * @param scanner for reading input
     * @return Item[]
     */
    private Item[] retrieveItemsFromScanner(Scanner scanner) {
        // retrieving the number of items
        System.out.println(DiscountCalculatorConstants.ENTER_QUANTITY);
        if (!scanner.hasNextInt()) {
            System.err.println(DiscountCalculatorConstants.INVALID_INPUT);
            return new Item[0];
        }
        int numOfItems = scanner.nextInt();
        scanner.nextLine();
        if (numOfItems <= 0) {
            System.err.println(DiscountCalculatorConstants.INVALID_NUMBER_OF_ITEM);
            return new Item[0];
        }
        // retrieving the list of items
        System.out.println(DiscountCalculatorConstants.ENTER_LIST_OF_ITEMS);
        Item[] items = new Item[numOfItems];
        for (int i = 0; i < numOfItems; i++) {
            String itemDescription = scanner.nextLine();
            // verifying the given format is valid or not
            if (!isValidItemFormat(itemDescription)) {
                System.err.println(DiscountCalculatorConstants.INVALID_ITEM_FORMAT + itemDescription);
                return new Item[0];
            }
            // parsing itemDescription into the required format
            items[i] = parseItem(itemDescription);
        }
        return items;
    }

    /**
     * isValidItemFormat validates the request format
     *
     * @param itemDescription command line argument
     * @return boolean
     */
    private boolean isValidItemFormat(String itemDescription) {
        return itemDescription.matches(DiscountCalculatorConstants.VALID_ITEM_FORMAT);
    }


    /**
     * parseItem method used to parse the request
     *
     * @param itemDescription command line argument
     * @return Item
     */
    private Item parseItem(String itemDescription) {
        // parsing the itemDescription and converting into required format
        String[] itemDetails = itemDescription.split(DiscountCalculatorConstants.AT_VALUE);
        String[] countAndName = itemDetails[0].split(DiscountCalculatorConstants.SPACE_VALUE, 2);
        int count = Integer.parseInt(countAndName[0]);
        String name = countAndName[1];
        double price = Double.parseDouble(itemDetails[1]);
        boolean isClearance = name.contains(DiscountCalculatorConstants.IS_CLEARANCE);
        // returning Item with the corresponding data
        return new Item(count, name, price, isClearance);
    }


    /**
     * printReceipt will print the receipt
     *
     * @param items request data
     */
    private void printReceipt(Item[] items) {
        double totalCost = 0.0;
        double totalSaved = 0.0;
        System.out.println(DiscountCalculatorConstants.RECEIPT_MESSAGE);
        for (Item item : items) {
            // calculating discount
            double discountedPrice = calculateDiscount(item);
            totalCost += discountedPrice;
            // calculating savings
            double savings = item.getPrice() - discountedPrice;
            totalSaved += savings;
            // printing each item with the discounted price
            System.out.printf(DiscountCalculatorConstants.ITEM_DETAILS_FORMAT, item.getCount(), item.getName(), discountedPrice);
        }
        // printing total cost and savings
        System.out.printf(DiscountCalculatorConstants.TOTAL_COST, totalCost);
        System.out.printf(DiscountCalculatorConstants.TOTAL_SAVED, totalSaved);
    }

    /**
     * calculateDiscount calculates the discounted price
     *
     * @param item request data
     * @return double
     */
    private double calculateDiscount(Item item) {
        // calculating the discounted price
        double discount = (DiscountCalculatorConstants.HUNDRED_VALUE - getDiscount(item)) / DiscountCalculatorConstants.HUNDRED_VALUE;
        if (item.isClearance()) {
            double clearanceDiscount = (DiscountCalculatorConstants.HUNDRED_VALUE - configPropertiesUtils.getClearance()) / DiscountCalculatorConstants.HUNDRED_VALUE;
            discount *= clearanceDiscount;
        }
        double discountedPrice = item.getPrice() * discount;
        // returning the discounted price by rounding to the nearest cent (0.01)
        return Math.round(discountedPrice * DiscountCalculatorConstants.HUNDRED_VALUE) / DiscountCalculatorConstants.HUNDRED_VALUE;
    }

    /**
     * getDiscount used to get the discount for the particular item
     *
     * @param item request data
     * @return int
     */
    private int getDiscount(Item item) {
        // fetching the discount percentage from config properties
        Map<String, Integer> discountMap = configPropertiesUtils.getItems();
        String itemName = item.getName().replace(DiscountCalculatorConstants.CLEARANCE_REMOVAL, DiscountCalculatorConstants.EMPTY_VALUE).toLowerCase().trim();
        // assigning the item name depends on the item type
        if (configPropertiesUtils.getBook().contains(itemName)) {
            itemName = DiscountCalculatorConstants.BOOK;
        } else if (configPropertiesUtils.getFood().contains(itemName)) {
            itemName = DiscountCalculatorConstants.FOOD;
        } else if (configPropertiesUtils.getDrink().contains(itemName)) {
            itemName = DiscountCalculatorConstants.DRINK;
        } else if (configPropertiesUtils.getCloth().contains(itemName)) {
            itemName = DiscountCalculatorConstants.CLOTH;
        }
        // returning the respective discount percentage
        return discountMap.getOrDefault(itemName, discountMap.get(DiscountCalculatorConstants.DEFAULT));
    }
}
