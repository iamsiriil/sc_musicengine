/*********************************************************************************************
* MusicEngine - A dynamic chord library for SuperCollider   								 *
* Copyright (C) 2025 Siriil									    							 *
* Licensed under GPLv3. See LICENSE file for details.			    						 *
*********************************************************************************************/

MEAccidentalValidators {

	*initClass { ^this }

	/****************************************************************************************/

	*signOffsetIsValid { |signOffset|
		var offsets = (-5..5).asSet;

		//MEDebug.log("MEAccidentalValidators", "signOffsetIsValid");

		if (signOffset.isInteger.not) {
			Error("Sign offset must be of type Integer.").throw;
		};
		if (offsets.includes(signOffset).not) {
			Error("% is not a valid sign offset.".format(signOffset)).throw;
		};
		^nil;
	}
}