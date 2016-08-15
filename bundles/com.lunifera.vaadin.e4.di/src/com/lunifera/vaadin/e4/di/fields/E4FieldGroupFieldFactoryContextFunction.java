package com.lunifera.vaadin.e4.di.fields;

import org.eclipse.e4.core.contexts.ContextFunction;
import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.model.application.MApplication;
import org.osgi.service.component.annotations.Component;

import com.vaadin.data.fieldgroup.FieldGroupFieldFactory;

@Component(service = org.eclipse.e4.core.contexts.IContextFunction.class, property = {
		"service.context.key=com.vaadin.data.fieldgroup.FieldGroupFieldFactory" })
public class E4FieldGroupFieldFactoryContextFunction extends ContextFunction {

	@Override
	public Object compute(IEclipseContext context, String contextKey) {
		// add the new object to the application context
		MApplication application = context.get(MApplication.class);
		IEclipseContext appCtx = application.getContext();

		E4FieldGroupFieldFactory factory = ContextInjectionFactory.make(E4FieldGroupFieldFactory.class, appCtx);
		appCtx.set(E4FieldGroupFieldFactory.class, factory);
		appCtx.set(FieldGroupFieldFactory.class, factory);

		return factory;
	}
}
