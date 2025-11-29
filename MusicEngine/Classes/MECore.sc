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
			1  -> Set["m2", "m9"],
			2  -> Set["M2", "d3", "d10", "M9"],
			3  -> Set["m3", "A2", "A9",  "m10"],
			4  -> Set["M3", "d4", "d11", "M10"],
			5  -> Set["P4", "A3", "A10", "P11"],
			6  -> Set["A4", "d5", "d12", "A11"],
			7  -> Set["P5", "d6", "d13", "P12"],
			8  -> Set["m6", "A5", "A12", "m13"],
			9  -> Set["M6", "d7", "d14", "M13"],
			10 -> Set["m7", "A6", "A13", "m14"],
			11 -> Set["M7", "M14"]
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

	*indexOfLetter { |noteLetter, validate = true|

		if (validate) {
			MEValidators.noteLetterIsValid(noteLetter);
		};

		^letters.indexOf(noteLetter.asSymbol);
	}

	/****************************************************************************************/

	*indexOfNote { |midiNote, validate = true|

		if (validate) {
			MEValidators.midiOffsetIsValid(midiNote);
		};

		^notes.indexOf(midiNote);
	}

	/****************************************************************************************/

	*noteFromLetter { |noteLetter, validate = true| // noteLetter
		var index = MECore.indexOfLetter(noteLetter, validate);

		MEDebug.log("MECore", "*noteFromLetter");

		^notes[index];
	}

	/****************************************************************************************/

	*letterFromNote { |midiNote, validate = true|
		var index = MECore.indexOfNote(midiNote, validate);

		MEDebug.log("MECore", "*letterFromNote");

		^letters[index]
	}
}