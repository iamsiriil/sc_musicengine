/*********************************************************************************************
* MusicEngine - A dynamic chord library for SuperCollider   								 *
* Copyright (C) 2025 Siriil									    							 *
* Licensed under GPLv3. See LICENSE file for details.			    						 *
*********************************************************************************************/

MEOctaves : MECore {

	*initClass {}

	/****************************************************************************************/

	*getClosestOctave { |midiNote, noteLetter, validate = true|
		var ref;

		MEDebug.log("MEOctaves", "*getClosestOctave");

		if (validate) {
			MEValidators.midiNoteIsValid(midiNote, min: -1);
			MEValidators.noteLetterIsValid(noteLetter);
		};

		ref = super.noteFromLetter(noteLetter, validate: false);

		case
		{ (midiNote >= 5) && (midiNote <= 127) } {

			while { ((ref >= (midiNote - 5)) && (ref <= (midiNote + 5))).not && (ref <= 127) } {

				ref = (ref + 12)
			};
		}
		{ (midiNote < 5)  && (noteLetter.asString == "B") } {

			ref = ref - 12;
		};

		if (((ref - midiNote).abs >= 6) || (ref > 129)) { ^nil } { ^ref };
	}

	/****************************************************************************************/

	*checkOctaveCross { |noteName, validate = true|
		var midiOffset, signOffset, cross;

		MEDebug.log("MEOctaves", "*checkOctaveCross");

		if (validate) {
			MEValidators.noteNameIsValid(noteName);
		};

		midiOffset = super.noteFromLetter(noteName[0], validate: false);
		signOffset = MEAccidental.getOffsetFromName(noteName, validate: false);
		cross      = midiOffset + signOffset;

		case
		{ cross < 0 }  { ^-1 }
		{ cross > 11 } { ^1 }
		{ ^0 }
	}

	/****************************************************************************************/

	*getOctave { |midiNote, noteName = nil, validate = true|
		var cross, octave = -1;

		MEDebug.log("MEOctaves", "*getOctave");

		if (validate) {

			if (noteName.notNil) {
				MEValidators.midiNamePairIsValid(midiNote, noteName);
			};
			MEValidators.midiNoteIsValid(midiNote);
		};

		octave = (octave + (midiNote/12).floor).asInteger;

		if (noteName.notNil) {
			cross = MEOctaves.checkOctaveCross(noteName, validate: false);
			^octave + (cross * -1);
		};
		^octave;
	}
}