/*********************************************************************************************
* MusicEngine - A dynamic chord library for SuperCollider   								 *
* Copyright (C) 2025 Siriil									    							 *
* Licensed under GPLv3. See LICENSE file for details.			    						 *
*********************************************************************************************/

TestMENoteRange_E2E_singleInterval : UnitTest {

	test_MENoteRange_singleIntervalFirstOct {
		var fixture = (
			symbol:   "C5",
			expected: [[0, 7], ["C-1", "G-1"], ["Rt", "P5"]],
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

	test_MENoteRange_singleIntervalMiddleOct {
		var fixture = (
			symbol:   "C5",
			expected: [[60, 67], ["C4", "G4"], ["Rt", "P5"]],
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

	test_MENoteRange_singleIntervalLastOct {
		var fixture = (
			symbol:   "C5",
			expected: [[120, 127], ["C9", "G9"], ["Rt", "P5"]],
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