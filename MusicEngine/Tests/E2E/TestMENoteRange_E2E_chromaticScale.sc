/*********************************************************************************************
* MusicEngine - A dynamic chord library for SuperCollider   								 *
* Copyright (C) 2025 Siriil									    							 *
* Licensed under GPLv3. See LICENSE file for details.			    						 *
*********************************************************************************************/

TestMENoteRange_E2E_chromaticScale : UnitTest {

	test_MENoteRange_ChromaticScaleCFirstOct {
		var fixture = (
			symbol:   "Cm2M2m3M3P4d5P5m6M6m7M7",
			expected: [
				[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11],
				["C-1", "Db-1", "D-1", "Eb-1", "E-1", "F-1", "Gb-1", "G-1", "Ab-1", "A-1", "Bb-1", "B-1"],
				["Rt", "m2", "M2", "m3", "M3", "P4", "d5", "P5", "m6", "M6", "m7", "M7"]
			],
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

	test_MENoteRange_ChromaticScaleCMiddleOct {
		var fixture = (
			symbol:   "Cm2M2m3M3P4d5P5m6M6m7M7",
			expected: [
				[60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71],
				["C4", "Db4", "D4", "Eb4", "E4", "F4", "Gb4", "G4", "Ab4", "A4", "Bb4", "B4"],
				["Rt", "m2", "M2", "m3", "M3", "P4", "d5", "P5", "m6", "M6", "m7", "M7"]
			],
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

	test_MENoteRange_ChromaticScaleCLastOct {
		var fixture = (
			symbol:   "Cm2M2m3M3P4d5P5m6M6m7M7",
			expected: [
				[120, 121, 122, 123, 124, 125, 126, 127],
				["C9", "Db9", "D9", "Eb9", "E9", "F9", "Gb9", "G9"],
				["Rt", "m2", "M2", "m3", "M3", "P4", "d5", "P5"]
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

	test_MENoteRange_ChromaticScaleFsFirstOct {
		var fixture = (
			symbol:   "F#m2M2m3M3P4d5P5m6M6m7M7",
			expected: [
				[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11],
				["C-1", "C#-1", "D-1", "D#-1", "E-1","E#-1", "F#-1", "G-1", "G#-1", "A-1", "A#-1", "B-1"],
				["d5", "P5", "m6", "M6", "m7", "M7", "Rt", "m2", "M2", "m3", "M3", "P4"]
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

	test_MENoteRange_ChromaticScaleFsMiddleOct {
		var fixture = (
			symbol:   "F#m2M2m3M3P4d5P5m6M6m7M7",
			expected: [
				[60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71],
				["C4", "C#4", "D4", "D#4", "E4","E#4", "F#4", "G4", "G#4", "A4", "A#4", "B4"],
				["d5", "P5", "m6", "M6", "m7", "M7", "Rt", "m2", "M2", "m3", "M3", "P4"]
			],
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

	test_MENoteRange_ChromaticScaleFsLastOct {
		var fixture = (
			symbol:   "F#m2M2m3M3P4d5P5m6M6m7M7",
			expected: [
				[120, 121, 122, 123, 124, 125, 126, 127],
				["C9", "C#9", "D9", "D#9", "E9", "E#9", "F#9", "G9"],
				["d5", "P5", "m6", "M6", "m7", "M7", "Rt", "m2"]
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