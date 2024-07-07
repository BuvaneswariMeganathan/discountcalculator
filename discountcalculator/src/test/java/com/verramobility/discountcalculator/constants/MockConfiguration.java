package com.verramobility.discountcalculator.constants;

import com.verramobility.discountcalculator.utils.ConfigPropertiesUtils;

import java.util.Map;
import java.util.Set;

import static org.mockito.Mockito.when;

public class MockConfiguration {
    public void mockData(ConfigPropertiesUtils configPropertiesUtils) {
        Map<String, Integer> discountMap = Map.of(
                "book", 5,
                "food", 5,
                "drink", 15,
                "cloth", 20,
                "default", 5
        );

        when(configPropertiesUtils.getItems()).thenReturn(discountMap);
        when(configPropertiesUtils.getBook()).thenReturn(Set.of("book"));
        when(configPropertiesUtils.getFood()).thenReturn(Set.of("food"));
        when(configPropertiesUtils.getDrink()).thenReturn(Set.of("drink"));
        when(configPropertiesUtils.getCloth()).thenReturn(Set.of("cloth"));
        when(configPropertiesUtils.getClearance()).thenReturn(10);
    }
}
