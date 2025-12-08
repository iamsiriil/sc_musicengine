MEDebug {
	classvar <>debug;
	classvar <>count;

	*initClass {

		debug = false;
		count = 0;
		super.initClass;
	}

	*log { |class, method, message = nil|

		if (debug) {

			"#%".format(count).padRight(5).post;
			"@%.".format(class).post;

			if (message.notNil) {
				"%".format(method).post;
				"%".format(message).padRight(18).postln;
			} {
				"%".format(method).postln;
			};

			count = count + 1;
		}
	}
}