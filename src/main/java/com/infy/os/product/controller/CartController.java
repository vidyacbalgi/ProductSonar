package com.infy.os.product.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.infy.os.product.dto.CartDTO;
import com.infy.os.product.dto.WishlistDTO;

@RestController
public class CartController {

//	@Autowired
//	RestTemplate restTemplate;
	
	Logger logger = LoggerFactory.getLogger(this.getClass());

//	@Value("${wishlist.uri}")
//	String wishlistUri;
//	
//	@Value("${cart.uri}")
//	String cartUri;
	
	@PostMapping(value = "/product/cart/addFromWishlist")
	public ResponseEntity<String> addToCartFromWishlist(@RequestBody CartDTO cartDTO) {
		logger.info("Add from wishlist to cart");
		RestTemplate restTemplate = new RestTemplate();
		WishlistDTO wishlistDTO = restTemplate.getForObject("http://localhost:8000"+"/wishlist/"+cartDTO.getBuyerId()+"/"+cartDTO.getProdId(), WishlistDTO.class);
		if (wishlistDTO==null)
			return new ResponseEntity<>("No such product in Wishlist", HttpStatus.OK);
		String message = restTemplate.postForObject("http://localhost:8000"+"/cart/add", cartDTO, String.class);
		return new ResponseEntity<>(message, HttpStatus.OK);	
		}

}
