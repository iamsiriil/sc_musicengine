MESymbols : MESymbolData {
	var <>root;
	var <>degrees;
	var <>symbol;

	*new { |symbol|
		^super.new.init(symbol);
	}

	init { |newSymbol|

		if (super.symbolIsValid(newSymbol)) {
			root   = newSymbol.findRegexp("^[A-G][#|b]*")[0][1];
			symbol = newSymbol.replace(root, "");

			"root:   %".format(root).postln;
			"symbol: %".format(symbol).postln;
		};
		^this;
	}
}