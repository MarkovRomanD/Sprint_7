package com.my;

import com.my.client.OrdersClient;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

public class OrderListGettingTest {

    private OrdersClient ordersClient;

    @Before
    public void init() {
        ordersClient = new OrdersClient();
    }

    @Test
    @DisplayName("Проверка, что запрос списка заказов возвращает статус код 200 + проверка тела ответа")
    public void successRequestGetOKStatusCode() {
        Response response = ordersClient.orderListGet(200);
        ordersClient.orderListResponseDataMatch(response);
    }
}