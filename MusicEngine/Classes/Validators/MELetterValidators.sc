/*********************************************************************************************
* MusicEngine - A dynamic chord library for SuperCollider   								 *
* Copyright (C) 2025 Siriil									    							 *
* Licensed under GPLv3. See LICENSE file for details.			    						 *
*********************************************************************************************/

MELetterValidators {

	*initClass {}

	/****************************************************************************************/

	*noteLetterIsValid { |noteLetter|
		var regex = "^[A-G]$";

		//MEDebug.log("MEValidators", "*noteLetterIsValid");

		if (regex.matchRegexp(noteLetter.asString) == false) {
			Error("% is not a valid note letter.".format(noteLetter)).throw;
		};
		^nil;
	}

	/****************************************************************************************/

	*letterOffsetIsValid { |letterOffset|
		var offsets = (0..6).asSet;

		if (letterOffset.isInteger.not || offsets.includes(letterOffset).not) {
			Error("% is not a valid letter offset.".format(letterOffset)).throw;
		};
		^nil;
	}

	/****************************************************************************************/

	*letterOffsetArrayIsValid { |letterOffsetArr|

		letterOffsetArr.do { |o|
			this.letterOffsetIsValid(o);
		};
		^nil;
	}
}