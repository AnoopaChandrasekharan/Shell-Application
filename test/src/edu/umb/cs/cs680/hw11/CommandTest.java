package edu.umb.cs.cs680.hw11;

import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.umb.cs.cs680.hw11.Element.Directory;
import edu.umb.cs.cs680.hw11.Element.File;
import edu.umb.cs.cs680.hw11.Element.Link;
import edu.umb.cs.cs680.hw11.command.Cd;
import edu.umb.cs.cs680.hw11.command.Chown;
import edu.umb.cs.cs680.hw11.command.Count;
import edu.umb.cs.cs680.hw11.command.Cp;
import edu.umb.cs.cs680.hw11.command.Dir;
import edu.umb.cs.cs680.hw11.command.Ln;
import edu.umb.cs.cs680.hw11.command.Mkdir;
import edu.umb.cs.cs680.hw11.command.Mv;
import edu.umb.cs.cs680.hw11.command.Pwd;
import edu.umb.cs.cs680.hw11.command.Rmdir;
import edu.umb.cs.cs680.hw11.command.Sort;

public class CommandTest {
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
		fs.setCurrent(fs.getRoot());
		Pwd pwd = new Pwd();
		assertTrue("pwd test",pwd.execute().equals("root"));
		Cd cd = new Cd("home");
		cd.execute();
		assertTrue("cd test valid",fs.getCurrent().getName().equals("home"));
		cd = new Cd("pictures");
		cd.execute();
		cd = new Cd("..");
		cd.execute();
		assertTrue("cd test ..",fs.getCurrent().getName().equals("home"));
		cd = new Cd("");
		cd.execute();
		assertTrue("cd test blank",fs.getCurrent().getName().equals("root"));
		Chown chown = new Chown("anu", "system");
		chown.execute();
		assertTrue("chown test",fs.getCurrent().getChildByName("system").getOwner().equals("anu"));
		cd = new Cd("system");
		cd.execute();
		Mkdir md = new Mkdir("test");
		md.execute();
		assertTrue("mkdir test",fs.getCurrent().getChildByName("test") != null);
		Rmdir rd = new Rmdir("test");
		rd.execute();
		assertTrue("mkdir test",fs.getCurrent().getChildByName("test") == null);
		Count c = new Count("f");
		assertTrue("count file test",c.execute().equals("3 files found"));
		cd = new Cd("..");
		cd.execute();
		c = new Count("d");
		assertTrue("count dir test",c.execute().equals("3 directories found"));
		c = new Count("l");
		assertTrue("count dir test",c.execute().equals("2 links found"));
		Ln l = new Ln("system", "aaa");
		l.execute();
		System.out.println(fs.getCurrent().getChildByName("aaa"));
		assertTrue("ln test",fs.getCurrent().getChildByName("aaa").getType().equals("L"));
		Dir d = new Dir("asdsad");
		assertTrue("dir not found test", d.execute().contains("not found"));
		Cp cp = new Cp("system/a", "home");
		cp.execute();
		assertTrue("cp test", ((Directory)fs.getCurrent().getChildByName("home")).getChildByName("a") != null);
		Mv mv = new Mv("system/b", "home");
		mv.execute();
		assertTrue("mv test - target", ((Directory)fs.getCurrent().getChildByName("home")).getChildByName("b") != null);
		assertTrue("mv test - source", ((Directory)fs.getCurrent().getChildByName("system")).getChildByName("b") == null);
		cd = new Cd("system");
		cd.execute();
		md = new Mkdir("test");
		md.execute();
		Sort s = new Sort("n");
		s.execute();
		assertTrue("sort test - alphabetical", fs.getCurrent().getChildren().get(0).getName().equals("a"));
		s = new Sort("t");
		s.execute();
		assertTrue("sort test - timestamp", fs.getCurrent().getChildren().get(0).getName().equals("test"));
	}
	@After
	public void cleanUp(){
		FileSystem fs= FileSystem.getInstance();
		fs.destroy();
	}	

}
