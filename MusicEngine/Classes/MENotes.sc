// NOTE Class

MENotes : METools {
	// Data that require validity check
	var <note;       // String with name (May include octave? "Bb4")
	var <midi;
	var <freq;

	// Data to be resolved internaly
	var <accidental;
	var <octave;

	// Data that does not need validity checks
	var <duration;
	var <articulation;
	var <dynamic;

	// Data that require context (Note as parte of range, chord or voice)
	var <degree;
	//var <>chordId
	//var <>voiceId


	*new { |note = nil, midi = nil, freq = nil|
		^super.new.init(note, midi)
	}

	init { |newN, newM, newF|

		note = newN;
		midi = newM;

		if (note.size > 1) {
			accidental = MEAccidentals.new(note[1..]);
		};

		if (newM.isNil) {
			midi = super.getMidiFromNote(newN, accidental);
		};

		octave = super.getOctave(note);

		freq = newF;

		^this;
	}

}

