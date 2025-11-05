// Test root for more than one accidentals
(
var regex = "#+#|b+b";
var symbol = "G1m3P5m7A11";
regex.matchRegexp(symbol).postln;
symbol.findRegexp(regex).postln;
)

// Test for single 1
(
var regex = "[dmPMA]1(?!\\d)";
var symbol = "GP1m3P5m7A11";
regex.matchRegexp(symbol).postln;
symbol.findRegexp(regex).postln;
)

// Test for 8 and 15
(
var regex = "[dmPMA]8|[dmPMA]15";
var symbol = "G#m3P8m15";
regex.matchRegexp(symbol).postln;
symbol.findRegexp(regex).postln;
)

// Test for d2 or d9
(
var regex = "d[29]";
var symbol = "G#d2m3P5m7";
regex.matchRegexp(symbol).postln;
symbol.findRegexp(regex).postln;
)

// Test for A7 or A14
(
var regex = "A7|A14";
var symbol = "G#d2m3P5A7m14";
regex.matchRegexp(symbol).postln;
symbol.findRegexp(regex).postln;
)

// Test for Perfect 2,3,6,7,9,10,13 or 14
(
var regex = "P[23679]|P1[034]";
var symbol = "G#Pm3P5A7P14";
regex.matchRegexp(symbol).postln;
symbol.findRegexp(regex).postln;
)

// Test for Major/minor 4,5,11 or 12
(
var regex = "[mM][45]|[mM]1[12]";
var symbol = "G#M3M4P5M12m13";
regex.matchRegexp(symbol).postln;
symbol.findRegexp(regex).postln;
)

// Test for modifier without degree
(
var regex = "[dmPMA](?!\\d{1,2})";
var symbol = "G#mPM4";
regex.matchRegexp(symbol).postln;
symbol.findRegexp(regex).postln;
)

// Test for degree without modifier
(
var regex = "(?<![dmPMA])\\d{1,2}";
var symbol = "G#3P57";
regex.matchRegexp(symbol).postln;
symbol.findRegexp(regex).postln;
)