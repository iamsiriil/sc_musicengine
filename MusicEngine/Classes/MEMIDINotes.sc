/*********************************************************************************************
* MusicEngine - A dynamic chord library for SuperCollider   								 *
* Copyright (C) 2025 Siriil									    							 *
* Licensed under GPLv3. See LICENSE file for details.			    						 *
*********************************************************************************************/

MEMIDINotes : MECore {

	*initClass {}

	/****************************************************************************************/

	*getOffsetFromInterval { |interval, validate = true|

		//MEDebug.log("MEMidiNotes", "*getOffsetFromInterval");

		if (validate) {
			MEIntervalValidators.intervalIsValid(interval);
		};

		super.intervals.keysValuesDo { |k, v|

			if (v.includes(interval)) {
				^k;
			};
		};
	}

	/****************************************************************************************/

	*getOffsetFromName { |noteName, validate = true|
		var midiOffset, signOffset = 0;

		if (validate) {
			MENameValidators.noteNameIsValid(noteName, octave: false);
		};

		if (noteName.size > 1) {
			signOffset = MEAccidental.getOffsetFromName(noteName, validate: false);
		};

		midiOffset = super.noteFromLetter(noteName[0], validate: false) + signOffset;

		/*if (noteName.asString == "Cb") {
			^midiOffset + 12;
		};*/

		case
		{ midiOffset < 0 }  { ^midiOffset + 12 }
		{ midiOffset > 11 } { ^midiOffset - 12 }
		{ ^midiOffset };

		MEDebug.log("MEMidiNotes", "*getOffsetFromName", "\nin:  %\nout: %\n".format(noteName, midiOffset));

		//^midiOffset;
	}

	/****************************************************************************************/

	*transposeMidiOffset { |midiOffsetArr, midiRoot, validate = true|
		var transpose;

		if (validate) {
			MEMIDIValidators.midiOffsetArrayIsValid(midiOffsetArr, diatonic: false);
			MEMIDIValidators.midiOffsetIsValid(midiRoot, diatonic: false);
		};

		transpose = midiRoot + midiOffsetArr;

		MEDebug.log("MEMidiNotes", "*transposeMidiOffset" "\nin:  %, %\nout: %\n".format(midiRoot, midiOffsetArr, transpose));

		^transpose;
	}
}