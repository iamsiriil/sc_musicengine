MEMidiNotes : METools {

	*initClass {}

	/****************************************************************************************/

	*getMidiOffset { |degree|
		var  midiOffset;

		"getMidiOffset".postln;

		if (intervals[degree].notNil) {

			midiOffset = super.intervals[degree][1];
		} {

			intervals.do { |i|

				if (i[0].includes(degree)) {

					midiOffset = i[1];
				};
			};
		};

		^midiOffset;
	}

	/****************************************************************************************/

	*getMidiFromName { |noteName|
		var accidentalOffset = 0;

		"getMidiFromName".postln;

		if (name.size > 1) {
			accidentalOffset = MEAccidentals.getOffsetFromName(noteName[1..]);
		};

		^super.noteFromLetter(noteName[0]) + accidentalOffset;
	}

	/****************************************************************************************/

	*transposeMidiOffset { |midiOffset, midiRoot|

		"transposeMidiOffset".postln;

		^midiOffset + midiRoot;
	}
}