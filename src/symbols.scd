/*****************************************************************************************
* MUSIC ENGINE / Symbols                                                                 *
*****************************************************************************************/
(
~test1 = "C#M3P5m7M9P11M13";
~test2 = "Eb"
)

(
~parseSymbol = { |symbol|
	var arr =
};
)

(
~sym = "DbM2P5m7";
~not = "1^[0-9]|8|15|.1^[0-9]|.8|.15".matchRegexp(~sym);
~arr = ~sym.findRegexp(".2|.3|.4|.5|.6|.7|.9|.11|.12|.13|.14").collect { |n| n[1]};
)

/*
~parse symbol
-> get root
-> test if root is valid
-> get degrees
-> testif degrees are valid
-> save root as string
-> save degrees in array
*/