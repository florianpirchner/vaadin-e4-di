package com.lunifera.vaadin.e4.di.fields;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.eclipse.e4.core.contexts.IEclipseContext;

import com.lunifera.vaadin.e4.di.api.fields.IBeanFieldCache;
import com.vaadin.ui.Field;
import com.vaadin.ui.Grid;
import com.vaadin.ui.TextField;

/**
 * Should be used for grid editors.
 */
@SuppressWarnings("serial")
@Singleton
public class E4GridEditorFieldGroupFieldFactory extends Grid.EditorFieldFactory {

	@Inject
	IBeanFieldCache fieldCache;

	@Inject
	IEclipseContext context;

	@SuppressWarnings({ "rawtypes", "unchecked" })
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
