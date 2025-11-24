/*********************************************************************************************
* MusicEngine - A dynamic chord library for SuperCollider   								 *
* Copyright (C) 2025 Siriil									    							 *
* Licensed under GPLv3. See LICENSE file for details.			    						 *
*********************************************************************************************/

MENoteName : MECore {
	var solfege = #["Do", "Re", "Mi", "Fa", "Sol", "La", "Si"];
	var nameLetter;
	var accidental;

	*new { |noteName = nil, noteLetter = nil, midi = nil|

		^super.new.init(noteName, noteLetter, midi);
	}

	init { |newN, newL, newM|

		MEDebug.log("MENoteNames", "init");

		case
		{ newN.isNil && newL.notNil && newM.notNil } {
			nameLetter = newL;
			accidental = MEAccidental(noteLetter: newL, midi: newM);
		}
		{ newN.notNil && newL.isNil && newM.isNil } {
			nameLetter = newN[0];
			accidental = MEAccidental(noteName: newN);
		}
		{
			Error("Instance must be created with either a complete note name, or a note letter and a midi note.\n").throw;
		};

		^this;
	}

	/****************************************************************************************/

	*getOffsetFromInterval { |interval|
		var offset;

		MEDebug.log("MENoteNames", "*getOffsetFromInterval");

		offset = interval[1..].asInteger;

		if (offset > 7) {
			^(offset - 7) - 1;
		} {
			^offset - 1;
		};
	}

	/****************************************************************************************/

	*getOffsetArray { |intervalArray|
		var offsetArray = Array.new(intervalArray.size + 1);

		MEDebug.log("MENoteNames", "*getOffsetArray");

		offsetArray.add(0);

		intervalArray.do { |i|
			offsetArray.add(MENoteName.getOffsetFromInterval(i));
		};

		^offsetArray;
	}

	/****************************************************************************************/

	*getNoteNames { |intervalArray, rootLetter|
		var index = super.indexOfLetter(rootLetter);
		var namesArray;

		MEDebug.log("MENoteNames", "*getNoteNames");

		namesArray  = super.names.wrapAt(index + intervalArray);

		namesArray.do { |n, i| namesArray[i] = n.asString };

		^namesArray;
	}

	/****************************************************************************************/

	name {
		^nameLetter ++ accidental.sign;
	}

	/****************************************************************************************/

	letter {
		^nameLetter;
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
		var index = MECore.indexOfLetter(nameLetter);

		^solfege[index] ++ accidental.sign;
	}
}