MENoteNames : METools {

	*initClass {}

	/****************************************************************************************/

	*getOffsetFromInterval { |interval|
		var offset;

		"getOffsetFromInterval".postln;

		offset = interval[1..].asInteger;

		if (offset > 7) {
			^(offset - 7) - 1;
		} {
			^offset - 1;
		};
	}

	/****************************************************************************************/

	*getOffsetArray { |intervalArray|
		var offsetArray = Array.new(intervalArray.size + 1);

		"getOffsetArray".postln;

		offsetArray.add(0);

		intervalArray.do { |i|
			offsetArray.add(MENoteNames.getOffsetFromInterval(i));
		};

		^offsetArray;
	}

	/****************************************************************************************/

	*getNoteNames { |intervalArray, rootLetter|
		var index = super.indexOfLetter(rootLetter).postln;
		var namesArray;

		"getNoteNames".postln;

		namesArray  = super.names.wrapAt(index + intervalArray);

		namesArray.do { |n, i| namesArray[i] = n.asString };

		^namesArray;
	}

	/****************************************************************************************/

	*resolveAccidental { |midi, noteLetter|
		var offset, symbol;

		offset = MEAccidentals.getOffsetFromMidi(midi, noteLetter);
		symbol = MEAccidentals.getSymbolFromOffset(offset);

		^noteLetter ++ symbol;
	}
}