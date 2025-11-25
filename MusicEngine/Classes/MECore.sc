/*********************************************************************************************
* MusicEngine - A dynamic chord library for SuperCollider   								 *
* Copyright (C) 2025 Siriil									    							 *
* Licensed under GPLv3. See LICENSE file for details.			    						 *
*********************************************************************************************/

MECore {
	classvar notes;
	classvar letters;
	classvar intervals;

	*initClass {

		notes = [0, 2, 4, 5, 7, 9, 11];

		letters = ['C', 'D', 'E', 'F', 'G', 'A', 'B'];

		intervals = Dictionary[
			"m2" -> [Set["m9"], 1],
			"M2" -> [Set["d3", "d10", "M9"], 2],
			"m3" -> [Set["A2", "A9",  "m10"], 3],
			"M3" -> [Set["d4", "d11", "M10"], 4],
			"P4" -> [Set["A3", "A10", "P11"], 5],
			"A4" -> [Set["d5", "d12", "A11"], 6],
			"P5" -> [Set["d6", "d13", "P12"], 7],
			"m6" -> [Set["A5", "A12", "m13"], 8],
			"M6" -> [Set["d7", "d14", "M13"], 9],
			"m7" -> [Set["A6", "A13", "m14"], 10],
			"M7" -> [Set["M14"], 11],
		];
	}

	/****************************************************************************************/

	*letters {
		^letters;
	}

	/****************************************************************************************/

	*notes {
		^notes;
	}

	/****************************************************************************************/

	*intervals {
		^intervals;
	}

	/****************************************************************************************/

	*indexOfLetter { |letter|
		^letters.indexOf(letter.asSymbol);
	}

	/****************************************************************************************/

	*indexOfNote { |note|
		^notes.indexOf(note);
	}

	/****************************************************************************************/

	*noteFromLetter { |letter|
		var index = MECore.indexOfLetter(letter);

		MEDebug.log("MECore", "*noteFromLetter");

		"letter: % | class: %".format(letter, letter.class).postln;

		// To be abstracted in special error handling class
		if ((letter.isKindOf(Symbol).not && letter.isKindOf(String).not && letter.isKindOf(Char).not) ||
			letters.includes(letter.asSymbol).not
		) {
			Error("% is not a valid letter. Only % allowed.".format(letter, letters.join(", "))).throw;
		};

		^notes[index];
	}

	/****************************************************************************************/

	*letterFromNote { |note|
		var index = MECore.indexOfNote(note);

		MEDebug.log("MECore", "*letterFromNote");

		// To be abstracted in special error handling class
		if (note.isInteger.not || notes.includes(note).not) {
			Error("% is not a valid note. Only % allowed.".format(note, notes.join(", "))).throw;
		};

		^letters[index]
	}
}