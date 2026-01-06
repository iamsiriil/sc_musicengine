/*********************************************************************************************
* MusicEngine - A dynamic chord library for SuperCollider   								 *
* Copyright (C) 2025 Siriil									    							 *
* Licensed under GPLv3. See LICENSE file for details.			    						 *
*********************************************************************************************/

MEMIDIValidators {

	*initClass { ^this }

	/****************************************************************************************/

	*midiNoteIsValid { |midiNote|
		var min;

		MEDebug.log(thisMethod, 3);

		if (midiNote.isInteger.not) {
			Error("MIDI offset must be of type Integer.").throw;
		};
		if ((midiNote < 0) || (midiNote > 127)) {
			Error("% is not a valid MIDI note.".format(midiNote)).throw;
		};
		^nil;
	}

	/****************************************************************************************/

	*midiOffsetIsValid { |midiOffset, diatonic = true|
		var offsets, min;

		MEDebug.log(thisMethod, 3);

		if (midiOffset.isInteger.not) {
			Error("MIDI offset must be of type Integer.").throw;
		};

		if (diatonic) {
			offsets = [0, 2, 4, 5, 7, 9, 11].asSet;
		} {
			offsets = (0..11).asSet;
		};

		if (offsets.includes(midiOffset).not) {
			Error("% is not a valid MIDI offset.".format(midiOffset)).throw;
		};
		^nil;
	}

	/****************************************************************************************/

	*midiOffsetArrayIsValid { |midiOffsetArr, diatonic = true|
		var arrSize, setSize;

		MEDebug.log(thisMethod, 3);

		if (midiOffsetArr[0] != 0) {
			Error("MIDI offset array must start with '0'.".format(midiOffsetArr.join(", ")));
		};

		arrSize = midiOffsetArr.size;
		setSize = midiOffsetArr.asSet.size;

		if (arrSize > setSize) {
			Error("% contains enharmonics.".format(midiOffsetArr.join(", "))).throw;
		};

		midiOffsetArr.do { |o|
			this.midiOffsetIsValid(o, diatonic);
		};
		^nil;
	}

	/****************************************************************************************/

	*midiNamePairIsValid { |midiNote, noteName, validate = true|
		var signOffset, cross, reference;
		var octave, noteFirstOct;

		MEDebug.log(thisMethod, 3);

		if (validate) {
			this.midiNoteIsValid(midiNote);
			MENameValidators.noteNameIsValid(noteName);
		};

		signOffset  = MEAccidental.getOffsetFromName(noteName, validate: false);
		reference   = MECore.offsetFromLetter(noteName[0], validate: false);
		cross       = MEOctaves.checkOctaveCross(noteName, validate: false);

		octave        = (midiNote / 12).floor;
		noteFirstOct  = (midiNote - (12 * octave));

		if (cross != 0) {
			case
			{ cross == -1 } { reference = reference + 12 }
			{ cross == 1 }  { reference = reference - 12 }
		};

		if ((reference + signOffset) != noteFirstOct) {
			Error("% is not a valid representation of MIDI note %.".format(
				noteName,
				midiNote)
			).throw;
		};
		^nil;
	}
}