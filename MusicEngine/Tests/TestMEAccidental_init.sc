/*********************************************************************************************
* MusicEngine - A dynamic music library for SuperCollider   								 *
* Copyright (C) 2025 Siriil									    							 *
* Licensed under GPLv3. See LICENSE file for details.			    						 *
*********************************************************************************************/

TestMEAccidental_init : UnitTest {

	test_init_objectInstantiation {
		var fixture      = (noteLetter: "C", midiNote: 60);
		var meAccidental = MEAccidental(fixture.noteLetter, fixture.midiNote);

		this.assert(
			meAccidental.isKindOf(MEAccidental),
			"Instantiating MEAccidental. Should return % object."
			.format(meAccidental)
		);
	}

	/****************************************************************************************/

	test_offset_sharp {
		var fixtures = [
			(noteLetter: "C", midiNote: 60, expect: 0),
			(noteLetter: "C", midiNote: 61, expect: 1),
			(noteLetter: "C", midiNote: 62, expect: 2),
			(noteLetter: "C", midiNote: 63, expect: 3),
			(noteLetter: "C", midiNote: 64, expect: 4),
			(noteLetter: "C", midiNote: 65, expect: 5)
		];

		fixtures.do { |f|

			this.assertEquals(
				MEAccidental(f.noteLetter, f.midiNote, validate: true).offset,
				f.expect,
				"Instantiating MEAccidental with letter: % and midi: %. Should return: %."
				.format(f.noteLetter, f.midiNote, f.expect)
			);
		};
	}

	/****************************************************************************************/

	test_offset_flat {
		var fixtures = [
			(noteLetter: "C", midiNote: 60, expect: 0),
			(noteLetter: "C", midiNote: 59, expect: -1),
			(noteLetter: "C", midiNote: 58, expect: -2),
			(noteLetter: "C", midiNote: 57, expect: -3),
			(noteLetter: "C", midiNote: 56, expect: -4),
			(noteLetter: "C", midiNote: 55, expect: -5)
		];

		fixtures.do { |f|

			this.assertEquals(
				MEAccidental(f.noteLetter, f.midiNote, validate: true).offset,
				f.expect,
				"Instantiating MEAccidental with letter: % and midi: %. Should return: %."
				.format(f.noteLetter, f.midiNote, f.expect)
			);
		};
	}

	/****************************************************************************************/

	test_sign_ascii {
		var fixtures = [
			(noteLetter: "C", midiNote: 59, charSet: \ascii, expect: "b"),
			(noteLetter: "C", midiNote: 60, charSet: \ascii, expect: ""),
			(noteLetter: "C", midiNote: 61, charSet: \ascii, expect: "#"),
		];

		fixtures.do { |f|

			this.assertEquals(
				MEAccidental(f.noteLetter, f.midiNote, validate: true).sign(f.charSet),
				f.expect,
				"Instantiating MEAccidental with letter: % and midi: %. Should return: %."
				.format(f.noteLetter, f.midiNote, f.expect)
			);
		};
	}

	/****************************************************************************************/

	test_sign_m21 {
		var fixtures = [
			(noteLetter: "C", midiNote: 59, charSet: \m21, expect: "-"),
			(noteLetter: "C", midiNote: 60, charSet: \m21, expect: ""),
			(noteLetter: "C", midiNote: 61, charSet: \m21, expect: "#"),
		];

		fixtures.do { |f|

			this.assertEquals(
				MEAccidental(f.noteLetter, f.midiNote, validate: true).sign(f.charSet),
				f.expect,
				"Instantiating MEAccidental with letter: % and midi: %. Should return: %."
				.format(f.noteLetter, f.midiNote, f.expect)
			);
		};
	}

	/****************************************************************************************/

	test_sign_unic {
		var fixtures = [
			(noteLetter: "C", midiNote: 59, charSet: \unic, expect: "♭"),
			(noteLetter: "C", midiNote: 60, charSet: \unic, expect: ""),
			(noteLetter: "C", midiNote: 61, charSet: \unic, expect: "♯"),
		];

		fixtures.do { |f|

			this.assertEquals(
				MEAccidental(f.noteLetter, f.midiNote, validate: true).sign(f.charSet),
				f.expect,
				"Instantiating MEAccidental with letter: % and midi: %. Should return: %."
				.format(f.noteLetter, f.midiNote, f.expect)
			);
		};
	}
}