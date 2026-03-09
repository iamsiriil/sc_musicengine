# MusicEngine

__MusicEngine__ is a dynamic music theory based library for SuperCollider that facilitates the generation of note ranges for use in other projects. Given its ability to generate ranges from two to twelve degrees, MusicEngine is flexible enough to generate chord or scale note ranges.

__MusicEngine__ uses a system of verbose range symbols, where all intervals, or degrees, are expressed after a root note (e.g., _F#M3P5m7_). This data is then used to generate note information. Additionally, an alias system maps conventional symbols to their verbose representation for convenience (e.g., _FMaj7 -> FM3P5M7_). When generating a range, the user may use either format.

Note ranges are collections of `MENote` objects that encapsulate all data pertaining to each note (e.g., MIDI value, frequency, note name, degree, octave, etc.). Note ranges gather all notes belonging to any chord or scale across the entire MIDI range (0-127), spanning a total of 11 octaves.

__MusicEngine__ is currently in its `version 0.2.0-beta`.

## Changes from previous version

* The classes MENoteRange, MERange, MENote and MERegister, that make up the interface, are now fully documented by SuperCollider help files. An additional guide was created explaining how to write valid range symbols.

* Users are now able to register costume aliases and symbols via the MERegister class. These entries are permanently saved to a file via the SuperCollider class Archive.

```supercollider
// Create a new entry
MERegister.newEntry('Toby', "M3d5M6"); // The root note should not be included

// Then generate a range over any root
r = MENoteRange.new("F#Toby");
```

* `MENoteRange` objects now behave as a true sequenceable collection.
Upon instantiation the collection is directly returned, without the need to call on the `notes` property to access its items.

```supercollider
r = MENoteRange.new("F#-7"); // MENoteRange[C#-1:P5, E-1:m7, F#-1:P1, A-1:m3, C#0:P5, ..., F#9:P1]
```

From this, most conventional indexing operations can be done.

```supercollider
r = MENoteRange.new("F#-7");

/* Indexing */
r[10];
r.at(10);
r @ 10;

/* Filtering ranges */
r[10..20];
r[10..];
r[..20];
r.copyRange(10,20);

/* Filtering series */
r[10,2..40];
r.copySeries(10,2,40);
```

* Trimming can be done based on octave, MIDI, frequency, degree or note name.

```supercollider
r = MENoteRange.new("F#-7");

/* Double-ended trimming */

r.trimO(4, 8);      // Returns range from octave 4 to 8
r.trimF(500, 1000); // Returns range with freq values >= 500Hz and <= 1000Hz
r.trimD(\P1, \P1);  // Returns range from the first note found matching P1 until the last one found matching P1

// Since most methods return a MENoteRange object, methods can be chained:
r.trimF(500,1000).trimD(\P1, \P1); // Trims by frequency and then by degree

/* Single-ended trimming */

r.bTrimM(60); // Trims the bottom of the sequence and returns a range with MIDI values >= 60
r.tTrimN("E"); // Trims the top of the sequence by ending it in the last "E" found

/* Trimming operators */

r |> 500.0 |> \P1; 
// Same as:
r.bTrimF(500.0).bTrimD(\P1);

r <| 1000.0 <| "F#";
// Same as:
r.tTrimF(1000.0).tTrimN("F#");

r >< [500.0, 5000.0] >< ["F#", "E"];
// Same as:
r.trimF(500.0, 5000.0).trimN("F#", "E");
```

* It is now possible to filter MENote objects based on degree and name.

```supercollider
r = MENoteRange.new("F#-7");
r.filterD(\P1);   // Removes all notes with degree matching P1
r.filterN("F#");  // Removes all notes with name matching F#
r.filterN("F#4"); // Removes F#4 (note names with octave number are unique)

/* Filtering operator */
r | \P1 | "E" | "A4"; // Removes all P1 (F#), all E (m7) and A4

// Can be used in conjuction with other operators
r >< [4, 6] |> \P1 | "A4" | "E5" | "C#6" | "F#6"; // MENoteRange[F#4:P1, C#5:P5, F#5:P1, A5:m3, E6:m7, A6:m3]
// Same as:
(r >< [4, 6] |> \P1).filterN("A4","E5","C#6","F#6");
```

* New method `span` allows for note ranges containing degrees larger than a seventh, to be spread across two octaves.

```supercollider
r = MENoteRange.new("F#-13");
r.trimO(4, 6).trimD(\P1, \P1).span; // MENoteRange[F#4:P1, A4:m3, C#5:P5, E5:m7, G#5:M9, B5:P11, D#6:M13, F#6:P1]

// Regardless of where the sequence starts (ending degree works by aproximation):
r.trimO(4, 6).trimD(\M9, \P1).span; // MENoteRange[G#4:M9, B4:P11, D#5:M13, F#5:P1, A5:m3, C#6:P5, E6:m7]

// Two degrees range
r = MENoteRange.new("F#A11");

(r >< [4, 6] |> "F#").span; // MENoteRange[F#4:P1, B#5:A11, F#6:P1]

(r >< [4, 7] |> "B#").span; // MENoteRange[B#4:A11, F#5:P1, B#6:A11, F#7:P1]
```

* MENoteRange now supports range transposition by interval symbol or MIDI offset.

```supercollider
r = MENoteRange.new("F#-7");
r = r[20..25]; // MENoteRange[C#4:P5, E4:m7, F#4:P1, A4:m3, C#5:P5, E5:m7]

/* Transpose up */
r >> \M2; // MENoteRange[D#4:P5, F#4:m7, G#4:P1, B4:m3, D#5:P5, F#5:m7]
r >> 4;   // MENoteRange[E#4:P5, G#4:m7, A#4:P1, C#5:m3, E#5:P5, G#5:m7]

/* Transpose down */
r << \M2; // MENoteRange[B3:P5, D4:m7, E4:P1, G4:m3, B4:P5, D5:m7]
r << 4;   // MENoteRange[A3:P5, C4:m7, D4:P1, F4:m3, A4:P5, C5:m7]
```
## Range Symbols

A range symbol may be built using a verbose syntax, where all intervals are discriminated after a root, or by appending a conventional symbol to a root.

> [!NOTE]
> For more information on verbose symbols and aliases, see the [Range Symbols](https://github.com/iamsiriil/sc_musicengine/wiki/Range-Symbols) documentation page.

## Support

This project is shared freely with the community, and feedback, testing, or ideas for improvements are always welcome.
If you appreciate this project and would like to help support my work, consider becoming a [GitHub Sponsor](https://github.com/sponsors/iamsiriil) or [Buy me a coffee](http://paypal.me/iamsiriil).

## License

__MusicEngine__ is licensed under the GNU General Public License v3 (GPLv3).
