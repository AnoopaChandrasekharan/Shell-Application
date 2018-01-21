package edu.umb.cs.cs680.hw11.command;

import java.time.LocalDateTime;

import edu.umb.cs.cs680.hw11.FileSystem;
import edu.umb.cs.cs680.hw11.Element.FSElement;
import edu.umb.cs.cs680.hw11.Element.Link;

public class Ln implements Command{

	String target = "";
	String name = "";
	
	public Ln(String t, String n){
		target = t;
		name = n;
	}
	
	@Override
	public String execute(){
		FileSystem fs = FileSystem.getInstance();
		FSElement targetElement = fs.getCurrent().getElementAtPath(target);
		if(targetElement == null || targetElement.getType().equals("L")){
			return "Target not found";
		}
		Link l= new Link(fs.getCurrent(),name,"efg", LocalDateTime.now(),LocalDateTime.now(), 0);
		l.setTarget(targetElement);
		fs.addChild(fs.getCurrent(), l);
		return "";
		
	}

}
