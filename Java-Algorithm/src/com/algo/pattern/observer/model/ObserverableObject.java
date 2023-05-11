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
	 * 添加Observer
	 */
    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }
    /**
     * 
     * 移除Observer
     */
    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        support.removePropertyChangeListener(pcl);
    }

    /**
     * 
     * 当被观察的ObservableObject属性变化，通知Observers
     */
    public void setNews(String value) {
    	// 1. name of the observed property.
    	// 2. old value
    	// 3. new value
        support.firePropertyChange("news", this.news, value);
        this.news = value;
    }
}
