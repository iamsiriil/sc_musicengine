/*********************************************************************************************
* MusicEngine - A dynamic music library for SuperCollider   								 *
* Copyright (C) 2025 Siriil									    							 *
* Licensed under GPLv3. See LICENSE file for details.			    						 *
*********************************************************************************************/

MENote {
	var <midi;
	var <freq;
	var <data;
	var <octave;
	var degree;
	var name;

	*new { |noteLetter, midiNote, interval, data, validate = false|
		^super.new.init(noteLetter, midiNote, interval, data, validate);
	}

	init { |newL, newM, newI, newD, val|

		MEDebug.log(thisMethod, 2);

		midi   = newM;
		freq   = newM.midicps;
		name   = MENoteName(newL, newM, val);
		octave = MEOctave.getOctave(midi, name.name, val);

		degree = if (newI.notNil) { MEInterval(newI) } { nil };
		data   = if (newD.notNil) { newD } { Dictionary() };

		^this;
	}

	*newFromName { |noteName, interval, data|
		var noteLetter = noteName[0].asString;
		var midiNote   = MEMIDINote.getMIDINoteFromName(noteName);

		^this.new(noteLetter, midiNote, interval, data);
	}

	/****************************************************************************************/

	printOn { |stream|

		if (degree.notNil) {
			stream << this.name << ":" << degree.interval;
		} {
			stream << this.name;
		}
	}

	/****************************************************************************************/

	copy { ^this.deepCopy }

	/****************************************************************************************/
	/****************************************************************************************/
	// Note name data

	name { |charSet = \ascii, withOctave = true|

		if (withOctave) {
			^name.name(charSet) ++ octave;
		};
		^name.name(charSet);
	}

	/****************************************************************************************/

	sol { |charSet = \ascii, withOctave = true|

		if (withOctave) {
			^name.solfege(charSet) ++ octave;
		};
		^name.solfege(charSet);
	}

	/****************************************************************************************/

	m21 { ^name.name(\m21) ++ octave }

	/****************************************************************************************/

	letter { ^name.letter }

	/****************************************************************************************/

	nameObj { ^name }

	/****************************************************************************************/
	/****************************************************************************************/
	// Accidental data

	sign { |charSet = \ascii| ^name.sign(charSet) }

	/****************************************************************************************/

	signOffset { ^name.signOffset }

	/****************************************************************************************/

	accidentalObj { ^name.accidental }

	/****************************************************************************************/
	/****************************************************************************************/
	// Interval data

	degree { |meInterval = false|

		if (meInterval) {
			^degree;
		};
		^degree.interval;
	}

	/****************************************************************************************/

	number { |asInt = false| ^degree.number(asInt) }

	/****************************************************************************************/

	quality { |asFloat = false| ^degree.quality(asFloat) }

	/****************************************************************************************/
	/****************************************************************************************/
	// Data dict

	set { |key, value| data[key] = value }

	/****************************************************************************************/

	get { |key| ^data[key] }

	/****************************************************************************************/

	clearValue { |key| data[key] = nil }

	/****************************************************************************************/

	clearDict { data.clear }

	/****************************************************************************************/

	getKeys { ^data.keys }

	/****************************************************************************************/

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

	/****************************************************************************************/
	/****************************************************************************************/
	// Note transposition

	transposeUp { |interval|
		var meInt, newL, newM, symbol;

		case
		{ interval.isInteger } {
			symbol = MECore.default[interval % 12];
			meInt  = MEInterval(symbol);
			newM   = this.midi + interval;
		}
		{ interval.isKindOf(Symbol) } {
			meInt = MEInterval(interval);
			newM  = this.midi + meInt.asMIDIOffset;
		};
		newL = MECore.letters.wrapAt(
			MECore.indexOfLetter(this.letter) + meInt.asLetterOffset
		);

		if (newM <= 127) {
			^MENote(newL, newM, this.degree, this.data);
		};
		^nil;
	}

	/****************************************************************************************/

	transposeDown { |interval|
		var meInt, newL, newM, symbol;

		case
		{ interval.isInteger } {
			symbol = MECore.default[interval % 12];
			meInt  = MEInterval(symbol);
			newM   = this.midi - interval;
		}
		{ interval.isKindOf(Symbol) } {
			meInt = MEInterval(interval);
			newM  = this.midi - meInt.asMIDIOffset;
		};
		newL = MECore.letters.wrapAt(
			MECore.indexOfLetter(this.letter) + (7 - meInt.asLetterOffset)
		);

		if (newM >= 0) {
			^MENote(newL, newM, this.degree, this.data);
		};
		^nil;
	}

	/****************************************************************************************/

	+ { |interval| ^this.transposeUp(interval) }

	/****************************************************************************************/

	- { |interval| ^this.transposeDown(interval) }
}

