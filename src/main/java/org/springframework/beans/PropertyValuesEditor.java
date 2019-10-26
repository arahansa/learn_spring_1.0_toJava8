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

import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.propertyeditors.PropertiesEditor;

import java.beans.PropertyEditorSupport;
import java.util.Properties;

/**
 * Editor for PropertyValues objects. Not
 * a GUI editor.
 *
 * PropertyValues 를 위한 에디터로 GUI 에디터가 아니다
 *
 * 이 에디터는 사용가능하기전에 JavaBeans API 과 등록이 되어져야한다.
 * 이 패키지의 에디터들은 BeanWrapperImpl 에 의해 등록되어진다
 *
 * <br>NB: this editor must be registered with the JavaBeans API before it
 * will be available. Editors in this package are
 * registered by BeanWrapperImpl.
 * <br>The required format is defined in java.util.Properties documentation.
 * Each property must be on a new line.
 * <br>
 * The present implementation relies on a PropertiesEditor.
 * @author Rod Johnson
 */
public class PropertyValuesEditor extends PropertyEditorSupport {
	
	
	/**
	 * @see java.beans.PropertyEditor#setAsText(java.lang.String)
	 */
	public void setAsText(String s) throws IllegalArgumentException {
		PropertiesEditor pe = new PropertiesEditor();
		pe.setAsText(s);
		Properties props = (Properties) pe.getValue();
		setValue(new MutablePropertyValues(props));
	}

}

