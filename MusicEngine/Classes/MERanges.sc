/*********************************************************************************************
* MusicEngine - A dynamic chord library for SuperCollider   								 *
* Copyright (C) 2025 Siriil									    							 *
* Licensed under GPLv3. See LICENSE file for details.			    						 *
*********************************************************************************************/

MERanges {

	*initClass { ^this }

	/****************************************************************************************/

	*sortAndSplit { |dataArray|
		var tempM = dataArray.collect { |n| n[1] };
		var tempL = Array.new(dataArray.size);
		var tempI = Array.new(dataArray.size);

		MEDebug.log(thisMethod, 1, [dataArray]);

		tempM.sort;

		tempM.do { |n, i|

			dataArray.do { |a|

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
		var arr = Array.new(intervalsArr.size + 1);

		MEDebug.log(thisMethod, 1, [intervalsArr]);

		arr.add(["Rt", 0, 0]);

		intervalsArr.do { |i|
			var temp = Array.new(3);

			temp.add(i);
			temp.add(MEMIDINotes.getOffsetFromInterval(i, validate: false));
			temp.add(MENoteName.getOffsetFromInterval(i, validate: false));

			arr.add(temp);
		};
		^this.sortAndSplit(arr);
	}

	/****************************************************************************************/

	*wrapFirstOctave { |midiNotesArr, noteLettersArr, intervalsArr|
		var tempM, tempL, tempI;

		MEDebug.log(thisMethod, 1, [midiNotesArr, noteLettersArr, intervalsArr]);

		tempM = midiNotesArr.copy;
		tempL = noteLettersArr.copy;
		tempI = intervalsArr.copy;

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

			range = range.add(
				MENote(
					noteLetter: tempL[i],
					midiNote:   m,
					degree:     tempI[i],
					validate:   validate
				);
			);
		};
		^range;
	}

	/****************************************************************************************/

	*getRange { |symbol, validate = false|
		var tempM, tempL, tempI, tempR;

		MEDebug.log(thisMethod, 1, [symbol]);

		#tempM, tempL, tempI = this.getOffsets(symbol.intervals);

		MEMIDIValidators.midiOffsetArrayIsValid(tempM, diatonic: false);

		tempR = MEMIDINotes.getOffsetFromName(symbol.root, validate);
		tempM = MEMIDINotes.transposeMidiOffset(tempM, tempR, validate);
		tempL = MENoteName.getNoteLetters(tempL, symbol.root[0].asString, validate);

		^this.getMENotes(tempM, tempL, tempI, validate);
	}
}