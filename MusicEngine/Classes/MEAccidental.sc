/*********************************************************************************************
* MusicEngine - A dynamic chord library for SuperCollider   								 *
* Copyright (C) 2025 Siriil									    							 *
* Licensed under GPLv3. See LICENSE file for details.			    						 *
*********************************************************************************************/

MEAccidental {
	var <offset;
	var <sign;

	*new { |noteName = nil, noteLetter = nil, midiNote = nil, validate = true|

		^super.new.init(noteName, noteLetter, midiNote, validate);
	}

	init { |newN, newL, newM, val|

		MEDebug.log(thisMethod, 2);

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

	printOn { |stream|
		var s = if (sign == "") { nil } { sign };

		stream << "MEAccidental [ ";
		stream << "Offset: " << offset << ", ";
		stream << "Sign: " << s << " ]";
	}

	/****************************************************************************************/

	*getOffsetFromName { |noteName, validate = true|
		var signOffset;

		MEDebug.log(thisMethod, 2);

		if (validate) {
			MENameValidators.noteNameIsValid(noteName);
		};

		signOffset = noteName[1..];

		if (signOffset.includes($b)) {
			^signOffset.size * -1;
		};
		^signOffset.size;
	}

	/****************************************************************************************/

	*getOffsetFromMidi { |midiNote, noteLetter, validate = true|
		var ref;

		MEDebug.log(thisMethod, 2);

		if (validate) {
			MEMIDIValidators.midiNoteIsValid(midiNote);
			MELetterValidators.noteLetterIsValid(noteLetter);
		};

		ref = MEOctave.getClosestOctave(midiNote, noteLetter, validate: false);

		if (ref.notNil) { ^midiNote - ref } { ^nil };
	}

	/****************************************************************************************/

	*getSignFromOffset { |signOffset, validate = true|
		var sign = "";

		MEDebug.log(thisMethod, 2);

		if (validate) {
			MEAccidentalValidators.signOffsetIsValid(signOffset);
		};

		case
		{ signOffset < 0 } { signOffset.abs.do { sign = sign ++ "b"} }
		{ signOffset > 0 } { signOffset.do { sign = sign ++ "#" } };

		^sign;
	}

	/****************************************************************************************/

	*resolveAccidental { |midiNote, noteLetter, validate = true|
		var signOffset, sign;

		MEDebug.log(thisMethod, 2);

		if (validate) {
			MEMIDIValidators.midiNoteIsValid(midiNote);
			MELetterValidators.noteLetterIsValid(noteLetter);
		};

		signOffset = this.getOffsetFromMidi(midiNote, noteLetter, validate: false);
		sign       = this.getSignFromOffset(signOffset, validate: false);

		^noteLetter ++ sign;
	}
}