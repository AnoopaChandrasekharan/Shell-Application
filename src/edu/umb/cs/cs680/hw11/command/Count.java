package edu.umb.cs.cs680.hw11.command;

import edu.umb.cs.cs680.hw11.FileSystem;

public class Count implements Command{
	
	String type = "d";
	
	public Count(String t){
		type = t;
	}

	@Override
	public String execute() {
		FileSystem fs = FileSystem.getInstance();
		switch(type){
		case "d":
			int found = fs.getDirectoryCount(fs.getCurrent()) - 1 > 0 ? fs.getDirectoryCount(fs.getCurrent()) - 1 : 0;
			return String.valueOf(found) + " directories found"; 
		case "f":
			return String.valueOf(fs.getFileCount(fs.getCurrent())) + " files found";
		case "l":
			return String.valueOf(fs.getLinkCount(fs.getCurrent())) + " links found";
		}
		
		return "";
	}

}
