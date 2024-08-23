package com.my;

import com.my.client.OrdersClient;
import com.my.generator.OrderGenerator;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class OrderCreationColorTest {
    private final String[] colors;

    private OrdersClient ordersClient;

    public OrderCreationColorTest(String[] colors) {
        this.colors = colors;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][]{
                {new String[]{"BLACK"}},
                {new String[]{"GREY"}},
                {new String[]{"BLACK", "GREY"}},
                {null},
        };
    }

    @Before
    public void init() {
        ordersClient = new OrdersClient();
    }

    @Test
    @DisplayName("Создаем заказ с выбранным(и) цветом(ами) через статус код ответа")
    public void createOrderWithSelectColor() {
        ordersClient.orderCreate(OrderGenerator.getRandom(colors), 201);
    }
}