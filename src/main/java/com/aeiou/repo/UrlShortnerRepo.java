package com.aeiou.repo;

import org.springframework.data.repository.CrudRepository;

import com.aeiou.entity.UrlShortner;

// TODO: Auto-generated Javadoc
/**
 * The Interface UrlShortnerRepo.
 */
public interface UrlShortnerRepo extends CrudRepository<UrlShortner, String> {
	
	/**
	 * Find by short url.
	 *
	 * @param shortUrl the short url
	 * @return the url shortner
	 */
	UrlShortner findByShortUrl(String shortUrl);
}
