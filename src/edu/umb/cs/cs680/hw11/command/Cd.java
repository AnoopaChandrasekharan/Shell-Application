package edu.umb.cs.cs680.hw11.command;

import edu.umb.cs.cs680.hw11.FileSystem;
import edu.umb.cs.cs680.hw11.Element.Directory;
import edu.umb.cs.cs680.hw11.Element.FSElement;
import edu.umb.cs.cs680.hw11.Element.Link;

public class Cd implements Command{
	
	String arg = "";
	public Cd(String a){
		arg = a;
	}

	@Override
	public String execute(){
		// TODO Auto-generated method stub
		FileSystem fs = FileSystem.getInstance();
		if(arg.equals("")){
			fs.setCurrent(fs.getRoot());
		}else if(arg.equals("..")){
			if(fs.getCurrent().getParent() != null){
				fs.setCurrent(fs.getCurrent().getParent());
			}
		}else{
			FSElement found = fs.getCurrent().getElementAtPath(arg);
			if(found instanceof Directory){
				fs.setCurrent((Directory)found);
			}else if(found instanceof Link){
				found = ((Link)found).getTarget();
				if(found instanceof Directory){
					fs.setCurrent((Directory)found);
				}else{
					return arg + " not found";
				}
			}else{
				return arg + " not found";
			}
		}
		return "";
	}

}
