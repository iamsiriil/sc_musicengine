/*********************************************************************************************
* MusicEngine - A dynamic chord library for SuperCollider   								 *
* Copyright (C) 2025 Siriil									    							 *
* Licensed under GPLv3. See LICENSE file for details.			    						 *
*********************************************************************************************/

TestMENoteRange_E2E_enharmonicPairs : UnitTest {

	test_MENoteRange_BsCEnharmonicPair {
		var fixture = (
			symbol1:   "B#M2M3P4P5M6M7",
			symbol2:   "CM2M3P4P5M6M7",
			expected: [
				[62, 64, 65, 67, 69, 71, 72],
				["C##4", "D##4", "E#4", "F##4", "G##4", "A##4", "B#4"],
				[60, 62, 64, 65, 67, 69, 71],
				["C4", "D4", "E4", "F4", "G4", "A4", "B4"],
			]
		);
		var range1 = MENoteRange(fixture.symbol1);
		var range2 = MENoteRange(fixture.symbol2);
		var rangeData = [
			range1.midi(4, 4),
			range1.names(4, 4),
			range2.midi(4, 4),
			range2.names(4, 4)
		];

		this.assertEquals(
			rangeData,
			fixture.expected,
			"Testing valid symbol pair: %, %. Middle octave.".format(fixture.symbol1, fixture.symbol2)
		);
	}

	/****************************************************************************************/

	test_MENoteRange_CfBEnharmonicPair {
		var fixture = (
			symbol1:   "CbM2M3P4P5M6M7",
			symbol2:   "BM2M3P4P5M6M7",
			expected: [
				[59, 61, 63, 64, 66, 68, 70],
				["Cb4", "Db4", "Eb4", "Fb4", "Gb4", "Ab4", "Bb4"],
				[61, 63, 64, 66, 68, 70, 71],
				["C#4", "D#4", "E4", "F#4", "G#4", "A#4", "B4"],
			]
		);
		var range1 = MENoteRange(fixture.symbol1);
		var range2 = MENoteRange(fixture.symbol2);
		var rangeData = [
			range1.midi(4, 4),
			range1.names(4, 4),
			range2.midi(4, 4),
			range2.names(4, 4)
		];

		this.assertEquals(
			rangeData,
			fixture.expected,
			"Testing valid symbol pair: %, %. Middle octave.".format(fixture.symbol1, fixture.symbol2)
		);
	}
}