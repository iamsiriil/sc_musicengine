/*****************************************************************************************
* MUSIC ENGINE / Notes                                                                   *
*****************************************************************************************/
(
~noteNames = [
	['C', 'D', 'E', 'F', 'G', 'A', 'B'],
	[0, 2, 4, 5, 7, 9, 11]
];

~noteNameOffset = Dictionary[
	\1 -> 0,
	\2 -> 1,
	\3 -> 2,
	\4 -> 3,
	\5 -> 4,
	\6 -> 5,
	\7 -> 6,
	\8 -> 0
];

/****************************************************************************************/

~getMidiNote = #{ |name, accidental|
	var midi, i;

	"~getMidiNote".postln;

	i    = ~noteNames[0].indexOf(name.asSymbol);
	midi = ~noteNames[1][i];

	midi = (midi + accidental).postln;

	case // --> Leave this operation till after all note names have been resolved?
	{ midi > 11 } { midi = midi - 12 }
	{ midi < 0 }  { midi = midi + 12 };

	midi;
};

/****************************************************************************************/

~getAccidental = #{ |note|
	var accidental = note[1..].size;

	"~getAccidental".postln;

	if ((accidental).abs > 1) {
		Error("Accidental number cannot exceed 1.\n").throw;
	} {
		if (note.includes($b)) {
			accidental = accidental * -1;
		};
	};

	accidental;
};

/****************************************************************************************/
// We have the note string and the accidental and we're trying to get the midi value

~parseNote = #{ |note|
	var testNote = "^([A-G](#*|b*))$";
	var name, accidental, midi;

	"~parseNote".postln;

	if (testNote.matchRegexp(note).not) {
		Error("Note % is note valid.\n".format(note)).throw;
	} {
		name       = note[0].asString; // Prevents character being stored as Char
		accidental = ~getAccidental.(note);
		midi       = ~getMidiNote.(name, accidental);
	};

	[name, accidental, midi].postln;
};
)
