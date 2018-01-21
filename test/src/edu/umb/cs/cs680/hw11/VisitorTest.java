package edu.umb.cs.cs680.hw11;
import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.umb.cs.cs680.hw11.visitor.VirusCheckingVisitor;
import edu.umb.cs.cs680.hw11.visitor.FileSearchVisitor;
import edu.umb.cs.cs680.hw11.Element.Directory;
import edu.umb.cs.cs680.hw11.Element.File;
import edu.umb.cs.cs680.hw11.Element.Link;
import edu.umb.cs.cs680.hw11.visitor.CountingVisitor;

import static org.hamcrest.CoreMatchers.instanceOf;
import org.junit.Test;
public class VisitorTest {
	FileSystem fs;
	File test;
	@Before
	public void setup(){
		fs = FileSystem.getInstance();
		LocalDateTime date = LocalDateTime.now();
		
		Directory root = new Directory(null, "root", "abc", date, date, 300);
		Directory system= new Directory(root,"system","abc",date, date,300);
		Directory home=new Directory(root,"home","efg",date, date,80);
		
		File a = new File(system,"a","abc",date, date, 100);
		File b= new File(system,"b","abc",date, date, 100);
		File c= new File(system,"c","abc",date, date, 100);
		
		fs.addChild(system, a);
		fs.addChild(system, b);
		fs.addChild(system, c);
		
		fs.addChild(root, system);
		
		Directory pictures= new Directory(home,"pictures","abc",date, date,22);
		
		Link y= new Link(pictures,"y","efg", date,date, 0);
		File e= new File(pictures,"e.txt","efg",date,date, 100);
		File f= new File(pictures,"f.txt","efg",date,date, 100);
		f.setVirusFlag();
		
		Link x= new Link(home,"x","efg", date,date, 0);
		File d= new File(home,"d.doc","efg",date, date, 100);
		x.setTarget(system);
		y.setTarget(e);
		
		fs.addChild(pictures, y);
		fs.addChild(pictures, e);
		fs.addChild(pictures, f);
		
		fs.addChild(home, pictures);
		fs.addChild(home, x);
		fs.addChild(home, d);
		
		fs.addChild(root, home);
		fs.setRoot(root);
	}
	
	@Test
	public void countingDirSize(){
		CountingVisitor visitor = new CountingVisitor();
		fs.getRoot().accept( visitor );
		assertTrue("",visitor.getDirNum()==4);
		
	}
	@Test
	public void virusTest(){
		VirusCheckingVisitor visitor = new VirusCheckingVisitor();
		fs.getRoot().accept( visitor );
		System.out.println(visitor.getQuarantinedNum()); 
		assertTrue("",visitor.getQuarantinedNum()==1);
		
	}
	@Test
	public void fileSearchTest(){
		FileSearchVisitor visitor = new FileSearchVisitor("e.txt");
		fs.getRoot().accept( visitor );
		System.out.println(visitor.getFoundFiles().size()); 
		assertTrue("",visitor.getFoundFiles().size()==1);
	}
	@After
	public void cleanUp(){
		FileSystem fs= FileSystem.getInstance();
		fs.destroy();
	}

}

