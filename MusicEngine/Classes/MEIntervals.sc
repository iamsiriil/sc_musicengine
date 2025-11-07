MEInterval {
	var lower, upper;

	*new { |b, t| ^super.new.init(b, t) }

	init { |newBot, newTop|

		if (newBot.isKindOf(MENote).not || newTop.isKindOf(MENote).not) {
			Error("newTop and newBottom must be of type MENote.\n").throw;
		} {
			lower = newBot.midi;
			upper = newTop.midi;
		}
	}

	lower {
		^lower;
	}

	upper {
		^upper;
	}
}