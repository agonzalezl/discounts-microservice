package com.discounts.controller;

import com.discounts.exceptions.DiscountException;
import com.discounts.service.DiscountService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DiscountController {

    @Autowired
    DiscountService service;

    @RequestMapping(value = "/codes" , method = RequestMethod.GET)
    public ResponseEntity<?> getAllCodes() {

        try {
            String jsonResponse = new ObjectMapper().writeValueAsString(service.getAllCodes());
            return ResponseEntity.ok(jsonResponse);
        } catch (JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @RequestMapping(value = "/{brandId}/codes" , method = RequestMethod.GET)
    public ResponseEntity<?> getCodes(@PathVariable long brandId) {
        try {
            String jsonResponse = new ObjectMapper().writeValueAsString(service.getAllCodes());
            return ResponseEntity.ok(jsonResponse);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @RequestMapping(value = "/{brandId}/codes" , method = RequestMethod.POST)
    public ResponseEntity<?>  createCodes(@PathVariable long brandId, @RequestParam int amount) {
        service.createCodes(brandId, amount);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/{brandId}/codes" , method = RequestMethod.PUT)
    public ResponseEntity<?>  associateCode(@PathVariable long brandId, @RequestHeader("userId") long userId) {
        
        try {
            String jsonResponse = new ObjectMapper().writeValueAsString(service.associateCode(brandId, userId));
            return ResponseEntity.ok(jsonResponse);
        } catch (JsonProcessingException ejson) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (DiscountException e) {
            return ResponseEntity.ok(e.getMessage());
        }
        
    }


}
