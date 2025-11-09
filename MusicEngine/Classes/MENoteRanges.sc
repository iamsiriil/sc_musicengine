MENoteRanges : MERangeTools {
	var <symbol;
	var <notes;

	*new { |symbol|
		^super.new.init(symbol);
	}

	init { |newSymbol|

		symbol = MESymbols.new(newSymbol);
		notes  = super.getRange(symbol);

		^this;
	}

	midi {
		^notes.collect { |n| n.midi };
	}

	freq {
		^notes.collect { |n| n.freq };
	}

	names {
		^notes.collect { |n| n.name };
	}

	degrees {
		^notes.collect { |n| n.degree };
	}
}