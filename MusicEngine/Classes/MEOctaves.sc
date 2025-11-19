/*********************************************************************************************
* MusicEngine - A dynamic chord library for SuperCollider   								 *
* Copyright (C) 2025 Siriil									    							 *
* Licensed under GPLv3. See LICENSE file for details.			    						 *
*********************************************************************************************/

MEOctaves : METools {

	*initClass {}

	/****************************************************************************************/

	*closestOctave { |midi, noteLetter|
		var ref = super.noteFromLetter(noteLetter);


		if ((midi < -3) || (midi > 127)) {

			Error("Midi value not valid.\n").throw;
		} {

			case
			{ (midi >= 5) && (midi <= 127) } {

				while { ((ref >= (midi - 5)) && (ref <= (midi + 5))).not } {

					ref = ref + 12;
				};
			}
			{ midi <= 5  && noteLetter == "B" } {

				ref = ref - 12;
			}
		}

		^ref;
	}

	/****************************************************************************************/

	*octaveCross { |noteName|
		var mOffset, aOffset;

		mOffset = super.noteFromLetter(noteName[0]);
		aOffset = MEAccidentals.getOffsetFromName(noteName);

		^mOffset + aOffset;
	}

	/****************************************************************************************/

	*getOctave { |midi, noteName = nil|
		var octave = -1;
		var cross;

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