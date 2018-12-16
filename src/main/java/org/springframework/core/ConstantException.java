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

package org.springframework.core;

/**
 * Exception thrown when the Constants class is asked for an invalid
 * constant name.
 * @see org.springframework.core.Constants
 * @version $Id: ConstantException.java,v 1.2 2004/03/18 02:46:06 trisberg Exp $
 * @author Rod Johnson
 * @since 28-Apr-2003
 *
 * Constants 클래스가 유효하지 않은 상수 이름을 물어볼때 발생할 수 있는 예외
 */
public class ConstantException extends IllegalArgumentException {
	
	/**
	 * Thrown when an invalid constant name is requested.
	 * @param clazz class containing the constant definitions
	 * @param field invalid constant name
	 * @param message description of the problem
	 */
	public ConstantException(Class clazz, String field, String message) {
		super("Field '" + field + "' " + message + " in " + clazz);
	}

	/**
	 * Thrown when an invalid constant value is looked up.
	 * @param clazz class containing the constant definitions
	 * @param namePrefix prefix of the searched constant names
	 * @param value the looked up constant value
	 */
	public ConstantException(Class clazz, String namePrefix, Object value) {
		super("No '" + namePrefix + "' field with value '" + value + "' found in " + clazz);
	}

}
