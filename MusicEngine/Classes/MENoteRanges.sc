MENoteRanges : MERangeTools {
	var <symbol;
	var <midiTemp;
	var <nameTemp;

	*new { |symbol|
		^super.new.init(symbol);
	}

	init { |newSymbol|

		// Create instance of MESymbols (tests symbol and extracts root and degrees) -> MESymbols
		symbol = MESymbols.new(newSymbol);

		// Transpose midi
		midiTemp = super.transposeMidiOffset(
			symbol.midiOffset,
			symbol.root[1]
		);

		// Resolve note names
		nameTemp = super.getNoteNames(
			symbol.nameOffset,
			symbol.root[0]
		);

		nameTemp.do { |n, i|
			nameTemp[i] = super.resolveAccidental(midiTemp[i], n);
		};

		// Wrap first octave

		// Extend ranges

		// Use data to generate a sequence of MENotes instances
		^this;
	}

	wrapRangeData {}
}