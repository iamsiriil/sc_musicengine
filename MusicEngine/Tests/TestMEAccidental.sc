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
}