package edu.umb.cs.cs680.hw11;

import java.util.ArrayList;
import java.util.Comparator;

import edu.umb.cs.cs680.hw11.Element.Directory;
import edu.umb.cs.cs680.hw11.Element.FSElement;
import edu.umb.cs.cs680.hw11.Element.File;
import edu.umb.cs.cs680.hw11.comparator.AlphabeticalComparator;
import edu.umb.cs.cs680.hw11.visitor.CountingVisitor;
import edu.umb.cs.cs680.hw11.visitor.FileSearchVisitor;
import edu.umb.cs.cs680.hw11.visitor.PrintVisitor;
import edu.umb.cs.cs680.hw11.visitor.VirusCheckingVisitor;

public class FileSystem {
	public static FileSystem instance=null;
	public Directory rootDir = null;
	public Directory currentDir = null;
	public Comparator<FSElement> sortPolicy = new AlphabeticalComparator();
	
	private FileSystem(){
	//private constructor for singleton	
	}
	
	public static FileSystem getInstance(){
		if(instance==null){
			instance= new FileSystem();
			return instance;
		}else{
			return instance;
		}	
	}
	
	public Directory getRoot() {
		return rootDir;
	}
	
	public void showAllElements(){
		rootDir.showElement("");
	}
	
	public void setRoot(Directory root){
		rootDir = root;
		currentDir = rootDir;
	}
	
	public void setCurrent(Directory newCurrent) {
		currentDir = newCurrent;
	}
	
	public Directory getCurrent(){
		return currentDir;
	}
	
	public void addChild(Directory p, FSElement c){
		p.appendChild(c);
		c.setParent(p);
		p.getChildren().sort(sortPolicy);
	}
	
	public ArrayList<FSElement> getChildren(){
		return currentDir.getChildren();
	}
	
	public void sort(Directory d, Comparator<FSElement> c){
		d.getChildren().sort(c);
	}
	
	public void printChildren(Directory d, boolean showInfo, boolean dirOnly){
		PrintVisitor p = new PrintVisitor(showInfo, dirOnly);
		d.accept(p);
	}
	
	public int getFileCount(Directory d){
		CountingVisitor c = new CountingVisitor();
		d.accept(c);
		return c.getfileNum();
	}
	
	public int getDirectoryCount(Directory d){
		CountingVisitor c = new CountingVisitor();
		d.accept(c);
		return c.getDirNum();
	}
	
	public int getLinkCount(Directory d){
		CountingVisitor c = new CountingVisitor();
		d.accept(c);
		return c.getLinkNum();
	}
	
	public void virusCheck(Directory d){
		VirusCheckingVisitor v = new VirusCheckingVisitor();
		d.accept(v);
		v.printAffectedFiles();
	}
	
	public void searchInDirectory(Directory d, String k){
		FileSearchVisitor v = new FileSearchVisitor(k);
		d.accept(v);
		ArrayList<File>found = v.getFilesFound();
		System.out.println(found.size() + " files found");
		for(File f: found){
			System.out.println(f.getFullPath());
		}
	}
	
	public void destroy(){
		instance = null;
	}
	

}
