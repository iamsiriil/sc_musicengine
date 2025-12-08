/*********************************************************************************************
* MusicEngine - A dynamic chord library for SuperCollider   								 *
* Copyright (C) 2025 Siriil									    							 *
* Licensed under GPLv3. See LICENSE file for details.			    						 *
*********************************************************************************************/

MESymbol : MESymbolValidator {
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

		root   = super.getRoot(newSymbol);
		symbol = newSymbol[root.size..];

		if ((normSymbol = MEAliases.checkAliases(symbol)).notNil) {

			if (symbol == "") {
				alias = nil;
			} {
				alias = symbol;
			};

			symbol  = normSymbol;
			degrees = super.getDegrees(symbol);
		} {
			degrees = super.getDegrees(symbol);
		};

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

	symbol {
		^root ++ symbol;
	}

	/****************************************************************************************/

	alias {

		if (alias.notNil) {
			^root ++ alias;
		};
		^nil;
	}
}