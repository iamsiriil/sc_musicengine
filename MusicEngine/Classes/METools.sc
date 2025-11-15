METools {
	classvar notes;
	classvar names;

	*initClass {

		notes = [0, 2, 4, 5, 7, 9, 11];
		names = ['C', 'D', 'E', 'F', 'G', 'A', 'B'];
	}

	/****************************************************************************************/

	*names {
		^names;
	}

	*notes {
		^notes;
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

	getOctave { |midi, note = nil|
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
	}

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

	getNoteNames { |nameOffset, root|
		var index = names.indexOf(root[0].asSymbol);
		var namesArr;

		"getNoteNames".postln;

		namesArr = names.wrapAt(index + nameOffset);

		namesArr.do { |n, i| namesArr[i] = n.asString };

		^namesArr;
	}

	/****************************************************************************************/

	resolveAccidental { |midi, name|
		var index = names.indexOf(name.asSymbol);
		var ref   = notes[index];
		var dif;

		"resolveAccidentals".postln;

		dif = MEOffsets.getAccidentalOffsetFromMidi(midi, name, ref);

		"name: % | midi: % | ref: % | dif: %".format(name, midi, ref, dif).postln;

		if (dif.isNegative) {
			dif.abs.do { name = name ++ "b" };
		} {
			dif.do { name = name ++ "#" };
		};

		^name;
	}
}