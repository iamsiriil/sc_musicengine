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

// Parse symbol to get root and interval arrays
~parsedInput = ~parseInput.(~s10);

~root        = ~parsedInput[0];
~degrees     = ~parsedInput[1];

// Get midi and note name offsets
~offsets     = ~getChordData.(~degrees);

// transpose midi and resolve note names
~midi        = ~transposeMidi.(~root, ~offsets[0]);
~names       = ~resolveNoteNames.(~root, ~offsets[1], ~midi);

// Wrap first octave and extend midi range
~dict = Dictionary.new;
~wrapAndExtend.(~dict, ~names[0], ~names[1], ~offsets[2]);

"\nnotes:   ".post;
~dict[\notes].postln;
"names:   ".post;
~dict[\names].postln;
"degrees: ".post;
~dict[\degrees].postln;
// Get octave numbers

~arr = Array.new(~dict[\notes].size);

~dict[\notes].do { |n, i|

	~arr.add(~getOctave.(n, ~dict[\names][i]));
};

"octaves: ".post;
~arr.postln;
)
