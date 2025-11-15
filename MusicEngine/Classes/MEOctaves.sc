MEOctaves {

	*initClass {}

	/****************************************************************************************/
	// Logic could be improved

	*getOctave { |midi, noteName = nil|
		var octave = -1;

		octave = (octave + (midi/12).floor).asInteger;

		if (noteName.notNil) {

			case
			{ noteName.contains("Db") && (noteName.count{|c| c == $b} > 2) } { ^octave + 1 }
			{ noteName.contains("Cb") }                                      { ^octave + 1 }
			{ noteName.contains("B#") }                                      { ^octave - 1 }
		};

		^octave
	}
}