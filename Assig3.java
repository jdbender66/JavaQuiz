//Joe Bender
//CS 401
//Ramirez Assignment #3
//Tues/Thurs 9:30-10:45


import java.util.Scanner;
import java.io.*;
import java.util.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Assig3
{
	
	public static void main(String [] args) throws IOException
	{
		String filename = "";
		//create a scanner to read keyboard input
		Scanner keyboard = new Scanner(System.in);
		//create the arraylist of question objects that will store all the fields of each question
		ArrayList<Questions> fullqs = new ArrayList<Questions>();
		
		System.out.println("Welcome to the Quiz! Good luck!");
		
		System.out.println("Enter name of file:");
		filename = keyboard.nextLine();
		filename = filename +".txt";
		
		//Create buffered reader to take in data from file
		
		BufferedReader inputfile = new BufferedReader(new FileReader(filename));
		//a while loop takes the data out of the file
		while (inputfile.ready())
		{
			
			String q = inputfile.readLine();
			String numofanswer = inputfile.readLine();
			int numofa = Integer.parseInt(numofanswer);
			
			
			String[] potAns = new String[numofa];
			
			//puts the string answers into an array
			for (int i = 0; i < numofa; i++)
			{
				potAns[i] = inputfile.readLine();
				
				
			}
			
			
			
			String CA = inputfile.readLine();
			int correctAns = Integer.parseInt(CA);
			
			String TT = inputfile.readLine();
			int timestried = Integer.parseInt(TT);
				
			String TR = inputfile.readLine();
			int timesright = Integer.parseInt(TR);
			//creates a new question object that contains all of the data from the specific question
			Questions X = new Questions(q, numofa, potAns, correctAns, timestried, timesright);
			//adds the specific question to the arraylist
			fullqs.add(X);
		}
		//close the file
		inputfile.close();
		//creates an arrray to store the users answer selections
		int[] userAns = new int[fullqs.size()];
		//this for loop takes in all of the user answers and then adds them to the array
		for(int j = 0; j< fullqs.size();j++)
		{
			System.out.println("Question "+j);
			fullqs.get(j).showQuestion();
			
			int check = 0;
			do{
				System.out.print("Enter the correct answer's #: ");
				int userA = keyboard.nextInt();
				if(userA >= 0 && userA<fullqs.get(j).getNumberofAns())
				{
					userAns[j] = userA;
					check = 0;
				}
				else
				{
					check = 1;
				}
			}while(check == 1);	
			
		}
		//creates an array of values that checks if the correct answer was selected
		int[] checking = new int[fullqs.size()];
		
		int numberright = 0;
		int numberwrong = 0;
		//an array that shows the user the question, answer, and their guess including if they were right or wrong
		for(int j = 0; j< fullqs.size();j++)
		{
			System.out.println("");
			fullqs.get(j).dispQ();
			System.out.println("Correct Answer: "+fullqs.get(j).retA());
			System.out.println("User Answer: "+fullqs.get(j).retU(userAns[j]));
			int areuright = fullqs.get(j).compareAns(userAns[j]);
			
			checking [j] = areuright;
			
			if(areuright == 1)
			{
				System.out.println("Correct!");
				numberright++;
			}
			else
			{
				System.out.println("Incorrect. Remember answer for later.\n");
				numberwrong++;
			}
		}
		
		
		
		
		//personal stats for the session
		
		System.out.println("Your personal statistics:");
		System.out.println("Number Correct: "+ numberright);
		System.out.println("Number Incorrect: "+ numberwrong);
		double grade;
		grade = ((double)numberright/(numberright + numberwrong));
		System.out.println("Percentage: "+grade*100+"%");
		
		//cumulative stats
		
		System.out.println("\n\nCumulative statistics:");
		
		int highest = 0;
		int lowest = 0;
		double lowestVal = 1;
		double highestVal = 0;
		//this for loop goes through all of the percentages to find the easiest and hardest question
		for(int j = 0; j< fullqs.size();j++)
		{
			fullqs.get(j).printcuml();
			double per = fullqs.get(j).getPer();
			
			if(per < lowestVal)
			{
				lowestVal = per;
				lowest = j;
			}
			
			if(per > highestVal)
			{
				highestVal = per;
				highest = j;
			}
		}
		//prints easiest and hardest questions
		System.out.println("\nEasiest Question: ");
		fullqs.get(highest).printcuml();
		
		System.out.println("\nHardest Question: ");
		fullqs.get(lowest).printcuml();
		//updates all the variables in preparation for rewriting the file
		for(int j = 0; j< fullqs.size();j++)
		{
			fullqs.get(j).update(checking[j]);
		}
		//create a new file that overwrites the old. It would be too difficult to append the specific locations in the text file
		//so I like just creating a new file
		PrintWriter outputFile = new PrintWriter(filename);
		//writes it to the file
		for(int j = 0; j< fullqs.size();j++)
		{
			outputFile.println(fullqs.get(j).getQuestion());
			outputFile.println(fullqs.get(j).getNumberofAns());
			
			String[] finalarray = fullqs.get(j).getAnswers();
			
			for (int i = 0; i < (fullqs.get(j).getNumberofAns()); i++)
			{
				outputFile.println(finalarray[i]);	
			}
			outputFile.println(fullqs.get(j).getCorrectans());
			outputFile.println(fullqs.get(j).getTried());
			outputFile.println(fullqs.get(j).getCorrect());
		}
		
		outputFile.close();
	}
}
