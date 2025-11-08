MEAccidentals {
	var <sign;
	var <offset;

	*new { |sign|

		^super.new.init(sign);
	}

	init { |newS|

		if (newS.isNil || newS == "") {
			sign = nil;
			offset = 0;
		} {
			sign   = newS;
			offset = this.getAccidentalOffset(sign).postln;
		};

		^this;
	}

	getAccidentalOffset { |sign|

		"getAccidentalOffset".postln;

		if (sign.contains($b)) {
			^sign.size * -1;
		} {
			^sign.size;
		};
	}
}