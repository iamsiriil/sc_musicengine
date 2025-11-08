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

		if  ((name == "C") && (midi > 3)) {

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

	wrapRangeData {}

	extendRangeData {}

	extendMidiRange {}


}