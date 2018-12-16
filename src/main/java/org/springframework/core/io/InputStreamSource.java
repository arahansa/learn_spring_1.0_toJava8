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
 * Simple interface for objects that are sources for java.io.InputStreams.
 * Base interface for Spring's Resource interface.
 * @author Juergen Hoeller
 * @since 20.01.2004
 * @see Resource
 */
public interface InputStreamSource {

	/**
	 * Return an InputStream.
	 * It is expected that each call creates a <i>fresh</i> stream.
	 * @throws IOException if the stream could not be opened
	 */
	InputStream getInputStream() throws IOException;

}
