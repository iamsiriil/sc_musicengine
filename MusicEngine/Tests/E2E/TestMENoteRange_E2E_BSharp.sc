/*********************************************************************************************
* MusicEngine - A dynamic music library for SuperCollider   								 *
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
				['M3', 'P5', 'P1']
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

	test_MENoteRange_BSharpAsRootMiddleOct {
		var fixture = (
			symbol:   "B#",
			expected: [
				[64, 67, 72],
				["D##4", "F##4", "B#4"],
				['M3', 'P5', 'P1']
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

	test_MENoteRange_BSharpAsRootLastOct {
		var fixture = (
			symbol:   "B#",
			expected: [
				[124, 127],
				["D##9", "F##9"],
				['M3', 'P5']
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

	test_MENoteRange_BSharpAsThirdFirstOct {
		var fixture = (
			symbol:   "G#",
			expected: [
				[3, 8, 12],
				["D#-1", "G#-1", "B#-1"],
				['P5', 'P1', 'M3']
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

	test_MENoteRange_BSharpAsThirdMiddleOct {
		var fixture = (
			symbol:   "G#",
			expected: [
				[63, 68, 72],
				["D#4", "G#4", "B#4"],
				['P5', 'P1', 'M3']
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

	test_MENoteRange_BSharpAsThirdLastOct {
		var fixture = (
			symbol:   "G#",
			expected: [
				[123],
				["D#9"],
				['P5']
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

	test_MENoteRange_BSharpAsFifthFirstOct {
		var fixture = (
			symbol:   "E#",
			expected: [
				[5, 9, 12],
				["E#-1", "G##-1", "B#-1"],
				['P1', 'M3', 'P5']
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

	test_MENoteRange_BSharpAsFifthMiddleOct {
		var fixture = (
			symbol:   "E#",
			expected: [
				[65, 69, 72],
				["E#4", "G##4", "B#4"],
				['P1', 'M3', 'P5']
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

	test_MENoteRange_BSharpAsFifthLastOct {
		var fixture = (
			symbol:   "E#",
			expected: [
				[125],
				["E#9"],
				['P1']
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