/*********************************************************************************************
* MusicEngine - A dynamic chord library for SuperCollider   								 *
* Copyright (C) 2025 Siriil									    							 *
* Licensed under GPLv3. See LICENSE file for details.			    						 *
*********************************************************************************************/

MESymbol {
	var <root;
	var <degrees;
	var symbol;
	var alias;

	*new { |symbol|
		^super.new.init(symbol);
	}

	init { |newSymbol|
		var normSymbol;

		MEDebug.log("MESymbols", "init", "\nin:  %\n".format(newSymbol));

		#root, symbol = MESymbol.splitSymbol(newSymbol);

		if ((normSymbol = MEAliases.checkAliases(symbol)).notNil) {

			if (symbol == "") {
				alias = nil;
			} {
				alias = symbol;
			};

			symbol  = normSymbol;
		};
		degrees = MESymbol.getIntervalsFromSymbol(symbol);

		^this;
	}

	/****************************************************************************************/

	printOn { |stream|
		stream << "MESymbol [ ";
		stream << "Root: " << root << ", ";
		stream << "Intervals: " << degrees << ", ";
		stream << "Symbol: " << symbol << ", ";
		stream << "Alias: " << alias << " ]";
	}

	/****************************************************************************************/

	*splitSymbol { |rangeSymbol|
		var regex = "^(?:([A-G][#b]?)([^#b]*))$";
		var root, symbol;

		MESymbolValidator.rootIsValid(rangeSymbol);

		#root, symbol = rangeSymbol.findRegexp(regex).collect { |n| n[1] }[1..2];

		//MEDebug.log("MESymbol", "splitSymbol");

		^[root, symbol];
	}

	/****************************************************************************************/

	*getRootFromSymbol { |chorSymbol|
		var rootNote;

		MESymbolValidator.symbolRootIsValid(chorSymbol);

		rootNote = chorSymbol.findRegexp("^[A-G][#|b]*")[0][1];

		MENameValidators.rootNoteIsValid(rootNote);

		MEDebug.log("MESymbol", "getRootFromSymbol", "\nin:  %\nout: %\n".format(chorSymbol, rootNote));

		^rootNote;
	}

	/****************************************************************************************/

	*getIntervalsFromSymbol { |chordSymbol|
		var regex = MESymbolValidator.testRegex;
		var intervalsArr;

		intervalsArr = chordSymbol.findRegexp(regex).collect { |i| i[1] };

		MESymbolValidator.symbolIsValid(chordSymbol, intervalsArr);

		MEDebug.log("MESymbol", "getIntervalsFromSymbol", "\nin:  %\nout: %\n".format(chordSymbol, intervalsArr));

		^intervalsArr;
	}

	/****************************************************************************************/

	symbol { // withRoot = true
		^root ++ symbol;
	}

	/****************************************************************************************/

	alias { // withRoot = true

		if (alias.notNil) {
			^root ++ alias;
		};
		^nil;
	}
}