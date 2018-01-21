package edu.umb.cs.cs680.hw11.command;

import edu.umb.cs.cs680.hw11.FileSystem;

public class Pwd implements Command{

	public Pwd(){	
	}
	
	public String execute(){
		FileSystem fs = FileSystem.getInstance();
		return fs.getCurrent().getName();
	}

}
