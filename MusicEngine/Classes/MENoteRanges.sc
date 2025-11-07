MENoteRanges {
	var noteRange;

	*new { |symbol|
		^super.new.init(symbol);
	}

	init { |newSymbol|

		// Create instance of MESymbols (tests symbol and extracts root and degrees) -> MESymbols

		// Get name offsets from MENoteData

		// Get note offsets from MEIntervals

		// Transpose midi

		// Resolve note names

		// Wrap first octave

		// Extend ranges

		// Use data to generate a sequence of MENotes instances
	}
}