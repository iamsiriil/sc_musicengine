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

		"getAccidentalOffsetFromName".postln;

		if (accidental.includes($b)) {
			^accidental.size * -1;
		};

		^accidental.size;
	}

	/****************************************************************************************/
	// Logic could be improved

	*getOffsetFromMidi { |midi, noteLetter|
		var ref = super.noteFromLetter(noteLetter);

		"getAccidentalOffsetFromMidi".postln;

		if (
			((noteLetter == "C") && (midi > 3))   ||
			((noteLetter == "D") && (midi == 11)) ||
			 (midi > 12)
		) {
			ref = ref + 12;
		};

		^midi - ref;
	}

	/****************************************************************************************/

	*getSymbolFromOffset { |offset|
		var symbol = "";

		case
		{ offset < 0 } { offset.abs.do { symbol = symbol ++ "b"} }
		{ offset > 0 } { offset.do { symbol = symbol ++ "#" } }
		{ symbol = nil };

		^symbol;
	}
}