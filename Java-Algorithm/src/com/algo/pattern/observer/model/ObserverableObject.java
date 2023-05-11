package com.algo.pattern.observer.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ObserverableObject {
	
	private String news;
	private PropertyChangeSupport support;
	
	public ObserverableObject() {
        support = new PropertyChangeSupport(this);
    }
	
	/**
	 * 
	 * ���Observer
	 */
    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }
    /**
     * 
     * �Ƴ�Observer
     */
    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        support.removePropertyChangeListener(pcl);
    }

    /**
     * 
     * �����۲��ObservableObject���Ա仯��֪ͨObservers
     */
    public void setNews(String value) {
    	// 1. name of the observed property.
    	// 2. old value
    	// 3. new value
        support.firePropertyChange("news", this.news, value);
        this.news = value;
    }
}
