package com.my.paramTests;

import com.my.Courier;
import com.my.client.CourierClient;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class CourierLoginRequiredFieldsTest {
    private final String login;
    private final String password;

    private final Courier courier;
    private final CourierClient courierClient;

    public CourierLoginRequiredFieldsTest(
            String login,
            String password) {
        this.login = login;
        this.password = password;
        courierClient = new CourierClient();
        courier = new Courier("pupa", "Lupa", "gupa");
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][]{
                {"pupa", null},
                {null, "Lupa"},
                {null, null},
        };
    }


    @After
    public void removeCourier() {
        courierClient.removeCourier(courier);
    }


    @Test
    @DisplayName("Проверка на обязательность (всех) полей в теле запроса + проверка на корректный 400 статус код")
    public void checkRequiredFieldsLoginCourierRequestError() {
        courierClient.courierCreate(courier, 201);
        courierClient.courierLogin(new Courier(login, password), 400);
    }
}