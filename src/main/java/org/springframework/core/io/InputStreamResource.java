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

import java.io.IOException;
import java.io.InputStream;

/**
 * Resource implementation for a given InputStream. Should only
 * be used if no specific Resource implementation is applicable.
 *
 * <p>In contrast to other Resource implementations, this is a descriptor
 * for an <i>already opened</i> resource - therefore returning true on
 * isOpen(). Do not use it if you need to keep the resource descriptor
 * somewhere, or if you need to read a stream multiple times.
 *
 * @author Juergen Hoeller
 * @since 28.12.2003
 */
public class InputStreamResource extends AbstractResource {

	private InputStream inputStream;

	private final String description;

	/**
	 * Create a new InputStreamResource.
	 * @param inputStream the InputStream to use
	 * @param description where the InputStream comes from
	 */
	public InputStreamResource(InputStream inputStream, String description) {
		if (inputStream == null) {
			throw new IllegalArgumentException("inputStream must not be null");
		}
		this.inputStream = inputStream;
		this.description = description;
	}

	public boolean exists() {
		return true;
	}

	public boolean isOpen() {
		return true;
	}

	/**
	 * This implementation throws IllegalStateException if attempting to
	 * read the underlying stream multiple times.
	 */
	public InputStream getInputStream() throws IOException, IllegalStateException {
		if (this.inputStream == null) {
			throw new IllegalStateException("InputStream has already been read - " +
			                                "do not use InputStreamResource if a stream needs to be read multiple times");
		}
		InputStream result = this.inputStream;
		this.inputStream = null;
		return result;
	}

	public String getDescription() {
		return description;
	}

}
