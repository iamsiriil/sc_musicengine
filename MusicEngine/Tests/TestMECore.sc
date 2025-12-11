/*********************************************************************************************
* MusicEngine - A dynamic chord library for SuperCollider   								 *
* Copyright (C) 2025 Siriil									    							 *
* Licensed under GPLv3. See LICENSE file for details.			    						 *
*********************************************************************************************/

TestMECore : UnitTest {
	classvar letters;
	classvar notes;

	setUp {
		letters = MECore.letters;
		notes   = MECore.notes;
	}

	/****************************************************************************************/

	test_names_dataCorrectness {

		this.assertEquals(
			['C', 'D', 'E', 'F', 'G', 'A', 'B'],
			MECore.letters,
			"names should return an array with Symbols representing the seven note letters."
		);
	}

	/****************************************************************************************/

	test_notes_dataCorrectness {

		this.assertEquals(
			[0, 2, 4, 5, 7, 9, 11],
			MECore.notes,
			"notes should return an array containing MIDI notes for the C major scale in octave -1."
		);
	}

	/****************************************************************************************/

	test_indexOfLetter_stringInput {

		letters.do { |n, i|

			this.assertEquals(
				i,
				MECore.indexOfLetter(n.asString),
				"indexOfLetter should also accept String."
			);
		};
	}

	/****************************************************************************************/

	test_indexOfLetter_wrongInput {
		var fixture = [1, 0.5, ["C"], "Z", 'a', "b"];

		fixture.do { |f|

			this.assertException(
				{ MECore.indexOfLetter(f) },
				Error,
				"indexOfLetter should not accept anything other than letters A-G."
			);
		};
	}

	/****************************************************************************************/

	test_indexOfNote_indexCorrectness {

		notes.do { |n, i|

			this.assertEquals(
				i,
				MECore.indexOfNote(n),
				"indexOfNote should return index of note from MECore.notes array."
			);
		};
	}

	/****************************************************************************************/

	test_indexOfNote_wrongInput {
		var fixtures = [1, 0.5, "A", [], -8, 13];

		fixtures.do { |f|

			this.assertException(
				{ MECore.indexOfNote(f) },
				Error,
				"indexofNote should not accept anithing other than %.".format(notes.join(", "))
			);
		};
	}

	/****************************************************************************************/

	test_noteFromLetter_mappingCorrectness {
		var index;

		letters.do { |l|
			index = letters.indexOf(l);

			this.assertEquals(
				notes[index],
				MECore.noteFromLetter(l.asString),
				"noteFromLetter should map note letters to the first MIDI octave."
			);
		};
	}

	/****************************************************************************************/

	test_noteFromLetter_stringInput {

		letters.do { |l, i|

			this.assertEquals(
				notes[i],
				MECore.noteFromLetter(l.asString),
				"noteFromLetter should also accept String."
			);
		};
	}

	/****************************************************************************************/

	test_noteFromLetter_wrongInput {
		var fixtures = ['a', "b", 'Z', ">", 1, 0.5, ["A"]];

		fixtures.do { |f|

			this.assertException(
				{ MECore.noteFromLetter(f) },
				Error,
				"noteFromLetter should not accept anything other than %.".format(letters.join(", "))
			);
		};
	}

	/****************************************************************************************/

	test_noteFromLetter_roundTrip {
		var note;

		letters.do { |l|
			note = MECore.noteFromLetter(l.asString);

			this.assertEquals(
				l,
				MECore.letterFromNote(note),
				"n should be equal to letterFromNote(noteFromLetter(n))."
			);
		};
	}

	/****************************************************************************************/

	test_letterFromNote_mappingCorrectness {

		notes.do { |n, i|

			this.assertEquals(
				letters[i],
				MECore.letterFromNote(n),
				"letterFromNote should map MIDI notes, from the first octave, to note letters."
			);
		};
	}

	/****************************************************************************************/

	test_letterFromNote_wrongInput {
		var fixtures = ["b", 'Z', 1, 0.5, 12, ["A"]];

		fixtures.do { |f|

			this.assertException(
				{ MECore.letterFromNote(f) },
				Error,
				"letterFromNote should not accept anything other than %.".format(notes.join(", "))
			);
		};
	}

	/****************************************************************************************/

	test_letterFromNote_roundTrip {
		var letter;

		notes.do { |n|
			letter = MECore.letterFromNote(n);

			this.assertEquals(
				n,
				MECore.noteFromLetter(letter.asString),
				"n should be equal to noteFromLetter(letterFromNote(n))."
			);
		};
	}
}