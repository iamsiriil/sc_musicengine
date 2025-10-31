# Music Engine

A dynamic chord library for SuperCollider

## Something

* Interval clashes are not allowed (m2/M2, P4/A11, etc.)
* Note clashes are not allowed (m3/A9, A4/d5, etc.)
* Diminished seconds and augmented sevenths are not allowed, since they clash with the root.

```mermaid
classDiagram
    MENotes <|-- MENoteRanges
    MENoteRanges o-- MEChords
    MEChords <|-- MEProgression
    class MENotes:::musicengine {
        +int midi
        +int octave
        +str name
        +str accident
        +str degree
        +int chordId
        +int voiceId
        +new()
    }
    %%style MENotes stroke:#42f5bf,fill:#e6fff8

    class MENoteRanges:::musicengine {
        +MENotes[] range
        +new()
    }

    class MEIntervalData:::musicengine {
        -dictionary intervals
        +getIntervalDict()
        +getInterval()
    }

    class MESymbols:::musicengine {
        +str symbol
        +str root
        +new()
    }

    class MEChords:::harmonizer {
        +MENotes root
        +MENotes bass
        +MENotes top
        +bool retardation
        +bool anticipation
        +MERules ruleProfile
        +MENotes[] chord
        +new()
    }

    class MEProgression:::harmonizer {
        +MEChords[] progression
        +new()
    }
    classDef musicengine fill:#daf542,stroke:#000, color:#000
    classDef harmonizer fill:#f5d442,stroke:#000, color:#000
```