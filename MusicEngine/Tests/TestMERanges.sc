/*********************************************************************************************
* MusicEngine - A dynamic chord library for SuperCollider   								 *
* Copyright (C) 2025 Siriil									    							 *
* Licensed under GPLv3. See LICENSE file for details.			    						 *
*********************************************************************************************/

TestMERanges : UnitTest {

	test_sortAndSplit_validInputReverseChromaticUnder8th {
		var fixtures = [(
			expected: [
				[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11],
				[0, 1, 1, 2, 2, 3, 4, 4, 5, 5, 6, 6],
				["Rt", "m2", "M2", "m3", "M3", "P4", "d5", "P5", "m6", "M6", "m7", "M7"]
			],
			dataArray: [
				["M7", 11, 6],
				["m7", 10, 6],
				["M6", 9, 5],
				["m6", 8, 5],
				["P5", 7, 4],
				["d5", 6, 4],
				["P4", 5, 3],
				["M3", 4, 2],
				["m3", 3, 2],
				["M2", 2, 1],
				["m2", 1, 1],
				["Rt", 0, 0]
			]
		)];

		fixtures.do { |f|

			this.assertEquals(
				f.expected,
				MERanges.sortAndSplit(f.dataArray),
				"Testing valid data array: %. Should return %.".format(f.dataArray, f.expected)
			);
		};
	}

	/****************************************************************************************/

	test_sortAndSplit_validInputReverseChromaticOver8th {
		var fixtures = [(
			expected: [
				[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11],
				[0, 1, 1, 2, 2, 3, 4, 4, 5, 5, 6, 6],
				["Rt", "m9", "M9", "m10", "M10", "P11", "d12", "P12", "m13", "M13", "m14", "M14"]
			],
			dataArray: [
				["M14", 11, 6],
				["m14", 10, 6],
				["M13", 9, 5],
				["m13", 8, 5],
				["P12", 7, 4],
				["d12", 6, 4],
				["P11", 5, 3],
				["M10", 4, 2],
				["m10", 3, 2],
				["M9", 2, 1],
				["m9", 1, 1],
				["Rt", 0, 0]
			]
		)];

		fixtures.do { |f|

			this.assertEquals(
				f.expected,
				MERanges.sortAndSplit(f.dataArray),
				"Testing valid data array: %. Should return %.".format(f.dataArray, f.expected)
			);
		};
	}

	/****************************************************************************************/

	test_sortAndSplit_validInputReverseChromaticMixedOcts {
		var fixtures = [(
			expected: [
				[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11],
				[0, 1, 1, 2, 2, 3, 4, 4, 5, 5, 6, 6],
				["Rt", "m2", "M9", "m3", "M10", "P4", "d12", "P5", "m13", "M6", "m14", "M7"]
			],
			dataArray: [
				["M7", 11, 6],
				["m14", 10, 6],
				["M6", 9, 5],
				["m13", 8, 5],
				["P5", 7, 4],
				["d12", 6, 4],
				["P4", 5, 3],
				["M10", 4, 2],
				["m3", 3, 2],
				["M9", 2, 1],
				["m2", 1, 1],
				["Rt", 0, 0]
			]
		)];

		fixtures.do { |f|

			this.assertEquals(
				f.expected,
				MERanges.sortAndSplit(f.dataArray),
				"Testing valid data array: %. Should return %.".format(f.dataArray, f.expected)
			);
		};
	}

	/****************************************************************************************/

	test_getOffsets_validInputReverseChromaticUnder8th {
		var fixtures = [(
			expected: [
				[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11],
				[0, 1, 1, 2, 2, 3, 4, 4, 5, 5, 6, 6],
				["Rt", "m2", "M2", "m3", "M3", "P4", "d5", "P5", "m6", "M6", "m7", "M7"]
			],
			intervalsArr: ["M7", "m7",	"M6", "m6",	"P5", "d5",	"P4", "M3",	"m3", "M2", "m2"]
		)];

		fixtures.do { |f|

			this.assertEquals(
				f.expected,
				MERanges.getOffsets(f.intervalsArr),
				"Testing valid data array: %. Should return %.".format(f.intervalsArr, f.expected)
			);
		};
	}

	/****************************************************************************************/

	test_getOffsets_validInputReverseChromaticOver8th {
		var fixtures = [(
			expected: [
				[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11],
				[0, 1, 1, 2, 2, 3, 4, 4, 5, 5, 6, 6],
				["Rt", "m9", "M9", "m10", "M10", "P11", "d12", "P12", "m13", "M13", "m14", "M14"]
			],
			intervalsArr: ["M14", "m14", "M13", "m13", "P12", "d12", "P11", "M10", "m10", "M9", "m9"]
		)];

		fixtures.do { |f|

			this.assertEquals(
				f.expected,
				MERanges.getOffsets(f.intervalsArr),
				"Testing valid data array: %. Should return %.".format(f.intervalsArr, f.expected)
			);
		};
	}

	/****************************************************************************************/

	test_getOffsets_validInputReverseChromaticMixedOcts {
		var fixtures = [(
			expected: [
				[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11],
				[0, 1, 1, 2, 2, 3, 4, 4, 5, 5, 6, 6],
				["Rt", "m2", "M9", "m3", "M10", "P4", "d12", "P5", "m13", "M6", "m14", "M7"]
			],
			intervalsArr: ["M7", "m14", "M6", "m13", "P5", "d12", "P4", "M10", "m3", "M9", "m2"]
		)];

		fixtures.do { |f|

			this.assertEquals(
				f.expected,
				MERanges.getOffsets(f.intervalsArr),
				"Testing valid data array: %. Should return %.".format(f.intervalsArr, f.expected)
			);
		};
	}
}