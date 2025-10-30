```mermaid
classDiagram
    class MENote {
        +int midi
        +int octave
        +str name
        +str accident
        +str degree
        +new()
    }

    class MENoteRange {
        +array~MENote~ range
        +new()
    }

    class MESymbol {
        +str Symbol
        +MENote root
        +new()
    }
```