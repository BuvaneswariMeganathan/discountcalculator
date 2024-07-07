package com.verramobility.discountcalculator.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Configuration
@Data
@ConfigurationProperties(prefix = "bill")
public class ConfigPropertiesUtils {
    private Map<String, Integer> items = new HashMap<>();
    private Integer clearance;
    private Set<String> book = new HashSet<>();
    private Set<String> food = new HashSet<>();
    private Set<String> drink = new HashSet<>();
    private Set<String> cloth = new HashSet<>();
}
