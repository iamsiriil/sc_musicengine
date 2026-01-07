/*********************************************************************************************
* MusicEngine - A dynamic chord library for SuperCollider   								 *
* Copyright (C) 2025 Siriil									    							 *
* Licensed under GPLv3. See LICENSE file for details.			    						 *
*********************************************************************************************/

MEOctave {

	*initClass { ^this }

	/****************************************************************************************/

	*getClosestOctave { |midiNote, noteLetter, validate = true|
		var ref;

		MEDebug.log(thisMethod, 2);

		if (validate) {
			MEMIDIValidators.midiNoteIsValid(midiNote);
			MELetterValidators.noteLetterIsValid(noteLetter);
		};

		ref = MECore.offsetFromLetter(noteLetter, false);

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

		MEDebug.log(thisMethod, 2);

		if (validate) {
			MENameValidators.noteNameIsValid(noteName);
		};

		midiOffset = MECore.offsetFromLetter(noteName[0], false);
		signOffset = MEAccidental.getOffsetFromName(noteName, false);
		cross      = midiOffset + signOffset;

		case
		{ cross < 0 }  { ^-1 }
		{ cross > 11 } { ^1 }
		{ ^0 }
	}

	/****************************************************************************************/

	*getOctave { |midiNote, noteName = nil, validate = true|
		var cross, octave = -1;

		MEDebug.log(thisMethod, 2);

		if (validate) {

			if (noteName.notNil) {
				MEMIDIValidators.midiNamePairIsValid(midiNote, noteName);
			};
			MEMIDIValidators.midiNoteIsValid(midiNote);
		};

		octave = (octave + (midiNote/12).floor).asInteger;

		if (noteName.notNil) {
			cross = this.checkOctaveCross(noteName, false);
			^octave + (cross * -1);
		};
		^octave;
	}
}