/*********************************************************************************************
* MusicEngine - A dynamic music library for SuperCollider   								 *
* Copyright (C) 2025 Siriil									    							 *
* Licensed under GPLv3. See LICENSE file for details.			    						 *
*********************************************************************************************/

TestMENoteRange_E2E_allAugmented : UnitTest {

		test_MENoteRange_BsAllAugmentedFirstOct {
		var fixture = (
			symbol:   "B#A2A3A4A5A6",
			expected: [
				[3, 5, 6, 8, 10, 12],
				["C###-1", "D###-1", "E##-1", "F###-1", "G###-1", "B#-1"],
				['A2', 'A3', 'A4', 'A5', 'A6', 'P1']
			]
		);
		var range = MENoteRange(fixture.symbol);
		var rangeData = [
			range.trimO(-1, -1).midi,
			range.trimO(-1, -1).names,
			range.trimO(-1, -1).degrees
		];

		this.assertEquals(
			rangeData,
			fixture.expected,
			"Testing valid symbol: %. First octave.".format(fixture.symbol)
		);
	}

	/****************************************************************************************/

	test_MENoteRange_BsAllAugmentedMiddleOct {
		var fixture = (
			symbol:   "B#A2A3A4A5A6",
			expected: [
				[63, 65, 66, 68, 70, 72],
				["C###4", "D###4", "E##4", "F###4", "G###4", "B#4"],
				['A2', 'A3', 'A4', 'A5', 'A6', 'P1']
			]
		);
		var range = MENoteRange(fixture.symbol);
		var rangeData = [
			range.trimO(4, 4).midi,
			range.trimO(4, 4).names,
			range.trimO(4, 4).degrees
		];

		this.assertEquals(
			rangeData,
			fixture.expected,
			"Testing valid symbol: %. Middle octave.".format(fixture.symbol)
		);
	}

	/****************************************************************************************/

	test_MENoteRange_BsAllAugmentedLastOct {
		var fixture = (
			symbol:   "B#A2A3A4A5A6",
			expected: [
				[123, 125, 126],
				["C###9", "D###9", "E##9"],
				['A2', 'A3', 'A4']
			]
		);
		var range = MENoteRange(fixture.symbol);
		var rangeData = [
			range.trimO(9, 9).midi,
			range.trimO(9, 9).names,
			range.trimO(9, 9).degrees
		];

		this.assertEquals(
			rangeData,
			fixture.expected,
			"Testing valid symbol: %. Last octave.".format(fixture.symbol)
		);
	}

	/****************************************************************************************/

	test_MENoteRange_EsAllAugmentedFirstOct {
		var fixture = (
			symbol:   "E#A2A3A4A5A6",
			expected: [
				[3, 5, 8, 10, 11, 13],
				["C###-1", "E#-1", "F###-1", "G###-1", "A##-1", "B##-1"],
				['A6', 'P1', 'A2', 'A3', 'A4', 'A5']
			]
		);
		var range = MENoteRange(fixture.symbol);
		var rangeData = [
			range.trimO(-1, -1).midi,
			range.trimO(-1, -1).names,
			range.trimO(-1, -1).degrees
		];

		this.assertEquals(
			rangeData,
			fixture.expected,
			"Testing valid symbol: %. First octave.".format(fixture.symbol)
		);
	}

	/****************************************************************************************/

	test_MENoteRange_EsAllAugmentedMiddleOct {
		var fixture = (
			symbol:   "E#A2A3A4A5A6",
			expected: [
				[63, 65, 68, 70, 71, 73],
				["C###4", "E#4", "F###4", "G###4", "A##4", "B##4"],
				['A6', 'P1', 'A2', 'A3', 'A4', 'A5']
			]
		);
		var range = MENoteRange(fixture.symbol);
		var rangeData = [
			range.trimO(4, 4).midi,
			range.trimO(4, 4).names,
			range.trimO(4, 4).degrees
		];

		this.assertEquals(
			rangeData,
			fixture.expected,
			"Testing valid symbol: %. Middle octave.".format(fixture.symbol)
		);
	}

	/****************************************************************************************/

	test_MENoteRange_EsAllAugmentedLastOct {
		var fixture = (
			symbol:   "E#A2A3A4A5A6",
			expected: [
				[123, 125],
				["C###9", "E#9"],
				['A6', 'P1']
			]
		);
		var range = MENoteRange(fixture.symbol);
		var rangeData = [
			range.trimO(9, 9).midi,
			range.trimO(9, 9).names,
			range.trimO(9, 9).degrees
		];

		this.assertEquals(
			rangeData,
			fixture.expected,
			"Testing valid symbol: %. Last octave.".format(fixture.symbol)
		);
	}
}