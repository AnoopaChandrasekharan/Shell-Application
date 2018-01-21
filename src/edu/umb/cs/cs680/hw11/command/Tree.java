package edu.umb.cs.cs680.hw11.command;

import edu.umb.cs.cs680.hw11.FileSystem;

public class Tree implements Command{

	@Override
	public String execute(){
		FileSystem fs = FileSystem.getInstance();
		fs.showAllElements();
		return "";
	}

}
