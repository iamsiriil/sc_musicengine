/*********************************************************************************************
* MusicEngine - A dynamic chord library for SuperCollider   								 *
* Copyright (C) 2025 Siriil									    							 *
* Licensed under GPLv3. See LICENSE file for details.			    						 *
*********************************************************************************************/

MECore {
	classvar <offsets = #[0, 2, 4, 5, 7, 9, 11];
	classvar <letters = #["C", "D", "E", "F", "G", "A", "B"];
	classvar <intervals;

	*initClass {

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

		^this;
	}

	/****************************************************************************************/

	*indexOfLetter { |noteLetter, validate = true|

		if (validate) {
			MELetterValidators.noteLetterIsValid(noteLetter);
		};

		^letters.detectIndex { |i| i == noteLetter.asString };
	}

	/****************************************************************************************/

	*indexOfOffset { |midiOffset, validate = true|

		if (validate) {
			MEMIDIValidators.midiOffsetIsValid(midiOffset, diatonic: true);
		};

		^offsets.indexOf(midiOffset);
	}

	/****************************************************************************************/

	*letterFromOffset { |midiOffset, validate = true|

		^letters[this.indexOfOffset(midiOffset, validate)]
	}

	/****************************************************************************************/

	*offsetFromLetter { |noteLetter, validate = true|

		^offsets[this.indexOfLetter(noteLetter, validate)];
	}
}