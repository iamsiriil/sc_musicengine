/*********************************************************************************************
* MusicEngine - A dynamic chord library for SuperCollider   								 *
* Copyright (C) 2025 Siriil									    							 *
* Licensed under GPLv3. See LICENSE file for details.			    						 *
*********************************************************************************************/

MEOctaves : MECore {

	*initClass {}

	/****************************************************************************************/

	*getClosestOctave { |midi, letter|
		var ref = super.noteFromLetter(letter); // Already checks if note letter is valid

		MEDebug.log("MEOctaves", "*getClosestOctave");

		// Check if MIDI note is valid. Test to be abstracted in dedicated validators class
		if ((midi < -1) || (midi > 127) || midi.isInteger.not) {

			Error("Midi value not valid.\n").throw;
		};

		case
		{ (midi >= 5) && (midi <= 127) } {

			while { ((ref >= (midi - 5)) && (ref <= (midi + 5))).not && (ref <= 127) } {

				ref = (ref + 12)
			};
		}
		{ (midi < 5)  && (letter == "B") } {

			ref = ref - 12;
		};

		if (((ref - midi).abs >= 6) || (ref > 129)) { ^nil } { ^ref };
	}

	/****************************************************************************************/

	// Method to be included in validators class
	*midiNamePairIsValid { |midiNote, noteName|
		var sign  = MEAccidental.getOffsetFromName(noteName);
		var cross = MEOctaves.checkOctaveCross(noteName);
		var ref   = MECore.noteFromLetter(noteName[0]);
		var oct   = (midiNote / 12).floor;
		var note  = (midiNote - (12 * oct));

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
		^true;
	}

	/****************************************************************************************/

	*checkOctaveCross { |noteName|
		var midiOffset = super.noteFromLetter(noteName[0]);
		var signOffset = MEAccidental.getOffsetFromName(noteName);
		var cross      = midiOffset + signOffset;

		MEDebug.log("MEOctaves", "*checkOctaveCross");

		case
		{ cross < 0 }  { ^-1 }
		{ cross > 11 } { ^1 }
		{ ^0 }
	}

	/****************************************************************************************/

	*getOctave { |midiNote, noteName = nil|
		var octave = -1;
		var cross;

		MEDebug.log("MEOctaves", "*getOctave");

		if (MEOctaves.midiNamePairIsValid(midiNote, noteName)) {

			octave = (octave + (midiNote/12).floor).asInteger;

			if (noteName.notNil) {

				cross = MEOctaves.checkOctaveCross(noteName);
				^octave + (cross * -1);
			};
			^octave
		};
	}
}