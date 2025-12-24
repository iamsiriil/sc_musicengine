/*********************************************************************************************
* MusicEngine - A dynamic chord library for SuperCollider   								 *
* Copyright (C) 2025 Siriil									    							 *
* Licensed under GPLv3. See LICENSE file for details.			    						 *
*********************************************************************************************/

TestMEAccidentalValidators : UnitTest {

	test_signOffsetIsValid_validInput {
		var fixtures = (-5..5);

		fixtures.do { |f|

			this.assertEquals(
				MEAccidentalValidators.signOffsetIsValid(f),
				nil,
				"Testing valid sign offset: %. Should return 'nil'.".format(f)
			);
		};
	}

	/****************************************************************************************/

	test_signOffsetIsValid_invalidInput {
		var fixtures = [-6, 6, 1.0, "1", '1', $1];

		fixtures.do { |f|

			this.assertException(
				{ MEAccidentalValidators.signOffsetIsValid(f) },
				Error,
				"Testing invalid sign offset: %. Should throw Error.".format(f)
			);
		};
	}
}