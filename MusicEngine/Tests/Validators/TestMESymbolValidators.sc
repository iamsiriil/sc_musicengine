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
				MESymbolValidator.rootIsValid(f),
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
				MESymbolValidator.rootIsValid(f),
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
				{ MESymbolValidator.rootIsValid(f) },
				Error,
				"Testing invalid input: %. Should throw Error.".format(f)
			);
		};
	}

	/****************************************************************************************/

	test_rootIsValid_invalidInputEmptyString {

		this.assertException(
			{ MESymbolValidator.rootIsValid("") },
			Error,
			"Testing empty string. Should throw Error."
		);
	}

	/****************************************************************************************/

	test_rootIsValid_invalidInputNoRoot {
		var fixtures = ["M3P5", "min7"];

		fixtures.do { |f|

			this.assertException(
				{ MESymbolValidator.rootIsValid(f) },
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
				{ MESymbolValidator.rootIsValid(f) },
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
				{ MESymbolValidator.rootIsValid(f) },
				Error,
				"Testing invalid accidental sign: %. Should throw Error.".format(f)
			);
		};
	}
}