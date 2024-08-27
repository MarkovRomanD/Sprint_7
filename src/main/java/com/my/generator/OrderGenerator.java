package com.my.generator;

import com.my.Order;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;

public class OrderGenerator {
    public static Order getRandom(String[] colors) {
        Random random = new Random();
        String randomString = RandomStringUtils.random(10, true, false);
        String randomDate = RandomStringUtils.random(4, false, true);
        Number randomNumber = random.nextInt(5) + 1;
        return new Order(
                randomString,
                randomString,
                randomString,
                randomString,
                randomString,
                randomNumber,
                randomDate,
                colors,
                randomString
        );
    }
}
