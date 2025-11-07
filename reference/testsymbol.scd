/*****************************************************************************************
* MUSIC ENGINE / Reference Tests                                                         *
*****************************************************************************************/
// TEST ROOT

// Test root for valid notes
(
var regex = "^[^A-G]"; // Fails with true
var symbol = "X1m3P5m7A11";
regex.matchRegexp(symbol).postln;
symbol.findRegexp(regex).postln;
)

// Test root for more than one accidentals
(
var regex = "#+#|b+b"; // Fails with true
var symbol = "G###1m3P5m7A11";
regex.matchRegexp(symbol).postln;
symbol.findRegexp(regex).postln;
)

/****************************************************************************************/
// TEST DEGREES

// Test for single 1
(
var regex = "[dmPMA]1(?!\\d)"; // Fails with true
var symbol = "GP1m3P5m7A11";
regex.matchRegexp(symbol).postln;
symbol.findRegexp(regex).postln;
)

// Test for 8 and 15
(
var regex = "[dmPMA]8|[dmPMA]15"; // Fails with true
var symbol = "G#m3P8m15";
regex.matchRegexp(symbol).postln;
symbol.findRegexp(regex).postln;
)

// Test degrees 16 and above
(
var regex = "1[6-9]|[2-9][0-9]|[0-9]{3,4}"; // Fails with true
var symbol = "G#m3P8m3";
regex.matchRegexp(symbol).postln;
symbol.findRegexp(regex).postln;
)

// Test for characters other than d, m, P, M and A
(
var regex = "[a-ce-ln-oq-zA-CE-LN-OQ-Z]+"; // Fails with true
var symbol = "m3P8x3";
regex.matchRegexp(symbol).postln;
symbol.findRegexp(regex).postln;
)

// Test for d2 or d9
(
var regex = "d[29]"; // Fails with true
var symbol = "G#d2m3P5m7";
regex.matchRegexp(symbol).postln;
symbol.findRegexp(regex).postln;
)

// Test for A7 or A14
(
var regex = "A7|A14"; // Fails with true
var symbol = "G#d2m3P5A7m14";
regex.matchRegexp(symbol).postln;
symbol.findRegexp(regex).postln;
)

// Test for Perfect 2,3,6,7,9,10,13 or 14
(
var regex = "P[23679]|P1[034]"; // Fails with true
var symbol = "G#P3P5A7P14";
regex.matchRegexp(symbol).postln;
symbol.findRegexp(regex).postln;
)

// Test for Major/minor 4,5,11 or 12
(
var regex = "[mM][45]|[mM]1[12]"; // Fails with true
var symbol = "G#M3M4P5M12m13";
regex.matchRegexp(symbol).postln;
symbol.findRegexp(regex).postln;
)

/****************************************************************************************/
// TEST MODIFIERS AND DEGREE NUMBERS

// Test for modifier without degree
(
var regex = "[dmPMA](?!\\d{1,2})"; // Fails with true
var symbol = "G#mPM4";
regex.matchRegexp(symbol).postln;
symbol.findRegexp(regex).postln;
)

// Test for degree without modifier
(
var regex  = "[dmPMA](?:[2-7]|9|1[0-4])"; // <-- Issue here
var symbol = "3P10";
var array  = symbol.findRegexp(regex).collect { |i| i[1] }.postln;
array.do { |r|
	symbol = symbol.replace(r, "");
};
symbol;
)

/****************************************************************************************/
// TEST SIZE

// Check for more than 11 degrees
(
var regex = "[dmMPA]\\d{1,2}";
var symbol = "M3P4A4P5m6M6m7M7M9";
if (symbol.findRegexp(regex).size > 11) {
	"No more than 11 degrees in addition to the root".postln;
};
symbol.findRegexp(regex).size;
)

/****************************************************************************************/
// DIFFERENT APPROACH
(
var r0 = "^[A-G][#|b]*";
var r1 = "[mMA][29]|";
var r2 = "[dmMA][36]|[dmMA]1[03]|";
var r3 = "[dPA][45]|[dPA]1[12]|";
var r4 = "[dmM]7|[dmM]14";

var regex  = "%%%%".format(r1, r2, r3, r4); // Empty array indicates invalid degree

var symbol = "XXXm2M345dp5lA723M2m2M4P9h7h16t7h5";

var error  = Array.new(symbol.size);
var test   = symbol;
var symbolArray, root;

// 1. Get root and remove it from symbol
if (r0.matchRegexp(symbol)) {
	root   = symbol.findRegexp(r0)[0][1].postln;
	symbol = symbol.replace(root, "").postln;
} {
	"No root detected".postln; // Throw error
};

// 2. Test root
if (root.notNil) { // If statement ncluded here for the purpose of drafting
	"[A-G][#b]{2}".matchRegexp(root).postln; // True is root has more than one accidental
};

// 3. Test symbol
// 3.1 Separate all modifier/interval pairs
symbolArray = symbol.findRegexp("[a-zA-Z][0-9]|[a-zA-Z]1[0-5]").collect { |i| i[1] }.postln;

// 3.2 Replace every modifier/interval pair with space
// What remains are invalid numbers and letters
symbolArray.do { |s|
	test = test.replace(s, " ");
};
test = test.split($ ).select { |i| i != ""}.postln;

// 3.3 Check symbolArray size. Max 11, min 0 (Root without symbol is allowed -> Maj triad)
if (symbolArray.size > 11) { "Symbol array cannot have more than 11 intervals.".postln };

// 3.4 Test each interval agains regex test and add to error array all intervals
// that do not match. When testing, try matching what we expect to have, and not
// all that is not allowed.
symbolArray.do { |s, i|

	if (s.findRegexp(regex).isEmpty) {

		error.add(s);
	};

};
symbolArray = symbolArray.select { |i| error.includes(i).not };
"symbolArray: %".format(symbolArray).postln;

// Prompt a nice little warning message and throw an error
~verb = if (error.size > 1) { ["Are", "", "intervals."] } { ["Is", "a ", "interval."] }; // Why not?!
"%: % not %valid %".format(error.join(", "), ~verb[0], ~verb[1], ~verb[2]).postln; // Throw error
)