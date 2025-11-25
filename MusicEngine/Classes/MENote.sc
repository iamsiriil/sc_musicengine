/*********************************************************************************************
* MusicEngine - A dynamic chord library for SuperCollider   								 *
* Copyright (C) 2025 Siriil									    							 *
* Licensed under GPLv3. See LICENSE file for details.			    						 *
*********************************************************************************************/

MENote : MECore {
	var name;
	var <midi;
	var <freq;
	var <octave;
	var <degree;
	var <>duration;
	var <>articulation;
	var <>dynamic;

	*new { |noteName = nil, letter = nil, midi = nil, degree = nil, octave = nil|

		^super.new.init(noteName, letter, midi, degree, octave);
	}

	init { |newN, newL, newM, newD, newO|

		MEDebug.log("MENotes", "init");

		case
		{ newN.isNil && newL.notNil && newM.notNil } {
			midi   = newM;
			freq   = midi.midicps;
			name   = MENoteName(letter: newL, midi: newM);
			octave = MEOctaves.getOctave(midi, name.name);
		}
		{ newN.notNil && newL.isNil && newM.isNil } {
			midi   = MEMIDINotes.getOffsetFromName(newN) + (12 * 5); // Octave number 4
			freq   = midi.midicps;
			name   = MENoteName(noteName: newN);
			octave = MEOctaves.getOctave(midi, name.name);
		}
		{
			Error("Instance must be created with either a complete note name, or a note letter and a midi note.\n").throw;
		};

		degree = newD;

		^this;
	}

	/****************************************************************************************/

	copy {
		^this.deepCopy;
	}

	/****************************************************************************************/

	name { |withOctave = true|

		if (withOctave) {
			^name.name ++ octave;
		};
		^name.name;
	}

	/****************************************************************************************/

	sol { |withOctave = true|

		if (withOctave) {
			^name.solfege ++ octave;
		};
		^name.solfege;
	}

	/****************************************************************************************/

	letter {
		^name.letter;
	}

	/****************************************************************************************/

	accidental {
		^name.sign;
	}

	/****************************************************************************************/

	accidentalOffset {
		^name.accidentalOffset;
	}
}

