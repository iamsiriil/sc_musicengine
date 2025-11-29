/*********************************************************************************************
* MusicEngine - A dynamic chord library for SuperCollider   								 *
* Copyright (C) 2025 Siriil									    							 *
* Licensed under GPLv3. See LICENSE file for details.			    						 *
*********************************************************************************************/

MENoteName : MECore {
	var solfege = #["Do", "Re", "Mi", "Fa", "Sol", "La", "Si"];
	var letter; // noteLetter
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
		var letterOffset;

		MEDebug.log("MENoteNames", "*getOffsetFromInterval");

		// intervalIsValid

		letterOffset = interval[1..].asInteger;

		if (letterOffset > 7) {
			^(letterOffset - 7) - 1;
		} {
			^letterOffset - 1;
		};
	}

	/****************************************************************************************/

	*getOffsetArray { |intervalArray|
		var letterOffsetArr = Array.new(intervalArray.size + 1);

		MEDebug.log("MENoteNames", "*getOffsetArray");

		letterOffsetArr.add(0);

		intervalArray.do { |i|
			letterOffsetArr.add(MENoteName.getOffsetFromInterval(i));
		};

		^letterOffsetArr;
	}

	/****************************************************************************************/

	*getNoteNames { |letterOffsetArr, rootLetter, validate = true| // getNoteLetters
		var index, letterArr;

		MEDebug.log("MENoteNames", "*getNoteNames");

		if (validate) {
			MEValidators.letterOffsetArrayIsValid(letterOffsetArr);
		};

		index     = super.indexOfLetter(rootLetter, validate: false);
		letterArr = super.letters.wrapAt(index + letterOffsetArr);

		letterArr.do { |n, i| letterArr[i] = n.asString };

		^letterArr;
	}

	/****************************************************************************************/

	name {
		^letter ++ accidental.sign; // signOffset
	}

	/****************************************************************************************/

	letter {
		^letter; // ^noteLetter
	}

	/****************************************************************************************/

	sign {
		^accidental.sign // signOffset
	}

	/****************************************************************************************/

	accidentalOffset {
		^accidental.offset; // signOffset
	}

	/****************************************************************************************/

	solfege {
		var index = MECore.indexOfLetter(letter); // noteLetter; validate: false

		^solfege[index] ++ accidental.sign; // signOffset
	}
}