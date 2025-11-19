package org.pneditor.petrinet.adapters.groupe10;

import org.pneditor.petrinet.AbstractArc;
import org.pneditor.petrinet.AbstractNode;
import org.pneditor.petrinet.ResetArcMultiplicityException;
import org.pneditor.petrinet.models.groupe10.Arc;
import org.pneditor.petrinet.models.groupe10.ResetArc;
import org.pneditor.petrinet.models.groupe10.ZeroArc;

public class Groupe10Arc extends AbstractArc {

    private final Arc delegate;
    private final AbstractNode source;
    private final AbstractNode destination;

    public Groupe10Arc(Arc delegate, AbstractNode source, AbstractNode destination) {
        this.delegate = delegate;
        this.source = source;
        this.destination = destination;
    }

    public Arc getDelegate() {
        return delegate;
    }

    @Override
    public AbstractNode getSource() {
        return source;
    }

    @Override
    public AbstractNode getDestination() {
        return destination;
    }

    @Override
    public boolean isReset() {
        return delegate instanceof ResetArc;
    }

    @Override
    public boolean isRegular() {
        // ni reset, ni inhibiteur
        return !(delegate instanceof ResetArc) && !(delegate instanceof ZeroArc);
    }

    @Override
    public boolean isInhibitory() {
        return delegate instanceof ZeroArc;
    }

    @Override
    public int getMultiplicity() throws ResetArcMultiplicityException {
        return delegate.getWeight();
    }

    @Override
    public void setMultiplicity(int multiplicity) throws ResetArcMultiplicityException {
        delegate.setWeight(multiplicity);
    }
}
