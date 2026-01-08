/*****************************************************************************************
* MusicEngine - Inspect tools                                                            *
*****************************************************************************************/

// INSPECT MUSICENGINE DATA
(
~inspectRange = { |range, from = 4, to = 8|

	"notes: ".padRight(8).post;
	range.notes(from, to).postln;

	"midi: ".padRight(8).post;
	range.midi(from, to).postln;

	"freq: ".padRight(8).post;
	range.freq(from, to).postln;

	"name: ".padRight(8).post;
	range.names(from, to).postln;

	"sol: ".padRight(8).post;
	range.sol(from, to).postln;

	"degree: ".padRight(8).post;
	range.degrees(from, to).postln;


	"\nnotes.size: ".padRight(15).post;
	range.notes(from, to).size.postln;

	"midi.size: ".padRight(14).post;
	range.midi(from, to).size.postln;

	"freq.size: ".padRight(14).post;
	range.freq(from, to).size.postln;

	"name.size: ".padRight(14).post;
	range.names(from, to).size.postln;

	"sol.size: ".padRight(14).post;
	range.sol(from, to).size.postln;

	"degrees.size: ".padRight(14).post;
	range.degrees(from, to).size.postln;
};

/****************************************************************************************/

~inspectNotes = { |range, from = 4, to = 8|
	var size = range.notes(from, to).size - 1;
	var i = (0..size).choose;

	"midi: ".padRight(12).post;
	range.notes(from, to)[i].midi.postln;

	"freq: ".padRight(12).post;
	range.notes(from, to)[i].freq.postln;

	"name: ".padRight(12).post;
	range.notes(from, to)[i].name.postln;

	"solfege: ".padRight(12).post;
	range.notes(from, to)[i].sol.postln;

	"degree: ".padRight(12).post;
	range.notes(from, to)[i].degree.postln;

	"octave: ".padRight(12).post;
	range.notes(from, to)[i].octave.postln;

	"accidental: ".padRight(12).post;
	range.notes(from, to)[i].accidental.postln;
};

/****************************************************************************************/

SynthDef(\tone, { |freq = 440, amp = 0.3|
	var env = EnvGen.kr(Env(
		levels:  [0, 1, 0],
		times:   [0.01, 1],
		curve:   [4, -4]
	),
	doneAction: 2
	);
	var sig = Pulse.ar(freq) * amp;
	sig = sig * env;
	sig = FreeVerb.ar(sig);
	Out.ar(0, Pan2.ar(sig, 0));
}).add;

/****************************************************************************************/

~routine = { |range|

	Routine {

		range.do { |n|
			Synth(\tone, [\freq, n.freq, \amp, 0.3]);
			"% ".format(n.degree).padRight(4).post;
			"| %".format(n.name).postln;
			(1/2).wait;
		};
	};
};
)