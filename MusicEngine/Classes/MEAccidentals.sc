MEAccidentals : METools {
	var <sign;
	var <offset;

	*new { |noteName|

		^super.new.init(noteName);
	}

	init { |newN|

		if (newN.size == 1) {

			sign = nil;
			offset = 0;
		} {

			sign   = newN[1..];
			offset = MEAccidentals.getOffsetFromName(newN);
		};

		^this;
	}

	/****************************************************************************************/

	*getOffsetFromName { |noteName|
		var accidental = noteName[1..];

		"getOffsetFromName".postln;

		if (accidental.includes($b)) {
			^accidental.size * -1;
		};

		^accidental.size;
	}

	/****************************************************************************************/

	*getOffsetFromMidi { |midi, noteLetter|
		var ref;

		"getOffsetFromMidi".postln;

		ref = MEOctaves.closestOctave(midi, noteLetter);

		^midi - ref;
	}

	/****************************************************************************************/

	*getSymbolFromOffset { |offset|
		var symbol = "";

		case
		{ offset < 0 } { offset.abs.do { symbol = symbol ++ "b"} }
		{ offset > 0 } { offset.do { symbol = symbol ++ "#" } };

		^symbol;
	}
}