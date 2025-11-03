/*****************************************************************************************
* MUSIC ENGINE / Chord Data                                                              *
*****************************************************************************************/
// interval already tested for validity before ~getOffsets is called
(
~getOffsets = #{ |interval|
	var offsets = Array.new(2);
	var degree  = interval[1..].asInteger;

	"~getOffsets".postln;

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
	offsets.add(~noteNameOffset[degree]);

	offsets;
};

/****************************************************************************************/

~sortData = #{ |notes, data, i|
	var temp = Array.new(data.size);

	"~sortData".postln;

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
// Midi notes get split here, degrees and names get split during sorting
// Maybe split everything here, for consistency

~splitChordData = #{ |data|
	var notes   = data.collect { |n| n[1] };
	var degrees, names;

	"~splitChordData".postln;

	notes.sort;

	names   = ~sortData.(notes, data, 2);
	degrees = ~sortData.(notes, data, 0);

	[notes, names, degrees];

};

/****************************************************************************************/
// Returns midi and note names offsets, and degrees with added "Rt"

~getChordData = #{ |arr|
	var result  = Array.new(arr.size + 1);
	var temp;

	"~getChordData".postln;

	result.add(["Rt", 0, 0]);

	arr.do { |i|
		temp = Array.new(3);

		temp.add(i);
		temp = temp ++ ~getOffsets.(i);
		result.add(temp);
	};

	~splitChordData.(result);
};

/****************************************************************************************/

~transposeMidi = #{ |root, midiOffset|

	"~transposeMidi".postln;

	midiOffset + root[2];
};

/****************************************************************************************/
// There is a bug with Cbb+ and B#
// We have the note string and the midi value and we want to get the accidental

~resolveAccidentals = #{ |note, root, midi, i|
	var noteIndex = ~noteNames[0].indexOf(note.asSymbol);
	var ref, dif;

	"~resolveAccidentals".postln;

	// Cb and B# require special treatment
	"root: % | note: % | midi[i]: %".format(root, note, midi[i]).postln;
	case
	{ (note == "C") && ((midi[i] == 11) || (midi[i] == 10)) } {
		ref = 12;
	}
	{ (note == "B") && ((midi[i] == 12) || (midi[i] == 13)) } {
		ref = 11;
	}
	{
		if ((midi[i] >= 12)) {
			ref = (~noteNames[1][noteIndex] + 12);
		} {
			ref = ~noteNames[1][noteIndex];
		};
	};

	if (i == 0) {
		dif = root[1];
	} {
		dif = (midi[i] - ref);
	};

	"dif: %".format(dif).postln;

	if (dif.abs > 3){
		Error("Quadruple flats/sharps are NOT allowed.\n".format(note)).throw;
	};

	case
	{ dif.isPositive } { dif.do {note = note ++ "#"} }
	{ dif.isNegative } { dif.abs.do {note = note ++ "b"} };

	note;
};

/****************************************************************************************/

~resolveNoteNames = #{ |root, nameOffset, midi|
	var rootIndex = ~noteNames[0].indexOf(root[0].asSymbol);
	var noteNames = ~noteNames[0].wrapAt(rootIndex + nameOffset);

	"~resolveNoteNames".postln;

	noteNames.do { |n, i| noteNames[i] = n.asString };

	noteNames.do { |n, i|
		noteNames[i] = ~resolveAccidentals.(n, root, midi, i);
	};

	[midi, noteNames];
};
)