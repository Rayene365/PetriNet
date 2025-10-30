# Plan de tests PetriNet

Ce document complete les sections 7.2 et 7.3 du plan de test pour le reseau de Petri. Les cas listes renvoient directement aux tests JUnit implementes dans `test`.

## 7.2 Specification des tests - operations structurelles

| Fonction | Entrees | Cas | Code (ID) | Resultat Attendu |
|----------|---------|-----|-----------|------------------|
| Suppression de jetons | Place avec 4 jetons | Appel de `removeTokens(P, 3)` | SJP-01 | P conserve 1 jeton (`PetriNetDestructionTest.removeTokensUpdatesPlace`) |
| Suppression de jetons | Arc PT de poids 2, P=2 | `transition.fire()` | SJP-02 | P perd 2 jetons (`PetriNetActivationTest.transitionFireMovesTokensToOutputs`) |
| Creation d'Arc PT | Couple P/T rattache | `addArcPT(new ArcPT(P,T,2))` | CAR-01 | Transition liste l'arc unique (`PetriNetCreationTest.arcPTRegistersOnTransition`) |
| Creation d'Arc TP | Couple T/P rattache | `addArcTP(new ArcTP(T,P,4))` | CAR-02 | Transition liste l'arc unique (`PetriNetCreationTest.arcTPRegistersOnTransition`) |
| Arc PT en double | ArcPT(P,T,1) deja present | Ajout d'une nouvelle instance | GAD-01 | Transition ne garde qu'un exemplaire (`PetriNetCreationTest.duplicateArcPTIsRejectedAndNotKeptOnTransition`) |
| Arc TP en double | ArcTP(T,P,1) deja present | Ajout d'une nouvelle instance | GAD-02 | Transition ne garde qu'un exemplaire (`PetriNetCreationTest.duplicateArcTPIsRejectedAndNotKeptOnTransition`) |

## 7.3 Specification des tests - activation des transitions

| Avant | Code (ID) | Apres |
|-------|-----------|-------|
| P=1, arc poids 2 | TSA-01 | `isEnabled` devient vrai uniquement quand P>=2 puis consommation de 2 jetons (`PetriNetActivationTest.simpleTransitionRequiresSufficientTokens`) |
| P=0, arc poids 1 | TSA-02 | `fire()` leve `IllegalStateException` et l'etat reste identique (`PetriNetActivationTest.fireFailsWhenTransitionNotEnabled`) |
| P=3, arc PT poids 2, arc TP poids 1 | TSA-03 | P=1 et sortie +1 jeton (`PetriNetActivationTest.transitionFireMovesTokensToOutputs`) |
| ZeroArc sur place vide, ResetArc sur place non vide | TSA-04 | Tir autorise, place reset videe, sortie +1 jeton (`PetriNetActivationTest.zeroAndResetArcsAffectEnabling`) |
| P0=1, P1 progresse 0->2 (poids 1 et 2) | TAM-01 | Tir possible seulement quand P1 atteint 2, consommation totale (`PetriNetActivationTest.multiInputTransitionNeedsAllPlaces`) |
| Meme scenario avec arc TP poids 3 vers P2 | TAM-02 | P0=P1=0 et P2 gagne 3 jetons (`PetriNetActivationTest.multiInputTransitionNeedsAllPlaces`) |
