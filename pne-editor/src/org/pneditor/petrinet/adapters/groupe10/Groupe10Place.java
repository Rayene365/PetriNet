package org.pneditor.petrinet.adapters.groupe10;

import org.pneditor.petrinet.AbstractPlace;
import org.pneditor.petrinet.models.groupe10.Place;

public class Groupe10Place extends AbstractPlace {

    private final Place delegate;

    public Groupe10Place(Place delegate) {
        super("");          // label vide par défaut
        this.delegate = delegate;
    }

    public Place getDelegate() {
        return delegate;
    }

    @Override
    public void addToken() {
        delegate.addTokens(1);
    }

    @Override
    public void removeToken() {
        delegate.removeTokens(1);
    }

    @Override
    public int getTokens() {
        return delegate.getTokens();
    }

    @Override
    public void setTokens(int tokens) {
        int current = delegate.getTokens();
        if (tokens > current) {
            delegate.addTokens(tokens - current);
        } else if (tokens < current) {
            delegate.removeTokens(current - tokens);
        }
    }
}
