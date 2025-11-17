# Référentiel de documents - Projet PetriNet

Ce document centralise l'ensemble des ressources et documents du projet pour faciliter la navigation et la compréhension.

##  Documentation principale

| Document | Emplacement | Description |
|----------|-------------|-------------|
| **README principal** | [`README.md`](README.md) | Vue d'ensemble du projet, guide de démarrage et architecture  |
| **Documentation technique** | [`docs/README.md`](docs/README.md) | Guide détaillé pour l'exécution des tests JUnit avec Eclipse et EclEmma |
| **Plan de tests** | [`docs/plan_de_tests.md`](docs/plan_de_tests.md) | Spécification complète des tests  |

##  Diagrammes UML

| Document | Emplacement | Description |
|----------|-------------|-------------|
| **Diagramme de classes** | [`Diagrammes/UML classe.png`](Diagrammes/UML%20classe.png) | Diagramme UML généré avec ObjectAid depuis l'implémentation finale |
| **Fichier ObjectAid** | [`Diagrammes/UML classe.ucls`](Diagrammes/UML%20classe.ucls) | Fichier source du diagramme (éditable dans Eclipse avec ObjectAid) |

##  Tests

Tous les tests sont dans le package `test/petrinet/` et utilisent JUnit 5.

| Fichier de test | Description | Cas couverts |
|-----------------|-------------|--------------|
| **PetriNetCreationTest.java** | Tests de création | Places, transitions, arcs (PT/TP), gestion des doublons |
| **PetriNetActivationTest.java** | Tests d'activation | Transitions simples/multiples, arcs Zero/Reset, conditions d'activation |
| **PetriNetDestructionTest.java** | Tests de destruction | Suppression d'arcs, de jetons, validation des exceptions |
| **PetriNetDisplayTest.java** | Tests d'affichage | Rendu textuel du réseau (méthode `describe()`) |

## 📝 Code source

Le code source est organisé dans le package `src/petrinet/` :

### Classes principales

| Classe | Rôle | Responsabilités |
|--------|------|----------------|
| **PetriNet** | Contrôleur principal | Gestion du réseau, exécution interactive (`step()`) |
| **PetriNetInterface** | Interface | Définition du contrat pour les réseaux de Petri |
| **Place** | Conteneur de jetons | Stockage et gestion des jetons |
| **Transition** | Composant actif | Vérification d'activation, franchissement |


##  Structure du projet

```
PetriNet/
├── src/petrinet/              # Code source (9 classes Java)
├── test/petrinet/             # Tests JUnit 5 (4 fichiers)
├── Diagrammes/                # Diagrammes UML
├── docs/                      # Documentation détaillée
├── bin/                       # Classes compilées (généré)
├── .classpath, .project       # Configuration Eclipse
├── README.md                  # Documentation principale
└── REFERENTIEL.md             # Ce fichier
```

##  Contexte du projet

- **Auteurs** : ABBASSI Rayene et BOUZID Adam
- **Institution** : IMT Atlantique
- **Cours** : MAPD 
- **Objectif** : Implémentation d'un système de simulation de réseaux de Petri


---

