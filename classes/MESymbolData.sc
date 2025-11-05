MESymbolData {
	classvar aliases;
	classvar testInt;
	classvar testDeg;

	*initClass {
		aliases = Dictionary[
			// Power chords
			'P5'   -> Set['5'],

			// Triads,
			'M3P5' -> Set['M', 'Maj', '', 'Neapolitan', 'Na']


			// Seventh chords

			// Nineth chords

			// Eleventh chords

			// Thirteenth chords

			// Suspended chords

			// Augmented sixth chords
		];

		testInt = [
			["^[^A-G]",              "% is not a valid root note."],
			["#+#|b+b",              "Root can only have one accidental."],
			["[dmPMA](?!\\d{1,2})",  "Modifiers without degree are not allowed."],
			["(?<![dmPMA])\\d{1,2}", "Degrees without modifier are not allowed."]
		];

		testDeg = [
			["[dmPMA]1(?!\\d)",      "% cannot be used as degree."],
			["[dmPMA]8|[dmPMA]15",   "% is not a valid degree. Octaves are not allowed."],
			["d[29]",                "% is not a valid degree. Only m, M and A allowed with 2nd and 9th degrees."],
			["A7|A14",               "% is not a valid degree. Only d, m and M allowed with 7th and 14th degrees"],
			["P[23679]|P1[034]",     "% is not a valid degree. Only 4ths, 5ths, 11ths and 12ths may be perfect."],
			["[mM][45]|[mM]1[12]",   "% is not a valid degree. 4ths, 5ths, 11ths and 12ths cannot be major or minor."],
		]
	}

	testIntegrity { |symbol|
		var info;

		testInt.do { |t, i|

			case
			{ i == 0 && t[0].matchRegexp(symbol) } {

				info = symbol[0];
				Error(t[1].format(info)).throw;
			}
			{ t[0].matchRegexp(symbol) }{

				Error(t[1]).throw;
			}
		};
		^true;
	}

	testDegrees { |symbol|
		var info;

		testDeg.do { |t|

			if (t[0].matchRegexp(symbol)) {

				info = symbol.findRegexp(t[0])[0][1];
				Error(t[1].format(info)).throw;
			}
		};
		^true;
	}

	symbolIsValid { |symbol|

		if (this.testIntegrity(symbol) && this.testDegrees(symbol)) {
			^true;
		}
	}
}