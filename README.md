# PetriNet

Implémentation Java d'un système de simulation de réseaux de Petri avec support pour différents types d'arcs, incluant les arcs standards, les arcs zéro et les arcs de remise à zéro.

## Table des matières
- [Aperçu](#aperçu)
- [Démarrage](#démarrage)
  - [Compilation](#compilation)
- [Architecture](#architecture)
- [Évolution du diagramme de classes UML](#évolution-du-diagramme-de-classes-uml)
- [Documentation des classes](#documentation-des-classes)
- [Types d'arcs](#types-darcs)

>  **Pour un référentiel complet des documents du projet**, consultez [`REFERENTIEL.md`](REFERENTIEL.md)

## Aperçu

Un réseau de Petri est un langage de modélisation mathématique pour la description de systèmes distribués. Il se compose de places, transitions et arcs qui les connectent. Les places peuvent contenir des jetons, et les transitions peuvent être franchies lorsque leurs conditions d'entrée sont satisfaites, déplaçant ainsi les jetons à travers le réseau.

Cette implémentation fournit un framework flexible pour créer et simuler des réseaux de Petri avec les composants suivants :
- **Places** : Contiennent des jetons et représentent des conditions ou des ressources
- **Transitions** : Composants actifs qui peuvent être franchis lorsqu'ils sont activés
- **Arcs** : Connectent les places aux transitions et vice versa avec des poids configurables
- **Types d'arcs spéciaux** : Support pour les arcs zero-test et les arcs de remise à zéro


## Démarrage

### Prérequis

- Java Development Kit (JDK) 8 ou supérieur
- Un IDE Java (Eclipse, IntelliJ IDEA, VS Code) ou le compilateur `javac` en ligne de commande

### Compilation

Depuis le répertoire racine du projet :

```bash
# Créer le répertoire bin
mkdir -p bin

# Compiler tous les fichiers source
javac -d bin src/petrinet/*.java

# Compiler les fichiers de test (nécessite JUnit 5)

```

### Exécuter vos propres simulations

On a créé une classe principale dans le package `petrinet` Main.java :

**Fichier : `src/petrinet/Main.java`**
```java
package petrinet;

public class Main {
    public static void main(String[] args) {
        PetriNet net = new PetriNet();
        
        Place p1 = new Place(5);
        Place p2 = new Place(0);
        Transition t = new Transition();
        
        net.addPlace(p1);
        net.addPlace(p2);
        net.addTransition(t);
        
        new ArcPT(p1, t, 1);
        new ArcTP(t, p2, 1);
        
        net.step(); // Exécution interactive
    }
}
```

Compilez et exécutez :
```bash
javac -d bin src/petrinet/Main.java
java -cp bin petrinet.Main
```

## Architecture

### Classes principales

```
PetriNet                    (Contrôleur principal de simulation)
├── Place                   (Contient des jetons)
├── Transition              (Se déclenche quand activée)
└── Arc                     (Classe de base pour les connexions)
    ├── ArcPT               (Place → Transition)
    │   ├── ZeroArc         (S'active quand la place est vide)
    │   └── ResetArc        (Retire tous les jetons)
    └── ArcTP               (Transition → Place)
```

### Relations entre classes

- **PetriNet** : Gère l'ensemble du réseau et fournit l'interface de simulation
- **Place** : Composant passif qui contient des jetons
- **Transition** : Composant actif qui se déclenche lorsque tous ses arcs d'entrée sont actifs
- **Arc** : Classe de base définissant les connexions pondérées
- **ArcPT** : Arc d'entrée de place vers transition (consomme des jetons)
- **ArcTP** : Arc de sortie de transition vers place (produit des jetons)
- **ZeroArc** : Arc spécial actif uniquement lorsque la place a 0 jetons
- **ResetArc** : Arc spécial qui retire tous les jetons d'une place

## Evolution du diagramme de classe

### Comparaison: Conception Initiale vs. Diagramme de classe final

Notre implémentation finale présente quelques différences par rapport au diagramme de conception initial :

#### **Diagramme Initial (Conception)**
Le diagramme initial montrait une architecture théorique avec :
- Une interface `PetriNetInterface` définissant le contrat
- Des classes de base `Arc`, `Place`, `Transition`
- Des spécialisations d'arcs : `ArcPT`, `ArcTP`, `ZeroArc`, `ResetArc`
- La classe principale `PetriNet` implémentant l'interface

#### **Diagramme Final (Implémentation réelle - généré avec ObjectAid)**
Le diagramme final, généré automatiquement à partir du code source, reflète l'implémentation réelle :

**Modifications et améliorations :**

1. **Structure conservée** : La hiérarchie des classes est conforme à la conception initiale
   - `Arc` comme classe de base avec l'attribut `weight`
   - `ArcPT` et `ArcTP` héritant de `Arc`
   - `ZeroArc` et `ResetArc` héritant de `ArcPT`

2. **Relations bidirectionnelles** :
   - Les arcs maintiennent des références vers les places et transitions
   - `Transition` contient des listes `ArrayList<ArcPT>` et `ArrayList<ArcTP>`
   - Auto-enregistrement des arcs lors de leur création

3. **Responsabilités clarifiées** :
   - `Place` : gestion simple des tokens (attribut `tokens`)
   - `Transition` : logique de vérification (`isEnabled()`) et d'exécution (`fire()`)
   - `PetriNet` : coordination globale et interface utilisateur (méthode `step()`)

4. **Encapsulation renforcée** :
   - Attributs `protected` dans la hiérarchie des `Arc`
   - Attributs `private` dans `PetriNet`, `Place`, et `Transition`
   - Méthodes publiques bien définies

**Points notables de l'implémentation finale :**
-  Gestion automatique des arcs (ajout à la transition lors de la construction)
-  Validation des poids (≥ 0) dans la classe `Arc`
-  Méthodes spécialisées `consume()` et `produce()` pour les différents types d'arcs
-  Scanner intégré dans `PetriNet` pour l'exécution interactive
-  Gestion des cas particuliers (deadlock, transition unique, choix multiple)

Le diagramme final confirme que l'implémentation respecte fidèlement la conception initiale tout en ajoutant les détails nécessaires au bon fonctionnement du système.

## Documentation des classes

### PetriNet
Classe contrôleur principale implémentant `PetriNetInterface`. Gère toutes les places, transitions et arcs du réseau.

**Méthodes principales :**
- `addPlace(Place)` / `removePlace(Place)` - Gérer les places
- `addTransition(Transition)` / `removeTransition(Transition)` - Gérer les transitions
- `addArcPT(ArcPT)` / `addArcTP(ArcTP)` - Ajouter des arcs
- `step()` - Exécuter une étape de simulation
- `addTokens(Place, int)` / `removeTokens(Place, int)` - Gestion des jetons (la suppression lève une exception si elle rendrait la place négative)

### Place
Représente une place dans le réseau de Petri qui peut contenir des jetons.

**Méthodes principales :**
- `getTokens()` - Retourne le nombre actuel de jetons
- `addTokens(int)` - Ajoute des jetons à la place
- `removeTokens(int)` - Retire des jetons et lève `IllegalArgumentException` si l'opération rendrait le compte négatif

### Transition
Composant actif qui se déclenche lorsque tous ses arcs d'entrée sont actifs.

**Méthodes principales :**
- `isEnabled()` - Vérifie si la transition peut se déclencher
- `fire()` - Déclenche la transition (consomme les entrées, produit les sorties)
- `addArcPT(ArcPT)` / `addArcTP(ArcTP)` - Enregistrer des arcs

### Arc
Classe de base pour tous les types d'arcs avec un poids configurable.

**Méthodes principales :**
- `getWeight()` / `setWeight(int)` - Gérer le poids de l'arc

## Types d'arcs

### Arcs standards

**ArcPT (Place → Transition)**
- Arc d'entrée qui consomme des jetons d'une place
- Actif lorsque la place a ≥ poids jetons
- Consomme exactement `poids` jetons lors du franchissement de la transition

**ArcTP (Transition → Place)**
- Arc de sortie qui produit des jetons vers une place
- Permet toujours à la transition de se déclencher
- Produit exactement `poids` jetons lors du franchissement de la transition

### Types d'arcs spéciaux

**ZeroArc (Arc inhibiteur)**
- Étend ArcPT
- Actif uniquement lorsque la place source a exactement 0 jetons
- Ne consomme aucun jeton

**ResetArc**
- Étend ArcPT
- Actif lorsque la place source a ≥ 1 jeton
- Retire TOUS les jetons de la place source (pas seulement `poids`)

## Structure du projet

```
PetriNet/
├── src/                            # Répertoire du code source
│   └── petrinet/                   # Package principal
│       ├── Arc.java                    # Classe de base des arcs
│       ├── ArcPT.java                  # Arc Place vers Transition
│       ├── ArcTP.java                  # Arc Transition vers Place
│       ├── ZeroArc.java                # Arc zero-test (inhibiteur)
│       ├── ResetArc.java               # Arc de remise à zéro
│       ├── Place.java                  # Classe Place
│       ├── Transition.java             # Classe Transition
│       ├── PetriNet.java               # Implémentation principale
│       └── PetriNetInterface.java      # Définition de l'interface
├── test/                           # Répertoire des tests
│   └── petrinet/                   # Package de tests
│       ├── PetriNetActivationTest.java    # Tests d'activation
│       ├── PetriNetCreationTest.java      # Tests de création
│       ├── PetriNetDestructionTest.java   # Tests de destruction
│       └── PetriNetDisplayTest.java       # Tests d'affichage
├── Diagrammes/                     # Diagrammes UML
│   ├── UML classe.png                  # Diagramme de classes final
│   └── UML classe.ucls                 # Fichier ObjectAid
├── docs/                           # Documentation
│   ├── README.md                       # Documentation détaillée
│   └── plan_de_tests.md                # Plan de tests
├── bin/                            # Classes compilées (généré)
├── .classpath                      # Configuration Eclipse
├── .project                        # Projet Eclipse
└── README.md                       # Ce fichier
```

## Auteurs

**ABBASSI Rayene** et **BOUZID Adam**  


---

*Projet développé dans le cadre du cours de MAPD*
