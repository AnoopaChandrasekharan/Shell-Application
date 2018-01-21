package edu.umb.cs.cs680.hw11.visitor;

import edu.umb.cs.cs680.hw11.Element.Directory;
import edu.umb.cs.cs680.hw11.Element.File;
import edu.umb.cs.cs680.hw11.Element.Link;

public interface FSVisitor {
	public void visit(Link link);
	public void visit(Directory dir);
	public void visit(File file);

}
