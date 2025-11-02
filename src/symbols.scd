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
~test = "C#M1P5m7";
//~result = ~test.findRegexp("^[A-G][#|b]*");
~parseSymbol = { |symbol|
	var testRoot = "^([A-G](#*|b*))";
	var note, accidental, result;

	if (testRoot.matchRegexp(symbol)) {

		note   = symbol.findRegexp("^[A-G][#|b]*")[0][1];
		result = ~parseNote.(note);
		symbol = symbol.replace(note, "");
	};

	result ++ [symbol];
};
~parseSymbol.(~test);
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
~test2 = "m3P5A7P9";
~test2.findRegexp(~tP);
//~tT.matchRegexp(~test2)
)

(
~str = "Cbbbbm3";
~str.findRegexp("[A-G]??[#b]{0,12}")
)