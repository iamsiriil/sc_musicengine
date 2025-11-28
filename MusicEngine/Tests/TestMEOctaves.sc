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
				f.expected,
				MEOctaves.getClosestOctave(f.midi, f.letter),
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
				f.expected,
				MEOctaves.getClosestOctave(f.midi, f.letter),
				"Expected: %, for MIDI: % letter: %.".format(f.expected, f.midi, f.letter)
			);
		};
	}

	/****************************************************************************************/

	test_getClosestOctave_correctInputLowerEdge {
		var fixtures = [
			(expected: -1, midi: -1, letter: "B"),
			(expected: -1, midi: 4, letter: "B"),
			(expected: 0, midi: -1, letter: "C"),
			(expected: 0, midi: 0, letter: "C"),
			(expected: 0, midi: 5, letter: "C"),
			(expected: 2, midi: -1, letter: "D"),
			(expected: 2, midi: 2, letter: "D"),
			(expected: 2, midi: 7, letter: "D"),
			(expected: 4, midi: -1, letter: "E"),
			(expected: 4, midi: 4, letter: "E"),
			(expected: 4, midi: 9, letter: "E")
		];

		fixtures.do { |f|

			this.assertEquals(
				f.expected,
				MEOctaves.getClosestOctave(f.midi, f.letter),
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
				f.expected,
				MEOctaves.getClosestOctave(f.midi, f.letter),
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
				{ MEOctaves.getClosestOctave(f.midi, f.letter) },
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
				{ MEOctaves.getClosestOctave(f.midi, f.letter) },
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
			(midi: -1, letter: "F"),
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
				nil,
				MEOctaves.getClosestOctave(f.midi, f.letter),
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
				nil,
				MEOctaves.getClosestOctave(f.midi, f.letter),
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
				nil,
				MEOctaves.getClosestOctave(f.midi, f.letter),
				"Expected: nil for MIDI: % letter: %".format(f.midi, f.letter)
			);
		};

	}

	/****************************************************************************************/

	test_getClosestOctave_rangeInvariant {
		var letters = MECore.letters;
		var result;

		(-1..127).do { |midi|

			letters.do { |letter|
				result = MEOctaves.getClosestOctave(midi, letter);

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

		(-1..127).do { |midi|

			letters.do { |letter|
				result = MEOctaves.getClosestOctave(midi, letter);

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
			letter = letters.choose;

			result1 = MEOctaves.getClosestOctave(midi, letter);
			result2 = MEOctaves.getClosestOctave(midi, letter);

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
			note   = MECore.noteFromLetter(letter);

			expected = note + octave;
			testNote = expected + offset;

			this.assertEquals(
				expected,
				MEOctaves.getClosestOctave(testNote, letter),
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
				{ MEOctaves.getClosestOctave(pick, "C") },
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
			(expected: -1, noteName: "Ebbbbb"),
			(expected: -1, noteName: "Fbbbbbb"),
			(expected: -1, noteName: "Gbbbbbbbb"),
			(expected: -1, noteName: "Abbbbbbbbbb"),
			(expected: -1, noteName: "Bbbbbbbbbbbbb")
		];

		fixtures.do { |f|

			this.assertEquals(
				f.expected,
				MEOctaves.checkOctaveCross(f.noteName),
				"Expected % from note name %.".format(f.expected, f.noteName)
			);
		};
	}

	/****************************************************************************************/

	test_checkOctaveCross_crossOver {
		var fixtures = [
			(expected: 1, noteName: "C############"),
			(expected: 1, noteName: "D##########"),
			(expected: 1, noteName: "E########"),
			(expected: 1, noteName: "F#######"),
			(expected: 1, noteName: "G#####"),
			(expected: 1, noteName: "A###"),
			(expected: 1, noteName: "B#")
		];

		fixtures.do { |f|

			this.assertEquals(
				f.expected,
				MEOctaves.checkOctaveCross(f.noteName),
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
			(expected: 0, noteName: "C###########"),
			(expected: 0, noteName: "D#########"),
			(expected: 0, noteName: "E#######"),
			(expected: 0, noteName: "F######"),
			(expected: 0, noteName: "G####"),
			(expected: 0, noteName: "A##"),
			(expected: 0, noteName: "Dbb"),
			(expected: 0, noteName: "Ebbbb"),
			(expected: 0, noteName: "Fbbbbb"),
			(expected: 0, noteName: "Gbbbbbbb"),
			(expected: 0, noteName: "Abbbbbbbbb"),
			(expected: 0, noteName: "Bbbbbbbbbbbb")
		];

		fixtures.do { |f|

			this.assertEquals(
				f.expected,
				MEOctaves.checkOctaveCross(f.noteName),
				"Expected % from note name %.".format(f.expected, f.noteName)
			);
		};
	}

	/****************************************************************************************/
}
