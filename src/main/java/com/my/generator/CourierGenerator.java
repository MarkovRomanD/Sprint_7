package com.my.generator;

import com.my.Courier;
import org.apache.commons.lang3.RandomStringUtils;

public class CourierGenerator {

    public static Courier getRandom() {

        String randomData = RandomStringUtils.random(10, true, false);
        return new Courier(
                randomData,
                randomData,
                randomData
        );
    }
}
