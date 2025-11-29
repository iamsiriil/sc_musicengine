/*********************************************************************************************
* MusicEngine - A dynamic chord library for SuperCollider   								 *
* Copyright (C) 2025 Siriil									    							 *
* Licensed under GPLv3. See LICENSE file for details.			    						 *
*********************************************************************************************/

MEAccidental : MECore {
	var <offset;
	var <sign;

	*new { |noteName = nil, noteLetter = nil, midiNote = nil, validate = true|

		^super.new.init(noteName, noteLetter, midiNote, validate);
	}

	init { |newN, newL, newM, val|

		MEDebug.log("MEAccidentals", "init");

		case
		{ newN.isNil && newL.notNil && newM.notNil } {
			offset = MEAccidental.getOffsetFromMidi(newM, newL, val);
			sign   = MEAccidental.getSignFromOffset(offset, val);
		}
		{ newN.notNil && newL.isNil && newM.isNil } {
			offset = MEAccidental.getOffsetFromName(newN, val);
			sign   = MEAccidental.getSignFromOffset(offset, val);
		}
		{
			Error("Instance must be created with either a complete note name, or a note letter and a midi note.\n").throw;
		};

		^this;
	}

	/****************************************************************************************/

	*getOffsetFromName { |noteName, validate = true|
		var signOffset = noteName[1..];

		MEDebug.log("MEAccidentals", "*getOffsetFromName");

		if (validate) {
			MEValidators.noteNameIsValid(noteName);
		};

		if (signOffset.includes($b)) {
			^signOffset.size * -1;
		};
		^signOffset.size;
	}

	/****************************************************************************************/

	*getOffsetFromMidi { |midiNote, noteLetter, validate = true|
		var ref;

		MEDebug.log("MEAccidentals", "*getOffsetFromMidi");

		if (validate) {
			MEValidators.midiNoteIsValid(midiNote);
			MEValidators.noteLetterIsValid(noteLetter);
		};

		ref = MEOctaves.getClosestOctave(midiNote, noteLetter, validate: false); // max 5

		if (ref.notNil) { ^midiNote - ref } { ^nil };
	}

	/****************************************************************************************/

	*getSignFromOffset { |signOffset, validate = true|
		var sign = "";

		MEDebug.log("MEAccidentals", "*getSignFromOffset");

		if (validate) {
			MEValidators.signOffsetIsValid(signOffset);
		};

		case
		{ signOffset < 0 } { signOffset.abs.do { sign = sign ++ "b"} }
		{ signOffset > 0 } { signOffset.do { sign = sign ++ "#" } };

		^sign;
	}

	/****************************************************************************************/

	*resolveAccidental { |midiNote, noteLetter, validate = true|
		var signOffset, sign;

		MEDebug.log("MEAccidentals", "*resolveAccidental");

		if (validate) {
			MEValidators.midiNoteIsValid(midiNote);
			MEValidators.noteLetterIsValid(noteLetter);
		};

		signOffset = MEAccidental.getOffsetFromMidi(midiNote, noteLetter, validate: false);
		sign       = MEAccidental.getSignFromOffset(signOffset, validate: false);

		^noteLetter ++ sign;
	}
}