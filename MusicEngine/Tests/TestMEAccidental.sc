TestMEAccidental : UnitTest {

	test_getOffsetFromName_validInputNoSign {
		var fixtures = [ "A", "B", "C", "D", "E", "F", "G"];

		fixtures.do { |f|

			this.assertEquals(
				0,
				MEAccidental.getOffsetFromName(f, validate: true),
				"Testing valid note name: %. Should return 0.".format(f)
			);
		};
	}

	/****************************************************************************************/

	test_getOffsetFromName_validInputOneFlat {
		var fixtures = [ "Ab", "Bb", "Cb", "Db", "Eb", "Fb", "Gb"];

		fixtures.do { |f|

			this.assertEquals(
				-1,
				MEAccidental.getOffsetFromName(f, validate: true),
				"Testing valid note name: %. Should return 0.".format(f)
			);
		};
	}

	/****************************************************************************************/

	test_getOffsetFromName_validInputOneSharp {
		var fixtures = [ "A#", "B#", "C#", "D#", "E#", "F#", "G#"];

		fixtures.do { |f|

			this.assertEquals(
				1,
				MEAccidental.getOffsetFromName(f, validate: true),
				"Testing valid note name: %. Should return 0.".format(f)
			);
		};
	}

	/****************************************************************************************/

	test_getOffsetFromName_validInputMultipleSigns {
		var fixtures = [
			(expected: -5, noteName: "Cbbbbb"),
			(expected: -4, noteName: "Cbbbb"),
			(expected: -3, noteName: "Cbbb"),
			(expected: -2, noteName: "Cbb"),
			(expected: -1, noteName: "Cb"),
			(expected: 0, noteName: "C"),
			(expected: 1, noteName: "C#"),
			(expected: 2, noteName: "C##"),
			(expected: 3, noteName: "C###"),
			(expected: 4, noteName: "C####"),
			(expected: 5, noteName: "C#####")
		];

		fixtures.do { |f|

			this.assertEquals(
				f.expected,
				MEAccidental.getOffsetFromName(f.noteName, validate: true),
				"Testing valid note name: %. Should return %.".format(f.noteName, f.expected)
			);
		};
	}

	/****************************************************************************************/

	test_getOffsetFromName_invalidNoteName {
		var fixtures = ["A######", "a", "Ax", "X", "1", "+"];

		fixtures.do { |f|

			this.assertException(
				{ MEAccidental.getOffsetFromName(f, validate: true) },
				Error,
				"Testing invalid note name: %. Should throw Error.".format(f)
			);
		};
	}

	/****************************************************************************************/

	test_getOffsetFromName_invalidDataType {
		var fixtures = [1, 1.0, $A, 'A', ["A"]];

		fixtures.do { |f|

			this.assertException(
				{ MEAccidental.getOffsetFromName(f, validate: true) },
				Error,
				"Testing invalid note name: %. Should throw Error.".format(f)
			);
		};
	}

	/****************************************************************************************/

	test_getOffsetFromMidi_validInputFirstOctaveNoSign {
		var fixtures = [
			(midiNote: 0, noteLetter: "C"),
			(midiNote: 2, noteLetter: "D"),
			(midiNote: 4, noteLetter: "E"),
			(midiNote: 5, noteLetter: "F"),
			(midiNote: 7, noteLetter: "G"),
			(midiNote: 9, noteLetter: "A"),
			(midiNote: 11, noteLetter: "B")
		];

		fixtures.do { |f|

			this.assertEquals(
				0,
				MEAccidental.getOffsetFromMidi(f.midiNote, f.noteLetter, validate: true),
				"Testing valid MIDI note: %, note letter: %. Should return 0.".format(f.midiNote, f.noteLetter)
			);
		};
	}

	/****************************************************************************************/

	test_getOffsetFromMidi_validInputFirstOctaveOneFlat {
		var fixtures = [
			(midiNote: 1, noteLetter: "D"),
			(midiNote: 3, noteLetter: "E"),
			(midiNote: 4, noteLetter: "F"),
			(midiNote: 6, noteLetter: "G"),
			(midiNote: 8, noteLetter: "A"),
			(midiNote: 10, noteLetter: "B"),
			(midiNote: 11, noteLetter: "C")
		];

		fixtures.do { |f|

			this.assertEquals(
				-1,
				MEAccidental.getOffsetFromMidi(f.midiNote, f.noteLetter, validate: true),
				"Testing valid MIDI note: %, note letter: %. Should return -1.".format(f.midiNote, f.noteLetter)
			);
		};
	}

	/****************************************************************************************/

	test_getOffsetFromMidi_validInputFirstOctaveOneSharp {
		var fixtures = [
			(midiNote: 0, noteLetter: "B"),
			(midiNote: 1, noteLetter: "C"),
			(midiNote: 3, noteLetter: "D"),
			(midiNote: 5, noteLetter: "E"),
			(midiNote: 6, noteLetter: "F"),
			(midiNote: 8, noteLetter: "G"),
			(midiNote: 10, noteLetter: "A"),
			(midiNote: 12, noteLetter: "B")
		];

		fixtures.do { |f|

			this.assertEquals(
				1,
				MEAccidental.getOffsetFromMidi(f.midiNote, f.noteLetter, validate: true),
				"Testing valid MIDI note: %, note letter: %. Should return 1.".format(f.midiNote, f.noteLetter)
			);
		};
	}

	/****************************************************************************************/

	test_getOffsetFromMidi_validInputFirstOctaveCMultipleSharps {
		var fixtures = [
			(expected: 0, midiNote: 0, noteLetter: "C"),
			(expected: 1, midiNote: 1, noteLetter: "C"),
			(expected: 2, midiNote: 2, noteLetter: "C"),
			(expected: 3, midiNote: 3, noteLetter: "C"),
			(expected: 4, midiNote: 4, noteLetter: "C"),
			(expected: 5, midiNote: 5, noteLetter: "C")
		];

		fixtures.do { |f|

			this.assertEquals(
				f.expected,
				MEAccidental.getOffsetFromMidi(f.midiNote, f.noteLetter, validate: true),
				"Testing valid MIDI note: %, note letter: %. Should return %.".format(f.midiNote, f.noteLetter, f.expected)
			);
		};
	}

	/****************************************************************************************/

	test_getOffsetFromMidi_validInputFirstOctaveCMultipleFlats {
		var fixtures = [
			(expected: 0, midiNote: 12, noteLetter: "C"),
			(expected: -1, midiNote: 11, noteLetter: "C"),
			(expected: -2, midiNote: 10, noteLetter: "C"),
			(expected: -3, midiNote: 9, noteLetter: "C"),
			(expected: -4, midiNote: 8, noteLetter: "C"),
			(expected: -5, midiNote: 7, noteLetter: "C")
		];

		fixtures.do { |f|

			this.assertEquals(
				f.expected,
				MEAccidental.getOffsetFromMidi(f.midiNote, f.noteLetter, validate: true),
				"Testing valid MIDI note: %, note letter: %. Should return %.".format(f.midiNote, f.noteLetter, f.expected)
			);
		};
	}

	/****************************************************************************************/

	test_getOffsetFromMidi_validInputLastOctaveNoSign {
		var fixtures = [
			(midiNote: 127, noteLetter: "G"),
			(midiNote: 125, noteLetter: "F"),
			(midiNote: 124, noteLetter: "E"),
			(midiNote: 122, noteLetter: "D"),
			(midiNote: 120, noteLetter: "C")
		];

		fixtures.do { |f|

			this.assertEquals(
				0,
				MEAccidental.getOffsetFromMidi(f.midiNote, f.noteLetter, validate: true),
				"Testing valid MIDI note: %, note letter: %. Should return 0.".format(f.midiNote, f.noteLetter)
			);
		};
	}

	/****************************************************************************************/

	test_getOffsetFromMidi_validInputLastOctaveOneFlat {
		var fixtures = [
			(midiNote: 126, noteLetter: "G"),
			(midiNote: 124, noteLetter: "F"),
			(midiNote: 123, noteLetter: "E"),
			(midiNote: 121, noteLetter: "D"),
			(midiNote: 119, noteLetter: "C")
		];

		fixtures.do { |f|

			this.assertEquals(
				-1,
				MEAccidental.getOffsetFromMidi(f.midiNote, f.noteLetter, validate: true),
				"Testing valid MIDI note: %, note letter: %. Should return -1.".format(f.midiNote, f.noteLetter)
			);
		};
	}

	/****************************************************************************************/

	test_getOffsetFromMidi_validInputLastOctaveOneSharp {
		var fixtures = [
			(midiNote: 126, noteLetter: "F"),
			(midiNote: 125, noteLetter: "E"),
			(midiNote: 123, noteLetter: "D"),
			(midiNote: 121, noteLetter: "C"),
			(midiNote: 120, noteLetter: "B")
		];

		fixtures.do { |f|

			this.assertEquals(
				1,
				MEAccidental.getOffsetFromMidi(f.midiNote, f.noteLetter, validate: true),
				"Testing valid MIDI note: %, note letter: %. Should return 1.".format(f.midiNote, f.noteLetter)
			);
		};
	}

	/****************************************************************************************/

	test_getOffsetFromMidi_validInputUpperEdgeMultipleFlats {
		var fixtures = [
			(expected: -2, midiNote: 127, noteLetter: "A"),
			(expected: -3, midiNote: 126, noteLetter: "A"),
			(expected: -4, midiNote: 125, noteLetter: "A"),
			(expected: -5, midiNote: 124, noteLetter: "A")
		];

		fixtures.do { |f|

			this.assertEquals(
				f.expected,
				MEAccidental.getOffsetFromMidi(f.midiNote, f.noteLetter, validate: true),
				"Testing valid MIDI note: %, note letter: %. Should return %.".format(f.midiNote, f.noteLetter, f.expected)
			);
		};
	}

	/****************************************************************************************/

	test_getOffsetFromMidi_validInputUpperEdgeMultipleSharps {
		var fixtures = [
			(expected: 0, midiNote: 122, noteLetter: "D"),
			(expected: 1, midiNote: 123, noteLetter: "D"),
			(expected: 2, midiNote: 124, noteLetter: "D"),
			(expected: 3, midiNote: 125, noteLetter: "D"),
			(expected: 4, midiNote: 126, noteLetter: "D"),
			(expected: 5, midiNote: 127, noteLetter: "D")
		];

		fixtures.do { |f|

			this.assertEquals(
				f.expected,
				MEAccidental.getOffsetFromMidi(f.midiNote, f.noteLetter, validate: true),
				"Testing valid MIDI note: %, note letter: %. Should return %.".format(f.midiNote, f.noteLetter, f.expected)
			);
		};
	}

	/****************************************************************************************/

	test_getOffsetFromMidi_validInputMiddleOctavesNoSign {
		var fixtures = [
			(midiNote: 12, noteLetter: "C"),
			(midiNote: 26, noteLetter: "D"),
			(midiNote: 40, noteLetter: "E"),
			(midiNote: 53, noteLetter: "F"),
			(midiNote: 67, noteLetter: "G"),
			(midiNote: 81, noteLetter: "A"),
			(midiNote: 95, noteLetter: "B"),
			(midiNote: 108, noteLetter: "C"),
		];

		fixtures.do { |f|

			this.assertEquals(
				0,
				MEAccidental.getOffsetFromMidi(f.midiNote, f.noteLetter, validate: true),
				"Testing valid MIDI note: %, note letter: %. Should return 0.".format(f.midiNote, f.noteLetter)
			);
		};
	}

	/****************************************************************************************/

	test_getOffsetFromMidi_validInputMiddleOctavesOneFlat {
		var fixtures = [
			(midiNote: 11, noteLetter: "C"),
			(midiNote: 25, noteLetter: "D"),
			(midiNote: 39, noteLetter: "E"),
			(midiNote: 52, noteLetter: "F"),
			(midiNote: 66, noteLetter: "G"),
			(midiNote: 80, noteLetter: "A"),
			(midiNote: 94, noteLetter: "B"),
			(midiNote: 107, noteLetter: "C"),
		];

		fixtures.do { |f|

			this.assertEquals(
				-1,
				MEAccidental.getOffsetFromMidi(f.midiNote, f.noteLetter, validate: true),
				"Testing valid MIDI note: %, note letter: %. Should return -1.".format(f.midiNote, f.noteLetter)
			);
		};
	}

	/****************************************************************************************/

	test_getOffsetFromMidi_validInputMiddleOctavesOneSharp {
		var fixtures = [
			(midiNote: 13, noteLetter: "C"),
			(midiNote: 27, noteLetter: "D"),
			(midiNote: 41, noteLetter: "E"),
			(midiNote: 54, noteLetter: "F"),
			(midiNote: 68, noteLetter: "G"),
			(midiNote: 82, noteLetter: "A"),
			(midiNote: 96, noteLetter: "B"),
			(midiNote: 109, noteLetter: "C"),
		];

		fixtures.do { |f|

			this.assertEquals(
				1,
				MEAccidental.getOffsetFromMidi(f.midiNote, f.noteLetter, validate: true),
				"Testing valid MIDI note: %, note letter: %. Should return 1.".format(f.midiNote, f.noteLetter)
			);
		};
	}

	/****************************************************************************************/

	test_getOffsetFromMidi_validInputMiddleOctavesMultipleFlats {
		var fixtures = [
			(expected: 0, midiNote: 12, noteLetter: "C"),
			(expected: -1, midiNote: 23, noteLetter: "C"),
			(expected: -2, midiNote: 34, noteLetter: "C"),
			(expected: -3, midiNote: 45, noteLetter: "C"),
			(expected: -4, midiNote: 56, noteLetter: "C"),
			(expected: -5, midiNote: 67, noteLetter: "C")
		];

		fixtures.do { |f|

			this.assertEquals(
				f.expected,
				MEAccidental.getOffsetFromMidi(f.midiNote, f.noteLetter, validate: true),
				"Testing valid MIDI note: %, note letter: %. Should return %.".format(f.midiNote, f.noteLetter, f.expected)
			);
		};
	}

	/****************************************************************************************/

	test_getOffsetFromMidi_validInputMiddleOctavesMultipleSharps {
		var fixtures = [
			(expected: 0, midiNote: 48, noteLetter: "C"),
			(expected: 1, midiNote: 61, noteLetter: "C"),
			(expected: 2, midiNote: 74, noteLetter: "C"),
			(expected: 3, midiNote: 87, noteLetter: "C"),
			(expected: 4, midiNote: 100, noteLetter: "C"),
			(expected: 5, midiNote: 113, noteLetter: "C")
		];

		fixtures.do { |f|

			this.assertEquals(
				f.expected,
				MEAccidental.getOffsetFromMidi(f.midiNote, f.noteLetter, validate: true),
				"Testing valid MIDI note: %, note letter: %. Should return %.".format(f.midiNote, f.noteLetter, f.expected)
			);
		};
	}

	/****************************************************************************************/

	test_getOffsetFromMidi_invalidMIDINote {
		var fixtures = [-1, 128, 1.0, "1", '1'];

		fixtures.do { |f|

			this.assertException(
				{ MEAccidental.getOffsetFromMidi(f, "C", validate: true) },
				Error,
				"Testing invalid MIDI note: %, with valid note letter: C. Should throw Error.".format(f)
			);
		};
	}

	/****************************************************************************************/

	test_getOffsetFromMidi_invalidNoteLetter {
		var fixtures = ["a", 'a', "X", "1", "+", $1, ["A"]];

		fixtures.do { |f|

			this.assertException(
				{ MEAccidental.getOffsetFromMidi(60, f, validate: true) },
				Error,
				"Testing valid MIDI note: 60, with valid note letter: %. Should throw Error.".format(f)
			);
		};
	}
}