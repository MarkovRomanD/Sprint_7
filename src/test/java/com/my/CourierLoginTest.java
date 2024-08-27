package com.my;

import com.my.client.CourierClient;
import com.my.generator.CourierGenerator;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

public class CourierLoginTest {

    private CourierClient courierClient;
    private Courier courier;

    @Before
    public void init() {
        courierClient = new CourierClient();
        courier = CourierGenerator.getRandom();
    }

    @Test
    @DisplayName("Успешный логин курьера в системе - проверка статус кода")
    public void courierSuccessLoginResponseStatusCodeAndBodyIsCorrect() {
        courierClient.courierCreate(courier, 201);
        Response response = courierClient.courierLogin(courier, 200);
        courierClient.compareResponseBodySuccessLogin(response);
        courierClient.removeCourier(courier);
    }

    @Test
    @DisplayName("Проверка тела ответа при 400 ошибке")
    public void checkResponseBodyErrorBadRequest() {
        courierClient.courierCreate(courier, 201);
        Response response = courierClient.courierLogin(
                new Courier(null, courier.getPassword()),
                400);
        courierClient.compareResponseBodyError(
                response,
                new ResponseErrorBody("Недостаточно данных для входа")
        );
        courierClient.removeCourier(courier);
    }

    @Test
    @DisplayName("Проверка тела ответа при статус коде 404")
    public void checkLoginCourierResponseBodyErrorNotFound() {
        Response response = courierClient.courierLogin(courier, 404);
        courierClient.compareResponseBodyError(response, new ResponseErrorBody("Учетная запись не найдена"));
    }
}