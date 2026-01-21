/*********************************************************************************************
* MusicEngine - A dynamic chord library for SuperCollider   								 *
* Copyright (C) 2025 Siriil									    							 *
* Licensed under GPLv3. See LICENSE file for details.			    						 *
*********************************************************************************************/

MERange {

	*initClass { ^this }

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

		dataArr.add(["Rt", 0, 0]);

		intervalsArr.do { |i|
			var temp = Array.new(3);

			temp.add(i);
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
		var tempM, tempL, tempI, range = Array.new();

		MEDebug.log(thisMethod, 1, [midiNotesArr, noteLettersArr, intervalsArr]);

		#tempM, tempL, tempI = this.wrapAndExtend(
			midiNotesArr,
			noteLettersArr,
			intervalsArr
		);

		tempM.do { |m, i|
			range = range.add(MENote(tempL[i], m, tempI[i], validate));
		};

		^range;
	}

	/****************************************************************************************/

	*getRange { |symbol, validate = false|
		var tempM, tempL, tempI, tempR;

		MEDebug.log(thisMethod, 1, [symbol]);

		#tempM, tempL, tempI = this.getOffsets(symbol.intervals);

		MEMIDIValidators.midiOffsetArrayIsValid(tempM, diatonic: false);

		tempR = MEMIDINote.getOffsetFromName(symbol.root, validate);
		tempM = MEMIDINote.transposeMidiOffset(tempM, tempR, validate);
		tempL = MENoteName.getNoteLetters(tempL, symbol.root[0].asString, validate);

		^this.getMENotes(tempM, tempL, tempI, validate);
	}
}