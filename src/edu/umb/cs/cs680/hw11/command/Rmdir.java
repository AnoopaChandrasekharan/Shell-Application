package edu.umb.cs.cs680.hw11.command;

import edu.umb.cs.cs680.hw11.FileSystem;

public class Rmdir implements Command{

	String arg;
	
	public Rmdir(String a){
		arg = a;
	}
	
	@Override
	public String execute(){
		FileSystem fs = FileSystem.getInstance();
		fs.getCurrent().removeChild(arg, "D");
		return arg + " removed";
	}

}
