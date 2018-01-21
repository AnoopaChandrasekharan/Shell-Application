package edu.umb.cs.cs680.hw11.comparator;

import java.util.Comparator;

import edu.umb.cs.cs680.hw11.Element.FSElement;

public class TimeStampComparator implements Comparator<FSElement> {

	@Override
	public int compare(FSElement o1, FSElement o2) {
		return o2.getCreated().compareTo(o1.getCreated());
	}

}
