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
 * Object containing 0 or more PropertyValues comprising one update.
 * 0 혹은 많은 PropertyValue 들을 간직하는 객체
 * @author Rod Johnson
 * @since 13 May 2001
 * @version $Id: PropertyValues.java,v 1.3 2004/03/18 02:46:12 trisberg Exp $
 */
public interface PropertyValues {
   
	/** 
	 * Return an array of the PropertyValue objects held in this object.
	 * @return an array of the PropertyValue objects held in this object.
	 */
	PropertyValue[] getPropertyValues();	
	
	/**
	 * Return the property value with the given name.
	 * @param propertyName name to search for
	 * @return pv or null
	 */
	PropertyValue getPropertyValue(String propertyName);

	/**
	 * Is there a property value for this property?
	 * @param propertyName name of the property we're interested in
	 * @return whether there is a property value for this property
	 */
	boolean contains(String propertyName);
	
	/**
	 * Return the changes since the previous PropertyValues.
	 * Subclasses should also override equals.
	 * @param old old property values
	 * @return PropertyValues updated or new properties.
	 * Return the empty PropertyValues if there are no changes.
	 */
	PropertyValues changesSince(PropertyValues old);

}
