/*********************************************************************************************
* MusicEngine - A dynamic chord library for SuperCollider   								 *
* Copyright (C) 2025 Siriil									    							 *
* Licensed under GPLv3. See LICENSE file for details.			    						 *
*********************************************************************************************/

TestMEValidators : UnitTest {

	/****************************************************************************************/
	// Unit Tests for noteLetterIsValid
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
	// Unit Tests for letterOffsetIsValid
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
	// Unit Tests for letterOffsetArrayIsValid
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
}