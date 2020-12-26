package com.aeiou.service;

import java.nio.file.Path;

import org.springframework.core.io.Resource;

// TODO: Auto-generated Javadoc
/**
 * The Interface FileRetriverService.
 */
public interface FileRetriverService {

	/**
	 * Load file as resource.
	 *
	 * @param filename the filename
	 * @return the resource
	 */
	Resource loadFileAsResource(String filename);

	/**
	 * Gets the file.
	 *
	 * @param filename the filename
	 * @return the file
	 */
	Path getFile(String filename);

}
