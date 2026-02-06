/*********************************************************************************************
* MusicEngine - A dynamic music library for SuperCollider   								 *
* Copyright (C) 2025 Siriil									    							 *
* Licensed under GPLv3. See LICENSE file for details.			    						 *
*********************************************************************************************/

MEInterval {
	classvar <dict;
	var interval;
	var number;


	*new {|interval|
		^super.new.init(interval);
	}

	init { |newI|
		var temp, quality;

		interval = newI;
		temp     = newI.asString;

		quality  = MEInterval.convertQuality(temp[0].asSymbol);
		number   = temp[1..].asInteger + quality;

		^this;
	}

	*initClass {
		dict = Dictionary[
			\d -> 0.0,
			\m -> 0.1,
			\P -> 0.2,
			\M -> 0.3,
			\A -> 0.4
		];
	}

	/****************************************************************************************/

	printOn { |stream|
		stream << this.interval(false);
	}

	/****************************************************************************************/

	*getMEIntervalArray { |intervalsArr|
		var arr = Array();

		intervalsArr.do { |i|
			arr = arr.add(MEInterval(i.asSymbol));
		};
		^this.sortIntervals(arr);
	}

	/****************************************************************************************/

	*sortIntervals { |intervalsArr|
		^intervalsArr.sort { |a, b| a.number < b.number };
	}

	/****************************************************************************************/

	*convertQuality { |value|

		case
		{ value.isKindOf(Float)  } { ^dict.findKeyForValue(value) }
		{ value.isKindOf(Symbol) } { ^dict[value] }
		{
			Error("% is not a valid value.".format(value)).throw;
		};
	}

	/****************************************************************************************/

	interval { |root = false|

		if (root && (number == 1.2) ) {
			^\Rt;
		};
		^interval;
	}

	/****************************************************************************************/

	number { |asInt = false|

		if (asInt) {
			^number.floor.asInteger;
		};
		^number;
	}

	/****************************************************************************************/

	quality { |asFloat = false|
		var int, float;

		int   = this.number.floor;
		float = this.number - int;

		if (asFloat) {
			^float;
		};
		^MEInterval.convertQuality(float);
	}
}

