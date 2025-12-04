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
				MEValidators.midiNoteIsValid(f),
				"Testing valid input MIDI note %.".format(f)
			);
		};
	}

	/****************************************************************************************/

	test_midiNoteIsValid_wrongInputNonNegative {
		var fixtures = [-10, -1, 1.0, 128, "1", '1'];

		fixtures.do { |f|

			this.assertException(
				{ MEValidators.midiNoteIsValid(f) },
				Error,
				"Testing invalid midi note %.".format(f)
			);
		};
	}

	/****************************************************************************************/
	// MIDI VALIDATORS: Unit Tests for midiOffsetIsValid
	/****************************************************************************************/

	test_midiOffsetIsValid_correctInputDiatonic {
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
				MEValidators.midiOffsetIsValid(f.midiOffset, diatonic: true),
				"Testing valid MIDI offset %.".format(f.midiOffset)
			);
		};
	}

	/****************************************************************************************/

	test_midiOffsetIsValid_correctInputChromatic {
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
				MEValidators.midiOffsetIsValid(f.midiOffset, diatonic: false),
				"Testing valid MIDI offset %.".format(f.midiOffset)
			);
		};
	}

	/****************************************************************************************/

	test_midiOffsetIsValid_wrongInputDiatonic {
		var fixtures = [-1, 1, 3, 6, 8, 10, 12, "1", '1', $1, [1]];

		fixtures.do { |f|

			this.assertException(
				{ MEValidators.midiOffsetIsValid(f, diatonic: true) },
				Error,
				"Testing invalid MIDI offset % (diatonic: true; negative: false).".format(f)
			);
		};
	}

	/****************************************************************************************/

	test_midiOffsetIsValid_wrongInputChromatic {
		var fixtures = [-1, 12, 1.0, "1", '1', $1, [1]];

		fixtures.do { |f|

			this.assertException(
				{ MEValidators.midiOffsetIsValid(f, diatonic: false) },
				Error,
				"Testing invalid MIDI offset % (diatonic: false; negative: false).".format(f)
			);
		};
	}

	/****************************************************************************************/
	// MIDI VALIDATORS: Unit Tests for midiOffsetArrayIsValid
	/****************************************************************************************/

	test_midiOffsetArrayIsValid_noEnharmonicsTwoNotesDiatonic {
		var fixtures = [
			(expected: MEValidators, midiOffsetArr: [0, 2]),
			(expected: MEValidators, midiOffsetArr: [0, 4]),
			(expected: MEValidators, midiOffsetArr: [0, 5]),
			(expected: MEValidators, midiOffsetArr: [0, 7]),
			(expected: MEValidators, midiOffsetArr: [0, 9]),
			(expected: MEValidators, midiOffsetArr: [0, 11])
		];

		fixtures.do { |f|

			this.assertEquals(
				f.expected,
				MEValidators.midiOffsetArrayIsValid(f.midiOffsetArr, diatonic: true),
				"Testing valid MIDI offset array %. Two notes, diatonic".format(f.midiOffsetArr.join(", "))
			);
		};
	}

	/****************************************************************************************/

	test_midiOffsetArrayIsValid_noEnharmonicsTwoNotesChromatic {
		var fixtures = [
			(expected: MEValidators, midiOffsetArr: [0, 1]),
			(expected: MEValidators, midiOffsetArr: [0, 2]),
			(expected: MEValidators, midiOffsetArr: [0, 3]),
			(expected: MEValidators, midiOffsetArr: [0, 4]),
			(expected: MEValidators, midiOffsetArr: [0, 5]),
			(expected: MEValidators, midiOffsetArr: [0, 6]),
			(expected: MEValidators, midiOffsetArr: [0, 7]),
			(expected: MEValidators, midiOffsetArr: [0, 8]),
			(expected: MEValidators, midiOffsetArr: [0, 9]),
			(expected: MEValidators, midiOffsetArr: [0, 10]),
			(expected: MEValidators, midiOffsetArr: [0, 11])
		];

		fixtures.do { |f|

			this.assertEquals(
				f.expected,
				MEValidators.midiOffsetArrayIsValid(f.midiOffsetArr, diatonic: false),
				"Testing valid MIDI offset array %. Two notes, chromatic".format(f.midiOffsetArr.join(", "))
			);
		};
	}

	/****************************************************************************************/

	test_midiOffsetArrayIsValid_noEnharmonicsFourNotesDiatonic {
		var fixtures = [
			(expected: MEValidators, midiOffsetArr: [0, 2, 4, 5]),
			(expected: MEValidators, midiOffsetArr: [0, 4, 5, 7]),
			(expected: MEValidators, midiOffsetArr: [0, 5, 7, 9]),
			(expected: MEValidators, midiOffsetArr: [0, 7, 9, 11])
		];

		fixtures.do { |f|

			this.assertEquals(
				f.expected,
				MEValidators.midiOffsetArrayIsValid(f.midiOffsetArr, diatonic: true),
				"Testing valid MIDI offset array %. Two notes, diatonic".format(f.midiOffsetArr.join(", "))
			);
		};
	}

	/****************************************************************************************/

	test_midiOffsetArrayIsValid_noEnharmonicsFourNotesChromatic {
		var fixtures = [
			(expected: MEValidators, midiOffsetArr: [0, 2, 3, 4]),
			(expected: MEValidators, midiOffsetArr: [0, 3, 4, 5]),
			(expected: MEValidators, midiOffsetArr: [0, 4, 5, 6]),
			(expected: MEValidators, midiOffsetArr: [0, 5, 6, 7]),
			(expected: MEValidators, midiOffsetArr: [0, 6, 7, 8]),
			(expected: MEValidators, midiOffsetArr: [0, 7, 8, 9]),
			(expected: MEValidators, midiOffsetArr: [0, 8, 9, 10]),
			(expected: MEValidators, midiOffsetArr: [0, 9, 10, 11])
		];

		fixtures.do { |f|

			this.assertEquals(
				f.expected,
				MEValidators.midiOffsetArrayIsValid(f.midiOffsetArr, diatonic: false),
				"Testing valid MIDI offset array %. Two notes, diatonic".format(f.midiOffsetArr.join(", "))
			);
		};
	}

	/****************************************************************************************/

	test_midiOffsetArrayIsValid_wrongInputEnharmonics {
		var fixtures = [
			[0, 0, 0, 0],
			[0, 1, 1, 2],
			[0, 1, 2, 2]
		];

		fixtures.do { |f|

			this.assertException(
				{ MEValidators.midiOffsetArrayIsValid(f) },
				Error,
				"Testing invalid MIDI offset array %. Enharmonics present.".format(f.join(", "))
			);
		};
	}

	/****************************************************************************************/

	test_midiOffsetArrayIsValid_wrongInput {
		var fixtures = [
			[1, 2, 3, 4, 5, 6],
			[-1, 0, 1, 2, 3, 4],
			["0", 1, 2, 3, 4, 5],
			[0, '1', 2, 3, 4, 5],
			[0, 1, $2, 3, 4, 5],
			[0, 1, 2, [3], 4, 5],
			[0, 1, 2, 3, 4.0, 5],
			[0, 7, 8, 10, 11, 12]
		];

		fixtures.do { |f|

			this.assertException(
				{ MEValidators.midiOffsetArrayIsValid(f) },
				Error,
				"Testing invalid MIDI offset array %. Invalid values.".format(f.join(", "))
			);
		};
	}

	/****************************************************************************************/
	// MIDI NAME PAIR: Unit Tests for midiNamePairIsValid
	/****************************************************************************************/

	test_midiNamePairIsValid_correctInputFirstOctaveNoSign {
		var fixtures = [
			(expected: MEValidators, midiNote: 0, noteName: "C"),
			(expected: MEValidators, midiNote: 2, noteName: "D"),
			(expected: MEValidators, midiNote: 4, noteName: "E"),
			(expected: MEValidators, midiNote: 5, noteName: "F"),
			(expected: MEValidators, midiNote: 7, noteName: "G"),
			(expected: MEValidators, midiNote: 9, noteName: "A"),
			(expected: MEValidators, midiNote: 11, noteName: "B")
		];

		fixtures.do { |f|

			this.assertEquals(
				f.expected,
				MEValidators.midiNamePairIsValid(f.midiNote, f.noteName, validate: true),
				"Testing valid input MIDI note % note name %.".format(f.midiNote, f.noteName)
			);
		};
	}

	/****************************************************************************************/

	test_midiNamePairIsValid_correctInputFirstOctaveOnesign {
		var fixtures = [
			(expected: MEValidators, midiNote: 0, noteName: "B#"),
			(expected: MEValidators, midiNote: 1, noteName: "C#"),
			(expected: MEValidators, midiNote: 1, noteName: "Db"),
			(expected: MEValidators, midiNote: 3, noteName: "D#"),
			(expected: MEValidators, midiNote: 3, noteName: "Eb"),
			(expected: MEValidators, midiNote: 4, noteName: "Fb"),
			(expected: MEValidators, midiNote: 5, noteName: "E#"),
			(expected: MEValidators, midiNote: 6, noteName: "F#"),
			(expected: MEValidators, midiNote: 6, noteName: "Gb"),
			(expected: MEValidators, midiNote: 8, noteName: "G#"),
			(expected: MEValidators, midiNote: 8, noteName: "Ab"),
			(expected: MEValidators, midiNote: 10, noteName: "A#"),
			(expected: MEValidators, midiNote: 10, noteName: "Bb"),
			(expected: MEValidators, midiNote: 11, noteName: "Cb")
		];

		fixtures.do { |f|

			this.assertEquals(
				f.expected,
				MEValidators.midiNamePairIsValid(f.midiNote, f.noteName, validate: true),
				"Testing valid input MIDI note % note name %.".format(f.midiNote, f.noteName)
			);
		};
	}

	/****************************************************************************************/

	test_midiNamePairIsValid_correctInputFirstOctaveManySigns {
		var fixtures = [
			(expected: MEValidators, midiNote: 0, noteName: "G#####"),
			(expected: MEValidators, midiNote: 0, noteName: "Fbbbbb"),
			(expected: MEValidators, midiNote: 1, noteName: "A####"),
			(expected: MEValidators, midiNote: 1, noteName: "Fbbbb"),
			(expected: MEValidators, midiNote: 2, noteName: "A#####"),
			(expected: MEValidators, midiNote: 2, noteName: "Gbbbbb"),
			(expected: MEValidators, midiNote: 3, noteName: "B####"),
			(expected: MEValidators, midiNote: 3, noteName: "Gbbbb"),
			(expected: MEValidators, midiNote: 4, noteName: "B#####"),
			(expected: MEValidators, midiNote: 4, noteName: "Abbbbb"),
			(expected: MEValidators, midiNote: 5, noteName: "C#####"),
			(expected: MEValidators, midiNote: 5, noteName: "Abbbb"),
			(expected: MEValidators, midiNote: 6, noteName: "D####"),
			(expected: MEValidators, midiNote: 6, noteName: "Bbbbbb"),
			(expected: MEValidators, midiNote: 7, noteName: "D#####"),
			(expected: MEValidators, midiNote: 7, noteName: "Bbbbb"),
			(expected: MEValidators, midiNote: 8, noteName: "E####"),
			(expected: MEValidators, midiNote: 8, noteName: "Cbbbb"),
			(expected: MEValidators, midiNote: 9, noteName: "F####"),
			(expected: MEValidators, midiNote: 9, noteName: "Dbbbbb"),
			(expected: MEValidators, midiNote: 10, noteName: "F#####"),
			(expected: MEValidators, midiNote: 10, noteName: "Dbbbb"),
			(expected: MEValidators, midiNote: 11, noteName: "G####"),
			(expected: MEValidators, midiNote: 11, noteName: "Ebbbbb")
		];

		fixtures.do { |f|

			this.assertEquals(
				f.expected,
				MEValidators.midiNamePairIsValid(f.midiNote, f.noteName, validate: true),
				"Testing valid input MIDI note % note name %.".format(f.midiNote, f.noteName)
			);
		};
	}

	/****************************************************************************************/

	test_midiNamePairIsValid_correctInputLastOctaveNoSign {
		var fixtures = [
			(expected: MEValidators, midiNote: 120, noteName: "C"),
			(expected: MEValidators, midiNote: 122, noteName: "D"),
			(expected: MEValidators, midiNote: 124, noteName: "E"),
			(expected: MEValidators, midiNote: 125, noteName: "F"),
			(expected: MEValidators, midiNote: 127, noteName: "G")
		];

		fixtures.do { |f|

			this.assertEquals(
				f.expected,
				MEValidators.midiNamePairIsValid(f.midiNote, f.noteName, validate: true),
				"Testing valid input MIDI note % note name %.".format(f.midiNote, f.noteName)
			);
		};
	}

	/****************************************************************************************/

	test_midiNamePairIsValid_correctInputLastOctaveOnesign {
		var fixtures = [
			(expected: MEValidators, midiNote: 120, noteName: "B#"),
			(expected: MEValidators, midiNote: 121, noteName: "C#"),
			(expected: MEValidators, midiNote: 121, noteName: "Db"),
			(expected: MEValidators, midiNote: 123, noteName: "D#"),
			(expected: MEValidators, midiNote: 123, noteName: "Eb"),
			(expected: MEValidators, midiNote: 124, noteName: "Fb"),
			(expected: MEValidators, midiNote: 125, noteName: "E#"),
			(expected: MEValidators, midiNote: 126, noteName: "F#"),
			(expected: MEValidators, midiNote: 126, noteName: "Gb")
		];

		fixtures.do { |f|

			this.assertEquals(
				f.expected,
				MEValidators.midiNamePairIsValid(f.midiNote, f.noteName, validate: true),
				"Testing valid input MIDI note % note name %.".format(f.midiNote, f.noteName)
			);
		};
	}

	/****************************************************************************************/

	test_midiNamePairIsValid_correctInputLastOctaveManySigns {
		var fixtures = [
			(expected: MEValidators, midiNote: 120, noteName: "G#####"),
			(expected: MEValidators, midiNote: 120, noteName: "Fbbbbb"),
			(expected: MEValidators, midiNote: 121, noteName: "A####"),
			(expected: MEValidators, midiNote: 121, noteName: "Fbbbb"),
			(expected: MEValidators, midiNote: 122, noteName: "A#####"),
			(expected: MEValidators, midiNote: 122, noteName: "Gbbbbb"),
			(expected: MEValidators, midiNote: 123, noteName: "B####"),
			(expected: MEValidators, midiNote: 123, noteName: "Gbbbb"),
			(expected: MEValidators, midiNote: 124, noteName: "B#####"),
			(expected: MEValidators, midiNote: 124, noteName: "Abbbbb"),
			(expected: MEValidators, midiNote: 125, noteName: "C#####"),
			(expected: MEValidators, midiNote: 125, noteName: "Abbbb"),
			(expected: MEValidators, midiNote: 126, noteName: "D####"),
			(expected: MEValidators, midiNote: 126, noteName: "Bbbbbb"),
			(expected: MEValidators, midiNote: 127, noteName: "D#####"),
			(expected: MEValidators, midiNote: 127, noteName: "Bbbbb")
		];

		fixtures.do { |f|

			this.assertEquals(
				f.expected,
				MEValidators.midiNamePairIsValid(f.midiNote, f.noteName, validate: true),
				"Testing valid input MIDI note % note name %.".format(f.midiNote, f.noteName)
			);
		};
	}

	/****************************************************************************************/

	test_midiNamePairIsValid_correctInputMiddleOctavesNoSign {
		var fixtures = [
			(expected: MEValidators, midiNote: 12, noteName: "C"),
			(expected: MEValidators, midiNote: 26, noteName: "D"),
			(expected: MEValidators, midiNote: 40, noteName: "E"),
			(expected: MEValidators, midiNote: 53, noteName: "F"),
			(expected: MEValidators, midiNote: 67, noteName: "G"),
			(expected: MEValidators, midiNote: 81, noteName: "A"),
			(expected: MEValidators, midiNote: 95, noteName: "B"),
			(expected: MEValidators, midiNote: 108, noteName: "C")
		];

		fixtures.do { |f|

			this.assertEquals(
				f.expected,
				MEValidators.midiNamePairIsValid(f.midiNote, f.noteName, validate: true),
				"Testing valid input MIDI note % note name %.".format(f.midiNote, f.noteName)
			);
		};
	}

	/****************************************************************************************/

	test_midiNamePairIsValid_correctInputMiddleOctaveOneSign {
		var fixtures = [
			(expected: MEValidators, midiNote: 12, noteName: "B#"),
			(expected: MEValidators, midiNote: 25, noteName: "C#"),
			(expected: MEValidators, midiNote: 25, noteName: "Db"),
			(expected: MEValidators, midiNote: 39, noteName: "D#"),
			(expected: MEValidators, midiNote: 39, noteName: "Eb"),
			(expected: MEValidators, midiNote: 52, noteName: "Fb"),
			(expected: MEValidators, midiNote: 65, noteName: "E#"),
			(expected: MEValidators, midiNote: 78, noteName: "F#"),
			(expected: MEValidators, midiNote: 78, noteName: "Gb"),
			(expected: MEValidators, midiNote: 92, noteName: "G#"),
			(expected: MEValidators, midiNote: 92, noteName: "Ab"),
			(expected: MEValidators, midiNote: 106, noteName: "A#"),
			(expected: MEValidators, midiNote: 106, noteName: "Bb"),
			(expected: MEValidators, midiNote: 119, noteName: "Cb")
		];

		fixtures.do { |f|

			this.assertEquals(
				f.expected,
				MEValidators.midiNamePairIsValid(f.midiNote, f.noteName, validate: true),
				"Testing valid input MIDI note % note name %.".format(f.midiNote, f.noteName)
			);
		};
	}

	/****************************************************************************************/

	test_midiNamePairIsValid_correctInputMiddleOctaveManySigns {
		var fixtures = [
			(expected: MEValidators, midiNote: 12, noteName: "G#####"),
			(expected: MEValidators, midiNote: 12, noteName: "Fbbbbb"),
			(expected: MEValidators, midiNote: 25, noteName: "A####"),
			(expected: MEValidators, midiNote: 25, noteName: "Fbbbb"),
			(expected: MEValidators, midiNote: 38, noteName: "A#####"),
			(expected: MEValidators, midiNote: 38, noteName: "Gbbbbb"),
			(expected: MEValidators, midiNote: 51, noteName: "B####"),
			(expected: MEValidators, midiNote: 51, noteName: "Gbbbb"),
			(expected: MEValidators, midiNote: 64, noteName: "B#####"),
			(expected: MEValidators, midiNote: 64, noteName: "Abbbbb"),
			(expected: MEValidators, midiNote: 77, noteName: "C#####"),
			(expected: MEValidators, midiNote: 77, noteName: "Abbbb"),
			(expected: MEValidators, midiNote: 90, noteName: "D####"),
			(expected: MEValidators, midiNote: 90, noteName: "Bbbbbb"),
			(expected: MEValidators, midiNote: 103, noteName: "D#####"),
			(expected: MEValidators, midiNote: 103, noteName: "Bbbbb"),
			(expected: MEValidators, midiNote: 116, noteName: "E####"),
			(expected: MEValidators, midiNote: 116, noteName: "Cbbbb")
		];

		fixtures.do { |f|

			this.assertEquals(
				f.expected,
				MEValidators.midiNamePairIsValid(f.midiNote, f.noteName, validate: true),
				"Testing valid input MIDI note % note name %.".format(f.midiNote, f.noteName)
			);
		};
	}

	/****************************************************************************************/

	test_midiNamePairIsValid_wrongInputFirstOctaveNoSign {
		var fixtures = [
			(midiNote: 1, noteName: "C"),
			(midiNote: 3, noteName: "D"),
			(midiNote: 5, noteName: "E"),
			(midiNote: 6, noteName: "F"),
			(midiNote: 8, noteName: "G"),
			(midiNote: 10, noteName: "A"),
			(midiNote: 12, noteName: "B")
		];

		fixtures.do { |f|

			this.assertException(
				{ MEValidators.midiNamePairIsValid(f.midiNote, f.noteName, validate: true) },
				Error,
				"Testing invalid input MIDI note % note name %. Should throw Error.".format(f.midiNote, f.noteName)
			);
		};
	}

	/****************************************************************************************/

	test_midiNamePairIsValid_wrongInputFirstOctaveOnesign {
		var fixtures = [
			(midiNote: 0, noteName: "C#"),
			(midiNote: 1, noteName: "B#"),
			(midiNote: 2, noteName: "Db"),
			(midiNote: 2, noteName: "D#"),
			(midiNote: 4, noteName: "Eb"),
			(midiNote: 4, noteName: "E#"),
			(midiNote: 5, noteName: "Fb"),
			(midiNote: 5, noteName: "F#"),
			(midiNote: 7, noteName: "Gb"),
			(midiNote: 7, noteName: "G#"),
			(midiNote: 9, noteName: "Ab"),
			(midiNote: 9, noteName: "A#"),
			(midiNote: 11, noteName: "Bb"),
			(midiNote: 12, noteName: "Cb")
		];

		fixtures.do { |f|

			this.assertException(
				{ MEValidators.midiNamePairIsValid(f.midiNote, f.noteName, validate: true) },
				Error,
				"Testing invalid input MIDI note % note name %. Should throw Error.".format(f.midiNote, f.noteName)
			);
		};
	}

	/****************************************************************************************/

	test_midiNamePairIsValid_wrongInputFirstOctaveManySigns {
		var fixtures = [
			(midiNote: 0, noteName: "G####"),
			(midiNote: 0, noteName: "Fbbbb"),
			(midiNote: 1, noteName: "A###"),
			(midiNote: 1, noteName: "Fbbb"),
			(midiNote: 2, noteName: "A####"),
			(midiNote: 2, noteName: "Gbbbb"),
			(midiNote: 3, noteName: "B###"),
			(midiNote: 3, noteName: "Gbbb"),
			(midiNote: 4, noteName: "B####"),
			(midiNote: 4, noteName: "Abbbb"),
			(midiNote: 5, noteName: "C####"),
			(midiNote: 5, noteName: "Abbb"),
			(midiNote: 6, noteName: "D###"),
			(midiNote: 6, noteName: "Bbbbb"),
			(midiNote: 7, noteName: "D####"),
			(midiNote: 7, noteName: "Bbbb"),
			(midiNote: 8, noteName: "E###"),
			(midiNote: 8, noteName: "Cbbb"),
			(midiNote: 9, noteName: "F###"),
			(midiNote: 9, noteName: "Dbbbb"),
			(midiNote: 10, noteName: "F####"),
			(midiNote: 10, noteName: "Dbbb"),
			(midiNote: 11, noteName: "G###"),
			(midiNote: 11, noteName: "Ebbbb")
		];

		fixtures.do { |f|

			this.assertException(
				{ MEValidators.midiNamePairIsValid(f.midiNote, f.noteName, validate: true) },
				Error,
				"Testing invalid input MIDI note % note name %. Should throw Error.".format(f.midiNote, f.noteName)
			);
		};
	}

	/****************************************************************************************/

	test_midiNamePairIsValid_wrongInputLastOctaveNoSign {
		var fixtures = [
			(midiNote: 121, noteName: "C"),
			(midiNote: 123, noteName: "D"),
			(midiNote: 125, noteName: "E"),
			(midiNote: 124, noteName: "F"),
			(midiNote: 126, noteName: "G")
		];

		fixtures.do { |f|

			this.assertException(
				{ MEValidators.midiNamePairIsValid(f.midiNote, f.noteName, validate: true) },
				Error,
				"Testing invalid input MIDI note % note name %. Should throw Error.".format(f.midiNote, f.noteName)
			);
		};
	}

	/****************************************************************************************/

	test_midiNamePairIsValid_wrongInputLastOctaveOnesign {
		var fixtures = [
			(midiNote: 119, noteName: "B#"),
			(midiNote: 120, noteName: "C#"),
			(midiNote: 122, noteName: "Db"),
			(midiNote: 122, noteName: "D#"),
			(midiNote: 124, noteName: "Eb"),
			(midiNote: 125, noteName: "Fb"),
			(midiNote: 124, noteName: "E#"),
			(midiNote: 125, noteName: "F#"),
			(nidiNote: 127, noteName: "Gb")
		];

		fixtures.do { |f|

			this.assertException(
				{ MEValidators.midiNamePairIsValid(f.midiNote, f.noteName, validate: true) },
				Error,
				"Testing invalid input MIDI note % note name %. Should throw Error.".format(f.midiNote, f.noteName)
			);
		};
	}

	/****************************************************************************************/

	est_midiNamePairIsValid_wrongInputLastOctaveManySigns {
		var fixtures = [
			(midiNote: 120, noteName: "G####"),
			(midiNote: 120, noteName: "Fbbbb"),
			(midiNote: 121, noteName: "A###"),
			(midiNote: 121, noteName: "Fbbb"),
			(midiNote: 122, noteName: "A####"),
			(midiNote: 122, noteName: "Gbbbb"),
			(midiNote: 123, noteName: "B###"),
			(midiNote: 123, noteName: "Gbbb"),
			(midiNote: 124, noteName: "B####"),
			(midiNote: 124, noteName: "Abbbb"),
			(midiNote: 125, noteName: "C####"),
			(midiNote: 125, noteName: "Abbb"),
			(midiNote: 126, noteName: "D###"),
			(midiNote: 126, noteName: "Bbbbb"),
			(midiNote: 127, noteName: "D####"),
			(midiNote: 127, noteName: "Bbbb")
		];

		fixtures.do { |f|

			this.assertException(
				{ MEValidators.midiNamePairIsValid(f.midiNote, f.noteName, validate: true) },
				Error,
				"Testing invalid input MIDI note % note name %. Should throw Error.".format(f.midiNote, f.noteName)
			);
		};
	}

	/****************************************************************************************/

	test_midiNamePairIsValid_wrongInputMiddleOctavesNoSign {
		var fixtures = [
			(midiNote: 11, noteName: "C"),
			(midiNote: 25, noteName: "D"),
			(midiNote: 39, noteName: "E"),
			(midiNote: 52, noteName: "F"),
			(midiNote: 66, noteName: "G"),
			(midiNote: 80, noteName: "A"),
			(midiNote: 96, noteName: "B"),
			(midiNote: 107, noteName: "C")
		];

		fixtures.do { |f|

			this.assertException(
				{ MEValidators.midiNamePairIsValid(f.midiNote, f.noteName, validate: true) },
				Error,
				"Testing invalid input MIDI note % note name %. Should throw Error.".format(f.midiNote, f.noteName)
			);
		};
	}

	/****************************************************************************************/

	test_midiNamePairIsValid_wrongInputMiddleOctaveOneSign {
		var fixtures = [
			(midiNote: 13, noteName: "B#"),
			(midiNote: 24, noteName: "C#"),
			(midiNote: 24, noteName: "Db"),
			(midiNote: 38, noteName: "D#"),
			(midiNote: 38, noteName: "Eb"),
			(midiNote: 51, noteName: "Fb"),
			(midiNote: 64, noteName: "E#"),
			(midiNote: 77, noteName: "F#"),
			(midiNote: 77, noteName: "Gb"),
			(midiNote: 91, noteName: "G#"),
			(midiNote: 91, noteName: "Ab"),
			(midiNote: 105, noteName: "A#"),
			(midiNote: 105, noteName: "Bb"),
			(midiNote: 118, noteName: "Cb")
		];

		fixtures.do { |f|

			this.assertException(
				{ MEValidators.midiNamePairIsValid(f.midiNote, f.noteName, validate: true) },
				Error,
				"Testing invalid input MIDI note % note name %. Should throw Error.".format(f.midiNote, f.noteName)
			);
		};
	}

	/****************************************************************************************/

	test_midiNamePairIsValid_wrongInputMiddleOctaveManySigns {
		var fixtures = [
			(midiNote: 12, noteName: "G####"),
			(midiNote: 12, noteName: "Fbbbb"),
			(midiNote: 25, noteName: "A###"),
			(midiNote: 25, noteName: "Fbbb"),
			(midiNote: 38, noteName: "A####"),
			(midiNote: 38, noteName: "Gbbbb"),
			(midiNote: 51, noteName: "B###"),
			(midiNote: 51, noteName: "Gbbb"),
			(midiNote: 64, noteName: "B####"),
			(midiNote: 64, noteName: "Abbbb"),
			(midiNote: 77, noteName: "C####"),
			(midiNote: 77, noteName: "Abbb"),
			(midiNote: 90, noteName: "D###"),
			(midiNote: 90, noteName: "Bbbbb"),
			(midiNote: 103, noteName: "D####"),
			(midiNote: 103, noteName: "Bbbb"),
			(midiNote: 116, noteName: "E###"),
			(midiNote: 116, noteName: "Cbbb")
		];

		fixtures.do { |f|

			this.assertException(
				{ MEValidators.midiNamePairIsValid(f.midiNote, f.noteName, validate: true) },
				Error,
				"Testing invalid input MIDI note % note name %. Should throw Error.".format(f.midiNote, f.noteName)
			);
		};
	}

	/****************************************************************************************/
	// OCTAVE VALIDATORS: Unit Tests for octaveIsValid
	/****************************************************************************************/

	test_octaveIsValid_correctInput {
		var fixtures = (-1..9);

		fixtures.do { |f|

			this.assertEquals(
				nil,
				MEValidators.octaveIsValid(f, start: -1),
				"Testing correct input %. Should return 'nil'.".format(f)
			);
		};
	}

	/****************************************************************************************/

	test_octaveIsValid_wrongInput {
		var fixtures = [-2, 10, 1.0, "1", '1', $1];

		fixtures.do { |f|

			this.assertException(
				{ MEValidators.octaveIsValid(f, start: -1) },
				Error,
				"Testing wrong input %. Should throw error.".format(f)
			);
		};
	}

	/****************************************************************************************/
	// ÃCCIDENTAL SIGN VALIDATORS: Unit Tests for signOffsetIsValid
	/****************************************************************************************/

	test_signOffsetIsValid_correctInput {
		var fixtures = (-5..5);

		fixtures.do { |f|

			this.assertEquals(
				nil,
				MEValidators.signOffsetIsValid(f),
				"Testing correct input %. Should return 'nil'.".format(f)
			);
		};
	}

	/****************************************************************************************/

	test_signOffsetIsValid_wrongInput {
		var fixtures = [-6, 6, 1.0, "1", '1', $1];

		fixtures.do { |f|

			this.assertException(
				{ MEValidators.signOffsetIsValid(f) },
				Error,
				"Testing wrong input %. Should throw error.".format(f)
			);
		};
	}

}