/*********************************************************************************************
* MusicEngine - A dynamic chord library for SuperCollider   								 *
* Copyright (C) 2025 Siriil									    							 *
* Licensed under GPLv3. See LICENSE file for details.			    						 *
*********************************************************************************************/

TestMENoteRange_E2E_CFlat : UnitTest {

	test_MENoteRange_CFlatAsRootFirstOct {
		var fixture = (
			symbol:   "Cb",
			expected: [
				[3, 6],
				["Eb-1", "Gb-1"],
				["M3", "P5"]
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

	test_MENoteRange_CFlatAsRootMiddleOct {
		var fixture = (
			symbol:   "Cb",
			expected: [
				[59, 63, 66],
				["Cb4", "Eb4", "Gb4"],
				["Rt", "M3", "P5"]
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

	test_MENoteRange_CFlatAsRootLastOct {
		var fixture = (
			symbol:   "Cb",
			expected: [
				[119, 123, 126],
				["Cb9", "Eb9", "Gb9"],
				["Rt", "M3", "P5"]
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

	test_MENoteRange_CFlatAsThirdFirstOct {
		var fixture = (
			symbol:   "Abm",
			expected: [
				[3, 8],
				["Eb-1", "Ab-1"],
				["P5", "Rt"]
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

	test_MENoteRange_CFlatAsThirdMiddleOct {
		var fixture = (
			symbol:   "Abm",
			expected: [
				[59, 63, 68],
				["Cb4", "Eb4", "Ab4"],
				["m3", "P5", "Rt"]
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

	test_MENoteRange_CFlatAsThirdLastOct {
		var fixture = (
			symbol:   "Abm",
			expected: [
				[119, 123],
				["Cb9", "Eb9"],
				["m3", "P5"]
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

	test_MENoteRange_CFlatAsFifthFirstOct {
		var fixture = (
			symbol:   "Fb",
			expected: [
				[4, 8],
				["Fb-1", "Ab-1"],
				["Rt", "M3"]
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

	test_MENoteRange_CFlatAsFifthMiddleOct {
		var fixture = (
			symbol:   "Fb",
			expected: [
				[59, 64, 68],
				["Cb4", "Fb4", "Ab4"],
				["P5", "Rt", "M3"]
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

	test_MENoteRange_CFlatAsFifthLastOct {
		var fixture = (
			symbol:   "Fb",
			expected: [
				[119, 124],
				["Cb9", "Fb9"],
				["P5", "Rt"]
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