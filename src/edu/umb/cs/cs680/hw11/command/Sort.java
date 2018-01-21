package edu.umb.cs.cs680.hw11.command;

import java.util.Comparator;

import edu.umb.cs.cs680.hw11.FileSystem;
import edu.umb.cs.cs680.hw11.Element.FSElement;
import edu.umb.cs.cs680.hw11.comparator.AlphabeticalComparator;
import edu.umb.cs.cs680.hw11.comparator.TimeStampComparator;

public class Sort implements Command{

	String policy;
	
	public Sort(String p){
		policy = p;
	}
	
	@Override
	public String execute() {
		FileSystem fs = FileSystem.getInstance();
		Comparator<FSElement> c = new AlphabeticalComparator();
		if(policy.equals("t")){
			c = new TimeStampComparator();
		}
		fs.sort(fs.getCurrent(), c);
		return "";
	}

}
