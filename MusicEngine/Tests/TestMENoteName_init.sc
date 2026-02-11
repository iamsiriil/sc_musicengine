/*********************************************************************************************
* MusicEngine - A dynamic music library for SuperCollider   								 *
* Copyright (C) 2025 Siriil									    							 *
* Licensed under GPLv3. See LICENSE file for details.			    						 *
*********************************************************************************************/

TestMENoteName_init : UnitTest {

	test_init_objectInstantiation {
		var fixture  = (noteLetter: "C", midiNote: 60);
		var noteName = MENoteName(fixture.noteLetter, fixture.midiNote);

		this.assert(
			noteName.isKindOf(MENoteName),
			"Instantiating a MENoteName object. Should return an object."
		);
	}

	/****************************************************************************************/
	/****************************************************************************************/

	test_letter_validInput {
		var fixture = (noteLetter: "C", midiNote: 60, expect: "C");

		this.assertEquals(
			MENoteName(fixture.noteLetter, fixture.midiNote, validate: true).letter,
			fixture.expect,
			"Instantiating MENoteName with letter: %, midi: %. .letter should return: %."
			.format(fixture.noteName, fixture.midiNote, fixture.expect)
		);
	}

	/****************************************************************************************/
	/****************************************************************************************/

	test_accidental_validInput {
		var fixture = (noteLetter: "C", midiNote: 60, expect: MEAccidental);
		var meAccidental = MENoteName(
			fixture.noteLetter,
			fixture.midiNote,
			validate: true).accidental;

		this.assert(
			meAccidental.isKindOf(fixture.expect),
			"Instantiating MENoteName with letter: %, midi: %. .accidental should return: %."
			.format(fixture.noteName, fixture.midiNote, fixture.expect)
		);
	}

	/****************************************************************************************/
	/****************************************************************************************/

	test_name_asciiSharp {
		var fixture = (noteLetter: "C", midiNote: 61, charSet: \ascii, expect: "C#");

		this.assertEquals(
			MENoteName(fixture.noteLetter, fixture.midiNote, validate: true).name(fixture.charSet),
			fixture.expect,
			"Instantiating MENoteName with letter: %, midi: %. .name should return: %."
			.format(fixture.noteName, fixture.midiNote, fixture.expect)
		);
	}

	/****************************************************************************************/

	test_name_asciiFlat {
		var fixture = (noteLetter: "C", midiNote: 59, charSet: \ascii, expect: "Cb");

		this.assertEquals(
			MENoteName(fixture.noteLetter, fixture.midiNote, validate: true).name(fixture.charSet),
			fixture.expect,
			"Instantiating MENoteName with letter: %, midi: %. .name should return: %."
			.format(fixture.noteName, fixture.midiNote, fixture.expect)
		);
	}

	/****************************************************************************************/

	test_name_m21Sharp {
		var fixture = (noteLetter: "C", midiNote: 61, charSet: \m21, expect: "C#");

		this.assertEquals(
			MENoteName(fixture.noteLetter, fixture.midiNote, validate: true).name(fixture.charSet),
			fixture.expect,
			"Instantiating MENoteName with letter: %, midi: %. .name should return: %."
			.format(fixture.noteName, fixture.midiNote, fixture.expect)
		);
	}

	/****************************************************************************************/

	test_name_m21Flat {
		var fixture = (noteLetter: "C", midiNote: 59, charSet: \m21, expect: "C-");

		this.assertEquals(
			MENoteName(fixture.noteLetter, fixture.midiNote, validate: true).name(fixture.charSet),
			fixture.expect,
			"Instantiating MENoteName with letter: %, midi: %. .name should return: %."
			.format(fixture.noteName, fixture.midiNote, fixture.expect)
		);
	}

	/****************************************************************************************/

	test_name_ansiSharp {
		var fixture = (noteLetter: "C", midiNote: 61, charSet: \ansi, expect: "C♯");

		this.assertEquals(
			MENoteName(fixture.noteLetter, fixture.midiNote, validate: true).name(fixture.charSet),
			fixture.expect,
			"Instantiating MENoteName with letter: %, midi: %. .name should return: %."
			.format(fixture.noteName, fixture.midiNote, fixture.expect)
		);
	}

	/****************************************************************************************/

	test_name_ansiFlat {
		var fixture = (noteLetter: "C", midiNote: 59, charSet: \ansi, expect: "C♭");

		this.assertEquals(
			MENoteName(fixture.noteLetter, fixture.midiNote, validate: true).name(fixture.charSet),
			fixture.expect,
			"Instantiating MENoteName with letter: %, midi: %. .name should return: %."
			.format(fixture.noteName, fixture.midiNote, fixture.expect)
		);
	}

	/****************************************************************************************/
	/****************************************************************************************/

	test_solfege_asciiSharp {
		var fixture = (noteLetter: "C", midiNote: 61, charSet: \ascii, expect: "Do#");

		this.assertEquals(
			MENoteName(fixture.noteLetter, fixture.midiNote, validate: true)
			.solfege(fixture.charSet),
			fixture.expect,
			"Instantiating MENoteName with letter: %, midi: %. .name should return: %."
			.format(fixture.noteName, fixture.midiNote, fixture.expect)
		);
	}

	/****************************************************************************************/

	test_solfege_asciiFlat {
		var fixture = (noteLetter: "C", midiNote: 59, charSet: \ascii, expect: "Dob");

		this.assertEquals(
			MENoteName(fixture.noteLetter, fixture.midiNote, validate: true)
			.solfege(fixture.charSet),
			fixture.expect,
			"Instantiating MENoteName with letter: %, midi: %. .name should return: %."
			.format(fixture.noteName, fixture.midiNote, fixture.expect)
		);
	}

	/****************************************************************************************/

	test_solfege_m21Sharp {
		var fixture = (noteLetter: "C", midiNote: 61, charSet: \m21, expect: "Do#");

		this.assertEquals(
			MENoteName(fixture.noteLetter, fixture.midiNote, validate: true)
			.solfege(fixture.charSet),
			fixture.expect,
			"Instantiating MENoteName with letter: %, midi: %. .name should return: %."
			.format(fixture.noteName, fixture.midiNote, fixture.expect)
		);
	}

	/****************************************************************************************/

	test_solfege_m21Flat {
		var fixture = (noteLetter: "C", midiNote: 59, charSet: \m21, expect: "Do-");

		this.assertEquals(
			MENoteName(fixture.noteLetter, fixture.midiNote, validate: true)
			.solfege(fixture.charSet),
			fixture.expect,
			"Instantiating MENoteName with letter: %, midi: %. .name should return: %."
			.format(fixture.noteName, fixture.midiNote, fixture.expect)
		);
	}

	/****************************************************************************************/

	test_solfege_ansiSharp {
		var fixture = (noteLetter: "C", midiNote: 61, charSet: \ansi, expect: "Do♯");

		this.assertEquals(
			MENoteName(fixture.noteLetter, fixture.midiNote, validate: true)
			.solfege(fixture.charSet),
			fixture.expect,
			"Instantiating MENoteName with letter: %, midi: %. .name should return: %."
			.format(fixture.noteName, fixture.midiNote, fixture.expect)
		);
	}

	/****************************************************************************************/

	test_solfege_ansiFlat {
		var fixture = (noteLetter: "C", midiNote: 59, charSet: \ansi, expect: "Do♭");

		this.assertEquals(
			MENoteName(fixture.noteLetter, fixture.midiNote, validate: true)
			.solfege(fixture.charSet),
			fixture.expect,
			"Instantiating MENoteName with letter: %, midi: %. .name should return: %."
			.format(fixture.noteName, fixture.midiNote, fixture.expect)
		);
	}
}