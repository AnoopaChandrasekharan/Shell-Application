package edu.umb.cs.cs680.hw11;

import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;

import org.junit.Test;

import edu.umb.cs.cs680.hw11.Element.Directory;
import edu.umb.cs.cs680.hw11.Element.File;
import edu.umb.cs.cs680.hw11.Element.Link;


public class FSElementTest {
	
	@Test
	public void testFile(){
		LocalDateTime date = LocalDateTime.now();
		Directory system= new Directory(null,"system","abc",date, date,300);
		File a = new File(system,"a","abc",date, date, 100);
		assertTrue("file parent", a.getParent().getName().equals("system"));
		assertTrue("file name", a.getName().equals("a"));
		assertTrue("file owner", a.getOwner().equals("abc"));
		assertTrue("file type", a.getType().equals("F"));
	}
	
	@Test
	public void testDirectory(){
		LocalDateTime date = LocalDateTime.now();
		Directory root= new Directory(null,"root","abc",date, date,300);
		Directory system= new Directory(root,"system","abc",date, date,300);
		Directory test = new Directory(system,"test","abc",date, date,300);
		File a = new File(system,"a","abc",date, date, 100);
		File b = new File(test,"b","abc",date, date, 100);
		test.appendChild(b);
		system.appendChild(a);
		system.appendChild(test);
		assertTrue("dir parent", system.getParent().getName().equals("root"));
		assertTrue("dir name", system.getName().equals("system"));
		assertTrue("dir owner", system.getOwner().equals("abc"));
		assertTrue("dir type", system.getType().equals("D"));
		assertTrue("dir - search by name", system.getChildByName("test") != null);
		assertTrue("dir - search by name and type", system.getChildByNameAndType("a", "F") != null);
		assertTrue("dir - get size", system.getSize() == 200);
		assertTrue("dir - get element at path", system.getElementAtPath("test/b") != null);
		
	}
	
	@Test
	public void testLinks(){
		LocalDateTime date = LocalDateTime.now();
		Directory root= new Directory(null,"root","abc",date, date,300);
		Directory system= new Directory(root,"system","abc",date, date,300);
		Link y= new Link(system,"y","efg", date,date, 0);
		y.setTarget(root);
		system.appendChild(y);
		assertTrue("link target", y.getTarget().getName().equals("root"));
		
	}
		

}
