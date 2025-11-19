package org.pneditor.petrinet.adapters.groupe10;

import org.pneditor.petrinet.AbstractTransition;
import org.pneditor.petrinet.models.groupe10.Transition;

public class Groupe10Transition extends AbstractTransition {

    private final Transition delegate;

    public Groupe10Transition(Transition delegate) {
        super("");          // label vide par défaut
        this.delegate = delegate;
    }

    public Transition getDelegate() {
        return delegate;
    }
}
