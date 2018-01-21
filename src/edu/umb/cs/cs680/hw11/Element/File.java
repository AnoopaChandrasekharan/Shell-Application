package edu.umb.cs.cs680.hw11.Element;

import java.time.LocalDateTime;
import java.util.Date;

import edu.umb.cs.cs680.hw11.visitor.FSVisitor;

public class File extends FSElement{
	boolean virusFlag=false;
	public File(Directory p, String n, String o, LocalDateTime c, LocalDateTime l, int s) {
		super(p, n, o, c, l, s);
		// TODO Auto-generated constructor stub
		type = "F";
		
	}
	
	public void accept(FSVisitor fs){
		fs.visit(this);
	}
	public void setVirusFlag(){
		virusFlag= true;
	}
	public boolean getVirusFlag(){
		return virusFlag;
	}

}
