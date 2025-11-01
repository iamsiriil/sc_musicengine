/*****************************************************************************************
* MUSIC ENGINE / Chord Data                                                              *
*****************************************************************************************/
// interval already tested for validity before ~getOffsets is called
(
~getOffsets = { |interval|
	var offsets = Array.new(2);
	var degree  = interval[1..].asInteger.postln;

	"~getIntervalOffset".postln;

	if (degree > 7) {
		degree = (degree - 7).asSymbol;
	} {
		degree = degree.asSymbol;
	};

	interval = interval.asSymbol;

	// Get note offset
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
	offsets.add(~noteNameOffset[degree.postln]);

	offsets;
};

/****************************************************************************************/

~sortData = { |notes, data, i|
	var temp = Array.new(data.size);

	notes.do { |n|

		data.do { |d|

			if ((d[1] == n)) {
				temp.add(d[i]);

			};
		};
	};
	temp;
};

/****************************************************************************************/

~splitChordData = { |data|
	var notes   = data.collect { |n| n[1] }.postln;
	var degrees, names;
	"~splitChordData".postln;

	notes.sort;

	names   = ~sortData.(notes, data, 2);
	degrees = ~sortData.(notes, data, 0);

	[notes, names, degrees];

};

/****************************************************************************************/

~getChordData = { |arr|
	var result  = Array.new(arr.size + 1);
	var temp;

	"~getChordData".postln;

	result.add(["Rt", 0, 0]);

	arr.do { |i|
		temp = Array.new(3);

		temp.add(i);
		temp = temp ++ ~getOffsets.(i).postln;
		result.add(temp);
	};

	~splitChordData.(result);
};
)

(
~test = ["d3", "d5", "M7"];
~getChordData.(~test);
)