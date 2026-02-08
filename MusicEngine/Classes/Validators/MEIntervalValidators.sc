/*********************************************************************************************
* MusicEngine - A dynamic music library for SuperCollider   								 *
* Copyright (C) 2025 Siriil									    							 *
* Licensed under GPLv3. See LICENSE file for details.			    						 *
*********************************************************************************************/

MEIntervalValidators {

	*initClass { ^this }

	/****************************************************************************************/

	*intervalIsValid { |interval|
		var result = false;

		MEDebug.log(thisMethod, 3);

		if (interval.isKindOf(Symbol).not) {
			Error("Interval must be of type Symbol.").throw;
		};

		MECore.intervals.do { |v|
			result = result || v.includes(interval);
		};

		if (result == false) {
			Error("% is not a valid interval.".format(interval)).throw;
		};
		^nil;
	}
}