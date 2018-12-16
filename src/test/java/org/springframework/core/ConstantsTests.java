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

import java.util.Set;

import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * @author Rod Johnson
 * @since 28-Apr-2003
 * @version $Revision: 1.2 $
 */
public class ConstantsTests{

	@Test
	public void propertyToConstantNamePrefix(){
		Constants c = new Constants(A.class);

		String myPropertyNo = c.propertyToConstantNamePrefix("myPropertyNo");
		assertEquals("MY_PROPERTY_NO", myPropertyNo);

		myPropertyNo = c.propertyToConstantNamePrefix("myPropertyNo1");
		assertEquals("MY_PROPERTY_NO1", myPropertyNo);
	}


	@Test
	public void testConstants() {
		Constants c = new Constants(A.class);
		assertTrue(c.getSize() == 5);
		
		assertEquals(c.asNumber("DOG").intValue(), A.DOG);
		assertEquals(c.asNumber("dog").intValue(), A.DOG);
		assertEquals(c.asNumber("cat").intValue(), A.CAT);

		try {
			c.asNumber("bogus");
			fail("Can't get bogus field");
		}
		catch (ConstantException ex) {
		}

		assertTrue(c.asString("S1").equals(A.S1));
		try {
			c.asNumber("S1");
			fail("Wrong type");
		}
		catch (ConstantException ex) {
		}

		Set<Object> values = c.getValues("");
		assertEquals(5, values.size());
		assertEquals(c.getSize(), values.size());
		assertTrue(values.contains(Integer.valueOf(0)));
		assertTrue(values.contains(Integer.valueOf(66)));
		assertTrue(values.contains(""));

		values = c.getValues("D");
		assertEquals(1, values.size());
		assertTrue(values.contains(Integer.valueOf(0)));

		/**
		 * public static final int MY_PROPERTY_NO = 1;
		 * public static final int MY_PROPERTY_YES = 2;
		 * 두개 리턴
		 */
		values = c.getValuesForProperty("myProperty");
		assertEquals(2, values.size());
		assertTrue(values.contains(Integer.valueOf(1)));
		assertTrue(values.contains(Integer.valueOf(2)));

		/**
		 * toCode 는 첫번째 파라미터 값과 namePrefix 가 맞으면 필드명 리턴
		 */
		assertEquals(c.toCode(Integer.valueOf(0), ""), "DOG");
		assertEquals(c.toCode(Integer.valueOf(0), "D"), "DOG");
		assertEquals(c.toCode(Integer.valueOf(0), "DO"), "DOG");
		assertEquals(c.toCode(Integer.valueOf(0), "DoG"), "DOG");
		assertEquals(c.toCode(Integer.valueOf(66), ""), "CAT");
		assertEquals(c.toCode(Integer.valueOf(66), "C"), "CAT");
		assertEquals(c.toCode(Integer.valueOf(66), "ca"), "CAT");
		assertEquals(c.toCode(Integer.valueOf(66), "cAt"), "CAT");
		assertEquals(c.toCode("", ""), "S1");
		assertEquals(c.toCode("", "s"), "S1");
		assertEquals(c.toCode("", "s1"), "S1");
		try {
			c.toCode("bogus", "bogus");
			fail("Should have thrown ConstantException");
		}
		catch (ConstantException ex) {
			// expected
		}

		/**
		 * toCodeForProperty 는 Integer 값과 바뀐 필드명을 넣으면 실제 필드명 리턴
		 */
		assertEquals(c.toCodeForProperty(Integer.valueOf(1), "myProperty"), "MY_PROPERTY_NO");
		assertEquals(c.toCodeForProperty(Integer.valueOf(2), "myProperty"), "MY_PROPERTY_YES");
		try {
			c.toCodeForProperty("bogus", "bogus");
			fail("Should have thrown ConstantException");
		}
		catch (ConstantException ex) {
			// expected
		}
	}
	
	
	public static class A {
		
		public static final int DOG = 0;
		public static final int CAT = 66;
		public static final String S1 = "";

		public static final int MY_PROPERTY_NO = 1;
		public static final int MY_PROPERTY_YES = 2;

		/** ignore these */
		protected static final int P = -1;
		protected boolean f;
		static final Object o = new Object();
	}

}
