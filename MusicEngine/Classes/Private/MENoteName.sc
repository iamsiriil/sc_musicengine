/*********************************************************************************************
* MusicEngine - A dynamic chord library for SuperCollider   								 *
* Copyright (C) 2025 Siriil									    							 *
* Licensed under GPLv3. See LICENSE file for details.			    						 *
*********************************************************************************************/

MENoteName {
	var solfege = #["Do", "Re", "Mi", "Fa", "Sol", "La", "Si"];
	var noteLetter;
	var <accidental;

	*new { |noteLetter = nil, midiNote = nil, validate = false|

		^super.new.init(noteLetter, midiNote, validate);
	}

	init { |newL, newM, val|

		MEDebug.log(thisMethod, 2);

		noteLetter = newL;
		accidental = MEAccidental(newL, newM, val);

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

		MEDebug.log(thisMethod, 1, [interval]);

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

		MEDebug.log(thisMethod, 2);

		letterOffsetArr.add(0);

		intervalArray.do { |i|
			letterOffsetArr.add(this.getOffsetFromInterval(i, validate));
		};

		^letterOffsetArr;
	}

	/****************************************************************************************/

	*getNoteLetters { |letterOffsetArr, rootLetter, validate = true|
		var index, letterArr;

		MEDebug.log(thisMethod, 1, [letterOffsetArr]);

		if (validate) {
			MELetterValidators.letterOffsetArrayIsValid(letterOffsetArr);
			MELetterValidators.noteLetterIsValid(rootLetter);
		};

		index     = MECore.indexOfLetter(rootLetter, validate: false);
		letterArr = MECore.letters.wrapAt(index + letterOffsetArr);

		letterArr.do { |n, i| letterArr[i] = n.asString };

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