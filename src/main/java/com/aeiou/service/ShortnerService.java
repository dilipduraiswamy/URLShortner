package com.aeiou.service;

// TODO: Auto-generated Javadoc
/**
 * The Interface ShortnerService.
 */
public interface ShortnerService {

	/**
	 * Shorten URL.
	 *
	 * @param url the url
	 * @param generateNewUrl the generate new url
	 * @return the string
	 */
	String shortenURL(String url,Boolean generateNewUrl);
}
