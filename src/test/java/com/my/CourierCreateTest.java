package com.my;

import com.my.client.CourierClient;
import com.my.generator.CourierGenerator;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CourierCreateTest {

    private CourierClient courierClient;
    private Courier courier;

    @Before
    public void init() {
        courierClient = new CourierClient();
        courier = CourierGenerator.getRandom();
    }

    @After
    public void removeCourier() {
        courierClient.removeCourier(courier);
    }

    @Test
    @DisplayName("Проверка тела ответа успешного запроса создания курьера")
    public void successCreateCourierRequestBodyIsCorrect() {
        Response response = courierClient.courierCreate(courier, 201);
        courierClient.compareResponseBodyStatusCodeCreated(response); // вывел отдельным степом для наглядности
    }

    @Test
    @DisplayName("Нельзя создать двух одинаковых курьеров")
    public void cannotCreateDuplicateCourier() {
        courierClient.courierCreate(courier, 201); //создали первого
        courierClient.courierCreate(courier, 409); //попытка создать дубликат
    }

    @Test
    @DisplayName("Нельзя создать двух курьеров с одинаковыми логинами")
    public void cannotCreateDuplicateCourierLogin() {
        courierClient.courierCreate(courier, 201); //создали первого
        courierClient.courierCreate(new Courier(
                        courier.getLogin(),
                        "notSamePassword",
                        "notSameFirstName"),
                409
        );
    }

    @Test
    @DisplayName("Проверка тела ответа запроса создания курьера с ошибкой 409")
    public void notSuccessCreateCourierResponseBodySameLogin() {
        courierClient.courierCreate(courier, 201); //создали первого
        Response response = courierClient.courierCreate(courier, 409); //вызываем 409 ошибку
        courierClient.compareResponseBodyError(
                response,
                new ResponseErrorBody("Этот логин уже используется. Попробуйте другой."));
    }


    @Test
    @DisplayName("Проверка тела ответа при статус коде 400 + корректность статус кода")
    public void checkCreateCourierResponseBodyErrorBadRequest() {
        Response response = courierClient.courierCreate(new Courier(), 400);
        courierClient.compareResponseBodyError(
                response,
                new ResponseErrorBody("Недостаточно данных для создания учетной записи")
        );
    }
}