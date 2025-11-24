/*********************************************************************************************
* MusicEngine - A dynamic chord library for SuperCollider   								 *
* Copyright (C) 2025 Siriil									    							 *
* Licensed under GPLv3. See LICENSE file for details.			    						 *
*********************************************************************************************/

TestMECore : UnitTest {
	classvar names;
	classvar notes;

	setUp {
		names = MECore.names;
		notes = MECore.notes;
	}

	test_names_dataCorrectness {

		this.assertEquals(
			['C', 'D', 'E', 'F', 'G', 'A', 'B'],
			MECore.names,
			"names should return an array with Symbols representing the seven note letters."
		);
	}

	test_notes_dataCorrectness {

		this.assertEquals(
			[0, 2, 4, 5, 7, 9, 11],
			MECore.notes,
			"notes should return an array containing MIDI notes for the C major scale in octave -1."
		);
	}

	test_indexOfLetter_indexCorrectness {

		names.do { |n, i|

			this.assertEquals(
				i,
				MECore.indexOfLetter(n),
				"indexOfLetter should return index of letter from MECore.names array."
			);
		};
	}

	test_indexOfLetter_stringInput {

		names.do { |n, i|

			this.assertEquals(
				i,
				MECore.indexOfLetter(n.asString),
				"indexOfLetter should also accept String."
			);
		};
	}

	test_indexOfLetter_wrongInput {
		var fixture = [1, 0.5, ["C"], "Z", 'a', "b"];

		fixture.do { |f|

			this.assertEquals(
				nil,
				MECore.indexOfLetter(f),
				"indexOfLetter should not accept anything other than letters A-G."
			);
		};
	}

	test_indexOfNote_indexCorrectness {

		notes.do { |n, i|

			this.assertEquals(
				i,
				MECore.indexOfNote(n),
				"indexOfNote should return index of note from MECore.notes array."
			);
		};
	}

	test_indexOfNote_wrongInput {
		var fixtures = [1, 0.5, "A", [], -8, 13];

		fixtures.do { |f|

			this.assertEquals(
				nil,
				MECore.indexOfNote(f),
				"indexofNote should not accept anithing other than %.".format(notes.join(", "))
			);
		};
	}

	test_noteFromLetter_mappingCorrectness {
		var index;

		names.do { |l|
			index = names.indexOf(l);

			this.assertEquals(
				notes[index],
				MECore.noteFromLetter(l),
				"noteFromLetter should map note letters to the first MIDI octave."
			);
		};
	}

	test_noteFromLetter_stringInput {
		var index;

		names.do { |l|
			index = names.indexOf(l);

			this.assertEquals(
				notes[index],
				MECore.noteFromLetter(l.asString),
				"noteFromLetter should also accept String."
			);
		};
	}

	test_noteFromLetter_wrongInput {
		var fixtures = ['a', "b", 'Z', ">", 1, 0.5, ["A"]];

		fixtures.do { |f|

			this.assertException(
				{ MECore.noteFromLetter(f) },
				Error,
				"noteFromLetter should not accept anything other than %.".format(names.join(", "))
			);
		};
	}

	test_noteFromLetter_roundTrip {
		var note;

		names.do { |n|
			note = MECore.noteFromLetter(n);

			this.assertEquals(
				n,
				MECore.letterFromNote(note),
				"n should be equal to letterFromNote(noteFromLetter(n))."
			);
		};
	}

	test_letterFromNote_mappingCorrectness {
		var index;

		notes.do { |n|
			index = notes.indexOf(n);

			this.assertEquals(
				names[index],
				MECore.letterFromNote(n),
				"letterFromNote should map MIDI notes, from the first octave, to note letters."
			);
		};
	}

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

	test_letterFromNote_roundTrip {
		var letter;

		notes.do { |n|
			letter = MECore.letterFromNote(n);

			this.assertEquals(
				n,
				MECore.noteFromLetter(letter),
				"n should be equal to noteFromLetter(letterFromNote(n))."
			);
		};
	}
}