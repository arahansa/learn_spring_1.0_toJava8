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
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Resource implementation for java.io.File handles.
 * Obviously supports resolution as File, and also as URL.
 * @author Juergen Hoeller
 * @since 28.12.2003
 * @see File
 */
public class FileSystemResource extends AbstractResource {

	private final File file;

	/**
	 * Create a new FileSystemResource.
	 * @param file a File handle
	 */
	public FileSystemResource(File file) {
		this.file = file;
	}

	/**
	 * Create a new FileSystemResource.
	 * @param path a file path
	 */
	public FileSystemResource(String path) {
		this.file = new File(path);
	}

	public boolean exists() {
		return this.file.exists();
	}

	public InputStream getInputStream() throws IOException {
		return new FileInputStream(this.file);
	}

	public URL getURL() throws IOException {
		return new URL(URL_PROTOCOL_FILE + ":" + this.file.getAbsolutePath());
	}

	public File getFile() {
		return file;
	}

	public String getDescription() {
		return "file [" + this.file.getAbsolutePath() + "]";
	}

}
