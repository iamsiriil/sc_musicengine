// NOTE Class

MENotes : MENoteData {
	var <>note;       // Name string
	var <>accidental;
	var <>octave;
	var <>degree;
	var <>midi;
	var <>freq;
	var <>duration;
	var <>articulation;

	*new { |note, midi = nil|
		^super.new.init(note, midi)
	}

	init { |newN, newM|
		note       = newN;
		midi       = newM;
		accidental = note[1..];

		if (midi.notNil) {
			octave = super.getOctave(midi, note);
			freq   = midi.midicps;
		};

		^this;
	}

}

