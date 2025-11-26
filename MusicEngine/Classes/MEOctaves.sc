/*********************************************************************************************
* MusicEngine - A dynamic chord library for SuperCollider   								 *
* Copyright (C) 2025 Siriil									    							 *
* Licensed under GPLv3. See LICENSE file for details.			    						 *
*********************************************************************************************/

MEOctaves : MECore {

	*initClass {}

	/****************************************************************************************/

	*getClosestOctave { |midi, letter|
		var ref = super.noteFromLetter(letter); // Already checks is name letter is valid

		MEDebug.log("MEOctaves", "*getClosestOctave");

		// MIDI note is valid. Test to be abstracted in dedicated validators class
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

	*octaveCross { |noteName|
		var mOffset, aOffset;

		MEDebug.log("MEOctaves", "*octaveCross");

		mOffset = super.noteFromLetter(noteName[0]);
		aOffset = MEAccidental.getOffsetFromName(noteName);

		^mOffset + aOffset;
	}

	/****************************************************************************************/

	*getOctave { |midi, noteName = nil|
		var octave = -1;
		var cross;

		MEDebug.log("MEOctaves", "*getOctave");

		octave = (octave + (midi/12).floor).asInteger;

		if (noteName.notNil) {

			cross = MEOctaves.octaveCross(noteName);

			case
			{ cross < 0 }  { ^octave + 1 }
			{ cross > 11 } { ^octave - 1 }
		};

		^octave
	}
}