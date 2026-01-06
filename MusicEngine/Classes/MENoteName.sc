/*********************************************************************************************
* MusicEngine - A dynamic chord library for SuperCollider   								 *
* Copyright (C) 2025 Siriil									    							 *
* Licensed under GPLv3. See LICENSE file for details.			    						 *
*********************************************************************************************/

MENoteName : MECore {
	var solfege = #["Do", "Re", "Mi", "Fa", "Sol", "La", "Si"];
	var noteLetter;
	var <accidental;

	*new { |noteName = nil, noteLetter = nil, midiNote = nil, validate = true|

		^super.new.init(noteName, noteLetter, midiNote, validate);
	}

	init { |newN, newL, newM, val|

		//MEDebug.log("MENoteNames", "init");

		case
		{ newN.isNil && newL.notNil && newM.notNil } {
			noteLetter = newL;
			accidental = MEAccidental(noteLetter: newL, midiNote: newM, validate: val);
		}
		{ newN.notNil && newL.isNil && newM.isNil } {
			noteLetter = newN[0];
			accidental = MEAccidental(noteName: newN, validate: val);
		}
		{
			Error("Instance must be created with either a complete note name, or a note letter and a midi note.\n").throw;
		};

		^this;
	}

	/****************************************************************************************/

	printOn { |stream|
		var s = if (accidental.sign == "") { nil } { accidental.sign };
		stream << "MENoteName [ ";
		stream << "Letter: " << noteLetter << ", ";
		stream << "Accidental: "<< accidental << " ]";
	}

	/****************************************************************************************/

	*getOffsetFromInterval { |interval, validate = true|
		var letterOffset;

		//MEDebug.log("MENoteNames", "*getOffsetFromInterval");

		if (validate) {
			MEIntervalValidators.intervalIsValid(interval);
		};

		letterOffset = interval[1..].asInteger;

		if (letterOffset > 7) {
			^(letterOffset - 7) - 1;
		} {
			^letterOffset - 1;
		};
	}

	/****************************************************************************************/

	*getOffsetArray { |intervalArray, validate = true|
		var letterOffsetArr = Array.new(intervalArray.size + 1);

		//MEDebug.log("MENoteNames", "*getOffsetArray");

		letterOffsetArr.add(0);

		intervalArray.do { |i|
			letterOffsetArr.add(this.getOffsetFromInterval(i, validate));
		};

		^letterOffsetArr;
	}

	/****************************************************************************************/

	*getNoteLetters { |letterOffsetArr, rootLetter, validate = true|
		var index, letterArr;


		if (validate) {
			MELetterValidators.letterOffsetArrayIsValid(letterOffsetArr);
			MELetterValidators.noteLetterIsValid(rootLetter);
		};

		index     = MECore.indexOfLetter(rootLetter, validate: false);
		letterArr = MECore.letters.wrapAt(index + letterOffsetArr);

		letterArr.do { |n, i| letterArr[i] = n.asString };

		MEDebug.log("MENoteNames", "*getNoteNames", "\nin:  %, %\nout: %\n".format(rootLetter, letterOffsetArr, letterArr));

		^letterArr;
	}

	/****************************************************************************************/

	name {
		^noteLetter ++ accidental.sign;
	}

	/****************************************************************************************/

	letter {
		^noteLetter;
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
		var index = MECore.indexOfLetter(noteLetter, validate: false);

		^solfege[index] ++ accidental.sign;
	}
}