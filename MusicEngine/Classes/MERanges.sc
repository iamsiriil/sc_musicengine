/*********************************************************************************************
* MusicEngine - A dynamic chord library for SuperCollider   								 *
* Copyright (C) 2025 Siriil									    							 *
* Licensed under GPLv3. See LICENSE file for details.			    						 *
*********************************************************************************************/

MERanges : MECore {
	classvar letterOffsets;
	classvar midiOffsets;
	classvar intervals;
	classvar rootMidi;

	*initClass {}

	/****************************************************************************************/

	sortAndSplit { |arr|
		midiOffsets   = arr.collect { |n| n[1] };
		letterOffsets = Array.new(arr.size);
		intervals     = Array.new(arr.size);

		MEDebug.log("MERangeTools", "*sortAndSplit");

		midiOffsets.sort;

		midiOffsets.do { |n, i|

			arr.do { |a|

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

		MEDebug.log("MERangeTools", "*getOffsets");

		arr.add(["Rt", 0, 0]);

		intervals.do { |d|
			var temp = Array.new(3);

			temp.add(d);
			temp.add(MEMIDINotes.getOffsetFromInterval(d));
			temp.add(MENoteName.getOffsetFromInterval(d));

			arr.add(temp);
		};

		this.sortAndSplit(arr);
	}


	/****************************************************************************************/

	wrapFirstOctave { |midi, letters, degrees|

		MEDebug.log("MERangeTools", "*wrapFirstOctave");

		if (midi[0] < 0) {

			midi = midi + 12;
		};

		midi.do { |m, i|

			if (m > 11) {

				midi[i] = m - 12;
				letters = letters.rotate(1);
				degrees = degrees.rotate(1);
			};
		};

		midi.sort;

		^[midi, letters, degrees]
	}

	/****************************************************************************************/

	extendMidiRange { |midi|
		var arr = Array.new(midi.size * 11);

		MEDebug.log("MERangeTools", "*extendMidiRange");

		midi.do { |m|

			while { m <= 127 } {

				arr.add(m);
				m = m + 12;
			};
		};

		^arr.sort;
	}

	/****************************************************************************************/

	wrapAndExtend { |midi, letters, degrees|
		var tempM, tempL, tempD;

		MEDebug.log("MERangeTools", "*wrapAndExtend");

		#tempM, tempL, tempD = this.wrapFirstOctave(midi, letters, degrees);

		tempM = this.extendMidiRange(tempM);
		tempL = tempL.wrapExtend(tempM.size);
		tempD = tempD.wrapExtend(tempM.size);

		^[tempM, tempL, tempD];
	}

	/****************************************************************************************/

	getMENotes { |midi, letters, degrees|
		var arr = Array.new(midi.size * 5);
		var temp;

		MEDebug.log("MERangeTools", "*getMENotes");

		midi.do { |m, i|

			temp = MENote(letter: letters[i], midi: m, degree: degrees[i]);
			arr.add(temp);
		};

		^arr;
	}

	/****************************************************************************************/

	// To be abstrackted in an dedicated error class
	checkEnharmonics { |midiOffsets|
		var arrSize = midiOffsets.size;
		var setSize = midiOffsets.asSet.size;

		MEDebug.log("MERangeTools", "*checkEnharmonics");

		if (setSize < arrSize) {
			Error("Enharmonics are not allowed.\n").throw;
		};
	}

	/****************************************************************************************/

	getRange { |symbol|
		var midiTemp, letterTemp, degreeTemp;

		MEDebug.log("MERangeTools", "*getRange");

		this.getOffsets(symbol.degrees);
		this.checkEnharmonics(midiOffsets);

		rootMidi = MEMIDINotes.getOffsetFromName(symbol.root);

		midiTemp = MEMIDINotes.transposeMidiOffset(midiOffsets, rootMidi);

		letterTemp = MENoteName.getNoteNames(letterOffsets, symbol.root[0]);

		#midiTemp, letterTemp, degreeTemp = this.wrapAndExtend(
			midiTemp,
			letterTemp,
			intervals
		);

		^this.getMENotes(midiTemp, letterTemp, degreeTemp);
	}
}