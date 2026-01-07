MEDebug {
	classvar <debug = false;
	classvar <count = nil;
	classvar <scope = nil;
	classvar <level1 = false;
	classvar <level2 = false;
	classvar <level3 = false;
	classvar <>showData = false;
	classvar <>validate = false;

	*initClass {
		super.initClass;
	}

	/****************************************************************************************/

	*debug_ { |value|

		debug  = value;
		count  = 0;
		level1 = false;
		level2 = false;
		level3 = false;
	}

	/****************************************************************************************/

	*scope_ { |value|

		scope = value;

		switch (value)
		{ 1 } { level1 = true }
		{ 2 } { level2 = true }
		{ 3 } { level1 = true; level2 = true }
		{ 4 } { level3 = true }
		{ 5 } { level1 = true; level3 = true }
		{ 6 } { level2 = true; level3 = true }
		{ 7 } { level1 = true; level2 = true; level3 = true };
	}

	/****************************************************************************************/

	*printValues { |method, in, out|

		"#%".format(count).padRight(5).post;
		"@%".format(method.asString.replace("Meta_", "")).postln;

		if (in.notNil && (showData == true)) {
			in.do { |v|
				"in: ".padRight(5).post;
				"%".format(v).postln;
			};
		};
		if (out.notNil && (showData == true)) {
			out.do { |v|
				"out: ".padRight(5).post;
				"%".format(v).postln;
			};
		};
		if ((scope == 1) && (showData == true)) {
			"".postln;
		};
		count = count + 1;
	}

	/****************************************************************************************/

	*log { |method, level, in = nil, out = nil, showData = true|

		if (debug) {

			switch(level)
			{ 1 } { if (level1) { this.printValues(method, in, out, showData) } }
			{ 2 } { if (level2) { this.printValues(method, in, out, showData) } }
			{ 3 } { if (level3) { this.printValues(method, in, out, showData) } };
		};
	}
}