// NOTE Class

MENotes : METools {
	var name;
	var <midi;
	var <freq;
	var <octave;
	var <degree;
	var <>duration;
	var <>articulation;
	var <>dynamic;

	*new { |noteName = nil, noteLetter = nil, midi = nil, degree = nil, octave = nil|

		^super.new.init(noteName, noteLetter, midi, degree, octave);
	}

	init { |newN, newL, newM, newD, newO|

		case
		{ newN.isNil && newL.notNil && newM.notNil } {
			midi   = newM;
			freq   = midi.midicps;
			name   = MENoteNames(noteLetter: newL, midi: newM);
			octave = MEOctaves.getOctave(midi, name.name);
		}
		{ newN.notNil && newL.isNil && newM.isNil } {
			midi   = MEMidiNotes.getOffsetFromName(newN) + (12 * 5); // Octave number 4
			freq   = midi.midicps;
			name   = MENoteNames(noteName: newN);
			octave = MEOctaves.getOctave(midi, name.name);
		}
		{
			Error("Instance must be created with either a complete note name, or a note letter and a midi note.\n").throw;
		};

		degree = newD;

		^this;
	}

	copy {
		^this.deepCopy;
	}

	name {
		^name.name ++ octave;
	}
}

