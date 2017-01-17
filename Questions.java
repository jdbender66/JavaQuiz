//Joe Bender
//CS 401
//Ramirez Assignment #3 - Questions Class
//Tues/Thurs 9:30-10:45


import java.util.Scanner;
import java.io.*;
import java.util.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Questions
{
	String ans = "";
	int numAns = 0;
	String[] answers;
	int correctAns = 0;
	int tried = 0;
	int correct = 0;
	double percent = 0.0;
	
	
	
	
	
	
	public Questions()
	{
		
	}
	//passes data from the main program (that it got from the file) into the class so it can assign it to instance variables
	public Questions(String a, int num, String[] an, int c, int t, int r)
	{
		ans = a;
		numAns = num;
		answers = an;
		correctAns = c;
		tried = t;
		correct = r;
		
		if (tried == 0 || correct == 0)
		{
		
		}
		else
		{
			percent = (double)r/t;
		}
		
	}	
	//displays question when called
	public void showQuestion()
	{
		System.out.println(ans);
		for(int k = 0; k<numAns; k++)
		{
			System.out.println(k+". "+answers[k]);
		}
	}
	//returns the percent
	public double getPer()
	{
		return percent;	
	}
	//returns the question
	public String getQuestion()
	{
		return ans;	
	}
	//returns number of answers the question has
	public int getNumberofAns()
	{
		return numAns;	
	}
	//returns the array of answers for each question
	public String[] getAnswers()
	{
		return answers;	
	}
	//returns the location of the correct answer in question
	public int getCorrectans()
	{
		return correctAns;
	}
	//returns times the question has been tried
	public int getTried()
	{
		return tried;	
	}
	//returns the number of correct tries that have been attempted on that problem
	public int getCorrect()
	{
		return correct;	
	}
	//Displays the question when called
	
	public void dispQ()
	{
		System.out.println(ans);	
	}
	//returns a String of the correct answer
	public String retA()
	{
		return answers[correctAns]; 	
	}
	//returns the user answer
	public String retU(int x)
	{
		return answers[x];	
	}
	//compares the user and correct answer
	public int compareAns(int x)
	{
		if(x == correctAns)
		{
			return 1;
		}
		else
		{
			
			return 0;
		}
	}
	//prints the cumulative counts
	public void printcuml()
	{
		System.out.println("Question: "+ans);
		System.out.println("Times Tried: "+ tried);
		System.out.println("Times Correct: "+ correct);
		System.out.println("Percent Correct: "+ percent*100 +"%");
	}
	//updates the accumulator variables
	public void update(int x)
	{
		
		tried++;
		correct = correct + x;
		
	}

}
