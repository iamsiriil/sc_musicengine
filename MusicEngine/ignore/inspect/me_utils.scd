/*********************************************************************************************
* MusicEngine - A dynamic music library for SuperCollider   								 *
* Copyright (C) 2025 Siriil									    							 *
* Licensed under GPLv3. See LICENSE file for details.			    						 *
*********************************************************************************************/

// INSPECT MUSICENGINE DATA
(
~inspectRange = { |range, from = 4, to = 8|

	"notes: ".padRight(8).post;
	range.trimO(from, to).postln;

	"midi: ".padRight(8).post;
	range.trimO(from, to).midi.postln;

	"freq: ".padRight(8).post;
	range.trimO(from, to).freq.postln;

	"name: ".padRight(8).post;
	range.trimO(from, to).names(\ansi).postln;

	"sol: ".padRight(8).post;
	range.trimO(from, to).sol(\ansi).postln;

	"degree: ".padRight(8).post;
	range.trimO(from, to).degrees.postln;


	"\nnotes.size: ".padRight(8).post;
	range.trimO(from, to).size.postln;

	"midi.size: ".padRight(8).post;
	range.trimO(from, to).midi.size.postln;

	"freq.size: ".padRight(8).post;
	range.trimO(from, to).freq.size.postln;

	"name.size: ".padRight(8).post;
	range.trimO(from, to).names(\ansi).size.postln;

	"sol.size: ".padRight(8).post;
	range.trimO(from, to).sol(\ansi).size.postln;

	"degree.size: ".padRight(8).post;
	range.trimO(from, to).degrees.size.postln;
};

/********************************************************************************************/

~inspectNotes = { |range, from = 4, to = 8|
	var size = range.trimO(from, to).size - 1;
	var i = (0..size).choose;

	"midi: ".padRight(12).post;
	range.trimO(from, to)[i].midi.postln;

	"freq: ".padRight(12).post;
	range.trimO(from, to)[i].freq.postln;

	"name: ".padRight(12).post;
	range.trimO(from, to)[i].name(\ansi).postln;

	"solfege: ".padRight(12).post;
	range.trimO(from, to)[i].sol.postln;

	"degree: ".padRight(12).post;
	range.trimO(from, to)[i].degree.postln;

	"octave: ".padRight(12).post;
	range.trimO(from, to)[i].octave.postln;

	"sign: ".padRight(12).post;
	range.trimO(from, to)[i].sign.postln;
};

/********************************************************************************************/

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

/********************************************************************************************/

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