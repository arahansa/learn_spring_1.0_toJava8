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

package org.springframework.beans.propertyeditors;

import java.beans.PropertyEditorSupport;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Editor for java.net.URL, to directly feed a URL property
 * instead of using a String property.
 * @author Juergen Hoeller
 * @since 15.12.2003
 */
public class URLEditor extends PropertyEditorSupport {

	public void setAsText(String text) throws IllegalArgumentException {
		try {
			setValue(new URL(text));
		}
		catch (MalformedURLException ex) {
			throw new IllegalArgumentException("Malformed URL: " + ex.getMessage());
		}
	}

	public String getAsText() {
		return ((URL) getValue()).toExternalForm();
	}

}
