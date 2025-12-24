/*********************************************************************************************
* MusicEngine - A dynamic chord library for SuperCollider   								 *
* Copyright (C) 2025 Siriil									    							 *
* Licensed under GPLv3. See LICENSE file for details.			    						 *
*********************************************************************************************/

TestMENameValidators : UnitTest {

	test_noteNameIsValid_correctInputNoAccidentals {
		var fixtures = ["C", "D", "E", "F", "G", "A", "B"];

		fixtures.do { |f|

			this.assertEquals(
				MENameValidators.noteNameIsValid(f),
				nil,
				"Testing valid note name; %, as String. Should return 'nil'.".format(f)
			);
		};
	}

	/****************************************************************************************/

	test_noteNameIsValid_correctInputSharps {
		var fixtures = [
			"C#", "C#####", "D#", "D#####",
			"E#", "E#####", "F#", "F#####",
			"G#", "G#####", "A#", "A#####",
			"B#", "B#####"
		];

		fixtures.do { |f|

			this.assertEquals(
				MENameValidators.noteNameIsValid(f),
				nil,
				"Testing valid note name: %, as String. Should return 'nil'.".format(f)
			);
		};
	}

	/****************************************************************************************/

	test_noteNameIsValid_correctInputFlats {
		var fixtures = [
			"Cb", "Cbbbbb", "Db", "Dbbbbb",
			"Eb", "Ebbbbb", "Fb", "Fbbbbb",
			"Gb", "Gbbbbb", "Ab", "Abbbbb",
			"Bb", "Bbbbbb"
		];

		fixtures.do { |f|

			this.assertEquals(
				MENameValidators.noteNameIsValid(f),
				nil,
				"Testing valid note name: %, as String. Should return 'nil'.".format(f)
			);
		};
	}

	/****************************************************************************************/

	test_noteNameIsValid_validOctaveString {
		var fixtures = [
			"C-1", "Cbbbbb0", "D1", "D#####2",
			"E3",  "Ebbbbb4", "F5", "F#####6",
			"G7",  "Gbbbbb8", "A9"
		];

		fixtures.do { |f|

			this.assertEquals(
				MENameValidators.noteNameIsValid(f, octave: true),
				nil,
				"Testing valid note name with octave: %, as String. Should return 'nil'.".format(f)
			);
		};
	}

	/****************************************************************************************/

	test_noteNameIsValid_wrongInputString {
		var fixtures = [
			"C######", "D######", "E######", "F######", "G######", "A######", "B######",
			"Cbbbbbb", "Dbbbbbb", "Ebbbbbb", "Fbbbbbb", "Gbbbbbb", "Abbbbbb", "Bbbbbbb",
			"c", "d", "e", "f", "g", "a", "b", "X", "x", "+", "1"
		];

		fixtures.do { |f|

			this.assertException(
				{ MENameValidators.noteNameIsValid(f) },
				Error,
				"Testing invalid note name: %, as String. Should throw an Error.".format(f)
			);
		};
	}

	/****************************************************************************************/

	test_noteNameIsValid_invalidOctavesString {
		var fixtures = ["C-10", "D-2", "Eb-", "F#10", "G###4.0", "Abbb-1.0", "B####x"];

		fixtures.do { |f|

			this.assertException(
				{ MENameValidators.noteNameIsValid(f, octave: true) },
				Error,
				"Testing note name with invalid octave: %, as String. Should throw an Error.".format(f)
			);
		};
	}

	/****************************************************************************************/

	test_rootNoteIsValid_correctInputString {
		var fixtures = [
			"C",  "D",  "E",  "F",  "G",  "A",  "B",
			"C#", "D#", "E#", "F#", "G#", "A#", "B#",
			"Cb", "Db", "Eb", "Fb", "Gb", "Ab", "Bb"
		];

		fixtures.do { |f|

			this.assertEquals(
				MENameValidators.rootNoteIsValid(f),
				nil,
				"Testing valid root note: %, as String. Should return 'nil'.".format(f)
			);
		};
	}

	/****************************************************************************************/

	test_rootNoteIsValid_correctInputSymbol {
		var fixtures = [
			'C',  'D',  'E',  'F',  'G',  'A',  'B',
			'C#', 'D#', 'E#', 'F#', 'G#', 'A#', 'B#',
			'Cb', 'Db', 'Eb', 'Fb', 'Gb', 'Ab', 'Bb'
		];

		fixtures.do { |f|

			this.assertEquals(
				MENameValidators.rootNoteIsValid(f),
				nil,
				"Testing valid root note: %, as Symbol. Should return 'nil'.".format(f)
			);
		};
	}

	/****************************************************************************************/

	test_rootNoteIsValid_correctInputChar {
		var fixtures = [$C, $D, $E, $F, $G, $A, $B];

		fixtures.do { |f|

			this.assertEquals(
				MENameValidators.rootNoteIsValid(f),
				nil,
				"Testing valid root note: %, as Char. Should return 'nil'.".format(f)
			);
		};
	}

	/****************************************************************************************/

	test_rootNoteIsValid_invalidAccidentalString {
		var fixtures = [
			"C##", "D###", "E####", "F#####", "G######", "A#######", "B########",
			"Cbb", "Dbbb", "Ebbbb", "Fbbbbb", "Gbbbbbb", "Abbbbbbb", "Bbbbbbbbb"
		];

		fixtures.do { |f|

			this.assertException(
				{ MENameValidators.rootNoteIsValid(f) },
				Error,
				"Testing invalid root note: %, as String. Should throw an Error".format(f)
			);
		};
	}

	/****************************************************************************************/

	test_rootNoteIsValid_invalidAccidentalSymbol {
		var fixtures = [
			'C##', 'D###', 'E####', 'F#####', 'G######', 'A#######', 'B########',
			'Cbb', 'Dbbb', 'Ebbbb', 'Fbbbbb', 'Gbbbbbb', 'Abbbbbbb', 'Bbbbbbbbb'
		];

		fixtures.do { |f|

			this.assertException(
				{ MENameValidators.rootNoteIsValid(f) },
				Error,
				"Testing invalid root note: %, as Symbol. Should throw an Error.".format(f)
			);
		};
	}

	/****************************************************************************************/

	test_rootNoteIsValid_invalidNoteString {
		var fixtures = ["a", "b", "c", "d", "e", "f", "g", "1", "X", "+", "0.0"];

		fixtures.do { |f|

			this.assertException(
				{ MENameValidators.rootNoteIsValid(f) },
				Error,
				"Testing invalid root note: %, as String. Should throw an Error.".format(f)
			);
		};
	}

	/****************************************************************************************/

	test_rootNoteIsValid_invalidNoteSymbol {
		var fixtures = ['a', 'b', 'c', 'd', 'e', 'f', 'g', '1', 'X', '+', '0.0'];

		fixtures.do { |f|

			this.assertException(
				{ MENameValidators.rootNoteIsValid(f) },
				Error,
				"Testing invalid root note: %, as Symbol. Should throw an Error.".format(f)
			);
		};
	}

	/****************************************************************************************/

	test_rootNoteIsValid_invalidNoteChar {
		var fixtures = [$a, $b, $c, $d, $e, $f, $g, $1, $X, $+, $0];

		fixtures.do { |f|

			this.assertException(
				{ MENameValidators.rootNoteIsValid(f) },
				Error,
				"Testing invalid root note: %, as Char. Should throw an Error".format(f)
			);
		};
	}
}