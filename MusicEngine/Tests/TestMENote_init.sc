/*********************************************************************************************
* MusicEngine - A dynamic music library for SuperCollider   								 *
* Copyright (C) 2025 Siriil									    							 *
* Licensed under GPLv3. See LICENSE file for details.			    						 *
*********************************************************************************************/

TestMENote_init : UnitTest {

	test_new {
		var fixture = (
			expect: MENote,
			meNote: MENote("C", 59, \P1)
		);

		this.assert(
			fixture.meNote.isKindOf(fixture.expect),
			"Testing .new MENote instantiation. Should return a MENote object."
		)
	}

	/****************************************************************************************/

	test_newFromName {
		var fixture = (
			expect: MENote,
			meNote: MENote.newFromName("Cb4", \P1);
		);

		this.assert(
			fixture.meNote.isKindOf(fixture.expect),
			"Testing .newFromName MENote instantiation. Should return a MENote object."
		)
	}

	/****************************************************************************************/

	test_midi {
		var fixture = (
			expect: 59,
			meNote: MENote("C", 59, \P1)
		);
		this.assertEquals(
			fixture.meNote.midi,
			fixture.expect,
			"Testing MENote .midi method. Should return MIDI value."
		)
	}

	/****************************************************************************************/

	test_freq {
		var fixture = (
			expect: 246.94165062806,
			meNote: MENote("C", 59, \P1)
		);
		this.assertEquals(
			fixture.meNote.freq.ceil,
			fixture.expect.ceil,
			"Testing MENote .freq method. Should return frequency value."
		)
	}

	/****************************************************************************************/

	test_octave {
		var fixture = (
			expect: 4,
			meNote: MENote("C", 59, \P1)
		);
		this.assertEquals(
			fixture.meNote.octave,
			fixture.expect,
			"Testing MENote .octave method. Should return octave number."
		)
	}

	/****************************************************************************************/

	test_data_isDict {
		var meNote = MENote("C", 59, \P1);

		this.assert(
			meNote.data.isKindOf(Dictionary),
			"Testing MENote .data method. Should return Dictionary."
		)
	}

	/****************************************************************************************/

	test_data_isEmpty {
		var meNote = MENote("C", 59, \P1);

		this.assert(
			meNote.data.isEmpty,
			"Testing MENote data Dictionary is empty. Should return true."
		)
	}

	/****************************************************************************************/

	test_copy_isMENote {
		var meNote = MENote("C", 59, \P1);
		var copy = meNote.copy;

		this.assert(
			copy.isKindOf(MENote),
			"Testing MENote .copy method. Should return a MENote object."
		)
	}

	/****************************************************************************************/

	test_copy_hasDifferentIdentity {
		var meNote = MENote("C", 59, \P1);
		var copy = meNote.copy;

		this.assert(
			copy !== meNote,
			"Testing copyed MENote has different identity. Should true."
		)
	}

	/****************************************************************************************/

	test_name_ASCIIWithOctave {
		var fixture = (
			expect: "Cb4",
			meNote: MENote("C", 59, \P1));

		this.assertEquals(
			fixture.meNote.name,
			fixture.expect,
			"Testing MENote .name method. Should return %.".format(fixture.expect)
		)
	}

	/****************************************************************************************/

	test_name_ASCIINoOctave {
		var fixture = (
			expect: "Cb",
			meNote: MENote("C", 59, \P1));

		this.assertEquals(
			fixture.meNote.name(\ascii, false),
			fixture.expect,
			"Testing MENote .name method. Should return %.".format(fixture.expect)
		)
	}

	/****************************************************************************************/

	test_name_UnicodeWithOctave {
		var fixture = (
			expect: "C♭4",
			meNote: MENote("C", 59, \P1));

		this.assertEquals(
			fixture.meNote.name(\unic, true),
			fixture.expect,
			"Testing MENote .name method. Should return %.".format(fixture.expect)
		)
	}

	/****************************************************************************************/

	test_name_UnicodeNoOctave {
		var fixture = (
			expect: "C♭",
			meNote: MENote("C", 59, \P1));

		this.assertEquals(
			fixture.meNote.name(\unic, false),
			fixture.expect,
			"Testing MENote .name method. Should return %.".format(fixture.expect)
		)
	}

	/****************************************************************************************/

	test_name_music21WithOctave {
		var fixture = (
			expect: "C-4",
			meNote: MENote("C", 59, \P1));

		this.assertEquals(
			fixture.meNote.name(\m21, true),
			fixture.expect,
			"Testing MENote .name method. Should return %.".format(fixture.expect)
		)
	}

	/****************************************************************************************/

	test_name_music21NoOctave {
		var fixture = (
			expect: "C-",
			meNote: MENote("C", 59, \P1));

		this.assertEquals(
			fixture.meNote.name(\m21, false),
			fixture.expect,
			"Testing MENote .name method. Should return %.".format(fixture.expect)
		)
	}

	/****************************************************************************************/

	test_sol_ASCIIWithOctave {
		var fixture = (
			expect: "Dob4",
			meNote: MENote("C", 59, \P1));

		this.assertEquals(
			fixture.meNote.sol,
			fixture.expect,
			"Testing MENote .sol method. Should return %.".format(fixture.expect)
		)
	}

	/****************************************************************************************/

	test_sol_ASCIINoOctave {
		var fixture = (
			expect: "Dob",
			meNote: MENote("C", 59, \P1));

		this.assertEquals(
			fixture.meNote.sol(\ascii, false),
			fixture.expect,
			"Testing MENote .sol method. Should return %.".format(fixture.expect)
		)
	}

	/****************************************************************************************/

	test_sol_UnicodeWithOctave {
		var fixture = (
			expect: "Do♭4",
			meNote: MENote("C", 59, \P1));

		this.assertEquals(
			fixture.meNote.sol(\unic, true),
			fixture.expect,
			"Testing MENote .sol method. Should return %.".format(fixture.expect)
		)
	}

	/****************************************************************************************/

	test_sol_UnicodeNoOctave {
		var fixture = (
			expect: "Do♭",
			meNote: MENote("C", 59, \P1));

		this.assertEquals(
			fixture.meNote.sol(\unic, false),
			fixture.expect,
			"Testing MENote .sol method. Should return %.".format(fixture.expect)
		)
	}

	/****************************************************************************************/

	test_sol_music21WithOctave {
		var fixture = (
			expect: "Do-4",
			meNote: MENote("C", 59, \P1));

		this.assertEquals(
			fixture.meNote.sol(\m21, true),
			fixture.expect,
			"Testing MENote .sol method. Should return %.".format(fixture.expect)
		)
	}

	/****************************************************************************************/

	test_sol_music21NoOctave {
		var fixture = (
			expect: "Do-",
			meNote: MENote("C", 59, \P1));

		this.assertEquals(
			fixture.meNote.sol(\m21, false),
			fixture.expect,
			"Testing MENote .sol method. Should return %.".format(fixture.expect)
		)
	}

	/****************************************************************************************/

	test_degree {
		var fixture = (
			expect: \P1,
			meNote: MENote("C", 59, \P1)
		);

		this.assertEquals(
			fixture.meNote.degree,
			fixture.expect,
			"Testing MENote .degree method. Should return %.".format(fixture.expect)
		)
	}

	/****************************************************************************************/

	test_interval {
		var meNote = MENote("C", 59, \P1);

		this.assert(
			meNote.interval.isKindOf(MEInterval),
			"Testing MENote .interval returns MEInterval object. Should return true."
		)
	}

	/****************************************************************************************/

	test_number_asFloat {
		var fixture = (
			expect: 1.2,
			meNote: MENote("C", 59, \P1)
		);

		this.assertEquals(
			fixture.meNote.number(false),
			fixture.expect,
			"Testing MENote .number method. Should return %.".format(fixture.expect)
		);
	}

	/****************************************************************************************/

	test_number_asInteger {
		var fixture = (
			expect: 1,
			meNote: MENote("C", 59, \P1)
		);

		this.assertEquals(
			fixture.meNote.number(true),
			fixture.expect,
			"Testing MENote .number method. Should return %.".format(fixture.expect)
		)
	}

	/****************************************************************************************/

	test_quality_asSymbol {
		var fixture = (
			expect: \P,
			meNote: MENote("C", 59, \P1)
		);

		this.assertEquals(
			fixture.meNote.quality(false),
			fixture.expect,
			"Testing MENote .quality method. Should return %.".format(fixture.expect)
		);
	}

	/****************************************************************************************/

	test_quality_asFloat {
		var fixture = (
			expect: '0.2',
			meNote: MENote("C", 59, \P1)
		);

		this.assertEquals(
			fixture.meNote.quality(true).asSymbol,
			fixture.expect,
			"Testing MENote .quality method. Should return %.".format(fixture.expect)
		)
	}

	/****************************************************************************************/

	test_set {
		var meNote = MENote("C", 59, \P1);

		meNote.set(\test, 1);

		this.assert(
			meNote.data.notEmpty,
			"Testing .set method. Data dict should not be empty."
		)
	}

	/****************************************************************************************/

	test_get {
		var fixture = (
			expect: 1,
			meNote: MENote("C", 59, \P1)
		);

		fixture.meNote.set(\test, 1);


		this.assertEquals(
			fixture.meNote.get(\test),
			fixture.expect,
			"Testing .get method. Should return %.".format(fixture.expect)
		)
	}

	/****************************************************************************************/

	test_clearValue {
		var fixture = (
			expect: nil,
			meNote: MENote("C", 59, \P1)
		);

		fixture.meNote.set(\test, 1);
		fixture.meNote.clearValue(\test);


		this.assertEquals(
			fixture.meNote.get(\test),
			fixture.expect,
			"Testing .clearValue method. Should return %.".format(fixture.expect)
		)
	}

	/****************************************************************************************/

	test_clearDict {
		var meNote = MENote("C", 59, \P1);

		meNote.set(\test1, 1);
		meNote.set(\test2, 2);
		meNote.clearDict;

		this.assert(
			meNote.data.isEmpty,
			"Testing .clearDict method. Should return true."
		)
	}

	/****************************************************************************************/

	test_getKeys {
		var fixture = (
			expect: Set[\test1, \test2],
			meNote: MENote("C", 59, \P1)
		);

		fixture.meNote.set(\test1, 1);
		fixture.meNote.set(\test2, 2);

		this.assertEquals(
			fixture.meNote.getKeys,
			fixture.expect,
			"Testing .getKeys method. Should return %.".format(fixture.expect)
		)
	}
}