package edu.umb.cs.cs680.hw11.command;

import edu.umb.cs.cs680.hw11.FileSystem;
import edu.umb.cs.cs680.hw11.Element.Directory;
import edu.umb.cs.cs680.hw11.Element.FSElement;

public class Dir implements Command{

	String arg = "";
	
	public Dir(String a){
		arg = a;
	}
	
	@Override
	public String execute(){
		FileSystem fs = FileSystem.getInstance();
		if(arg.equals("")){
			fs.getCurrent().printChildren(false, true);
		}else if(arg.equals("..")){
			Directory parent = fs.getCurrent().getParent();
			if(parent != null){
				parent.printInfo();
			}
		}else{
			FSElement found = fs.getCurrent().getChildByNameAndType(arg, "D");
			if(found instanceof Directory){
				found.printInfo();
			}else{
				return arg + " not found";
			}
		}
		return "";
	}
}
