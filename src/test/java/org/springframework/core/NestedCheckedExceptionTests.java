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

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;

import junit.framework.TestCase;

/**
 * 
 * @author Rod Johnson
 * @version $Id: NestedCheckedExceptionTests.java,v 1.3 2004/03/18 03:01:13 trisberg Exp $
 */
public class NestedCheckedExceptionTests extends TestCase {

	public void testNoRootCause() {
		String mesg = "mesg of mine";
		// Making a class abstract doesn't _really_ prevent instantiation :-)
		NestedCheckedException nce = new NestedCheckedException(mesg) {};
		assertNull(nce.getCause());
		assertEquals(nce.getMessage(), mesg);
		
		// Check PrintStackTrace
		 ByteArrayOutputStream baos = new ByteArrayOutputStream();
		 PrintWriter pw = new PrintWriter(baos);
		 nce.printStackTrace(pw);
		 pw.flush();
		 String stackTrace = new String(baos.toByteArray());
		 assertFalse(stackTrace.indexOf(mesg) == -1);
	}
	
	public void testRootCause() {
		String myMessage = "mesg for this exception";
		String rootCauseMesg = "this is the obscure message of the root cause";
		ServletException rootCause = new ServletException(rootCauseMesg);
		// Making a class abstract doesn't _really_ prevent instantiation :-)
		NestedCheckedException nce = new NestedCheckedException(myMessage, rootCause) {};
		assertEquals(nce.getCause(), rootCause);
		assertTrue(nce.getMessage().indexOf(myMessage) != -1);
		assertTrue(nce.getMessage().indexOf(rootCauseMesg) != -1);
		
		// Check PrintStackTrace
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintWriter pw = new PrintWriter(baos);
		nce.printStackTrace(pw);
		pw.flush();
		String stackTrace = new String(baos.toByteArray());
		assertFalse(stackTrace.indexOf(rootCause.getClass().getName()) == -1);
		assertFalse(stackTrace.indexOf(rootCauseMesg) == -1);
	}

}
