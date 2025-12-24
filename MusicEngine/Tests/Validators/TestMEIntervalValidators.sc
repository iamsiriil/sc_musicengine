/*********************************************************************************************
* MusicEngine - A dynamic chord library for SuperCollider   								 *
* Copyright (C) 2025 Siriil									    							 *
* Licensed under GPLv3. See LICENSE file for details.			    						 *
*********************************************************************************************/

TestMEIntervalValidators : UnitTest {

	test_intervalIsValid_validInput {
		var fixtures = [
			"m2", "m9", "A1", "A8",
			"M2", "M9", "d3", "d10",
			"m3", "m10", "A2", "A9",
			"M3", "M10", "d4", "d11",
			"P4", "P11", "A3", "A10",
			"A4", "A11", "d5", "d12",
			"P5", "P12", "d6", "d13",
			"m6", "m13", "A5", "A12",
			"M6", "M13", "d7", "d14",
			"m7", "m14", "A6", "A13",
			"M7", "M14", "d1", "d8"
		];

		fixtures.do { |f|

			this.assertEquals(
				nil,
				MEIntervalValidators.intervalIsValid(f),
				"Testing valid interval: %. Should return 'nil'.".format(f)
			);
		};
	}

	/****************************************************************************************/

	test_intervalIsValid_invalidtInput {
		var fixtures = [
			"P1", "P8",
			"d2", "d9",
			"P2", "P9",
			"P3", "P10",
			"m4", "m11",
			"M4", "M11",
			"m5", "m12",
			"M5", "M12",
			"P6", "P13",
			"P7", "P14",
			"A7", "A14",
		];

		fixtures.do { |f|

			this.assertException(
				{ MEIntervalValidators.intervalIsValid(f) },
				Error,
				"Testing invalid interval: %. Sould throw Error.".format(f)
			);
		};
	}
}