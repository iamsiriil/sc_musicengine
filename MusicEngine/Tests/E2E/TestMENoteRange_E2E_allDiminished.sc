/*********************************************************************************************
* MusicEngine - A dynamic chord library for SuperCollider   								 *
* Copyright (C) 2025 Siriil									    							 *
* Licensed under GPLv3. See LICENSE file for details.			    						 *
*********************************************************************************************/

TestMENoteRange_E2E_allDiminished : UnitTest {

	test_MENoteRange_CfAllDiminishedFirstOct {
		var fixture = (
			symbol:   "Cbd3d4d5d6d7",
			expected: [
				[1, 3, 5, 6, 8],
				["Ebbb-1", "Fbb-1", "Gbb-1", "Abbb-1", "Bbbb-1"],
				["d3", "d4", "d5", "d6", "d7"],
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

	test_MENoteRange_CfAllDiminishedMiddleOct {
		var fixture = (
			symbol:   "Cbd3d4d5d6d7",
			expected: [
				[59, 61, 63, 65, 66, 68],
				["Cb4", "Ebbb4", "Fbb4", "Gbb4", "Abbb4", "Bbbb4"],
				["Rt", "d3", "d4", "d5", "d6", "d7"],
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

	test_MENoteRange_CfAllDiminishedLastOct {
		var fixture = (
			symbol:   "Cbd3d4d5d6d7",
			expected: [
				[119, 121, 123, 125, 126],
				["Cb9", "Ebbb9", "Fbb9", "Gbb9", "Abbb9"],
				["Rt", "d3", "d4", "d5", "d6"],
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

	test_MENoteRange_FfAllDiminishedFirstOct {
		var fixture = (
			symbol:   "Fbd3d4d5d6d7",
			expected: [
				[1, 4, 6, 8],
				["Ebbb-1", "Fb-1", "Abbb-1", "Bbbb-1"],
				["d7", "Rt", "d3", "d4"]
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

	test_MENoteRange_FfAllDiminishedMiddleOct {
		var fixture = (
			symbol:   "Fbd3d4d5d6d7",
			expected: [
				[58, 59, 61, 64, 66, 68],
				["Cbb4", "Dbbb4", "Ebbb4", "Fb4", "Abbb4", "Bbbb4"],
				["d5", "d6", "d7", "Rt", "d3", "d4"]
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

	test_MENoteRange_FfAllDiminishedLastOct {
		var fixture = (
			symbol:   "Fbd3d4d5d6d7",
			expected: [
				[118, 119, 121, 124, 126],
				["Cbb9", "Dbbb9", "Ebbb9", "Fb9", "Abbb9"],
				["d5", "d6", "d7", "Rt", "d3"]
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