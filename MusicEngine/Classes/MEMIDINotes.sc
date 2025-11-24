/*********************************************************************************************
* MusicEngine - A dynamic chord library for SuperCollider   								 *
* Copyright (C) 2025 Siriil									    							 *
* Licensed under GPLv3. See LICENSE file for details.			    						 *
*********************************************************************************************/

MEMidiNotes : METools {

	*initClass {}

	/****************************************************************************************/

	*getOffsetFromInterval { |degree|
		var  midiOffset;

		MEDebug.log("MEMidiNotes", "*getOffsetFromInterval");

		if (intervals[degree].notNil) {

			midiOffset = super.intervals[degree][1];
		} {

			intervals.do { |i|

				if (i[0].includes(degree)) {

					midiOffset = i[1];
				};
			};
		};

		^midiOffset;
	}

	/****************************************************************************************/

	*getOffsetFromName { |noteName|
		var accidentalOffset = 0;

		MEDebug.log("MEMidiNotes", "*getOffsetFromName");

		if (noteName.size > 1) {
			accidentalOffset = MEAccidentals.getOffsetFromName(noteName).postln;
		};

		^super.noteFromLetter(noteName[0]) + accidentalOffset;
	}

	/****************************************************************************************/

	*transposeMidiOffset { |midiOffset, midiRoot|

		MEDebug.log("MEMidiNotes", "*transposeMidiOffset");

		^midiOffset + midiRoot;
	}
}