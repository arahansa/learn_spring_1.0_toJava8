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

import java.beans.PropertyChangeEvent;

/**
 * Exception thrown on a type mismatch when trying to set a property.
 * @author Rod Johnson
 * @author Juergen Hoeller
 * @version $Revision: 1.5 $
 */
public class TypeMismatchException extends PropertyAccessException {

	public TypeMismatchException(PropertyChangeEvent propertyChangeEvent, Class requiredType) {
		super("Cannot convert property value of type [" +
		      (propertyChangeEvent.getNewValue() != null ?
		       propertyChangeEvent.getNewValue().getClass().getName() : null) +
		      "] to required type [" + requiredType.getName() + "]" +
					(propertyChangeEvent.getPropertyName() != null ?
					 " for property '" + propertyChangeEvent.getPropertyName() + "'" : ""),
					propertyChangeEvent);
	}

	public TypeMismatchException(PropertyChangeEvent propertyChangeEvent, Class requiredType, Throwable ex) {
		super("Failed to convert property value of type [" +
		      (propertyChangeEvent.getNewValue() != null ?
		       propertyChangeEvent.getNewValue().getClass().getName() : null) +
		      "] to required type [" + requiredType.getName() + "]" +
					(propertyChangeEvent.getPropertyName() != null ?
					 " for property '" + propertyChangeEvent.getPropertyName() + "'" : ""),
					propertyChangeEvent, ex);
	}

	public String getErrorCode() {
		return "typeMismatch";
	}

}
