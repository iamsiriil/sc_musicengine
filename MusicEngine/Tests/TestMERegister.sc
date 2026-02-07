/*********************************************************************************************
* MusicEngine - A dynamic music library for SuperCollider   								 *
* Copyright (C) 2025 Siriil									    							 *
* Licensed under GPLv3. See LICENSE file for details.			    						 *
*********************************************************************************************/

TestMERegister : UnitTest{

	setUp {
		MERegister.test = true;
	}

	tearDown {
		MERegister.test = false;
		Archive.global.removeAt(\METest);
	}

	/****************************************************************************************/

	test_newEntry_validInput {
		var fixture = (
			alias:  'boby',
			symbol: "M3d5",
			expect: "M3d5"
		);

		MERegister.newEntry(fixture.alias, fixture.symbol);

		this.assertEquals(
			Archive.global.at(\METest)[fixture.alias],
			fixture.expect,
			"Setting register with valid alias: % and symbol: %. Should return % from Archive."
			.format(
				fixture.alias,
				fixture.symbol,
				fixture.expect
			);
		);
	}

	/****************************************************************************************/

	test_newEntry_invalidAlias {
		var fixture = (alias: '-7', symbol: "m3P5m7");

		this.assertException(
			{ MERegister.newEntry(fixture.alias, fixture.symbol) },
			Error,
			"Alias % already in use by MEAlias class. Should throw Error.".format(
				fixture.alias
			);
		);
	}

	/****************************************************************************************/

	test_newEntry_invalidInterval {
		var fixture = (alias: 'm7', symbol: "m3P5m7");

		this.assertException(
			{ MERegister.newEntry(fixture.alias, fixture.symbol) },
			Error,
			"Interval % already in use by MECore intervals dictionary. Should throw Error."
			.format(
				fixture.alias
			);
		);
	}

	/****************************************************************************************/

	test_changeEntry_validInput {
		var fixture = (
			alias:        'boby',
			symbolBefore: "M3d5",
			symbolAfter:  "m3A5",
			expect:       "m3A5"
		);

		MERegister.newEntry(fixture.alias, fixture.symbolBefore);
		MERegister.changeEntry(fixture.alias, fixture.symbolAfter);

		this.assertEquals(
			Archive.global.at(\METest)[fixture.alias],
			fixture.expect,
			"Changing register at % from symbol: % and symbol: %. Should return % from Archive."
			.format(
				fixture.alias,
				fixture.symbolBefore,
				fixture.symbolAfter,
				fixture.expect
			);
		);
	}

	/****************************************************************************************/

	test_removeRantry_validInput {
		var fixture = (
			alias:  'boby',
			symbol: "M3d5",
			expect: nil;
		);

		MERegister.newEntry(fixture.alias, fixture.symbol);
		MERegister.removeEntry(fixture.alias);

		this.assertEquals(
			Archive.global.at(\METest)[fixture.alias],
			fixture.expect,
			"Removing alias % from register. Should return '%'.".format(
				fixture.alias,
				fixture.expect
			)
		)
	}

	/****************************************************************************************/

	test_clearDict_validInput {
		var fixture = [
			(alias:  'boby', symbol: "M3d5"),
			(alias:  'toby', symbol: "m3A5")
		];
		var expect = true;

		MERegister.newEntry(fixture[0].alias, fixture[0].symbol);
		MERegister.newEntry(fixture[1].alias, fixture[1].symbol);

		MERegister.clearDict;

		this.assertEquals(
			Archive.global.at(\METest).isEmpty,
			expect,
			"Clearing alias dictionary from register. Should return '%'.".format(expect);
		);
	}

	/****************************************************************************************/

	test_getSymbolFromAlias_validInput {
		var fixture = (
			alias:  'boby',
			symbol: "M3d5",
			expect: "M3d5"
		);
		var symbol;

		MERegister.newEntry(fixture.alias, fixture.symbol);
		symbol = MERegister.getSymbolFromAlias(fixture.alias);

		this.assertEquals(
			symbol,
			fixture.expect,
			"Getting symbol from register. Should return '%'.".format(fixture.expect);
		);
	}

	/****************************************************************************************/

	test_getSymbolFromAlias_nonExistingAlias {
		var fixture = (alias: 'susan', expect: nil);
		var symbol;

		MERegister.newEntry('boby', "M3d5");
		symbol = MERegister.getSymbolFromAlias(fixture.alias);

		this.assertEquals(
			symbol,
			fixture.expect,
			"Getting non existing symbol from register. Should return '%'.".format(fixture.expect);
		);
	}
}