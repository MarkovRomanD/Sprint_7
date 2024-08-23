package com.my;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Courier {

    private Integer id;
    private String login;
    private String password;
    private String firstName;

    public Courier(String login, String password) {
        this.id = null;
        this.login = login;
        this.password = password;
        this.firstName = null;
    }

    public Courier(String login, String password, String firstName) {
        this.id = null;
        this.login = login;
        this.password = password;
        this.firstName = firstName;
    }


}
