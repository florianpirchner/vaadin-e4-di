package com.lunifera.vaadin.e4.di.api.fields;

import com.vaadin.event.SelectionEvent.SelectionListener;
import com.vaadin.ui.Grid;

public interface IGridComponent {

	/**
	 * This property in the eclipse context indicates, that the grid component
	 * should run with a JPAContainer. Otherwise, a BeanItemContainer is used.
	 * If collection properties from main data are bound to this component, a
	 * JPAContainer must not be used. Eg. <code>person#children</code> should be
	 * visualized in the grid, where the #children collection is provided by a
	 * person bean.<br>
	 * If the property is missing a BeanItemContainer is used.<br>
	 * Type of value is <code>Boolean</code>.
	 */
	String CONTEXT_PROPERTY__JPA_MODE = "jpaContainerModel";

	void intialize();

	void addSelectionListener(SelectionListener listener);

	void removeSelectionListener(SelectionListener listener);

	/**
	 * Returns the grid.
	 * 
	 * @return
	 */
	Grid getGrid();

	void dispose();

}