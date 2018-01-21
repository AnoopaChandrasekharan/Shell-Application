package edu.umb.cs.cs680.hw11.visitor;

import java.util.ArrayList;

import edu.umb.cs.cs680.hw11.Element.Directory;
import edu.umb.cs.cs680.hw11.Element.FSElement;
import edu.umb.cs.cs680.hw11.Element.File;
import edu.umb.cs.cs680.hw11.Element.Link;

public class VirusCheckingVisitor implements FSVisitor {

	int quarantined=0;
	ArrayList <File> infectedFiles = new ArrayList<File>();
	public VirusCheckingVisitor(){
		//f.getVirusFlag();
	}
	@Override
	public void visit(Link link) {
	}

	@Override
	public void visit(Directory dir) {
	}

	@Override
	public void visit(File file) {
		if(file.getVirusFlag()==true){
			quarantined++;
			infectedFiles.add(file);
		}
		
	}
	public int getQuarantinedNum(){
		return quarantined;
	}
	public void printAffectedFiles(){
		System.out.println(quarantined + " infected files found -");
		for(File f: infectedFiles){
			System.out.println(f.getFullPath());
		}
	}

}
