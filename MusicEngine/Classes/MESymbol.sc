/*********************************************************************************************
* MusicEngine - A dynamic chord library for SuperCollider   								 *
* Copyright (C) 2025 Siriil									    							 *
* Licensed under GPLv3. See LICENSE file for details.			    						 *
*********************************************************************************************/

MESymbol {
	var <root;
	var <intervals;
	var symbol;
	var alias;

	*new { |symbol|
		^super.new.init(symbol);
	}

	init { |newSymbol|
		var normSymbol;
		var validate = true;

		MEDebug.log("MESymbols", "init", "\nin:  %\n".format(newSymbol));

		#root, symbol = MESymbol.splitSymbol(newSymbol);

		if ((normSymbol = MEAliases.checkAliases(symbol)).notNil) {

			if (symbol == "") {
				alias = nil;
			} {
				alias = symbol;
			};
			symbol   = normSymbol;
			validate = false;
		};
		intervals = MESymbol.getIntervalsFromSymbol(symbol, validate);

		^this;
	}

	/****************************************************************************************/

	printOn { |stream|
		stream << "MESymbol [ ";
		stream << "Root: " << root << ", ";
		stream << "Intervals: " << intervals << ", ";
		stream << "Symbol: " << symbol << ", ";
		stream << "Alias: " << alias << " ]";
	}

	/****************************************************************************************/

	*splitSymbol { |rangeSymbol|
		var regex = "^(?:([A-G][#b]?)([^#b]*))$";
		var root, symbol;

		MESymbolValidators.rootIsValid(rangeSymbol);

		#root, symbol = rangeSymbol.findRegexp(regex).collect { |n| n[1] }[1..2];

		//MEDebug.log("MESymbol", "splitSymbol");

		^[root, symbol];
	}


	/****************************************************************************************/

	*getIntervalsFromSymbol { |symbol, validate = true|
		var regex = MESymbolValidators.testRegex;
		var intervalsArr;

		if (validate) {
			MESymbolValidators.symbolIsValid(symbol);
		};

		intervalsArr = symbol.findRegexp(regex).collect { |i| i[1] };

		MEDebug.log("MESymbol", "getIntervalsFromSymbol", "\nin:  %\nout: %\n".format(symbol, intervalsArr));

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