package com.discounts.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.RandomStringUtils;


@Entity
@Table
public class DiscountCode {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column
    private String code;

    @Column
    private Long userId;

    @Column
    private long brandId;

    public DiscountCode(){}

    public DiscountCode(long brandId){
        this.brandId = brandId;
        this.code = RandomStringUtils.randomAlphabetic(10);
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public long getBrandId() {
        return this.brandId;
    }

    public void setBrandId(long brandId) {
        this.brandId = brandId;
    }


}
