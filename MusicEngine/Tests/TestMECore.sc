
/*********************************************************************************************
* MusicEngine - A dynamic chord library for SuperCollider   								 *
* Copyright (C) 2025 Siriil									    							 *
* Licensed under GPLv3. See LICENSE file for details.			    						 *
*********************************************************************************************/

TestMECore : UnitTest {
	classvar letters;
	classvar offsets;

	setUp {
		letters = MECore.letters;
		offsets = MECore.offsets;
	}

	/****************************************************************************************/

	test_letters_dataCorrectness {

		this.assertEquals(
			MECore.letters,
			["C", "D", "E", "F", "G", "A", "B"],
			"Testing letters array for data correctness."
		);
	}

	/****************************************************************************************/

	test_offsets_dataCorrectness {

		this.assertEquals(
			MECore.offsets,
			[0, 2, 4, 5, 7, 9, 11],
			"Testing offsets array for data correctness."
		);
	}

	/****************************************************************************************/

	test_indexOfLetter_validNoteLetters {
		var fixtures = [
			(expected: 0, noteLetter: "C"),
			(expected: 1, noteLetter: "D"),
			(expected: 2, noteLetter: "E"),
			(expected: 3, noteLetter: "F"),
			(expected: 4, noteLetter: "G"),
			(expected: 5, noteLetter: "A"),
			(expected: 6, noteLetter: "B"),
		];

		fixtures.do { |f|

			this.assertEquals(
				MECore.indexOfLetter(f.noteLetter, validate: true),
				f.expected,
				"Testing valid note letter: %. Should return: %.".format(f.noteLetter, f.expected)
			);
		};
	}

	/****************************************************************************************/

	test_indexOfLetter_invalidNoteLetter {
		var fixture = ["X", "c", "+", "1", "", " "];

		fixture.do { |f|

			this.assertException(
				{ MECore.indexOfLetter(f, validate: true) },
				Error,
				"Testing invalid note letter: %. Should throw Error.".format(f)
			);
		};
	}

	/****************************************************************************************/

	test_indexOfLetter_invalidDataType {
		var fixture = ['C', $C, 1, 1.0, ["C"]];

		fixture.do { |f|

			this.assertException(
				{ MECore.indexOfLetter(f, validate: true) },
				Error,
				"Testing invalid data type: %. Should throw Error.".format(f.class)
			);
		};
	}

	/****************************************************************************************/

	test_indexOfOffset_validMidiOffsets {
		var fixtures = [
			(expected: 0, midiOffset: 0),
			(expected: 1, midiOffset: 2),
			(expected: 2, midiOffset: 4),
			(expected: 3, midiOffset: 5),
			(expected: 4, midiOffset: 7),
			(expected: 5, midiOffset: 9),
			(expected: 6, midiOffset: 11),
		];

		fixtures.do { |f|

			this.assertEquals(
				MECore.indexOfOffset(f.midiOffset, validate: true),
				f.expected,
				"Testing valid midi offset: %. Should return: %.".format(f.midiOffset, f.expected)
			);
		};
	}

	/****************************************************************************************/

	test_indexOfOffset_invalidMidiOffset {
		var fixtures = [1, 12, -1, 1000];

		fixtures.do { |f|

			this.assertException(
				{ MECore.indexOfOffset(f, validate: true) },
				Error,
				"Testing invalid midi offset: %. Should throw Error.".format(f)
			);
		};
	}

	/****************************************************************************************/

	test_indexOfOffset_invalidDataType {
		var fixtures = [1.0, "1", '1', $1, [1]];

		fixtures.do { |f|

			this.assertException(
				{ MECore.indexOfOffset(f, validate: true) },
				Error,
				"Testing invalid data type: %. Should throw Error.".format(f.class)
			);
		};
	}

	/****************************************************************************************/

	test_offsetFromLetter_mappingCorrectness {
		var index;

		letters.do { |l|
			index = letters.indexOf(l);

			this.assertEquals(
				MECore.offsetFromLetter(l.asString, validate: true),
				offsets[index],
				"Testing mapping correctness between letters and offsets."
			);
		};
	}

	/****************************************************************************************/

	test_offsetFromLetter_validNoteLetter {
		var fixtures = [
			(expected: 0, noteLetter: "C"),
			(expected: 2, noteLetter: "D"),
			(expected: 4, noteLetter: "E"),
			(expected: 5, noteLetter: "F"),
			(expected: 7, noteLetter: "G"),
			(expected: 9, noteLetter: "A"),
			(expected: 11, noteLetter: "B"),
		];

		fixtures.do { |f|

			this.assertEquals(
				MECore.offsetFromLetter(f.noteLetter, validate: true),
				f.expected,
				"Testing valid note letter: %. Should return: %.".format(f.noteLetter, f.expected)
			);
		};
	}

	/****************************************************************************************/

	test_offsetFromLetter_invalidNoteLetter {
		var fixtures = ["X", "c", "+", "1", "", " "];

		fixtures.do { |f|

			this.assertException(
				{ MECore.offsetFromLetter(f, validate: true) },
				Error,
				"Testing invalid String: %. Should throw Error.".format(f)
			);
		};
	}

	/****************************************************************************************/

	test_offsetFromLetter_roundTrip {
		var offset;

		letters.do { |l|
			offset = MECore.offsetFromLetter(l.asString, validate: true);

			this.assertEquals(
				MECore.letterFromOffset(offset, validate: true),
				l,
				"letter: %, should be equal to letterFromNote(noteFromLetter(%)).".format(l, l);
			);
		};
	}

	/****************************************************************************************/

	test_letterFromOffset_mappingCorrectness {

		offsets.do { |o, i|

			this.assertEquals(
				MECore.letterFromOffset(o, validate: true),
				letters[i],
				"Testing mapping correctness between offsets and letters."
			);
		};
	}

	/****************************************************************************************/

	test_letterFromOffset_invalidOffset {
		var fixtures = [1, 12, -1, 1000];

		fixtures.do { |f|

			this.assertException(
				{ MECore.letterFromOffset(f, validate: true) },
				Error,
				"Testing invalid offser: %. Should throw Error.".format(f)
			);
		};
	}

	/****************************************************************************************/

	test_letterFromOffset_roundTrip {
		var letter;

		offsets.do { |o|
			letter = MECore.letterFromOffset(o, validate: true);

			this.assertEquals(
				MECore.offsetFromLetter(letter.asString, validate: true),
				o,
				"offset: %, should be equal to offsetFromLetter(letterFromOffset(%)).".format(o, o)
			);
		};
	}
}