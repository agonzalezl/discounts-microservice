package com.discounts.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import com.discounts.dao.DiscountDao;
import com.discounts.exceptions.DiscountException;
import com.discounts.model.DiscountCode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiscountService{
 
    @Autowired
    DiscountDao dao; 

    public List<DiscountCode> getAllCodes() {
        return dao.findAll();
    }

    public List<DiscountCode> getCodes(long brandId) {
        return dao.findByBrandId(brandId);
    }

    @Transactional
    public void createCodes(long brandId, int amount) {
        List<DiscountCode> discounts  = new ArrayList<>();
        for(int i = 0; i<amount; i++){
            DiscountCode discount = new DiscountCode(brandId);
            discounts.add(discount);
        }
        dao.add(discounts);
    }

    @Transactional
    public DiscountCode associateCode(long brandId, long userId) throws DiscountException {
        if(dao.findByBrandIdAndUserId(brandId, userId).size()>0){
            throw new DiscountException("Discount code already issued to this user for this brand");
        }
        List<DiscountCode> availableDiscounts = dao.findAvailableByBrandId(brandId);
        if(availableDiscounts.size()==0){
            throw new DiscountException("No available discounts for this brand");
        }
        DiscountCode selectedCode = availableDiscounts.get(0);
        selectedCode.setUserId(userId);
        dao.update(selectedCode);
        return selectedCode;
    }

}