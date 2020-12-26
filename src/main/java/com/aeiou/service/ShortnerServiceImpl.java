package com.aeiou.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.aeiou.entity.UrlShortner;
import com.aeiou.repo.UrlShortnerRepo;
import com.aeiou.util.Generator;

// TODO: Auto-generated Javadoc
/**
 * The Class ShortnerServiceImpl.
 */
@Service
public class ShortnerServiceImpl implements ShortnerService {

	/** The url shortner repo. */
	@Autowired
	private UrlShortnerRepo urlShortnerRepo;

	/** The generator. */
	@Autowired
	private Generator generator;

	/** The short url. */
	@Value("${short.url}")
	private String shortUrl;

	/**
	 * Shorten URL.
	 *
	 * @param url the url
	 * @param generateNewUrl the generate new url
	 * @return the string
	 */
	@Override
	public String shortenURL(String url, Boolean generateNewUrl) {

		String shortenKey = generator.getAlphaNumericString(6);
		String finalShortUrl = shortUrl + shortenKey;
		UrlShortner urlShortnerDBData = urlShortnerRepo.findByShortUrl(url);
		UrlShortner urlShortner = null;
		if (urlShortnerDBData == null) {
			urlShortner = new UrlShortner();
			urlShortner.setInsertedDateTime(new Date());
			urlShortner.setIsActive(true);
			urlShortner.setUrl(url);
			urlShortner.setShortUrl(finalShortUrl);

		} else {

			urlShortner = urlShortnerDBData;
			urlShortner.setUpdatedDateTime(new Date());
			finalShortUrl = urlShortnerDBData.getShortUrl();
			// finalShortUrl = shortUrl + shortenURL(finalShortUrl, generateNewUrl);
		}
		urlShortnerRepo.save(urlShortner);
		return finalShortUrl;
	}

}
