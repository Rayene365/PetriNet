# Plan de tests PetriNet

Ce document complete les sections 7.2 et 7.3 du plan de test pour le reseau de Petri. Les cas listes renvoient directement aux tests JUnit implementes dans `test`.

## 7.2 Specification des tests - operations structurelles

### Suppression de jetons d'une place
| ID | Objectif | Pre-conditions | Actions | Resultats attendus | Tests automatise |
|----|----------|----------------|---------|--------------------|------------------|
| SJP-01 | Verifier la decrementation du marquage | Place P initialisee a 4 jetons et enregistre dans le reseau | Appeler `removeTokens(P, 3)` | Le marquage final de P doit etre egal a 1 jeton | `PetriNetDestructionTest.removeTokensUpdatesPlace` |
| SJP-02 | Assurer la coherence lors d'une consommation via arc | Transition T connectee a P avec un arc PT de poids 2 et P initialise a 2 jetons | Appeler `transition.fire()` | P perd deux jetons et passe a 0 | `PetriNetActivationTest.transitionFireMovesTokensToOutputs` |

### Creation d'arcs
| ID | Objectif | Pre-conditions | Actions | Resultats attendus | Tests automatise |
|----|----------|----------------|---------|--------------------|------------------|
| CAR-01 | Enregistrer un arc PT et sa navigabilite | P et T crees et rattaches au reseau | Construire un `ArcPT(P, T, 2)` puis `addArcPT` | L'arc est present dans la liste des entrees de T et le reseau conserve une seule entree | `PetriNetCreationTest.arcPTRegistersOnTransition` |
| CAR-02 | Enregistrer un arc TP et sa navigabilite | T et P crees et rattaches au reseau | Construire un `ArcTP(T, P, 4)` puis `addArcTP` | L'arc est present dans la liste des sorties de T | `PetriNetCreationTest.arcTPRegistersOnTransition` |

### Gestion des arcs doubles
| ID | Objectif | Pre-conditions | Actions | Resultats attendus | Tests automatise |
|----|----------|----------------|---------|--------------------|------------------|
| GAD-01 | Empecher l'ajout multiple d'un meme arc PT | ArcPT(P, T, 1) deja present dans le reseau | Appeler `addArcPT` avec le meme couple P->T (nouvelle instance) | Le deuxieme arc est rejete et la transition ne garde qu'une entree | `PetriNetCreationTest.duplicateArcPTIsRejectedAndNotKeptOnTransition` |
| GAD-02 | Empecher l'ajout multiple d'un meme arc TP | ArcTP(T, P, 1) deja present | Appeler `addArcTP` avec le meme couple T->P | Le second arc est supprime immediatement de la transition et n'apparait pas dans la description | `PetriNetCreationTest.duplicateArcTPIsRejectedAndNotKeptOnTransition` |

## 7.3 Specification des tests - activation des transitions

### Transitions simples
| ID | Transition | Conditions d'entree | Stimuli | Resultats attendus | Tests automatise |
|----|------------|--------------------|---------|--------------------|------------------|
| TSA-01 | Consommation standard | P contient 1 jeton, arc de poids 2 | Ajouter 1 jeton suppl. puis evaluer | `isEnabled` devient vrai uniquement lorsque P >= 2 et la consommation retire 2 jetons | `PetriNetActivationTest.simpleTransitionRequiresSufficientTokens` |
| TSA-02 | Tir non autorise | P contient 0 jeton, arc de poids 1 | Appeler `fire()` | Une exception `IllegalStateException` est levee et le marquage reste inchange | `PetriNetActivationTest.fireFailsWhenTransitionNotEnabled` |
| TSA-03 | Production vers une sortie | P contient 3 jetons, arc PT de poids 2 et arc TP de poids 1 | Appeler `fire()` | P perd 2 jetons et la place de sortie gagne 1 jeton | `PetriNetActivationTest.transitionFireMovesTokensToOutputs` |
| TSA-04 | Arcs speciaux Zero/Reset | ZeroArc sur place vide, ResetArc sur place non vide | Mettre la place du ZeroArc a 0 jeton puis `fire()` | Le ZeroArc autorise le tir, le ResetArc vide la place et la sortie recoit 1 jeton | `PetriNetActivationTest.zeroAndResetArcsAffectEnabling` |

### Transitions a entrees multiples
| ID | Transition | Conditions d'entree | Stimuli | Resultats attendus | Tests automatise |
|----|------------|--------------------|---------|--------------------|------------------|
| TAM-01 | Synchronisation de plusieurs places | Transition connectee a P0 (poids 1) et P1 (poids 2) | Alimenter progressivement P1 (0 -> 1 -> 2 jetons) | `isEnabled` reste faux tant que P1 < 2, devient vrai a 2, puis `fire()` consomme 1 jeton sur P0 et 2 jetons sur P1 | `PetriNetActivationTest.multiInputTransitionNeedsAllPlaces` |
| TAM-02 | Production multi-entrees | Meme configuration que TAM-01 + arc TP (poids 3) vers P2 | Declencher `fire()` une fois les pre-conditions reunies | P0 et P1 sont vides, P2 recoit 3 jetons | `PetriNetActivationTest.multiInputTransitionNeedsAllPlaces` (aspects post-conditions) |
