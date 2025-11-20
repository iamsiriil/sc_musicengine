MEDebug {
	classvar <>debug;
	classvar <>count;

	*initClass {

		debug = false;
		count = 0;
		super.initClass;
	}

	*log { |class, method|

		if (debug) {

			"#%".format(count).padRight(5).post;
			"| %".format(class).padRight(16).post;
			"| %".format(method).padRight(10).postln;

			count = count + 1;
		}
	}
}