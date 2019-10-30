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

import org.springframework.util.StringUtils;

import java.beans.PropertyEditorSupport;
import java.util.Locale;

/**
 * Editor for java.util.Locale, to directly feed a Locale property.
 * Expects the same syntax as Locale.toString, i.e. language + optionally
 * country + optionally variant, separated by "_" (e.g. "en", "en_US").
 * @author Juergen Hoeller
 * @since 26.05.2003
 */
public class LocaleEditor extends PropertyEditorSupport {

	public void setAsText(String text) {
		String[] parts = StringUtils.delimitedListToStringArray(text, "_");
		String language = parts.length > 0 ? parts[0] : "";
		String country = parts.length > 1 ? parts[1] : "";
		String variant = parts.length > 2 ? parts[2] : "";
		setValue(language.length() > 0 ? new Locale(language, country, variant) : null);
	}

}
