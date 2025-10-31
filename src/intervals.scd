/*****************************************************************************************
* MUSIC ENGINE / Intervals                                                               *
*****************************************************************************************/
(
~intervals = Dictionary[
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

~noteNameOffset = Dictionary[
	\1 -> 0,
	\2 -> 1,
	\3 -> 2,
	\4 -> 3,
	\5 -> 4,
	\6 -> 5,
	\7 -> 6,
	\8 -> 0
];

~getIntervalOffset = { |interval|
	var offsets = Array.new(2);
	var degree  = interval[1..].asInteger.postln;

	if (degree > 7) {
		degree = (degree - 7).asSymbol;
	} {
		degree = degree.asSymbol;
	};

	interval = interval.asSymbol;

	// Get interval offset
	if (~intervals[interval].notNil) {
		offsets.add(~intervals[interval][1]);
	} {
		~intervals.do { |v|

			if (v[0].includes(interval)) {
				offsets.add(v[1]);
			}
		};
	};

	// Get note name offset
	offsets.add(~noteNameOffset[degree]);

	offsets;
};

~getChord = { |arr|
	var degrees = Array.new(arr + 1);
	var notes   = Array.new(arr + 1);

	degrees.add(0);
	notes.add(0);

	arr.do { |a|
		degrees.add(a[1]);
		notes.add(a[0]);
	};

	[notes, degrees];
};
)

(
~getIntervalOffset.("A9")
)

"A4"[1..].asInteger

