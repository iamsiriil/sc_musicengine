/*********************************************************************************************
* MusicEngine - A dynamic chord library for SuperCollider   								 *
* Copyright (C) 2025 Siriil									    							 *
* Licensed under GPLv3. See LICENSE file for details.			    						 *
*********************************************************************************************/

MEAliases {
	classvar <aliases;

	*initClass {

		aliases = Dictionary[
			// Power chords
			"P5"   -> Set["5"],

			// Triads,
			"m3d5" -> Set["Dim", "dim", "Dim5", "dim5", "o5", "05", "o", "0"],
			"m3P5" -> Set["m", "Mi", "mi", "Min", "min", "-"],
			"M3P5" -> Set["", "M", "Ma", "ma", "Maj", "maj", "^", "Neapolitan", "neapolitan", "Nap", "nap", "Na", "na"],
			"M3A5" -> Set["Aug", "aug", "Aug5", "aug5", "+5", "+"],

			// Seventh chords
			"m3d5d7" -> Set["Dim7", "dim7", "o7", "07"],
			"m3d5m7" -> Set["m7o5", "m705", "Mi7o5", "Mi705", "mi7o5", "mi705", "Min7o5", "Min705", "min7o5", "min705", "-7o5", "-705"],
			"m3P5m7" -> Set["m7", "Mi7", "mi7", "Min7", "min7", "-7"],
			"m3P5M7" -> Set["mM", "mM7", "MiMa7", "mima7", "MinMaj7", "minmaj7", "m^7", "Mi^7", "mi^7", "Min^7", "min^7", "-^7"],
			"M3P5m7" -> Set["Dom", "dom", "Dom7", "dom7", "7"],
			"M3P5M7" -> Set["M7", "Ma7", "ma7", "Maj7", "maj7", "^7"],

			// Sixth chords
			"M3P5M6" -> Set["Ma6", "ma6", "Maj6", "maj6", "^6"],
			"m3P5M6" -> Set["Mi6", "mi6", "Min6", "min6", "-6"],

			// Augmented sixth chords
			"d3d5"   -> Set["Italian", "italian", "Ita", "ita", "It", "it"],
			"M3d5m7" -> Set["French", "french", "Fre", "fre", "Fr", "fr"],
			"d3d5d7" -> Set["German", "german", "Ger", "ger", "Gr", "gr"],

			// Suspended chords
			"M2P5"   -> Set["Sus2", "sus2"],
			"P4P5"   -> Set["Sus4", "sus4"],
			"M2P4P5" -> Set["Sus24", "sus24"],

			// Nineth chords
			"m3P5m7M9" -> Set["m79", "Mi79", "mi79", "Min79", "min79", "-9", "-79"],
			"M3P5m7M9" -> Set["Dom9", "dom9", "Dom79", "dom79", "9"],
			"M3P5M7M9" -> Set["M79", "Ma79", "ma79", "Maj79", "maj79", "^9", "^79"],

			// Eleventh chords
			"m3P5m7M9P11" -> Set["m11", "m711", "Mi11", "mi11", "Mi711", "mi711", "Min11", "min11", "Min711", "min711", "-11", "-711"],
			"M3P5m7M9P11" -> Set["Dom11", "dom11", "Dom711", "dom711", "11"],
			"M3P5M7M9P11" -> Set["M11", "M711", "Ma11", "ma11", "Ma711", "ma711", "Maj11", "maj11", "Maj711", "maj711", "^11", "^711"],

			// Thirteenth chords
			"m3P5m7M9P11M13" -> Set["m13", "m713", "Mi13", "mi13", "Mi713", "mi713", "Min13", "min13", "Min713", "min713", "-13", "-713"],
			"M3P5m7M9P11M13" -> Set["Dom13", "dom13", "Dom713", "dom713", "13"],
			"M3P5M7M9P11M13" -> Set["M13", "M713", "Ma13", "ma13", "Ma713", "ma713", "Maj13", "maj13", "Maj713", "maj713", "^13", "^713"]
		];
	}

	/****************************************************************************************/

	*getSymbolFromAlias { |alias|
		var out = nil;

		aliases.keysValuesDo { |k, v|

			if (v.includes(alias)) {
				out = k;
			};
		};

		MEDebug.log("MEAliases", "*checkAliases", "\nin:  %;\nout: %\n".format(alias, out));

		^out;
	}
}