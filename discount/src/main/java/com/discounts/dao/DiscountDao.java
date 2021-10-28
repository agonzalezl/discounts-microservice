package com.discounts.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.discounts.model.DiscountCode;

import java.util.List;

import org.springframework.stereotype.Repository;


@Repository
public class DiscountDao {
    
	@PersistenceContext
    private EntityManager em;
	
	public List<DiscountCode> findAll(){
        return em.createQuery("Select d from DiscountCode d", DiscountCode.class).getResultList();
    }

    public void add(List<DiscountCode> discountCodes){
        for(DiscountCode discount : discountCodes){
            em.persist(discount);
        }
        em.flush();
    }

    public void update(DiscountCode discountCode){
        em.merge(discountCode);
        em.flush();
    }

    public List<DiscountCode> findByBrandId(long brandId){
        return em.createQuery("Select d from DiscountCode d where d.brandId = :brandId", DiscountCode.class)
            .setParameter("brandId", brandId)
            .getResultList();
    }
    
    public List<DiscountCode> findAvailableByBrandId(long brandId){
        return em.createQuery("Select d from DiscountCode d where d.brandId = :brandId and userId is null", DiscountCode.class)
            .setParameter("brandId", brandId)
            .getResultList();
    }

    public List<DiscountCode> findByBrandIdAndUserId(long brandId, Long userId){
        return em.createQuery("Select d from DiscountCode d where d.brandId = :brandId and userId is :userId", DiscountCode.class)
            .setParameter("brandId", brandId)
            .setParameter("userId", userId)
            .getResultList();
    }
}
