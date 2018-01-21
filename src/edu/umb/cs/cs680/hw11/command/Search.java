package edu.umb.cs.cs680.hw11.command;

import edu.umb.cs.cs680.hw11.FileSystem;

public class Search implements Command{

	String keyword;
	
	public Search(String k){
		keyword = k;
	}
	
	@Override
	public String execute() {
		FileSystem fs = FileSystem.getInstance();
		if(!keyword.equals("") && keyword != null){
			fs.searchInDirectory(fs.getCurrent(), keyword);
		}
		return "";
	}

}
