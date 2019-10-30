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
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.NumberFormat;
import java.text.ParseException;

/**
 * Property editor for any Number subclass like Integer, Long, Float, Double.
 * Uses a given NumberFormat for (locale-specific) parsing and rendering.
 *
 * <p>This is not meant to be used as system PropertyEditor but rather as
 * locale-specific number editor within custom controller code, to parse
 * user-entered number strings into Number properties of beans, and render
 * them in the UI form.
 *
 * <p>In web MVC code, this editor will typically be registered with
 * binder.registerCustomEditor calls in an implementation of
 * BaseCommandController's initBinder method.
 *
 * @author Juergen Hoeller
 * @since 06.06.2003
 * @see org.springframework.validation.DataBinder#registerCustomEditor
 * @see org.springframework.web.servlet.mvc.BaseCommandController#initBinder
 * @see org.springframework.web.bind.BindInitializer#initBinder
 */
public class CustomNumberEditor extends PropertyEditorSupport {

	private Class numberClass;

	private NumberFormat numberFormat;

	private final boolean allowEmpty;

	/**
	 * Create a new instance, using the given NumberFormat for
	 * parsing and rendering.
	 * <p>The allowEmpty parameter states if an empty String should
	 * be allowed for parsing, i.e. get interpreted as null value.
	 * Else, an IllegalArgumentException gets thrown in that case.
	 * @param numberClass Number subclass to generate
	 * @param numberFormat NumberFormat to use for parsing and rendering
	 * @param allowEmpty if empty strings should be allowed
	 * @throws IllegalArgumentException if an invalid numberClass has been specified
	 */
	public CustomNumberEditor(Class numberClass, NumberFormat numberFormat, boolean allowEmpty)
	    throws IllegalArgumentException {
		if (!Number.class.isAssignableFrom(numberClass)) {
			throw new IllegalArgumentException("Property class must be a subclass of Number");
		}
		this.numberClass = numberClass;
		this.numberFormat = numberFormat;
		this.allowEmpty = allowEmpty;
	}

	public void setAsText(String text) throws IllegalArgumentException {
		if (this.allowEmpty && text.trim().equals("")) {
			setValue(null);
		}
		else {
			try {
				Number number = this.numberFormat.parse(text);
				if (this.numberClass.isInstance(number)) {
					setValue(number);
				}
				else if (this.numberClass.equals(Short.class)) {
					setValue(new Short(number.shortValue()));
				}
				else if (this.numberClass.equals(Integer.class)) {
					setValue(new Integer(number.intValue()));
				}
				else if (this.numberClass.equals(Long.class)) {
					setValue(new Long(number.longValue()));
				}
				else if (this.numberClass.equals(BigInteger.class)) {
					setValue(BigInteger.valueOf(number.longValue()));
				}
				else if (this.numberClass.equals(Float.class)) {
					setValue(new Float(number.floatValue()));
				}
				else if (this.numberClass.equals(Double.class)) {
					setValue(new Double(number.doubleValue()));
				}
				else if (this.numberClass.equals(BigDecimal.class)) {
					setValue(new BigDecimal(Double.toString(number.doubleValue())));
				}
				else {
					throw new IllegalArgumentException("Cannot convert [" + text + "] to [" + this.numberClass + "]");
				}
			}
			catch (ParseException ex) {
				throw new IllegalArgumentException("Cannot parse number: " + ex.getMessage());
			}
		}
	}

	public String getAsText() {
		return this.numberFormat.format(getValue());
	}

}
