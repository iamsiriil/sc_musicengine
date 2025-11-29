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

		MEDebug.log("MERanges", "*sortAndSplit");

		midiOffsets.sort;

		midiOffsets.do { |n, i|

			dataArray.do { |a|

				if (a[1] == n) {
					letterOffsets.add(a[2]);
					intervals.add(a[0]);
				};
			};
		};
	}

	/****************************************************************************************/

	getOffsets { |intervals|
		var arr = Array.new(intervals.size + 1);

		MEDebug.log("MERanges", "*getOffsets");

		arr.add(["Rt", 0, 0]);

		intervals.do { |d|
			var temp = Array.new(3);

			temp.add(d);
			temp.add(MEMIDINotes.getOffsetFromInterval(d, validate: false));
			temp.add(MENoteName.getOffsetFromInterval(d, validate: false));

			arr.add(temp);
		};

		this.sortAndSplit(arr);
	}


	/****************************************************************************************/

	wrapFirstOctave { |midiNotesArr, noteLettersArr, intervalsArr|

		MEDebug.log("MERanges", "*wrapFirstOctave");

		if (midiNotesArr[0] < 0) {
			midiNotesArr = midiNotesArr + 12;
		};

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

	extendMidiRange { |midiNotesArr|
		var midiRange = Array.new(midiNotesArr.size * 11);

		MEDebug.log("MERanges", "*extendMidiRange");

		midiNotesArr.do { |m|

			while { m <= 127 } {

				midiRange.add(m);
				m = m + 12;
			};
		};
		^midiRange.sort;
	}

	/****************************************************************************************/

	wrapAndExtend { |midiNotesArr, noteLettersArr, intervalsArr|
		var tempM, tempL, tempI;

		MEDebug.log("MERanges", "*wrapAndExtend");

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

		MEDebug.log("MERanges", "*getRange");

		this.getOffsets(symbol.degrees);

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