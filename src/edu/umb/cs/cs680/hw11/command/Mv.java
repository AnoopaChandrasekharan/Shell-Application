package edu.umb.cs.cs680.hw11.command;

import edu.umb.cs.cs680.hw11.FileSystem;
import edu.umb.cs.cs680.hw11.Element.Directory;
import edu.umb.cs.cs680.hw11.Element.FSElement;

public class Mv implements Command{

	String source;
	String target;
	
	public Mv(String s, String t){
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
				Directory currParent = s.getParent();
				if(s instanceof Directory){
					Directory sClone = ((Directory)s).getClone();
					sClone.setParent(((Directory)t));
					fs.addChild((Directory)t, sClone);
				} else {
					fs.addChild((Directory)t, s);
				}
				currParent.removeChild(s.getName(), s.getType());
			}else{
				return source + " not found";
			}
		}else{
			return target + " not found";
		}
		return source + " moved to " + target;
	}

}
