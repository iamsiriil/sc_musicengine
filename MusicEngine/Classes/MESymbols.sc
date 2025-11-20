/*********************************************************************************************
* MusicEngine - A dynamic chord library for SuperCollider   								 *
* Copyright (C) 2025 Siriil									    							 *
* Licensed under GPLv3. See LICENSE file for details.			    						 *
*********************************************************************************************/

MESymbols : MESymbolTools {
	var <root;
	var <degrees;
	var symbol;
	var alias;

	*new { |symbol|
		^super.new.init(symbol);
	}

	init { |newSymbol|
		var normSymbol;

		MEDebug.log("MESymbols", "init");

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