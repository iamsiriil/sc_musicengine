/*********************************************************************************************
* MusicEngine - A dynamic chord library for SuperCollider   								 *
* Copyright (C) 2025 Siriil									    							 *
* Licensed under GPLv3. See LICENSE file for details.			    						 *
*********************************************************************************************/

MERanges : MECore {
	classvar midiOffsets;
	classvar nameOffsets;
	classvar intervals;
	classvar rootMidi;

	*initClass {}

	/****************************************************************************************/

	sortAndSplit { |arr|
		midiOffsets = arr.collect { |n| n[1] };
		nameOffsets = Array.new(arr.size);
		intervals   = Array.new(arr.size);

		MEDebug.log("MERangeTools", "*sortAndSplit");

		midiOffsets.sort;

		midiOffsets.do { |n, i|

			arr.do { |a|

				if (a[1] == n) {
					nameOffsets.add(a[2]);
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

	wrapFirstOctave { |midi, names, degrees|

		MEDebug.log("MERangeTools", "*wrapFirstOctave");

		if (midi[0] < 0) {

			midi = midi + 12;
		};

		midi.do { |m, i|

			if (m > 11) {

				midi[i] = m - 12;
				names   = names.rotate(1);
				degrees = degrees.rotate(1);
			};
		};

		midi.sort;

		^[midi, names, degrees]
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

	wrapAndExtend { |midi, names, degrees|
		var tempM, tempN, tempD;

		MEDebug.log("MERangeTools", "*wrapAndExtend");

		#tempM, tempN, tempD = this.wrapFirstOctave(midi, names, degrees);

		tempM = this.extendMidiRange(tempM);
		tempN = tempN.wrapExtend(tempM.size);
		tempD = tempD.wrapExtend(tempM.size);

		^[tempM, tempN, tempD];
	}

	/****************************************************************************************/

	getMENotes { |midi, names, degrees|
		var arr = Array.new(midi.size * 5);
		var temp;

		MEDebug.log("MERangeTools", "*getMENotes");

		midi.do { |m, i|

			temp = MENote(noteLetter: names[i], midi: m, degree: degrees[i]);
			arr.add(temp);
		};

		^arr;
	}

	/****************************************************************************************/

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
		var midiTemp, nameTemp, degreeTemp;

		MEDebug.log("MERangeTools", "*getRange");

		this.getOffsets(symbol.degrees);
		this.checkEnharmonics(midiOffsets);

		rootMidi = MEMIDINotes.getOffsetFromName(symbol.root);

		midiTemp = MEMIDINotes.transposeMidiOffset(midiOffsets, rootMidi);

		nameTemp = MENoteName.getNoteNames(nameOffsets, symbol.root[0]);

		#midiTemp, nameTemp, degreeTemp = this.wrapAndExtend(
			midiTemp,
			nameTemp,
			intervals
		);

		^this.getMENotes(midiTemp, nameTemp, degreeTemp);
	}
}