
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

import org.springframework.util.StringUtils;

import java.util.*;

/**
 * Default implementation of the PropertyValues interface.
 * Allows simple manipulation of properties, and provides constructors
 * to support deep copy and construction from a Map.
 *
 * PropertyValues 에 대한 기본 구현체로 properties 에 대한 단순한 생성과 맵으로부터의 구성 딥카피를 지원하기 위한 생성자의 제공을 허용합니다.
 *
 * @author Rod Johnson
 * @since 13 May 2001
 * @version $Id: MutablePropertyValues.java,v 1.6 2004/03/18 02:46:12 trisberg Exp $
 */
public class MutablePropertyValues implements PropertyValues {

	/** List of PropertyValue objects */
	private List propertyValuesList;

	/**
	 * Creates a new empty MutablePropertyValues object.
	 * Property values can be added with the addPropertyValue methods.
	 * @see #addPropertyValue(PropertyValue)
	 * @see #addPropertyValue(String, Object)
	 */
	public MutablePropertyValues() {
		this.propertyValuesList = new ArrayList(10);
	}

	/**
	 * Deep copy constructor. Guarantees PropertyValue references
	 * are independent, although it can't deep copy objects currently
	 * referenced by individual PropertyValue objects
	 */
	public MutablePropertyValues(PropertyValues other) {
		this();
		if (other != null) {
			PropertyValue[] pvs = other.getPropertyValues();
			this.propertyValuesList = new ArrayList(pvs.length);
			for (int i = 0; i < pvs.length; i++) {
				addPropertyValue(new PropertyValue(pvs[i].getName(), pvs[i].getValue()));
			}
		}
	}

	/**
	 * Construct a new PropertyValues object from a Map.
	 * @param map Map with property values keyed by property name,
	 * which must be a String
	 */
	public MutablePropertyValues(Map map) {
		Set keys = map.keySet();
		this.propertyValuesList = new ArrayList(keys.size());
		Iterator itr = keys.iterator();
		while (itr.hasNext()) {
			String key = (String) itr.next();
			addPropertyValue(new PropertyValue(key, map.get(key)));
		}
	}

	/**
	 * Add a PropertyValue object, replacing any existing one
	 * for the respective property.
	 * @param pv PropertyValue object to add
	 */
	public void addPropertyValue(PropertyValue pv) {
		for (int i = 0; i < this.propertyValuesList.size(); i++) {
			PropertyValue currentPv = (PropertyValue) this.propertyValuesList.get(i);
			if (currentPv.getName().equals(pv.getName())) {
				this.propertyValuesList.set(i, pv);
				return;
			}
		}
		this.propertyValuesList.add(pv);
	}

	/**
	 * Overloaded version of addPropertyValue that takes
	 * a property name and a property value.
	 * @param propertyName name of the property
	 * @param propertyValue value of the property
	 * @see #addPropertyValue(PropertyValue)
	 */
	public void addPropertyValue(String propertyName, Object propertyValue) {
		addPropertyValue(new PropertyValue(propertyName, propertyValue));
	}

	/**
	 * Remove the given PropertyValue, if contained.
	 * @param pv the PropertyValue to remove
	 */
	public void removePropertyValue(PropertyValue pv) {
		this.propertyValuesList.remove(pv);
	}

	/**
	 * Overloaded version of removePropertyValue that takes
	 * a property name.
	 * @param propertyName name of the property
	 * @see #removePropertyValue(PropertyValue)
	 */
	public void removePropertyValue(String propertyName) {
		removePropertyValue(getPropertyValue(propertyName));
	}

	/**
	 * Modify a PropertyValue object held in this object.
	 * Indexed from 0.
	 */
	public void setPropertyValueAt(PropertyValue pv, int i) {
		this.propertyValuesList.set(i, pv);
	}

	public PropertyValue[] getPropertyValues() {
		return (PropertyValue[]) this.propertyValuesList.toArray(new PropertyValue[0]);
	}

	public PropertyValue getPropertyValue(String propertyName) {
		for (int i = 0; i < this.propertyValuesList.size(); i++) {
			PropertyValue pv = (PropertyValue) this.propertyValuesList.get(i);
			if (pv.getName().equals(propertyName)) {
				return pv;
			}
		}
		return null;
	}

	public boolean contains(String propertyName) {
		return getPropertyValue(propertyName) != null;
	}

	public PropertyValues changesSince(PropertyValues old) {
		MutablePropertyValues changes = new MutablePropertyValues();
		if (old == this)
			return changes;

		// For each property value in the new set
		for (int i = 0; i < this.propertyValuesList.size(); i++) {
			PropertyValue newPv = (PropertyValue) this.propertyValuesList.get(i);
			// If there wasn't an old one, add it
			PropertyValue pvOld = old.getPropertyValue(newPv.getName());
			if (pvOld == null) {
				changes.addPropertyValue(newPv);
			}
			else if (!pvOld.equals(newPv)) {
				// It's changed
				changes.addPropertyValue(newPv);
			}
		}
		return changes;
	}

	public String toString() {
		PropertyValue[] pvs = getPropertyValues();
		StringBuffer sb = new StringBuffer("MutablePropertyValues: length=" + pvs.length + "; ");
		sb.append(StringUtils.arrayToDelimitedString(pvs, ","));
		return sb.toString();
	}

}
