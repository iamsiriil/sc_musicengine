/*********************************************************************************************
* MusicEngine - A dynamic chord library for SuperCollider   								 *
* Copyright (C) 2025 Siriil									    							 *
* Licensed under GPLv3. See LICENSE file for details.			    						 *
*********************************************************************************************/

MELetterValidators {

	*initClass { ^this }

	/****************************************************************************************/

	*noteLetterIsValid { |noteLetter|
		var regex = "^[A-G]$";

		MEDebug.log(thisMethod, 3);

		if (noteLetter.isString.not) {
			Error("Note letter must be of type String.").throw;
		};
		if (regex.matchRegexp(noteLetter.asString) == false) {
			Error("% is not a valid note letter.".format(noteLetter)).throw;
		};
		^nil;
	}

	/****************************************************************************************/

	*letterOffsetIsValid { |letterOffset|
		var offsets = (0..6).asSet;

		MEDebug.log(thisMethod, 3);

		if (letterOffset.isInteger.not) {
			Error("Letter offset must be of type Integer.").throw;
		};
		if (offsets.includes(letterOffset).not) {
			Error("% is not a valid letter offset.".format(letterOffset)).throw;
		};
		^nil;
	}

	/****************************************************************************************/

	*letterOffsetArrayIsValid { |letterOffsetArr|

		MEDebug.log(thisMethod, 3);

		letterOffsetArr.do { |o|
			this.letterOffsetIsValid(o);
		};
		^nil;
	}
}