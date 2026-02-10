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

	test_getASCIISign_ascii {
		var fixtures = [
			(signOffset: -5, expect: "bbbbb"),
			(signOffset: -4, expect: "bbbb"),
			(signOffset: -3, expect: "bbb"),
			(signOffset: -2, expect: "bb"),
			(signOffset: -1, expect: "b"),
			(signOffset: 0, expect: ""),
			(signOffset: 1, expect: "#"),
			(signOffset: 2, expect: "##"),
			(signOffset: 3, expect: "###"),
			(signOffset: 4, expect: "####"),
			(signOffset: 5, expect: "#####"),
		];
		var charSet = \ansii;

		fixtures.do { |f|

			this.assertEquals(
				MEAccidental.getASCIISign(f.signOffset, charSet),
				f.expect,
				"Testing sign offset: %. Should return ASCII string: %."
				.format(f.signOffset, f.expect)
			)
		};
	}

	/****************************************************************************************/

	test_getASCIISign_m21 {
		var fixtures = [
			(signOffset: -5, expect: "-----"),
			(signOffset: -4, expect: "----"),
			(signOffset: -3, expect: "---"),
			(signOffset: -2, expect: "--"),
			(signOffset: -1, expect: "-"),
			(signOffset: 0, expect: ""),
			(signOffset: 1, expect: "#"),
			(signOffset: 2, expect: "##"),
			(signOffset: 3, expect: "###"),
			(signOffset: 4, expect: "####"),
			(signOffset: 5, expect: "#####"),
		];
		var charSet = \m21;

		fixtures.do { |f|

			this.assertEquals(
				MEAccidental.getASCIISign(f.signOffset, charSet),
				f.expect,
				"Testing sign offset: %. Should return ASCII string: %."
				.format(f.signOffset, f.expect)
			)
		};
	}

	/****************************************************************************************/

	test_getANSISign_ansi {
		var fixtures = [
			(signOffset: -5, expect: "𝄫𝄫♭"),
			(signOffset: -4, expect: "𝄫𝄫"),
			(signOffset: -3, expect: "𝄫♭"),
			(signOffset: -2, expect: "𝄫"),
			(signOffset: -1, expect: "♭"),
			(signOffset: 0, expect: ""),
			(signOffset: 1, expect: "♯"),
			(signOffset: 2, expect: "𝄪"),
			(signOffset: 3, expect: "𝄪♯"),
			(signOffset: 4, expect: "𝄪𝄪"),
			(signOffset: 5, expect: "𝄪𝄪♯"),
		];

		fixtures.do { |f|

			this.assertEquals(
				MEAccidental.getANSISign(f.signOffset),
				f.expect,
				"Testing sign offset: %. Should return ANSI string: %."
				.format(f.signOffset, f.expect)
			)
		};
	}

	/****************************************************************************************/

	test_getSignFromOffset_validInput {
		var fixtures = [
			(signOffset: -5, charSet: \ascii, expect: "bbbbb"),
			(signOffset: -5, charSet: \m21, expect: "-----"),
			(signOffset: -5, charSet: \ansi, expect: "𝄫𝄫♭"),
			(signOffset: 5, charSet: \ascii, expect: "#####"),
			(signOffset: 5, charSet: \m21, expect: "#####"),
			(signOffset: 5, charSet: \ansi, expect: "𝄪𝄪♯"),
			(signOffset: 0, charSet: \ascii, expect: ""),
			(signOffset: 0, charSet: \m21, expect: ""),
			(signOffset: 0, charSet: \ansi, expect: "")
		];

		fixtures.do { |f|

			this.assertEquals(
				MEAccidental.getSignFromOffset(f.signOffset, charSet: f.charSet),
				f.expect,
				"Testing valid sign offset: %. Should return: %."
				.format(f.signOffset, f.expect)
			);
		}
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
		var meAccidental;

		fixtures.do { |f|

			this.assertEquals(
				MEAccidental(f.noteLetter, f.midiNote).offset,
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
		var meAccidental;

		fixtures.do { |f|

			this.assertEquals(
				MEAccidental(f.noteLetter, f.midiNote).offset,
				f.expect,
				"Instantiating MEAccidental with letter: % and midi: %. Should return: %."
				.format(f.noteLetter, f.midiNote, f.expect)
			);
		};
	}

	/****************************************************************************************/
}