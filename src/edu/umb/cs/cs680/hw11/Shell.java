package edu.umb.cs.cs680.hw11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.Stack;

import edu.umb.cs.cs680.hw11.FileSystem;
import edu.umb.cs.cs680.hw11.Element.Directory;
import edu.umb.cs.cs680.hw11.Element.File;
import edu.umb.cs.cs680.hw11.Element.Link;
import edu.umb.cs.cs680.hw11.command.Cd;
import edu.umb.cs.cs680.hw11.command.Chown;
import edu.umb.cs.cs680.hw11.command.Command;
import edu.umb.cs.cs680.hw11.command.Count;
import edu.umb.cs.cs680.hw11.command.Cp;
import edu.umb.cs.cs680.hw11.command.Dir;
import edu.umb.cs.cs680.hw11.command.Ln;
import edu.umb.cs.cs680.hw11.command.Ls;
import edu.umb.cs.cs680.hw11.command.Mkdir;
import edu.umb.cs.cs680.hw11.command.Mv;
import edu.umb.cs.cs680.hw11.command.Pwd;
import edu.umb.cs.cs680.hw11.command.Rmdir;
import edu.umb.cs.cs680.hw11.command.Search;
import edu.umb.cs.cs680.hw11.command.Sort;
import edu.umb.cs.cs680.hw11.command.Tree;
import edu.umb.cs.cs680.hw11.command.VirusCheck;

public class Shell {
	
	private Stack<String> commandHistory = new Stack<String>(); 
	
	public int run(){
		String commandLine = "";
		BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Type commands below (h for Help, q to Quit) & press Enter");
		while (!commandLine.equals("q")) {
		  try {
			  System.out.print(">");
			  commandLine = console.readLine();
		  } catch (IOException e) {
			  // TODO Auto-generated catch block
			  e.printStackTrace();
		  }
		  System.out.println(runCommand(commandLine));
 		}
		return 0;
	}
	
	public String getLatestCommand(){
		return commandHistory.pop();
	}
	
	private void printHistory(){
		for(String c: commandHistory){
			System.out.println(c);
		}
	}
	
	public String runCommand(String commandLine){
		switch(commandLine){
		  case "":
			  return "";
		  case "h":
			  return ("- pwd \n-- Print the current working directory. \n- cd <dir name>\n-- Change the current directory to the specified directory. Accepts a relative (not absolute) directory name. Accepts “..”\n(move to the parent directory of the current directory.)\n- cd\n-- Change the current directory to the root directory.\n- ls\n-- Prints the name of every file, directory and link in the current directory.\n- dir\n-- Prints the information (i.e., kind, name, size and owner) of every file, directory and link in the current directory.\n- dir <dir/file name>\n-- Prints the specified directory’s/file’s information. Accepts relative (not absolute) directory name. Accepts “..”\n- mkdir <dir name>\n-- Makes the specified directory in the current directory.\n- rmdir <dir name>\n-- Removes the specified directory in the current directory.\n- ln <target (real) dir/file> <link (alias) dir/file>\n-- Makes a link\n- mv <dir/file> <destination dir>\n-- Moves a directory/file to the destination directory\n- cp <dir/file> <destination dir>\n-- Copy a directory/file to the destination directory\n- chown\n-- Change the owner of a file/directory\n- history\n-- Print a sequence of previously-executed commands.\n- redo\n-- Redo the most recently-executed command.\n- sort <n|t> \n-- Sort directories and files in the current directory (n - sort by name, t - sort by timestamp)\n- count <d|f|l> \n-- Count in current directory (d - directory, f - file, l - link)\n- scan\n-- Virus scan\n- search <keyword>\n-- Search in directory");
		  case "q":
			  return("Exiting shell..Done");
		  case "history":
			  printHistory();
			  return "";
		  case "redo":
			  if(commandHistory.size() > 0){
				  String command = commandHistory.pop();
				  Command c = getCommand(command);
				  if(c != null){
					  return (c.execute());				  
				  }else{
					 return ("Command not found");  
				  }
			  }else{
				  return("Nothing to redo");
			  }  
		  default:
			  commandHistory.push(commandLine);
			  Command c = getCommand(commandLine);
			  if(c != null){
				  return(c.execute());				  
			  }else{
				  return("Command not found");  
			  }
		  }
	}
	
	private Command getCommand(String cl){
		String[] parts = cl.split(" ");
		if(parts.length > 0){
			switch(parts[0]){
			case "pwd":
				return new Pwd();
			case "cd":
				return new Cd(parts.length > 1 ? parts[1] : "" );
			case "ls":
				return new Ls();
			case "dir":
				return new Dir(parts.length > 1 ? parts[1] : "" );
			case "mkdir":
				return new Mkdir(parts.length > 1 ? parts[1] : "" );
			case "rmdir":
				return new Rmdir(parts.length > 1 ? parts[1] : "" );
			case "ln":
				return new Ln(parts.length > 2 ? parts[1] : "",  parts.length > 2 ? parts[2] : "");	
			case "cp":
				return new Cp(parts.length > 2 ? parts[1] : "",  parts.length > 2 ? parts[2] : "");	
			case "mv":
				return new Mv(parts.length > 2 ? parts[1] : "",  parts.length > 2 ? parts[2] : "");		
			case "chown":
				return new Chown(parts.length > 2 ? parts[1] : "",  parts.length > 2 ? parts[2] : "");	
			case "sort":
				return new Sort(parts.length > 1 ? parts[1] : "n" );
			case "count":
				return new Count(parts.length > 1 ? parts[1] : "d" );
			case "search":
				return new Search(parts.length > 1 ? parts[1] : null );
			case "scan":
				return new VirusCheck();		
			case "tree":
				return new Tree();
			}
		}
		return null;
	}
	
	/**
	 * Creates default structure
	 */
	public void initFS(){
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
	
	 public static void main(String[] args){
		 Shell s = new Shell();
		 s.initFS();
		 s.run();
	 }
}
