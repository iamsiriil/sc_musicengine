/*********************************************************************************************
* MusicEngine - A dynamic chord library for SuperCollider   								 *
* Copyright (C) 2025 Siriil									    							 *
* Licensed under GPLv3. See LICENSE file for details.			    						 *
*********************************************************************************************/

TestMESymbol : UnitTest {

	test_splitSymbol_validInput {
		var fixtures = [
			(expected: ["C", ""],               rangeSymbol: "C"),
			(expected: ["Cb", "M3P5"],          rangeSymbol: "CbM3P5"),
			(expected: ["C#", "-9"],            rangeSymbol: "C#-9"),
			(expected: ["C", "M3P5m7M9M11M13"], rangeSymbol: "CM3P5m7M9M11M13")
		];

		fixtures.do { |f|

			this.assertEquals(
				MESymbol.splitSymbol(f.rangeSymbol),
				f.expected,
				"Testing valid input: %. Should return: %.".format(f.rangeSymbol, f.expected)
			);
		};
	}

	/****************************************************************************************/

	test_splitSymbol_invalidRoot {
		var fixtures = ["CbbbbM3P5", "C##M3P5", "cM3P5", "XM3P5", "M3P5", "X"];

		fixtures.do { |f|

			this.assertException(
				{ MESymbol.splitSymbol(f) },
				Error,
				"Testing symbol with invalid root: %. Should throw Error.".format(f)
			);
		};
	}

	/****************************************************************************************/

	test_splitSymbol_emptyString {
		var fixture = "";

		this.assertException(
			{ MESymbol.splitSymbol(fixture) },
			Error,
			"Testing invalid empty String: %. Should throw Error.".format(fixture)
		);
	}

	/****************************************************************************************/

	test_splitSymbol_invalidDataTypre {
		var fixtures = ['CM3P5', $C, 1, 1.0, ["CM3P5"]];

		fixtures.do { |f|

			this.assertException(
				{ MESymbol.splitSymbol(f) },
				Error,
				"Testing invalid data type: %. Should throw Error.".format(f)
			);
		};
	}

	/****************************************************************************************/

	test_getIntervalsFromSymbol_validInputOnes {
		var fixtures = [
			(expected: ["A1","A10","A11","A12","A13"], symbol: "A1A10A11A12A13"),
			(expected: ["A13","A12","A11","A10","A1"], symbol: "A13A12A11A10A1")
		];

		fixtures.do { |f|

			this.assertEquals(
				MESymbol.getIntervalsFromSymbol(f.symbol),
				f.expected,
				"Testing valid intervals with ones: %. Should return: %.".format(f.symbol, f.expected)
			);
		};
	}

	/****************************************************************************************/

	test_getIntervalsFromSymbol_validInputChromaticUnderOct {
		var fixtures = [
			(
				expected: ["m2","M2","m3","M3","P4","d5","P5","m6","M6","m7","M7"],
				symbol: "m2M2m3M3P4d5P5m6M6m7M7"
			),
			(
				expected: ["M7","m7","M6","m6","P5","d5","P4","M3","m3","M2","m2"],
				symbol: "M7m7M6m6P5d5P4M3m3M2m2"
			)
		];

		fixtures.do { |f|

			this.assertEquals(
				MESymbol.getIntervalsFromSymbol(f.symbol),
				f.expected,
				"Testing chromatic intervals, under octave: %. Should return: %.".format(f.symbol, f.expected)
			);
		};
	}

	/****************************************************************************************/

	test_getIntervalsFromSymbol_validInputChromaticOverOct {
		var fixtures = [
			(
				expected: ["m9","M9","m10","M10","P11","d12","P12","m13","M13","m14","M14"],
				symbol: "m9M9m10M10P11d12P12m13M13m14M14"
			),
			(
				expected: ["M14","m14","M13","m13","P12","d12","P11","M10","m10","M9","m9"],
				symbol: "M14m14M13m13P12d12P11M10m10M9m9"
			)
		];

		fixtures.do { |f|

			this.assertEquals(
				MESymbol.getIntervalsFromSymbol(f.symbol),
				f.expected,
				"Testing chromatic intervals, over octave: %. Should return: %.".format(f.symbol, f.expected)
			);
		};
	}

	/****************************************************************************************/

	test_getIntervalsFromSymbol_validInputChromaticCrossOct {
		var fixtures = [
			(
				expected: ["m2","M9","m3","M10","P4","d12","P5","m13","M6","m14","M7"],
				symbol: "m2M9m3M10P4d12P5m13M6m14M7"
			),
			(
				expected: ["M14","m7","M13","m6","P12","d5","P11","M3","m10","M2","m9"],
				symbol: "M14m7M13m6P12d5P11M3m10M2m9"
			)
		];

		fixtures.do { |f|

			this.assertEquals(
				MESymbol.getIntervalsFromSymbol(f.symbol),
				f.expected,
				"Testing chromatic intervals, across octaves: %. Should return: %.".format(f.symbol, f.expected)
			);
		};
	}

	/****************************************************************************************/

	test_getIntervalsFromSymbol_validInputSameNumberUnderOctave {
		var fixtures = [
			(expected: ["A1"],                symbol: "A1"),
			(expected: ["m2","M2","A2"],      symbol: "m2M2A2"),
			(expected: ["d3","m3","M3","A3"], symbol: "d3m3M3A3"),
			(expected: ["d4","P4","A4"],      symbol: "d4P4A4"),
			(expected: ["d5","P5","A5"],      symbol: "d5P5A5"),
			(expected: ["d6","m6","M6","A6"], symbol: "d6m6M6A6"),
			(expected: ["d7","m7","M7"],      symbol: "d7m7M7"),
		];

		fixtures.do { |f|

			this.assertEquals(
				MESymbol.getIntervalsFromSymbol(f.symbol, validate: true),
				f.expected,
				"Testing valid intervals with the same number: %. Should return: %.".format(f.symbol, f.expected)
			);
		};
	}

	/****************************************************************************************/

	test_getIntervalsFromSymbol_validInputSameNumberOverOctave {
		var fixtures = [
			(expected: ["d8","A8"],               symbol: "d8A8"),
			(expected: ["m9","M9","A9"],          symbol: "m9M9A9"),
			(expected: ["d10","m10","M10","A10"], symbol: "d10m10M10A10"),
			(expected: ["d11","P11","A11"],       symbol: "d11P11A11"),
			(expected: ["d12","P12","A12"],       symbol: "d12P12A12"),
			(expected: ["d13","m13","M13","A13"], symbol: "d13m13M13A13"),
			(expected: ["d14","m14","M14"],       symbol: "d14m14M14"),
		];

		fixtures.do { |f|

			this.assertEquals(
				MESymbol.getIntervalsFromSymbol(f.symbol, validate: true),
				f.expected,
				"Testing valid intervals with the same number: %. Should return: %.".format(f.symbol, f.expected)
			);
		};
	}

	/****************************************************************************************/

	test_getIntervalsFromSymbol_validInputChords {
		var fixtures = [
			(expected: ["P5"],                            symbol: "P5"),
			(expected: ["M3","P5"],                       symbol: "M3P5"),
			(expected: ["M3","P5","m7"],                  symbol: "M3P5m7"),
			(expected: ["M3","P5","m7","M9"],             symbol: "M3P5m7M9"),
			(expected: ["M3","P5","m7","M9","P11"],       symbol: "M3P5m7M9P11"),
			(expected: ["M3","P5","m7","M9","P11","M13"], symbol: "M3P5m7M9P11M13")
		];

		fixtures.do { |f|

			this.assertEquals(
				MESymbol.getIntervalsFromSymbol(f.symbol, validate: true),
				f.expected,
				"Testing valid chord: %. Should return: %.".format(f.symbol, f.expected)
			);
		};
	}

	/****************************************************************************************/

	test_getIntervalsFromSymbol_invalidNumbers {
		var fixtures = ["M0P5m7", "M3P5m7P15", "0", "m100m1000m10000"];

		fixtures.do { |f|

			this.assertException(
				{ MESymbol.getIntervalsFromSymbol(f, validate: true) },
				Error,
				"Testing symbol with invalid number: %. Should throw Error.".format(f)
			);
		};
	}

	/****************************************************************************************/

	test_getIntervalsFromSymbol_invalidSymbols {
		var fixtures = ["M+P5m7", "M3P5m7P--", "?", "m#m%m/"];

		fixtures.do { |f|

			this.assertException(
				{ MESymbol.getIntervalsFromSymbol(f, validate: true) },
				Error,
				"Testing symbol with invalid symbols: %. Should throw Error.".format(f)
			);
		};
	}

	/****************************************************************************************/

	test_getIntervalsFromSymbol_invalidSpaces {
		var fixtures = ["M P5m7", "M3P5m7P  ", " ", "m m  m   "];

		fixtures.do { |f|

			this.assertException(
				{ MESymbol.getIntervalsFromSymbol(f, validate: true) },
				Error,
				"Testing symbol with invalid spaces: %. Should throw Error.".format(f)
			);
		};
	}

	/****************************************************************************************/

	test_getIntervalsFromSymbol_invalidWords {
		var fixtures = ["MbobyP5m7", "M3P5m7PToby", "Molly", "mJennymBillymJimmy"];

		fixtures.do { |f|

			this.assertException(
				{ MESymbol.getIntervalsFromSymbol(f, validate: true) },
				Error,
				"Testing symbol with invalid Words: %. Should throw Error.".format(f)
			);
		};
	}

	/****************************************************************************************/

	test_getIntervalsFromSymbol_invalidSize {
		var fixture = "m2M2m3M3P4d5P5m6M6m7M7A8";

		this.assertException(
			{ MESymbol.getIntervalsFromSymbol(fixture, validate: true) },
			Error,
			"Testing symbol with invalid size: %. Should throw Error.".format(fixture)
		);
	}

	/****************************************************************************************/

	test_getIntervalsFromSymbol_invalidIntervalsUnderOctave {
		var fixture = "P1d2P2P3m4M4m5M5P6P7A7";

		this.assertException(
			{ MESymbol.getIntervalsFromSymbol(fixture, validate: true) },
			Error,
			"Testing symbol with invalid intervals: %. Should throw Error.".format(fixture)
		);
	}

	/****************************************************************************************/

	test_getIntervalsFromSymbol_invalidIntervalsOverOctave {
		var fixture = "P8d9P9P10m11M11m12M12P13P14A14";

		this.assertException(
			{ MESymbol.getIntervalsFromSymbol(fixture, validate: true) },
			Error,
			"Testing symbol with invalid intervals: %. Should throw Error.".format(fixture)
		);
	}

	/****************************************************************************************/

	test_getIntervalsFromSymbol_invalidIntervalsRandomLetters {
		var fixture = "X3h4J6k8F3t9";

		this.assertException(
			{ MESymbol.getIntervalsFromSymbol(fixture, validate: true) },
			Error,
			"Testing symbol with random letters: %. Should throw Error.".format(fixture)
		);
	}

	/****************************************************************************************/

	test_getIntervalsFromSymbol_invalidIntervalsDuplicateIntervals {
		var fixture = "m3P5P5m7";

		this.assertException(
			{ MESymbol.getIntervalsFromSymbol(fixture, validate: true) },
			Error,
			"Testing symbol with duplicate intervals: %. Should throw Error.".format(fixture)
		);
	}
}