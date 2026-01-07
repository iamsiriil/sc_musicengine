/*********************************************************************************************
* MusicEngine - A dynamic chord library for SuperCollider   								 *
* Copyright (C) 2025 Siriil									    							 *
* Licensed under GPLv3. See LICENSE file for details.			    						 *
*********************************************************************************************/

TestMEAlias : UnitTest {

	test_aliases_dictionaryUniqueness {
		var testArr, testSet;

		MEAlias.aliases.do { |s|
			testArr = testArr ++ s.asArray;
		};

		testSet = testArr.asSet;

		this.assertEquals(
			testArr.size,
			testSet.size,
			"Testing aliases dictionary for alias uniqueness."
		);
	}

	/****************************************************************************************/

	test_getSymbolFromAlias_validTriads {
		var fixtures = [
			(expected: "m3d5", alias: "o"),
			(expected: "M3P5", alias: "^"),
			(expected: "m3P5", alias: "-"),
			(expected: "M3A5", alias: "+"),
		];

		fixtures.do { |f|

			this.assertEquals(
				MEAlias.getSymbolFromAlias(f.alias),
				f.expected,
				"Testing valid alias: %. Should return: %.".format(f.alias, f.expected)
			);
		};
	}

	/****************************************************************************************/

	test_getSymbolFromAlias_validInputEmptyString {
		var fixture = (expected: "M3P5", alias: "");

		this.assertEquals(
			MEAlias.getSymbolFromAlias(fixture.alias),
			fixture.expected,
			"Testing empty String. Should return: %.".format(fixture.expected)
		);
	}

	/****************************************************************************************/

	test_getSymbolFromAlias_validInputSameSymbol {
		var fixtures = ["m79", "Mi79", "mi79", "Min79", "min79", "-9", "-79"];

		fixtures.do { |f|

			this.assertEquals(
				MEAlias.getSymbolFromAlias(f),
				"m3P5m7M9",
				"Testing valid alias: %. Should return: m3P5m7M9.".format(f)
			);
		};
	}
}