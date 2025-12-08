// Run debugger
(
MEDebug.debug = true;
MEDebug.count = 0;
~r = MENoteRange("F#m3P5M7M9");
)

// Inspect MENoteRange
~r.postln;

// Inspect MENote, MENoteName and MEAccidental
(
~r.notes[0].postln;
~r.notes[0].nameObj.postln;
~r.notes[0].accidentalObj.postln;
)

// Inspect range of notes
~r.notes(4, 5).do { |n| n.postln };
