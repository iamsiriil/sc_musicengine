MEAliasValidators {

	*checkMEAlias { |alias|

		if (MEAlias.getSymbolFromAlias(alias).notNil) {
			Error("Alias '%' already in use by MEAlias class.".format(alias)).throw;
		};
		^nil;
	}

	*checkIntervals { |alias|
		var intervals = MECore.intervals;

		intervals.do { |s|
			if (s.includes(alias)) {
				Error("Interval '%' is not valid as an alias.".format(alias)).throw;
			};
		};
		^nil;
	}

	*aliasIsValid { |alias|
		this.checkMEAlias(alias);
		this.checkIntervals(alias);
		^nil;
	}
}