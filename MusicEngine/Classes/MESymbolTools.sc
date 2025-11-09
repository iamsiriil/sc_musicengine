MESymbolTools : METools {
	classvar aliases;
	classvar testRegex;

	/****************************************************************************************/

	*initClass {
		var rx1 = "[mMA][29]|";
		var rx2 = "[dmMA][36]|[dmMA]1[03]|";
		var rx3 = "[dPA][45]|[dPA]1[12]|";
		var rx4 = "[dmM]7|[dmM]14";

		testRegex  = "%%%%".format(rx1, rx2, rx3, rx4);

		aliases = Dictionary[
			// Power chords
			"P5"   -> [Set["5"], [0, 7], [0, 4]],

			// Triads,
			"d3d5" -> [Set["Italian", "Ita", "It"],             [0, 2, 6], [0, 2, 4]],
			"m3d5" -> [Set["Dim", "Dim5", "º5", "o5"],          [0, 3, 6], [0, 2, 4]],
			"m3P5" -> [Set["m", "min"],                         [0, 3, 7], [0, 2, 4]],
			"M3P5" -> [Set["", "M", "Maj", "Neapolitan", "Na"], [0, 4, 7], [0, 2, 4]],
			"M3A5" -> [Set["Aug5", "Aug", "+5"],                [0, 4, 8], [0, 2, 4]],

			// Seventh chords

			// Nineth chords
			"M3P5m7M9" -> [Set["9"], [0, 2, 4, 7, 10], [0, 1, 2, 4, 6]] // Degrees need to be sorted, or provide array in correct order.
			// Eleventh chords

			// Thirteenth chords

			// Suspended chords

			// Augmented sixth chords
		];
	}

	/****************************************************************************************/

	getOffsets { |degrees|
		var arr;

		"MESymbolData : getOffsets".postln;

		arr = super.getOffsets(degrees);

		^super.sortAndSplit(arr);
	}

	/****************************************************************************************/

	checkAliases { |symbol|
		var normSymbol;
		var midiOffset, nameOffset;

		"checkAliases".postln;

		aliases.keysValuesDo { |k, v|

			if (v[0].includes(symbol)) {
				normSymbol = k;
				midiOffset = v[1];
				nameOffset = v[2];

				^[normSymbol, midiOffset, nameOffset];
			};
		};

		^nil;
	}

	/****************************************************************************************/

	getDegreeArray { |symbol|
		var regex = "[a-zA-Z]\\d{1,2}";
		var degreeArray;

		"getDegreeArray".postln;

		degreeArray = symbol.findRegexp(testRegex).collect { |i| i[1] };

		^degreeArray;
	}

	/****************************************************************************************/
	// Split and test symbol

	getDegrees { |symbol|
		var error = Array.new(12);
		var degreeArray, verb;

		"getDegrees".postln;

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

		"getRoot".postln;

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

		^super.getMidiFromName(root);
	}
}