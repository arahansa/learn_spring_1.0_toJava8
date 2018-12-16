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

import java.beans.PropertyEditorSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Editor for Resource descriptors, to convert String locations to Resource
 * properties automatically instead of using a String location property.
 *
 * <p>The path may contain ${...} placeholders, to be resolved as
 * system properties: e.g. ${user.dir}.
 *
 * <p>Delegates to a ResourceLoader, by default DefaultResourceLoader.
 *
 * @author Juergen Hoeller
 * @since 28.12.2003
 * @see #PLACEHOLDER_PREFIX
 * @see #PLACEHOLDER_SUFFIX
 * @see #getResourceLoader
 * @see Resource
 * @see ResourceLoader
 * @see DefaultResourceLoader
 * @see System#getProperty(String)
 */
public class ResourceEditor extends PropertyEditorSupport {

	protected static final Log logger = LogFactory.getLog(ResourceEditor.class);

	public static final String PLACEHOLDER_PREFIX = "${";

	public static final String PLACEHOLDER_SUFFIX = "}";

	public void setAsText(String text) {
		setValue(getResourceLoader().getResource(resolvePath(text)));
	}

	/**
	 * Resolve the given path, replacing placeholders with corresponding
	 * system property values if necessary.
	 * @param path the original file path
	 * @return the resolved file path
	 * @see #PLACEHOLDER_PREFIX
	 * @see #PLACEHOLDER_SUFFIX
	 */
	protected String resolvePath(String path) {
		int startIndex = path.indexOf(PLACEHOLDER_PREFIX);
		if (startIndex != -1) {
			int endIndex = path.indexOf(PLACEHOLDER_SUFFIX, startIndex + PLACEHOLDER_PREFIX.length());
			if (endIndex != -1) {
				String placeholder = path.substring(startIndex + PLACEHOLDER_PREFIX.length(), endIndex);
				String propVal = System.getProperty(placeholder);
				if (propVal != null) {
					return path.substring(0, startIndex) + propVal + path.substring(endIndex+1);
				}
				else {
					logger.warn("Could not resolve placeholder '" + placeholder +
					            "' in file path [" + path + "] as system property");
				}
			}
		}
		return path;
	}

	/**
	 * Determine the ResourceLoader to use for converting the
	 * property text to a Resource. Default is DefaultResourceLoader.
	 * @see DefaultResourceLoader
	 */
	protected ResourceLoader getResourceLoader() {
		return new DefaultResourceLoader();
	}

}
