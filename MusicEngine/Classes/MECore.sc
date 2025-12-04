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
			1  -> Set["m2", "m9", "A1", "A8"],
			2  -> Set["M2", "M9", "d3", "d10"],
			3  -> Set["m3", "m10", "A2", "A9"],
			4  -> Set["M3", "M10", "d4", "d11"],
			5  -> Set["P4", "P11", "A3", "A10"],
			6  -> Set["A4", "A11", "d5", "d12"],
			7  -> Set["P5", "P12", "d6", "d13"],
			8  -> Set["m6", "m13", "A5", "A12"],
			9  -> Set["M6", "M13", "d7", "d14"],
			10 -> Set["m7", "m14", "A6", "A13"],
			11 -> Set["M7", "M14", "d1", "d8"]
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
			MELetterValidators.noteLetterIsValid(noteLetter);
		};

		^letters.indexOf(noteLetter.asSymbol);
	}

	/****************************************************************************************/

	*indexOfNote { |midiNote, validate = true|

		if (validate) {
			MEMIDIValidators.midiOffsetIsValid(midiNote, diatonic: true);
		};

		^notes.indexOf(midiNote);
	}

	/****************************************************************************************/

	*noteFromLetter { |noteLetter, validate = true|
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