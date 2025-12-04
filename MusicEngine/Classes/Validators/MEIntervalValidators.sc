/*********************************************************************************************
* MusicEngine - A dynamic chord library for SuperCollider   								 *
* Copyright (C) 2025 Siriil									    							 *
* Licensed under GPLv3. See LICENSE file for details.			    						 *
*********************************************************************************************/

MEIntervalValidators {

	*initClass {}

	/****************************************************************************************/

	*intervalIsValid { |interval|
		var result = false;

		MECore.intervals.do { |v|
			result = result || v.includes(interval.asString);
		};

		if (result == false) {
			Error("% is not a valid interval.".format(interval)).throw;
		};
		^nil;
	}
}