package com.infy.os.product.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.infy.os.product.dto.WishlistDTO;

@RestController
public class WishlistController {

	Logger logger = LoggerFactory.getLogger(this.getClass());

//	@Value("${wishlist.uri}")
//	String wishlistUri;
	
	@SuppressWarnings("unchecked")
	@GetMapping(value = "/product/wishlist/{buyerId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<WishlistDTO> viewWishlist(@PathVariable Long buyerId){
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.getForObject("http://localhost:8000/wishlist"+"/"+buyerId, List.class);
	}
	
	
	@SuppressWarnings("unchecked")
	@GetMapping(value = "/product/wishlist",produces = MediaType.APPLICATION_JSON_VALUE)
	public List<WishlistDTO> getWishlistDetails(){
		logger.info("Fetching all products from wishlist");
		RestTemplate restTemplate = new RestTemplate();
		List<WishlistDTO> wishlistDTOs = restTemplate.getForObject("http://localhost:8000/wishlist", List.class);
		return wishlistDTOs;
	}
	
	@PostMapping(value = "/product/wishlist",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addToWishlist(@RequestBody WishlistDTO wishlistDTO) {
		logger.info("Place order cart : {}", wishlistDTO);
		RestTemplate restTemplate = new RestTemplate();
		String message = restTemplate.postForObject("http://localhost:8000/wishlist", wishlistDTO, String.class);
		return new ResponseEntity<>(message, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/product/wishlist/{buyerId}/{prodId}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> removeFromWishlist(@PathVariable Long buyerId, @PathVariable Long prodId) {
		logger.info("Delete order from wishlist having buyerId - {}, prodId - {}", buyerId, prodId);
		RestTemplate restTemplate = new RestTemplate();
		WishlistDTO wishlistDTO = restTemplate.getForObject("http://localhost:8000/wishlist"+"/"+buyerId+"/"+prodId, WishlistDTO.class);
		if (wishlistDTO==null)
			return new ResponseEntity<>("Product not present in wishlist", HttpStatus.OK);
		restTemplate.delete("http://localhost:8000/wishlist"+"/"+buyerId+"/"+prodId);
		return new ResponseEntity<>("Product removed from wishlist", HttpStatus.OK);
	}
	
	@GetMapping(value = "/product/wishlist/{buyerId}/{prodId}",produces = MediaType.APPLICATION_JSON_VALUE)
	public WishlistDTO getSpecificProductFromWishlist(@PathVariable Long buyerId, @PathVariable Long prodId) {
		logger.info("Get order from wishlist having buyerId - {}, prodId - {}", buyerId, prodId);
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.getForObject("http://localhost:8000/wishlist"+"/"+buyerId+"/"+prodId, WishlistDTO.class);
	}
	
	
}
