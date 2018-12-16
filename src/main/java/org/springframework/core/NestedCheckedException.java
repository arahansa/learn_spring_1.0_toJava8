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

import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * Handy class for wrapping runtime Exceptions with a root cause. This time-honoured
 * technique is no longer necessary in Java 1.4, which provides built-in support for
 * exception nesting. Thus exceptions in applications written to use Java 1.4 need not
 * extend this class.
 *
 * <p>Abstract to force the programmer to extend the class.
 * printStackTrace() etc. are forwarded to the wrapped Exception.
 * The present assumption is that all application-specific exceptions that could be
 * displayed to humans (users, administrators etc.) will implement the ErrorCoded interface.
 *
 * <p>The similarity between this class and the NestedCheckedException class is unavoidable,
 * as Java forces these two classes to have different superclasses (ah, the inflexibility
 * of concrete inheritance!).
 *
 * <p>As discussed in <a href="http://www.amazon.com/exec/obidos/tg/detail/-/0764543857/">Expert One-On-One J2EE Design and Development</a>,
 * runtime exceptions are often a better alternative to checked exceptions. However, all exceptions
 * should preserve their stack trace, if caused by a lower-level exception.
 *
 * @author Rod Johnson
 * @version $Id: NestedCheckedException.java,v 1.6 2004/03/18 02:46:06 trisberg Exp $
 */
public abstract class NestedCheckedException extends Exception {

	/** Root cause of this nested exception */
	private Throwable cause;

	/**
	 * Construct a <code>ExceptionWrapperException</code> with the specified detail message.
	 * @param msg the detail message
	 */
	public NestedCheckedException(String msg) {
		super(msg);
	}

	/**
	 * Construct a <code>RemoteException</code> with the specified detail message
	 * and nested exception.
	 * @param msg the detail message
	 * @param ex the nested exception
	 */
	public NestedCheckedException(String msg, Throwable ex) {
		super(msg);
		this.cause = ex;
	}

	/**
	 * Return the nested cause, or null if none.
	 */
	public Throwable getCause() {
		return cause;
	}

	/**
	 * Return the detail message, including the message from the nested exception
	 * if there is one.
	 */
	public String getMessage() {
		if (this.cause == null) {
			return super.getMessage();
		}
		else {
			return super.getMessage() + "; nested exception is " + this.cause.getClass().getName() +
					": " + this.cause.getMessage();
		}
	}

	/**
	 * Print the composite message and the embedded stack trace to the specified stream.
	 * @param ps the print stream
	 */
	public void printStackTrace(PrintStream ps) {
		if (this.cause == null) {
			super.printStackTrace(ps);
		}
		else {
			ps.println(this);
			this.cause.printStackTrace(ps);
		}
	}

	/**
	 * Prints the composite message and the embedded stack trace to the specified print writer.
	 * @param pw the print writer
	 */
	public void printStackTrace(PrintWriter pw) {
		if (this.cause == null) {
			super.printStackTrace(pw);
		}
		else {
			pw.println(this);
			this.cause.printStackTrace(pw);
		}
	}

}
