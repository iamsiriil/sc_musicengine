/****************************************************************************************/
// Trimming range
(
~range1 = MENoteRange("Eb-9");

~range1[20..40].postln;
~range1[20..].postln;
~range1[..20].postln;

~range1.trimF(500, 2000).freq.postln;   // trim by frequency values
~range1.trimM(40, 80).midi.postln;      // trim by MIDI values
~range1.trimO(4, 6).names.postln;       // trim by octave number
~range1[30..50].trimD(\P1, \P1).postln;
)

/****************************************************************************************/
// Filtering
(
// Since most tools return a MENoteRange object, methods can be chained
~r1 = ~range1[10..50].trimF(50, 2000) // trim by frequency
.trimD(\m3, \P1)                      // then by degree
.filterD(\P1)                         // remove all notes with P1 degree
.filterN('Gb2', 'F4', 'Bb5');         // remove notes

~r1.freq.postln; // return array with midi values for the remaining notes
~r1.midi.postln; // return array with frequency values for the remaining notes
)

/****************************************************************************************/
// Two octave span
(
~range2 = MENoteRange("Gb13");
~r2 = ~range2[20..50].trimD(\M3, \P1).span; // span still a bit buggy. requires revision.
)

/****************************************************************************************/
// Handle data dictionary
(
~range3 = MENoteRange("Gb13");

~c1 = ~range3[20..30].setValues(\dur, 1);   // set single value to all notes in range
~c1 = ~range3[20..30].setValues(\amp, 0.5);
~c1 = ~range3[20..30].setFunc(\legato, { rrand(0.1, 0.9) }); // set values using function

~c1.getKeys.postln; // current keys storing data
~c1.getValues(\dur).postln;    // return array of values
~c1.getValues(\amp).postln;
~c1.getValues(\legato).postln;

// convineance method to convert data from range into Pseq objects
~s1 = ~c1.asPseq(\midi, inf, 0).asStream;
~s2 = ~c1.asPseq(\freq, inf, 0).asStream;
~s3 = ~c1.asPseq(\amp, inf, 0).asStream;
~s4 = ~c1.asPseq(\dur, inf, 0).asStream;
~s5 = ~c1.asPseq(\legato, inf, 0).asStream;
)

(
~c1.size.do {
	"\\midi: ".post;
	~s1.next.postln;
	"\\freq: ".post;
	~s2.next.postln;
	"\\amp: ".post;
	~s3.next.postln;
	"\\dur: ".post;
	~s4.next.postln;
	"\\legato: ".post;
	~s5.next.postln;
	"".postln;
};
~s1.reset;
~s2.reset;
~s3.reset;
~s4.reset;
~s5.reset;
)

// Methods for setting data should return a deep copy to prevent contaminating
// the original instance of the range
~range3.getValues(\dur)

/****************************************************************************************/
// Symbol data
(
~range3.symbol.postln;        // symbol with root
~range3.symbol(false).postln; // symbol without root
~range3.alias.postln;         // alias with root
~range3.alias(false).postln;  // alias without root
~range3.root.postln;          // root note
~range3.root(true).postln;    // root MIDI offset
~range3.intervals.postln;        // interval symbols
~range3.intervals(false).postln; // float representation of intervals
~range3.symbolObj.postln         // symbol object stored in MENoteRange
)