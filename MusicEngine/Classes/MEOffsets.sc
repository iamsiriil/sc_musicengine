MEOffsets : METools {
	classvar intervals;
	classvar nameOffsets;

	*initClass {

		intervals = Dictionary[
			"m2" -> [Set["m9"], 1],
			"M2" -> [Set["d3", "d10", "M9"], 2],
			"m3" -> [Set["A2", "A9",  "m10"], 3],
			"M3" -> [Set["d4", "d11", "M10"], 4],
			"P4" -> [Set["A3", "A10", "P11"], 5],
			"A4" -> [Set["d5", "d12", "A11"], 6],
			"P5" -> [Set["d6", "d13", "P12"], 7],
			"m6" -> [Set["A5", "A12", "m13"], 8],
			"M6" -> [Set["d7", "d14", "M13"], 9],
			"m7" -> [Set["A6", "A13", "m14"], 10],
			"M7" -> [Set["M14"], 11],
		];

		nameOffsets = Dictionary[
			2 -> 1,
			3 -> 2,
			4 -> 3,
			5 -> 4,
			6 -> 5,
			7 -> 6,
		];
	}

	/****************************************************************************************/

	*getAccidentalOffsetFromMidi { |midi, name, ref|

		"getAccidentalOffsetFromMidi".postln;

		case
		{ (name == "C") && (midi > 3) }   { ref = ref + 12 }
		{ (name == "D") && (midi == 11) } { ref = ref + 12 }
		{ midi > 12 }                     { ref = ref + 12 };

		^midi - ref;
	}

	/****************************************************************************************/

	*getAccidentalOffsetFromName { |accidental|

		"getAccidentalOffsetFromName".postln;

		if (accidental.includes($b)) {
			^accidental.size * -1;
		};

		^accidental.size;
	}

	/****************************************************************************************/

	*getMidiOffsets { |degree|
		var  midiOffset;

		"getMidiOffset".postln;

		if (intervals[degree].notNil) {

			midiOffset = intervals[degree][1];
		} {

			intervals.do { |i|

				if (i[0].includes(degree)) {

					midiOffset = i[1];
				};
			};
		};

		^midiOffset;
	}

	/****************************************************************************************/

	*getNameOffsets { |degree|
		var d = degree[1..].asInteger;

		"getNameOffsets".postln;

		if (d > 7) {
			d = (d - 7);
		} {
			d = d;
		};

		^nameOffsets[d];
	}

	/****************************************************************************************/

	*getOffsets { |degrees|
		var arr = Array.new(degrees.size + 1);

		"getOffsets".postln;

		arr.add(["Rt", 0, 0]);

		degrees.do { |d|
			var temp = Array.new(3);

			temp.add(d);
			temp.add(this.getMidiOffsets(d));
			temp.add(this.getNameOffsets(d));

			arr.add(temp);
		};

		^arr;
	}

	/****************************************************************************************/

	*transposeMidiOffset { |midiOffset, midiRoot|

		"transposeMidiOffset".postln;

		^midiOffset + midiRoot;
	}
}