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

	*symbolRootIsValid { |chordSymbol|
		var regex = "^[A-G][#|b]*";

		MEDebug.log("MESymbolValidators", "symbolRootIsValid", "\n");

		if (regex.matchRegexp(chordSymbol).not) {
			Error("Symbol: %, has no valid root.".format(chordSymbol)).throw;
		};
		^nil;
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

	/****************************************************************************************/

	/*getDegreeArray { |symbol|
		var regex = "[a-zA-Z]\\d{1,2}";
		var degreeArray;

		//MEDebug.log("MESymbolTools", "*getDegreeArray");

		degreeArray = symbol.findRegexp(testRegex).collect { |i| i[1] };

		^degreeArray;
	}*/

	/****************************************************************************************/
	// Split and test symbol

	/*getDegrees { |symbol|
		var error = Array.new(12);
		var degreeArray, verb;
		var input = symbol;


		// Collect all modifier/degree pairs
		degreeArray = this.getDegreeArray(symbol);

		// Check number of pairs
		if (degreeArray.size > 11) {
			Error("Symbol array cannot have more than 11 degrees.").throw;
		};

		// Remove degrees from string and check leftover characters
		degreeArray.do { |s|
			symbol = symbol.replace(s, " ");
		};

		// Add leftovers to error
		error = symbol.split($ ).select { |i| i != ""};

		// Iterate array, look for invalid symbols and append them to error
		degreeArray.do { |s|

			if (s.findRegexp(testRegex).isEmpty) {

				error.add(s);
			}
		};

		// If error is not empty throw error
		if (error.notEmpty) {

			verb = if (error.size > 1)  {["Are", "", "degrees"]} {["Is", "a ", "degree"]};

			Error("%: % not %valid %.".format(
				error.join(", "),
				verb[0],
				verb[1],
				verb[2])
			).throw;
		};

		MEDebug.log("MESymbolValidator", "*getDegrees", "\nin:  %;\nout: %\n".format(input, degreeArray));

		^degreeArray;
	}*/

	/****************************************************************************************/
	// Split and test root

	/*getRoot { |symbol|
		var regex = "^[A-G][#|b]*";
		var root;


		// Checks for a valid root at the beginning of the symbol
		if (regex.matchRegexp(symbol).not) {
			Error("Symbol: %, has no valid root.".format(symbol)).throw;
		} {
			root  = symbol.findRegexp(regex)[0][1];

			// Checks for more than one accidental
			if ("[A-G][#b]{2}".matchRegexp(root)) {
				Error("%: Root can only have 0 to 1 accidentals.".format(root)).throw;
			}
		};

		MEDebug.log("MESymbolValidator", "*getRoot", "\nin:  %;\nout: %\n".format(symbol, root));

		^root;
	}*/

}