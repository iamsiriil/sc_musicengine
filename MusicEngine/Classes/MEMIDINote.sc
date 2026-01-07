/*********************************************************************************************
* MusicEngine - A dynamic chord library for SuperCollider   								 *
* Copyright (C) 2025 Siriil									    							 *
* Licensed under GPLv3. See LICENSE file for details.			    						 *
*********************************************************************************************/

MEMIDINote {

	*initClass { ^this }

	/****************************************************************************************/

	*getOffsetFromInterval { |interval, validate = true|

		MEDebug.log(thisMethod, 1, [interval]);

		if (validate) {
			MEIntervalValidators.intervalIsValid(interval);
		};

		MECore.intervals.keysValuesDo { |k, v|

			if (v.includes(interval)) {
				^k;
			};
		};
	}

	/****************************************************************************************/

	*getOffsetFromName { |noteName, validate = true|
		var midiOffset, signOffset = 0;

		MEDebug.log(thisMethod, 1, [noteName]);

		if (validate) {
			MENameValidators.noteNameIsValid(noteName, octave: false);
		};

		if (noteName.size > 1) {
			signOffset = MEAccidental.getOffsetFromName(noteName, validate: false);
		};

		midiOffset = MECore.offsetFromLetter(noteName[0], validate: false) + signOffset;

		case
		{ midiOffset < 0 }  { ^midiOffset + 12 }
		{ midiOffset > 11 } { ^midiOffset - 12 };

		^midiOffset;
	}

	/****************************************************************************************/

	*transposeMidiOffset { |midiOffsetArr, midiRoot, validate = true|
		var transpose;

		MEDebug.log(thisMethod, 1, [midiOffsetArr, midiRoot]);

		if (validate) {
			MEMIDIValidators.midiOffsetArrayIsValid(midiOffsetArr, diatonic: false);
			MEMIDIValidators.midiOffsetIsValid(midiRoot, diatonic: false);
		};
		^midiRoot + midiOffsetArr;
	}
}