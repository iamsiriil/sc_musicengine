/*********************************************************************************************
* MusicEngine - A dynamic chord library for SuperCollider   								 *
* Copyright (C) 2025 Siriil									    							 *
* Licensed under GPLv3. See LICENSE file for details.			    						 *
*********************************************************************************************/

TestMENoteRange_E2E_BSharp : UnitTest {

	test_MENoteRange_BSharpAsRootFirstOct {
		var fixture = (
			symbol:   "B#",
			expected: [
				[4, 7, 12],
				["D##-1", "F##-1", "B#-1"],
				["M3", "P5", "Rt"]
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

	test_MENoteRange_BSharpAsRootMiddleOct {
		var fixture = (
			symbol:   "B#",
			expected: [
				[64, 67, 72],
				["D##4", "F##4", "B#4"],
				["M3", "P5", "Rt"]
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

	test_MENoteRange_BSharpAsRootLastOct {
		var fixture = (
			symbol:   "B#",
			expected: [
				[124, 127],
				["D##9", "F##9"],
				["M3", "P5"]
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

	test_MENoteRange_BSharpAsThirdFirstOct {
		var fixture = (
			symbol:   "G#",
			expected: [
				[3, 8, 12],
				["D#-1", "G#-1", "B#-1"],
				["P5", "Rt", "M3"]
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

	test_MENoteRange_BSharpAsThirdMiddleOct {
		var fixture = (
			symbol:   "G#",
			expected: [
				[63, 68, 72],
				["D#4", "G#4", "B#4"],
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

	test_MENoteRange_BSharpAsThirdLastOct {
		var fixture = (
			symbol:   "G#",
			expected: [
				[123],
				["D#9"],
				["P5"]
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

	test_MENoteRange_BSharpAsFifthFirstOct {
		var fixture = (
			symbol:   "E#",
			expected: [
				[5, 9, 12],
				["E#-1", "G##-1", "B#-1"],
				["Rt", "M3", "P5"]
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

	test_MENoteRange_BSharpAsFifthMiddleOct {
		var fixture = (
			symbol:   "E#",
			expected: [
				[65, 69, 72],
				["E#4", "G##4", "B#4"],
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

	test_MENoteRange_BSharpAsFifthLastOct {
		var fixture = (
			symbol:   "E#",
			expected: [
				[125],
				["E#9"],
				["Rt"]
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