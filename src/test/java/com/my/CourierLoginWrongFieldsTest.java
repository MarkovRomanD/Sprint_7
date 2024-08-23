package com.my;

import com.my.client.CourierClient;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class CourierLoginWrongFieldsTest {
    private final String login;
    private final String password;

    private final CourierClient courierClient;
    private final Courier courier;

    public CourierLoginWrongFieldsTest(
            String login,
            String password) {
        this.login = login;
        this.password = password;
        courierClient = new CourierClient();
        courier = new Courier("Pupa", "Lupa", "Gupa");
    }


    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][]{
                {"Pupa", "Lupa"},
                {"Gupa", "Mupa"}
        };
    }

    @After
    public void removeCourier() {
        courierClient.removeCourier(courier);
    }

    @Test
    @DisplayName("Проверка на корректный 404 статус код и некорректные поля при логине в системе")
    public void checkRequiredFieldsLoginCourierRequestError() {
        courierClient.courierCreate(courier, 201);
        courierClient.courierLogin(new Courier(login, password), 404);
    }
}