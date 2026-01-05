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
}