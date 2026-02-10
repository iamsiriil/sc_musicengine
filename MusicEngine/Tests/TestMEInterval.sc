/*********************************************************************************************
* MusicEngine - A dynamic music library for SuperCollider   								 *
* Copyright (C) 2025 Siriil									    							 *
* Licensed under GPLv3. See LICENSE file for details.			    						 *
*********************************************************************************************/

TestMEInterval : UnitTest {

	test_getMEIntervalArray_validInput {
		var fixture = (
			intervalsArr: ["M13", "P11", "M9", "m7", "P5", "M3", "P1"],
			expect: [
				MEInterval("P1"),
				MEInterval("M3"),
				MEInterval("P5"),
				MEInterval("m7"),
				MEInterval("M9"),
				MEInterval("P11"),
				MEInterval("M13")
			]
		);

		this.assertEquals(
			MEInterval.getMEIntervalArray(fixture.intervalsArr),
			fixture.expect,
			"Testing valid intervals string array: %. Should return: %"
			.format(fixture.intervalsArr, fixture.expect)
		);
	}

	/****************************************************************************************/

	test_convertQuality_symbol {
		var fixtures = [
			(symbol: \d, expect: 0.0),
			(symbol: \m, expect: 0.1),
			(symbol: \P, expect: 0.2),
			(symbol: \M, expect: 0.3),
			(symbol: \A, expect: 0.4),
		];

		fixtures.do { |f|

			this.assertEquals(
				MEInterval.convertQuality(f.symbol),
				f.expect,
				"Testing valid symbol: %. Should return: %."
				.format(f.symbol, f.expect)
			)
		}
	}

	/****************************************************************************************/

	test_convertQuality_float {
		var fixtures = [
			(symbol: 0.0, expect: \d),
			(symbol: 0.1, expect: \m),
			(symbol: 0.2, expect: \P),
			(symbol: 0.3, expect: \M),
			(symbol: 0.4, expect: \A),
		];

		fixtures.do { |f|

			this.assertEquals(
				MEInterval.convertQuality(f.symbol),
				f.expect,
				"Testing valid float: %. Should return: %."
				.format(f.symbol, f.expect)
			)
		}
	}
}