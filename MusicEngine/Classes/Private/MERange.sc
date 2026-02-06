/*********************************************************************************************
* MusicEngine - A dynamic music library for SuperCollider   								 *
* Copyright (C) 2025 Siriil									    							 *
* Licensed under GPLv3. See LICENSE file for details.			    						 *
*********************************************************************************************/

MERange : SequenceableCollection {
	var <>symbol;
	var <notes;

	*new { |input|
		^super.new.init(input);
	}

	init { |newI|
		var validate = MEDebug.validate;

		case
		{ newI.isInteger } {
			notes = Array.new(newI);
		}
		{ newI.isString } {
			symbol = MESymbol(newI);
			this.getRange(symbol, validate);
		};

		^this;
	}

	*with { |symbol ... args|
		var newRange = this.new(args.size);

		newRange.symbol = symbol;

		args.do { |n|
			newRange.add(n);
		};
		^newRange
	}


	/****************************************************************************************/

	*sortAndSplit { |dataArr|
		var tempM = dataArr.collect { |n| n[1] };
		var tempL = Array.new(dataArr.size);
		var tempI = Array.new(dataArr.size);

		MEDebug.log(thisMethod, 1, [dataArr]);

		tempM.sort;

		tempM.do { |n, i|

			dataArr.do { |a|

				if (a[1] == n) {
					tempL.add(a[2]);
					tempI.add(a[0]);
				};
			};
		};
		^[tempM, tempL, tempI];
	}

	/****************************************************************************************/

	*getOffsets { |intervalsArr|
		var dataArr = Array.new(intervalsArr.size + 1);

		MEDebug.log(thisMethod, 1, [intervalsArr]);

		intervalsArr.do { |i|
			var temp = Array.new(3);

			temp.add(i.interval);
			temp.add(MEMIDINote.getOffsetFromInterval(i, false));
			temp.add(MENoteName.getOffsetFromInterval(i, false));

			dataArr.add(temp);
		};
		^this.sortAndSplit(dataArr);
	}

	/****************************************************************************************/

	*wrapFirstOctave { |midiNotesArr, noteLettersArr, intervalsArr|

		MEDebug.log(thisMethod, 1, [midiNotesArr, noteLettersArr, intervalsArr]);

		midiNotesArr.do { |m, i|

			if (m > 11) {
				midiNotesArr[i] = m - 12;
				noteLettersArr  = noteLettersArr.rotate(1);
				intervalsArr    = intervalsArr.rotate(1);
			};
		};
		midiNotesArr.sort;

		^[midiNotesArr, noteLettersArr, intervalsArr]
	}

	/****************************************************************************************/

	*extendMidiRange { |midiNotesArr|
		var midiRange = Array.new(midiNotesArr.size * 11);

		MEDebug.log(thisMethod, 1, [midiNotesArr]);

		midiNotesArr.do { |m|

			while { m <= 127 } {

				midiRange.add(m);
				m = m + 12;
			};
		};
		^midiRange.sort;
	}

	/****************************************************************************************/

	*wrapAndExtend { |midiNotesArr, noteLettersArr, intervalsArr|
		var tempM, tempL, tempI;

		MEDebug.log(thisMethod, 1, [midiNotesArr, noteLettersArr, intervalsArr]);

		#tempM, tempL, tempI = this.wrapFirstOctave(
			midiNotesArr,
			noteLettersArr,
			intervalsArr
		);

		tempM = this.extendMidiRange(tempM);
		tempL = tempL.wrapExtend(tempM.size);
		tempI = tempI.wrapExtend(tempM.size);

		^[tempM, tempL, tempI];
	}

	/****************************************************************************************/

	*getMENotes { |midiNotesArr, noteLettersArr, intervalsArr, validate|
		var tempM, tempL, tempI, range;

		MEDebug.log(thisMethod, 1, [midiNotesArr, noteLettersArr, intervalsArr]);

		#tempM, tempL, tempI = this.wrapAndExtend(
			midiNotesArr,
			noteLettersArr,
			intervalsArr
		);

		range = Array.new(tempM.size);

		tempM.do { |m, i|
			range.add(MENote(tempL[i], m, tempI[i], validate));
		};

		^range;
	}

	/****************************************************************************************/

	getRange { |newS, validate = false|
		var tempM, tempL, tempI, tempR;

		MEDebug.log(thisMethod, 1, [newS]);

		#tempM, tempL, tempI = MERange.getOffsets(newS.intervals);

		MEMIDIValidators.midiOffsetArrayIsValid(tempM, diatonic: false);

		tempR = MEMIDINote.getOffsetFromName(newS.root, validate);
		tempM = MEMIDINote.transposeMidiOffset(tempM, tempR, validate);
		tempL = MENoteName.getNoteLetters(tempL, newS.root[0].asString, validate);

		notes = MERange.getMENotes(tempM, tempL, tempI, validate);
	}

	/****************************************************************************************/

	do { |function|
		var size = notes.size - 1;
		var i = 0;

		while { i <= size } {
			function.value(this[i], i);
			i = i + 1;
		};
	}

	/****************************************************************************************/

	reverseDo { |function|
		var j = this.size - 1;
		var i = 0;

		while { j >= 0 } {
			function.value(this[j], i);
			j = j - 1;
			i = i + 1;
		};
	}

	/****************************************************************************************/

	at { |index|
		^notes.at(index);
	}

	/****************************************************************************************/

	species {
		^this.class;
	}

	/****************************************************************************************/

	size {
		^notes.size;
	}

	/****************************************************************************************/

	put { |index, item|

		if (item.isKindOf(MENote)) {
			notes.put(index, item)
		} {
			Error("MERange only allowes MENote objects.").throw;
		};
	}

	/****************************************************************************************/

	add { |item|
		var nts = notes;

		if (item.isKindOf(MENote)) {
			notes.add(item);
		} {
			Error("MERange only allowes MENote objects.").throw;
		};
	}

	/****************************************************************************************/

	copy {
		^this.class.newCopyArgs(symbol, notes)
	}

	/****************************************************************************************/

	copyRange { |start, end|
		^this.class.with(symbol, *notes.copyRange(start, end));
	}

	/****************************************************************************************/

	copySeries { |first, second, last|
		^this.class.with(symbol, *notes.copySeries(first, second, last));
	}

	/****************************************************************************************/

	select { |function|
		^this.selectAs(function, this.species);
	}

	/****************************************************************************************/

	reject { |function|
		^this.rejectAs(function, this.species);
	}

	/****************************************************************************************/

	collect { |function|
		^this.collectAs(function, Array);
	}
}
