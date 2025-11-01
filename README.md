# Music Engine

A dynamic music library for Harmonizer 02, implemented in SuperCollider.

### Course of Action

1. Implement MusicEngine with regular functions and workout the logic
2. From there project its implementation with classes
3. Build classes

## Rules

* Unison and octave of the root are not allowed when specifying chord degrees.
* Chord note number may range from two to seven.
* Degree clashes are not allowed (m2/M2, P4/A11, etc.).
* Note clashes are not allowed (m3/A9, A4/d5, etc.).
* Diminished seconds and augmented sevenths are not allowed, since they clash with the root.
* Roots with double sharps or flats are not allowed, other than that, any chord formation can be written over any root, provided non of the chord notes reaches a third flat/sharp (B# or C, F# or Gb, F or E#, etc.). MusicEngine should be able to handle enharmonics accordingly and name all notes correctly.

## Interval Types

Depending on the degree, an interval may be diminished, minor, perfect, major or augmented. Represented, respectively, with: d, m, P, M, A.

## Allowed Intervals

| **Degree** | **Type** |
|----------|-----------------------|
| 2nd/9th | m, M, A |
| 7th/14th | d, m, M |
| 3rd/10th; 6th/13th | d, m, M, A |
| 4th/11th; 5th/12th | d, P, A |

## Verbose Syntax

Chords may be described by:
* Referencing the root note (A-G), with or without accident (#/b)
* using a leter to describe degree type (d, m, P, M, A) and
* using an integer to represent degree (2-7, 9-14)

### Common Chord Examples

```supercollider
"Dbm3P5"           // Minor triad, over Db
"FM3P5m7"          // Dominant seventh chord, over F
"BbM3P5M7M9P11M13" // Major seventh chord with added ninth, eleventh and thirteenth, over Bb
"GM2P5m7"          // Sus2 dominant seventh chord, over G
```

### Less Common Chord Examples

```supercollider
"EbP4m7"          // Quartal triad, over Eb
"D#P5M9"          // Quintal triad, over D#
"Ed3d5d7"         // German sixth in root position, over E
```

## Validity Tests

* Contains root at the start (A-G, #/b)
* Contains only the numbers 2-7, 9-14
* Contains only the letters d, m, P, M, A
* No consecutive numbers without type descriptor
* No consecutive letters without numbers
* Numbers are prefixed by valid descriptor, given the degree they represent

## Class Diagram

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
    classDef musicengine fill:#42eff5,stroke:#000, color:#000
    classDef harmonizer fill:#42c2f5,stroke:#000, color:#000
```