// INSPECT MUSICENGINE DATA
(
~inspectRange = { |range|

	"notes: ".padRight(8).post;
	range.notes.postln;

	"midi: ".padRight(8).post;
	range.midi.postln;

	"freq: ".padRight(8).post;
	range.freq.postln;

	"name: ".padRight(8).post;
	range.names.postln;

	"degree: ".padRight(8).post;
	range.degrees.postln;

	"notes.size: ".padRight(14).post;
	range.notes.size.postln;

	"midi.size: ".padRight(14).post;
	range.midi.size.postln;

	"freq.size: ".padRight(14).post;
	range.freq.size.postln;

	"names.size: ".padRight(14).post;
	range.names.size.postln;

	"degrees.size: ".padRight(14).post;
	range.degrees.size.postln;
};

/****************************************************************************************/

~inspectSymbol = { |range|

	"symbol: ".padRight(12).post;
	range.symbol.symbol.postln;

	"alias: ".padRight(12).post;
	range.symbol.alias.postln;

	"root: ".padRight(12).post; // Name and accidental offset
	range.symbol.root.postln;

	"degrees: ".padRight(12).post;
	range.symbol.degrees.postln;

	"midiOffset: ".padRight(12).post;
	range.symbol.midiOffset.postln;

	"nameOffset: ".padRight(12).post;
	range.symbol.nameOffset.postln;
};

/****************************************************************************************/

~inspectNotes = { |range|
	var size = range.notes.size - 1;
	var i = (0..size).choose;

	"midi: ".padRight(12).post;
	range.notes[i].midi.postln;

	"freq: ".padRight(12).post;
	range.notes[i].freq.postln;

	"name: ".padRight(12).post;
	range.notes[i].name.postln;

	"degree: ".padRight(12).post;
	range.notes[i].degree.postln;

	"octave: ".padRight(12).post;
	range.notes[i].octave.postln;

	"accidental: ".padRight(12).post;
	range.notes[i].accidental.postln;
};

/****************************************************************************************/
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

// Augmented sixth chords and Neapolitan sixth

// Weird random chords
~wc0 = "Dd3A5d6";
)

(
// Select test symbol and create new noteRange
~testSymbol = "C#9";
~range = MENoteRanges(~testSymbol);
)

// Inspect range properties
~inspectRange.(~range);

// Inspect MENoteRanges' symbol property properties
~inspectSymbol.(~range);

// Inspect random note properties
// (different calls will prompt different notes)
~inspectNotes.(~range);
