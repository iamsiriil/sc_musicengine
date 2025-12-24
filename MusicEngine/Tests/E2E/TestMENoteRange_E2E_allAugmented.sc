/*********************************************************************************************
* MusicEngine - A dynamic chord library for SuperCollider   								 *
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
				["A2", "A3", "A4", "A5", "A6", "Rt"]
			]
		);
		var range = MENoteRange(fixture.symbol);
		var rangeData = [
			range.midi(-1, -1),
			range.names(-1, -1),
			range.degrees(-1, -1)
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
				["A2", "A3", "A4", "A5", "A6", "Rt"]
			]
		);
		var range = MENoteRange(fixture.symbol);
		var rangeData = [
			range.midi(4, 4),
			range.names(4, 4),
			range.degrees(4, 4)
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
				["A2", "A3", "A4"]
			]
		);
		var range = MENoteRange(fixture.symbol);
		var rangeData = [
			range.midi(9, 9),
			range.names(9, 9),
			range.degrees(9, 9)
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
				["A6", "Rt", "A2", "A3", "A4", "A5"]
			]
		);
		var range = MENoteRange(fixture.symbol);
		var rangeData = [
			range.midi(-1, -1),
			range.names(-1, -1),
			range.degrees(-1, -1)
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
				["A6", "Rt", "A2", "A3", "A4", "A5"]
			]
		);
		var range = MENoteRange(fixture.symbol);
		var rangeData = [
			range.midi(4, 4),
			range.names(4, 4),
			range.degrees(4, 4)
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
				["A6", "Rt"]
			]
		);
		var range = MENoteRange(fixture.symbol);
		var rangeData = [
			range.midi(9, 9),
			range.names(9, 9),
			range.degrees(9, 9)
		];

		this.assertEquals(
			rangeData,
			fixture.expected,
			"Testing valid symbol: %. Last octave.".format(fixture.symbol)
		);
	}
}