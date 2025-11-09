// NOTE Class

MENotes : METools {
	var <note;
	var <midi;
	var <freq;
	var <name;
	var <accidental;
	var <octave;
	var <degree;
	var <>duration;
	var <>articulation;
	var <>dynamic;

	*new { |midi = nil, note = nil, degree = nil, octave = nil|
		^super.new.init(midi, note, degree, octave);
	}

	init { |newM, newN, newD, newO|

		midi   = newM;
		note   = newN;
		freq   = midi.midicps;
		octave = newO;
		degree = newD;
		name   = note ++ octave;

		if (note.size > 1) {
			accidental = note[1..];
		} {
			accidental = nil;
		};

		^this;
	}
}

