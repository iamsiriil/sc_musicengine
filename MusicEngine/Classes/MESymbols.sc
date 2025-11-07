MESymbols : MESymbolTools {
	var <root;
	var <degrees;
	var <symbol;
	var <alias;
	var <midiOffset;
	var <nameOffset;

	*new { |symbol|
		^super.new.init(symbol);
	}

	init { |newSymbol|
		var normSymbol;
		var aliasData;
		var noteData;

		"init".postln;

		root   = super.getRoot(newSymbol);
		symbol = newSymbol.replace(root, "");
		// Test if root has more than one accidentals

		case
		//{ (normSymbol = super.checkRegister(symbol)).notNil } {}
		{ (aliasData = super.checkAliases(symbol)).notNil } {

			if (symbol == "") {
				alias = nil;
			} {
				alias = symbol;
			};

			symbol     = aliasData[0];
			midiOffset = aliasData[1];
			nameOffset = aliasData[2];
			degrees    = ["Rt"] ++ super.getDegreeArray(symbol); // No test needed

		} {

			degrees  = super.getDegrees(symbol);
			noteData = super.getOffsets(degrees);

			midiOffset = noteData[0];
			nameOffset = noteData[1];
			degrees    = noteData[2];

		};

		^this;
	}
}