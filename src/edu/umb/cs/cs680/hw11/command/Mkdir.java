package edu.umb.cs.cs680.hw11.command;

import java.time.LocalDateTime;

import edu.umb.cs.cs680.hw11.FileSystem;
import edu.umb.cs.cs680.hw11.Element.Directory;

public class Mkdir implements Command{

	String arg;
	
	public Mkdir(String a){
		arg = a;
	}
	
	@Override
	public String execute(){
		FileSystem fs = FileSystem.getInstance();
		if(arg.equals("")){
			return "No directory name provided";
		}else{
			Directory d = new Directory(fs.getCurrent(), arg , "abc", LocalDateTime.now(), LocalDateTime.now(), 300);
			fs.getCurrent().appendChild(d);
			return "New directory " + arg + " created";
		}
	}

}
