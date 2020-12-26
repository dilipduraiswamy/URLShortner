package com.aeiou.util;

import org.springframework.stereotype.Component;

// TODO: Auto-generated Javadoc
/**
 * The Class UrlSpliter.
 */
@Component
public class UrlSpliter {
	
	/**
	 * Gets the split file name.
	 *
	 * @param url the url
	 * @return the split file name
	 */
	public String getSplitFileName(String url) {
		String fileName = "";
		if (url.contains("/")) {
			String[] split = url.split("/");
			fileName = split[split.length - 1];
			fileName = fileName.replace("getFileByFileName?fileName=", "");
//			if (fileName.contains("//?")) {
//				String[] splitQuestion = url.split("//?");
//				// getFileByFileName?
//				fileName = splitQuestion[splitQuestion.length - 1];
//			}
		}
		return fileName;
	}
}
