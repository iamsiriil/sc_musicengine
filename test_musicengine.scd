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

// Cb
~cb0 = "Cbd3d5d7"; // Cb root
~cb1 = "Abm3P5";   // Cb m3
~cb2 = "Fm3d5";    // Cb d5

// B#
~bs0 = "B#Maj";    // B# root
~bs1 = "G#M3d5";   // B# M3
~bs2 = "EAug";     // B# A5
)

(
// Select test symbol and create new noteRange
~testSymbol = ~bs2;
~range = MENoteRanges(~testSymbol);
)

// Inspect range properties
~inspectRange.(~range);

// Inspect symbol
~inspectSymbol.(~range);

// Inspect Notes
~inspectNotes.(~range);


