/*********************************************************************************************
* MusicEngine - A dynamic music library for SuperCollider   								 *
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
				"Testing object instantiation for symbol: %. Should return a MESymbol object."
				.format(s)
			);
		};
	}

	/****************************************************************************************/

	/*test_init_validInputNoSymbol {
		var fixture = (
			symbol: "F#",
			expected: [
				"F#",
				[MEInterval('P1'),MEInterval('M3'),MEInterval('P5')],
				"F#M3P5",
				"M3P5",
				nil,
				nil
			]
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
	}*/

	/****************************************************************************************/

	/*test_init_validInputAlias {
		var fixture = (
			symbol: "G#min7",
			expected: [
				"G#",
				[MEInterval('P1'), MEInterval('m3'), MEInterval('P5'), MEInterval('m7')],
				"G#m3P5m7",
				"m3P5m7",
				"G#min7",
				"min7"
			]
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
	}*/

	/****************************************************************************************/

	/*test_init_validInputVerbose {
		var fixture = (
			symbol: "CbM3P5M7M9",
			expected: [
				"Cb",
				[
					MEInterval('P1'),
					MEInterval('M3'),
					MEInterval('P5'),
					MEInterval('M7'),
					MEInterval('M9')
				],
				"CbM3P5M7M9",
				"M3P5M7M9",
				nil,
				nil
			]
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
	}*/

	/****************************************************************************************/

	test_init_invalidRoot {
		var fixtures = ['CM3P5', "", "C####M3P5", "CbbbbbM3P5", "XM3P3", "M3P5"];

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

	/****************************************************************************************/

	test_symbol_withRoot {
		var fixture = (symbol: "CMaj7", expected: "CM3P5M7");

		this.assertEquals(
			MESymbol(fixture.symbol).symbol,
			fixture.expected,
			"Calling symbol method from instance. Should return %.".format(fixture.expected)
		);
	}

	/****************************************************************************************/

	test_symbol_withoutRoot {
		var fixture = (symbol: "CMaj7", expected: "M3P5M7");

		this.assertEquals(
			MESymbol(fixture.symbol).symbol(false),
			fixture.expected,
			"Calling symbol method from instance. Should return %.".format(fixture.expected)
		);
	}

	/****************************************************************************************/

	test_alias_withRoot {
		var fixture = (symbol: "CMaj7", expected: "CMaj7");

		this.assertEquals(
			MESymbol(fixture.symbol).alias,
			fixture.expected,
			"Calling alias method from instance. Should return %.".format(fixture.expected)
		);
	}

	/****************************************************************************************/

	test_alias_withoutRoot {
		var fixture = (symbol: "CMaj7", expected: "Maj7");

		this.assertEquals(
			MESymbol(fixture.symbol).alias(false),
			fixture.expected,
			"Calling alias method from instance. Should return %.".format(fixture.expected)
		);
	}

	/****************************************************************************************/

	test_root_asString {
		var fixture = (symbol: "CMaj7", expected: "C");

		this.assertEquals(
			MESymbol(fixture.symbol).root,
			fixture.expected,
			"Calling root as String from instance. Should return %.".format(fixture.expected)
		);
	}

	/****************************************************************************************/

	test_root_asOffset {
		var fixture = (symbol: "CMaj7", expected: 0);

		this.assertEquals(
			MESymbol(fixture.symbol).root(true),
			fixture.expected,
			"Calling root as offset from instance. Should return %.".format(fixture.expected)
		);
	}

	/****************************************************************************************/

	test_intervals_objArray {
		var fixture = (
			symbol: "CMaj7",
			expected: [MEInterval('P1'), MEInterval('M3'), MEInterval('P5'), MEInterval('M7')]
		);

		this.assertEquals(
			MESymbol(fixture.symbol).intervals,
			fixture.expected,
			"Calling intervals from instance. Should return %.".format(fixture.expected)
		);
	}
}