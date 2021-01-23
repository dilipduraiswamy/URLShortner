package com.aeiou.service;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.aeiou.dto.ShortnerRequest;
import com.aeiou.entity.UrlShortner;
import com.aeiou.entity.Users;
import com.aeiou.repo.UrlShortnerRepo;
import com.aeiou.repo.UsersRepository;
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

	@Autowired
	private UsersRepository usersRepository;

	/**
	 * Shorten URL.
	 *
	 * @param url            the url
	 * @param generateNewUrl the generate new url
	 * @return the string
	 */
	@Override
	public String shortenURL(ShortnerRequest shortnerRequest) {

		String shortenKey = generator.getAlphaNumericString(6);
		String finalShortUrl = shortUrl + shortenKey;
		UrlShortner urlShortnerDBData = urlShortnerRepo.findByShortUrl(shortnerRequest.getUrl());
		UrlShortner urlShortner = null;
		saveUsers(shortnerRequest);
		if (shortnerRequest.getUrl().contains(" ")) {
			shortnerRequest.setUrl(shortnerRequest.getUrl().replaceAll(" ", "%20"));
		}
		if (urlShortnerDBData == null) {
			urlShortner = new UrlShortner();
			urlShortner.setInsertedDateTime(new Date());
			urlShortner.setIsActive(true);
			urlShortner.setUrl(shortnerRequest.getUrl());
			urlShortner.setShortUrl(finalShortUrl);
			urlShortner.setClientIpAddress(shortnerRequest.getClientIpAddress());
			urlShortner.setCountToDownload(shortnerRequest.getCountToDownload());
			urlShortner.setEmailId(shortnerRequest.getEmailId());
			urlShortner.setNoOfTimesDownloaded(0);
			urlShortner.setSizeOfFile(shortnerRequest.getSizeOfFile());
		} else {

			urlShortner = urlShortnerDBData;
			urlShortner.setUpdatedDateTime(new Date());
			finalShortUrl = urlShortnerDBData.getShortUrl();
			// finalShortUrl = shortUrl + shortenURL(finalShortUrl, generateNewUrl);
		}
		urlShortnerRepo.save(urlShortner);
		return finalShortUrl;
	}

	@Async
	private void saveUsers(ShortnerRequest shortnerRequest) {
		if (shortnerRequest.getEmailId() != null && !shortnerRequest.getEmailId().isEmpty()) {
			Users users = usersRepository.findByUsername(shortnerRequest.getEmailId());
			if (users == null) {
				users = new Users();
				users.setUsername(shortnerRequest.getEmailId());
				users.setIsVerified(true);
				users.setInsertedDateTime(new Date());
				users.setIsEnabled(true);
				users.setTypeOfUser("CUSTOMER");
			}
			usersRepository.save(users);
		}
	}

}
