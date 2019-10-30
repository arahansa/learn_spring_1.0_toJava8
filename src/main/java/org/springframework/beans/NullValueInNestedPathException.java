
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

package org.springframework.beans;

/**
 * Exception thrown when navigation of a valid nested property
 * path encounters a null pointer exception. For example,
 * navigating spouse.age fails because the spouse property of the
 * target object has a null value.
 * @author Rod Johnson
 */
public class NullValueInNestedPathException extends FatalBeanException {

	private String property;

	private Class clazz;

	/**
	 * Constructor for NullValueInNestedPathException.
	 * @param clazz
	 * @param propertyName
	 */
	public NullValueInNestedPathException(Class clazz, String propertyName) {
		super("Value of nested property '" + propertyName + "' is null in " + clazz, null);
		this.property = propertyName;
		this.clazz = clazz;
	}

	/**
	 * @return the name of the offending property
	 */
	public String getPropertyName() {
		return property;
	}

	public Class getBeanClass() {
		return clazz;
	}

}
