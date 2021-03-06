package com.organization4242.tictactoe.controller;

import com.organization4242.tictactoe.model.AbstractModel;
import com.organization4242.tictactoe.view.AbstractView;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ilya-murzinov on 07.03.14.
 */
public abstract class AbstractController implements PropertyChangeListener {
    private List<AbstractView> registeredViews;
    private List<AbstractModel> registeredModels;

    public AbstractController() {
        registeredViews = new ArrayList<AbstractView>();
        registeredModels = new ArrayList<AbstractModel>();
    }

    public void addModel(AbstractModel model) {
        registeredModels.add(model);
        model.addPropertyChangeListener(this);
    }

    public void removeModel(AbstractModel model) {
        registeredModels.remove(model);
        model.removePropertyChangeListener(this);
    }

    public void addView(AbstractView view) {
        registeredViews.add(view);
        view.addPropertyChangeListener(this);
    }

    public void removeView(AbstractView view) {
        registeredViews.remove(view);
        view.removePropertyChangeListener(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent pce) {
        if (pce.getSource() instanceof AbstractModel) {
            for (AbstractView view : registeredViews) {
                view.modelPropertyChange(pce);
            }
        } else if (pce.getSource() instanceof AbstractView) {
            for (AbstractModel model : registeredModels) {
                model.viewPropertyChange(pce);
            }
        }
    }
}