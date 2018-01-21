package edu.umb.cs.cs680.hw11.visitor;

import edu.umb.cs.cs680.hw11.Element.Directory;
import edu.umb.cs.cs680.hw11.Element.File;
import edu.umb.cs.cs680.hw11.Element.Link;

public class CountingVisitor implements FSVisitor {
	int dirNum=0;
	int fileNum=0;
	int linkNum=0;

	@Override
	public void visit(Link link) {
		linkNum++;
	}

	@Override
	public void visit(Directory dir) {
		dirNum++;
	}

	@Override
	public void visit(File file) {
		fileNum++;
	}
	
	public int getDirNum(){
		return dirNum;
	}
	
	public int getfileNum(){
		return fileNum;
	}
	
	public int getLinkNum(){
		return linkNum;
	}

}
