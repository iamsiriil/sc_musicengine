/*********************************************************************************************
* MusicEngine - A dynamic chord library for SuperCollider   								 *
* Copyright (C) 2025 Siriil									    							 *
* Licensed under GPLv3. See LICENSE file for details.			    						 *
*********************************************************************************************/

MEValidators {

	*initClass {}

	*noteLetterIsValid { |noteLetter|
		var regex = "^[A-G]$";

		if (regex.matchRegexp(noteLetter.asString) == false) {
			Error("% is not a valid note letter.".format(noteLetter)).throw;
		};
	}

	*noteNameIsValid { |noteName|
		var regex = "^[A-G][#b]{0,12}-{0,1}[0-9]{0,1}$";

		if (regex.matchRegexp(noteName) == false) {
			Error("% is not a valid note name.".format(noteName)).throw;
		};
	}

	*rootNoteIsValid { |rootNote|
		var regex = "^[A-G][#b]{0,1}$";

		if (regex.matchRegexp(rootNote) == false) {
			Error("% is not a valid root note.".format(rootNote)).throw;
		};
	}

	*midiNoteIsValid { |midiNote, min = 0, max = 127|

		if ((midiNote < min) || (midiNote > max) || midiNote.isInteger.not) {
			Error("% is not a valid MIDI note.".format(midiNote)).throw;
		};
	}

	*octaveIsValid { |octave, min = -1, max = 9|

		if ((octave < min) || (octave > max) || (octave.isInteger.not)) {
			Error("% is not a valid octave number.".format(octave)).throw;
		};
	}

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
	}
}