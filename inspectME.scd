/*****************************************************************************************
* MusicEngine - Inspect                                                                  *
*****************************************************************************************/
(
s.waitForBoot({
	"./inspecttools.scd".loadRelative;
});
)

(
// TEST SYMBOLS

// PROBLEMATIC NOTES
// Cb
~cb0 = "Cbd3d5d7"; // Cb root
~cb1 = "Abm3P5";   // Cb m3
~cb2 = "Fm3d5";    // Cb d5

// B#
~bs0 = "B#Maj";    // B# root
~bs1 = "G#M3d5";   // B# M3
~bs2 = "EAug";     // B# A5

// Power chords
~pc0 = "F5";

// Major triads

// Seventh chords

// Chords with extensions

// Aliases
~ex0 = "A9";
~ex1 = "E"; // Root without symbol defaults to major triad

// Augmented sixth chords and Neapolitan sixth

// Weird random chords
~wc0 = "Dd3A5d6"; // Since the 6th is diminished and the 5th augmented, the 6th comes before the 5th in sorted order
)

(
// Select test symbol and create new noteRange
~testSymbol = ~wc0;
~range = MENoteRanges(~testSymbol);
)

// Inspect range properties
~inspectRange.(~range);

// Inspect random note properties
// (different calls will prompt different notes)
~inspectNotes.(~range);


// Inspect sound
~routine.(~range.notes(4, 8, "Rt")).play

