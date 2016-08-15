package com.lunifera.vaadin.e4.di.fields;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.eclipse.e4.core.contexts.IEclipseContext;

import com.lunifera.vaadin.e4.di.api.fields.IBeanFieldCache;
import com.vaadin.data.fieldgroup.DefaultFieldGroupFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.TextField;

@SuppressWarnings({ "unchecked", "serial" })
@Singleton
public class E4FieldGroupFieldFactory extends DefaultFieldGroupFieldFactory {

	@Inject
	IBeanFieldCache fieldCache;

	@Inject
	IEclipseContext context;

	@SuppressWarnings("rawtypes")
	public <T extends Field> T createField(Class<?> type, Class<T> fieldType) {
		Field<?> field = fieldCache.getFieldFor(type, context);
		if (field != null) {
			return (T) field;
		}

		field = super.createField(type, fieldType);
		if (field instanceof TextField) {
			((TextField) field).setNullRepresentation("");
		}
		return (T) field;
	}

}
