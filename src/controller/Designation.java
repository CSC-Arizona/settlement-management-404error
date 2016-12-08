package controller;

public enum Designation {
	NONE                 ("Not doing anything",         "Doing something",     'n'),
	ATTACKING            ("Stop attacking",             "Attack",              'a'),
	CONSTRUCTING         ("Stop constructing",          "Construct",           'c'),
	DIGGING              ("Stop digging",               "Dig",                 'd'),
	GATHERING_PLANTS     ("Stop gathering plants",      "Gather plants",       'g'),
	UPGRADING            ("Stop upgrading room",        "Upgrade room",        'u'),
	REMOVING_FURNITURE   ("Stop removing furniture",    "Remove furniture",    'r'),
	CUTTING_DOWN_TREES   ("Stop cutting trees",         "Cut trees",           't');

	public String active;
	public String inactive;
	public char keyboardShortcut;

	private Designation(String active, String inactive, char keyboardShortcut) {
		this.active = active;
		this.inactive = inactive;
		this.keyboardShortcut = keyboardShortcut;
	}
}
