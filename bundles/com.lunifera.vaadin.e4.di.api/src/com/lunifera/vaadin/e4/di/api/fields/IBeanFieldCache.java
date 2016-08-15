package com.lunifera.vaadin.e4.di.api.fields;

import org.eclipse.e4.core.contexts.IEclipseContext;

import com.vaadin.ui.Field;

public interface IBeanFieldCache {

	/**
	 * Returns a field for the given entity class.
	 * 
	 * @param beanClass
	 * @return
	 */
	<T> Field<T> getFieldFor(Class<T> beanClass, IEclipseContext context);

	/**
	 * Register a field for the given field type.
	 * 
	 * @param beanClass
	 * @param fieldType
	 */
	<T> void registerField(Class<T> beanClass, Class<? extends Field<? extends T>> fieldType);

	/**
	 * Unregisters the beanClass mapping.
	 * 
	 * @param beanClass
	 */
	void unregisterField(Class<?> beanClass);

}
