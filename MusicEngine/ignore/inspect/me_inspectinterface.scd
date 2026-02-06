/****************************************************************************************/
// Trimming range
(
~range1 = MENoteRange("Eb-9");

~range1[20..40].postln;
~range1[20..].postln;
~range1[..20].postln;

~range1.trimF(500, 2000).freq.postln;
~range1.trimM(40, 80).midi.postln;
~range1.trimO(4, 6).names.postln;
~range1[30..50].trimD(\P1, \P1).postln;
)

/****************************************************************************************/
// Filtering
(
~r1 = ~range1[10..50].trimF(50, 2000).trimD(\m3, \P1).filterD(\P1).filterN('Gb2', 'F4', 'Bb5');
~r1.freq.postln;
~r1.midi.postln;
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

~c1 = ~range3[20..30].setValues(\dur, 1);
~c1 = ~range3[20..30].setValues(\amp, 0.5);
~c1 = ~range3[20..30].setFunc(\legato, { rrand(0.1, 0.9) });

~c1.getKeys.postln;
~c1.getValues(\dur).postln;
~c1.getValues(\amp).postln;
~c1.getValues(\legato).postln;

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
