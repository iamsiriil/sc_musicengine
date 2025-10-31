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
    class MENotes {
        +int midi
        +int octave
        +str name
        +str accident
        +str degree
        +int chordId
        +int voiceId
        +new()
    }

    class MENoteRanges {
        +MENotes[] range
        +new()
    }

    class MEIntervalData {
        -dictionary intervals
        +getIntervalDict()
        +getInterval()
    }

    class MESymbols {
        +str symbol
        +str root
        +new()
    }

    class MEChords {
        +MENotes root
        +MENotes bass
        +MENotes top
        +bool retardation
        +bool anticipation
        +MERules ruleProfile
        +MENotes[] chord
        +new()
    }

    class MEProgression {
        +MEChords[] progression
        +new()
    }

    classDef musicengine stroke:#42f5bf;
    classDef harmonizer stroke:#42b3f5;
    class MENotes musicengine;
    class MENoteRanges musicengine;
    class MEIntervalData musicengine;
    class MESymbols musicengine;
    class MEChords musicengine;
    class MEProgression harmonizer;
```