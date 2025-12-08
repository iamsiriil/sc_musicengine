/*********************************************************************************************
* MusicEngine - A dynamic chord library for SuperCollider   								 *
* Copyright (C) 2025 Siriil									    							 *
* Licensed under GPLv3. See LICENSE file for details.			    						 *
*********************************************************************************************/

MERanges : MECore {
	classvar letterOffsets;
	classvar midiOffsets;
	classvar intervals;
	classvar midiRoot;

	*initClass {}

	/****************************************************************************************/

	sortAndSplit { |dataArray|
		midiOffsets   = dataArray.collect { |n| n[1] };
		letterOffsets = Array.new(dataArray.size);
		intervals     = Array.new(dataArray.size);


		midiOffsets.sort;

		midiOffsets.do { |n, i|

			dataArray.do { |a|

				if (a[1] == n) {
					letterOffsets.add(a[2]);
					intervals.add(a[0]);
				};
			};
		};

		MEDebug.log("MERanges", "*sortAndSplit", "\nin:  %\nout: midiOffsets: %\nout: letterOffsets: %\nout: intervals: %\n".format(dataArray, midiOffsets, letterOffsets, intervals));
	}

	/****************************************************************************************/

	getOffsets { |intervals|
		var arr = Array.new(intervals.size + 1);


		arr.add(["Rt", 0, 0]);

		intervals.do { |d|
			var temp = Array.new(3);

			temp.add(d);
			temp.add(MEMIDINotes.getOffsetFromInterval(d, validate: false));
			temp.add(MENoteName.getOffsetFromInterval(d, validate: false));

			arr.add(temp);
		};

		MEDebug.log("MERanges", "*getOffsets", "\nin:  %;\nout: %\n".format(intervals, arr));

		this.sortAndSplit(arr);
	}

	/****************************************************************************************/

	wrapFirstOctave { |midiNotesArr, noteLettersArr, intervalsArr|
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

	extendMidiRange { |midiNotesArr|
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

	wrapAndExtend { |midiNotesArr, noteLettersArr, intervalsArr|
		var tempM, tempL, tempI;

		#tempM, tempL, tempI = this.wrapFirstOctave(
			midiNotesArr,
			noteLettersArr,
			intervalsArr
		);

		tempM = this.extendMidiRange(tempM);
		tempL = tempL.wrapExtend(tempM.size);
		tempI = tempI.wrapExtend(tempM.size);

		MEDebug.log("MERanges", "*wrapAndExtend", "\nin:  %, %, %\nout: %\nout: %\nout: %\n".format(midiNotesArr, noteLettersArr, intervalsArr, tempM, tempL, tempI));

		^[tempM, tempL, tempI];
	}

	/****************************************************************************************/

	getMENotes { |midiNotesArr, noteLettersArr, intervalsArr|
		var noteRange = Array.new(midiNotesArr.size * 5);

		MEDebug.log("MERanges", "*getMENotes");

		midiNotesArr.do { |m, i|

			noteRange.add(
				MENote(
					noteLetter: noteLettersArr[i],
					midiNote:   m,
					degree:     intervalsArr[i],
					validate:   false
				);
			);
		};
		^noteRange;
	}

	/****************************************************************************************/

	getRange { |symbol|
		var tempM, tempL, tempD;

		MEDebug.log("MERanges", "*getRange", "\nin:  %\n".format(symbol));

		this.getOffsets(symbol.degrees);

		MEMIDIValidators.midiOffsetArrayIsValid(midiOffsets, diatonic: false);

		midiRoot = MEMIDINotes.getOffsetFromName(symbol.root, validate: false);
		tempM    = MEMIDINotes.transposeMidiOffset(midiOffsets, midiRoot, validate: false);
		tempL    = MENoteName.getNoteLetters(letterOffsets, symbol.root[0], validate: false);

		#tempM, tempL, tempD = this.wrapAndExtend(
			tempM,
			tempL,
			intervals
		);

		^this.getMENotes(tempM, tempL, tempD);
	}
}