/*********************************************************************************************
* MusicEngine - A dynamic chord library for SuperCollider   								 *
* Copyright (C) 2025 Siriil									    							 *
* Licensed under GPLv3. See LICENSE file for details.			    						 *
*********************************************************************************************/

TestMEOctaveValidators : UnitTest {

    /****************************************************************************************/
	// OCTAVE VALIDATORS: Unit Tests for octaveIsValid
	/****************************************************************************************/

	test_octaveIsValid_validInput {
		var fixtures = (-1..9);

		fixtures.do { |f|

			this.assertEquals(
				nil,
				MEOctaveValidators.octaveIsValid(f, start: -1),
				"Testing valid octave number: %. Should return 'nil'.".format(f)
			);
		};
	}

	/****************************************************************************************/

	test_octaveIsValid_invalidInput {
		var fixtures = [-2, 10, 1.0, "1", '1', $1];

		fixtures.do { |f|

			this.assertException(
				{ MEOctaveValidators.octaveIsValid(f, start: -1) },
				Error,
				"Testing invalid octave number: %. Should throw Error.".format(f)
			);
		};
	}
}