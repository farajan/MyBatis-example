package com.example.mybatis.model.payload;

/**
 * This POJO holds information which is required for a car transfer request.
 */
public class TransferCar {

    private Long id_seller;

    private Long id_buyer;

    private Long id_car;

    public Long getId_seller() {
        return id_seller;
    }

    public void setId_seller(Long id_seller) {
        this.id_seller = id_seller;
    }

    public Long getId_buyer() {
        return id_buyer;
    }

    public void setId_buyer(Long id_buyer) {
        this.id_buyer = id_buyer;
    }

    public Long getId_car() {
        return id_car;
    }

    public void setId_car(Long id_car) {
        this.id_car = id_car;
    }
}
