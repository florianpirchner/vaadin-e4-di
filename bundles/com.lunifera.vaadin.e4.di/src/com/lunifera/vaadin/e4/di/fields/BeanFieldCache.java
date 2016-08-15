package com.lunifera.vaadin.e4.di.fields;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.osgi.service.component.annotations.Component;

import com.lunifera.vaadin.e4.di.api.fields.IBeanFieldCache;
import com.vaadin.ui.Field;

@SuppressWarnings("unchecked")
@Component
public class BeanFieldCache implements IBeanFieldCache {

	@SuppressWarnings("rawtypes")
	private final Map map = new HashMap();

	/**
	 * Returns a field for the given entity class.
	 * 
	 * @param beanClass
	 * @return
	 */
	public <T> Field<T> getFieldFor(Class<T> beanClass, IEclipseContext context) {
		Class<Field<T>> clazz = (Class<Field<T>>) map.get(beanClass);
		if (clazz != null) {
			return ContextInjectionFactory.make(clazz, context);
		}
		return null;
	}

	@Override
	public <T> void registerField(Class<T> beanClass, Class<? extends Field<? extends T>> fieldType) {
		map.put(beanClass, fieldType);
	}

	@Override
	public void unregisterField(Class<?> beanClass) {
		map.remove(beanClass);
	}

}
