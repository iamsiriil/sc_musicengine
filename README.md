# MusicEngine

__MusicEngine__ is a dynamic music-theory based library for SuperCollider that generates note ranges for chords, scales and custom harmonic structures.

It produces collections of __MENote__ objects spanning the full MIDI range (_0-127_, octaves _-1_ to _9_). Each __MENote__ encapsulates data: MIDI note number, frequency value, note name, degree symbol, user assigned data, etc.

Ranges are defined via a verbose syntax, where all intervals are descriminated (e.g.: _"F#m3P5m7"_) or via conventional aliases (e.g.: _"F#-7"_ -> _"F#m3P5m7"_). The user may extend the system by registering custom aliases that persist across sessions.

__MusicEngine__ is currently in its `version 0.2.0-beta`.

## Key Features and Recent Improvements

* Fully documented interface (__MENoteRange__, __MERange__, __MENote__, __MERegister__) with help files.

* Updated guide for [Range Symbols](https://github.com/iamsiriil/sc_musicengine/wiki/Range-Symbols).

* Custom aliases via __MERagister__ (saved permanently using SuperCollider's __Archive__ class).

* __MENoteRange__ behaves as a trully SequenceableCollection.

* Most intance methods return a new __MENoteRange__, which allows for methods to be chained.

* Powerful trimming, filtering and transposition (with operator shorthands).

* Arbitrary user-defined data can be set to notes as key-value pairs (durations, amplitudes, etc.).

* Supports two to twelve degrees (root note always counts as `\P1`).

### Basic Usage

```supercollider
// Simple major triad over F#
r = MENoteRange.new("F#");

// Dominant 7th chord
r = MENoteRange.new("F#7");        // or "F#Dom7" or "F#M3P5m7"

// Minor scale (full diatonic)
r = MENoteRange.new("F#m");        // alias for natural minor

// Custom verbose range (e.g., major 9th chord)
r = MENoteRange.new("F#M3P5M7M9");

// Inspect the range
r.postln;                          // MENoteRange[F#-1:P1, A#-1:m3, C#0:P5, ...]
r.size;                            // Total notes across all octaves
r[0];                              // First MENote
```

## Creating and Using Ranges

### 1. Aliases (Recommended for common chords/scales)

MusicEngine includes dozens of predefined aliases. A single root note (e.g.: _"F#"_, _"C"_, etc.) defaults to the major triad.

#### Common chord examples

```supercollider
MENoteRange.new("F#");    // Major triad, over F#
MENoteRange.new("F#5");   // Power chord, over F#
MENoteRange.new("F#-");   // Minor triad, over F#
MENoteRange.new("F#7");   // Dominant 7th chord, over F#
MENoteRange.new("F#^9");  // 9th chord with Major 7th, over F#
MENoteRange.new("F#-13"); // Minor 7th, with major 9th, perfect 11th and major 13th, over F#
```

#### Scale examples

```supercollider
MENoteRange.new("F#Mp");     // Major pentatonic, over F#
MENoteRange.new("F#Blues");  // Blues scale, over F#
MENoteRange.new("F#Io");     // Ionian mode, over F#
MENoteRange.new("F#ph");     // Phrygian mode, over F#
MENoteRange.new("F#Protus"); // Meddieval D mode (dorian), over F#
MENoteRange.new("F#OA1");    // Octatonic scale starting with A1, over F#
MENoteRange.new("F#OM2");    // Octatonic scale starting with M2, over F#
MENoteRange.new("F#C");      // Chromatic scale, over F#
```

> [!NOTE]
> For more information the aliases available and their verbose equivalents, see the [Range Symbols](https://github.com/iamsiriil/sc_musicengine/wiki/Range-Symbols) wiki page.

### 2. Verbose Syntax 

The verbose syntax allows the user to generate a note range by explicitly listing every degree after the root (e.g.: _"F#m3P5m7"_).

```supercollider
// Power chord, over F#
MENoteRange.new("F#P5");

// 13th chord with dominant 7th, over F#
MENoteRange.new("F#M3P5m7M9P11M13");

// Chromatic scale, over F#
MENoteRange.new("F#m2M2m3M3P4d5P5m6M6m7M7");

// Hybrid chord, over F#
MENoteRange.new("F#M3m6m7A9");
```

#### Rules summary (full details in [wiki](https://github.com/iamsiriil/sc_musicengine/wiki/Range-Symbols)):

* _2-11_ intervals after root (root is always _P1_).
* Qualities: _d_ (diminished), _m_ (minor), _P_ (perfect), _M_ (major), _A_ (augmented).
* No duplicate degrees, no enharmonic conflicts (e.g.: _m3_ and _A9_, or _A4_ with _d5_, etc.).
* No _d2_ or _A7_ (enharmonic to root).
* Root must consist of letter _A-G_ with 0 to 3 accidentals (_#_ or _b_). MusicEngine resolves up to 5 accidentals, but root may only take 3.

### 3. Custom Aliases (Persistent)

```supercollider
MERegister.newEntry('Toby', "M3d5M6"); // No root needed

// Use anywhere
r = MENoteRange.new("F#Toby");
```

## Advanced Operations

### Indexing and Slicing

Most conventonal methods for indexing and slicing are available:

```supercollider
r = MENoteRange.new("F#-7");

r @ 10;                   // Index at 10
r[10];                    // Index at 10
r[10..20];                // Range slice
r[10,2..40];              // Arithmetic series
r.copyRange(10, 20);
r.copySeries(10, 2, 40);
```

### Trimming

Double-ended or single-ended, by octave (_O_), MIDI (_M_), by frequency (_F_),  by degree (_D_) or note name (_N_):

```supercollider
r = MENoteRange.new("F#-7");

r.trimO(4, 8);                    // Octaves 4–8
r.trimF(500, 1000);               // Frequency window
r.trimD(\P1, \P1);                // From first P1 to last P1

// Single-ended
r.bTrimM(60);                     // MIDI ≥ 60
r.tTrimN("E");                    // End at last "E"

// Operators (very readable)
r |> 500.0 |> \P1;                // Bottom trim freq + degree
r <| 1000.0 <| "F#";              // Top trim
r >< [4, 6] >< ["F#", "E"];       // Double-ended trim


// Note names with octave are unique and may be used
r |> "F#4" <| "A6"
```

### Filtering

Remove notes that match degree of note name (with or without octave):

```supercollider
r.filterD(\P1);                   // Remove all roots
r.filterN("F#");                  // Remove all F# (any octave)
r.filterN("F#4");                 // Exact note

// Operator
r | \P1 | "E" | "A4";
```


### Spanning (for extended chords)

`span` spreads degrees > 7 across two octaves:

```supercollider
r = MENoteRange.new("F#-13")
  .trimO(4, 6)
  .trimD(\P1, \P1)
  .span;
// Result: F#4:P1, A4:m3, C#5:P5, E5:m7, G#5:M9, B5:P11, D#6:M13, F#6:P1
```

### Transposition

```supercollider
r = MENoteRange.new("F#-7")[20..25];

r >> \M2;     // Up major 2nd (interval symbol)
r >> 4;       // Up 4 semitones (MIDI offset)
r << \M2;     // Down
```

### Adding Data to Notes

```supercollider
r = MENoteRange.new("F#-7")[20..25];

r.setValue(\dur, 0.25);                    // Same value for all
r.setValues(\amp, [0.2, 0.3, 0.4, 0.5, 0.6, 0.7]);
r.setFunc(\pan, { rrand(-1.0, 1.0) });

r.getValues(\dur);
```

Practical playback example:

```supercollider
(
r = MENoteRange.new("Cm9")
    .trimO(3, 5)
    .setFunc(\dur, { rrand(0.1, 0.4) })
    .setFunc(\amp, { rrand(0.1, 0.8) });

Pbind(
    \midinote, r.asPseq(\midi),
    \dur, r.asPseq(\dur),
    \amp, r.asPseq(\amp)
).play;
)
```

## Support

This project is shared freely with the community, and feedback, testing, or ideas for improvements are always welcome.
If you appreciate this project and would like to help support my work, consider becoming a [GitHub Sponsor](https://github.com/sponsors/iamsiriil) or [Buy me a coffee](http://paypal.me/iamsiriil).

## License

__MusicEngine__ is licensed under the GNU General Public License v3 (GPLv3).
