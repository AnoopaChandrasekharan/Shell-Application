package edu.umb.cs.cs680.hw11.command;

import edu.umb.cs.cs680.hw11.FileSystem;
import edu.umb.cs.cs680.hw11.Element.FSElement;

public class Chown implements Command{
    String owner;
    String target;
    
    public Chown(String o, String t){
    	owner = o;
    	target = t;
    }
	@Override
	public String execute() {
		FileSystem fs = FileSystem.getInstance();
		FSElement f = fs.getCurrent().getChildByName(target);
		if(f != null){
			f.setOwner(owner);
		}else{
			return target + " not found";
		}
		return "";
	}

}
