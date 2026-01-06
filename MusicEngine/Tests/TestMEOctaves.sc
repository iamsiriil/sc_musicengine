/*********************************************************************************************
* MusicEngine - A dynamic chord library for SuperCollider   								 *
* Copyright (C) 2025 Siriil									    							 *
* Licensed under GPLv3. See LICENSE file for details.			    						 *
*********************************************************************************************/

TestMEOctaves : UnitTest {

	test_getClosestOctave_correctInputMiddleOctaves {
		var fixtures = [
			(expected: 12, midi: 7, letter: "C"),
			(expected: 12, midi: 12, letter: "C"),
			(expected: 12, midi: 17, letter: "C"),
			(expected: 26, midi: 21, letter: "D"),
			(expected: 26, midi: 26, letter: "D"),
			(expected: 26, midi: 31, letter: "D"),
			(expected: 40, midi: 35, letter: "E"),
			(expected: 40, midi: 40, letter: "E"),
			(expected: 40, midi: 45, letter: "E"),
			(expected: 53, midi: 48, letter: "F"),
			(expected: 53, midi: 53, letter: "F"),
			(expected: 53, midi: 58, letter: "F"),
			(expected: 67, midi: 62, letter: "G"),
			(expected: 67, midi: 67, letter: "G"),
			(expected: 67, midi: 72, letter: "G"),
			(expected: 81, midi: 76, letter: "A"),
			(expected: 81, midi: 81, letter: "A"),
			(expected: 81, midi: 86, letter: "A"),
			(expected: 95, midi: 90, letter: "B"),
			(expected: 95, midi: 95, letter: "B"),
			(expected: 95, midi: 100, letter: "B"),
			(expected: 108, midi: 103, letter: "C"),
			(expected: 108, midi: 108, letter: "C"),
			(expected: 108, midi: 113, letter: "C")
		];

		fixtures.do { |f|

			this.assertEquals(
				MEOctaves.getClosestOctave(f.midi, f.letter, validate: true),
				f.expected,
				"Expected: %, for MIDI: % letter: %.".format(f.expected, f.midi, f.letter)
			);
		};
	}

	/****************************************************************************************/

	test_getClosestOctave_correctInputFirstOctave {
		var fixtures = [
			(expected: 5, midi: 0, letter: "F"),
			(expected: 5, midi: 5, letter: "F"),
			(expected: 5, midi: 10, letter: "F"),
			(expected: 7, midi: 2, letter: "G"),
			(expected: 7, midi: 7, letter: "G"),
			(expected: 7, midi: 12, letter: "G"),
			(expected: 9, midi: 4, letter: "A"),
			(expected: 9, midi: 9, letter: "A"),
			(expected: 9, midi: 14, letter: "A"),
			(expected: 11, midi: 6, letter: "B"),
			(expected: 11, midi: 11, letter: "B"),
			(expected: 11, midi: 16, letter: "B")
		];

		fixtures.do { |f|

			this.assertEquals(
				MEOctaves.getClosestOctave(f.midi, f.letter, validate: true),
				f.expected,
				"Expected: %, for MIDI: % letter: %.".format(f.expected, f.midi, f.letter)
			);
		};
	}

	/****************************************************************************************/

	test_getClosestOctave_correctInputLowerEdge {
		var fixtures = [
			(expected: -1, midi: 4, letter: "B"),
			(expected: 0, midi: 0, letter: "C"),
			(expected: 0, midi: 5, letter: "C"),
			(expected: 2, midi: 2, letter: "D"),
			(expected: 2, midi: 7, letter: "D"),
			(expected: 4, midi: 4, letter: "E"),
			(expected: 4, midi: 9, letter: "E")
		];

		fixtures.do { |f|

			this.assertEquals(
				MEOctaves.getClosestOctave(f.midi, f.letter, validate: true),
				f.expected,
				"Expected: %, for MIDI: % letter: %.".format(f.expected, f.midi, f.letter)
			);
		};
	}

	/****************************************************************************************/

	test_getClosestOctave_correctInputUpperEdge {
		var fixtures = [
			(expected: 122, midi: 117, letter: "D"),
			(expected: 122, midi: 122, letter: "D"),
			(expected: 122, midi: 127, letter: "D"),
			(expected: 124, midi: 119, letter: "E"),
			(expected: 124, midi: 124, letter: "E"),
			(expected: 124, midi: 127, letter: "E"),
			(expected: 125, midi: 120, letter: "F"),
			(expected: 125, midi: 125, letter: "F"),
			(expected: 125, midi: 127, letter: "F"),
			(expected: 127, midi: 122, letter: "G"),
			(expected: 127, midi: 127, letter: "G"),
			(expected: 129, midi: 127, letter: "A"),
		];

		fixtures.do { |f|

			this.assertEquals(
				MEOctaves.getClosestOctave(f.midi, f.letter, validate: true),
				f.expected,
				"Expected: %, for MIDI: % letter: %.".format(f.expected, f.midi, f.letter)
			);
		};
	}

	/****************************************************************************************/

	test_getClosestOctave_invalidMIDI {
		var fixtures = [
			(midi: -2, letter: "C"),
			(midi: -102, letter: "C"),
			(midi: -202, letter: "C"),
			(midi: -302, letter: "C"),
			(midi: 128, letter: "C"),
			(midi: 228, letter: "C"),
			(midi: 328, letter: "C"),
			(midi: 428, letter: "C"),
			(midi: 0.5, letter: "C"),
			(midi: "2", letter: "C"),
			(midi: '2', letter: "C"),
			(midi: [2], letter: "C")
		];

		fixtures.do { |f|

			this.assertException(
				{ MEOctaves.getClosestOctave(f.midi, f.letter, validate: true) },
				Error,
				"Expected Error for Midi: %".format(f.midi)
			);
		};
	}

	/****************************************************************************************/

	test_getClosestOctave_invalidLetter {
		var fixtures = [
			(midi: 0, letter: "Q"),
			(midi: 0, letter: "X"),
			(midi: 0, letter: ["C"]),
			(midi: 0, letter: 1),
			(midi: 0, letter: 0.5),
			(midi: 0, letter: ">")
		];

		fixtures.do { |f|

			this.assertException(
				{ MEOctaves.getClosestOctave(f.midi, f.letter, validate: true) },
				Error,
				"Expected Error for letter value: %".format(f.letter)
			);
		};
	}

	/****************************************************************************************/

	test_getClosestOctave_tritoneExceptionLowerEdge {
		var fixtures = [
			(midi: 5, letter: "B"),
			(midi: 6, letter: "C"),
			(midi: 8, letter: "D"),
			(midi: 10, letter: "E"),
			(midi: 11, letter: "F"),
			(midi: 1, letter: "G"),
			(midi: 13, letter: "G"),
			(midi: 3, letter: "A"),
			(midi: 15, letter: "A"),
			(midi: 5, letter: "B"),
			(midi: 17, letter: "B")
		];

		fixtures.do { |f|

			this.assertEquals(
				MEOctaves.getClosestOctave(f.midi, f.letter, validate: true),
				nil,
				"Expected: nil for MIDI: % letter: %".format(f.midi, f.letter)
			);
		};
	}

	/****************************************************************************************/

	test_getClosestOctave_tritoneExceptionUpperEdge {
		var fixtures = [
			(midi: 113, letter: "B"),
			(midi: 125, letter: "B"),
			(midi: 114, letter: "C"),
			(midi: 126, letter: "C"),
			(midi: 116, letter: "D"),
			(midi: 118, letter: "E"),
			(midi: 119, letter: "F"),
			(midi: 121, letter: "G"),
		];

		fixtures.do { |f|

			this.assertEquals(
				MEOctaves.getClosestOctave(f.midi, f.letter, validate: true),
				nil,
				"Expected: nil for MIDI: % letter: %".format(f.midi, f.letter)
			);
		};
	}

	/****************************************************************************************/

	test_getClosestOctave_tritoneExceptionMiddleOctaves {
		var fixtures = [
			(midi: 6, letter: "C"),
			(midi: 18, letter: "C"),
			(midi: 20, letter: "D"),
			(midi: 32, letter: "D"),
			(midi: 34, letter: "E"),
			(midi: 46, letter: "E"),
			(midi: 47, letter: "F"),
			(midi: 59, letter: "F"),
			(midi: 61, letter: "G"),
			(midi: 73, letter: "G"),
			(midi: 75, letter: "A"),
			(midi: 87, letter: "A"),
			(midi: 89, letter: "B"),
			(midi: 101, letter: "B"),
			(midi: 102, letter: "C"),
			(midi: 114, letter: "C")
		];

		fixtures.do { |f|

			this.assertEquals(
				MEOctaves.getClosestOctave(f.midi, f.letter, validate: true),
				nil,
				"Expected: nil for MIDI: % letter: %".format(f.midi, f.letter)
			);
		};

	}

	/****************************************************************************************/

	test_getClosestOctave_rangeInvariant {
		var letters = MECore.letters;
		var result;

		(0..127).do { |midi|

			letters.do { |letter|
				result = MEOctaves.getClosestOctave(midi, letter.asString, validate: true);

				if (result.notNil) {

					this.assert(
						(result >= -1) && (result <= 129),
						"Result: %, within MIDI range.".format(result)
					);
				};
			};
		};
	}

	/****************************************************************************************/

	test_getClosestOctave_neverCrossesTritone {
		var letters = MECore.letters;
		var result;

		(0..127).do { |midi|

			letters.do { |letter|
				result = MEOctaves.getClosestOctave(midi, letter.asString, validate: true);

				if (result.notNil) {

					this.assert(
						(result - midi).abs <= 5,
						"Expected difference between MIDI and result no larger than 5. Diff: %".format((result - midi))
					);
				};
			};
		};
	}

	/****************************************************************************************/
	// RANDOM TESTS

	test_getClosestOctave_deterministic {
		var letters = MECore.letters;
		var midi, letter;
		var result1, result2;

		100.do {
			midi   = (0..127).choose;
			letter = letters.choose.asString;

			result1 = MEOctaves.getClosestOctave(midi, letter, validate: true);
			result2 = MEOctaves.getClosestOctave(midi, letter, validate: true);

			this.assertEquals(
				result1,
				result2,
				"Expected %".format(result1)
			);
		};
	}

	/****************************************************************************************/

	test_getClosestOctave_randomCorrectness09 {
		var index, octave, offset;
		var letter, note;
		var expected, testNote;

		100.do {
			index  = (0..6).choose;
			octave = (1..9).choose * 12;
			offset = (-5..5).choose;

			letter = MECore.letters[index];
			note   = MECore.offsetFromLetter(letter.asString);

			expected = note + octave;
			testNote = expected + offset;

			this.assertEquals(
				MEOctaves.getClosestOctave(testNote, letter.asString, validate: true),
				expected,
				"Expected: %, for MIDI: % letter: %".format(expected, testNote, letter)
			);
		};
	}

	/****************************************************************************************/

	test_getClosestOctave_randomMIDIValidation {
		var fixtures = ((-100..-4) ++ (128..224));

		100.do { |m|
			var pick = fixtures.choose;

			this.assertException(
				{ MEOctaves.getClosestOctave(pick, "C", validate: true) },
				Error,
				"Expected Error for MIDI: %.".format(pick)
			);
		};
	}

	/****************************************************************************************/
	// Tests for checkOctaveCross

	test_checkOctaveCross_crossUnder {
		var fixtures = [
			(expected: -1, noteName: "Cb"),
			(expected: -1, noteName: "Dbbb"),
			(expected: -1, noteName: "Ebbbbb")
		];

		fixtures.do { |f|

			this.assertEquals(
				MEOctaves.checkOctaveCross(f.noteName, validate: true),
				f.expected,
				"Expected % from note name %.".format(f.expected, f.noteName)
			);
		};
	}

	/****************************************************************************************/

	test_checkOctaveCross_crossOver {
		var fixtures = [
			(expected: 1, noteName: "G#####"),
			(expected: 1, noteName: "A###"),
			(expected: 1, noteName: "B#")
		];

		fixtures.do { |f|

			this.assertEquals(
				MEOctaves.checkOctaveCross(f.noteName, validate: true),
				f.expected,
				"Expected % from note name %.".format(f.expected, f.noteName)
			);
		};
	}

	/****************************************************************************************/

	test_checkOctaveCross_noCross {
		var fixtures = [
			(expected: 0, noteName: "C"),
			(expected: 0, noteName: "D"),
			(expected: 0, noteName: "E"),
			(expected: 0, noteName: "F"),
			(expected: 0, noteName: "G"),
			(expected: 0, noteName: "A"),
			(expected: 0, noteName: "B"),
			(expected: 0, noteName: "G####"),
			(expected: 0, noteName: "A##"),
			(expected: 0, noteName: "Dbb"),
			(expected: 0, noteName: "Ebbbb"),
			(expected: 0, noteName: "Fbbbbb")
		];

		fixtures.do { |f|

			this.assertEquals(
				MEOctaves.checkOctaveCross(f.noteName, validate: true),
				f.expected,
				"Expected % from note name %.".format(f.expected, f.noteName)
			);
		};
	}

	/****************************************************************************************/

	test_checkOctaveCross_invalidNoteName {
		var fixtures = ["X", "a", "Dxx", "1", "+"];

		fixtures.do { |f|

			this.assertException(
				{ MEOctaves.checkOctaveCross(f, validate: true) },
				Error,
				"Testing invalid note name: %. Should throw Error.".format(f)
			);
		};
	}

	/****************************************************************************************/

	test_getOctave_validInputFirstOctave {
		var fixtures = (0..11);

		fixtures.do { |f|

			this.assertEquals(
				MEOctaves.getOctave(f, validate: true),
				-1,
				"Testing valid MIDI note: %. Should return -1.".format(f)
			);
		};
	}

	/****************************************************************************************/

	test_getOctave_validInputLastOctave {
		var fixtures = (120..127);

		fixtures.do { |f|

			this.assertEquals(
				MEOctaves.getOctave(f, validate: true),
				9,
				"Testing valid MIDI note: %. Should return 9.".format(f)
			);
		};
	}

	/****************************************************************************************/

	test_getOctave_validInputMiddleOctave {
		var fixtures = (60..71);

		fixtures.do { |f|

			this.assertEquals(
				MEOctaves.getOctave(f, validate: true),
				4,
				"Testing valid MIDI note: %. Should return 4.".format(f)
			);
		};
	}

	/****************************************************************************************/

	test_getOctave_validInputCFlat {
		var fixtures = [
			(expected: 0, midiNote: 11, noteName: "Cb"),
			(expected: 1, midiNote: 23, noteName: "Cb"),
			(expected: 2, midiNote: 35, noteName: "Cb"),
			(expected: 3, midiNote: 47, noteName: "Cb"),
			(expected: 4, midiNote: 59, noteName: "Cb"),
			(expected: 5, midiNote: 71, noteName: "Cb"),
			(expected: 6, midiNote: 83, noteName: "Cb"),
			(expected: 7, midiNote: 95, noteName: "Cb"),
			(expected: 8, midiNote: 107, noteName: "Cb"),
			(expected: 9, midiNote: 119, noteName: "Cb")
		];

		fixtures.do { |f|

			this.assertEquals(
				MEOctaves.getOctave(f.midiNote, f.noteName, validate: true),
				f.expected,
				"Testing valid MIDI note: % note name: %. Should return %.".format(f.midiNote, f.noteName, f.expected)
			);
		};
	}

	/****************************************************************************************/

	test_getOctave_validInputBSharp {
		var fixtures = [
			(expected: -1, midiNote: 12, noteName: "B#"),
			(expected: 0, midiNote: 24, noteName: "B#"),
			(expected: 1, midiNote: 36, noteName: "B#"),
			(expected: 2, midiNote: 48, noteName: "B#"),
			(expected: 3, midiNote: 60, noteName: "B#"),
			(expected: 4, midiNote: 72, noteName: "B#"),
			(expected: 5, midiNote: 84, noteName: "B#"),
			(expected: 6, midiNote: 96, noteName: "B#"),
			(expected: 7, midiNote: 108, noteName: "B#"),
			(expected: 8, midiNote: 120, noteName: "B#")
		];

		fixtures.do { |f|

			this.assertEquals(
				MEOctaves.getOctave(f.midiNote, f.noteName, validate: true),
				f.expected,
				"Testing valid MIDI note: % note name: %. Should return %.".format(f.midiNote, f.noteName, f.expected)
			);
		};
	}

	/****************************************************************************************/

	test_getOctave_invalidMIDINote {
		var fixtures = [-1, 128, 1.0, "1", '1'];

		fixtures.do { |f|

			this.assertException(
				{ MEOctaves.getOctave(f, validate: true) },
				Error,
				"Testing invalid MIDI note: %. Should throw Error.".format(f)
			);
		};
	}

	/****************************************************************************************/

	test_getOctave_invalidNoteName {
		var fixtures = [
			(midiNote: 60, noteName: "X#"),
			(midiNote: 60, noteName: "a"),
			(midiNote: 60, noteName: "+"),
			(midiNote: 60, noteName: "1"),
			(midiNote: 60, noteName: "C######")
		];

		fixtures.do { |f|

			this.assertException(
				{ MEOctaves.getOctave(f.midiNote, f.noteName, validate: true) },
				Error,
				"Testing invalid note name: %. Should throw Error.".format(f.noteName)
			);
		};
	}

	/****************************************************************************************/

	test_getOctave_invalidMIDINamePair {
		var fixtures = [
			(midiNote: 60, noteName: "D"),
			(midiNote: 60, noteName: "Eb"),
			(midiNote: 60, noteName: "B"),
			(midiNote: 60, noteName: "A#")
		];

		fixtures.do { |f|

			this.assertException(
				{ MEOctaves.getOctave(f.midiNote, f.noteName, validate: true) },
				Error,
				"Testing invalid MIDI note: % note name: % pair. Should throw Error.".format(f.midiNote, f.noteName)
			);
		};
	}
}
