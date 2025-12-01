/*********************************************************************************************
* MusicEngine - A dynamic chord library for SuperCollider   								 *
* Copyright (C) 2025 Siriil									    							 *
* Licensed under GPLv3. See LICENSE file for details.			    						 *
*********************************************************************************************/

TestMEValidators : UnitTest {

	/****************************************************************************************/
	// NOTE LETTER VALIDATORS: Unit Tests for noteLetterIsValid
	/****************************************************************************************/

	test_noteLetterIsValid_correctInputString {
		var fixtures = [
			(expected: MEValidators, letter: "C"),
			(expected: MEValidators, letter: "D"),
			(expected: MEValidators, letter: "E"),
			(expected: MEValidators, letter: "F"),
			(expected: MEValidators, letter: "G"),
			(expected: MEValidators, letter: "A"),
			(expected: MEValidators, letter: "B")
		];

		fixtures.do { |f|

			this.assertEquals(
				f.expected,
				MEValidators.noteLetterIsValid(f.letter),
				"Testing valid letter %, as String.".format(f.letter)
			);
		};
	}

	/****************************************************************************************/

	test_noteLetterIsValid_correctInputSymbol {
		var fixtures = [
			(expected: MEValidators, letter: 'C'),
			(expected: MEValidators, letter: 'D'),
			(expected: MEValidators, letter: 'E'),
			(expected: MEValidators, letter: 'F'),
			(expected: MEValidators, letter: 'G'),
			(expected: MEValidators, letter: 'A'),
			(expected: MEValidators, letter: 'B')
		];

		fixtures.do { |f|

			this.assertEquals(
				f.expected,
				MEValidators.noteLetterIsValid(f.letter),
				"Testing valid letter %, as Symbol.".format(f.letter)
			);
		};
	}

	/****************************************************************************************/

	test_noteLetterIsValid_correctInputChar {
		var fixtures = [
			(expected: MEValidators, letter: $C),
			(expected: MEValidators, letter: $D),
			(expected: MEValidators, letter: $E),
			(expected: MEValidators, letter: $F),
			(expected: MEValidators, letter: $G),
			(expected: MEValidators, letter: $A),
			(expected: MEValidators, letter: $B)
		];

		fixtures.do { |f|

			this.assertEquals(
				f.expected,
				MEValidators.noteLetterIsValid(f.letter),
				"Testing valid letter %, as Char.".format(f.letter)
			);
		};
	}

	/****************************************************************************************/

	test_noteLetterIsValid_wrongInput {
		var fixtures = [1, 0.1, "-", "c", "X", ["C"], \d, $e];

		fixtures.do { |f|

			this.assertException(
				{ MEValidators.noteLetterIsValid(f) },
				Error,
				"Wrong input %, should throw an Error.".format(f)
			);
		};
	}

	/****************************************************************************************/
	// NOTE LETTER VALIDATORS: Unit Tests for letterOffsetIsValid
	/****************************************************************************************/

	test_letterOffsetIsValid_correctInput {
		var fixtures = [
			(expected: MEValidators, offset: 0),
			(expected: MEValidators, offset: 1),
			(expected: MEValidators, offset: 2),
			(expected: MEValidators, offset: 3),
			(expected: MEValidators, offset: 4),
			(expected: MEValidators, offset: 5),
			(expected: MEValidators, offset: 6)
		];

		fixtures.do { |f|

			this.assertEquals(
				f.expected,
				MEValidators.letterOffsetIsValid(f.offset),
				"Testing valid letter offset %.".format(f.offset)
			);
		};
	}

	/****************************************************************************************/

	test_letterOffsetIsValid_wrongInput {
		var fixtures = [-1, 1.0, 7, 7.0, "0", '1', [1], $1];

		fixtures.do { |f|

			this.assertException(
				{ MEValidators.letterOffsetIsValid(f) },
				Error,
				"Wrong input %, should throw an Error.".format(f)
			);
		};
	}

	/****************************************************************************************/
	// NOTE LETTER VALIDATORS: Unit Tests for letterOffsetArrayIsValid
	/****************************************************************************************/

	test_letterOffsetArrayIsValid_correctInput {
		var fixtures = [
			(expected: MEValidators, offsetArray: [0, 1, 2, 3]),
			(expected: MEValidators, offsetArray: [1, 2, 3, 4]),
			(expected: MEValidators, offsetArray: [2, 3, 4, 5]),
			(expected: MEValidators, offsetArray: [3, 4, 5, 6]),
			(expected: MEValidators, offsetArray: [0, 1, 2, 3, 4, 5, 6]),
			(expected: MEValidators, offsetArray: [6, 5, 4, 3, 2, 1, 0])
		];

		fixtures.do { |f|

			this.assertEquals(
				f.expected,
				MEValidators.letterOffsetArrayIsValid(f.offsetArray),
				"Testing valid letter offset array %.".format(f.offsetArray)
			);
		};
	}

	/****************************************************************************************/

	test_letterOffsetArrayIsValid_wrongInput {
		var fixtures = [
			["0", 1, 2, 3],
			[1, '2', 3, 4],
			[2, 3, $4, 5],
			[3, 4, 5, [6]],
			[0.0, 1.0, 2.0, 3.0, 4.0, 5.0, 6.0]
		];

		fixtures.do { |f|

			this.assertException(
				{ MEValidators.letterOffsetArrayIsValid(f) },
				Error,
				"Invalid array %, should throw an Error.".format(f)
			);
		};
	}

	/****************************************************************************************/
	// NOTE NAME VALIDATORS: Unit Tests for noteNameIsValid
	/****************************************************************************************/

	test_noteNameIsValid_correctInputNoAccidentals {
		var fixtures = [
			(expected: MEValidators, noteName: "C"),
			(expected: MEValidators, noteName: "D"),
			(expected: MEValidators, noteName: "E"),
			(expected: MEValidators, noteName: "F"),
			(expected: MEValidators, noteName: "G"),
			(expected: MEValidators, noteName: "A"),
			(expected: MEValidators, noteName: "B"),
		];

		fixtures.do { |f|

			this.assertEquals(
				f.expected,
				MEValidators.noteNameIsValid(f.noteName),
				"Testing valid note name %, as String.".format(f.noteName)
			);
		};
	}

	/****************************************************************************************/

	test_noteNameIsValid_correctInputSharps {
		var fixtures = [
			(expected: MEValidators, noteName: "C#"),
			(expected: MEValidators, noteName: "C#####"),
			(expected: MEValidators, noteName: "D#"),
			(expected: MEValidators, noteName: "D#####"),
			(expected: MEValidators, noteName: "E#"),
			(expected: MEValidators, noteName: "E#####"),
			(expected: MEValidators, noteName: "F#"),
			(expected: MEValidators, noteName: "F#####"),
			(expected: MEValidators, noteName: "G#"),
			(expected: MEValidators, noteName: "G#####"),
			(expected: MEValidators, noteName: "A#"),
			(expected: MEValidators, noteName: "A#####"),
			(expected: MEValidators, noteName: "B#"),
			(expected: MEValidators, noteName: "B#####")
		];

		fixtures.do { |f|

			this.assertEquals(
				f.expected,
				MEValidators.noteNameIsValid(f.noteName),
				"Testing valid note name %, as String.".format(f.noteName)
			);
		};
	}

	/****************************************************************************************/

	test_noteNameIsValid_correctInputFlats {
		var fixtures = [
			(expected: MEValidators, noteName: "Cb"),
			(expected: MEValidators, noteName: "Cbbbbb"),
			(expected: MEValidators, noteName: "Db"),
			(expected: MEValidators, noteName: "Dbbbbb"),
			(expected: MEValidators, noteName: "Eb"),
			(expected: MEValidators, noteName: "Ebbbbb"),
			(expected: MEValidators, noteName: "Fb"),
			(expected: MEValidators, noteName: "Fbbbbb"),
			(expected: MEValidators, noteName: "Gb"),
			(expected: MEValidators, noteName: "Gbbbbb"),
			(expected: MEValidators, noteName: "Ab"),
			(expected: MEValidators, noteName: "Abbbbb"),
			(expected: MEValidators, noteName: "Bb"),
			(expected: MEValidators, noteName: "Bbbbbb")
		];

		fixtures.do { |f|

			this.assertEquals(
				f.expected,
				MEValidators.noteNameIsValid(f.noteName),
				"Testing valid note name %, as String.".format(f.noteName)
			);
		};
	}

	/****************************************************************************************/

	test_noteNameIsValid_correctInputSymbols {
		var fixtures = [
			(expected: MEValidators, noteName: 'C'),
			(expected: MEValidators, noteName: 'Cbbbbb'),
			(expected: MEValidators, noteName: 'D'),
			(expected: MEValidators, noteName: 'D#####'),
			(expected: MEValidators, noteName: 'E'),
			(expected: MEValidators, noteName: 'Ebbbbb'),
			(expected: MEValidators, noteName: 'F'),
			(expected: MEValidators, noteName: 'F#####'),
			(expected: MEValidators, noteName: 'G'),
			(expected: MEValidators, noteName: 'Gbbbbb'),
			(expected: MEValidators, noteName: 'A'),
			(expected: MEValidators, noteName: 'A#####'),
			(expected: MEValidators, noteName: 'B'),
			(expected: MEValidators, noteName: 'Bbbbbb')
		];

		fixtures.do { |f|

			this.assertEquals(
				f.expected,
				MEValidators.noteNameIsValid(f.noteName),
				"Testing valid note name %, as Symbol.".format(f.noteName)
			);
		};
	}

	/****************************************************************************************/

	test_noteNameIsValid_validOctaveString {
		var fixtures = [
			(expected: MEValidators, noteName: "C-1"),
			(expected: MEValidators, noteName: "Cbbbbb0"),
			(expected: MEValidators, noteName: "D2"),
			(expected: MEValidators, noteName: "D#####"),
			(expected: MEValidators, noteName: "E3"),
			(expected: MEValidators, noteName: "Ebbbbb4"),
			(expected: MEValidators, noteName: "F5"),
			(expected: MEValidators, noteName: "F#####6"),
			(expected: MEValidators, noteName: "G7"),
			(expected: MEValidators, noteName: "Gbbbbb8"),
			(expected: MEValidators, noteName: "A9")
		];

		fixtures.do { |f|

			this.assertEquals(
				f.expected,
				MEValidators.noteNameIsValid(f.noteName),
				"Testing valid note name with octave %, as String.".format(f.noteName)
			);
		};
	}

	/****************************************************************************************/

	test_noteNameIsValid_validOctaveSymbol {
		var fixtures = [
			(expected: MEValidators, noteName: 'C-1'),
			(expected: MEValidators, noteName: 'Cbbbbb0'),
			(expected: MEValidators, noteName: 'D2'),
			(expected: MEValidators, noteName: 'D#####'),
			(expected: MEValidators, noteName: 'E3'),
			(expected: MEValidators, noteName: 'Ebbbbb4'),
			(expected: MEValidators, noteName: 'F5'),
			(expected: MEValidators, noteName: 'F#####6'),
			(expected: MEValidators, noteName: 'G7'),
			(expected: MEValidators, noteName: 'Gbbbbb8'),
			(expected: MEValidators, noteName: 'A9')
		];

		fixtures.do { |f|

			this.assertEquals(
				f.expected,
				MEValidators.noteNameIsValid(f.noteName),
				"Testing valid note name with octave %, as Symbol.".format(f.noteName)
			);
		};
	}

	/****************************************************************************************/

	test_noteNameIsValid_correctInputChar {
		var fixtures = [
			(expected: MEValidators, noteName: $C),
			(expected: MEValidators, noteName: $D),
			(expected: MEValidators, noteName: $E),
			(expected: MEValidators, noteName: $F),
			(expected: MEValidators, noteName: $G),
			(expected: MEValidators, noteName: $A),
			(expected: MEValidators, noteName: $B),
		];

		fixtures.do { |f|

			this.assertEquals(
				f.expected,
				MEValidators.noteNameIsValid(f.noteName),
				"Testing valid note name %, as Char.".format(f.noteName)
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
				{ MEValidators.noteNameIsValid(f) },
				Error,
				"Testing invalid input %, as String. Should throw an Error.".format(f)
			);
		};
	}

	/****************************************************************************************/

	test_noteNameIsValid_wrongInputSymbol {
		var fixtures = [
			'C######', 'D######', 'E######', 'F######', 'G######', 'A######', 'B######',
			'Cbbbbbb', 'Dbbbbbb', 'Ebbbbbb', 'Fbbbbbb', 'Gbbbbbb', 'Abbbbbb', 'Bbbbbbb',
			'c', 'd', 'e', 'f', 'g', 'a', 'b', 'X', 'x', '+', '1'
		];

		fixtures.do { |f|

			this.assertException(
				{ MEValidators.noteNameIsValid(f) },
				Error,
				"Testing invalid input %, as Symbol. Should throw an Error.".format(f)
			);
		};
	}

	/****************************************************************************************/

	test_noteNameIsValid_invalidOctavesString {
		var fixtures = ["C-10", "D-2", "Eb-", "F#10", "G###4.0", "Abbb-1.0", "B####x"];

		fixtures.do { |f|

			this.assertException(
				{ MEValidators.noteNameIsValid(f) },
				Error,
				"Testing input with invalid octaves %, as String. Should throw an Error.".format(f)
			);
		};
	}

	/****************************************************************************************/

	test_noteNameIsValid_invalidOctavesSymbol {
		var fixtures = ['C-10', 'D-2', 'Eb-', 'F#10', 'G###4.0', 'Abbb-1.0', 'B####x'];

		fixtures.do { |f|

			this.assertException(
				{ MEValidators.noteNameIsValid(f) },
				Error,
				"Testing input with invalid octaves %, as Symbols. Should throw an Error.".format(f)
			);
		};
	}

	/****************************************************************************************/
	// NOTE NAME VALIDATORS: Unit Tests for rootNoteIsValid
	/****************************************************************************************/

	test_rootNoteIsValid_correctInputString {
		var fixtures = [
			(expected: MEValidators, rootNote: "C"),
			(expected: MEValidators, rootNote: "D"),
			(expected: MEValidators, rootNote: "E"),
			(expected: MEValidators, rootNote: "F"),
			(expected: MEValidators, rootNote: "G"),
			(expected: MEValidators, rootNote: "A"),
			(expected: MEValidators, rootNote: "B"),
			(expected: MEValidators, rootNote: "C#"),
			(expected: MEValidators, rootNote: "D#"),
			(expected: MEValidators, rootNote: "E#"),
			(expected: MEValidators, rootNote: "F#"),
			(expected: MEValidators, rootNote: "G#"),
			(expected: MEValidators, rootNote: "A#"),
			(expected: MEValidators, rootNote: "B#"),
			(expected: MEValidators, rootNote: "Cb"),
			(expected: MEValidators, rootNote: "Db"),
			(expected: MEValidators, rootNote: "Eb"),
			(expected: MEValidators, rootNote: "Fb"),
			(expected: MEValidators, rootNote: "Gb"),
			(expected: MEValidators, rootNote: "Ab"),
			(expected: MEValidators, rootNote: "Bb")
		];

		fixtures.do { |f|

			this.assertEquals(
				f.expected,
				MEValidators.rootNoteIsValid(f.rootNote),
				"Testing valid root note %, as String.".format(f.rootNote)
			);
		};
	}

	/****************************************************************************************/

	test_rootNoteIsValid_correctInputSymbol {
		var fixtures = [
			(expected: MEValidators, rootNote: 'C'),
			(expected: MEValidators, rootNote: 'D'),
			(expected: MEValidators, rootNote: 'E'),
			(expected: MEValidators, rootNote: 'F'),
			(expected: MEValidators, rootNote: 'G'),
			(expected: MEValidators, rootNote: 'A'),
			(expected: MEValidators, rootNote: 'B'),
			(expected: MEValidators, rootNote: 'C#'),
			(expected: MEValidators, rootNote: 'D#'),
			(expected: MEValidators, rootNote: 'E#'),
			(expected: MEValidators, rootNote: 'F#'),
			(expected: MEValidators, rootNote: 'G#'),
			(expected: MEValidators, rootNote: 'A#'),
			(expected: MEValidators, rootNote: 'B#'),
			(expected: MEValidators, rootNote: 'Cb'),
			(expected: MEValidators, rootNote: 'Db'),
			(expected: MEValidators, rootNote: 'Eb'),
			(expected: MEValidators, rootNote: 'Fb'),
			(expected: MEValidators, rootNote: 'Gb'),
			(expected: MEValidators, rootNote: 'Ab'),
			(expected: MEValidators, rootNote: 'Bb')
		];

		fixtures.do { |f|

			this.assertEquals(
				f.expected,
				MEValidators.rootNoteIsValid(f.rootNote),
				"Testing valid root note %, as Symbol.".format(f.rootNote)
			);
		};
	}

	/****************************************************************************************/

	test_rootNoteIsValid_correctInputChar {
		var fixtures = [
			(expected: MEValidators, rootNote: $C),
			(expected: MEValidators, rootNote: $D),
			(expected: MEValidators, rootNote: $E),
			(expected: MEValidators, rootNote: $F),
			(expected: MEValidators, rootNote: $G),
			(expected: MEValidators, rootNote: $A),
			(expected: MEValidators, rootNote: $B)
		];

		fixtures.do { |f|

			this.assertEquals(
				f.expected,
				MEValidators.rootNoteIsValid(f.rootNote),
				"Testing valid root note %, as Char.".format(f.rootNote)
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
				{ MEValidators.rootNoteIsValid(f) },
				Error,
				"Testing invalid root note %, as String.".format(f)
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
				{ MEValidators.rootNoteIsValid(f) },
				Error,
				"Testing invalid root note %, as Symbol.".format(f)
			);
		};
	}

	/****************************************************************************************/

	test_rootNoteIsValid_invalidNoteString {
		var fixtures = ["a", "b", "c", "d", "e", "f", "g", "1", "X", "+", "0.0"];

		fixtures.do { |f|

			this.assertException(
				{ MEValidators.rootNoteIsValid(f) },
				Error,
				"Testing invalid root note %, as String.".format(f)
			);
		};
	}

	/****************************************************************************************/

	test_rootNoteIsValid_invalidNoteSymbol {
		var fixtures = ['a', 'b', 'c', 'd', 'e', 'f', 'g', '1', 'X', '+', '0.0'];

		fixtures.do { |f|

			this.assertException(
				{ MEValidators.rootNoteIsValid(f) },
				Error,
				"Testing invalid root note %, as Symbol.".format(f)
			);
		};
	}

	/****************************************************************************************/

	test_rootNoteIsValid_invalidNoteChar {
		var fixtures = [$a, $b, $c, $d, $e, $f, $g, $1, $X, $+, $0];

		fixtures.do { |f|

			this.assertException(
				{ MEValidators.rootNoteIsValid(f) },
				Error,
				"Testing invalid root note %, as Char.".format(f)
			);
		};
	}

	/****************************************************************************************/
	// MIDI VALIDATORS: Unit Tests for midiNoteIsValid
	/****************************************************************************************/

	test_midiNoteIsValid_correctInputNonNegative {

		(0..127).do { |f|

			this.assertEquals(
				MEValidators,
				MEValidators.midiNoteIsValid(f, negative: false),
				"Testing valid input MIDI note %.".format(f)
			);
		};
	}

	/****************************************************************************************/

	test_midiNoteIsValid_correctInputNegative {

		(-1..127).do { |f|

			this.assertEquals(
				MEValidators,
				MEValidators.midiNoteIsValid(f, negative: true),
				"Testing valid input MIDI note %.".format(f)
			);
		};
	}

	/****************************************************************************************/

	test_midiNoteIsValid_wrongInputNonNegative {
		var fixtures = [-10, -1, 1.0, 128, "1", '1'];

		fixtures.do { |f|

			this.assertException(
				{ MEValidators.midiNoteIsValid(f, negative: false) },
				Error,
				"Testing invalid midi note %.".format(f)
			);
		};
	}

	/****************************************************************************************/

	test_midiNoteIsValid_wrongInputNegative {
			var fixtures = [-10, -2, 1.0, 128, "1", '1'];

		fixtures.do { |f|

			this.assertException(
				{ MEValidators.midiNoteIsValid(f, negative: true) },
				Error,
				"Testing invalid midi note %.".format(f)
			);
		};
	}

	/****************************************************************************************/
	// MIDI VALIDATORS: Unit Tests for midiOffsetIsValid
	/****************************************************************************************/

	test_midiOffsetIsValid_correctInputDiatonicNonNegative {
		var fixtures = [
			(expected: MEValidators, midiOffset: 0),
			(expected: MEValidators, midiOffset: 2),
			(expected: MEValidators, midiOffset: 4),
			(expected: MEValidators, midiOffset: 5),
			(expected: MEValidators, midiOffset: 7),
			(expected: MEValidators, midiOffset: 9),
			(expected: MEValidators, midiOffset: 11)
		];

		fixtures.do { |f|

			this.assertEquals(
				f.expected,
				MEValidators.midiOffsetIsValid(f.midiOffset, diatonic: true, negative: false),
				"Testing valid MIDI offset %.".format(f.midiOffset)
			);
		};
	}

	/****************************************************************************************/

	test_midiOffsetIsValid_correctInputDiatonicNegative {
		var fixtures = [
			(expected: MEValidators, midiOffset: -1),
			(expected: MEValidators, midiOffset: 0),
			(expected: MEValidators, midiOffset: 2),
			(expected: MEValidators, midiOffset: 4),
			(expected: MEValidators, midiOffset: 5),
			(expected: MEValidators, midiOffset: 7),
			(expected: MEValidators, midiOffset: 9),
			(expected: MEValidators, midiOffset: 11)
		];

		fixtures.do { |f|

			this.assertEquals(
				f.expected,
				MEValidators.midiOffsetIsValid(f.midiOffset, diatonic: true, negative: true),
				"Testing valid MIDI offset %.".format(f.midiOffset)
			);
		};
	}

	/****************************************************************************************/

	test_midiOffsetIsValid_correctInputChromaticNonNegative {
		var fixtures = [
			(expected: MEValidators, midiOffset: 0),
			(expected: MEValidators, midiOffset: 1),
			(expected: MEValidators, midiOffset: 2),
			(expected: MEValidators, midiOffset: 3),
			(expected: MEValidators, midiOffset: 4),
			(expected: MEValidators, midiOffset: 5),
			(expected: MEValidators, midiOffset: 6),
			(expected: MEValidators, midiOffset: 7),
			(expected: MEValidators, midiOffset: 8),
			(expected: MEValidators, midiOffset: 9),
			(expected: MEValidators, midiOffset: 10),
			(expected: MEValidators, midiOffset: 11)
		];

		fixtures.do { |f|

			this.assertEquals(
				f.expected,
				MEValidators.midiOffsetIsValid(f.midiOffset, diatonic: false, negative: false),
				"Testing valid MIDI offset %.".format(f.midiOffset)
			);
		};
	}

	/****************************************************************************************/

	test_midiOffsetIsValid_correctInputChromaticNegative {
		var fixtures = [
			(expected: MEValidators, midiOffset: -1),
			(expected: MEValidators, midiOffset: 0),
			(expected: MEValidators, midiOffset: 1),
			(expected: MEValidators, midiOffset: 2),
			(expected: MEValidators, midiOffset: 3),
			(expected: MEValidators, midiOffset: 4),
			(expected: MEValidators, midiOffset: 5),
			(expected: MEValidators, midiOffset: 6),
			(expected: MEValidators, midiOffset: 7),
			(expected: MEValidators, midiOffset: 8),
			(expected: MEValidators, midiOffset: 9),
			(expected: MEValidators, midiOffset: 10),
			(expected: MEValidators, midiOffset: 11)
		];

		fixtures.do { |f|

			this.assertEquals(
				f.expected,
				MEValidators.midiOffsetIsValid(f.midiOffset, diatonic: false, negative: true),
				"Testing valid MIDI offset %.".format(f.midiOffset)
			);
		};
	}

	/****************************************************************************************/

	test_midiOffsetIsValid_wrongInputDiatonicNonNegative {
		var fixtures = [-1, 1, 3, 6, 8, 10, 12, "1", '1', $1, [1]];

		fixtures.do { |f|

			this.assertException(
				{ MEValidators.midiOffsetIsValid(f, diatonic: true, negative: false) },
				Error,
				"Testing invalid MIDI offset % (diatonic: true; negative: false).".format(f)
			);
		};
	}

	/****************************************************************************************/

	test_midiOffsetIsValid_wrongInputDiatonicNegative {
		var fixtures = [-2, 1, 3, 6, 8, 10, 12, "1", '1', $1, [1]];

		fixtures.do { |f|

			this.assertException(
				{ MEValidators.midiOffsetIsValid(f, diatonic: true, negative: true) },
				Error,
				"Testing invalid MIDI offset % (diatonic: true; negative: true).".format(f)
			);
		};
	}

	/****************************************************************************************/

	test_midiOffsetIsValid_wrongInputChromaticNonNegative {
		var fixtures = [-1, 12, 1.0, "1", '1', $1, [1]];

		fixtures.do { |f|

			this.assertException(
				{ MEValidators.midiOffsetIsValid(f, diatonic: false, negative: false) },
				Error,
				"Testing invalid MIDI offset % (diatonic: false; negative: false).".format(f)
			);
		};
	}

	/****************************************************************************************/

	test_midiOffsetIsValid_wrongInputChromaticNegative {
		var fixtures = [-2, 12, 1.0, "1", '1', $1, [1]];

		fixtures.do { |f|

			this.assertException(
				{ MEValidators.midiOffsetIsValid(f, diatonic: false, negative: true) },
				Error,
				"Testing invalid MIDI offset % (diatonic: false; negative: false).".format(f)
			);
		};
	}

}