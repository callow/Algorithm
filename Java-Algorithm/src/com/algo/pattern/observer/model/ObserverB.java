package com.algo.pattern.observer.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
/**
 * 
 * π€≤Ï’ﬂB
 *
 */
public class ObserverB implements PropertyChangeListener {
	
	private String news;

	@Override
	public void propertyChange(PropertyChangeEvent event) {
		 this.setNews((String) event.getNewValue());
		
	}
	
	public String getNews() {
		return news;
	}

	public void setNews(String news) {
		this.news = news;
	}

}
