/*********************************************************************************************
* MusicEngine - A dynamic chord library for SuperCollider   								 *
* Copyright (C) 2025 Siriil									    							 *
* Licensed under GPLv3. See LICENSE file for details.			    						 *
*********************************************************************************************/

MENameValidators {

	*initClass {}

	/****************************************************************************************/

	*noteNameIsValid { |noteName|
		var regex = "^[A-G][#b]{0,5}(-1|[0-9]{0,1})$";

		MEDebug.log("MEValidators", "*noteNameIsValid");

		if (regex.matchRegexp(noteName.asString) == false) {
			Error("% is not a valid note name.".format(noteName)).throw;
		};
		^nil;
	}

	/****************************************************************************************/

	*rootNoteIsValid { |rootNote|
		var regex = "^[A-G][#b]{0,1}$";

		MEDebug.log("MEValidators", "*rootNoteIsValid");

		if (regex.matchRegexp(rootNote.asString) == false) {
			Error("% is not a valid root note.".format(rootNote)).throw;
		};
		^nil;
	}
}