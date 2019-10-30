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

/**
 * Property editor that trims Strings.
 * Allows to transform an empty string into a null value.
 * Needs to be explictly registered, e.g. for command binding.
 * @author Juergen Hoeller
 * @see org.springframework.validation.DataBinder#registerCustomEditor
 * @see org.springframework.web.servlet.mvc.BaseCommandController#initBinder
 * @see org.springframework.web.bind.BindInitializer#initBinder
 */
public class StringTrimmerEditor extends PropertyEditorSupport {

	private boolean emptyAsNull;

	/**
	 * Create a new instance.
	 * @param emptyAsNull whether to transform an empty string to null
	 */
	public StringTrimmerEditor(boolean emptyAsNull) {
		this.emptyAsNull = emptyAsNull;
	}

	public void setAsText(String text) throws IllegalArgumentException {
		if (text == null) {
			setValue(null);
		}
		else {
			String value = text.trim();
			if (this.emptyAsNull && "".equals(value)) {
				setValue(null);
			}
			else {
				setValue(value);
			}
		}
	}

}
