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
 * Exception thrown on an attempt to set the value of a property
 * that isn't writable, because there's no setter method.
 * @author Rod Johnson
 */
public class NotWritablePropertyException extends BeansException {

	/**
	 * Creates new NotWritablePropertyException.
	 */
	public NotWritablePropertyException(String propertyName, Class beanClass) {
		super("Property '" + propertyName + "' is not writable in bean class [" + beanClass.getName() + "]");
	}

	/**
	 * Creates new NotWritablePropertyException with a root cause.
	 */
	public NotWritablePropertyException(String propertyName, Class beanClass, Throwable ex) {
		super("Property '" + propertyName + "' is not writable in bean class [" + beanClass.getName() + "]", ex);
	}

}
