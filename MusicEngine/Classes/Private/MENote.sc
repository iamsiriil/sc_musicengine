/*********************************************************************************************
* MusicEngine - A dynamic chord library for SuperCollider   								 *
* Copyright (C) 2025 Siriil									    							 *
* Licensed under GPLv3. See LICENSE file for details.			    						 *
*********************************************************************************************/

MENote {
	var name;
	var <midi;
	var <freq;
	var <octave;
	var <degree;
	var <>duration;
	var <>articulation;
	var <>dynamic;

	*new { |noteLetter = nil, midiNote = nil, degree = nil, validate = false|

		^super.new.init(noteLetter, midiNote, degree, validate);
	}

	init { |newL, newM, newD, val|

		MEDebug.log(thisMethod, 2);

		midi   = newM;
		freq   = midi.midicps;
		name   = MENoteName(newL, newM, val);
		octave = MEOctave.getOctave(midi, name.name, val);
		degree = newD;

		^this;
	}

	/****************************************************************************************/

	printOn { |stream|
		stream << "MENote [ ";
		stream << "MIDI: " << midi << ", ";
		stream << "Degree: " << degree << ", ";
		stream << "Octave: " << octave << ", ";
		stream << "Name: " << name << " ]";
	}

	/****************************************************************************************/

	nameObj {
		^name;
	}

	/****************************************************************************************/

	accidentalObj {
		^name.accidental;
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

