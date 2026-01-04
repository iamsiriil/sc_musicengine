/*********************************************************************************************
* MusicEngine - A dynamic chord library for SuperCollider   								 *
* Copyright (C) 2025 Siriil									    							 *
* Licensed under GPLv3. See LICENSE file for details.			    						 *
*********************************************************************************************/

MESymbolValidator {
	classvar aliases;
	classvar <testRegex;

	/****************************************************************************************/

	*initClass {
		var rx0 = "[dA][18]|";
		var rx1 = "[mMA][29]|";
		var rx2 = "[dmMA][36]|[dmMA]1[03]|";
		var rx3 = "[dPA][45]|[dPA]1[12]|";
		var rx4 = "[dmM]7|[dmM]14";

		testRegex  = "%%%%%".format(rx0, rx1, rx2, rx3, rx4);
	}

	/****************************************************************************************/

	*symbolIsValid { |symbol, intervalsArr|
		var error = Array.new(12);
		var verb;

		if (intervalsArr.size > 11) {
			Error("Symbol array cannot have more than 11 degrees.").throw;
		};

		intervalsArr.do { |s|
			symbol = symbol.replace(s, " ");
		};

		error = symbol.split($ ).select { |i| i != ""};

		intervalsArr.do { |s|

			if (s.findRegexp(this.testRegex).isEmpty) {

				error.add(s);
			};
		};

		if (error.notEmpty) {

			verb = if (error.size > 1)  {["Are", "", "degrees"]} {["Is", "a ", "degree"]};

			Error("%: % not %valid %.".format(
				error.join(", "),
				verb[0],
				verb[1],
				verb[2])
			).throw;
		};

		^intervalsArr;
	}

	/****************************************************************************************/

	*rootIsValid { |rangeSymbol|
		var rgx0 = "^(?:[A-G][#b]*)";
		var rgx1 = "^(?:[A-G][#b]{2,})";
		var root;

		MEDebug.log("MESymbolValidators", "symbolRootIsValid", "\n");

		if (rangeSymbol.isString.not) {
			Error("Symbol must be of type String.").throw;
		};
		if (rangeSymbol.isEmpty) {
			Error("Symbol cannot be empty String").throw;
		};
		if (rgx0.matchRegexp(rangeSymbol).not) {
			Error("Symbol %, has no valid root.".format(rangeSymbol)).throw;
		};
		if (rgx1.matchRegexp(rangeSymbol)) {
			root = rangeSymbol.findRegexp(rgx1).collect { |n| n[1] }[0];
			Error("%, is not a valid root.".format(root)).throw;
		};
		^nil;
	}
}