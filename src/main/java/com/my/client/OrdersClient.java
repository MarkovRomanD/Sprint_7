package com.my.client;

import com.my.Order;
import com.my.OrderList;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.junit.Assert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.is;

public class OrdersClient {
    private static final String ORDERS_PATH = "/api/v1/orders";
    private static final String BASE_URI = "https://qa-scooter.praktikum-services.ru";

    @Step("Получение списка заказов")
    public Response orderListGet(int expectedStatusCode) {
        return given()
                .baseUri(BASE_URI)
                .get(ORDERS_PATH)
                .then()
                .assertThat()
                .statusCode(expectedStatusCode)
                .extract()
                .response();
    }

    @Step("Создание заказа")
    public Response orderCreate(Order order, int expectedStatusCode) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(order)
                .when()
                .baseUri(BASE_URI)
                .post(ORDERS_PATH)
                .then()
                .assertThat()
                .statusCode(expectedStatusCode)
                .extract()
                .response();
    }

    @Step("Проверка, что тело ответа содержит track")
    public void orderCreateResponseDataMatch(Response sendPostRequestOrderResponse) {
        sendPostRequestOrderResponse.then()
                .body("$", hasKey("track"))
                .and()
                .body("size()", is(1));
    }


    @Step("Мэтчим данные. Можно ли тело ответа спарсить в список заказов")
    public void orderListResponseDataMatch(Response response) {
        try {
            response.body().as(OrderList.class);
            Assert.assertTrue(true);
        } catch (Exception ex) {
            Assert.fail("Не удалось спарсить данные, текст ошибки: \n" + ex.getMessage());
        }
    }

}
