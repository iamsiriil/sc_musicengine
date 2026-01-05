/*********************************************************************************************
* MusicEngine - A dynamic chord library for SuperCollider   								 *
* Copyright (C) 2025 Siriil									    							 *
* Licensed under GPLv3. See LICENSE file for details.			    						 *
*********************************************************************************************/

TestMESymbolValidators : UnitTest {

	test_rootIsValid_validInputAllRoots {
		var fixtures = [
			"Cb", "C", "C#",
			"Db", "D", "D#",
			"Eb", "E", "E#",
			"Fb", "F", "F#",
			"Gb", "G", "G#",
			"Ab", "A", "A#",
			"Bb", "B", "B#",
		];

		fixtures.do { |f|

			this.assertEquals(
				MESymbolValidators.rootIsValid(f),
				nil,
				"Testing valid range symbol: %. Should return nil.".format(f)
			);
		};
	}

	/****************************************************************************************/

	test_rootIsValid_validInputRootWithSymbol {
		var fixtures = ["CbP5", "CM3P5", "C#M3P5M7", "Cb13", "C-^7", "C#Gr"];

		fixtures.do { |f|

			this.assertEquals(
				MESymbolValidators.rootIsValid(f),
				nil,
				"Testing valid range symbol: %. Should return nil.".format(f)
			);
		};
	}

	/****************************************************************************************/

	test_rootIsValid_invalidInputDataType {
		var fixtures = ['C', $C, ["C"], 1, 1.0];

		fixtures.do { |f|

			this.assertException(
				{ MESymbolValidators.rootIsValid(f) },
				Error,
				"Testing invalid input: %. Should throw Error.".format(f)
			);
		};
	}

	/****************************************************************************************/

	test_rootIsValid_invalidInputEmptyString {

		this.assertException(
			{ MESymbolValidators.rootIsValid("") },
			Error,
			"Testing empty string. Should throw Error."
		);
	}

	/****************************************************************************************/

	test_rootIsValid_invalidInputNoRoot {
		var fixtures = ["M3P5", "min7"];

		fixtures.do { |f|

			this.assertException(
				{ MESymbolValidators.rootIsValid(f) },
				Error,
				"Testing symbol with no root: %. Should throw Error.".format(f)
			);
		};
	}

	/****************************************************************************************/

	test_rootIsValid_invalidInpuInvalidRoot {
		var fixtures = ["cM3P5", "Xb^", "+M3P5", "#o"];

		fixtures.do { |f|

			this.assertException(
				{ MESymbolValidators.rootIsValid(f) },
				Error,
				"Testing invalid root: %. Should throw Error.".format(f)
			);
		};
	}

	/****************************************************************************************/

	test_rootIsValid_invalidInpuInvalidAccidental {
		var fixtures = ["C##M3P5", "CbbXb^", "C###+M3P5", "Cbbbo"];

		fixtures.do { |f|

			this.assertException(
				{ MESymbolValidators.rootIsValid(f) },
				Error,
				"Testing invalid accidental sign: %. Should throw Error.".format(f)
			);
		};
	}

	/****************************************************************************************/

	test_symbolIsValid_validInputSingleIntervalUnderOct {
		var fixtures = [
			"A1",
			"m2", "M2", "A2",
			"d3", "m3", "M3", "A3",
			"d4", "P4", "A4",
			"d5", "P5", "A5",
			"d6", "m6", "M6", "A6",
			"d7", "m7", "M7",
		];

		fixtures.do { |f|

			this.assertEquals(
				MESymbolValidators.symbolIsValid(f),
				nil,
				"Testing valid interval, under octave: %. Should return nil.".format(f)
			);
		};
	}

	/****************************************************************************************/

	test_symbolIsValid_validInputSingleIntervalOverOct {
		var fixtures = [
			"d8", "A8",
			"m9", "M9", "A9",
			"d10", "m10", "M10", "A10",
			"d11", "P11", "A11",
			"d12", "P12", "A12",
			"d13", "m13", "M13", "A13",
			"d14", "m14", "M14",
		];

		fixtures.do { |f|

			this.assertEquals(
				MESymbolValidators.symbolIsValid(f),
				nil,
				"Testing valid interval, over octave: %. Should return nil.".format(f)
			);
		};
	}

	/****************************************************************************************/

	test_symbolIsValid_validInputChromaticsUnderOct {
		var fixture = "m2M2m3M3P4A4P5m6M6m7M7";

		this.assertEquals(
			MESymbolValidators.symbolIsValid(fixture),
			nil,
			"Testing chromatic scale, under octave: %. Should return nil.".format(fixture)
		);
	}

	/****************************************************************************************/

	test_symbolIsValid_validInputChromaticsOverOct {
		var fixture = "m9M9m10M10P11A11P12m13M13m14M14";

		this.assertEquals(
			MESymbolValidators.symbolIsValid(fixture),
			nil,
			"Testing chromatic scale, over octave: %. Should return nil.".format(fixture)
		);
	}

	/****************************************************************************************/

	test_symbolIsValid_validInputMultipleIntervalsUnderOct {
		var fixtures = ["M3P5", "m3d5m7"];

		fixtures.do { |f|

			this.assertEquals(
				MESymbolValidators.symbolIsValid(f),
				nil,
				"Testing multiple valid intervals, under octave: %. Should return nil.".format(f)
			);
		};
	}

	/****************************************************************************************/

	test_symbolIsValid_validInputMultipleIntervalsCrossOct {
		var fixtures = ["M3P5m7M9", "M3P5m7M9P11", "M3P5m7M9P11M13"];

		fixtures.do { |f|

			this.assertEquals(
				MESymbolValidators.symbolIsValid(f),
				nil,
				"Testing valid intervals across two octaves: %. Should return nil.".format(f)
			);
		};
	}

	/****************************************************************************************/

	test_symbolIsValid_invalidNumbers {
		var fixtures = ["M0P5m7", "M3P15m7M9", "M3P5m1000"];

		fixtures.do { |f|

			this.assertException(
				{ MESymbolValidators.symbolIsValid(f) },
				Error,
				"Testing invalid numbers: %. Should throw Error.".format(f)
			);
		};
	}

	/****************************************************************************************/

	test_symbolIsValid_invalidSymbols {
		var fixtures = ["M+P5m7", "M3P*-m7M9", "M3P5m!?.,"];

		fixtures.do { |f|

			this.assertException(
				{ MESymbolValidators.symbolIsValid(f) },
				Error,
				"Testing invalid symbols: %. Should throw Error.".format(f)
			);
		};
	}

	/****************************************************************************************/

	test_symbolIsValid_invalidSpaces {
		var fixtures = ["M P5m7", "M3P  m7M9", "M3P5m    "];

		fixtures.do { |f|

			this.assertException(
				{ MESymbolValidators.symbolIsValid(f) },
				Error,
				"Testing invalid spaces: %. Should throw Error.".format(f)
			);
		};
	}

	/****************************************************************************************/

	test_symbolIsValid_invalidWords {
		var fixtures = ["MMP5m7", "M3Pojyum7M9", "M3P5mig"];

		fixtures.do { |f|

			this.assertException(
				{ MESymbolValidators.symbolIsValid(f) },
				Error,
				"Testing invalid words: %. Should throw Error.".format(f)
			);
		};
	}

	/****************************************************************************************/

	test_symbolIsValid_invalidSize {
		var fixture = "m2M2m3M3P4d5P5m6M6m7M7A8";

		this.assertException(
			{ MESymbolValidators.symbolIsValid(fixture) },
			Error,
			"Testing invalid number of intervals: %. Should throw Error.".format(fixture)
		);
	}

	/****************************************************************************************/

	test_symbolIsValid_invalidIntervalsUnderOct {
		var fixture = "d1d2P3m4M5P6P7";

		this.assertException(
			{ MESymbolValidators.symbolIsValid(fixture) },
			Error,
			"Testing invalid intervals, under octave: %. Should throw Error.".format(fixture)
		);
	}

	/****************************************************************************************/

	test_symbolIsValid_invalidIntervalsOverOct {
		var fixture = "P8d9P10m11M12P13A14";

		this.assertException(
			{ MESymbolValidators.symbolIsValid(fixture) },
			Error,
			"Testing invalid intervals, over octave: %. Should throw Error.".format(fixture)
		);
	}

	/****************************************************************************************/

	test_symbolIsValid_invalidIntervalsRandomLetters {
		var fixture = "t8X9l3a4U12D13g14";

		this.assertException(
			{ MESymbolValidators.symbolIsValid(fixture) },
			Error,
			"Testing invalid intervals, random letters: %. Should throw Error.".format(fixture)
		);
	}
}