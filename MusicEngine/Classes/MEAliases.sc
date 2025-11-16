MEAliases {
	classvar aliases;

	*initClass {

		aliases = Dictionary[
			// Power chords
			"P5"   -> [Set["5"], [0, 7], [0, 4]],

			// Triads,
			"d3d5" -> [Set["Italian", "Ita", "It"],             [0, 2, 6], [0, 2, 4]],
			"m3d5" -> [Set["Dim", "Dim5", "º5", "o5"],          [0, 3, 6], [0, 2, 4]],
			"m3P5" -> [Set["m", "min"],                         [0, 3, 7], [0, 2, 4]],
			"M3P5" -> [Set["", "M", "Maj", "Neapolitan", "Na"], [0, 4, 7], [0, 2, 4]],
			"M3A5" -> [Set["Aug5", "Aug", "+5"],                [0, 4, 8], [0, 2, 4]],

			// Seventh chords

			// Nineth chords
			"M9M3P5m7" -> [Set["9"], [0, 2, 4, 7, 10], [0, 1, 2, 4, 6]] // Degrees need to be sorted, or provide array in correct order.
			// Eleventh chords

			// Thirteenth chords

			// Suspended chords

			// Augmented sixth chords
		];
	}

	/****************************************************************************************/

	*checkAliases { |symbol|

		"checkAliases".postln;

		aliases.keysValuesDo { |k, v|

			if (v[0].includes(symbol)) {
				^k;
			};
		};

		^nil;
	}

}