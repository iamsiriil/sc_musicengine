// NOTE Class

MENote {
	classvar notes = #[0, 2, 4, 5, 7, 9, 11];
	classvar names = #['C', 'D', 'E', 'F', 'G', 'A', 'B'];

	var <>note         = nil;
	//var <>accidental   = nil; // Get dynamically from note name?
	var <>octave       = nil;
	var <>degree       = nil;
	var <>midi         = nil;
	var <>freq         = nil;
	var <>duration     = nil;
	var <>articulation = nil;

	*new { |note, octave = nil, midi = nil|
		^super.new.init(note, midi)
	}

	init { |newN, newM|
		note     = newN;
		midi     = newM;
	}

	/*_parseNote { |note|

	}*/

	accidental {

		if (this.note.size > 1) {
			^this.note[1..].asString;
		} {
			^nil;
		}
	}

	getOctave {}
}

