MESymbols : MESymbolTools {
	var <root;
	var <degrees;
	var symbol;
	var alias;

	*new { |symbol|
		^super.new.init(symbol);
	}

	init { |newSymbol|
		var normSymbol;

		"MESymbols: init".postln;

		root   = super.getRoot(newSymbol);
		symbol = newSymbol[root.size..];

		if ((normSymbol = MEAliases.checkAliases(symbol)).notNil) {

			if (symbol == "") {
				alias = nil;
			} {
				alias = symbol;
			};

			symbol  = normSymbol;
			degrees = super.getDegrees(symbol);
		} {
			degrees = super.getDegrees(symbol);
		};

		^this;
	}

	/****************************************************************************************/

	symbol {
		^root ++ symbol;
	}

	/****************************************************************************************/

	alias {

		if (alias.notNil) {
			^root ++ alias;
		};
		^nil;
	}
}