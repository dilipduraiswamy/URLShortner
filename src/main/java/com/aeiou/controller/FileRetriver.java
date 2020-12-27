package com.aeiou.controller;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	 * @throws URISyntaxException
	 */
	@GetMapping(path = "/{fileName}")
	public ResponseEntity<Object> getFileResource(@PathVariable("fileName") String fileName) throws URISyntaxException {
		String orlUrl = fileRetriverService.loadFileAsUrl(fileName);
		URI uri = new URI(orlUrl);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(uri);
		return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
	}

//	@GetMapping(path = "/{fileName}")
//	public ResponseEntity<Object>  getFileResource(@PathVariable("fileName") String fileName) throws URISyntaxException {
//		URI yahoo = new URI("http://localhost:9090/v1/getFileByFileName?fileName=Webp.net-resizeimage-2.jpg");
//	    HttpHeaders httpHeaders = new HttpHeaders();
//	    httpHeaders.setLocation(yahoo);
//	    return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
//	}
}
