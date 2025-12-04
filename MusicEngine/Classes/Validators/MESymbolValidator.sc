/*********************************************************************************************
* MusicEngine - A dynamic chord library for SuperCollider   								 *
* Copyright (C) 2025 Siriil									    							 *
* Licensed under GPLv3. See LICENSE file for details.			    						 *
*********************************************************************************************/

MESymbolValidator : MECore {
	classvar aliases;
	classvar testRegex;

	/****************************************************************************************/

	*initClass {
		var rx1 = "[mMA][29]|";
		var rx2 = "[dmMA][36]|[dmMA]1[03]|";
		var rx3 = "[dPA][45]|[dPA]1[12]|";
		var rx4 = "[dmM]7|[dmM]14";

		testRegex  = "%%%%".format(rx1, rx2, rx3, rx4);
	}

	/****************************************************************************************/

	getDegreeArray { |symbol|
		var regex = "[a-zA-Z]\\d{1,2}";
		var degreeArray;

		MEDebug.log("MESymbolTools", "*getDegreeArray");

		degreeArray = symbol.findRegexp(testRegex).collect { |i| i[1] };

		^degreeArray;
	}

	/****************************************************************************************/
	// Split and test symbol

	getDegrees { |symbol|
		var error = Array.new(12);
		var degreeArray, verb;

		MEDebug.log("MESymbolTools", "*getDegrees");

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

		^degreeArray;
	}

	/****************************************************************************************/
	// Split and test root

	getRoot { |symbol|
		var regex = "^[A-G][#|b]*";
		var root;

		MEDebug.log("MESymbolTools", "*getRoot");

		// Checks for a valid root at the beginning of the symbol
		if (regex.matchRegexp(symbol).not) {
			Error("No valid root detected").throw;
		} {
			root  = symbol.findRegexp(regex)[0][1];

			// Checks for more than one accidental
			if ("[A-G][#b]{2}".matchRegexp(root)) {
				Error("%: Root can only have 0 to 1 accidentals.".format(root)).throw;
			}
		};

		^root;
	}
}