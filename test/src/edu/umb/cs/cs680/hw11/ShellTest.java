package edu.umb.cs.cs680.hw11;

import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.umb.cs.cs680.hw11.Element.Directory;
import edu.umb.cs.cs680.hw11.Element.File;
import edu.umb.cs.cs680.hw11.Element.Link;
import edu.umb.cs.cs680.hw11.comparator.AlphabeticalComparator;
import edu.umb.cs.cs680.hw11.comparator.TimeStampComparator;

public class ShellTest {
	FileSystem fs;

	@Before
	public void setup(){
		fs=FileSystem.getInstance();
		FileSystem fs= FileSystem.getInstance();
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
	public void testCommands(){
		FileSystem fs = FileSystem.getInstance();
		fs.setCurrent(fs.getRoot());
		Shell s = new Shell();
		assertTrue("test valid command", s.runCommand("pwd").equals("root"));
		assertTrue("test invalid command", s.runCommand("notvalid").equals("Command not found"));
		
	}
	
	@Test
	public void testHistory(){
		Shell s = new Shell();
		s.runCommand("pwd");
		s.runCommand("dir");
		assertTrue("test command history", s.getLatestCommand().equals("dir"));
		assertTrue("test command history", s.getLatestCommand().equals("pwd"));
	}
	
	@After
	public void cleanUp(){
		FileSystem fs= FileSystem.getInstance();
		fs.destroy();
	}	

}
