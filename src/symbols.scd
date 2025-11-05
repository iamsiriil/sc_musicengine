/*****************************************************************************************
* MUSIC ENGINE / Symbols                                                                 *
*****************************************************************************************/
(
~parseSymbol = #{ |symbol|
	var regexGet  = "\.(?:[2-7]|9|1[0-4])";
	var regexTest = "^(?:[dmPMA](?:[2-7]|9|1[0-4]))+$";
	var iArray;

	"~parseSymbol".postln;

	if (regexTest.matchRegexp(symbol).not) {
		Error("Symbol % is not valid.\n".format(symbol)).throw;
	} {
		iArray = symbol.findRegexp(regexGet).collect { |n| n[1] };
	};

	iArray;
};

/****************************************************************************************/

~parseInput = #{ |symbol|
	var regexTest = "^([A-G](#*|b*))";
	var note, accidental, result, iArray;

	"~parseInput".postln;

	if (regexTest.matchRegexp(symbol)) {

		note   = symbol.findRegexp("^[A-G][#|b]*")[0][1];
		result = ~parseNote.(note);

		symbol = symbol.replace(note, "");
		iArray = ~parseSymbol.(symbol)
	};

	[result, iArray];
};
)
