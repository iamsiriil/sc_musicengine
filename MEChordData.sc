MEChordData {
	classvar chords;

	*initClass {
		chords = Dictionary[
			\d3d5 -> [0, 2, 6]
		];
	}

	getDict {
		^chords
	}
}
