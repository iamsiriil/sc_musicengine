/*********************************************************************************************
* MusicEngine - A dynamic chord library for SuperCollider   								 *
* Copyright (C) 2025 Siriil									    							 *
* Licensed under GPLv3. See LICENSE file for details.			    						 *
*********************************************************************************************/

TestMEMIDINote : UnitTest {

	test_getOffsetFromInterval_validInputUnder8th {
		var fixtures = [
			(expected: 1, interval: "m2"),
			(expected: 1, interval: "A1"),
			(expected: 2, interval: "M2"),
			(expected: 2, interval: "d3"),
			(expected: 3, interval: "m3"),
			(expected: 3, interval: "A2"),
			(expected: 4, interval: "M3"),
			(expected: 4, interval: "d4"),
			(expected: 5, interval: "P4"),
			(expected: 5, interval: "A3"),
			(expected: 6, interval: "A4"),
			(expected: 6, interval: "d5"),
			(expected: 7, interval: "P5"),
			(expected: 7, interval: "d6"),
			(expected: 8, interval: "m6"),
			(expected: 8, interval: "A5"),
			(expected: 9, interval: "M6"),
			(expected: 9, interval: "d7"),
			(expected: 10, interval: "m7"),
			(expected: 10, interval: "A6"),
			(expected: 11, interval: "M7"),
			(expected: 11, interval: "d1")
		];

		fixtures.do { |f|

			this.assertEquals(
				MEMIDINote.getOffsetFromInterval(f.interval, validate: true),
				f.expected,
				"Testing valid interval: %. Should return %.".format(f.interval, f.expected)
			);
		};
	}

	/****************************************************************************************/

	test_getOffsetFromInterval_validInputOver8th {
		var fixtures = [
			(expected: 1, interval: "m9"),
			(expected: 1, interval: "A8"),
			(expected: 2, interval: "M9"),
			(expected: 2, interval: "d10"),
			(expected: 3, interval: "m10"),
			(expected: 3, interval: "A9"),
			(expected: 4, interval: "M10"),
			(expected: 4, interval: "d11"),
			(expected: 5, interval: "P11"),
			(expected: 5, interval: "A10"),
			(expected: 6, interval: "A11"),
			(expected: 6, interval: "d12"),
			(expected: 7, interval: "P12"),
			(expected: 7, interval: "d13"),
			(expected: 8, interval: "m13"),
			(expected: 8, interval: "A12"),
			(expected: 9, interval: "M13"),
			(expected: 9, interval: "d14"),
			(expected: 10, interval: "m14"),
			(expected: 10, interval: "A13"),
			(expected: 11, interval: "M14"),
			(expected: 11, interval: "d8")
		];

		fixtures.do { |f|

			this.assertEquals(
				MEMIDINote.getOffsetFromInterval(f.interval, validate: true),
				f.expected,
				"Testing valid interval: %. Should return %.".format(f.interval, f.expected)
			);
		};
	}

	/****************************************************************************************/

	test_getOffsetFromInterval_invalidInputUnder8th {
		var fixtures = ["P1", "d2", "P2", "P3", "m4", "M4", "m5", "M5", "P6", "P7", "A7"];

		fixtures.do { |f|

			this.assertException(
				{ MEMIDINote.getOffsetFromInterval(f, validate: true) },
				Error,
				"Testing invalid interval: %. Should throw Error.".format(f)
			);
		};
	}

	/****************************************************************************************/

	test_getOffsetFromInterval_invalidInputOver8th {
		var fixtures = ["P8", "d9", "P9", "P10", "m11", "M11", "m12", "M12", "P13", "P14", "A14"];

		fixtures.do { |f|

			this.assertException(
				{ MEMIDINote.getOffsetFromInterval(f, validate: true) },
				Error,
				"Testing invalid interval: %. Should throw Error.".format(f)
			);
		};
	}

	/****************************************************************************************/

	test_getOffsetFromInterval_invalidDataType {
		var fixtures = ['M2', 2, 0.2, $M, ["M2"]];

		fixtures.do { |f|

			this.assertException(
				{ MEMIDINote.getOffsetFromInterval(f, validate: true) },
				Error,
				"Testing invalid interval: %. Should throw Error.".format(f)
			);
		};
	}

	/****************************************************************************************/

	test_getOffsetFromName_validInputNoSign {
		var fixtures = [
			(expected: 0, noteName: "C"),
			(expected: 2, noteName: "D"),
			(expected: 4, noteName: "E"),
			(expected: 5, noteName: "F"),
			(expected: 7, noteName: "G"),
			(expected: 9, noteName: "A"),
			(expected: 11, noteName: "B")
		];

		fixtures.do { |f|

			this.assertEquals(
				MEMIDINote.getOffsetFromName(f.noteName, validate: true),
				f.expected,
				"Testing valid MIDI note: %. Should return %.".format(f.noteName, f.expected)
			);
		};
	}

	/****************************************************************************************/

	test_getOffsetFromName_validInputOneSharp {
		var fixtures = [
			(expected: 0, noteName: "B#"),
			(expected: 1, noteName: "C#"),
			(expected: 3, noteName: "D#"),
			(expected: 5, noteName: "E#"),
			(expected: 6, noteName: "F#"),
			(expected: 8, noteName: "G#"),
			(expected: 10, noteName: "A#")
		];

		fixtures.do { |f|

			this.assertEquals(
				MEMIDINote.getOffsetFromName(f.noteName, validate: true),
				f.expected,
				"Testing valid MIDI note: %. Should return %.".format(f.noteName, f.expected)
			);
		};
	}

	/****************************************************************************************/

	test_getOffsetFromName_validInputOneFlat {
		var fixtures = [
			(expected: 1, noteName: "Db"),
			(expected: 3, noteName: "Eb"),
			(expected: 4, noteName: "Fb"),
			(expected: 6, noteName: "Gb"),
			(expected: 8, noteName: "Ab"),
			(expected: 10, noteName: "Bb"),
			(expected: 11, noteName: "Cb")
		];

		fixtures.do { |f|

			this.assertEquals(
				MEMIDINote.getOffsetFromName(f.noteName, validate: true),
				f.expected,
				"Testing valid MIDI note: %. Should return %.".format(f.noteName, f.expected)
			);
		};
	}

	/****************************************************************************************/

	test_getOffsetFromName_validInputMultipleFlats {
		var fixtures = [
			(expected: 0, noteName: "C"),
			(expected: 11, noteName: "Cb"),
			(expected: 10, noteName: "Cbb"),
			(expected: 9, noteName: "Cbbb"),
			(expected: 8, noteName: "Cbbbb"),
			(expected: 7, noteName: "Cbbbbb")
		];

		fixtures.do { |f|

			this.assertEquals(
				MEMIDINote.getOffsetFromName(f.noteName, validate: true),
				f.expected,
				"Testing valid MIDI note: %. Should return %.".format(f.noteName, f.expected)
			);
		};
	}

	/****************************************************************************************/

	test_getOffsetFromName_validInputMultipleSharps {
		var fixtures = [
			(expected: 11, noteName: "B"),
			(expected: 0, noteName: "B#"),
			(expected: 1, noteName: "B##"),
			(expected: 2, noteName: "B###"),
			(expected: 3, noteName: "B####"),
			(expected: 4, noteName: "B#####")
		];

		fixtures.do { |f|

			this.assertEquals(
				MEMIDINote.getOffsetFromName(f.noteName, validate: true),
				f.expected,
				"Testing valid MIDI note: %. Should return %.".format(f.noteName, f.expected)
			);
		};
	}

	/****************************************************************************************/

	test_getOffsetFromName_invalidNoteName {
		var fixtures = ["C######", "Cbbbbbb", "X", "c", "1", "+"];

		fixtures.do { |f|

			this.assertException(
				{ MEMIDINote.getOffsetFromName(f, validate: true) },
				Error,
				"Testing invalid note name: %. Should throw Error.".format(f)
			);
		};
	}

	/****************************************************************************************/

	test_getOffsetFromName_invalidDataType {
		var fixtures = ['C', $C, 1, 1.0, ["C"]];

		fixtures.do { |f|

			this.assertException(
				{ MEMIDINote.getOffsetFromName(f, validate: true) },
				Error,
				"Testing invalid note name: %. Should throw Error.".format(f)
			);
		};
	}

	/****************************************************************************************/

	test_transposeMidiOffset_validInput {
		var fixtures = [
			(expected: [0, 4, 7, 10], midiOffsetArr: [0, 4, 7, 10], midiRoot: 0),
			(expected: [1, 5, 8, 11], midiOffsetArr: [0, 4, 7, 10], midiRoot: 1),
			(expected: [2, 6, 9, 12], midiOffsetArr: [0, 4, 7, 10], midiRoot: 2),
			(expected: [3, 7, 10, 13], midiOffsetArr: [0, 4, 7, 10], midiRoot: 3),
			(expected: [4, 8, 11, 14], midiOffsetArr: [0, 4, 7, 10], midiRoot: 4),
			(expected: [5, 9, 12, 15], midiOffsetArr: [0, 4, 7, 10], midiRoot: 5),
			(expected: [6, 10, 13, 16], midiOffsetArr: [0, 4, 7, 10], midiRoot: 6),
			(expected: [7, 11, 14, 17], midiOffsetArr: [0, 4, 7, 10], midiRoot: 7),
			(expected: [8, 12, 15, 18], midiOffsetArr: [0, 4, 7, 10], midiRoot: 8),
			(expected: [9, 13, 16, 19], midiOffsetArr: [0, 4, 7, 10], midiRoot: 9),
			(expected: [10, 14, 17, 20], midiOffsetArr: [0, 4, 7, 10], midiRoot: 10),
			(expected: [11, 15, 18, 21], midiOffsetArr: [0, 4, 7, 10], midiRoot: 11)
		];

		fixtures.do { |f|

			this.assertEquals(
				MEMIDINote.transposeMidiOffset(f.midiOffsetArr, f.midiRoot, validate: true),
				f.expected,
				"Testing valid MIDI offset array: %, MIDI root: %. Should return %.".format(f.midiOffsetArr, f.midiRoot, f.expected)
			);
		};
	}

	/****************************************************************************************/

	test_transposeMidiOffset_invalidArrayOutOfRange {
		var fixtures = [
			(midiOffsetArr: [-1, 4, 7, 10], midiRoot: 0),
			(midiOffsetArr: [0, 4, 7, 12], midiRoot: 0)
		];

		fixtures.do { |f|

			this.assertException(
				{ MEMIDINote.transposeMidiOffset(f.midiOffsetArr, f.midiRoot, validate: true) },
				Error,
				"Testing invalid MIDI offset array: %, midi root: %. Should throw Error.".format(f.midiOffsetArr, f.midiRoot)
			);
		};
	}

	/****************************************************************************************/

	test_transposeMidiOffset_invalidOffsetArrayDataType {
		var fixtures = [
			(midiOffsetArr: [0.0, 4, 7, 11], midiRoot: 0),
			(midiOffsetArr: ["0", 4, 7, 12], midiRoot: 0),
			(midiOffsetArr: ['0', 4, 7, 12], midiRoot: 0),
			(midiOffsetArr: [$0, 4, 7, 12], midiRoot: 0),
			(midiOffsetArr: [[0], 4, 7, 12], midiRoot: 0)
		];

		fixtures.do { |f|

			this.assertException(
				{ MEMIDINote.transposeMidiOffset(f.midiOffsetArr, f.midiRoot, validate: true) },
				Error,
				"Testing invalid MIDI offset array: %, midi root: %. Should throw Error.".format(f.midiOffsetArr, f.midiRoot)
			);
		};
	}

	/****************************************************************************************/

	test_transposeMidiOffset_invalidMIDIRootOutOfRange {
		var fixtures = [
			(midiOffsetArr: [0, 4, 7, 11], midiRoot: -1),
			(midiOffsetArr: [0, 4, 7, 11], midiRoot: 12)
		];

		fixtures.do { |f|

			this.assertException(
				{ MEMIDINote.transposeMidiOffset(f.midiOffsetArr, f.midiRoot, validate: true) },
				Error,
				"Testing invalid MIDI offset array: %, midi root: %. Should throw Error.".format(f.midiOffsetArr, f.midiRoot)
			);
		};
	}

	/****************************************************************************************/

	test_transposeMidiOffset_invalidMIDIRootDataType {
		var fixtures = [
			(midiOffsetArr: [0, 4, 7, 11], midiRoot: 1.0),
			(midiOffsetArr: [0, 4, 7, 11], midiRoot: "1"),
			(midiOffsetArr: [0, 4, 7, 11], midiRoot: '1'),
			(midiOffsetArr: [0, 4, 7, 11], midiRoot: $1),
			(midiOffsetArr: [0, 4, 7, 11], midiRoot: [1])
		];

		fixtures.do { |f|

			this.assertException(
				{ MEMIDINote.transposeMidiOffset(f.midiOffsetArr, f.midiRoot, validate: true) },
				Error,
				"Testing invalid MIDI offset array: %, midi root: %. Should throw Error.".format(f.midiOffsetArr, f.midiRoot)
			);
		};
	}
}