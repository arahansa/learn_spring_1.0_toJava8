/*
 * Copyright 2002-2004 the original author or authors.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */ 

package org.springframework.core.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;

/**
 * Resource implementation for java.net.URL locators.
 * Obviously supports resolution as URL, and also as File
 * in case of the "file:" protocol.
 * @author Juergen Hoeller
 * @since 28.12.2003
 * @see URL
 */
public class UrlResource extends AbstractResource {

	public static final String PROTOCOL_FILE = "file";

	private final URL url;

	/**
	 * Create a new UrlResource.
	 * @param url a URL
	 */
	public UrlResource(URL url) {
		this.url = url;
	}

	/**
	 * Create a new UrlResource.
	 * @param path a URL path
	 */
	public UrlResource(String path) throws MalformedURLException {
		this.url = new URL(path);
	}

	public InputStream getInputStream() throws IOException {
		return this.url.openStream();
	}

	public File getFile() throws IOException {
		if (PROTOCOL_FILE.equals(this.url.getProtocol())) {
			return new File(URLDecoder.decode(this.url.getFile()));
		}
		else {
			throw new FileNotFoundException(getDescription() + " cannot be resolved to absolute file path - " +
																			"no 'file:' protocol");
		}
	}

	public String getDescription() {
		return "URL [" + this.url + "]";
	}

}
