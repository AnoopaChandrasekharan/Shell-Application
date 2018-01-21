package edu.umb.cs.cs680.hw11.visitor;

import java.util.ArrayList;

import edu.umb.cs.cs680.hw11.Element.Directory;
import edu.umb.cs.cs680.hw11.Element.File;
import edu.umb.cs.cs680.hw11.Element.Link;

public class FileSearchVisitor implements FSVisitor {
	String extension;
	ArrayList<File> filesFound;

	public FileSearchVisitor(String e){
		extension = e;
		filesFound = new ArrayList<File>();
	}
	
	public ArrayList<File> getFoundFiles(){
		return filesFound;
	}
	@Override
	public void visit(Link link) {


	}

	@Override
	public void visit(Directory dir) {

	}

	@Override
	public void visit(File file) {
		if(file.getName().contains(extension)){
			filesFound.add(file);
		}
	}
	
	public ArrayList<File> getFilesFound(){
		return filesFound;
	}

}
