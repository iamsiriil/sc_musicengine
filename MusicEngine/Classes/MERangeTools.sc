MERangeTools : METools {

	*initClass {

		^this
	}

	/****************************************************************************************/

	transposeMidiOffset { |midiOffset, midiRoot|

		"transposeMidiOffset".postln;

		^midiOffset + midiRoot;
	}

	/****************************************************************************************/

	getNoteNames { |nameOffset, root|
		var index = super.names.indexOf(root[0].asSymbol);
		var namesArr;

		"getNoteNames".postln;

		namesArr = super.names.wrapAt(index + nameOffset);

		namesArr.do { |n, i| namesArr[i] = n.asString };

		^namesArr;
	}

	/****************************************************************************************/

	resolveAccidental { |midi, name|
		var index = super.names.indexOf(name.asSymbol);
		var ref   = super.notes[index];
		var dif;

		if (((name == "C") && (midi > 3)) || (midi > 12)) {
			ref = ref + 12
		};

		dif = midi - ref;

		"name: % | midi: % | ref: % | dif: %".format(name, midi, ref, dif).postln;

		if (dif.isNegative) {
			dif.abs.do { name = name ++ "b" };
		} {
			dif.do { name = name ++ "#" };
		};

		^name;
	}

	/****************************************************************************************/

	wrapFirstOctave { |midi, names, degrees|

		"wrapFirstOctave".postln;

		if (midi[0] < 0) {
			midi = midi + 12;
		};

		midi.do { |m, i|
			if (m > 11) {
				midi[i] = m - 12;
				names   = names.rotate(1);
				degrees = degrees.rotate(1);
			};
		};

		midi.sort;

		^[midi, names, degrees]
	}

	/****************************************************************************************/

	extendMidiRange { |midi|
		var arr = Array.new(midi.size * 11);

		"extendMidi".postln;

		midi.do { |m|

			while { m <= 127 } {

				arr.add(m);
				m = m + 12;
			};
		};

		^arr.sort;
	}

	/****************************************************************************************/

	wrapAndExtend { |midi, names, degrees|
		var tempM, tempN, tempD;

		"wrapAndExtend".postln;

		#tempM, tempN, tempD = this.wrapFirstOctave(midi, names, degrees);

		tempM = this.extendMidiRange(tempM);
		tempN = tempN.wrapExtend(tempM.size);
		tempD = tempD.wrapExtend(tempM.size);

		^[tempM, tempN, tempD];
	}

	/****************************************************************************************/

	getMENotes { |midi, names, degrees|
		var arr = Array.new(midi.size * 5);
		var temp, octave;

		"getMENotes".postln;

		midi.do { |m, i|

			octave = super.getOctave(m, names[i]);
			temp   = MENotes.new(m, names[i], degrees[i], octave);
			arr.add(temp);
		};

		^arr;
	}

	/****************************************************************************************/

	getRange { |symbol|
		var midiTemp, nameTemp, degreeTemp;

		"getRange".postln;

		midiTemp = this.transposeMidiOffset(
			symbol.midiOffset,
			symbol.root[1]
		);

		nameTemp = this.getNoteNames(
			symbol.nameOffset,
			symbol.root[0]
		);

		nameTemp.do { |n, i|
			nameTemp[i] = this.resolveAccidental(midiTemp[i], n);
		};

		#midiTemp, nameTemp, degreeTemp = this.wrapAndExtend(
			midiTemp,
			nameTemp,
			symbol.degrees
		);

		^this.getMENotes(midiTemp, nameTemp, degreeTemp);
	}
}