package edu.umb.cs.cs680.hw11.command;

import edu.umb.cs.cs680.hw11.FileSystem;

public class Ls implements Command{

	@Override
	public String execute(){
		FileSystem fs = FileSystem.getInstance();
		fs.getCurrent().printChildren(false, false);
		return "";
	}

}
