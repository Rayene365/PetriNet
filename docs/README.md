# Tests JUnit du projet PetriNet

Ce repertoire decrit l'organisation des tests automatise et la procedure pour les lancer depuis Eclipse et EclEmma sans Maven.

## Organisation
- `test/PetriNetDisplayTest.java` : verifie le rendu textuel du reseau.
- `test/PetriNetCreationTest.java` : couvre la creation des places, transitions et arcs (y compris la gestion du doublonnage).
- `test/PetriNetActivationTest.java` : valide l'activation des transitions simples, l'utilisation des arcs Zero/Reset et les transitions a entrees multiples.
- `test/PetriNetDestructionTest.java` : controle la destruction des arcs, des elements rattaches et la suppression de jetons.

Les cas de tests sont aligne avec le plan de test documente dans `docs/plan_de_tests.md`.

## Preparation dans Eclipse
1. Importer le projet comme `Existing Projects into Workspace`.
2. Dans les proprietes du projet, ajouter `src` et `test` comme dossiers source (`Java Build Path > Source`).
3. Ajouter la bibliotheque `JUnit 5` au classpath via `Java Build Path > Libraries > Add Library... > JUnit`.

## Execution avec EclEmma
1. Faire un clic droit sur la classe de test ou sur le dossier `test`.
2. Choisir `Coverage As > JUnit Test`.
3. EclEmma produira un rapport de couverture directement dans Eclipse.
