import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Random;
import java.util.Scanner;


public class hangman {
	
	public static int score = 0 ;
	public static int dead = 0;

	
	 
	public static void main(String[] args) throws Exception {
		
		hangman_start();
		
	}
	
	
	 
	public static void hangman_start() throws Exception {
		
		// getting all the words from words.txt
		System.out.println("There are currently "+txtLineLength("words")+" words in the game.");
		String[] words = txtToArray ("words" );
		
		//getting a random word
		Random rand = new Random();
		int random = rand.nextInt(words.length);
		String yourWord = words[random];
		
		//System.out.println("Your word: "+yourWord);
		
		// Unknown array
		String[] unknown =  new String[yourWord.length()];
		for (int i = 0; i<yourWord.length(); i++) {unknown[i] = "-"; System.out.print(unknown[i]+ " " );}
		
		
		while(score < yourWord.length()) {
		// Start guessing
		guess(yourWord, unknown);
		
		//print results
		printResult(yourWord.length(), unknown);
	
		
		}
		
		System.out.println("");
		System.out.println("");
		System.out.println("Congrats You did it");
		
		
	}
	
	
	
	public static void guess (String yourWord, String[] unknown) {
		
		//get guess
				Scanner scann = new Scanner(System.in);
				System.out.println("");
				System.out.println("Your guess: ");
				String input = scann.next();
				
				//check if its a char
				if (input.length() != 1)  { System.out.println(input +" is not a valid guess. You can only guees a letter at a time.");  return;}	// bakýlacak
				
				// if everything is right check if guess char is in the yourWord
				int counter = 0;
				for (int i = 0; i < yourWord.length();  i++ ) {
					
					//if user guesses correctly
					if (yourWord.charAt(i) == input.toLowerCase().charAt(0) ) {   
						// if it is  change result set
						unknown[i] = input.toLowerCase() ;
						score++;
						counter++;
						
					}
				}
				if (counter == 0) { 
					System.out.println("You guessed wrong. There is no " +"<"+input+">"+ " in the word!"); 
					dead++;
					if(dead == 7) { dead(yourWord); }
				}
		return;
	}
	
	public static void dead (String yourWord) {
		System.out.println("You guessed wrong 7 times. You failed. You are dead!");
		System.out.println("Your word was " + yourWord);
		System.exit(0);
		
	}
	
	public static void printResult (int yourWord, String[] unknown) {
		
		System.out.print("Word: ");
		for (int i = 0; i<yourWord; i++) { System.out.print(unknown[i]+ " " );}
		return;
		
	}
	
	public static int  txtLineLength (String filename) throws Exception {
		
		String st;
		int counter = 0;
		
		BufferedReader br = new BufferedReader(new FileReader(filename+".txt"));
		
		while ((st = br.readLine()) != null) { counter++; }
		    
		return counter;
	}
	
	
	public static String[] txtToArray (String filename ) throws Exception {
		
		String[] txtArray = new String[txtLineLength (filename)];
		
		
		BufferedReader br = new BufferedReader(new FileReader(filename+".txt"));
		
		String st;
		int i =  0 ;
		while ((st = br.readLine()) != null) {    txtArray[i] = st; i++;   }
		
		
		return txtArray; 
	}
	
	
	
}


