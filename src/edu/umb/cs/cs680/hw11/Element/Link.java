package edu.umb.cs.cs680.hw11.Element  ;

import java.time.LocalDateTime;
import java.util.Date;

import edu.umb.cs.cs680.hw11.visitor.FSVisitor;

public class Link extends FSElement {
	FSElement target;
	public Link(Directory parent, String n, String o, LocalDateTime c, LocalDateTime l, int s) {
		super(parent, n, o, c, l, s);
		type = "L";
		// TODO Auto-generated constructor stub
	}
	public int getTargetSize(){	
	   return target.getSize();
	}
	public void setTarget(FSElement t){
		target= t;
	}
	public FSElement getTarget(){
		return target;
	}
	public int getSize(){
		return 0; //link 0 size
	}
	public void accept(FSVisitor fs){
		fs.visit(this);
	}

}
