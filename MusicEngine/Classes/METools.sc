METools {
	classvar notes;
	classvar names;
	classvar intervals;

	*initClass {

		notes = [0, 2, 4, 5, 7, 9, 11];

		names = ['C', 'D', 'E', 'F', 'G', 'A', 'B'];

		intervals = Dictionary[
			"m2" -> [Set["m9"], 1],
			"M2" -> [Set["d3", "d10", "M9"], 2],
			"m3" -> [Set["A2", "A9",  "m10"], 3],
			"M3" -> [Set["d4", "d11", "M10"], 4],
			"P4" -> [Set["A3", "A10", "P11"], 5],
			"A4" -> [Set["d5", "d12", "A11"], 6],
			"P5" -> [Set["d6", "d13", "P12"], 7],
			"m6" -> [Set["A5", "A12", "m13"], 8],
			"M6" -> [Set["d7", "d14", "M13"], 9],
			"m7" -> [Set["A6", "A13", "m14"], 10],
			"M7" -> [Set["M14"], 11],
		];
	}

	/****************************************************************************************/

	*names {
		^names;
	}

	*notes {
		^notes;
	}

	*intervals {
		^intervals;
	}

	*indexOfLetter { |letter|
		^names.indexOf(letter.asSymbol);
	}

	*indexOfNote { |note|
		^notes.indexOf(note);
	}

	*noteFromLetter { |letter|
		var index = METools.indexOfLetter(letter);

		^notes[index];
	}

	*letterFromNote { |note|
		var index = METools.indexOfNote(note);

		^names[index]
	}


	/****************************************************************************************/

	/*getOctave { |midi, note = nil|
		var octave = -1;

		"getOctave".postln;

		octave = octave + (midi/12).floor;

		if (note.notNil) {

			case
			{ note.contains("Db") && (note.count{|c| c == $b} > 2) } { octave = octave + 1 }
			{ note.contains("Cb") }                                  { octave = octave + 1 }
			{ note.contains("B#") }                                  { octave = octave - 1 }
		};

		^octave.asInteger;
	}*/

	/****************************************************************************************/

	getMidiFromName { |name|
		var accidentalOffset = 0;
		var base, midi, index;

		"getMidiFromName".postln;

		base = name[0].asSymbol;

		if (name.size > 1) {
			accidentalOffset = MEOffsets.getAccidentalOffsetFromName(name[1..]);
		};

		index = names.indexOf(base);
		midi  = notes[index] + accidentalOffset;

		^[name, midi];
	}

	/****************************************************************************************/

	/*getNoteNames { |nameOffset, root|
		var index = names.indexOf(root[0].asSymbol);
		var namesArr;

		"getNoteNames".postln;

		namesArr = names.wrapAt(index + nameOffset);

		namesArr.do { |n, i| namesArr[i] = n.asString };

		^namesArr;
	}*/

	/****************************************************************************************/

	/*resolveAccidental { |midi, name|
		var dif;

		"resolveAccidentals".postln;

		dif = MEAccidentals.getOffsetFromMidi(midi, name);

		"name: % | midi: % | dif: %".format(name, midi, dif).postln;

		if (dif.isNegative) {
			dif.abs.do { name = name ++ "b" };
		} {
			dif.do { name = name ++ "#" };
		};

		^name;
	}*/
}