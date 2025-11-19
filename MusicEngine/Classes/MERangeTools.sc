/*********************************************************************************************
* MusicEngine - A dynamic chord library for SuperCollider   								 *
* Copyright (C) 2025 Siriil									    							 *
* Licensed under GPLv3. See LICENSE file for details.			    						 *
*********************************************************************************************/

MERangeTools : METools {
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

		"MERangeTools: sortAndSplit".postln;

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

		"MERangeTools: getOffsets".postln;

		arr.add(["Rt", 0, 0]);

		intervals.do { |d|
			var temp = Array.new(3);

			temp.add(d);
			temp.add(MEMidiNotes.getOffsetFromInterval(d));
			temp.add(MENoteNames.getOffsetFromInterval(d));

			arr.add(temp);
		};

		this.sortAndSplit(arr);
	}


	/****************************************************************************************/

	wrapFirstOctave { |midi, names, degrees|

		"MERangeTools: wrapFirstOctave".postln;

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

		"MERangeTools: extendMidi".postln;

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

		"MERangeTools: wrapAndExtend".postln;

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

		"MERangeTools: getMENotes".postln;

		midi.do { |m, i|

			temp = MENotes(noteLetter: names[i], midi: m, degree: degrees[i]);
			arr.add(temp);
		};

		^arr;
	}

	/****************************************************************************************/

	checkEnharmonics { |midiOffsets|
		var arrSize = midiOffsets.size;
		var setSize = midiOffsets.asSet.size;

		"MERangeTools: checkEnharmonics".postln;

		if (setSize < arrSize) {
			Error("Enharmonics are not allowed.\n").throw;
		};
	}

	/****************************************************************************************/

	getRange { |symbol|
		var midiTemp, nameTemp, degreeTemp;

		"MERangeTools: getRange".postln;

		this.getOffsets(symbol.degrees);
		this.checkEnharmonics(midiOffsets);

		rootMidi = MEMidiNotes.getOffsetFromName(symbol.root);

		midiTemp = MEMidiNotes.transposeMidiOffset(midiOffsets, rootMidi);

		nameTemp = MENoteNames.getNoteNames(nameOffsets, symbol.root[0]);

		#midiTemp, nameTemp, degreeTemp = this.wrapAndExtend(
			midiTemp,
			nameTemp,
			intervals
		);

		^this.getMENotes(midiTemp, nameTemp, degreeTemp);
	}
}