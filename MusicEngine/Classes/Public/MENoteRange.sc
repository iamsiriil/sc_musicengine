/*********************************************************************************************
* MusicEngine - A dynamic music library for SuperCollider   								 *
* Copyright (C) 2025 Siriil									    							 *
* Licensed under GPLv3. See LICENSE file for details.			    						 *
*********************************************************************************************/

MENoteRange : MERange {

	*newFromName { |... names, kwargs|
		var newR = this.new(names.size);

		MENameValidators.noteNameArrayIsValid(names, true);

		names.do { |n| newR.add(MENote.newFromName(n)) };

		kwargs.keysValuesDo { |k, v|

			if (v.size != names.size) {
				Error("Sequence of values must be of same length as names.").throw;
			};

			if (k == \degrees) {
				v.do { |i| MEIntervalValidators.intervalIsValid(i) };
				v.do { |i, j| newR[j].degree = i };
			} {
				newR.setValues(k, v)
			};
		};
		^newR;
	}

	/****************************************************************************************/
	/****************************************************************************************/
	// Trimming ranges by data type

	trimO { |fromOctave, toOctave|
		^this.copyRange(
			this.firstIndexInOctave(fromOctave),
			this.lastIndexInOctave(toOctave)
		);
	}

	/****************************************************************************************/

	trimM { |fromMIDI, toMIDI|
		^this.copyRange(
			this.firstOverMIDI(fromMIDI),
			this.firstUnderMIDI(toMIDI)
		);
	}

	/****************************************************************************************/

	trimF { |fromFreq, toFreq|
		^this.copyRange(
			this.firstOverFreq(fromFreq),
			this.firstUnderFreq(toFreq)
		);
	}

	/****************************************************************************************/

	trimD { |fromDegree, toDegree|
		^this.copyRange(
			this.firstIndexOfDegree(fromDegree),
			this.lastIndexOfDegree(toDegree)
		);
	}

	/****************************************************************************************/

	trimN { |fromName, toName|
		var fIndex, tIndex;

		if ("[-]?\\d$".matchRegexp(fromName)) {
			fIndex = this.indexOfName(fromName);
		} {
			fIndex = this.firstIndexOfName(fromName);
		};

		if ("[-]?\\d$".matchRegexp(toName)) {
			tIndex = this.indexOfName(toName);
		} {
			tIndex = this.lastIndexOfName(toName);
		};

		if (fIndex > tIndex) { ^nil };

		^this.copyRange(fIndex, tIndex);
	}

	/****************************************************************************************/

	bTrimO { |fromOctave|
		^this.copyRange(this.firstIndexInOctave(fromOctave), nil);
	}

	/****************************************************************************************/

	bTrimM { |fromMIDI|
		^this.copyRange(this.firstOverMIDI(fromMIDI), nil);
	}

	/****************************************************************************************/

	bTrimF { |fromFreq|
		^this.copyRange(this.firstOverFreq(fromFreq), nil);
	}

	/****************************************************************************************/

	bTrimD { |fromDegree|
		^this.copyRange(this.firstIndexOfDegree(fromDegree), nil);
	}

	/****************************************************************************************/

	bTrimN { |fromName|

		if ("[-]?\\d$".matchRegexp(fromName)) {
			^this.copyRange(this.indexOfName(fromName), nil);
		} {
			^this.copyRange(this.firstIndexOfName(fromName), nil);
		};
	}

	/****************************************************************************************/

	tTrimO { |toOctave|
		^this.copyRange(nil, this.lastIndexInOctave(toOctave));
	}

	/****************************************************************************************/

	tTrimM { |toMIDI|
		^this.copyRange(nil, this.firstUnderMIDI(toMIDI));
	}

	/****************************************************************************************/

	tTrimF { |toFreq|
		^this.copyRange(nil, this.firstUnderFreq(toFreq));
	}

	/****************************************************************************************/

	tTrimD { |toDegree|
		^this.copyRange(nil, this.lastIndexOfDegree(toDegree));
	}

	/****************************************************************************************/

	tTrimN { |toName|

		if ("[-]?\\d$".matchRegexp(toName)) {
			^this.copyRange(nil, this.indexOfName(toName));
		} {
			^this.copyRange(nil, this.lastIndexOfName(toName));
		};
	}

	/****************************************************************************************/

	>< { |limits|

		if (limits.size == 2) {
			case
			{ limits.every { |i| i.isString }                                   } {
				^this.trimN(limits[0], limits[1]);
			}
			{ limits.every { |i| i.isKindOf(Symbol) }                           } {
				^this.trimD(limits[0], limits[1]);
			}
			{ limits.every { |i| i.isInteger && ((i >= -1) && (i <= 9)) }       } {
				^this.trimO(limits[0], limits[1]);
			}
			{ limits.every { |i| i.isInteger && ((i >= 12) && (i <= 127)) }     } {
				^this.trimM(limits[0], limits[1]);
			}
			{ limits.every { |i| i.isFloat && ((i >= 20.0) && (i <= 20000.0)) } } {
				^this.trimF(limits[0], limits[1]);
			}
			{ ^nil };
		};
	}

	/****************************************************************************************/

	|> { |limit|
		case
		{ limit.isString                                           } { ^this.bTrimN(limit) }
		{ limit.isKindOf(Symbol)                                   } { ^this.bTrimD(limit) }
		{ limit.isInteger && ((limit >= -1) && (limit <= 9))       } { ^this.bTrimO(limit) }
		{ limit.isInteger && ((limit >= 12) && (limit <= 127))     } { ^this.bTrimM(limit) }
		{ limit.isFloat && ((limit >= 20.0) && (limit <= 20000.0)) } { ^this.bTrimF(limit) }
		{ ^nil };
	}

	/****************************************************************************************/

	<| { |limit|
		case
		{ limit.isString                                           } { ^this.tTrimN(limit) }
		{ limit.isKindOf(Symbol)                                   } { ^this.tTrimD(limit) }
		{ limit.isInteger && ((limit >= -1) && (limit <= 9))       } { ^this.tTrimO(limit) }
		{ limit.isInteger && ((limit >= 12) && (limit <= 127))     } { ^this.tTrimM(limit) }
		{ limit.isFloat && ((limit >= 20.0) && (limit <= 20000.0)) } { ^this.tTrimF(limit) }
		{ ^nil };
	}

	/****************************************************************************************/
	/****************************************************************************************/
	// Filtering data from ranges

	filterD { |... degrees| ^this.reject { |n| degrees.includes(n.degree) } }

	/****************************************************************************************/

	filterN { |... names|

		if (names.every("[-]?\\d$".matchRegexp(_))) {
			names.do { |a, i| names[i] = a.asSymbol };
			^this.reject { |n| names.includes(n.name.asSymbol) }
		} {
			names.do { |a, i| names[i] = a.asSymbol };
			^this.reject { |n| names.includes(n.name(withOctave: false).asSymbol) };
		};
	}


	/****************************************************************************************/

	| { |item|
		case
		{ item.isKindOf(Symbol) } { ^this.filterD(item) }
		{ item.isString         } { ^this.filterN(item) }
		{ ^nil };
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

	degrees { ^this.collect { |n| n.degree } }

	/****************************************************************************************/
	/****************************************************************************************/
	// Symbol data

	intervals { |asSymbol = true|

		if (asSymbol) {
			^meSymbol.intervals.collect { |i| i.interval };
		};
		^meSymbol.intervals.collect { |i| i.number };
	}

	/****************************************************************************************/

	symbol { |withRoot = true| ^meSymbol.symbol(withRoot) }

	/****************************************************************************************/

	alias { |withRoot = true| ^meSymbol.alias(withRoot) }

	/****************************************************************************************/

	root { |offset = false| ^meSymbol.root(offset) }


	/****************************************************************************************/
	/****************************************************************************************/
	// Data dicts

	setValue { |key, value, inplace = true|
		var newR;

		if (inplace) {
			^this.do { |n| n.set(key, value) };
		} {
			newR = this.copy;
			newR.do { |n| n.set(key, value) };
			^newR;
		};
	}

	/****************************************************************************************/

	setValues { |key, sequence, inplace = true|
		var newR;

		if (sequence.size != this.size) {
			Error("Sequance must be of the same length as range.").throw;
		};

		if (inplace) {
			^this.do { |n, i| n.set(key, sequence[i]) };
		} {
			newR = this.copy;
			newR.do { |n, i| n.set(key, sequence[i]) };
			^newR;
		};
	}



	/****************************************************************************************/

	setFunc { |key, function, inplace = true|
		var newR;

		if (inplace) {
			^this.do { |n| n.set(key, function.value) };
		} {
			newR = this.copy;
			newR.do { |n| n.set(key, function.value) };
			^newR;
		};
	}

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

	clearDict { this.do { |n| n.clearDict } }

	/****************************************************************************************/

	getKeys {
		var set = Set();
		this.do { |n| set = set ++ n.getKeys };
		^set;
	}

	/****************************************************************************************/

	/*+ { |assoc|
		case
		{ assoc.value.isKindOf(Function)               } {
			^this.setFunc(assoc.key, assoc.value);
		}
		{ assoc.value.isKindOf(SequenceableCollection) } {
			^this.setValues(assoc.key, assoc.value);
		};
		^this.setValue(assoc.key, assoc.value)
	}*/

	/****************************************************************************************/

	/*- { |key| ^this.clearValues(key) }*/

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
			if ((newN = n.transposeUp(interval)).isNil) { ^nil };
			arr.add(newN);
		};

		^this.species.with(nil, *arr);
	}

	/****************************************************************************************/

	<< { |interval|
		var arr = Array.new(this.size);
		var newN;

		this.do { |n|
			if ((newN = n.transposeDown(interval)).isNil) { ^nil };
			arr.add(newN);
		};
		^this.species.with(nil, *arr);
	}

	/****************************************************************************************/

	span {
		var lt = this.intervals(false).select { |i| i < 8 };
		var gt = this.intervals(false).select { |i| i >= 8 };
		var ct, rt, bool, temp;

		if (gt.isEmpty) { ^this };

		temp = Array(this.size);

		if (this[0].number < 8.0) {
			bool = true;
			ct   = lt.detectIndex { |i| i == this[0].number };
			rt   = 0;
		} {
			bool = false;
			ct   = gt.detectIndex { |i| i == this[0].number };
			rt   = 1;
		};

		this.do { |n, i|

			case
			{ bool && lt[ct] == n.number } {
				ct = ct + 1;
				temp.add(n);
				if (ct == lt.size) {
					bool = false;
					ct = 0;
				}
			}
			{ bool.not && (gt[ct] == n.number) && (rt == 1) } {
				ct = ct + 1;
				temp.add(n);
				if (ct == gt.size) {
					bool = true;
					ct = 0;
					rt = 0;
				};
			}
			{ (n.number == lt[0]) && (rt == 0) } { rt = rt + 1 };
		};
		^this.class.with(meSymbol, *temp);
	}


}