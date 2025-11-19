/*********************************************************************************************
* MusicEngine - A dynamic chord library for SuperCollider   								 *
* Copyright (C) 2025 Siriil									    							 *
* Licensed under GPLv3. See LICENSE file for details.			    						 *
*********************************************************************************************/

MENoteRanges : MERangeTools {
	var symbol;
	var notes;

	*new { |symbol|
		^super.new.init(symbol);
	}

	init { |newSymbol|

		symbol = MESymbols.new(newSymbol);
		notes  = super.getRange(symbol);

		^this;
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

	firstIndexFromDegree { |degree = "Rt"|
		var note = notes.select { |n| n.degree == degree }.first;

		^notes.indexOf(note);
	}

	/****************************************************************************************/

	lastIndexFromDegree { |degree = "Rt"|
		var note = notes.select { |n| n.degree == degree }.last;

		^notes.indexOf(note);
	}

	/****************************************************************************************/

	degreeInOctave { |octave, degree|
		var note = notes.select { |n| (n.octave == octave) && (n.degree == degree) }.first;

		^notes.indexOf(note);
	}

	/****************************************************************************************/

	notes { |fromOctave = 1, toOctave = 9, fromDegree = nil|
		var degreeSet = (["Rt"] ++ symbol.degrees).asSet;
		var indexF, indexT;

		if ((fromOctave > toOctave) || (fromOctave < -1) || (toOctave > 9)) {
			Error("Octaves go from -1 to 9.\n").throw;
		};

		if (fromDegree.notNil && degreeSet.includes(fromDegree).not) {
			Error("Range does not include interval %. Pick from Rt, %.\n".format(
				fromDegree,
				symbol.degrees.join(", ")
			)).throw;
		};

		if (fromDegree.notNil) {
			indexF = this.degreeInOctave(fromOctave, fromDegree);
			indexT = this.lastIndexFromOctave(toOctave);

		} {
			indexF = this.firstIndexFromOctave(fromOctave);
			indexT = this.lastIndexFromOctave(toOctave);
		};

		^notes[indexF..indexT];

	}

	/****************************************************************************************/

	midi { |fromOctave = 1, toOctave = 9, fromDegree = nil|
		^this.notes(fromOctave, toOctave, fromDegree).collect { |n| n.midi };
	}

	/****************************************************************************************/

	freq { |fromOctave = 1, toOctave = 9, fromDegree = nil|
		^this.notes(fromOctave, toOctave, fromDegree).collect { |n| n.freq };
	}

	/****************************************************************************************/

	names { |fromOctave = 1, toOctave = 9, fromDegree = nil, withOctave = true|
		^this.notes(fromOctave, toOctave, fromDegree).collect { |n| n.name(withOctave) };
	}

	/****************************************************************************************/

	sol { |fromOctave = 1, toOctave = 9, fromDegree = nil, withOctave = true|
		^this.notes(fromOctave, toOctave, fromDegree).collect { |n| n.sol(withOctave) };
	}

	/****************************************************************************************/

	degrees { |fromOctave = 1, toOctave = 9, fromDegree = nil|
		^this.notes(fromOctave, toOctave, fromDegree).collect { |n| n.degree };
	}

	/****************************************************************************************/

	root {
		^symbol.root;
	}

	/****************************************************************************************/

	symbol {
		^symbol.symbol;
	}

	/****************************************************************************************/

	alias {
		^symbol.alias;
	}
}