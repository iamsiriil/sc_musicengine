// NOTE Class

MENotes : METools {
	// Data that require validity check
	var <>note;       // String with name (May include octave? "Bb4")
	var <>midi;
	var <>freq;

	// Data to be resolved internaly
	var <>accidental;
	var <>octave;

	// Data that does not need validity checks
	var <>duration;
	var <>articulation;
	var <>dynamic;

	// Data that require context (Note as parte of range, chord or voice)
	var <>degree;
	//var <>chordId
	//var <>voiceId


	*new { |note = nil, midi = nil, freq = nil|
		^super.new.init(note, midi)
	}

	init { |newN, newM, newF|

		if (newN.notNil) {
			if (super.noteIsValid(newN)) {
				note = newN
			}
		};

		if (newM.notNil) {
			if (super.midiIsValid(newM)) {
				midi = newM;
			}
		};

		if (midi.notNil) {
			octave = super.getOctave(midi, note);
			freq   = midi.midicps;
		};

		accidental = note[1..];
		^this;
	}

}

