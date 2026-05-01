/*********************************************************************************************
* MusicEngine - A dynamic music library for SuperCollider   								 *
* Copyright (C) 2025 Siriil									    							 *
* Licensed under GPLv3. See LICENSE file for details.			    						 *
*********************************************************************************************/

MERegister {
	classvar <test = false;
	classvar <dictName;

	*initClass {

		dictName = \MERegister;

		super.initClass;
	}

	/****************************************************************************************/

	*test_ { |bool|

		switch(bool)
		{ true  } {
			test     = true;
			dictName = \METest;
		}
		{ false } {
			test     = false;
			dictName = \MERegister;
		};
	}

	/****************************************************************************************/

	*newEntry { |alias, symbol|
		var dict;

		MEAliasValidators.aliasIsValid(alias);
		MESymbolValidators.symbolIsValid(symbol);

		if ((dict = Archive.global.at(dictName)).isNil) {
			dict = Dictionary();
			Archive.global.put(dictName, dict);
		};
		if (dict[alias].notNil) {
			if (test.not) {
				"Alias '%' already in dictionary with symbol %."
				.format(alias, dict[alias]).warn;
			};
		} {
			dict[alias] = symbol;
			if (test.not) {
				"Alias '%' has been added.".format(alias).postln;
			};
		};
	}

	/****************************************************************************************/

	*changeEntry { |alias, symbol|
		var dict, old;

		MESymbolValidators.symbolIsValid(symbol);

		if ((dict = Archive.global.at(dictName)).isNil) {
			dict = Dictionary();
			Archive.global.put(dictName, dict);
		};
		if ((old = dict[alias]).notNil) {
			dict[alias] = symbol;
			if (test.not) {
				"Symbol % changed to %.".format(old, symbol).postln;
			};
		} {
			if (test.not) {
				"Alias '%' does not exist.".format(alias).warn;
			};
		};
	}

	/****************************************************************************************/

	*removeEntry { |alias|
		var dict;

		if ((dict = Archive.global.at(dictName)).isNil) {
			^nil;
		};
		if (dict[alias].isNil) {
			if (test.not) {
				"Alias '%' does not exist.".format(alias).warn;
			};
		} {
			dict[alias] = nil;
			if (test.not) {
				"Alias '%' removed from dictionary.".format(alias).postln;
			};
		};
	}

	/****************************************************************************************/

	*clearDict {
		var dict = Archive.global.at(dictName);

		dict.clear;
		if (test.not) {
			"Alias dictionary has been cleared.".postln;
		};
	}

	/****************************************************************************************/

	*getSymbolFromAlias { |alias|
		var dict = Archive.global.at(dictName);

		if ((dict = Archive.global.at(dictName)).notNil) {
			^dict[alias.asSymbol];
		};
		^nil;
	}

	/****************************************************************************************/

	*showEntries {
		var maxSizeK, maxSizeV;
		var dict;

		if ((dict = Archive.global.at(dictName)).notNil) {

			if (dict.notEmpty) {
				maxSizeK = dict.keys.maxItem { |i| i.asString.size }.asString.size.postln;
				maxSizeV = dict.values.maxItem { |i| i.size }.size.postln;

				maxSizeK = if (maxSizeK > 6) { maxSizeK + 1 } { 7 };

				"\nALIAS ".padRight(maxSizeK + 1).post;
				"  SYMBOL".postln;
				"_".padRight(maxSizeK + maxSizeV + 2, "_").postln;

				dict.keysValuesDo { |k, v|
					"% ".format(k).padRight(maxSizeK).post;
					"│ %".format(v).postln;
				};
				"".postln;
			} {
				"Alias dictionary is empty.".warn;
			}
		} {
			"There are no entries in alias dictionary.".warn;
		};
	}
}
