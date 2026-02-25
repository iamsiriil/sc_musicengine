/*********************************************************************************************
* MusicEngine - A dynamic music library for SuperCollider   								 *
* Copyright (C) 2025 Siriil									    							 *
* Licensed under GPLv3. See LICENSE file for details.			    						 *
*********************************************************************************************/

MENoteRange : MERange {

	*newFromName { |... args, kwargs|
		var newRange = this.newClear(args.size);

		args.do { |n, i|
			newRange.put(i, MENote.newFromName(n));
		};

		kwargs.keysValuesDo { |k, v|

			if (k == \intervals) {
				v.do { |i, j|
					newRange[j].degree = MEInterval(i);
				};
			} {
				newRange.setArray(k, v) };
			};

		^newRange;
	}

	/****************************************************************************************/

	// Indexing data

	firstIndexInOctave { |octave|
		^this.detectIndex { |n| n.octave == octave };
	}

	/****************************************************************************************/

	lastIndexInOctave { |octave|
		^this.detectLastIndex { |n| n.octave == octave };
	}

	/****************************************************************************************/

	firstIndexOfDegree { |degree|
		^this.detectIndex { |n| n.degree == degree };
	}

	/****************************************************************************************/

	lastIndexOfDegree { |degree|
		^this.detectLastIndex { |n| n.degree == degree };
	}

	/****************************************************************************************/

	indexOfName { |name|
		^this.detectIndex { |n| n.name == name };
	}

	/****************************************************************************************/

	degreeInOctave { |degree, octave|
		^this.detectIndex { |n| (n.degree == degree) && (n.octave == octave) };
	}

	/****************************************************************************************/
	/****************************************************************************************/
	// Trimming ranges by data type

	trimO { |fromOctave, toOctave|
		var fIndex = this.firstIndexInOctave(fromOctave);
		var tIndex = this.lastIndexInOctave(toOctave);

		^this[fIndex..tIndex];
	}

	/****************************************************************************************/

	trimD { |fromDegree, toDegree|
		var fIndex = this.firstIndexOfDegree(fromDegree);
		var tIndex = this.lastIndexOfDegree(toDegree);

		^this[fIndex..tIndex];
	}

	/****************************************************************************************/

	trimM { |fromMIDI, toMIDI|
		^this.select { |n| (n.midi >= fromMIDI) && (n.midi <= toMIDI) };
	}

	/****************************************************************************************/

	trimF { |fromFreq, toFreq|
		^this.select { |n| (n.freq >= fromFreq) && (n.freq <= toFreq) };
	}

	>< { |limits|

		if (limits.size == 2) {
			case
			{ limits.every { |i| i.isInteger && ((i >= -1) && (i <= 9)) }       } {
				^this.trimO(limits[0], limits[1]);
			}
			{ limits.every { |i| i.isInteger && ((i >= 12) && (i <= 127)) }     } {
				^this.trimM(limits[0], limits[1]);
			}
			{ limits.every { |i| i.isFloat && ((i >= 20.0) && (i <= 20000.0)) } } {
				^this.trimF(limits[0], limits[1]);
			};
		};
	}

	/****************************************************************************************/
	/****************************************************************************************/
	// Filtering data from ranges

	filterD { |... args| ^this.reject { |n| args.includes(n.degree) } }

	/****************************************************************************************/

	filterN { |... args| ^this.reject { |n| args.includes(n.name.asSymbol) } }

	/****************************************************************************************/

	* { |args|

		case
		{ args.every { |n| n.isKindOf(Symbol) } } {
			^this.filterD(*args);
		}
		{ args.every { |n| n.isKindOf(String) } } {
			^this.filterN(*args);
		};
	}

	/****************************************************************************************/
	/****************************************************************************************/
	// Collecting data by type

	midi { ^this.collect { |n| n.midi } }

	/****************************************************************************************/

	freq { ^this.collect { |n| n.freq } }

	/****************************************************************************************/

	names { |charSet = \ascii, withOctave = true|
		^this.collect { |n| n.name(charSet, withOctave) };
	}

	/****************************************************************************************/

	sol { |charSet = \ascii, withOctave = true|
		^this.collect { |n| n.sol(charSet, withOctave) };
	}

	/****************************************************************************************/

	degrees { |root = false|
		^this.collect { |n| n.degree(root) };
	}

	/****************************************************************************************/
	/****************************************************************************************/
	// Symbol data

	intervals { |asSymbol = true|

		if (asSymbol) {
			^symbol.intervals.collect { |i| i.interval };
		};
		^symbol.intervals.collect { |i| i.number };
	}

	/****************************************************************************************/

	symbol { |withRoot = true| ^symbol.symbol(withRoot) }

	/****************************************************************************************/

	alias { |withRoot = true| ^symbol.alias(withRoot) }

	/****************************************************************************************/

	root { |offset = false| ^symbol.root(offset) }

	/****************************************************************************************/

	symbolObj { ^symbol }

	/****************************************************************************************/
	/****************************************************************************************/
	// Data dicts

	setValues { |key, value| this.do { |n| n.set(key, value) } }

	/****************************************************************************************/

	setArray { |key, array| this.do { |n, i| n.set(key, array[i]) } }

	/****************************************************************************************/

	setFunc { |key, function| this.do { |n| n.set(key, function.value) } }

	/****************************************************************************************/

	getValues { |key|

		if (this.getKeys.includes(key)) {
			^this.collect { |n| n.get(key) };
		};
		Error("Key '%' is not defined.".format(key)).throw;
	}

	/****************************************************************************************/

	clearValues { |key| this.do { |n| n.clearValue(key) } }

	/****************************************************************************************/

	clearDict { this.do { |n| n.clearData } }

	/****************************************************************************************/

	getKeys {
		var set = Set();
		this.do { |n| set = set ++ n.getKeys };
		^set;
	}

	/****************************************************************************************/
	/****************************************************************************************/

	asPseq { |what, rep = 1, off = 0|

		switch(what)
		{ \midi } { ^Pseq(this.midi, rep, off) }
		{ \freq } { ^Pseq(this.freq, rep, off) }
		{
			if (this.getKeys.includes(what)) {
				^Pseq(this.getValues(what), rep, off);
			};
			Error("Key '%' is not defined.".format(what)).throw;
		};
	}

	/****************************************************************************************/
	/****************************************************************************************/
	// Range transposition

	>> { |interval|
		var arr = Array.new(this.size);
		var newN;

		this.do { |n|
			if ((newN = n.transposeUp(interval)).notNil) {
				arr.add(newN);
			};
		};
		^this.species.with(nil, *arr);
	}

	/****************************************************************************************/

	<< { |interval|
		var arr = Array.new(this.size);
		var newN;

		this.do { |n|
			if ((newN = n.transposeDown(interval)).notNil) {
				arr.add(newN);
			};
		};
		^this.species.with(nil, *arr);
	}



	/****************************************************************************************/

	/*getTwoOctaveSpan { |notes, degrees|
		var index = degrees.detectIndex { |i| i == notes[0].degree };
		//var underOct = degrees.collect { |i| i[1..].asInteger }.count { |n| n < 7 };
		var temp  = Array();

		notes.do { |n|

			if (n.degree == degrees[index]) {

				temp  = temp.add(n);
				index = (index + 1) % degrees.size;
			};
		};

		^temp;
	}*/

	span {
		var intervals = this.collect{ |n| n.number }.asSet.asArray.sort;
		var temp = Array(this.size.postln);
		var lt   = intervals.select { |i| i < 8 };
		var bt   = intervals.select { |i| i >= 8 };
		var ct   = 0, rt, bool;

		if (this[0].number < 8.0) {
			bool = true;
			ct   = lt.detectIndex { |i| i == this[0].number };
			rt   = 0;
		} {
			bool = false;
			ct   = bt.detectIndex { |i| i == this[0].number };
			rt   = 1;
		};

		this.do { |n, i|

			case
			{ bool && lt[ct] == n.number } {
				ct = ct + 1;
				temp.add(n).postln;
				if (ct == lt.size) {
					bool = false;
					ct = 0;
				}
			}
			{ bool.not && (bt[ct] == n.number) && (rt == 1) } {
				ct = ct + 1;
				temp.add(n).postln;
				if (ct == bt.size) {
					bool = true;
					ct = 0;
					rt = 0;
				};
			}
			{ (n.number == lt[0]) && (rt == 0) } { rt = rt + 1 };
		};

		^this.class.with(nil, *temp);
	}

}