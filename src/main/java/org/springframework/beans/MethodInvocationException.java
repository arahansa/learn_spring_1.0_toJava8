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
 * Thrown when a method getter or setter throws an exception,
 * analogous to an InvocationTargetException.
 * @author Rod Johnson
 * @version $Revision: 1.6 $
 */
public class MethodInvocationException extends PropertyAccessException {

	/**
	 * Constructor to use when an exception results from a PropertyChangeEvent.
	 * @param ex Throwable raised by invoked method
	 * @param propertyChangeEvent PropertyChangeEvent that resulted in an exception
	 */
	public MethodInvocationException(Throwable ex, PropertyChangeEvent propertyChangeEvent) {
		super("Property '" + propertyChangeEvent.getPropertyName() + "' threw exception", propertyChangeEvent, ex);
	}

	public String getErrorCode() {
		return "methodInvocation";
	}

}
