import java.io.IOException;
import java.util.*;

public class gradingSystem {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		String temp;
		gradingSystemDB table = new gradingSystemDB(); 
		System.out.println("Welcome to Grade System!");
		System.out.println("========================================================");
		System.out.println("Key in 'help' to see help.");
		System.out.print("User> ");
		while(!(temp = input.nextLine()).equals("quit")){
			String[] com = temp.split(" ");
			if(com[0].equals("add")){
				if(com.length < 2)
					System.out.println("Synax Error! Please use 'add -help' for help.");
				else if(com[1].equals("-help")){
					System.out.println("Key in like this: add [Student name],[Course name],[Marks]");
				}
				else{
					String row = temp.replace(com[0] + " ", "");;
					table.insert(row);
				}
			}
			else if(com[0].equals("print")){
				if(com.length == 1){
					table.printAll();
				}
				else if(com[1].equals("-s")){
					String name = temp.replace(com[0] + " " + com[1] + " ", "");
					table.printS(name);
				}
				else if(com[1].equals("-c")){
					String cname = temp.replace(com[0] + " " + com[1] + " ", "");
					table.printC(cname);
				}
			}
			else if(com[0].equals("help")){
				System.out.println("Commands accepted:");
				System.out.println("add\t\tAdd a row or update datas");
				System.out.println("print\t\tPrint the whole database");
				System.out.println("print -s\tPrint the student's all course marks");
				System.out.println("print -c\tPrint the course's all students' marks");
				System.out.println("import [File]\tImport a csv file");
			}
			else if(com[0].equals("import")){
				table.importFile(com[1]);
			}
			else
				System.out.println("Synax error! Please use command 'help' to check input.");
			//else if(temp.equals("print "))
			System.out.print("User> ");
		}
		table.exportDB();
		input.close();
	}
}
