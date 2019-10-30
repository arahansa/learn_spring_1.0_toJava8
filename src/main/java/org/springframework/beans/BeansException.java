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

import org.springframework.core.NestedRuntimeException;

/**
 * Abstract superclass for all exceptions thrown in the beans package
 * and subpackages.
 *
 * 빈, 서브패키지에서의 던져지는 모든 예외들에 대한 추상클래스
 *
 *
 *
 * <p>Note that this is a runtime (unchecked) exception. Beans exceptions
 * are usually fatal; there is no reason for them to be checked.
 *
 * @author Rod Johnson
 */
public abstract class BeansException extends NestedRuntimeException {

	/**
	 * Constructs a <code>BeansException</code> with the specified message.
	 * @param msg the detail message
	 */
	public BeansException(String msg) {
		super(msg);
	}

	/**
	 * Constructs a <code>BeansException</code> with the specified message
	 * and root cause.
	 * @param msg the detail message
	 * @param ex the root cause
	 */
	public BeansException(String msg, Throwable ex) {
		super(msg, ex);
	}

}
