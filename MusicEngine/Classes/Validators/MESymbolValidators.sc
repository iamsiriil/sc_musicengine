/*********************************************************************************************
* MusicEngine - A dynamic chord library for SuperCollider   								 *
* Copyright (C) 2025 Siriil									    							 *
* Licensed under GPLv3. See LICENSE file for details.			    						 *
*********************************************************************************************/

MESymbolValidators {
	classvar <testRegex;

	/****************************************************************************************/

	*initClass {
		var rx0 = "[dmM](?:7|14)";
		var rx1 = "[dmMA](?:3|6|10|13)";
		var rx2 = "[dPA](?:4|5|11|12)";
		var rx3 = "[mMA](?:2|9)";
		var rx4 = "[dA]8|A1(?!\\d)";

		testRegex  = "(?:%|%|%|%|%)".format(rx0, rx1, rx2, rx3, rx4);

		^this;
	}

	/****************************************************************************************/

	*checkString { |rangeSymbol|

		if (rangeSymbol.isString.not) {
			Error("Range symbol must be of type String.").throw;
		};
		if (rangeSymbol.isEmpty) {
			Error("Range symbol cannot be an empty String").throw;
		};
		^nil;
	}

	/****************************************************************************************/

	*rootIsValid { |rangeSymbol|
		var rgx0 = "^(?:[A-G][#b]*)";
		var rgx1 = "^(?:[A-G][#b]{2,})";
		var root;

		MEDebug.log("MESymbolValidators", "symbolRootIsValid", "\n");

		this.checkString(rangeSymbol);

		if (rgx0.matchRegexp(rangeSymbol).not) {
			Error("Symbol %, has no valid root.".format(rangeSymbol)).throw;
		};
		if (rgx1.matchRegexp(rangeSymbol)) {
			root = rangeSymbol.findRegexp(rgx1).collect { |n| n[1] }[0];
			Error("%, is not a valid root.".format(root)).throw;
		};
		^nil;
	}

	/****************************************************************************************/

	*checkInvalidNumbers { |symbol|
		var regex = "(?:[0-9]{3,})|(?:[2-9][0-9])|(?:1[5-9])|(?<!\\d)0(?!\\d)";

		if (regex.matchRegexp(symbol)) {
			Error("Symbol %, contains invalid numbers. Only numbers from 1 to 14 allowed.".format(symbol)).throw;
		};
		^nil;
	}

	/****************************************************************************************/

	*checkInvalidSymbols { |symbol|
		var regex = "(?:[[:punct:]]+)";

		if (regex.matchRegexp(symbol)) {
			Error("Symbol %, contains invalid non-alphanumeric symbols.".format(symbol)).throw;
		};
		^nil;
	}

	/****************************************************************************************/

	*checkInvalidSpaces { |symbol|
		var regex = "(?:[[:space:]]+)";

		if (regex.matchRegexp(symbol)) {
			Error("Symbol %, contains space characters.".format(symbol)).throw;
		};
		^nil;
	}

	/****************************************************************************************/

	*checkInvalidWords { |symbol|
		var regex = "(?:[a-zA-Z]{2,})";

		if (regex.matchRegexp(symbol)) {
			Error("Symbol %, contains invalid alphabetical constructs.".format(symbol)).throw;
		};
		^nil;
	}

	/****************************************************************************************/

	*checkSymbolSize { |intervalsArr|

		if (intervalsArr.size > 11) {
			Error("Range symbol cannot contains more than 11 intervals.").throw;
		};
		^nil;
	}

	/****************************************************************************************/

	*checkDuplicateIntervals { |intervalsArr|

		intervalsArr.do { |i|

			if ((intervalsArr.count { |n| n == i }) > 1) {
				Error("% is duplicate. Intervals must be unique.".format(i)).throw;
			};
		};
		^nil;
	}

	/****************************************************************************************/

	*checkInvalidIntervals { |symbol, intervalsArr|
		var error = Array.new(11);
		var plural;

		intervalsArr.do { |i|

			if (i.findRegexp(this.testRegex).isEmpty) {
				error.add(i);
			};
		};

		if (error.notEmpty) {

			plural = if (error.size > 1)  {["Are", "", "intervals"]} {["Is", "a ", "interval"]};
			Error("%, % not %valid %.".format(
				error.join(", "),
				plural[0],
				plural[1],
				plural[2])
			).throw;
		};
		^nil;
	}

	/****************************************************************************************/

	*symbolIsValid { |symbol|
		var regex = "(?:[a-zA-Z][0-9]{1,2})";
		var intervalsArr;

		this.checkInvalidNumbers(symbol);
		this.checkInvalidSymbols(symbol);
		this.checkInvalidSpaces(symbol);
		this.checkInvalidWords(symbol);

		intervalsArr = symbol.findRegexp(regex).collect { |n| n[1] };

		this.checkSymbolSize(intervalsArr);
		this.checkInvalidIntervals(symbol, intervalsArr);
		this.checkDuplicateIntervals(intervalsArr);
		^nil;
	}
}