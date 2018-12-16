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

import org.junit.Test;

/**
 * Tests with ControlFlowFactory return.
 * @author Rod Johnson
 * @version $Id: Jdk13ControlFlowTests.java,v 1.2 2004/03/18 03:01:13 trisberg Exp $
 */
public class Jdk13ControlFlowTests extends AbstractControlFlowTests {
	
	/**
	 * Necessary only because Eclipse won't run test suite unless it declares
	 * some methods as well as inherited methods
	 */
	@Test
	public void testThisClassPlease() {
		super.testUnderClassAndMethod();
	}

	protected ControlFlow createControlFlow() {
		return new ControlFlowFactory.Jdk13ControlFlow();
	}

}
