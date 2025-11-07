METools {
	classvar notes;
	classvar names;
	classvar intervals;
	classvar nameOffsets;

	*initClass {
		notes    = [0, 2, 4, 5, 7, 9, 11];
		names    = ['C', 'D', 'E', 'F', 'G', 'A', 'B'];

		intervals = Dictionary[
			\Un -> [Set['P1', 'P8', 'd2', 'd9', 'A7', 'A14'], 0],
			\m2 -> [Set['A1', 'A8',  'm9'], 1],
			\M2 -> [Set['d3', 'd10', 'M9'], 2],
			\m3 -> [Set['A2', 'A9',  'm10'], 3],
			\M3 -> [Set['d4', 'd11', 'M10'], 4],
			\P4 -> [Set['A3', 'A10', 'P11'], 5],
			\A4 -> [Set['d5', 'd12', 'A11'], 6],
			\P5 -> [Set['d6', 'd13', 'P12'], 7],
			\m6 -> [Set['A5', 'A12', 'm13'], 8],
			\M6 -> [Set['d7', 'd14', 'M13'], 9],
			\m7 -> [Set['A6', 'A13', 'm14'], 10],
			\M7 -> [Set['d1', 'd8', 'd15', 'M14'], 11],
		];

		nameOffsets = Dictionary[
			\1 -> 0,
			\2 -> 1,
			\3 -> 2,
			\4 -> 3,
			\5 -> 4,
			\6 -> 5,
			\7 -> 6,
			\8 -> 0
		];


	}

	/****************************************************************************************/

	sortNoteData { |arr|
		var notes   = arr.collect { |n| n[1] };
		var names   = Array.new(arr.size);
		var degrees = Array.new(arr.size);

		"sortNoteData".postln;

		notes.sort;

		notes.do { |n, i|

			arr.do { |a|

				if (a[1] == n) {
					degrees.add(a[0]);
					names.add(a[2]);
				}
			}
		};

		^[notes, names, degrees];
	}

	/****************************************************************************************/

	getMidiOffsets { |degree|
		var  midiOffset;

		"getMidiOffset".postln;

		if (intervals[degree.asSymbol].notNil) {

			midiOffset = intervals[degree.asSymbol][1];
		} {

			intervals.do { |i|

				if (i[0].includes(degree.asSymbol)) {

					midiOffset = i[1];
				};
			};
		};

		^midiOffset;
	}

	/****************************************************************************************/

	getNameOffsets { |degree|
		var d = degree[1..].asInteger;

		"getNameOffsets".postln;

		if (d > 7) {
			d = (d - 7).asSymbol;
		} {
			d = d.asSymbol;
		};

		^nameOffsets[d];
	}

	/****************************************************************************************/

	getOffsets { |degrees|
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

	getOctave { |midi, note = nil|
		var octave = -1;

		"getOctave".postln;

		octave = octave + (midi/12).floor;

		if (note.notNil) {

			case
			{ note.contains("Cb") } { octave = octave + 1 }
			{ note.contains("B#") } { octave = octave - 1 }
		};
		^octave;
	}
}