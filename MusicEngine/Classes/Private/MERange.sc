/*********************************************************************************************
* MusicEngine - A dynamic music library for SuperCollider   								 *
* Copyright (C) 2025 Siriil									    							 *
* Licensed under GPLv3. See LICENSE file for details.			    						 *
*********************************************************************************************/

MERange : SequenceableCollection {
	var <>meSymbol;
	var <notes;

	*new { |input| ^super.new.init(input) }

	init { |newI|
		var validate = MEDebug.validate;

		case
		{ newI.isInteger } {
			notes = Array.new(newI);
		}
		{ newI.isString } {
			meSymbol = MESymbol(newI);
			notes    = MERange.getRange(meSymbol, validate);
		};
		^this;
	}

	/****************************************************************************************/

	*with { |symbol ... args|
		var newRange = this.new(args.size);

		if (symbol.notNil) {
			newRange.meSymbol = symbol;
		};

		args.do { |n|
			newRange.add(n);
		};
		^newRange
	}

	/****************************************************************************************/
	/****************************************************************************************/
	// Range building methods

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

	*getRange { |newS, validate = false|
		var tempM, tempL, tempI, tempR;

		MEDebug.log(thisMethod, 1, [newS]);

		#tempM, tempL, tempI = MERange.getOffsets(newS.intervals);

		MEMIDIValidators.midiOffsetArrayIsValid(tempM, diatonic: false);

		tempR = MEMIDINote.getOffsetFromName(newS.root, validate);
		tempM = MEMIDINote.transposeMidiOffset(tempM, tempR, validate);
		tempL = MENoteName.getNoteLetters(tempL, newS.root[0].asString, validate);

		^MERange.getMENotes(tempM, tempL, tempI, validate);
	}

	/****************************************************************************************/
	/****************************************************************************************/
	// Basic methods

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
	/****************************************************************************************/
	// Iterator methods

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

	select { |function|
		var temp = Array(this.size);
		var i = 0;

		while { i < this.size } {

			if (function.value(this[i])) {
				temp.add(this[i]);
			};
			i = i + 1;
		};
		^this.species.with(this.meSymbol, *temp);
	}

	/****************************************************************************************/

	reject { |function|
		var temp = Array(this.size);
		var i = 0;

		while { i < this.size } {

			if (function.value(this[i]).not) {
				temp.add(this[i]);
			};
			i = i + 1;
		};
		^this.species.with(this.meSymbol, *temp);
	}

	/****************************************************************************************/

	collect { |function| ^this.collectAs(function, Array) }

	/****************************************************************************************/
	/****************************************************************************************/
	// Copy methods

	copy {
		var newR = Array(this.size);

		this.do { |n| newR.add(n.copy) };

		^this.species.with(this.meSymbol, *newR);
	}

	/****************************************************************************************/

	copyRange { |start, end|
		var size, temp, i, j;

		i = if (start.isNil) { 0 } { start };
		j = if (end.isNil)   { this.size - 1 } { end };

		size = j - i;
		temp = Array(size);

		while { i <= j } {
			temp.add(this[i]);
			i = i + 1;
		};
		^this.species.with(this.meSymbol, *temp);
	}

	/****************************************************************************************/

	copySeries { |first, second, last|
		var size, temp, i, j, k;

		i = if (first.isNil)  { 0 } { first };
		j = if (second.isNil) { 1 } { second };

		size = (this.size / j).ceil;
		temp = Array(size.asInteger);

		case
		{ last.isNil             } { k = this.size - 1 }
		{ last > (this.size - 1) } { k = this.size - 1 }
		{ k = last };

		while { i <= k } {
			temp.add(this[i]);
			i = i + j;
		};
		^this.species.with(this.meSymbol, *temp);
	}

	/****************************************************************************************/
	/****************************************************************************************/
	// Clip, wrap and fold

	clipAt { |index| ^this.notes.clipAt(index) }

	/****************************************************************************************/

	wrapAt { |index| ^this.notes.wrapAt(index) }

	/****************************************************************************************/

	foldAt { |index| ^this.notes.foldAt(index) }

	/****************************************************************************************/

	clipExtend { |index|
		var temp = this.notes.clipExtend(index);
		var newR = Array(temp.size);

		temp.do { |n| newR.add(n.copy) };

		^this.species.with(this.meSymbol, *newR);
	}

	/****************************************************************************************/

	wrapExtend { |index|
		var temp = this.notes.wrapExtend(index);
		var newR = Array(temp.size);

		temp.do { |n| newR.add(n.copy) };

		^this.species.with(this.meSymbol, *newR);
	}

	/****************************************************************************************/


	foldExtend { |index|
		var temp = this.notes.foldExtend(index);
		var newR = Array(temp.size);

		temp.do { |n| newR.add(n.copy) };

		^this.species.with(this.meSymbol, *newR);
	}


	/****************************************************************************************/
	/****************************************************************************************/
	// Indexing data

	firstIndexInOctave { |octave| ^this.detectIndex { |n| n.octave == octave } }

	/****************************************************************************************/

	lastIndexInOctave { |octave| ^this.detectLastIndex { |n| n.octave == octave } }

	/****************************************************************************************/

	firstIndexOfDegree { |degree| ^this.detectIndex { |n| n.degree == degree } }

	/****************************************************************************************/

	lastIndexOfDegree { |degree| ^this.detectLastIndex { |n| n.degree == degree } }

	/****************************************************************************************/

	firstIndexOfName { |name|
		^this.detectIndex { |n| n.name(withOctave: false) == name };
	}

	/****************************************************************************************/

	lastIndexOfName { |name|
		^this.detectLastIndex { |n| n.name(withOctave: false) == name };
	}

	/****************************************************************************************/

	firstOverMIDI { |midi| ^this.detectIndex { |n| n.midi >= midi } }

	/****************************************************************************************/

	firstUnderMIDI { |midi| ^this.detectLastIndex { |n| n.midi <= midi } }

	/****************************************************************************************/

	firstOverFreq { |freq| ^this.detectIndex { |n| n.freq >= freq } }

	/****************************************************************************************/

	firstUnderFreq { |freq| ^this.detectLastIndex { |n| n.freq <= freq } }

	/****************************************************************************************/


	degreeOverMIDI { |degree, midi|
		^this.detectIndex { |n| (n.midi >= midi) && (n.degree == degree) };
	}

	/****************************************************************************************/

	degreeUnderMIDI { |degree, midi|
		^this.detectLastIndex { |n| (n.midi <= midi) && (n.degree == degree) };
	}

	/****************************************************************************************/

	degreeOverFreq { |degree, freq|
		^this.detectIndex { |n| (n.freq >= freq) && (n.degree == degree) };
	}

	/****************************************************************************************/

	degreeUnderFreq { |degree, freq|
		^this.detectLastIndex { |n| (n.freq <= freq) && (n.degree == degree) };
	}

	/****************************************************************************************/

	nameOverMIDI { |name, midi|
		^this.detectIndex { |n| (n.midi >= midi) && (n.name(withOctave: false) == name) };
	}

	/****************************************************************************************/

	nameUnderMIDI { |name, midi|
		^this.detectLastIndex { |n| (n.midi <= midi) && (n.name(withOctave: false) == name) };
	}

	/****************************************************************************************/

	nameOverFreq { |name, freq|
		^this.detectIndex { |n| (n.freq >= freq) && (n.name(withOctave: false) == name) };
	}

	/****************************************************************************************/

	nameUnderFreq { |name, freq|
		^this.detectLastIndex { |n| (n.freq <= freq) && (n.name(withOctave: false) == name) };
	}

	/****************************************************************************************/

	degreeInOctave { |degree, octave|
		^this.detectIndex { |n| (n.degree == degree) && (n.octave == octave) };
	}

	/****************************************************************************************/

	indicesOfDegree { |degree|
		^this.notes.selectIndices { |n| n.degree == degree };
	}

	/****************************************************************************************/

	indicesOfName { |name|
		^this.notes.selectIndices { |n| n.name(withOctave: false) == name };
	}

	/****************************************************************************************/

	indexOfName { |name| ^this.detectIndex { |n| n.name == name } }

	/****************************************************************************************/
	/****************************************************************************************/

	pyramid { |patternType|
		var temp = this.notes.pyramid(patternType);
		var newR = Array(temp.size);

		temp.do { |n| newR.add(n.copy) };

		^this.species.with(this.meSymbol, *newR);
	}

	/****************************************************************************************/
	/****************************************************************************************/

	== { |aMENoteRange|
		case
		{ this === aMENoteRange                } { ^true  }
		{ this.size != aMENoteRange.size       } { ^false }
		{ this.species != aMENoteRange.species } { ^false }
		{
			this.do { |n, i|
				if (n != aMENoteRange[i]) { ^false };
			};
			^true;
		}
	}

	hash {
		var hash;

		hash = this.species.hash;
		this.do { |n| hash.bitXor(n.hash) };

		^hash;
	}
}
