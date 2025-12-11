/*********************************************************************************************
* MusicEngine - A dynamic chord library for SuperCollider   								 *
* Copyright (C) 2025 Siriil									    							 *
* Licensed under GPLv3. See LICENSE file for details.			    						 *
*********************************************************************************************/

TestMELetterValidators : UnitTest {

	/****************************************************************************************/
	// Unit Tests for: noteLetterIsValid
	/****************************************************************************************/

	test_noteLetterIsValid_validtInputString {
		var fixtures = ["C", "D", "E", "F", "G", "A", "B"];

		fixtures.do { |f|

			this.assertEquals(
				nil,
				MELetterValidators.noteLetterIsValid(f),
				"Testing valid letter: %, as String. Should return 'nil'.".format(f)
			);
		};
	}

	/****************************************************************************************/

	test_noteLetterIsValid_invalidInput {
		var fixtures = [1, 0.1, "-", "c", "X", ["C"], \d, $e];

		fixtures.do { |f|

			this.assertException(
				{ MELetterValidators.noteLetterIsValid(f) },
				Error,
				"Testing invalid letter: %. Should throw an Error.".format(f)
			);
		};
	}

	/****************************************************************************************/
	// Unit Tests for: letterOffsetIsValid
	/****************************************************************************************/

	test_letterOffsetIsValid_validInput {
		var fixtures = (0..6);

		fixtures.do { |f|

			this.assertEquals(
				nil,
				MELetterValidators.letterOffsetIsValid(f),
				"Testing valid letter offset: %. Should return 'nil'.".format(f)
			);
		};
	}

	/****************************************************************************************/

	test_letterOffsetIsValid_invalidInput {
		var fixtures = [-1, 1.0, 7, 7.0, "0", '1', [1], $1];

		fixtures.do { |f|

			this.assertException(
				{ MELetterValidators.letterOffsetIsValid(f) },
				Error,
				"Testing invalid letter: %. should throw an Error.".format(f)
			);
		};
	}

	/****************************************************************************************/
	// Unit Tests for: letterOffsetArrayIsValid
	/****************************************************************************************/

	test_letterOffsetArrayIsValid_validInput {
		var fixtures = [
			[0, 1, 2, 3],
			[1, 2, 3, 4],
			[2, 3, 4, 5],
			[3, 4, 5, 6],
			[0, 1, 2, 3, 4, 5, 6],
			[6, 5, 4, 3, 2, 1, 0]
		];

		fixtures.do { |f|

			this.assertEquals(
				nil,
				MELetterValidators.letterOffsetArrayIsValid(f),
				"Testing valid letter offset array: %. Should return 'nil'.".format(f)
			);
		};
	}

	/****************************************************************************************/

	test_letterOffsetArrayIsValid_invalidInput {
		var fixtures = [
			["0", 1, 2, 3],
			[1, '2', 3, 4],
			[2, 3, $4, 5],
			[3, 4, 5, [6]],
			[0.0, 1.0, 2.0, 3.0, 4.0, 5.0, 6.0]
		];

		fixtures.do { |f|

			this.assertException(
				{ MELetterValidators.letterOffsetArrayIsValid(f) },
				Error,
				"Testing invalid letter offset array: %. Should throw an Error.".format(f)
			);
		};
	}
}