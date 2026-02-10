/*********************************************************************************************
* MusicEngine - A dynamic music library for SuperCollider   								 *
* Copyright (C) 2025 Siriil									    							 *
* Licensed under GPLv3. See LICENSE file for details.			    						 *
*********************************************************************************************/

MESymbol {
	var <intervals;
	var symbol;
	var alias;
	var root;

	*new { |rangeSymbol| ^super.new.init(rangeSymbol) }

	init { |newS|
		var normSymbol;
		var validate = true;

		MEDebug.log(thisMethod, 1, [newS]);

		#root, symbol = MESymbol.splitSymbol(newS);

		case
		{ ((normSymbol = MERegister.getSymbolFromAlias(symbol)).notNil) } {
			alias    = symbol;
			symbol   = normSymbol;
			validate = false;
		}
		{ ((normSymbol = MEAlias.getSymbolFromAlias(symbol)).notNil)    } {
			alias    = if (symbol == "") { nil } { symbol };
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
		var regex = "^(?:([A-G][#b]{0,3})(?![#b])(.*))$"; // "^(?:([A-G][#b]?)([^#b]*))$";

		MEDebug.log(thisMethod, 1, [rangeSymbol]);

		MESymbolValidators.rootIsValid(rangeSymbol);

		^rangeSymbol.findRegexp(regex)[1..2].collect { |n| n[1] };
	}


	/****************************************************************************************/

	*getIntervalsFromSymbol { |symbol, validate = true|
		var regex = MESymbolValidators.testRegex;
		var intervalsArr;

		MEDebug.log(thisMethod, 1, [symbol]);

		if (validate) {
			MESymbolValidators.symbolIsValid(symbol);
		};

		intervalsArr = symbol.findRegexp(regex).collect { |i| i[1] };

		^MEInterval.getMEIntervalArray(["P1"] ++ intervalsArr);
	}

	/****************************************************************************************/

	symbol { |withRoot = true|

		if (withRoot) {
			^root ++ symbol;
		};
		^symbol;
	}

	/****************************************************************************************/

	alias { |withRoot = true|

		if (alias.notNil) {

			if (withRoot) {
				^root ++ alias;
			};
			^alias;
		};
		^nil;
	}

	/****************************************************************************************/

	root { |offset = false|

		if (offset) {
			^MEMIDINote.getOffsetFromName(root);
		};
		^root;
	}
}