MENoteData {
	classvar notes;
	classvar names;

	*initClass {
		notes = [0, 2, 4, 5, 7, 9, 11];
		names = ['C', 'D', 'E', 'F', 'G', 'A', 'B'];
	}

	getOctave { |midi, note|
		var temp   = midi;
		var octave = -2;

		while { temp >= 0 } {
			temp   = temp - 12;
			octave = octave + 1;
		};

		case
		{ note == "Cb" } { ^octave + 1 }
		{ note == "B#" } { ^octave - 1 }
		{ ^octave }
	}
}