/*********************************************************************************************
* MusicEngine - A dynamic chord library for SuperCollider   								 *
* Copyright (C) 2025 Siriil									    							 *
* Licensed under GPLv3. See LICENSE file for details.			    						 *
*********************************************************************************************/

MEOctaveValidators {

	*initClass {}

	/****************************************************************************************/

	*octaveIsValid { |octave, start = -1|

		MEDebug.log("MEValidators", "*octaveIsValid");

		if ((octave < start) || (octave > (start + 10)) || (octave.isInteger.not)) {
			Error("% is not a valid octave number.".format(octave)).throw;
		};
		^nil;
	}
}