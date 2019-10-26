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
 * Class used to find the current JVM version.
 * Usually we want to find if we're in a 1.4 or higher JVM.
 * (Spring does not support 1.2 JVMs.)
 * @author Rod Johnson
 * @version $Id: JdkVersion.java,v 1.2 2004/03/18 02:46:06 trisberg Exp $
 *
 * 현재 JVM version 을 찾기 위해 사용되는 클래스
 */
public class JdkVersion {
	
	public static final int JAVA_13 = 0;
	
	public static final int JAVA_14 = 1;
	
	public static final int JAVA_15 = 2;

	public static final int JAVA_18 = 3;

	private static int majorJavaVersion = JAVA_18;
	
	static {
		String javaVersion = System.getProperty("java.version");
		// should look like "1.4.1_02"
		if (javaVersion.indexOf("1.4") != -1) {
			majorJavaVersion = JAVA_14;
		}
		else if (javaVersion.indexOf("1.5") != -1) {
			majorJavaVersion = JAVA_15;
		}
		// else leave as 1.3 default
	}
	
	/**
	 * Get the major version code. This means we can do things like
	 * if getJavaVersion() >= JAVA_14
	 * @return a code comparable to the JAVA_XX codes in this
	 * class. 
	 */
	public static int getMajorJavaVersion() {
		return majorJavaVersion;
	}

}
