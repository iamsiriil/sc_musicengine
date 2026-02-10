/*********************************************************************************************
* MusicEngine - A dynamic music library for SuperCollider   								 *
* Copyright (C) 2025 Siriil									    							 *
* Licensed under GPLv3. See LICENSE file for details.			    						 *
*********************************************************************************************/

TestMEInterval_init : UnitTest {

	test_init_objectInstantiation {
		var interval   = 'P5';
		var meInterval = MEInterval(interval);

		this.assert(
			meInterval.isKindOf(MEInterval),
			"Testing instantiation with symbol: '%'. Should return a MEInterval object."
			.format(interval)
		);
	}

	/****************************************************************************************/

	test_interval_symbol {
		var fixture = 'P5';

		this.assertEquals(
			MEInterval(fixture).interval,
			fixture,
			"Accessing interval value. Should return %.".format(fixture)
		);
	}

	/****************************************************************************************/

	test_number_asFloat {
		var fixture = (interval: 'P5', expected: 5.2);

		this.assertEquals(
			MEInterval(fixture.interval).number,
			fixture.expected,
			"Accessing number as Float. Should return %.".format(fixture.expected)
		);
	}

	/****************************************************************************************/

	test_number_asInteger {
		var fixture = (interval: 'P5', expected: 5);

		this.assertEquals(
			MEInterval(fixture.interval).number(true),
			fixture.expected,
			"Accessing number as Integer. Should return %.".format(fixture.expected)
		);
	}

	/****************************************************************************************/

	test_quality_asSymbol {
		var fixture = (interval: 'P5', expected: \P);

		this.assertEquals(
			MEInterval(fixture.interval).quality,
			fixture.expected,
			"Accessing quality as Symbol. Should return %.".format(fixture.expected)
		);
	}

	/****************************************************************************************/

	test_quality_asFloat {
		var fixture = (interval: 'P5', expected: "0.2");

		this.assertEquals(
			MEInterval(fixture.interval).quality(true).asString,
			fixture.expected,
			"Accessing quality as Float. Should return %.".format(fixture.expected)
		);
	}

	/****************************************************************************************/

	test_asLetterOffset {
		var fixture = (interval: 'P5', expected: 4);

		this.assertEquals(
			MEInterval(fixture.interval).asLetterOffset,
			fixture.expected,
			"Converting interval to letter offset. Should return %.".format(fixture.expected)
		);
	}

	/****************************************************************************************/

	test_asMIDIOffset {
		var fixture = (interval: 'P5', expected: 7);

		this.assertEquals(
			MEInterval(fixture.interval).asMIDIOffset,
			fixture.expected,
			"Converting interval to MIDI offset. Should return %.".format(fixture.expected)
		);
	}

	/****************************************************************************************/

	test_isEnharmonic_true {
		var meInterval1 = MEInterval('A4');
		var meInterval2 = MEInterval('d5');
		var expected = true;

		this.assertEquals(
			meInterval1.isEnharmonic(meInterval2),
			expected,
			"Comparing enharmony between two intervals % and %. Should return %."
			.format(meInterval1, meInterval2, expected)
		);
	}

	/****************************************************************************************/

	test_isEnharmonic_false {
		var meInterval1 = MEInterval('P4');
		var meInterval2 = MEInterval('P5');
		var expected = false;

		this.assertEquals(
			meInterval1.isEnharmonic(meInterval2),
			expected,
			"Comparing enharmony between two intervals % and %. Should return %."
			.format(meInterval1, meInterval2, expected)
		);
	}

	/****************************************************************************************/

	test_isEqual_true {
		var meInterval1 = MEInterval('P5');
		var meInterval2 = MEInterval('P5');
		var expected = true;

		this.assertEquals(
			meInterval1 == meInterval2,
			expected,
			"Testing comparison operation: % '==' %. Should return %."
			.format(meInterval1, meInterval2, expected)
		);
	}

	/****************************************************************************************/

	test_isEqual_false {
		var meInterval1 = MEInterval('P4');
		var meInterval2 = MEInterval('P5');
		var expected = false;

		this.assertEquals(
			meInterval1 == meInterval2,
			expected,
			"Testing comparison operation: % '==' %. Should return %."
			.format(meInterval1, meInterval2, expected)
		);
	}

	/****************************************************************************************/

	test_notEqual_true {
		var meInterval1 = MEInterval('P4');
		var meInterval2 = MEInterval('P5');
		var expected = true;

		this.assertEquals(
			meInterval1 != meInterval2,
			expected,
			"Testing comparison operation: % '!=' %. Should return %."
			.format(meInterval1, meInterval2, expected)
		);
	}

	/****************************************************************************************/

	test_notEqual_false {
		var meInterval1 = MEInterval('P5');
		var meInterval2 = MEInterval('P5');
		var expected = false;

		this.assertEquals(
			meInterval1 != meInterval2,
			expected,
			"Testing comparison operation: % '!=' %. Should return %."
			.format(meInterval1, meInterval2, expected)
		);
	}

	/****************************************************************************************/

	test_greaterOrEqual_greater {
		var meInterval1 = MEInterval('P5');
		var meInterval2 = MEInterval('P4');
		var expected = true;

		this.assertEquals(
			meInterval1 >= meInterval2,
			expected,
			"Testing comparison operation: % '>=' %. Should return %."
			.format(meInterval1, meInterval2, expected)
		);
	}

	/****************************************************************************************/

	test_greaterOrEqual_equal {
		var meInterval1 = MEInterval('P5');
		var meInterval2 = MEInterval('P5');
		var expected = true;

		this.assertEquals(
			meInterval1 >= meInterval2,
			expected,
			"Testing comparison operation: % '>=' %. Should return %."
			.format(meInterval1, meInterval2, expected)
		);
	}

	/****************************************************************************************/

	test_greaterOrEqual_false {
		var meInterval1 = MEInterval('P4');
		var meInterval2 = MEInterval('P5');
		var expected = false;

		this.assertEquals(
			meInterval1 >= meInterval2,
			expected,
			"Testing comparison operation: % '>=' %. Should return %."
			.format(meInterval1, meInterval2, expected)
		);
	}

	/****************************************************************************************/

	test_lessOrEqual_less {
		var meInterval1 = MEInterval('P4');
		var meInterval2 = MEInterval('P5');
		var expected = true;

		this.assertEquals(
			meInterval1 <= meInterval2,
			expected,
			"Testing comparison operation: % '<=' %. Should return %."
			.format(meInterval1, meInterval2, expected)
		);
	}

	/****************************************************************************************/

	test_lessOrEqual_equal {
		var meInterval1 = MEInterval('P4');
		var meInterval2 = MEInterval('P4');
		var expected = true;

		this.assertEquals(
			meInterval1 <= meInterval2,
			expected,
			"Testing comparison operation: % '<=' %. Should return %."
			.format(meInterval1, meInterval2, expected)
		);
	}

	/****************************************************************************************/

	test_lessOrEqual_false {
		var meInterval1 = MEInterval('P5');
		var meInterval2 = MEInterval('P4');
		var expected = false;

		this.assertEquals(
			meInterval1 <= meInterval2,
			expected,
			"Testing comparison operation: % '<=' %. Should return %."
			.format(meInterval1, meInterval2, expected)
		);
	}

	/****************************************************************************************/

	test_greaterThan_true {
		var meInterval1 = MEInterval('P5');
		var meInterval2 = MEInterval('P4');
		var expected = true;

		this.assertEquals(
			meInterval1 > meInterval2,
			expected,
			"Testing comparison operation: % '>' %. Should return %."
			.format(meInterval1, meInterval2, expected)
		);
	}

	/****************************************************************************************/

	test_greaterThan_false {
		var meInterval1 = MEInterval('P4');
		var meInterval2 = MEInterval('P5');
		var expected = false;

		this.assertEquals(
			meInterval1 > meInterval2,
			expected,
			"Testing comparison operation: % '>' %. Should return %."
			.format(meInterval1, meInterval2, expected)
		);
	}

	/****************************************************************************************/

	test_lesserThan_true {
		var meInterval1 = MEInterval('P4');
		var meInterval2 = MEInterval('P5');
		var expected = true;

		this.assertEquals(
			meInterval1 < meInterval2,
			expected,
			"Testing comparison operation: % '<' %. Should return %."
			.format(meInterval1, meInterval2, expected)
		);
	}

	/****************************************************************************************/

	test_lesserThan_false {
		var meInterval1 = MEInterval('P5');
		var meInterval2 = MEInterval('P4');
		var expected = false;

		this.assertEquals(
			meInterval1 < meInterval2,
			expected,
			"Testing comparison operation: % '<' %. Should return %."
			.format(meInterval1, meInterval2, expected)
		);
	}
}