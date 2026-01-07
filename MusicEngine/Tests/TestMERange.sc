/*********************************************************************************************
* MusicEngine - A dynamic chord library for SuperCollider   								 *
* Copyright (C) 2025 Siriil									    							 *
* Licensed under GPLv3. See LICENSE file for details.			    						 *
*********************************************************************************************/

TestMERange : UnitTest {

	test_sortAndSplit_validInputReverseChromaticUnderOct {
		var f = (
			expected: [
				[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11],
				[0, 1, 1, 2, 2, 3, 4, 4, 5, 5, 6, 6],
				["Rt", "m2", "M2", "m3", "M3", "P4", "d5", "P5", "m6", "M6", "m7", "M7"]
			],
			dataArray: [
				["M7", 11, 6],
				["m7", 10, 6],
				["M6", 9, 5],
				["m6", 8, 5],
				["P5", 7, 4],
				["d5", 6, 4],
				["P4", 5, 3],
				["M3", 4, 2],
				["m3", 3, 2],
				["M2", 2, 1],
				["m2", 1, 1],
				["Rt", 0, 0]
			]
		);

		this.assertEquals(
			MERange.sortAndSplit(f.dataArray),
			f.expected,
			"Testing valid data array: %. Should return %.".format(f.dataArray, f.expected)
		);
	}

	/****************************************************************************************/

	test_sortAndSplit_validInputReverseChromaticOverOct {
		var f = (
			expected: [
				[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11],
				[0, 1, 1, 2, 2, 3, 4, 4, 5, 5, 6, 6],
				["Rt", "m9", "M9", "m10", "M10", "P11", "d12", "P12", "m13", "M13", "m14", "M14"]
			],
			dataArray: [
				["M14", 11, 6],
				["m14", 10, 6],
				["M13", 9, 5],
				["m13", 8, 5],
				["P12", 7, 4],
				["d12", 6, 4],
				["P11", 5, 3],
				["M10", 4, 2],
				["m10", 3, 2],
				["M9", 2, 1],
				["m9", 1, 1],
				["Rt", 0, 0]
			]
		);

		this.assertEquals(
			MERange.sortAndSplit(f.dataArray),
			f.expected,
			"Testing valid data array: %. Should return %.".format(f.dataArray, f.expected)
		);
	}

	/****************************************************************************************/

	test_sortAndSplit_validInputReverseChromaticMixedOcts {
		var f = (
			expected: [
				[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11],
				[0, 1, 1, 2, 2, 3, 4, 4, 5, 5, 6, 6],
				["Rt", "m2", "M9", "m3", "M10", "P4", "d12", "P5", "m13", "M6", "m14", "M7"]
			],
			dataArray: [
				["M7", 11, 6],
				["m14", 10, 6],
				["M6", 9, 5],
				["m13", 8, 5],
				["P5", 7, 4],
				["d12", 6, 4],
				["P4", 5, 3],
				["M10", 4, 2],
				["m3", 3, 2],
				["M9", 2, 1],
				["m2", 1, 1],
				["Rt", 0, 0]
			]
		);

		this.assertEquals(
			MERange.sortAndSplit(f.dataArray),
			f.expected,
			"Testing valid data array: %. Should return %.".format(f.dataArray, f.expected)
		);
	}

	/****************************************************************************************/

	test_getOffsets_validInputReverseChromaticUnderOct {
		var f = (
			expected: [
				[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11],
				[0, 1, 1, 2, 2, 3, 4, 4, 5, 5, 6, 6],
				["Rt", "m2", "M2", "m3", "M3", "P4", "d5", "P5", "m6", "M6", "m7", "M7"]
			],
			intervalsArr: ["M7", "m7",	"M6", "m6",	"P5", "d5",	"P4", "M3",	"m3", "M2", "m2"]
		);

		this.assertEquals(
			MERange.getOffsets(f.intervalsArr),
			f.expected,
			"Testing valid data array: %. Should return %.".format(f.intervalsArr, f.expected)
		);
	}

	/****************************************************************************************/

	test_getOffsets_validInputReverseChromaticOverOct {
		var f = (
			expected: [
				[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11],
				[0, 1, 1, 2, 2, 3, 4, 4, 5, 5, 6, 6],
				["Rt", "m9", "M9", "m10", "M10", "P11", "d12", "P12", "m13", "M13", "m14", "M14"]
			],
			intervalsArr: ["M14", "m14", "M13", "m13", "P12", "d12", "P11", "M10", "m10", "M9", "m9"]
		);

		this.assertEquals(
			MERange.getOffsets(f.intervalsArr),
			f.expected,
			"Testing valid data array: %. Should return %.".format(f.intervalsArr, f.expected)
		);
	}

	/****************************************************************************************/

	test_getOffsets_validInputReverseChromaticMixedOcts {
		var f = (
			expected: [
				[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11],
				[0, 1, 1, 2, 2, 3, 4, 4, 5, 5, 6, 6],
				["Rt", "m2", "M9", "m3", "M10", "P4", "d12", "P5", "m13", "M6", "m14", "M7"]
			],
			intervalsArr: ["M7", "m14", "M6", "m13", "P5", "d12", "P4", "M10", "m3", "M9", "m2"]
		);

		this.assertEquals(
			MERange.getOffsets(f.intervalsArr),
			f.expected,
			"Testing valid data array: %. Should return %.".format(f.intervalsArr, f.expected)
		);
	}

	/****************************************************************************************/

	test_wrapFirstOctave_validInputChromaticNoWrapUnderOct {
		var f = (
			expected: [
				[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11],
				["C", "D", "D", "E", "E", "F", "G", "G", "A", "A", "B", "B"],
				["Rt", "m2", "M2", "m3", "M3", "P4", "d5", "P5", "m6", "M6", "m7", "M7"]
			],
			midiNotesArr:   [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11],
			noteLettersArr: ["C", "D", "D", "E", "E", "F", "G", "G", "A", "A", "B", "B"],
			intervalsArr:   ["Rt", "m2", "M2", "m3", "M3", "P4", "d5", "P5", "m6", "M6", "m7", "M7"]
		);

		this.assertEquals(
			MERange.wrapFirstOctave(f.midiNotesArr, f.noteLettersArr, f.intervalsArr),
			f.expected,
			"Testing valid MIDI notes array: %, note letters array: %, intervals array: %. Should return %.".format(f.midiNotesArr, f.noteLettersArr, f.intervalsArr, f.expected)
		)
	}

	/****************************************************************************************/

	test_wrapFirstOctave_validInputChromaticNoWrapOverOct {
		var f = (
			expected: [
				[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11],
				["C", "D", "D", "E", "E", "F", "G", "G", "A", "A", "B", "B"],
				["Rt", "m9", "M9", "m10", "M10", "P11", "d12", "P12", "m13", "M13", "m14", "M14"]
			],
			midiNotesArr:   [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11],
			noteLettersArr: ["C", "D", "D", "E", "E", "F", "G", "G", "A", "A", "B", "B"],
			intervalsArr:   ["Rt", "m9", "M9", "m10", "M10", "P11", "d12", "P12", "m13", "M13", "m14", "M14"]
		);

		this.assertEquals(
			MERange.wrapFirstOctave(f.midiNotesArr, f.noteLettersArr, f.intervalsArr),
			f.expected,
			"Testing valid MIDI notes array: %, note letters array: %, intervals array: %. Should return %.".format(f.midiNotesArr, f.noteLettersArr, f.intervalsArr, f.expected)
		)
	}

	/****************************************************************************************/

	test_wrapFirstOctave_validInputChromaticNoWrapMixedOcts {
		var f = (
			expected: [
				[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11],
				["C", "D", "D", "E", "E", "F", "G", "G", "A", "A", "B", "B"],
				["Rt", "m2", "M9", "m3", "M10", "P4", "d12", "P5", "m13", "M6", "m14", "M7"]
			],
			midiNotesArr:   [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11],
			noteLettersArr: ["C", "D", "D", "E", "E", "F", "G", "G", "A", "A", "B", "B"],
			intervalsArr:   ["Rt", "m2", "M9", "m3", "M10", "P4", "d12", "P5", "m13", "M6", "m14", "M7"]
		);

		this.assertEquals(
			MERange.wrapFirstOctave(f.midiNotesArr, f.noteLettersArr, f.intervalsArr),
			f.expected,
			"Testing valid MIDI notes array: %, note letters array: %, intervals array: %. Should return %.".format(f.midiNotesArr, f.noteLettersArr, f.intervalsArr, f.expected)
		)
	}

	/****************************************************************************************/

	test_wrapFirstOctave_validInputChromaticWrapUnderOct {
		var f = (
			expected: [
				[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11],
				["C", "D", "D", "E", "E", "F", "G", "G", "A", "A", "B", "C"],
				["P5", "m6", "M6", "m7", "M7", "Rt", "m2", "M2", "m3", "M3", "P4", "d5"]
			],
			midiNotesArr:   [5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16],
			noteLettersArr: ["F", "G", "G", "A", "A", "B", "C", "C", "D", "D", "E", "E"],
			intervalsArr:   ["Rt", "m2", "M2", "m3", "M3", "P4", "d5", "P5", "m6", "M6", "m7", "M7"]
		);

		this.assertEquals(
			MERange.wrapFirstOctave(f.midiNotesArr, f.noteLettersArr, f.intervalsArr),
			f.expected,
			"Testing valid MIDI notes array: %, note letters array: %, intervals array: %. Should return %.".format(f.midiNotesArr, f.noteLettersArr, f.intervalsArr, f.expected)
		)
	}

	/****************************************************************************************/

	test_wrapFirstOctave_validInputChromaticWrapOverOct {
		var f = (
			expected: [
				[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11],
				["C", "D", "D", "E", "E", "F", "G", "G", "A", "A", "B", "C"],
				["P12", "m13", "M13", "m14", "M14", "Rt", "m9", "M9", "m10", "M10", "P11", "d12"]
			],
			midiNotesArr:   [5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16],
			noteLettersArr: ["F", "G", "G", "A", "A", "B", "C", "C", "D", "D", "E", "E"],
			intervalsArr:   ["Rt", "m9", "M9", "m10", "M10", "P11", "d12", "P12", "m13", "M13", "m14", "M14"]
		);

		this.assertEquals(
			MERange.wrapFirstOctave(f.midiNotesArr, f.noteLettersArr, f.intervalsArr),
			f.expected,
			"Testing valid MIDI notes array: %, note letters array: %, intervals array: %. Should return %.".format(f.midiNotesArr, f.noteLettersArr, f.intervalsArr, f.expected)
		)
	}

	/****************************************************************************************/

	test_wrapFirstOctave_validInputChromaticWrapMixedOcts {
		var f = (
			expected: [
				[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11],
				["C", "D", "D", "E", "E", "F", "G", "G", "A", "A", "B", "C"],
				["P5", "m13", "M6", "m14", "M7", "Rt", "m2", "M9", "m3", "M10", "P4", "d12"]
			],
			midiNotesArr:   [5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16],
			noteLettersArr: ["F", "G", "G", "A", "A", "B", "C", "C", "D", "D", "E", "E"],
			intervalsArr:   ["Rt", "m2", "M9", "m3", "M10", "P4", "d12", "P5", "m13", "M6", "m14", "M7"]
		);

		this.assertEquals(
			MERange.wrapFirstOctave(f.midiNotesArr, f.noteLettersArr, f.intervalsArr),
			f.expected,
			"Testing valid MIDI notes array: %, note letters array: %, intervals array: %. Should return %.".format(f.midiNotesArr, f.noteLettersArr, f.intervalsArr, f.expected)
		)
	}

	/****************************************************************************************/

	test_extendMidiRange_validInputChromatic {
		var f = (expected: (0..127), midiNotesArr: (0..11));

		this.assertEquals(
			MERange.extendMidiRange(f.midiNotesArr),
			f.expected,
			"Testing valid MIDI notes array: %. Should return %.".format(f.midiNotesArr, f.expected)
		);
	}

	/****************************************************************************************/

	test_extendMidiRange_validInputSingleInterval {
		var f = (
			expected: [0, 7, 12, 19, 24, 31, 36, 43, 48, 55, 60, 67, 72, 79, 84, 91, 96, 103, 108, 115, 120, 127],
			midiNotesArr: [0, 7]
		);

		this.assertEquals(
			MERange.extendMidiRange(f.midiNotesArr),
			f.expected,
			"Testing valid MIDI notes array: %. Should return %.".format(f.midiNotesArr, f.expected)
		);
	}

	/****************************************************************************************/

	test_wrapAndExtend_validInputFMaj7th {
		var f = (
			expected: [
				[0, 4, 5, 9, 12, 16, 17, 21, 24, 28, 29, 33, 36, 40, 41, 45, 48, 52, 53, 57, 60, 64, 65, 69, 72, 76, 77, 81, 84, 88, 89, 93, 96, 100, 101, 105, 108, 112, 113, 117, 120, 124, 125],
				["C", "E", "F", "A", "C", "E", "F", "A", "C", "E", "F", "A", "C", "E", "F", "A", "C", "E", "F", "A", "C", "E", "F", "A", "C", "E", "F", "A", "C", "E", "F", "A", "C", "E", "F", "A", "C", "E", "F", "A", "C", "E", "F"],
				["P5", "M7", "Rt", "M3", "P5", "M7", "Rt", "M3", "P5", "M7", "Rt", "M3", "P5", "M7", "Rt", "M3", "P5", "M7", "Rt", "M3", "P5", "M7", "Rt", "M3", "P5", "M7", "Rt", "M3", "P5", "M7", "Rt", "M3", "P5", "M7", "Rt", "M3", "P5", "M7", "Rt", "M3", "P5", "M7", "Rt"]
			],
			midiNotesArr:   [5, 9, 12, 16],
			noteLettersArr: ["F", "A", "C", "E"],
			intervalsArr:   ["Rt", "M3", "P5", "M7"]
		);

		this.assertEquals(
			MERange.wrapAndExtend(f.midiNotesArr, f.noteLettersArr, f.intervalsArr),
			f.expected,
			"Testing valid MIDI notes array: %, note letters array: %, intervals array: %. Should return %.".format(f.midiNotesArr, f.noteLettersArr, f.intervalsArr, f.expected)
		);
	}
}