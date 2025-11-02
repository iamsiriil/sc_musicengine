/*****************************************************************************************
* MUSIC ENGINE / Test                                                                    *
*****************************************************************************************/
(
// Create a symbol
~symbol = "GbM2P5m7P11";

// Parse symbol to get root and interval arrays
~parsedInput = ~parseInput.(~symbol);

~root    = ~parsedInput[0];
~degrees = ~parsedInput[1];

// Get midi and note name offsets
~offsets = ~getChordData.(~degrees).postln;

// transpose midi and resolve note names
~midi    = ~transposeMidi.(~root, ~offsets[0]).postln;
~names   = ~resolveNoteNames.(~root, ~offsets[1], ~midi).postln;

~names ++ [~offsets[2]]
)