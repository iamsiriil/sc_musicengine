MENoteNames : METools {

	*initClass {}

	/****************************************************************************************/

	*getOffsetFromInterval { |interval|

		"getNameOffsets".postln;

		interval = interval[1..].asInteger;

		if (interval > 7) {
			^(interval - 7) - 1;
		} {
			^interval - 1;
		};
	}

	/****************************************************************************************/

	*getOffsetArray { |intervalArray|
		var offsetArray = Array.new(intervalArray.size + 1);

		offsetArray.add(0);

		intervalArray.do { |i|
			offsetArray.add(MENoteNames.getOffsetFromInterval(i));
		};

		^offsetArray;
	}

	/****************************************************************************************/

	*getNoteNames { |intervalArray, rootLetter|
		var index = super.indexOfLetter(rootLetter);
		var offsetArray, namesArray;

		offsetArray = MENoteNames.getOffsetArray(intervalArray);
		namesArray  = super.names.wrapAt(index + offsetArray);

		namesArray.do { |n, i| namesArray[i] = n.asString };

		^namesArray;
	}
}