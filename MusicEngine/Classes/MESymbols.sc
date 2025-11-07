MESymbols : MESymbolData {
	var <>root;
	var <>degrees;
	var <>symbol;
	var <>alias;
	var <>midiOffset;
	var <>nameOffset;

	*new { |symbol|
		^super.new.init(symbol);
	}

	init { |newSymbol|
		var normSymbol;
		var aliasData;

		"init".postln;

		root   = super.getRoot(newSymbol);
		symbol = newSymbol.replace(root, "");
		// Test if root has more than one accidentals

		case
		//{ (normSymbol = super.checkRegister(symbol)).notNil } {}
		{ (aliasData = super.checkAliases(symbol)).notNil } {

			alias      = symbol;
			symbol     = aliasData[0];
			midiOffset = aliasData[1];
			nameOffset = aliasData[2];
			degrees    = super.getDegreeArray(symbol); // No test needed

		} {

			degrees = super.getDegrees(symbol);

		};

		^this;
	}
}