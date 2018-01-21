package edu.umb.cs.cs680.hw11.command;

import edu.umb.cs.cs680.hw11.FileSystem;
import edu.umb.cs.cs680.hw11.Element.Directory;
import edu.umb.cs.cs680.hw11.Element.FSElement;

public class Cp implements Command{

	String source;
	String target;
	
	public Cp(String s, String t){
		source = s;
		target = t;
	}
	
	@Override
	public String execute(){
		FileSystem fs = FileSystem.getInstance();
		FSElement s = fs.getCurrent().getElementAtPath(source);
		FSElement t = fs.getCurrent().getElementAtPath(target);
		if(t instanceof Directory){
			if(s != null){
				if(s instanceof Directory){
					s = ((Directory)s).getClone();
				}
				s.setParent(((Directory)t));
				((Directory)t).appendChild(s);
			}else{
				return source + " not found";
			}
		}else{
			return target + " not found";
		}
		return source + " copied to " + target;
	}

}
