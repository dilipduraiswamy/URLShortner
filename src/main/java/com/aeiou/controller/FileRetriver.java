package com.aeiou.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.aeiou.service.FileRetriverService;

// TODO: Auto-generated Javadoc
/**
 * The Class FileRetriver.
 */
@RestController
public class FileRetriver {

	/** The file retriver service. */
	@Autowired
	private FileRetriverService fileRetriverService;

	/**
	 * Gets the file resource.
	 *
	 * @param fileName the file name
	 * @return the file resource
	 */
	@GetMapping(path = "/{fileName}")
	@ResponseBody
	public ResponseEntity<Resource> getFileResource(@PathVariable("fileName") String fileName) {
		Resource file = fileRetriverService.loadFileAsResource(fileName);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}
}
