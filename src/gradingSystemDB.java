import java.io.*;
import java.util.*;

public class gradingSystemDB {
	HashMap<String, HashMap<String, Double>> tableIS;//IS means indexed by student's name
	HashMap<String, HashMap<String, Double>> tableIC;
	
	gradingSystemDB() throws IOException{
		//table = new ArrayList<tableRow>();
		tableIS = new HashMap<String, HashMap<String, Double>>();
		tableIC = new HashMap<String, HashMap<String, Double>>();
		File file = new File("Database.csv");
		if(!file.exists()){
			file.createNewFile();
		}
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = reader.readLine();
			while(line != null){
				insert(line);
				line = reader.readLine();
			}
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
	}
	
	public void insert(String rowIn){
		String[] columns = rowIn.split(",");
		if(columns.length != 3){
			System.out.println("Synax Error! Please use 'add -help' for help.");
			System.out.println(columns.length);
			return;
		}
		
		HashMap<String, Double> studentAndScore = new HashMap<String, Double>();
		HashMap<String, Double> courseAndScore = new HashMap<String, Double>();
		if(tableIS.get(columns[0]) != null)	courseAndScore = tableIS.get(columns[0]);
		if(tableIC.get(columns[1]) != null)	studentAndScore = tableIC.get(columns[1]);
		
		courseAndScore.put(columns[1], Double.parseDouble(columns[2]));
		studentAndScore.put(columns[0], Double.parseDouble(columns[2]));

		tableIS.put(columns[0], courseAndScore);
		tableIC.put(columns[1], studentAndScore);
	}
	
	public void printAll(){
		Iterator<String> iter = tableIS.keySet().iterator();
		String linePrint;
		linePrint = "Student\tCourse\tScore";
		System.out.println(linePrint);
		while(iter.hasNext()){
			String studentName = iter.next();
			HashMap<String, Double> cs = tableIS.get(studentName);
			Iterator<String> citer = cs.keySet().iterator();
			while(citer.hasNext()){
				String courseName = citer.next();
				Double score = cs.get(courseName);
				linePrint = studentName + "\t" + courseName + "\t" + score;
				System.out.println(linePrint);
			}
		}
	}
	
	public void printS(String studentName){
		HashMap<String, Double> cs = tableIS.get(studentName);
		if(cs == null){
			System.out.println("No data returns!");
			return;
		}
		
		String linePrint;
		linePrint = "Course\tScore";
		System.out.println(linePrint);
		
		Iterator<String> citer = cs.keySet().iterator();
		double totalPoint = 0.0;
		int counter = 0;
		while(citer.hasNext()){
			String courseName = citer.next();
			Double score = cs.get(courseName);
			linePrint = courseName + "\t" + score;
			System.out.println(linePrint);
			counter++;
			totalPoint += score;
		}
		System.out.println(studentName + "'s total marks: " + totalPoint);
		System.out.println(studentName + "'s average marks: " + totalPoint/counter);
	}
	
	public void printC(String courseName){
		HashMap<String, Double> cs = tableIC.get(courseName);
		if(cs == null){
			System.out.println("No data returns!");
			return;
		}
		
		String linePrint;
		linePrint = "Course\tScore";
		System.out.println(linePrint);
		
		Iterator<String> citer = cs.keySet().iterator();
		int totalStudent = 0;
		double totalMark = 0.0;
		while(citer.hasNext()){
			String studentName = citer.next();
			Double score = cs.get(studentName);
			linePrint = studentName + "\t" + score;
			System.out.println(linePrint);
			totalStudent++;
			totalMark += score;
		}
		
		System.out.println("Course " + courseName + "'s has " + totalStudent + " students.");
		System.out.println("Course " + courseName + "'s average mark is " + totalMark/totalStudent);
	}
	
	public void importFile(String fileRoute){
		File file = new File(fileRoute);
		if(!file.exists()){
			System.out.println("File not exists!");
			return;
		}
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = reader.readLine();
			while(line != null){
				insert(line);
				line = reader.readLine();
			}
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void exportDB() throws IOException{
		File file = new File("Database.csv");
		FileWriter fw = new FileWriter(file);
		
		Iterator<String> iter = tableIS.keySet().iterator();
		String linePrint;
		linePrint = "";
		boolean flag = true;
		while(iter.hasNext()){
			String studentName = iter.next();
			HashMap<String, Double> cs = tableIS.get(studentName);
			Iterator<String> citer = cs.keySet().iterator();
			while(citer.hasNext()){
				String courseName = citer.next();
				Double score = cs.get(courseName);
				if(flag){
					linePrint += studentName + "," + courseName + "," + score;
					flag = false;
				}
				else{
					linePrint += "\n" + studentName + "," + courseName + "," + score;
				}
				//linePrint += studentName + "," + courseName + "," + score;
			}
		}
		
		fw.write(linePrint);
		fw.close();
	}
}
