/*********************************************************************************************
* MusicEngine - A dynamic music library for SuperCollider   								 *
* Copyright (C) 2025 Siriil									    							 *
* Licensed under GPLv3. See LICENSE file for details.			    						 *
*********************************************************************************************/

TestMENoteRange_E2E_singleInterval : UnitTest {

	test_MENoteRange_singleIntervalFirstOct {
		var fixture = (
			symbol:   "C5",
			expected: [[0, 7], ["C-1", "G-1"], ['P1', 'P5']],
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

	test_MENoteRange_singleIntervalMiddleOct {
		var fixture = (
			symbol:   "C5",
			expected: [[60, 67], ["C4", "G4"], ['P1', 'P5']],
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

	test_MENoteRange_singleIntervalLastOct {
		var fixture = (
			symbol:   "C5",
			expected: [[120, 127], ["C9", "G9"], ['P1', 'P5']],
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