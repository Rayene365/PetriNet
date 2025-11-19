package org.pneditor.petrinet.adapters.groupe10;

import java.util.HashMap;
import java.util.Map;

import org.pneditor.petrinet.AbstractArc;
import org.pneditor.petrinet.AbstractNode;
import org.pneditor.petrinet.AbstractPlace;
import org.pneditor.petrinet.AbstractTransition;
import org.pneditor.petrinet.PetriNetInterface;   // Target du PNE
import org.pneditor.petrinet.ResetArcMultiplicityException;
import org.pneditor.petrinet.UnimplementedCaseException;

// Ton modèle
import org.pneditor.petrinet.models.groupe10.PetriNet;
import org.pneditor.petrinet.models.groupe10.Place;
import org.pneditor.petrinet.models.groupe10.Transition;
import org.pneditor.petrinet.models.groupe10.Arc;
import org.pneditor.petrinet.models.groupe10.ArcPT;
import org.pneditor.petrinet.models.groupe10.ArcTP;
import org.pneditor.petrinet.models.groupe10.ResetArc;
import org.pneditor.petrinet.models.groupe10.ZeroArc;

public class PetriNetAdapter extends PetriNetInterface {

    // ===== Adaptee  =====
    private final PetriNet petriNet = new PetriNet();

    // correspondances GUI <-> modèle
    private final Map<AbstractPlace, Place>       placeMap      = new HashMap<>();
    private final Map<AbstractTransition, Transition> transitionMap = new HashMap<>();
    private final Map<AbstractArc, Arc>           arcMap        = new HashMap<>();

    // ---------- Places ----------

    @Override
    public AbstractPlace addPlace() {
        Place p = new Place(0);              // place vide par défaut
        petriNet.addPlace(p);

        Groupe10Place ap = new Groupe10Place(p);
        placeMap.put(ap, p);
        return ap;
    }

    @Override
    public void removePlace(AbstractPlace place) {
        Place p = placeMap.remove(place);
        if (p != null) {
            petriNet.removePlace(p);
        }
    }

    // ---------- Transitions ----------

    @Override
    public AbstractTransition addTransition() {
        Transition t = new Transition();
        petriNet.addTransition(t);

        Groupe10Transition at = new Groupe10Transition(t);
        transitionMap.put(at, t);
        return at;
    }

    @Override
    public void removeTransition(AbstractTransition transition) {
        Transition t = transitionMap.remove(transition);
        if (t != null) {
            petriNet.removeTransition(t);
        }
    }

    // ---------- Arcs réguliers ----------

    @Override
    public AbstractArc addRegularArc(AbstractNode source, AbstractNode destination)
            throws UnimplementedCaseException {

        // Place -> Transition (entrée)
        if (source instanceof AbstractPlace && destination instanceof AbstractTransition) {
            Place      p = placeMap.get((AbstractPlace) source);
            Transition t = transitionMap.get((AbstractTransition) destination);

            ArcPT arcPT = new ArcPT(p, t, 1);
            petriNet.addArcPT(arcPT);

            Groupe10Arc a = new Groupe10Arc(arcPT, source, destination);
            arcMap.put(a, arcPT);
            return a;
        }

        // Transition -> Place (sortie)
        if (source instanceof AbstractTransition && destination instanceof AbstractPlace) {
            Transition t = transitionMap.get((AbstractTransition) source);
            Place      p = placeMap.get((AbstractPlace) destination);

            ArcTP arcTP = new ArcTP(t, p, 1);
            petriNet.addArcTP(arcTP);

            Groupe10Arc a = new Groupe10Arc(arcTP, source, destination);
            arcMap.put(a, arcTP);
            return a;
        }

        throw new UnimplementedCaseException("Unsupported arc direction in groupe10 adapter");
    }

    // ---------- Arc inhibiteur (ZeroArc) ----------

    @Override
    public AbstractArc addInhibitoryArc(AbstractPlace place, AbstractTransition transition)
            throws UnimplementedCaseException {

        Place      p = placeMap.get(place);
        Transition t = transitionMap.get(transition);

        ZeroArc zeroArc = new ZeroArc(p, t, 0);      // poids ignoré dans ta classe
        petriNet.addArcPT(zeroArc);

        Groupe10Arc a = new Groupe10Arc(zeroArc, place, transition);
        arcMap.put(a, zeroArc);
        return a;
    }

    // ---------- Arc de reset (ResetArc) ----------

    @Override
    public AbstractArc addResetArc(AbstractPlace place, AbstractTransition transition)
            throws UnimplementedCaseException {

        Place      p = placeMap.get(place);
        Transition t = transitionMap.get(transition);

        ResetArc resetArc = new ResetArc(p, t, 1);   // poids logique fixé à 1
        petriNet.addArcPT(resetArc);

        Groupe10Arc a = new Groupe10Arc(resetArc, place, transition);
        arcMap.put(a, resetArc);
        return a;
    }

    // ---------- Suppression des arcs ----------

    @Override
    public void removeArc(AbstractArc arc) {
        Arc a = arcMap.remove(arc);
        if (a == null) {
            return;
        }
        if (a instanceof ArcPT) {
            petriNet.removeArcPT((ArcPT) a);
        } else if (a instanceof ArcTP) {
            petriNet.removeArcTP((ArcTP) a);
        }
    }

    // ---------- Tir des transitions ----------

    @Override
    public boolean isEnabled(AbstractTransition transition) throws ResetArcMultiplicityException {
        Transition t = transitionMap.get(transition);
        return t != null && t.isEnabled();
    }

    @Override
    public void fire(AbstractTransition transition) throws ResetArcMultiplicityException {
        Transition t = transitionMap.get(transition);
        if (t != null && t.isEnabled()) {
            t.fire();
        }
    }
}
