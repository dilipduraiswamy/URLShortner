package com.aeiou.service;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import com.aeiou.entity.UrlShortner;
import com.aeiou.repo.UrlShortnerRepo;
import com.aeiou.util.UrlSpliter;

// TODO: Auto-generated Javadoc
/**
 * The Class FileRetriverServiceImpl.
 */
@Service
public class FileRetriverServiceImpl implements FileRetriverService {

	/** The url shortner repo. */
	@Autowired
	private UrlShortnerRepo urlShortnerRepo;

	/** The short url. */
	@Value("${short.url}")
	private String shortUrl;

	/** The url spliter. */
	@Autowired
	private UrlSpliter urlSpliter;

	/** The file path. */
	@Value("${image.uploader.storage.path}")
	private String filePath;

	/**
	 * Gets the file.
	 *
	 * @param filename the filename
	 * @return the file
	 */
	@Override
	public Path getFile(String filename) {
		return Paths.get(filePath).resolve(filename);
	}

	/**
	 * Load file as resource.
	 *
	 * @param shortFileName the short file name
	 * @return the resource
	 */
	@Override
	public Resource loadFileAsResource(String shortFileName) {
		try {
			String completeShortUrl = shortUrl + shortFileName;
			UrlShortner urlShortnerFromDB = urlShortnerRepo.findByShortUrl(completeShortUrl);
			if (urlShortnerFromDB != null) {
				String fullUrl = urlShortnerFromDB.getUrl();
				String filename = urlSpliter.getSplitFileName(fullUrl);
				Path file = getFile(filename);
				Resource resource = new UrlResource(file.toUri());
				if (resource.exists() || resource.isReadable()) {
					return resource;
				} else {
					return null;

				}
			} else {
				return null;
			}

		} catch (MalformedURLException e) {
			return null;
		}
	}

}
