// NOTE Class

MENotes : METools {
	// Data that require validity check
	var <note;       // String with name (May include octave? "Bb4")
	var <midi;
	var <freq;
	var <name;

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


	*new { |midi = nil, note = nil, degree = nil, octave = nil|
		^super.new.init(midi, note, degree, octave);
	}

	init { |newM, newN, newD, newO|

		midi       = newM;
		note       = newN;
		freq       = midi.midicps;

		octave     = newO;
		degree     = newD;
		accidental = note[1..];

		name       = note ++ octave;

		^this;
	}

}

