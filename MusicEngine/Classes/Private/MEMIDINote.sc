/*********************************************************************************************
* MusicEngine - A dynamic music library for SuperCollider   								 *
* Copyright (C) 2025 Siriil									    							 *
* Licensed under GPLv3. See LICENSE file for details.			    						 *
*********************************************************************************************/

MEMIDINote {

	*initClass { ^this }

	/****************************************************************************************/

	*getOffsetFromName { |noteName, validate = true|
		var midiOffset, signOffset = 0;

		MEDebug.log(thisMethod, 1, [noteName]);

		if (validate) {
			MENameValidators.noteNameIsValid(noteName, octave: false);
		};

		if (noteName.size > 1) {
			signOffset = MEAccidental.getOffsetFromName(noteName, false);
		};

		midiOffset = MECore.offsetFromLetter(noteName[0], false) + signOffset;

		case
		{ midiOffset < 0  } { ^midiOffset + 12 }
		{ midiOffset > 11 } { ^midiOffset - 12 };

		^midiOffset;
	}

	/****************************************************************************************/

	*getMIDINoteFromName { |noteName|
		var regex = "^([A-G][#b]{0,5})([-]?[0-9])$";
		var octave, name, cross, midiOffset;

		#name, octave = noteName.findRegexp(regex)[1..].collect { |n| n[1] };

		midiOffset = this.getOffsetFromName(name);
		cross      = MEOctave.checkOctaveCross(name, false);
		octave     = octave.asInteger + 1 + cross;

		^midiOffset + (12 * octave);
	}

	/****************************************************************************************/

	*transposeMidiOffset { |midiOffsetArr, midiRoot, validate = true|

		MEDebug.log(thisMethod, 1, [midiOffsetArr, midiRoot]);

		if (validate) {
			MEMIDIValidators.midiOffsetArrayIsValid(midiOffsetArr, diatonic: false);
			MEMIDIValidators.midiOffsetIsValid(midiRoot, diatonic: false);
		};
		^midiRoot + midiOffsetArr;
	}
}