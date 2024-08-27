package com.my.client;

import com.my.Courier;
import com.my.ResponseErrorBody;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class CourierClient {

    private static final String COURIER_PATH = "/api/v1/courier";
    private static final String COURIER_LOGIN_PATH = "/api/v1/courier/login";
    private static final String BASE_URI = "https://qa-scooter.praktikum-services.ru";

    @Step("Создание курьера")
    public Response courierCreate(Courier courier, int expectedStatusCode) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(courier)
                .when()
                .baseUri(BASE_URI)
                .post(COURIER_PATH)
                .then()
                .assertThat()
                .statusCode(expectedStatusCode)
                .extract()
                .response();
    }

    @Step("Удаление курьера")
    public void courierRemove(String id) {
        given()
                .baseUri(BASE_URI)
                .delete(COURIER_PATH + "/" + id)
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Step("Авторизация курьера в системе")
    public Response courierLogin(Courier courier, int expectedStatusCode) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(courier)
                .when()
                .baseUri(BASE_URI)
                .post(COURIER_LOGIN_PATH)
                .then()
                .assertThat()
                .statusCode(expectedStatusCode)
                .extract()
                .response();
    }

    @Step("Удаление курьера по id")
    public void removeCourier(Courier courier) {
        Response response = courierLogin(courier, 200);
        courier.setId(response.body().path("id"));
        courierRemove(String.valueOf(courier.getId()));
    }

    @Step("Проверка успешного тела ответа запроса создания курьера")
    public void compareResponseBodyStatusCodeCreated(Response response) {
        response.then()
                .body("$", hasEntry("ok", true))
                .and()
                .body("size()", is(1));
    }

    @Step("Проверка наличия возвращения id при успешном логине курьера")
    public void compareResponseBodySuccessLogin(Response response) {
        response.then()
                .body("$", hasKey("id"))
                .and()
                .body("size()", is(1));
    }

    @Step("Проверка на одинаковые тела ответов для ошибок, состоящих из одного поля message")
    public void compareResponseBodyError(Response response, ResponseErrorBody expected) {
        response.then()
                .body("$", hasEntry("message", expected.getMessage()))
                .and()
                .body("size()", is(1));
    }
}
