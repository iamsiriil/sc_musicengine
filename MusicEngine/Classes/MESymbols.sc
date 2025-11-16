MESymbols : MESymbolTools {
	var <root; // Simple string
	var <degrees;
	var symbol;
	var alias;

	*new { |symbol|
		^super.new.init(symbol);
	}

	init { |newSymbol|
		var normSymbol;

		"init".postln;

		root   = super.getRoot(newSymbol);
		symbol = newSymbol[root[0].size..];

		case
		//{ (normSymbol = super.checkRegister(symbol)).notNil } {}
		{ (normSymbol = MEAliases.checkAliases(symbol)).notNil } {

			if (symbol == "") {
				alias = nil;
			} {
				alias = symbol;
			};
			symbol = normSymbol;

			degrees = super.getDegrees(symbol);
		} {

			degrees = super.getDegrees(symbol);
		};

		^this;
	}

	/****************************************************************************************/

	symbol {
		^root[0] ++ symbol;
	}

	/****************************************************************************************/

	alias {

		if (alias.notNil) {
			^root[0] ++ alias;
		};
		^nil;
	}
}