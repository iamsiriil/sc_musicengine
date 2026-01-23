/*********************************************************************************************
* MusicEngine - A dynamic music library for SuperCollider   								 *
* Copyright (C) 2025 Siriil									    							 *
* Licensed under GPLv3. See LICENSE file for details.			    						 *
*********************************************************************************************/

MEInterval {
	var <offset;
	var <number;
	var <quality;

	*new {|interval|
		^super.new.init(interval);
	}

	init { |newI|
		var temp;

		if (newI == "Rt") {
			quality = $P;
			number  = 1;
		} {
			quality  = newI[0];
			number   = newI[1..].asInteger;
		};

		switch(quality)
		{ $d } { offset = number + 0.0 }
		{ $m } { offset = number + 0.1 }
		{ $P } { offset = number + 0.2 }
		{ $M } { offset = number + 0.3 }
		{ $A } { offset = number + 0.4 };

		^this;
	}

	/****************************************************************************************/

	printOn { |stream|
		stream << this.interval(false);
	}

	/****************************************************************************************/

	*getMEIntervalArray { |intervalsArr|
		var arr = Array();

		intervalsArr.do { |i|
			arr = arr.add(MEInterval(i));
		};
		^this.sortIntervals(arr);
	}

	/****************************************************************************************/

	*sortIntervals { |intervalsArr|
		^intervalsArr.sort { |a, b| a.offset < b.offset };
	}

	/****************************************************************************************/

	interval { |root = false|

		if (root && (number == 1 && quality == $P)) {
			^"Rt";
		} {
			^quality ++ number;
		};
	}
}

