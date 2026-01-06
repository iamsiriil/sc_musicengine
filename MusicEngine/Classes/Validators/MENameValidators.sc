/*********************************************************************************************
* MusicEngine - A dynamic chord library for SuperCollider   								 *
* Copyright (C) 2025 Siriil									    							 *
* Licensed under GPLv3. See LICENSE file for details.			    						 *
*********************************************************************************************/

MENameValidators {

	*initClass { ^this }

	/****************************************************************************************/

	*noteNameIsValid { |noteName, octave = false|
		var regex;

		//MEDebug.log("MENameValidators", "noteNameIsValid");

		regex = if (octave) { "^[A-G][#b]{0,5}(-1|[0-9]{0,1})$" } { "^[A-G][#b]{0,5}$" };

		if (noteName.isString.not) {
			Error("Note name must be of type String.").throw;
		};
		if (regex.matchRegexp(noteName.asString) == false) {
			Error("% is not a valid note name.".format(noteName)).throw;
		};
		^nil;
	}

	/****************************************************************************************/

	*rootNoteIsValid { |rootNote|
		var regex = "^[A-G][#b]{0,1}$";

		//MEDebug.log("MENameValidators", "rootNoteIsValid");

		if (rootNote.isString.not) {
			Error("Note name must be of type String.").throw;
		};
		if (regex.matchRegexp(rootNote) == false) {
			Error("% is not a valid root note.".format(rootNote)).throw;
		};
		^nil;
	}
}