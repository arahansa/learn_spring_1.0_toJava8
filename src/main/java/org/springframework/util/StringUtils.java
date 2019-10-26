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

package org.springframework.util;

import java.util.*;

/**
 * Miscellaneous string utility methods. Mainly for internal use
 * within the framework; consider Jakarta's Commons Lang for a more
 * comprehensive suite of string utilities.
 *
 * 잡다한 문자열 유틸리티 메소드들. 프레임워크 내에서 주로 사용하기 위함
 * Jakarta의 Commons Lang을 좀 더 종합적인 문자열 유틸리티로 고려하였다.
 *
 * <p>This class delivers some simple functionality that should really
 * be provided by the core Java String and StringBuffer classes, such
 * as the ability to replace all occurrences of a given substring in a
 * target string. It also provides easy-to-use methods to convert between
 * delimited strings, such as CSV strings, and collections and arrays.
 *
 * 이 클래스는 자바 문자열과 Stringbuffer 클래스들에서 정말로 제공되어져야할 단순한 기능들을 제공하기 위해 만들어졌다.
 *
 *
 * @author Rod Johnson
 * @author Juergen Hoeller
 * @author Keith Donald
 * @since 16 April 2001
 * @version $Id: StringUtils.java,v 1.11 2004/03/19 16:45:29 jhoeller Exp $
 * @see org.apache.commons.lang.StringUtils
 */
public abstract class StringUtils {

	/**
	 * Count the occurrences of the substring in string s.
	 * @param s string to search in. Return 0 if this is null.
	 * @param sub string to search for. Return 0 if this is null.
	 */
	public static int countOccurrencesOf(String s, String sub) {
		if (s == null || sub == null || "".equals(sub)) {
			return 0;
		}
		int count = 0, pos = 0, idx = 0;
		while ((idx = s.indexOf(sub, pos)) != -1) {
			++count;
			pos = idx + sub.length();
		}
		return count;
	}

	/**
	 * Replace all occurences of a substring within a string with
	 * another string.
	 * @param inString String to examine
	 * @param oldPattern String to replace
	 * @param newPattern String to insert
	 * @return a String with the replacements
	 */
	public static String replace(String inString, String oldPattern, String newPattern) {
		if (inString == null) {
			return null;
		}
		if (oldPattern == null || newPattern == null) {
			return inString;
		}

		StringBuffer sbuf = new StringBuffer();
		// output StringBuffer we'll build up
		int pos = 0; // Our position in the old string
		int index = inString.indexOf(oldPattern);
		// the index of an occurrence we've found, or -1
		int patLen = oldPattern.length();
		while (index >= 0) {
			sbuf.append(inString.substring(pos, index));
			sbuf.append(newPattern);
			pos = index + patLen;
			index = inString.indexOf(oldPattern, pos);
		}
		sbuf.append(inString.substring(pos));

		// remember to append any characters to the right of a match
		return sbuf.toString();
	}

	/**
	 * Delete all occurrences of the given substring.
	 * @param pattern the pattern to delete all occurrences of
	 */
	public static String delete(String inString, String pattern) {
		return replace(inString, pattern, "");
	}

	/**
	 * Delete any character in a given string.
	 * @param chars characters to delete.
	 * E.g. az\n will delete as, zs and new lines.
	 */
	public static String deleteAny(String inString, String chars) {
		if (inString == null || chars == null) {
			return inString;
		}
		StringBuffer out = new StringBuffer();
		for (int i = 0; i < inString.length(); i++) {
			char c = inString.charAt(i);
			if (chars.indexOf(c) == -1) {
				out.append(c);
			}
		}
		return out.toString();
	}

	/**
	 * Tokenize the given String into a String array via a StringTokenizer.
	 * @param s the String to tokenize
	 * @param delimiters the delimiter characters, assembled as String
	 * @param trimTokens trim the tokens via String.trim
	 * @param ignoreEmptyTokens omit empty tokens from the result array
	 * @return an array of the tokens
	 * @see java.util.StringTokenizer
	 * @see java.lang.String#trim
	 */
	public static String[] tokenizeToStringArray(String s, String delimiters,
                                                 boolean trimTokens, boolean ignoreEmptyTokens) {
		StringTokenizer st = new StringTokenizer(s, delimiters);
		List tokens = new ArrayList();
		while (st.hasMoreTokens()) {
			String token = st.nextToken();
			if (trimTokens) {
				token = token.trim();
			}
			if (!(ignoreEmptyTokens && token.length() == 0)) {
				tokens.add(token);
			}
		}
		return (String[]) tokens.toArray(new String[tokens.size()]);
	}

	/**
	 * Take a String which is a delimited list and convert it to a String array.
	 * @param s String
	 * @param delim delim (this will not be returned)
	 * @return an array of the tokens in the list
	 */
	public static String[] delimitedListToStringArray(String s, String delim) {
		if (s == null) {
			return new String[0];
		}
		if (delim == null) {
			return new String[]{s};
		}

		List l = new LinkedList();
		int pos = 0;
		int delPos = 0;
		while ((delPos = s.indexOf(delim, pos)) != -1) {
			l.add(s.substring(pos, delPos));
			pos = delPos + delim.length();
		}
		if (pos <= s.length()) {
			// add rest of String
			l.add(s.substring(pos));
		}

		return (String[]) l.toArray(new String[l.size()]);
	}

