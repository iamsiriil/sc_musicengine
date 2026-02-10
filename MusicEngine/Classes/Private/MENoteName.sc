/*********************************************************************************************
* MusicEngine - A dynamic music library for SuperCollider   								 *
* Copyright (C) 2025 Siriil									    							 *
* Licensed under GPLv3. See LICENSE file for details.			    						 *
*********************************************************************************************/

MENoteName {
	classvar <solfege = #["Do", "Re", "Mi", "Fa", "Sol", "La", "Si"];
	var <letter;
	var <accidental;

	*new { |noteLetter, midiNote, validate = false|
		^super.new.init(noteLetter, midiNote, validate);
	}

	init { |newL, newM, val|

		MEDebug.log(thisMethod, 2);

		letter     = newL;
		accidental = MEAccidental(newL, newM, val);

		^this;
	}

	/****************************************************************************************/

	printOn { |stream|
		var s = if (accidental.sign == "") { nil } { accidental.sign };
		stream << "MENoteName [ ";
		stream << "Letter: " << letter << ", ";
		stream << "Accidental: "<< accidental << " ]";
	}

	/****************************************************************************************/

	/**getOffsetFromInterval { |meinterval, validate = true|
		var letterOffset;

		MEDebug.log(thisMethod, 1, [meinterval.interval]);

		if (validate) {
			MEIntervalValidators.intervalIsValid(meinterval.interval);
		};

		letterOffset = meinterval.number(true);

		if (letterOffset > 7) {
			^letterOffset - 8;
		} {
			^letterOffset - 1;
		};
	}*/

	/****************************************************************************************/

	*getNoteLetters { |letterOffsetArr, rootLetter, validate = true|
		var index, letterArr;

		MEDebug.log(thisMethod, 1, [letterOffsetArr]);

		if (validate) {
			MELetterValidators.letterOffsetArrayIsValid(letterOffsetArr);
			MELetterValidators.noteLetterIsValid(rootLetter);
		};

		index     = MECore.indexOfLetter(rootLetter, false);
		letterArr = MECore.letters.wrapAt(index + letterOffsetArr);

		letterArr.do { |n, i| letterArr[i] = n.asString };

		^letterArr;
	}

	/****************************************************************************************/

	name { |charSet = \ascii| ^letter ++ accidental.sign(charSet) }

	/****************************************************************************************/

	sign { |charSet = \ascii| ^accidental.sign(charSet) }

	/****************************************************************************************/

	signOffset { ^accidental.offset }

	/****************************************************************************************/

	solfege { |charSet = \ascii|
		var index = MECore.indexOfLetter(letter, false);

		^solfege[index] ++ accidental.sign(charSet);
	}
}