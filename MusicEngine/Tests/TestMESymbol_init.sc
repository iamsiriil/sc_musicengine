/*********************************************************************************************
* MusicEngine - A dynamic chord library for SuperCollider   								 *
* Copyright (C) 2025 Siriil									    							 *
* Licensed under GPLv3. See LICENSE file for details.			    						 *
*********************************************************************************************/

TestMESymbol_init : UnitTest {

	test_init_objectInstantiation {
		var symbols = ["C", "CMaj", "CM3P5"];

		symbols.do { |s|
			var temp = MESymbol(s);

			this.assert(
				temp.isKindOf(MESymbol),
				"Testing object instantiation, for valid symbol: %. Should return a MESymbol object.".format(s)
			);
		};
	}

	/****************************************************************************************/

	test_init_validInputNoSymbol {
		var fixture = (
			symbol: "F#",
			expected: ["F#", ["M3", "P5"], "F#M3P5", "M3P5", nil, nil]
		);
		var symbol = MESymbol(fixture.symbol);
		var symbolData = [
			symbol.root,
			symbol.intervals,
			symbol.symbol(true),
			symbol.symbol(false),
			symbol.alias(true),
			symbol.alias(false)
		];

		this.assertEquals(
			symbolData,
			fixture.expected,
			"Testing valid symbol: %. Should return symbol data.".format(fixture.symbol)
		);
	}

	/****************************************************************************************/

	test_init_validInputAlias {
		var fixture = (
			symbol: "G#min7",
			expected: ["G#", ["m3", "P5", "m7"], "G#m3P5m7", "m3P5m7", "G#min7", "min7"]
		);
		var symbol = MESymbol(fixture.symbol);
		var symbolData = [
			symbol.root,
			symbol.intervals,
			symbol.symbol(true),
			symbol.symbol(false),
			symbol.alias(true),
			symbol.alias(false)
		];

		this.assertEquals(
			symbolData,
			fixture.expected,
			"Testing valid symbol: %. Should return symbol data.".format(fixture.symbol)
		);
	}

	/****************************************************************************************/

	test_init_validInputVerbose {
		var fixture = (
			symbol: "CbM3P5M7M9",
			expected: ["Cb", ["M3", "P5", "M7", "M9"], "CbM3P5M7M9", "M3P5M7M9", nil, nil]
		);
		var symbol = MESymbol(fixture.symbol);
		var symbolData = [
			symbol.root,
			symbol.intervals,
			symbol.symbol(true),
			symbol.symbol(false),
			symbol.alias(true),
			symbol.alias(false)
		];

		this.assertEquals(
			symbolData,
			fixture.expected,
			"Testing valid symbol: %. Should return symbol data.".format(fixture.symbol)
		);
	}

	/****************************************************************************************/

	test_init_invalidRoot {
		var fixtures = ['CM3P5', "", "C###M3P5", "CbbbM3P5", "XM3P3", "M3P5"];

		fixtures.do { |f|

			this.assertException(
				{ MESymbol(f) },
				Error,
				"Testing symbol with invalid root: %. Should throw Error.".format(f)
			);
		};
	}

	/****************************************************************************************/

	test_init_invalidSymbol {
		var fixtures = ["CM15P5", "Cm3+P5", "CM3 P5" "CM3PP3", "Cm3m3m3m3m3m3m3m3m3m3m3m3", "CM3M5M7", "CM3M3P5"];

		fixtures.do { |f|

			this.assertException(
				{ MESymbol(f) },
				Error,
				"Testing invalid symbol: %. Should throw Error.".format(f)
			);
		};
	}

	/****************************************************************************************/

	test_init_invalidSymbolEdges {
		var fixtures = ["C3P5m7", "Cm3P5m"];

		fixtures.do { |f|

			this.assertException(
				{ MESymbol(f) },
				Error,
				"Testing invalid symbol: %. Should throw Error.".format(f)
			);
		};
	}
}