package com.organization4242.tictactoe.framework;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public abstract class Screen implements PropertyChangeListener {
    private PropertyChangeSupport pce = new PropertyChangeSupport(this);

    public Screen(Game game) {}

	public abstract void update(float deltaTime);
	
	public abstract void present(float deltaTime);
	
	public abstract void pause();
	
	public abstract void resume();
	
	public abstract void dispose();

    public void addListener(PropertyChangeListener listener) {
        pce.addPropertyChangeListener(listener);
    }

    public void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
        pce.firePropertyChange(propertyName, oldValue, newValue);
    }
}
