/*********************************************************************************************
* MusicEngine - A dynamic chord library for SuperCollider   								 *
* Copyright (C) 2025 Siriil									    							 *
* Licensed under GPLv3. See LICENSE file for details.			    						 *
*********************************************************************************************/

MEValidators {

	*initClass {}

	/****************************************************************************************/
	// NOTE LETTER VALIDATORS
	/****************************************************************************************/

	*noteLetterIsValid { |noteLetter|
		var regex = "^[A-G]$";

		MEDebug.log("MEValidators", "*noteLetterIsValid");

		if (regex.matchRegexp(noteLetter.asString) == false) {
			Error("% is not a valid note letter.".format(noteLetter)).throw;
		};
	}

	/****************************************************************************************/

	*letterOffsetIsValid { |letterOffset|
		var offsets = (0..6).asSet;

		if (letterOffset.isInteger.not || offsets.includes(letterOffset).not) {
			Error("% is not a valid letter offset.".format(letterOffset)).throw;
		};
	}

	/****************************************************************************************/

	*letterOffsetArrayIsValid { |letterOffsetArr|

		letterOffsetArr.do { |o|
			this.letterOffsetIsValid(o);
		};
	}

	/****************************************************************************************/
	// NOTE NAME VALIDATORS
	/****************************************************************************************/

	*noteNameIsValid { |noteName|
		var regex = "^[A-G][#b]{0,12}-{0,1}[0-9]{0,1}$";

		MEDebug.log("MEValidators", "*noteNameIsValid");

		if (regex.matchRegexp(noteName) == false) {
			Error("% is not a valid note name.".format(noteName)).throw;
		};
	}

	/****************************************************************************************/

	*rootNoteIsValid { |rootNote| // rootName
		var regex = "^[A-G][#b]{0,1}$";

		MEDebug.log("MEValidators", "*rootNoteIsValid");

		if (regex.matchRegexp(rootNote) == false) {
			Error("% is not a valid root note.".format(rootNote)).throw;
		};
	}

	/****************************************************************************************/
	// MIDI VALIDATORS
	/****************************************************************************************/

	*midiNoteIsValid { |midiNote, min = 0, max = 127|

		MEDebug.log("MEValidators", "*midiNoteIsValid");

		if ((midiNote < min) || (midiNote > max) || midiNote.isInteger.not) {
			Error("% is not a valid MIDI note.".format(midiNote)).throw;
		};
	}

	/****************************************************************************************/

	*midiOffsetIsValid { |midiOffset, diatonic = true, negative = false|
		var offsets, min;

		if (diatonic) {
			offsets = Set[0, 2, 4, 5, 7, 9, 11];
		} {
			min     = if (negative) { -1 } { 0 };
			offsets = (min..11).asSet.postln;
		};

		MEDebug.log("MEValidators", "*midiOffsetIsValid");

		if (midiOffset.isInteger.not || offsets.includes(midiOffset).not) {
			Error("% is not a valid MIDI offset.".format(midiOffset)).throw;
		};
	}

	/****************************************************************************************/

	*midiOffsetArrayIsValid { |midiOffsetArr, diatonic = true, negative = false|
		var arrSize = midiOffsetArr.size;
		var setSize = midiOffsetArr.asSet.size;

		if (arrSize > setSize) {
			Error("% contains enharmonics.".format(midiOffsetArr.join(", "))).throw;
		};

		midiOffsetArr.do { |o|
			this.midiOffsetIsValid(o, diatonic, negative);
		};
	}

	/****************************************************************************************/
	// MIDI NOTE AND NOTE NAME PAIR VALIDATOR
	/****************************************************************************************/

	*midiNamePairIsValid { |midiNote, noteName|
		var sign  = MEAccidental.getOffsetFromName(noteName); // validate: false
		var cross = MEOctaves.checkOctaveCross(noteName); // validate: false
		var ref   = MECore.noteFromLetter(noteName[0]); // validate: false
		var oct   = (midiNote / 12).floor;
		var note  = (midiNote - (12 * oct));

		MEDebug.log("MEValidators", "*midiNamePairIsValid");

		if (cross != 0) {
			case
			{ cross == -1 } { ref = ref + 12 }
			{ cross == 1 }  { ref = ref - 12 }
		};

		if ((ref + sign) != note) {
			Error("% is not a valid representation of MIDI note %.".format(
				noteName,
				midiNote)
			).throw;
		};
	}

	/****************************************************************************************/
	// OCTAVE VALIDATOR
	/****************************************************************************************/

	*octaveIsValid { |octave, min = -1, max = 9|

		MEDebug.log("MEValidators", "*octaveIsValid");

		if ((octave < min) || (octave > max) || (octave.isInteger.not)) {
			Error("% is not a valid octave number.".format(octave)).throw;
		};
	}

	/****************************************************************************************/
	// ACCIDENTAL SIGN VALIDATOR
	/****************************************************************************************/

	*signOffsetIsValid { |signOffset|
		var offsets = (-5..5).asSet;

		if (signOffset.isInteger.not || offsets.includes(signOffset).not) {
			Error("% is not a valid sign offset.".format(signOffset)).throw;
		};
	}

	/****************************************************************************************/
	// INTERVAL VALIDATOR
	/****************************************************************************************/

	*intervalIsValid { |interval|
		var result = false;

		MECore.intervals.do { |v|
			result = result || v.includes(interval);
		};

		if (result == false) {
			Error("% is not a valid interval.".format(interval)).throw;
		};
	}
}