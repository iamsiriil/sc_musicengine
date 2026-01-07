/*********************************************************************************************
* MusicEngine - A dynamic chord library for SuperCollider   								 *
* Copyright (C) 2025 Siriil									    							 *
* Licensed under GPLv3. See LICENSE file for details.			    						 *
*********************************************************************************************/

MEAccidental {
	var <offset;
	var <sign;

	*new { |noteLetter = nil, midiNote = nil, validate = false|

		^super.new.init(noteLetter, midiNote, validate);
	}

	init { |newL, newM, val|

		MEDebug.log(thisMethod, 2);

		offset = MEAccidental.getOffsetFromMidi(newM, newL, val);
		sign   = MEAccidental.getSignFromOffset(offset, val);

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

		ref = MEOctave.getClosestOctave(midiNote, noteLetter, false);

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

		signOffset = this.getOffsetFromMidi(midiNote, noteLetter, false);
		sign       = this.getSignFromOffset(signOffset, validate);

		^noteLetter ++ sign;
	}
}