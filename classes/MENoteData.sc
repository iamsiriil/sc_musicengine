MENoteData {
	classvar notes;
	classvar names;

	*initClass {
		notes = [0, 2, 4, 5, 7, 9, 11];
		names = ['C', 'D', 'E', 'F', 'G', 'A', 'B'];
	}

	getOctave { |midi, note = nil|
		var octave = -1;

		octave = octave + (midi/12).floor;

		if (note.notNil) {

			case
			{ note.contains("Cb") } { octave = octave + 1 }
			{ note.contains("B#") } { octave = octave - 1 }
		};
		^octave;
	}
}