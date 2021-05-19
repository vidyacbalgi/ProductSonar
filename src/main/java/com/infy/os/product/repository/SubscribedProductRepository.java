package com.infy.os.product.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infy.os.product.entity.SubscribedProduct;

public interface SubscribedProductRepository extends JpaRepository<SubscribedProduct, Long> {

	List<SubscribedProduct> findByBuyerId(Long buyerId);

	Optional<SubscribedProduct> findByBuyerIdAndProdId(Long buyerId, Long prodId);

}
