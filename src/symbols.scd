/*****************************************************************************************
* MUSIC ENGINE / Symbols                                                                 *
*****************************************************************************************/
/*
1. Separate root from symbol
2. Test root
3. Send root to ~parseNote
4. Test symbol
5. Send to ~parseSymbol
6. ~getNoteRange
*/

(
~test = "C#M2P5m7P11";

~parseSymbol = { |symbol|
	var regexGet  = "\.(?:[2-7]|1[0-4])";
	var regexTest = "^(?:[dmPMA](?:[2-7]|1[0-4]))+$";
	var iArray;

	if (regexTest.matchRegexp(symbol).not) {
		Error("Symbol % is not valid.\n".format(symbol)).throw;
	} {
		iArray = symbol.findRegexp(regexGet).collect { |n| n[1] };
	};

	iArray;
};

~parseInput = { |symbol|
	var regexTest = "^([A-G](#*|b*))";
	var note, accidental, result, iArray;

	if (regexTest.matchRegexp(symbol).postln) {

		note   = symbol.findRegexp("^[A-G][#|b]*")[0][1];
		result = ~parseNote.(note);

		symbol = symbol.replace(note, "");
		iArray = ~parseSymbol.(symbol)
	};

	[result, iArray];
};
~parseInput.(~test);
)

(
// Forbiden intervals

~t1 = "[dmPMA]1(?!\\d)";
~t2 = "d[2|9]";
~t7 = "A7|A14";
~t8 = "8|15";
~tP = "P[2|3|6|7|9]|P10|P13|P14";
~tM = "[mM][4|5]|[mM]11|[mM]12";
~tT = "[dmPMA][dmPMA]";
~tD = "^(?:[dmPMA](?:[2-7]|1[0-4]))+$";
)
(
~test2 = "m3P5A7P9A11M13";
~test2.findRegexp("\.(?:[2-7]|1[0-4])");
//~tT.matchRegexp(~test2)
)

(
~str = "Cbbbbm3";
~str.findRegexp("[A-G]??[#b]{0,12}")
)