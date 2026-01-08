// Run debugger
(
MEDebug.debug = true;
MEDebug.scope = 1;
MEDebug.validate = false;
MEDebug.showData = true; // Scope 1
~r = MENoteRange("Abm");
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

// Open unit tests GUI
UnitTest.gui;