	/**
	 * Convert a CSV list into an array of Strings.
	 * @param s CSV list
	 * @return an array of Strings, or the empty array if s is null
	 */
	public static String[] commaDelimitedListToStringArray(String s) {
		return delimitedListToStringArray(s, ",");
	}

	/**
	 * Convenience method to convert a CSV string list to a set.
	 * Note that this will suppress duplicates.
	 * @param s CSV String
	 * @return a Set of String entries in the list
	 */
	public static Set commaDelimitedListToSet(String s) {
		Set set = new TreeSet();
		String[] tokens = commaDelimitedListToStringArray(s);
		for (int i = 0; i < tokens.length; i++) {
			set.add(tokens[i]);
		}
		return set;
	}

	/**
	 * Convenience method to return a String array as a delimited (e.g. CSV)
	 * String. E.g. useful for toString() implementations.
	 * @param arr array to display. Elements may be of any type (toString
	 * will be called on each element).
	 * @param delim delimiter to use (probably a ,)
	 */
	public static String arrayToDelimitedString(Object[] arr, String delim) {
		if (arr == null) {
			return "null";
		}
		else {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < arr.length; i++) {
				if (i > 0)
					sb.append(delim);
				sb.append(arr[i]);
			}
			return sb.toString();
		}
	}

	/**
	 * Convenience method to return a Collection as a delimited (e.g. CSV)
	 * String. E.g. useful for toString() implementations.
	 * @param c Collection to display
	 * @param delim delimiter to use (probably a ",")
	 */
	public static String collectionToDelimitedString(Collection c, String delim) {
		if (c == null) {
			return "null";
		}
		StringBuffer sb = new StringBuffer();
		Iterator itr = c.iterator();
		int i = 0;
		while (itr.hasNext()) {
			if (i++ > 0) {
				sb.append(delim);
			}
			sb.append(itr.next());
		}
		return sb.toString();
	}

	/**
	 * Convenience method to return a String array as a CSV String.
	 * E.g. useful for toString() implementations.
	 * @param arr array to display. Elements may be of any type (toString
	 * will be called on each element).
	 */
	public static String arrayToCommaDelimitedString(Object[] arr) {
		return arrayToDelimitedString(arr, ",");
	}

	/**
	 * Convenience method to return a Collection as a CSV String.
	 * E.g. useful for toString() implementations.
	 * @param c Collection to display
	 */
	public static String collectionToCommaDelimitedString(Collection c) {
		return collectionToDelimitedString(c, ",");
	}

	/**
	 * Append the given String to the given String array, returning a new array
	 * consisting of the input array contents plus the given String.
	 * @param arr the array to append to
	 * @param s the String to append
	 * @return the new array
	 */
	public static String[] addStringToArray(String[] arr, String s) {
		String[] newArr = new String[arr.length + 1];
		System.arraycopy(arr, 0, newArr, 0, arr.length);
		newArr[arr.length] = s;
		return newArr;
	}

	/**
	 * Checks if a String has length.
	 * <p><pre>
	 * StringUtils.hasLength(null) = false
	 * StringUtils.hasLength("") = false
	 * StringUtils.hasLength(" ") = true
	 * StringUtils.hasLength("Hello") = true
	 * </pre>
	 * @param str the String to check, may be null
	 * @return <code>true</code> if the String is has length and is not null
	 */
	public static boolean hasLength(String str) {
		return (str != null && str.length() > 0);
	}

	/**
	 * Checks if a String has text. More specifically, returns <code>true</code>
	 * if the string not <code>null<code>, it's <code>length is > 0</code>, and
	 * it has at least one non-whitespace character.
	 * <p><pre>
	 * StringUtils.hasText(null) = false
	 * StringUtils.hasText("") = false
	 * StringUtils.hasText(" ") = false
	 * StringUtils.hasText("12345") = true
	 * StringUtils.hasText(" 12345 ") = true
	 * </pre>
	 * @param str the String to check, may be null
	 * @return <code>true</code> if the String is not null, length > 0,
	 * and not whitespace only
	 */
	public static boolean hasText(String str) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0) {
			return false;
		}
		for (int i = 0; i < strLen; i++) {
			if ((Character.isWhitespace(str.charAt(i)) == false)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Unqualifies a string qualified by a '.' dot character. For example,
	 * "this.name.is.qualified", returns "qualified".
	 * @param qualifiedName the qualified name
	 */
	public static String unqualify(String qualifiedName) {
		return unqualify(qualifiedName, '.');
	}

	/**
	 * Unqualifies a string qualified by a separator character. For example,
	 * "this:name:is:qualified" returns "qualified" if using a ':' separator.
	 * @param qualifiedName the qualified name
	 * @param separator the separator
	 */
	public static String unqualify(String qualifiedName, char separator) {
		return qualifiedName.substring(qualifiedName.lastIndexOf(separator) + 1);
	}

	/**
	 * Uncapitalizes a <code>String</code>, changing the first letter to
	 * lower case as per {@link Character#toLowerCase(char)}.
	 * No other letters are changed.
	 * @param str the String to uncapitalize, may be null
	 * @return the uncapitalized String, <code>null</code> if null
	 * String input
	 */
	public static String uncapitalize(String str) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0) {
			return str;
		}
		StringBuffer buf = new StringBuffer(strLen);
		buf.append(Character.toLowerCase(str.charAt(0)));
		buf.append(str.substring(1));
		return buf.toString();
	}

}
