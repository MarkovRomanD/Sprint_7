package com.my;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class Order {
    private Integer id;
    private Integer courierId;
    private String firstName;
    private String lastName;
    private String address;
    private String metroStation;
    private String phone;
    private Number rentTime;
    private String deliveryDate;
    private Integer track;
    private String[] color;
    private String comment;
    private String createdAt;
    private String updatedAt;
    private Integer status;

    public Order(String firstName,
                 String lastName,
                 String address,
                 String metroStation,
                 String phone,
                 Number rentTime,
                 String deliveryDate,
                 String[] color,
                 String comment) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.color = color;
        this.comment = comment;
        this.id = null;
        this.courierId = null;
        this.track = null;
        this.createdAt = null;
        this.updatedAt = null;
        this.status = null;
    }

    public Order(String firstName,
                 String lastName,
                 String address,
                 String metroStation,
                 String phone,
                 Number rentTime,
                 String deliveryDate,
                 Integer track,
                 String[] color,
                 String comment) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.color = color;
        this.comment = comment;
        this.id = null;
        this.courierId = null;
        this.track = track;
        this.createdAt = null;
        this.updatedAt = null;
        this.status = null;
    }


}
