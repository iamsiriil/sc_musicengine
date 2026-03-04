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
	/****************************************************************************************/
	// Instance methods

	printOn { |stream|

		if (degree.notNil) {
			stream << this.name << ":" << degree.interval;
		} {
			stream << this.name;
		};
	}

	/****************************************************************************************/

	species { ^this.class }

	/****************************************************************************************/

	hash { ^(this.species.hash.bitXor(this.name.hash).bitXor(this.midi.hash)) }

	/****************************************************************************************/

	copy { ^this.species.new(this.letter, this.midi, this.degree, this.data.copy) }

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

	letter { ^name.letter }

	/****************************************************************************************/

	sign { |charSet = \ascii| ^name.sign(charSet) }

	/****************************************************************************************/

	signOffset { ^name.signOffset }

	/****************************************************************************************/
	/****************************************************************************************/
	// Interval data

	degree_ { |interval| degree = MEInterval(interval) }

	/****************************************************************************************/

	degree { ^if (degree.notNil) { degree.interval } { nil } }

	/****************************************************************************************/

	interval { ^if (degree.notNil) { degree } { nil } }

	/****************************************************************************************/

	number { |asInt = false| ^if (degree.notNil) { degree.number(asInt) } { nil } }

	/****************************************************************************************/

	quality { |asFloat = false| ^if (degree.notNil) { degree.quality(asFloat) } { nil } }

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
			newM   = (this.midi + interval);
		}
		{ interval.isKindOf(Symbol) } {
			meInt = MEInterval(interval);
			newM  = this.midi + meInt.asMIDIOffset;
		};

		if (newM <= 127) {
			newL = MECore.letters.wrapAt(
				MECore.indexOfLetter(this.letter) + meInt.asLetterOffset
			);
			^MENote(newL, newM, this.degree, this.data.copy);
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

		if (newM >= 0) {
			newL = MECore.letters.wrapAt(
				MECore.indexOfLetter(this.letter) + (7 - meInt.asLetterOffset)
			);
			^MENote(newL, newM, this.degree, this.data.copy);
		};
		^nil;
	}

	/****************************************************************************************/

	>> { |interval| ^this.transposeUp(interval) }

	/****************************************************************************************/

	<< { |interval| ^this.transposeDown(interval) }

	/****************************************************************************************/
	/****************************************************************************************/
	// Note comparison

	enharmonicTo { |aMENote|
		^(this.species == aMENote.species) && (this.midi == aMENote.midi)
	}

	/****************************************************************************************/

	notEnharmonicTo { |aMENote|
		^(this.species == aMENote.species) && (this.midi == aMENote.midi).not
	}

	/****************************************************************************************/

	==? { |aMENote| ^this.enharmonicTo(aMENote) }

	/****************************************************************************************/

	!=? { |aMENote| ^this.notEnharmonicTo(aMENote) }


	/****************************************************************************************/

	== { |aMENote|
		^(this.species == aMENote.species) && (
			(this.midi == aMENote.midi)    &&
			(this.name == aMENote.name)
		);
	}

	/****************************************************************************************/

	< { |aMENote| ^(this.species == aMENote.species) && (this.midi < aMENote.midi) }

	/****************************************************************************************/

	> { |aMENote| ^(this.species == aMENote.species) && (this.midi > aMENote.midi) }

	/****************************************************************************************/

	<= { |aMENote| ^(this == aMENote) || (this < aMENote) }

	/****************************************************************************************/

	>= { |aMENote| ^(this == aMENote) || (this > aMENote) }
}

