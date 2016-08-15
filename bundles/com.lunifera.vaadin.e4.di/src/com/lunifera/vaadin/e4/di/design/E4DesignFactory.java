package com.lunifera.vaadin.e4.di.design;

import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;

import com.vaadin.ui.Component;
import com.vaadin.ui.declarative.Design;
import com.vaadin.ui.declarative.Design.ComponentFactory;
import com.vaadin.ui.declarative.DesignContext;
import com.vaadin.ui.declarative.DesignException;

@SuppressWarnings("serial")
public class E4DesignFactory {

	private static final Object LOCK = new Object();

	/**
	 * Creates a new instance of the rootComponent and ensure proper
	 * classloading and context injection.
	 * 
	 * @param rootComponent
	 * @param context
	 * @return
	 * @throws DesignException
	 */
	public static <T extends Component> T make(Class<T> rootComponent, IEclipseContext context)
			throws DesignException {
		synchronized (LOCK) {
			Design.ComponentFactory oldFactory = Design.getComponentFactory();
			try {
				Design.setComponentFactory(new DesignComponentFactory(rootComponent.getClassLoader(), context));
				return ContextInjectionFactory.make(rootComponent, context);
			} finally {
				Design.setComponentFactory(oldFactory);
			}
		}
	}

	/**
	 * This {@link ComponentFactory} will use the classloader of the root
	 * component and the eclipseContext to create new instances of the design.
	 */
	public static class DesignComponentFactory implements ComponentFactory {

		private final ClassLoader classLoader;
		private final IEclipseContext eclipseContext;

		public DesignComponentFactory(ClassLoader classLoader, IEclipseContext eclipseContext) {
			this.classLoader = classLoader;
			this.eclipseContext = eclipseContext;
		}

		@Override
		public Component createComponent(String fullyQualifiedClassName, DesignContext context) {
			Class<? extends Component> componentClass = resolveComponentClass(fullyQualifiedClassName, context);

			assert Component.class.isAssignableFrom(componentClass) : "resolveComponentClass returned " + componentClass
					+ " which is not a Vaadin Component class";

			try {
				return ContextInjectionFactory.make(componentClass, eclipseContext);
			} catch (Exception e) {
				throw new DesignException("Could not create component " + fullyQualifiedClassName, e);
			}
		}

		protected Class<? extends Component> resolveComponentClass(String qualifiedClassName, DesignContext context) {
			try {
				Class<?> componentClass = classLoader.loadClass(qualifiedClassName);
				return componentClass.asSubclass(Component.class);
			} catch (ClassNotFoundException e) {
				throw new DesignException("Unable to load component for design", e);
			}
		}

	}
}
