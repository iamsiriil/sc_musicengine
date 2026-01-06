/*********************************************************************************************
* MusicEngine - A dynamic chord library for SuperCollider   								 *
* Copyright (C) 2025 Siriil									    							 *
* Licensed under GPLv3. See LICENSE file for details.			    						 *
*********************************************************************************************/

MEOctaveValidators {

	*initClass { ^this }

	/****************************************************************************************/

	*octaveIsValid { |octave, start = -1|

		MEDebug.log(thisMethod, 3);

		if (octave.isInteger.not) {
			Error("Octave must be of type Integer.").throw;
		};
		if ((octave < start) || (octave > (start + 10))) {
			Error("% is not a valid octave number.".format(octave)).throw;
		};
		^nil;
	}
}