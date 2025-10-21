# PetriNet

A Java implementation of a Petri Net simulation system with support for various arc types including standard arcs, zero-test arcs, and reset arcs.

## Table of Contents
- [Overview](#overview)
- [Features](#features)
- [Architecture](#architecture)
- [Classes Documentation](#classes-documentation)
- [Arc Types](#arc-types)


## Overview

A Petri Net is a mathematical modeling language for the description of distributed systems. It consists of places, transitions, and arcs that connect them. Places can contain tokens, and transitions can fire when their input conditions are met, moving tokens through the network.

This implementation provides a flexible framework for creating and simulating Petri Nets with the following components:
- **Places**: Hold tokens and represent conditions or resources
- **Transitions**: Active components that can fire when enabled
- **Arcs**: Connect places to transitions and vice versa with configurable weights
- **Special Arc Types**: Support for zero-test arcs and reset arcs

## Features

-  Standard Petri Net semantics (places, transitions, weighted arcs)
-  Interactive step-by-step execution
-  Automatic deadlock detection
-  Multiple arc types:
  - Standard arcs (ArcPT, ArcTP)
  - Zero-test arcs (inhibitor arcs)
  - Reset arcs
-  Comprehensive JavaDoc documentation
-  Type-safe implementation with proper encapsulation

## Architecture

### Core Classes

```
PetriNet                    (Main simulation controller)
├── Place                   (Holds tokens)
├── Transition              (Fires when enabled)
└── Arc                     (Base class for connections)
    ├── ArcPT               (Place → Transition)
    │   ├── ZeroArc         (Activates when place is empty)
    │   └── ResetArc        (Removes all tokens)
    └── ArcTP               (Transition → Place)
```

### Class Relationships

- **PetriNet**: Manages the entire network and provides the simulation interface
- **Place**: Passive component that holds tokens
- **Transition**: Active component that fires when all input arcs are active
- **Arc**: Base class defining weighted connections
- **ArcPT**: Input arc from place to transition (consumes tokens)
- **ArcTP**: Output arc from transition to place (produces tokens)
- **ZeroArc**: Special arc active only when place has 0 tokens
- **ResetArc**: Special arc that removes all tokens from a place

## Classes Documentation

### PetriNet
Main controller class implementing `PetriNetInterface`. Manages all places, transitions, and arcs in the network.

**Key Methods:**
- `addPlace(Place)` / `removePlace(Place)` - Manage places
- `addTransition(Transition)` / `removeTransition(Transition)` - Manage transitions
- `addArcPT(ArcPT)` / `addArcTP(ArcTP)` - Add arcs
- `step()` - Execute one simulation step
- `addTokens(Place, int)` / `removeTokens(Place, int)` - Token management

### Place
Represents a place in the Petri Net that can hold tokens.

**Key Methods:**
- `getTokens()` - Returns current token count
- `addTokens(int)` - Adds tokens to the place
- `removeTokens(int)` - Removes tokens from the place

### Transition
Active component that fires when all input arcs are active.

**Key Methods:**
- `isEnabled()` - Checks if transition can fire
- `fire()` - Fires the transition (consumes inputs, produces outputs)
- `addArcPT(ArcPT)` / `addArcTP(ArcTP)` - Register arcs

### Arc
Base class for all arc types with a configurable weight.

**Key Methods:**
- `getWeight()` / `setWeight(int)` - Manage arc weight

## Arc Types

### Standard Arcs

**ArcPT (Place → Transition)**
- Input arc that consumes tokens from a place
- Active when place has ≥ weight tokens
- Consumes exactly `weight` tokens when transition fires

**ArcTP (Transition → Place)**
- Output arc that produces tokens to a place
- Always allows transition to fire
- Produces exactly `weight` tokens when transition fires

### Special Arc Types

**ZeroArc (Inhibitor Arc)**
- Extends ArcPT
- Active only when source place has exactly 0 tokens
- Does not consume any tokens

**ResetArc**
- Extends ArcPT
- Active when source place has ≥ 1 token
- Removes ALL tokens from source place (not just `weight`)

## Project Structure

```
PetriNet/
├── Arc.java                    # Base arc class
├── ArcPT.java                  # Place-to-Transition arc
├── ArcTP.java                  # Transition-to-Place arc
├── ZeroArc.java                # Zero-test (inhibitor) arc
├── ResetArc.java               # Reset arc
├── Place.java                  # Place class
├── Transition.java             # Transition class
├── PetriNet.java               # Main Petri Net implementation
├── PetriNetInterface.java      # Interface definition
└── README.md                   # This file
```

## Authors

ABBASSI Rayene and BOUZID Adam
