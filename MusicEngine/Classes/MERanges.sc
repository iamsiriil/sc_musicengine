/*********************************************************************************************
* MusicEngine - A dynamic chord library for SuperCollider   								 *
* Copyright (C) 2025 Siriil									    							 *
* Licensed under GPLv3. See LICENSE file for details.			    						 *
*********************************************************************************************/

MERanges : MECore {

	*initClass {}

	/****************************************************************************************/

	*sortAndSplit { |dataArray|
		var tempM = dataArray.collect { |n| n[1] };
		var tempL = Array.new(dataArray.size);
		var tempI = Array.new(dataArray.size);

		tempM.sort;

		tempM.do { |n, i|

			dataArray.do { |a|

				if (a[1] == n) {
					tempL.add(a[2]);
					tempI.add(a[0]);
				};
			};
		};

		MEDebug.log("MERanges", "*sortAndSplit", "\nin:  %\nout: midiOffsets: %\nout: letterOffsets: %\nout: intervals: %\n".format(dataArray, tempM, tempL, tempI));

		^[tempM, tempL, tempI];
	}

	/****************************************************************************************/

	*getOffsets { |intervalsArr|
		var arr = Array.new(intervalsArr.size + 1);

		arr.add(["Rt", 0, 0]);

		intervalsArr.do { |i|
			var temp = Array.new(3);

			temp.add(i);
			temp.add(MEMIDINotes.getOffsetFromInterval(i, validate: false));
			temp.add(MENoteName.getOffsetFromInterval(i, validate: false));

			arr.add(temp);
		};

		MEDebug.log("MERanges", "*getOffsets", "\nin:  %;\nout: %\n".format(intervalsArr, arr));

		^MERanges.sortAndSplit(arr);
	}

	/****************************************************************************************/

	*wrapFirstOctave { |midiNotesArr, noteLettersArr, intervalsArr|
		var tempM, tempL, tempI;

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

		MEDebug.log("MERanges", "*wrapFirstOctave", "\nin:  %, %, %\nout: %, %, %\n".format(tempM, tempL, tempI, midiNotesArr, noteLettersArr, intervalsArr));

		^[midiNotesArr, noteLettersArr, intervalsArr]
	}

	/****************************************************************************************/

	*extendMidiRange { |midiNotesArr|
		var midiRange = Array.new(midiNotesArr.size * 11);


		midiNotesArr.do { |m|

			while { m <= 127 } {

				midiRange.add(m);
				m = m + 12;
			};
		};
		midiRange.sort;

		MEDebug.log("MERanges", "*extendMidiRange", "\nin:  %\nout: %\n".format(midiNotesArr, midiRange));

		^midiRange;
	}

	/****************************************************************************************/

	*wrapAndExtend { |midiNotesArr, noteLettersArr, intervalsArr|
		var tempM, tempL, tempI;

		#tempM, tempL, tempI = MERanges.wrapFirstOctave(
			midiNotesArr,
			noteLettersArr,
			intervalsArr
		);

		tempM = MERanges.extendMidiRange(tempM);
		tempL = tempL.wrapExtend(tempM.size);
		tempI = tempI.wrapExtend(tempM.size);

		MEDebug.log("MERanges", "*wrapAndExtend", "\nin:  %, %, %\nout: %\nout: %\nout: %\n".format(midiNotesArr, noteLettersArr, intervalsArr, tempM, tempL, tempI));

		^[tempM, tempL, tempI];
	}

	/****************************************************************************************/

	*getMENotes { |midiNotesArr, noteLettersArr, intervalsArr|
		var tempM, tempL, tempI, range = List.new();

		MEDebug.log("MERanges", "*getMENotes");

		#tempM, tempL, tempI = MERanges.wrapAndExtend(
			midiNotesArr,
			noteLettersArr,
			intervalsArr
		);

		tempM.do { |m, i|

			range.add(
				MENote(
					noteLetter: tempL[i],
					midiNote:   m,
					degree:     tempI[i],
					validate:   false
				);
			);
		};
		^range;
	}

	/****************************************************************************************/

	*getRange { |symbol|
		var tempM, tempL, tempI, tempR;

		MEDebug.log("MERanges", "*getRange", "\nin:  %\n".format(symbol));

		#tempM, tempL, tempI = MERanges.getOffsets(symbol.intervals);

		MEMIDIValidators.midiOffsetArrayIsValid(tempM, diatonic: false);

		tempR = MEMIDINotes.getOffsetFromName(symbol.root, validate: false);
		tempM = MEMIDINotes.transposeMidiOffset(tempM, tempR, validate: false);
		tempL = MENoteName.getNoteLetters(tempL, symbol.root[0], validate: false);

		^MERanges.getMENotes(tempM, tempL, tempI);
	}
}