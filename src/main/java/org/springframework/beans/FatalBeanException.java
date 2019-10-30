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
 * Thrown on an unrecoverable problem encountered in the
 * beans packages or sub-packages, e.g. bad class or field.
 *
 * 빈/하위 패키지에서 나쁜 클래스나 필드같은 복구불가능한 문제에 직면했을 때 던져지는 예외
 *
 * @author Rod Johnson
 * @version $Revision: 1.4 $
 */
public class FatalBeanException extends BeansException {

	/**
	 * Constructs a <code>FatalBeanException</code> with the specified message.
	 * @param msg the detail message
	 */
	public FatalBeanException(String msg) {
		super(msg);
	}

	/**
	 * Constructs a <code>FatalBeanException</code> with the specified message
	 * and root cause.
	 * @param msg the detail message
	 * @param ex root cause
	 */
	public FatalBeanException(String msg, Throwable ex) {
		super(msg, ex);
	}

}
