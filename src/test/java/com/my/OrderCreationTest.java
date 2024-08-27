package com.my;

import com.my.client.OrdersClient;
import com.my.generator.OrderGenerator;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

public class OrderCreationTest {


    private OrdersClient ordersClient;

    @Before
    public void init() {
        ordersClient = new OrdersClient();
    }

    @Test
    @DisplayName("Проверка на корректный статус код при создании заказа + тело ответа track")
    public void createOrderRequestIsSuccessAndResponseStatusCodeIsCreated() {
        Response response = ordersClient
                .orderCreate(
                        OrderGenerator.getRandom(new String[]{"BLACK", "GREY"}),
                        201
                );
        ordersClient.orderCreateResponseDataMatch(response);
    }
}