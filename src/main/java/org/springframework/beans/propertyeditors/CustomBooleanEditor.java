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
 * Property editor for Boolean properties.
 *
 * <p>This is not meant to be used as system PropertyEditor but rather as
 * locale-specific Boolean editor within custom controller code, to parse
 * UI-caused boolean strings into Boolean properties of beans, and
 * evaluate them in the UI form.
 *
 * <p>In web MVC code, this editor will typically be registered with
 * binder.registerCustomEditor calls in an implementation of
 * BaseCommandController's initBinder method.
 *
 * @author Juergen Hoeller
 * @since 10.06.2003
 * @see org.springframework.validation.DataBinder#registerCustomEditor
 * @see org.springframework.web.servlet.mvc.BaseCommandController#initBinder
 * @see org.springframework.web.bind.BindInitializer#initBinder
 */
public class CustomBooleanEditor extends PropertyEditorSupport {

	private boolean allowEmpty;

	/**
	 * Create a new instance.
	 * <p>The allowEmpty parameter states if an empty String should
	 * be allowed for parsing, i.e. get interpreted as null value.
	 * Else, an IllegalArgumentException gets thrown in that case.
	 * @param allowEmpty if empty strings should be allowed
	 */
	public CustomBooleanEditor(boolean allowEmpty) {
		this.allowEmpty = allowEmpty;
	}

	public void setAsText(String text) throws IllegalArgumentException {
		if (this.allowEmpty && text.trim().equals("")) {
			setValue(null);
		}
		else if (text.equalsIgnoreCase("true")) {
			setValue(Boolean.TRUE);
		}
		else if (text.equalsIgnoreCase("false")) {
			setValue(Boolean.FALSE);
		}
		else
			throw new IllegalArgumentException("Invalid Boolean value [" + text + "]");
	}

}
