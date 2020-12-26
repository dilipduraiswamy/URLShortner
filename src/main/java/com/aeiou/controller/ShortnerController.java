package com.aeiou.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aeiou.dto.ShortnerRequest;
import com.aeiou.dto.ShortnerResponse;
import com.aeiou.service.ShortnerService;

// TODO: Auto-generated Javadoc
/**
 * The Class ShortnerController.
 */
@RestController
@RequestMapping("/v1")
public class ShortnerController {

	/** The shortner service. */
	@Autowired
	private ShortnerService shortnerService;

	/**
	 * Short url.
	 *
	 * @param shortnerRequest the shortner request
	 * @return the response entity
	 */
	@PostMapping(path = "/shortUrl", consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ShortnerResponse> shortUrl(@RequestBody final ShortnerRequest shortnerRequest) {
		String shortUrl = shortnerService.shortenURL(shortnerRequest.getUrl(), shortnerRequest.getGenerateNewUrl());
		ShortnerResponse shortnerResponse = new ShortnerResponse();
		shortnerResponse.setShortUrl(shortUrl);
		shortnerResponse.setMessage("Success");
		shortnerResponse.setStatus(200);
		return new ResponseEntity<>(shortnerResponse, HttpStatus.ACCEPTED);
	}
}
