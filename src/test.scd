/*****************************************************************************************
* MUSIC ENGINE / Test                                                                    *
*****************************************************************************************/
(
// Create a symbol
~symbol1 = "GbM3P5m7M9A11m13";
~symbol2 = "Abbm2M2m3M3P4A4P5m6M6m7M7";

// Parse symbol to get root and interval arrays
~parsedInput = ~parseInput.(~symbol2);

~root    = ~parsedInput[0];
~degrees = ~parsedInput[1];

// Get midi and note name offsets
~offsets = ~getChordData.(~degrees).postln;

// transpose midi and resolve note names
~midi    = ~transposeMidi.(~root, ~offsets[0]).postln;
~names   = ~resolveNoteNames.(~root, ~offsets[1], ~midi).postln;

~names ++ [~offsets[2]]

// Wrap first octave and extend midi range

// Get octave numbers
)