/*********************************************************************************************
* MusicEngine - A dynamic chord library for SuperCollider   								 *
* Copyright (C) 2025 Siriil									    							 *
* Licensed under GPLv3. See LICENSE file for details.			    						 *
*********************************************************************************************/

TestMEMIDIValidators : UnitTest {

	test_midiNoteIsValid_validInput {

		(0..127).do { |f|

			this.assertEquals(
				nil,
				MEMIDIValidators.midiNoteIsValid(f),
				"Testing valid MIDI note: %. Should return 'nil'.".format(f)
			);
		};
	}

	/****************************************************************************************/

	test_midiNoteIsValid_invalidInput {
		var fixtures = [-10, -1, 1.0, 128, "1", '1'];

		fixtures.do { |f|

			this.assertException(
				{ MEMIDIValidators.midiNoteIsValid(f) },
				Error,
				"Testing invalid MIDI note: %. Should throw Error.".format(f)
			);
		};
	}

	/****************************************************************************************/

	test_midiOffsetIsValid_validInputDiatonic {
		var fixtures = [0, 2, 4, 5, 7, 9, 11];

		fixtures.do { |f|

			this.assertEquals(
				nil,
				MEMIDIValidators.midiOffsetIsValid(f, diatonic: true),
				"Testing valid diatonic MIDI offset: %. Should return 'nil'.".format(f)
			);
		};
	}

	/****************************************************************************************/

	test_midiOffsetIsValid_validInputChromatic {
		var fixtures = (0..11);

		fixtures.do { |f|

			this.assertEquals(
				nil,
				MEMIDIValidators.midiOffsetIsValid(f, diatonic: false),
				"Testing valid chromatic MIDI offset: %. Should return 'nil'.".format(f)
			);
		};
	}

	/****************************************************************************************/

	test_midiOffsetIsValid_invalidInputDiatonic {
		var fixtures = [-1, 1, 3, 6, 8, 10, 12, "1", '1', $1, [1]];

		fixtures.do { |f|

			this.assertException(
				{ MEMIDIValidators.midiOffsetIsValid(f, diatonic: true) },
				Error,
				"Testing invalid diatonic MIDI offset: %. Should throw Error.".format(f)
			);
		};
	}

	/****************************************************************************************/

	test_midiOffsetIsValid_invalidInputChromatic {
		var fixtures = [-1, 12, 1.0, "1", '1', $1, [1]];

		fixtures.do { |f|

			this.assertException(
				{ MEMIDIValidators.midiOffsetIsValid(f, diatonic: false) },
				Error,
				"Testing invalid chromatic MIDI offset: %. Should throw Error.".format(f)
			);
		};
	}

	/****************************************************************************************/

	test_midiOffsetArrayIsValid_validInputTwoNotesDiatonic {
		var fixtures = [[0, 2],	[0, 4],	[0, 5],	[0, 7],	[0, 9],	[0, 11]];

		fixtures.do { |f|

			this.assertEquals(
				nil,
				MEMIDIValidators.midiOffsetArrayIsValid(f, diatonic: true),
				"Testing valid diatonic MIDI offset array: %. Should return 'nil'.".format(f.join(", "))
			);
		};
	}

	/****************************************************************************************/

	test_midiOffsetArrayIsValid_validInputTwoNotesChromatic {
		var fixtures = [
			[0, 1], [0, 2], [0, 3], [0, 4],  [0, 5], [0, 6],
			[0, 7], [0, 8], [0, 9], [0, 10], [0, 11]
		];

		fixtures.do { |f|

			this.assertEquals(
				nil,
				MEMIDIValidators.midiOffsetArrayIsValid(f, diatonic: false),
				"Testing valid chromatic MIDI offset array: %. Should return 'nil'.".format(f.join(", "))
			);
		};
	}

	/****************************************************************************************/

	test_midiOffsetArrayIsValid_validInputFourNotesDiatonic {
		var fixtures = [
			[0, 2, 4, 5],
			[0, 4, 5, 7],
			[0, 5, 7, 9],
			[0, 7, 9, 11]
		];

		fixtures.do { |f|

			this.assertEquals(
				nil,
				MEMIDIValidators.midiOffsetArrayIsValid(f, diatonic: true),
				"Testing valid diatonic MIDI offset array: %. Should return 'nil'.".format(f.join(", "))
			);
		};
	}

	/****************************************************************************************/

	test_midiOffsetArrayIsValid_validInputFourNotesChromatic {
		var fixtures = [
			[0, 2, 3, 4],
			[0, 3, 4, 5],
			[0, 4, 5, 6],
			[0, 5, 6, 7],
			[0, 6, 7, 8],
			[0, 7, 8, 9],
			[0, 8, 9, 10],
			[0, 9, 10, 11]
		];

		fixtures.do { |f|

			this.assertEquals(
				nil,
				MEMIDIValidators.midiOffsetArrayIsValid(f, diatonic: false),
				"Testing valid chromatic MIDI offset array: %. Should return 'nil'.".format(f.join(", "))
			);
		};
	}

	/****************************************************************************************/

	test_midiOffsetArrayIsValid_invalidInputEnharmonics {
		var fixtures = [
			[0, 0, 0, 0],
			[0, 1, 1, 2],
			[0, 1, 2, 2]
		];

		fixtures.do { |f|

			this.assertException(
				{ MEMIDIValidators.midiOffsetArrayIsValid(f) },
				Error,
				"Testing invalid MIDI offset array: %. Should throw Error.".format(f.join(", "))
			);
		};
	}

	/****************************************************************************************/

	test_midiOffsetArrayIsValid_invalidInput {
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
				{ MEMIDIValidators.midiOffsetArrayIsValid(f) },
				Error,
				"Testing invalid MIDI offset array: %. Should throw Error.".format(f.join(", "))
			);
		};
	}

	/****************************************************************************************/

	test_midiNamePairIsValid_validInputFirstOctaveNoSign {
		var fixtures = [
			(midiNote: 0, noteName: "C"),
			(midiNote: 2, noteName: "D"),
			(midiNote: 4, noteName: "E"),
			(midiNote: 5, noteName: "F"),
			(midiNote: 7, noteName: "G"),
			(midiNote: 9, noteName: "A"),
			(midiNote: 11, noteName: "B")
		];

		fixtures.do { |f|

			this.assertEquals(
				nil,
				MEMIDIValidators.midiNamePairIsValid(f.midiNote, f.noteName, validate: true),
				"Testing valid MIDI note: %, note name: %. Should return 'nil'.".format(f.midiNote, f.noteName)
			);
		};
	}

	/****************************************************************************************/

	test_midiNamePairIsValid_validInputFirstOctaveOnesign {
		var fixtures = [
			(midiNote: 0, noteName: "B#"),
			(midiNote: 1, noteName: "C#"),
			(midiNote: 1, noteName: "Db"),
			(midiNote: 3, noteName: "D#"),
			(midiNote: 3, noteName: "Eb"),
			(midiNote: 4, noteName: "Fb"),
			(midiNote: 5, noteName: "E#"),
			(midiNote: 6, noteName: "F#"),
			(midiNote: 6, noteName: "Gb"),
			(midiNote: 8, noteName: "G#"),
			(midiNote: 8, noteName: "Ab"),
			(midiNote: 10, noteName: "A#"),
			(midiNote: 10, noteName: "Bb"),
			(midiNote: 11, noteName: "Cb")
		];

		fixtures.do { |f|

			this.assertEquals(
				nil,
				MEMIDIValidators.midiNamePairIsValid(f.midiNote, f.noteName, validate: true),
				"Testing valid MIDI note: %, note name: %. Should return 'nil'.".format(f.midiNote, f.noteName)
			);
		};
	}

	/****************************************************************************************/

	test_midiNamePairIsValid_validInputFirstOctaveMultipleSigns {
		var fixtures = [
			(midiNote: 0, noteName: "G#####"),
			(midiNote: 0, noteName: "Fbbbbb"),
			(midiNote: 1, noteName: "A####"),
			(midiNote: 1, noteName: "Fbbbb"),
			(midiNote: 2, noteName: "A#####"),
			(midiNote: 2, noteName: "Gbbbbb"),
			(midiNote: 3, noteName: "B####"),
			(midiNote: 3, noteName: "Gbbbb"),
			(midiNote: 4, noteName: "B#####"),
			(midiNote: 4, noteName: "Abbbbb"),
			(midiNote: 5, noteName: "C#####"),
			(midiNote: 5, noteName: "Abbbb"),
			(midiNote: 6, noteName: "D####"),
			(midiNote: 6, noteName: "Bbbbbb"),
			(midiNote: 7, noteName: "D#####"),
			(midiNote: 7, noteName: "Bbbbb"),
			(midiNote: 8, noteName: "E####"),
			(midiNote: 8, noteName: "Cbbbb"),
			(midiNote: 9, noteName: "F####"),
			(midiNote: 9, noteName: "Dbbbbb"),
			(midiNote: 10, noteName: "F#####"),
			(midiNote: 10, noteName: "Dbbbb"),
			(midiNote: 11, noteName: "G####"),
			(midiNote: 11, noteName: "Ebbbbb")
		];

		fixtures.do { |f|

			this.assertEquals(
				nil,
				MEMIDIValidators.midiNamePairIsValid(f.midiNote, f.noteName, validate: true),
				"Testing valid MIDI note: %, note name: %. Should return 'nil'.".format(f.midiNote, f.noteName)
			);
		};
	}

	/****************************************************************************************/

	test_midiNamePairIsValid_validInputLastOctaveNoSign {
		var fixtures = [
			(midiNote: 120, noteName: "C"),
			(midiNote: 122, noteName: "D"),
			(midiNote: 124, noteName: "E"),
			(midiNote: 125, noteName: "F"),
			(midiNote: 127, noteName: "G")
		];

		fixtures.do { |f|

			this.assertEquals(
				nil,
				MEMIDIValidators.midiNamePairIsValid(f.midiNote, f.noteName, validate: true),
				"Testing valid MIDI note: %, note name: %. Should return 'nil'.".format(f.midiNote, f.noteName)
			);
		};
	}

	/****************************************************************************************/

	test_midiNamePairIsValid_validInputLastOctaveOnesign {
		var fixtures = [
			(midiNote: 120, noteName: "B#"),
			(midiNote: 121, noteName: "C#"),
			(midiNote: 121, noteName: "Db"),
			(midiNote: 123, noteName: "D#"),
			(midiNote: 123, noteName: "Eb"),
			(midiNote: 124, noteName: "Fb"),
			(midiNote: 125, noteName: "E#"),
			(midiNote: 126, noteName: "F#"),
			(midiNote: 126, noteName: "Gb")
		];

		fixtures.do { |f|

			this.assertEquals(
				nil,
				MEMIDIValidators.midiNamePairIsValid(f.midiNote, f.noteName, validate: true),
				"Testing valid MIDI note: %, note name: %. Should return 'nil'.".format(f.midiNote, f.noteName)
			);
		};
	}

	/****************************************************************************************/

	test_midiNamePairIsValid_validInputLastOctaveMultipleSigns {
		var fixtures = [
			(midiNote: 120, noteName: "G#####"),
			(midiNote: 120, noteName: "Fbbbbb"),
			(midiNote: 121, noteName: "A####"),
			(midiNote: 121, noteName: "Fbbbb"),
			(midiNote: 122, noteName: "A#####"),
			(midiNote: 122, noteName: "Gbbbbb"),
			(midiNote: 123, noteName: "B####"),
			(midiNote: 123, noteName: "Gbbbb"),
			(midiNote: 124, noteName: "B#####"),
			(midiNote: 124, noteName: "Abbbbb"),
			(midiNote: 125, noteName: "C#####"),
			(midiNote: 125, noteName: "Abbbb"),
			(midiNote: 126, noteName: "D####"),
			(midiNote: 126, noteName: "Bbbbbb"),
			(midiNote: 127, noteName: "D#####"),
			(midiNote: 127, noteName: "Bbbbb")
		];

		fixtures.do { |f|

			this.assertEquals(
				nil,
				MEMIDIValidators.midiNamePairIsValid(f.midiNote, f.noteName, validate: true),
				"Testing valid MIDI note: %, note name: %. Should return 'nil'.".format(f.midiNote, f.noteName)
			);
		};
	}

	/****************************************************************************************/

	test_midiNamePairIsValid_validtInputMiddleOctavesNoSign {
		var fixtures = [
			(midiNote: 12, noteName: "C"),
			(midiNote: 26, noteName: "D"),
			(midiNote: 40, noteName: "E"),
			(midiNote: 53, noteName: "F"),
			(midiNote: 67, noteName: "G"),
			(midiNote: 81, noteName: "A"),
			(midiNote: 95, noteName: "B"),
			(midiNote: 108, noteName: "C")
		];

		fixtures.do { |f|

			this.assertEquals(
				nil,
				MEMIDIValidators.midiNamePairIsValid(f.midiNote, f.noteName, validate: true),
				"Testing valid MIDI note: %, note name: %. Should return 'nil'.".format(f.midiNote, f.noteName)
			);
		};
	}

	/****************************************************************************************/

	test_midiNamePairIsValid_validInputMiddleOctaveOneSign {
		var fixtures = [
			(midiNote: 12, noteName: "B#"),
			(midiNote: 25, noteName: "C#"),
			(midiNote: 25, noteName: "Db"),
			(midiNote: 39, noteName: "D#"),
			(midiNote: 39, noteName: "Eb"),
			(midiNote: 52, noteName: "Fb"),
			(midiNote: 65, noteName: "E#"),
			(midiNote: 78, noteName: "F#"),
			(midiNote: 78, noteName: "Gb"),
			(midiNote: 92, noteName: "G#"),
			(midiNote: 92, noteName: "Ab"),
			(midiNote: 106, noteName: "A#"),
			(midiNote: 106, noteName: "Bb"),
			(midiNote: 119, noteName: "Cb")
		];

		fixtures.do { |f|

			this.assertEquals(
				nil,
				MEMIDIValidators.midiNamePairIsValid(f.midiNote, f.noteName, validate: true),
				"Testing valid MIDI note: %, note name: %. Should return 'nil'.".format(f.midiNote, f.noteName)
			);
		};
	}

	/****************************************************************************************/

	test_midiNamePairIsValid_validInputMiddleOctaveMultipleSigns {
		var fixtures = [
			(midiNote: 12, noteName: "G#####"),
			(midiNote: 12, noteName: "Fbbbbb"),
			(midiNote: 25, noteName: "A####"),
			(midiNote: 25, noteName: "Fbbbb"),
			(midiNote: 38, noteName: "A#####"),
			(midiNote: 38, noteName: "Gbbbbb"),
			(midiNote: 51, noteName: "B####"),
			(midiNote: 51, noteName: "Gbbbb"),
			(midiNote: 64, noteName: "B#####"),
			(midiNote: 64, noteName: "Abbbbb"),
			(midiNote: 77, noteName: "C#####"),
			(midiNote: 77, noteName: "Abbbb"),
			(midiNote: 90, noteName: "D####"),
			(midiNote: 90, noteName: "Bbbbbb"),
			(midiNote: 103, noteName: "D#####"),
			(midiNote: 103, noteName: "Bbbbb"),
			(midiNote: 116, noteName: "E####"),
			(midiNote: 116, noteName: "Cbbbb")
		];

		fixtures.do { |f|

			this.assertEquals(
				nil,
				MEMIDIValidators.midiNamePairIsValid(f.midiNote, f.noteName, validate: true),
				"Testing valid MIDI note: %, note name: %. Should return 'nil'.".format(f.midiNote, f.noteName)
			);
		};
	}

	/****************************************************************************************/

	test_midiNamePairIsValid_invalidInputFirstOctaveNoSign {
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
				{ MEMIDIValidators.midiNamePairIsValid(f.midiNote, f.noteName, validate: true) },
				Error,
				"Testing invalid MIDI note: %, note name: %. Should throw Error.".format(f.midiNote, f.noteName)
			);
		};
	}

	/****************************************************************************************/

	test_midiNamePairIsValid_invalidInputFirstOctaveOnesign {
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
				{ MEMIDIValidators.midiNamePairIsValid(f.midiNote, f.noteName, validate: true) },
				Error,
				"Testing invalid MIDI note: %, note name: %. Should throw Error.".format(f.midiNote, f.noteName)
			);
		};
	}

	/****************************************************************************************/

	test_midiNamePairIsValid_invalidInputFirstOctaveMultipleSigns {
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
				{ MEMIDIValidators.midiNamePairIsValid(f.midiNote, f.noteName, validate: true) },
				Error,
				"Testing invalid MIDI note: %, note name: %. Should throw Error.".format(f.midiNote, f.noteName)
			);
		};
	}

	/****************************************************************************************/

	test_midiNamePairIsValid_invalidInputLastOctaveNoSign {
		var fixtures = [
			(midiNote: 121, noteName: "C"),
			(midiNote: 123, noteName: "D"),
			(midiNote: 125, noteName: "E"),
			(midiNote: 124, noteName: "F"),
			(midiNote: 126, noteName: "G")
		];

		fixtures.do { |f|

			this.assertException(
				{ MEMIDIValidators.midiNamePairIsValid(f.midiNote, f.noteName, validate: true) },
				Error,
				"Testing invalid MIDI note: %, note name: %. Should throw Error.".format(f.midiNote, f.noteName)
			);
		};
	}

	/****************************************************************************************/

	test_midiNamePairIsValid_invalidInputLastOctaveOnesign {
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
				{ MEMIDIValidators.midiNamePairIsValid(f.midiNote, f.noteName, validate: true) },
				Error,
				"Testing invalid MIDI note: %, note name: %. Should throw Error.".format(f.midiNote, f.noteName)
			);
		};
	}

	/****************************************************************************************/

	est_midiNamePairIsValid_invalidInputLastOctaveMultipleSigns {
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
				{ MEMIDIValidators.midiNamePairIsValid(f.midiNote, f.noteName, validate: true) },
				Error,
				"Testing invalid MIDI note: %, note name: %. Should throw Error.".format(f.midiNote, f.noteName)
			);
		};
	}

	/****************************************************************************************/

	test_midiNamePairIsValid_invalidInputMiddleOctavesNoSign {
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
				{ MEMIDIValidators.midiNamePairIsValid(f.midiNote, f.noteName, validate: true) },
				Error,
				"Testing invalid MIDI note: %, note name: %. Should throw Error.".format(f.midiNote, f.noteName)
			);
		};
	}

	/****************************************************************************************/

	test_midiNamePairIsValid_invalidInputMiddleOctaveOneSign {
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
				{ MEMIDIValidators.midiNamePairIsValid(f.midiNote, f.noteName, validate: true) },
				Error,
				"Testing invalid MIDI note: %, note name: %. Should throw Error.".format(f.midiNote, f.noteName)
			);
		};
	}

	/****************************************************************************************/

	test_midiNamePairIsValid_invalidInputMiddleOctaveMultipleSigns {
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
				{ MEMIDIValidators.midiNamePairIsValid(f.midiNote, f.noteName, validate: true) },
				Error,
				"Testing invalid MIDI note: %, note name: %. Should throw Error.".format(f.midiNote, f.noteName)
			);
		};
	}
}