package edu.umb.cs.cs680.hw11.Element;

import java.time.LocalDateTime;
import java.util.ArrayList;

import java.util.Iterator;

import edu.umb.cs.cs680.hw11.visitor.FSVisitor;
;
//import edu.umb.cs.cs680.hw09.FileSystem;

public class Directory extends FSElement {
	ArrayList<FSElement> children= new ArrayList<FSElement>();
   // FileSystem fileSystem;
	public Directory(Directory parent, String n, String o, LocalDateTime c, LocalDateTime l, int s) {
		super(parent, n, o, c, l, s);
		type = "D";
	}
	
	public void accept(FSVisitor fs){
		fs.visit(this);
		for(FSElement e: children){
			e.accept(fs);
		}
	}
	
	public ArrayList<FSElement> getChildren(){
		return children;		
	}
	
	public void appendChild(FSElement fs){
		if(getChildByNameAndType(fs.getName(), fs.getType()) != null){
			System.out.println("Child already exists");
		}else{
			children.add(fs);
		}
	}
	
	
	public void removeChild(String name, String type){
		Iterator <FSElement> childIterator = children.iterator();
		while(childIterator.hasNext()){
			FSElement temp = childIterator.next();
			if(temp.getName().equals(name) && temp.getType().equals(type)){
				childIterator.remove();
			}
		}
	}
	public int getSize(){
		int size=0;
		Iterator<FSElement> i= children.iterator();
		while(i.hasNext()){
			FSElement temp= i.next();
			size= size + temp.getSize();	
		}
		return size;
	}
	
	public void showElement(String tab){
		super.showElement(tab);
		Iterator <FSElement> childIterator = children.iterator();
		while(childIterator.hasNext()){
			childIterator.next().showElement(tab.concat("-"));
		}
	}
	
	public FSElement getChildByNameAndType(String name, String type){
		FSElement found = null;
		Iterator<FSElement> i= children.iterator();
		while(i.hasNext()){
			FSElement temp= i.next();
			if(temp.getName().equals(name) && temp.getType().equals(type)){
				found = temp;
			}
		}
		return found;
	}
	
	public FSElement getChildByName(String name){
		FSElement found = null;
		Iterator<FSElement> i= children.iterator();
		while(i.hasNext()){
			FSElement temp= i.next();
			if(temp.getName().equals(name)){
				found = temp;
			}
		}
		return found;
	}

	
	public FSElement getElementAtPath(String path){
		FSElement found = this;
		String[] items = path.split("/");
		for(String i : items){
			if(found instanceof Directory){
				found = ((Directory)found).getChildByName(i);
			}
		}
		return found;
	}
	
	public void printChildren(boolean dirOnly, boolean showInfo){
		Iterator<FSElement> i= children.iterator();
		while(i.hasNext()){
			FSElement temp= i.next();
			if (!dirOnly){
				if(showInfo){
					temp.printInfo();
				}else{
					System.out.println(temp.getName());
				}
			}else{
				if(showInfo && temp instanceof Directory){
					temp.printInfo();
				}
			}
		}
	}
	
	public Directory getClone(){
		Directory d = new Directory(parent, name, owner, created, lastModified, size);
		for(FSElement f: children){
			if(f instanceof Directory){
				d.appendChild(((Directory) f).getClone());
			}else{
				d.appendChild(f);
			}
		}
		return d;
	}


}
