package com.my;

import com.my.client.CourierClient;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class CourierCreationRequiredFieldsTest {
    private final String login;
    private final String password;
    private final String firstName;

    public CourierCreationRequiredFieldsTest(
            String login,
            String password,
            String firstName
    ) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][]{
                //Если нет одного обязательного поля
                {"pupa", "lupa", null},
                {"pupa", null, "gupa"},
                {null, "lupa", "gupa"},
                //Если нет двух обязательных полей
                {"pupa", null, null},
                {null, null, "gupa"},
                {null, "lupa", null},
                //Если нет никаких полей
                {null, null, null}
        };
    }


    @Test
    @DisplayName("Проверка на обязательность (всех) полей в теле запроса + проверка на корректный 400 статус код")
    public void checkRequiredFieldsCreateCourierRequestError() {
        Courier courier = new Courier(
                login,
                password,
                firstName
        );
        CourierClient courierClient = new CourierClient();
        Response response = courierClient.courierCreate(courier, 400);
        if (response.statusCode() == 201) {
            courierClient.removeCourier(courier);
        }
    }
}