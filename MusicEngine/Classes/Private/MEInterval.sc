/*********************************************************************************************
* MusicEngine - A dynamic music library for SuperCollider   								 *
* Copyright (C) 2025 Siriil									    							 *
* Licensed under GPLv3. See LICENSE file for details.			    						 *
*********************************************************************************************/

MEInterval {
	classvar <dict;
	var <interval;
	var number;

	*new { |interval| ^super.new.init(interval) }

	init { |newI|
		var temp, quality;

		interval = newI;

		temp    = newI.asString;
		quality = MEInterval.convertQuality(temp[0].asSymbol);
		number  = temp[1..].asInteger + quality;

		^this;
	}

	*initClass {
		dict = TwoWayIdentityDictionary[
			\d -> '0.0',
			\m -> '0.1',
			\P -> '0.2',
			\M -> '0.3',
			\A -> '0.4'
		];
	}

	/****************************************************************************************/

	printOn { |stream| stream << "MEInterval(" << this.interval << ")" }

	/****************************************************************************************/

	*getMEIntervalArray { |intervalsArr|
		var size = intervalsArr.size, i = 0;
		var temp = Array(size);

		while { i < size } {
			temp.add(MEInterval(intervalsArr[i].asSymbol));
			i = i + 1;
		};
		^temp.sort { |a, b| a < b };
	}

	/****************************************************************************************/

	*convertQuality { |value|

		case
		{ value.isKindOf(Float)  } { ^dict.getID(value.asSymbol) }
		{ value.isKindOf(Symbol) } { ^dict[value].asFloat }
		{
			Error("% is not a valid value.".format(value)).throw;
		};
	}

	/****************************************************************************************/

	number { |asInt = false|

		if (asInt) {
			^number.asInteger;
		};
		^number;
	}

	/****************************************************************************************/

	quality { |asFloat = false|

		if (asFloat) {
			^this.number.frac;
		};
		^MEInterval.convertQuality(this.number.frac);
	}

	/****************************************************************************************/

	asLetterOffset {

		if (this.number(true) > 7) {
			^this.number(true) - 8;
		};
		^this.number(true) - 1;
	}

	/****************************************************************************************/

	asMIDIOffset {

		MECore.intervals.keysValuesDo { |k, v|
			if (v.includes(this.interval)) { ^k };
		};
		^nil;
	}

	/****************************************************************************************/

	isEnharmonic { |aMEInterval| ^this.asMIDIOffset == aMEInterval.asMIDIOffset }

	/****************************************************************************************/

	== { |aMEInterval| ^(this.number == aMEInterval.number) }

	/****************************************************************************************/

	!= { |aMEInterval| ^(this.number != aMEInterval.number) }

	/****************************************************************************************/

	>= { |aMEInterval| ^(this.number >= aMEInterval.number) }

	/****************************************************************************************/

	<= { |aMEInterval| ^(this.number <= aMEInterval.number) }

	/****************************************************************************************/

	>  { |aMEInterval| ^(this.number > aMEInterval.number) }

	/****************************************************************************************/

	<  { |aMEInterval| ^(this.number < aMEInterval.number) }

}