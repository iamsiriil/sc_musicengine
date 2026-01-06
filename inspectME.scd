/*****************************************************************************************
* MusicEngine - Inspect                                                                  *
*****************************************************************************************/
(
s.waitForBoot({
	"./inspecttools.scd".loadRelative;
});
/****************************************************************************************/
// TEST SYMBOLS

// Cb
~cb0 = "Cbd3d5d7"; // Cb root
~cb1 = "Abm3P5";   // Cb m3
~cb2 = "Fm3d5";    // Cb d5

// B#
~bs0 = "B#Maj";    // B# root
~bs1 = "G#M3d5";   // B# M3
~bs2 = "EAug";     // B# A5

// All diminished over Cb and Fb
~ad0 = "Cbd3d4d5d6d7";
~ad1 = "Fbd3d4d5d6d7";

// All augmented over B# and E#
~aa0 = "B#A2A3A4A5A6";
~aa1 = "E#A2A3A4A5A6";

// Power chords
~pc0 = "F5";

// Chords with extensions
~ex0 = "DM3P5m7M9P11";
~ex1 = "F#M3P5M7M9P11";

// Aliases
~al0 = "A13";
~al1 = "E";       // Root without symbol defaults to major triad
~al3 = "E#min6";
~al4 = "Gb-9";
~al5 = "C-^7";

// Augmented sixth chords and Neapolitan sixth
~as0 = "BFrench";
~as1 = "F#ita";
~as2 = "D#Gr";

// Weird random chords
~wc0 = "Dd3A5d6"; // Since the 6th is diminished and the 5th augmented, the 6th comes before the 5th in sorted order

~cs0 = "CbA1M2A2M3P4A4P5A5M6A6M7";
~cs1 = "Cbm2M2m3M3P4d5P5m6M6m7M7";

~a10 = "Cd8";
~a11 = "CA8";
)
/****************************************************************************************/
(
// Select test symbol and create new noteRange
~testSymbol = ~al4;
~range = MENoteRange(~testSymbol);
)

// Inspect range properties
~inspectRange.(~range);

// Inspect random note properties
// (different calls will prompt different notes)
~inspectNotes.(~range);

// Inspect sound
~routine.(~range.notes(4, 8, "Rt")).play
