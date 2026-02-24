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

		if (symbol.notNil) {
			newRange.symbol = symbol;
		};

		args.do { |n|
			newRange.add(n);
		};
		^newRange
	}

	*newClear { |size|
		var newR = MENoteRange(size);
		var i = 0;

		"newClear".postln;

		while { i < size } {
			newR.add(nil);
			i = i + 1;
		} ;
		^newR;
	}

	/****************************************************************************************/

	*getOffsets { |intervalsArr|
		var size    = intervalsArr.size;
		var dataArr = Array.new(size);
		var i, int, temp;

		MEDebug.log(thisMethod, 1, [intervalsArr]);

		i = 0;
		while { i < size } {
			int  = intervalsArr[i];
			temp = Array(3);

			temp.add(int.asMIDIOffset);
			temp.add(int.asLetterOffset);
			temp.add(int.interval);

			dataArr.add(temp);
			i = i + 1;
		};
		^dataArr.sort { |a, b| a[0] < b[0] }.flop;
	}

	/****************************************************************************************/

	*wrapFirstOctave { |midiNotesArr, noteLettersArr, intervalsArr|
		var size = midiNotesArr.size;
		var i, m;

		MEDebug.log(thisMethod, 1, [midiNotesArr, noteLettersArr, intervalsArr]);

		i = 0;
		while { i < size } {

			m = midiNotesArr[i];
			if (m > 11) {
				midiNotesArr[i] = m - 12;
				noteLettersArr  = noteLettersArr.rotate(1);
				intervalsArr    = intervalsArr.rotate(1);
			};
			i = i + 1;
		};
		midiNotesArr.sort;

		^[midiNotesArr, noteLettersArr, intervalsArr]
	}

	/****************************************************************************************/

	*extendMidiRange { |midiNotesArr|
		var size      = midiNotesArr.size;
		var midiRange = Array.new(size * 11);
		var i, m;

		MEDebug.log(thisMethod, 1, [midiNotesArr]);

		i = 0;
		while { i < size } {

			m = midiNotesArr[i];
			while { m <= 127 } {

				midiRange.add(m);
				m = m + 12;
			};
			i = i + 1;
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
		var size, i;

		MEDebug.log(thisMethod, 1, [midiNotesArr, noteLettersArr, intervalsArr]);

		#tempM, tempL, tempI = this.wrapAndExtend(
			midiNotesArr,
			noteLettersArr,
			intervalsArr
		);

		size  = tempM.size;
		range = Array.new(size);

		i = 0;
		while { i < size } {
			range.add(MENote(tempL[i], tempM[i], tempI[i], validate: validate));
			i = i + 1;
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

	at { |index| ^notes.at(index) }

	/****************************************************************************************/

	species { ^this.class }

	/****************************************************************************************/

	size { ^notes.size }

	/****************************************************************************************/

	put { |index, item| notes.put(index, item) }

	/****************************************************************************************/

	add { |item| notes.add(item) }

	/****************************************************************************************/

	copy {
		^this.deepCopy;
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

	collect { |function| ^this.collectAs(function, Array) }

	/****************************************************************************************/

	select { |function|
		var temp = Array(this.size);
		var i = 0;

		"Select".postln;

		while { i < this.size } {

			if (function.value(this[i])) {
				temp.add(this[i]);
			};
			i = i + 1;
		};
		^this.species.with(this.symbolObj, *temp);
	}

	/****************************************************************************************/

	reject { |function|
		var temp = Array(this.size);
		var i = 0;

		"Reject".postln;

		while { i < this.size } {

			if (function.value(this[i]).not) {
				temp.add(this[i]);
			};
			i = i + 1;
		};
		^this.species.with(this.symbolObj, *temp);
	}

	/****************************************************************************************/

	foldExtend { |index|
		var newR = this.notes.foldExtend(index);

		^this.species.with(nil, *newR);
	}

	pyramid { |number|
		var newR = this.notes.pyramid(number);

		^this.species.with(nil, *newR);
	}
}
