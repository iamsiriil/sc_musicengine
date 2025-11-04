/*****************************************************************************************
* MUSIC ENGINE / Test                                                                    *
*****************************************************************************************/

"./loadall.scd".loadRelative;

(
// Create a symbol
~s1 = "B#M3A5M7A9A11A13";
~s2 = "Abm2M2m3M3P4A4P5m6M6m7M7";
~s3 = "EbM2P5m7";
~s4 = "DP4P5m7";
~s5 = "F#M3d5m7A9";
~s6 = "E#A3A5M7";
~s7 = "C#A3A5M7";
~s8 = "B#A9A10A11A12A13M14";
~s9 = "Cbm9d10d11d12d13d14";
~s10 = "Cbm9M9m10M10P11d12P12m13M13m14M14";
~s11 = "B#m9M9m10M10P11d12P12m13M13m14M14";
~s12 = "DM2P5";
~s13 = "GbP5";

/****************************************************************************************/

~testSymbol = ~s3;

// Parse symbol to get root and interval arrays
~parsedInput = ~parseInput.(~testSymbol);

~root        = ~parsedInput[0];
~degrees     = ~parsedInput[1];

// Get midi and note name offsets
~offsets     = ~getChordData.(~degrees);

// transpose midi and resolve note names
~midi        = ~transposeMidi.(~root, ~offsets[0]);
~names       = ~resolveNoteNames.(~root, ~offsets[1], ~midi);

// Wrap first octave and extend midi ranges
~dict = Dictionary.new;
~wrapAndExtend.(~dict, ~names[0], ~names[1], ~offsets[2]);

// Get octave numbers
~arr = Array.new(~dict[\notes].size);

~dict[\notes].do { |n, i|
	~arr.add(~getOctave.(n, ~dict[\names][i]));
};

~dict[\octaves] = ~arr;

/****************************************************************************************/
// Check note data arrays

"\nnotes:   ".post;
~dict[\notes].postln;

"names:   ".post;
~dict[\names].postln;

"degrees: ".post;
~dict[\degrees].postln;

"octaves: ".post;
~dict[\octaves].postln;

// Check if all arrays have the same length
"".postln;

~dict.keysValuesDo { |k, v|
	"%: ".format(k).padRight(9).post;
	"%".format(v.size).postln };
)

/****************************************************************************************/
// Check if data is correctly aligned
(
~checkDataMapping = { |dict, symbol|
	var n = dict[\notes].choose;
	var i = dict[\notes].indexOf(n);

	"Symbol: ".padRight(9).post;
	"%\n".format(symbol).postln;

	"Note: ".padRight(9).post;
	"%".format(dict[\notes][i]).postln;

	"Name: ".padRight(9).post;
	"%".format(dict[\names][i]).postln;

	"Degree: ".padRight(9).post;
	"%".format(dict[\degrees][i]).postln;

	"Octaves: ".padRight(9).post;
	"%".format(dict[\octaves][i]).postln;
};

~checkDataMapping.(~dict, ~testSymbol);
)

