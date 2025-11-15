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

	*transposeMidiOffset { |midiOffset, midiRoot|

		"transposeMidiOffset".postln;

		^midiOffset + midiRoot;
	}
}