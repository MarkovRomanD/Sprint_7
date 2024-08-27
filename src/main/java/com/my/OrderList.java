package com.my;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderList {
    private Order[] orders;
    private PageInfo pageInfo;
    private Station[] availableStations;

}
