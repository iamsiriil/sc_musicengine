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

~parseNote = { |note, root = false|
	var testNote = "^([A-G](#*|b*))$";
	var name, accidental, midi, i;

	note = note.asString;

	if (testNote.matchRegexp(note)) {

		name       = note[0].asString;
		accidental = note[1..].size;

		if ((accidental).abs > 12) {
			Error("Accidental number cannot exceed 12.\n").throw;
		};

		if (note.includes($b)) {
			accidental = accidental * -1;
		};

		i    = ~noteNames[0].indexOf(name.asSymbol);
		midi = ~noteNames[1][i];

		midi = midi + accidental;

		case
		{ midi > 11 } { midi = midi - 12 }
		{ midi < 0 }  { midi = midi + 12 }

	} {
		Error("Note % is note valid.\n".format(note)).throw;
	};

	[name, accidental, midi];
};
~noteData = ~parseNote.("B#");
)
