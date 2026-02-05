/*********************************************************************************************
* MusicEngine - A dynamic music library for SuperCollider   								 *
* Copyright (C) 2025 Siriil									    							 *
* Licensed under GPLv3. See LICENSE file for details.			    						 *
*********************************************************************************************/

MENote {
	var name;
	var <midi;
	var <freq;
	var <octave;
	var degree; // <-- '<'
	var data;

	*new { |noteLetter = nil, midiNote = nil, degree = nil, validate = false|

		^super.new.init(noteLetter, midiNote, degree, validate);
	}

	init { |newL, newM, newD, val|

		MEDebug.log(thisMethod, 2);

		midi   = newM;
		freq   = midi.midicps;
		name   = MENoteName(newL, newM, val);
		octave = MEOctave.getOctave(midi, name.name, val);
		degree = MEInterval(newD);
		data   = Dictionary();

		^this;
	}

	/****************************************************************************************/

	printOn { |stream|
		stream << this.name << ":" << degree.interval;
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

	/****************************************************************************************/
	/****************************************************************************************/
	// MEInterval

	degree {
		^degree.interval;
	}

	number {
		^degree.number
	}

	offset {
		^degree.offset
	}

	/****************************************************************************************/
	/****************************************************************************************/
	// DATA DICT

	set { |key, value|
		data[key] = value;
	}

	get { |key|
		^data[key];
	}

	clearValue { |key|
		data[key] = nil;
	}

	clearData {
		data.clear;
	}

	getKeys {
		^data.keys;
	}

	printData {
		var maxKeySize = data
		.keys
		.maxItem { |i| i.asString.size }
		.asString
		.size;

		data.keysValuesDo { |k, v|
			"'%' ".format(k).padRight(maxKeySize + 3).post;
			"-> %".format(v).postln;
		};
	}
}

