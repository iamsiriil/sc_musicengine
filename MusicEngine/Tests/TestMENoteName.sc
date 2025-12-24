/*********************************************************************************************
* MusicEngine - A dynamic chord library for SuperCollider   								 *
* Copyright (C) 2025 Siriil									    							 *
* Licensed under GPLv3. See LICENSE file for details.			    						 *
*********************************************************************************************/

TestMENoteName : UnitTest {

	test_getOffsetFromInterval_validInputUnder8th {
		var fixtures = [
			(expected: 1, interval: "m2"),
			(expected: 0, interval: "A1"),
			(expected: 1, interval: "M2"),
			(expected: 2, interval: "d3"),
			(expected: 2, interval: "m3"),
			(expected: 1, interval: "A2"),
			(expected: 2, interval: "M3"),
			(expected: 3, interval: "d4"),
			(expected: 3, interval: "P4"),
			(expected: 2, interval: "A3"),
			(expected: 3, interval: "A4"),
			(expected: 4, interval: "d5"),
			(expected: 4, interval: "P5"),
			(expected: 5, interval: "d6"),
			(expected: 5, interval: "m6"),
			(expected: 4, interval: "A5"),
			(expected: 5, interval: "M6"),
			(expected: 6, interval: "d7"),
			(expected: 6, interval: "m7"),
			(expected: 5, interval: "A6"),
			(expected: 6, interval: "M7"),
			(expected: 0, interval: "d1")
		];

		fixtures.do { |f|

			this.assertEquals(
				MENoteName.getOffsetFromInterval(f.interval, validate: true),
				f.expected,
				"Testing valid interval: %. Should return %.".format(f.interval, f.expected)
			);
		};
	}

	/****************************************************************************************/

	test_getOffsetFromInterval_validInputOver8th {
		var fixtures = [
			(expected: 1, interval: "m9"),
			(expected: 0, interval: "A8"),
			(expected: 1, interval: "M9"),
			(expected: 2, interval: "d10"),
			(expected: 2, interval: "m10"),
			(expected: 1, interval: "A9"),
			(expected: 2, interval: "M10"),
			(expected: 3, interval: "d11"),
			(expected: 3, interval: "P11"),
			(expected: 2, interval: "A10"),
			(expected: 3, interval: "A11"),
			(expected: 4, interval: "d12"),
			(expected: 4, interval: "P12"),
			(expected: 5, interval: "d13"),
			(expected: 5, interval: "m13"),
			(expected: 4, interval: "A12"),
			(expected: 5, interval: "M13"),
			(expected: 6, interval: "d14"),
			(expected: 6, interval: "m14"),
			(expected: 5, interval: "A13"),
			(expected: 6, interval: "M14"),
			(expected: 0, interval: "d8")
		];

		fixtures.do { |f|

			this.assertEquals(
				MENoteName.getOffsetFromInterval(f.interval, validate: true),
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
				{ MENoteName.getOffsetFromInterval(f, validate: true) },
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
				{ MENoteName.getOffsetFromInterval(f, validate: true) },
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
				{ MENoteName.getOffsetFromInterval(f, validate: true) },
				Error,
				"Testing invalid interval: %. Should throw Error.".format(f)
			);
		};
	}

	/****************************************************************************************/

	test_getOffsetArray_validInputUnder8th {
		var fixtures = [
			["m2", "d3", "d4", "d5", "d6", "d7"],
			["m2", "m3", "d4", "d5", "m6", "m7"],
			["M2", "M3", "P4", "P5", "M6", "M7"],
			["A2", "A3", "A4", "A5", "A6", "M7"]
		];

		fixtures.do { |f|

			this.assertEquals(
				MENoteName.getOffsetArray(f, validate: true),
				[0, 1, 2, 3, 4, 5, 6],
				"Testing valid interval array: %. Should return [0, 1, 2, 3, 4, 5, 6].".format(f)
			);
		};
	}

	/****************************************************************************************/

	test_getOffsetArray_validInputOver8th {
		var fixtures = [
			["m9", "d10", "d11", "d12", "d13", "d14"],
			["m9", "m10", "d11", "d12", "m13", "m14"],
			["M9", "M10", "P11", "P12", "M13", "M14"],
			["A9", "A10", "A11", "A12", "A13", "M14"]
		];

		fixtures.do { |f|

			this.assertEquals(
				MENoteName.getOffsetArray(f, validate: true),
				[0, 1, 2, 3, 4, 5, 6],
				"Testing valid interval array: %. Should return [0, 1, 2, 3, 4, 5, 6].".format(f)
			);
		};
	}

	/****************************************************************************************/

	test_getOffsetArray_invalidInputUnder8th {
		var fixtures = [
			["P1", "M3", "P4", "P5", "M6", "M7"],
			["d2", "M3", "P4", "P5", "M6", "M7"],
			["P2", "M3", "P4", "P5", "M6", "M7"],
			["M2", "P3", "P4", "P5", "M6", "M7"],
			["M2", "M3", "m4", "P5", "M6", "M7"],
			["M2", "M3", "M4", "P5", "M6", "M7"],
			["M2", "M3", "P4", "m5", "M6", "M7"],
			["M2", "M3", "P4", "M5", "M6", "M7"],
			["M2", "M3", "P4", "P5", "P6", "M7"],
			["M2", "M3", "P4", "P5", "M6", "P7"],
			["M2", "M3", "P4", "P5", "M6", "A7"]
		];

		fixtures.do { |f|

			this.assertException(
				{ MENoteName.getOffsetArray(f, validate: true) },
				Error,
				"Testing invalid interval array: %. Should throw Error.".format(f)
			);
		};
	}

	/****************************************************************************************/

	test_getOffsetArray_invalidInputOver8th {
		var fixtures = [
			["P8", "M10", "P11", "P12", "M13", "M14"],
			["d9", "M10", "P11", "P12", "M13", "M14"],
			["P9", "M10", "P11", "P12", "M13", "M14"],
			["M9", "P10", "P11", "P12", "M13", "M14"],
			["M9", "M10", "m11", "P12", "M13", "M14"],
			["M9", "M10", "M11", "P12", "M13", "M14"],
			["M9", "M10", "P11", "m12", "M13", "M14"],
			["M9", "M10", "P11", "M12", "M13", "M14"],
			["M9", "M10", "P11", "P12", "P13", "M14"],
			["M9", "M10", "P11", "P12", "M13", "P14"],
			["M9", "M10", "P11", "P12", "M13", "A14"]
		];

		fixtures.do { |f|

			this.assertException(
				{ MENoteName.getOffsetArray(f, validate: true) },
				Error,
				"Testing invalid interval array: %. Should throw Error.".format(f)
			);
		};
	}

	/****************************************************************************************/

	test_getOffsetArray_invalidDataType {
		var fixtures = [
			['M2', "M3", "P4", "P5", "M6", "M7"],
			[$2, "P3", "P4", "P5", "M6", "M7"],
			[2, "M3", "P4", "P5", "M6", "M7"],
			[2.0, "M3", "P4", "P5", "M6", "M7"],
			[["M2"], "M3", "m4", "P5", "M6", "M7"]
		];

		fixtures.do { |f|

			this.assertException(
				{ MENoteName.getOffsetArray(f, validate: true) },
				Error,
				"Testing invalid interval array: %. Should throw Error.".format(f)
			);
		};
	}

	/****************************************************************************************/

	test_getNoteLetters_validInput {
		var fixtures = [
			(expected: ["C", "E", "G", "B"], letterOffsetArr: [0, 2, 4, 6], rootLetter: "C"),
			(expected: ["D", "F", "A", "C"], letterOffsetArr: [0, 2, 4, 6], rootLetter: "D"),
			(expected: ["E", "G", "B", "D"], letterOffsetArr: [0, 2, 4, 6], rootLetter: "E"),
			(expected: ["F", "A", "C", "E"], letterOffsetArr: [0, 2, 4, 6], rootLetter: "F"),
			(expected: ["G", "B", "D", "F"], letterOffsetArr: [0, 2, 4, 6], rootLetter: "G"),
			(expected: ["A", "C", "E", "G"], letterOffsetArr: [0, 2, 4, 6], rootLetter: "A"),
			(expected: ["B", "D", "F", "A"], letterOffsetArr: [0, 2, 4, 6], rootLetter: "B")
		];

		fixtures.do { |f|

			this.assertEquals(
				MENoteName.getNoteLetters(f.letterOffsetArr, f.rootLetter, validate: true),
				f.expected,
				"Testing valid letter offset array: %, root letter: %. Should return %.".format(f.letterOffsetArray, f.rootLetter, f.expected)
			);
		};
	}

	/****************************************************************************************/

	test_getNoteLetters_invalidLetterOffsetArrayOutOfRange {
		var fixtures = [
			(letterOffsetArr: [-1, 2, 4, 6], rootLetter: "C"),
			(letterOffsetArr: [0, 2, 4, 7], rootLetter: "C")
		];

		fixtures.do { |f|

			this.assertException(
				{ MENoteName.getNoteLetters(f.letterOffsetArr, f.rootLetter, validate: true) },
				Error,
				"Testing invalid offset array: %, valid root letter: %. Should throw Error.".format(f.letterOffsetArr, f.rootLetter)
			);
		};
	}

	/****************************************************************************************/

	test_getNoteLetters_invalidLetterOffsetArrayDataType {
		var fixtures = [
			(letterOffsetArr: ["0", 2, 4, 6], rootLetter: "C"),
			(letterOffsetArr: ['0', 2, 4, 7], rootLetter: "C"),
			(letterOffsetArr: [$0, 2, 4, 7], rootLetter: "C"),
			(letterOffsetArr: [0.0, 2, 4, 7], rootLetter: "C"),
			(letterOffsetArr: [[0], 2, 4, 7], rootLetter: "C")
		];

		fixtures.do { |f|

			this.assertException(
				{ MENoteName.getNoteLetters(f.letterOffsetArr, f.rootLetter, validate: true) },
				Error,
				"Testing invalid offset array: %, valid root letter: %. Should throw Error.".format(f.letterOffsetArr, f.rootLetter)
			);
		};
	}

	/****************************************************************************************/

	test_getNoteLetters_invalidRootLetter {
		var fixtures = [
			(letterOffsetArr: [0, 2, 4, 6], rootLetter: "c"),
			(letterOffsetArr: [0, 2, 4, 6], rootLetter: "X"),
			(letterOffsetArr: [0, 2, 4, 6], rootLetter: "+"),
			(letterOffsetArr: [0, 2, 4, 6], rootLetter: "1")
		];

		fixtures.do { |f|

			this.assertException(
				{ MENoteName.getNoteLetters(f.letterOffsetArr, f.rootLetter, validate: true) },
				Error,
				"Testing valid offset array: %, invalid root letter: %. Should throw Error.".format(f.letterOffsetArr, f.rootLetter)
			);
		};
	}

	/****************************************************************************************/

	test_getNoteLetters_invalidRootLetterDataType {
		var fixtures = [
			(letterOffsetArr: [0, 2, 4, 6], rootLetter: 'C'),
			(letterOffsetArr: [0, 2, 4, 6], rootLetter: $C),
			(letterOffsetArr: [0, 2, 4, 6], rootLetter: 1),
			(letterOffsetArr: [0, 2, 4, 6], rootLetter: 1.0),
			(letterOffsetArr: [0, 2, 4, 6], rootLetter: ["C"])
		];

		fixtures.do { |f|

			this.assertException(
				{ MENoteName.getNoteLetters(f.letterOffsetArr, f.rootLetter, validate: true) },
				Error,
				"Testing valid offset array: %, invalid root letter: %. Should throw Error.".format(f.letterOffsetArr, f.rootLetter)
			);
		};
	}
}