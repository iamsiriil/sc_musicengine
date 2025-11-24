/*********************************************************************************************
* MusicEngine - A dynamic chord library for SuperCollider   								 *
* Copyright (C) 2025 Siriil									    							 *
* Licensed under GPLv3. See LICENSE file for details.			    						 *
*********************************************************************************************/

MEAccidentals : METools {
	var <sign;
	var <offset;

	*new { |noteName = nil, noteLetter = nil, midi = nil|

		^super.new.init(noteName, noteLetter, midi);
	}

	init { |newN, newL, newM|

		MEDebug.log("MEAccidentals", "init");

		case
		{ newN.isNil && newL.notNil && newM.notNil } {
			offset = MEAccidentals.getOffsetFromMidi(newM, newL);
			sign   = MEAccidentals.getSignFromOffset(offset);
		}
		{ newN.notNil && newL.isNil && newM.isNil } {
			offset = MEAccidentals.getOffsetFromName(newN);
			sign   = MEAccidentals.getSignFromOffset(offset);
		}
		{
			Error("Instance must be created with either a complete note name, or a note letter and a midi note.\n").throw;
		};

		^this;
	}

	/****************************************************************************************/

	*getOffsetFromName { |noteName|
		var accidental = noteName[1..];

		MEDebug.log("MEAccidentals", "*getOffsetFromName");

		if (accidental.includes($b)) {
			^accidental.size * -1;
		};

		^accidental.size;
	}

	/****************************************************************************************/

	*getOffsetFromMidi { |midi, noteLetter|
		var ref;

		MEDebug.log("MEAccidentals", "*getOffsetFromMidi");

		ref = MEOctaves.closestOctave(midi, noteLetter);

		^midi - ref;
	}

	/****************************************************************************************/

	*getSignFromOffset { |offset|
		var symbol = "";

		MEDebug.log("MEAccidentals", "*getSignFromOffset");

		case
		{ offset < 0 } { offset.abs.do { symbol = symbol ++ "b"} }
		{ offset > 0 } { offset.do { symbol = symbol ++ "#" } };

		^symbol;
	}

	/****************************************************************************************/

	*resolveAccidental { |midi, noteLetter|
		var offset, symbol;

		MEDebug.log("MEAccidentals", "*resolveAccidental");

		offset = MEAccidentals.getOffsetFromMidi(midi, noteLetter);
		symbol = MEAccidentals.getSignFromOffset(offset);

		^noteLetter ++ symbol;
	}
}