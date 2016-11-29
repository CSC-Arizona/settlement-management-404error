package controller;

public enum Designation {
	NONE                 ("Not doing anything",         "Doing something",     'n'),
	ATTACKING            ("Stop attacking",             "Attack",              'a'),
	DIGGING              ("Stop digging",               "Dig",                 'd'),
	REMOVING_DESIGNATIONS("Stop removing designations", "Remove designations", 'e'),
	GATHERING_FRUIT      ("Stop gathering fruit",       "Gather fruit",        'f'),
	GATHERING_PLANTS     ("Stop gathering plants",      "Gather plants",       'g'),
	REMOVING_ROOMS       ("Stop removing rooms",        "Remove rooms",        'q'),
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