/*********************************************************************************************
* MusicEngine - A dynamic chord library for SuperCollider   								 *
* Copyright (C) 2025 Siriil									    							 *
* Licensed under GPLv3. See LICENSE file for details.			    						 *
*********************************************************************************************/

MENoteName : MECore {
	var solfege = #["Do", "Re", "Mi", "Fa", "Sol", "La", "Si"];
	var letter;
	var accidental;

	*new { |noteName = nil, noteLetter = nil, midiNote = nil, validate = true|

		^super.new.init(noteName, noteLetter, midiNote, validate);
	}

	init { |newN, newL, newM, val|

		MEDebug.log("MENoteNames", "init");

		case
		{ newN.isNil && newL.notNil && newM.notNil } {
			letter     = newL;
			accidental = MEAccidental(noteLetter: newL, midiNote: newM, validate: val);
		}
		{ newN.notNil && newL.isNil && newM.isNil } {
			letter     = newN[0];
			accidental = MEAccidental(noteName: newN, validate: val);
		}
		{
			Error("Instance must be created with either a complete note name, or a note letter and a midi note.\n").throw;
		};

		^this;
	}

	/****************************************************************************************/

	*getOffsetFromInterval { |interval|
		var nameOffset;

		MEDebug.log("MENoteNames", "*getOffsetFromInterval");

		nameOffset = interval[1..].asInteger;

		if (nameOffset > 7) {
			^(nameOffset - 7) - 1;
		} {
			^nameOffset - 1;
		};
	}

	/****************************************************************************************/

	*getOffsetArray { |intervalArray|
		var nameOffsetArr = Array.new(intervalArray.size + 1);

		MEDebug.log("MENoteNames", "*getOffsetArray");

		nameOffsetArr.add(0);

		intervalArray.do { |i|
			nameOffsetArr.add(MENoteName.getOffsetFromInterval(i));
		};

		^nameOffsetArr;
	}

	/****************************************************************************************/

	*getNoteNames { |nameOffsetArr, rootLetter|
		var index = super.indexOfLetter(rootLetter);
		var noteLettersArr;

		MEDebug.log("MENoteNames", "*getNoteNames");

		noteLettersArr  = super.letters.wrapAt(index + nameOffsetArr);

		noteLettersArr.do { |n, i| noteLettersArr[i] = n.asString };

		^noteLettersArr;
	}

	/****************************************************************************************/

	name {
		^letter ++ accidental.sign;
	}

	/****************************************************************************************/

	letter {
		^letter;
	}

	/****************************************************************************************/

	sign {
		^accidental.sign
	}

	/****************************************************************************************/

	accidentalOffset {
		^accidental.offset;
	}

	/****************************************************************************************/

	solfege {
		var index = MECore.indexOfLetter(letter);

		^solfege[index] ++ accidental.sign;
	}
}