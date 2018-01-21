package edu.umb.cs.cs680.hw11.visitor;

import edu.umb.cs.cs680.hw11.Element.Directory;
import edu.umb.cs.cs680.hw11.Element.File;
import edu.umb.cs.cs680.hw11.Element.Link;

public class PrintVisitor implements FSVisitor{
	
	public boolean dirOnly;
	public boolean showInfo;
	
	public PrintVisitor(boolean d, boolean s){
		dirOnly = d;
		showInfo = s;
	}

	@Override
	public void visit(Link link) {
		if(!dirOnly){
			if(showInfo){
				link.printInfo();
			}else{
				System.out.println(link.getName());
			}
		}

	}

	@Override
	public void visit(Directory dir) {
		if(showInfo){
			dir.printInfo();
		}else{
			System.out.println(dir.getName());
		}
	}

	@Override
	public void visit(File file) {
		if(!dirOnly){
			if(showInfo){
				file.printInfo();
			}else{
				System.out.println(file.getName());
			}
		}
	}

}
