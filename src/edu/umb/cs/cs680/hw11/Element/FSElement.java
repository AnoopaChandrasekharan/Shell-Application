package edu.umb.cs.cs680.hw11.Element;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import edu.umb.cs.cs680.hw11.visitor.FSVisitor;

public class FSElement {
	
	String name;
	String owner;
	LocalDateTime created;
	LocalDateTime lastModified;
	int size;
	Directory parent = null;
	String type = "";
	
	public FSElement(Directory p, String n, String o, LocalDateTime c, LocalDateTime l,int s){
		name = n;
		parent = p;
		owner = o;
		created = c;
		lastModified = l;
		size = s;
		
	}
	public void  accept(FSVisitor fs){
	
	}
	
	public Directory getParent(){
		return this.parent;
	}
	
	public void setParent(Directory p){
		parent=p;
	}
	
	public Boolean isFile(){
		if(size>0){
			return true;
		}else{
			return false;
		}
	}
	
	public int getSize(){
		return size;
	}
	
	public String getName(){
		return name;
	}
	
	public String getOwner(){
		return owner;
	}
	
	public LocalDateTime getCreated(){
		return created;
	}
	
	public void setOwner(String o){
		owner = o;
	}
	
	public void printInfo(){
		System.out.println(getType() + " " + getName() + " " + getSize() + " " + getOwner());
	}
	
	public void showElement(String tab){
		System.out.println(tab.concat(name));
	}
	
	public String getType(){
		return type;
	}
	
	public String getFullPath(){
		FSElement p = parent;
		String path = "/";
		ArrayList<String> pathArr = new ArrayList<String>();
		while(p != null){
			pathArr.add(p.getName());
			p = p.getParent();
		}
		Collections.reverse(pathArr);
		for( String rp: pathArr ){
			path = path.concat(rp + "/");
		}
		return path.concat(name);
	}
}
