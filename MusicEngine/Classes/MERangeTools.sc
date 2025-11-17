MERangeTools : METools {
	classvar midioffsets;
	classvar nameoffsets;
	classvar intervals;
	classvar rootMidi;

	*initClass {}

	/****************************************************************************************/

	sortAndSplit { |arr|
		midioffsets = arr.collect { |n| n[1] };
		nameoffsets = Array.new(arr.size);
		intervals   = Array.new(arr.size);

		"MERangeTools: sortAndSplit".postln;

		midioffsets.sort;

		midioffsets.do { |n, i|

			arr.do { |a|

				if (a[1] == n) {
					nameoffsets.add(a[2]);
					intervals.add(a[0]);
				}
			}
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

		^this.sortAndSplit(arr);
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
		var temp, octave;

		"MERangeTools: getMENotes".postln;

		midi.do { |m, i|

			octave = MEOctaves.getOctave(m, names[i]);
			temp   = MENotes.new(m, names[i], degrees[i], octave);
			arr.add(temp);
		};

		^arr;
	}

	/****************************************************************************************/

	getRange { |symbol|
		var midiTemp, nameTemp, degreeTemp;

		"MERangeTools: getRange".postln;

		rootMidi   = MEMidiNotes.getOffsetFromName(symbol.root).postln;

		this.getOffsets(symbol.degrees);

		midiTemp = MEMidiNotes.transposeMidiOffset(
			midioffsets,
			rootMidi
		);

		nameTemp = MENoteNames.getNoteNames(
			nameoffsets,
			symbol.root[0]
		);

		nameTemp.do { |n, i|
			nameTemp[i] = MENoteNames.resolveAccidental(midiTemp[i], n);
		};

		#midiTemp, nameTemp, degreeTemp = this.wrapAndExtend(
			midiTemp,
			nameTemp,
			intervals
		);

		^this.getMENotes(midiTemp, nameTemp, degreeTemp);
	}
}