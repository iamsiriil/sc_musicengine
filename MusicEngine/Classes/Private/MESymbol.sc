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

	*new { |rangeSymbol|
		^super.new.init(rangeSymbol);
	}

	init { |newS|
		var normSymbol;
		var validate = true;

		MEDebug.log(thisMethod, 1, [newS]);

		#root, symbol = MESymbol.splitSymbol(newS);

		if ((normSymbol = MEAlias.getSymbolFromAlias(symbol)).notNil) {

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

		MEDebug.log(thisMethod, 1, [rangeSymbol]);

		MESymbolValidators.rootIsValid(rangeSymbol);

		^rangeSymbol.findRegexp(regex).collect { |n| n[1] }[1..2];
	}


	/****************************************************************************************/

	*getIntervalsFromSymbol { |symbol, validate = true|
		var regex = MESymbolValidators.testRegex;
		var intervalsArr;

		MEDebug.log(thisMethod, 1, [symbol]);

		if (validate) {
			MESymbolValidators.symbolIsValid(symbol);
		};

		^symbol.findRegexp(regex).collect { |i| i[1] };
	}

	/****************************************************************************************/

	symbol { |withRoot = true|

		if (withRoot) {
			^root ++ symbol;
		} {
			^symbol;
		};
	}

	/****************************************************************************************/

	alias { |withRoot = true|

		if (alias.notNil) {

			if (withRoot) {
				^root ++ alias;
			} {
				^alias;
			};
		};
		^nil;
	}
}