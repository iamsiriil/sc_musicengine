/*********************************************************************************************
* MusicEngine - A dynamic music library for SuperCollider   								 *
* Copyright (C) 2025 Siriil									    							 *
* Licensed under GPLv3. See LICENSE file for details.			    						 *
*********************************************************************************************/

MENoteRange {
	var symbol;
	var notes;

	*new { |symbol|
		^super.new.init(symbol);
	}

	init { |newS|

		MEDebug.log(thisMethod, 1, [newS]);

		symbol = MESymbol.new(newS);
		notes  = MERange.getRange(symbol, MEDebug.validate);

		^this;
	}

	/****************************************************************************************/

	printOn { |stream|
		var chordSymbol = if (symbol.alias.isNil) { symbol.symbol } { symbol.alias };

		stream << chordSymbol << " " << notes;
	}

	/****************************************************************************************/

	firstIndexFromOctave { |octave|
		var note = notes.select { |n| (n.octave == octave) }.first;

		^notes.indexOf(note);
	}

	/****************************************************************************************/

	lastIndexFromOctave { |octave|
		var note = notes.select { |n| n.octave == octave }.last;

		^notes.indexOf(note);
	}

	/****************************************************************************************/

	firstIndexFromDegree { |degree = "P1"|
		var note = notes.select { |n| n.degree == degree }.first;

		^notes.indexOf(note);
	}

	/****************************************************************************************/

	lastIndexFromDegree { |degree = "P1"|
		var note = notes.select { |n| n.degree == degree }.last;

		^notes.indexOf(note);
	}

	/****************************************************************************************/

	degreeInOctave { |octave, degree|
		var note = notes.select { |n| (n.octave == octave) && (n.degree == degree) }.first;

		^notes.indexOf(note);
	}

	/****************************************************************************************/

	getTwoOctaveSpan { |notes, degrees|
		var index = degrees.detectIndex { |i| i == notes[0].degree };
		var temp  = Array();

		notes.do { |n|

			if (n.degree == degrees[index]) {
				temp  = temp.add(n);
				index = (index + 1) % degrees.size;
			};
		};

		^temp;
	}

	/****************************************************************************************/

	notes { |fromOctave = 1, toOctave = 9, fromDegree = nil, toDegree = nil, octaveSpan = 1|
		var degrees = symbol.intervals.collect { |i| i.interval };
		var indexF, indexT, temp;

		if ((fromOctave > toOctave) || (fromOctave < -1) || (toOctave > 9)) {
			Error("Octaves go from -1 to 9.\n").throw;
		};

		if (fromDegree.notNil && degrees.asSet.includes(fromDegree).not) {
			Error("Range does not include interval %. Pick from %.\n".format(
				fromDegree,
				degrees
			)).throw;
		};

		case
		{ fromDegree.notNil && toDegree.notNil } {
			indexF = this.degreeInOctave(fromOctave, fromDegree);
			indexT = this.degreeInOctave(toOctave, toDegree);
		}
		{ fromDegree.isNil && toDegree.notNil } {
			indexF = this.firstIndexFromOctave(fromOctave);
			indexT = this.degreeInOctave(toOctave, toDegree);
		}
		{ fromDegree.notNil && toDegree.isNil } {
			indexF = this.degreeInOctave(fromOctave, fromDegree);
			indexT = this.lastIndexFromOctave(toOctave);
		} {
			indexF = this.firstIndexFromOctave(fromOctave);
			indexT = this.lastIndexFromOctave(toOctave);
		};

		temp = notes[indexF..indexT];

		if (octaveSpan == 2) {
			^this.getTwoOctaveSpan(temp, degrees)
		} {
			^temp;
		};
	}

	/****************************************************************************************/

	midi { |fromOctave = 1, toOctave = 9, fromDegree = nil, toDegree = nil, octaveSpan = 1|
		^this.notes(fromOctave, toOctave, fromDegree, toDegree, octaveSpan).collect { |n| n.midi };
	}

	/****************************************************************************************/

	freq { |fromOctave = 1, toOctave = 9, fromDegree = nil, toDegree = nil, octaveSpan = 1|
		^this.notes(fromOctave, toOctave, fromDegree, toDegree, octaveSpan).collect { |n| n.freq };
	}

	/****************************************************************************************/

	names { |fromOctave = 1, toOctave = 9, fromDegree = nil, toDegree = nil,
		octaveSpan = 1, withOctave = true|

		^this.notes(fromOctave, toOctave, fromDegree, toDegree, octaveSpan).collect { |n| n.name(withOctave) };
	}

	/****************************************************************************************/

	sol { |fromOctave = 1, toOctave = 9, fromDegree = nil, toDegree = nil,
		octaveSpan = 1, withOctave = true|

		^this.notes(fromOctave, toOctave, fromDegree, toDegree, octaveSpan).collect { |n| n.sol(withOctave) };
	}

	/****************************************************************************************/

	degrees { |fromOctave = 1, toOctave = 9, fromDegree = nil, toDegree = nil, octaveSpan = 1|
		^this.notes(fromOctave, toOctave, fromDegree, toDegree, octaveSpan).collect { |n| n.degree };
	}

	/****************************************************************************************/

	root { |offset = false|

		if (offset) {
			^MEMIDINote.getOffsetFromName(symbol.root, false);
		} {
			^symbol.root;
		}
	}

	/****************************************************************************************/

	symbol { |withRoot = true|
		^symbol.symbol(withRoot);
	}

	/****************************************************************************************/

	alias { |withRoot = true|
		^symbol.alias(withRoot);
	}

	/****************************************************************************************/

	intervals {
		^symbol.intervals.collect { |i| i.interval };
	}
}