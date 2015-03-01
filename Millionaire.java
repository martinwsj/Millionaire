// The "Millionaire" class.

import hsa.Console;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import sun.audio.*;

public class Millionaire extends Panel

    /*Millionaire
    *Martin Jeung
    *This program simulates a Who Wants to Be a Millionaire game.
    *The objective of the game is to win one million dollars by answering all 15 round questions correctly.
    *If you do not know the answer to a question you can either guess the answer or use a help line.
    *The save option allows you to save your progress and the load option allows you to continue from where you left off.
    *The methods created include name [10], questions, rounds, millionaire, paint, millionDollars, helpLines, fifty50, audience, call, results, rules, save,
    *load, highScoreSaver, highScoreLoader, highScoreMenu, display, input, add, edit, remove, search, playMillionaireIntro, playClapping, playBuzzer,
    *playQuestion and playMillionDollars.
    */

{
    static Console c;

    //declare audio highScore to play sound clips
    static AudioPlayer ap = AudioPlayer.player;

    //declare buffered image to be shown after winning one million dollars
    static BufferedImage image;

    //declare new font formats to be used by the drawString method
    static Font font = new Font ("Copperplate Gothic Bold", Font.BOLD, 20);
    static Font font2 = new Font ("Copperplate Gothic Bold", Font.BOLD, 38);
    static Font font3 = new Font ("Arial", Font.BOLD, 36);

    //global variable declaration
    static int x = 61, i = 7, y = 12, temp, counter = 0, end = 0, choice, fiftyCounter = 0, callCounter = 0, audienceCounter = 0, highScore[] = new int [y], largest;
    static String name[] = new String [y], questions[] [] = new String [x] [i], response, temp2, line;

    public static void main (String[] args) throws IOException
    {
	c = new Console ();

	//display main menu until the option 5 is inputted by the user which ends the program
	highScoreLoader ();
	do
	{
	    end = 0;
	    counter = 0;
	    fiftyCounter = 0;
	    audienceCounter = 0;
	    callCounter = 0;
	    c.clear ();

	    //play start up music
	    playMillionaireIntro ();

	    //menu options
	    c.println ("\n\n\n\t\t\t***WHO WANTS TO BE A MILLIONAIRE***");
	    c.print ("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n   1. New Game");
	    c.print ("   2. Load Game");
	    c.print ("   3. Highscores");
	    c.print ("   4. Rules");
	    c.println ("   5. Exit Program");

	    //draw millionaire logo and banner
	    c.setColor (new Color (0, 0, 255));
	    c.fillRect (0, 15, 700, 65);
	    c.setColor (Color.yellow);
	    c.fillStar (20, 100, 130, 130);
	    c.fillStar (10, 230, 50, 50);
	    c.fillStar (85, 255, 50, 50);
	    c.fillStar (1, 280, 130, 130);
	    c.fillStar (470, 100, 130, 130);
	    c.fillStar (560, 230, 50, 50);
	    c.fillStar (485, 255, 50, 50);
	    c.fillStar (494, 280, 130, 130);
	    c.setColor (new Color (106, 90, 205));
	    c.fillOval (150, 85, 325, 325);
	    c.setColor (new Color (72, 61, 139));
	    c.fillOval (168, 102, 290, 290);
	    c.setColor (new Color (106, 90, 205));
	    c.fillOval (204, 137, 220, 220);
	    c.setColor (Color.red);
	    c.fillMapleLeaf (212, 145, 200, 200);
	    c.setFont (font);
	    c.setColor (new Color (255, 250, 250));
	    c.drawString ("WHO WANTS TO BE A", 196, 160);
	    c.setFont (font2);
	    c.drawString ("MILLIONAIRE", 173, 250);
	    c.setColor (new Color (255, 250, 250));
	    c.setFont (font);
	    c.drawString ("WHO WANTS TO BE A", 195, 340);
	    c.setFont (font3);
	    c.setColor (new Color (255, 250, 250));
	    c.drawString ("WHO WANTS TO BE A MILLIONAIRE", 5, 68);

	    //ask user for their menu choice option between 1 and 5
	    c.println ("\nWhat is your choice (1-5):");
	    choice = c.readInt ();

	    //display menu if user enters a menu choice not between 1 and 5
	    while ((choice < 1) || (choice > 5))
	    {
		c.clear ();

		//menu options
		c.println ("\n\n\n\t\t\t***WHO WANTS TO BE A MILLIONAIRE***");
		c.print ("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n   1. New Game");
		c.print ("   2. Load Game");
		c.print ("   3. Highscores");
		c.print ("   4. Rules");
		c.println ("   5. Exit Program");

		//draw millionaire logo and banner
		c.setColor (new Color (0, 0, 255));
		c.fillRect (0, 15, 700, 65);
		c.setColor (Color.yellow);
		c.fillStar (20, 100, 130, 130);
		c.fillStar (10, 230, 50, 50);
		c.fillStar (85, 255, 50, 50);
		c.fillStar (1, 280, 130, 130);
		c.fillStar (470, 100, 130, 130);
		c.fillStar (560, 230, 50, 50);
		c.fillStar (485, 255, 50, 50);
		c.fillStar (494, 280, 130, 130);
		c.setColor (new Color (106, 90, 205));
		c.fillOval (150, 85, 325, 325);
		c.setColor (new Color (72, 61, 139));
		c.fillOval (168, 102, 290, 290);
		c.setColor (new Color (106, 90, 205));
		c.fillOval (204, 137, 220, 220);
		c.setColor (Color.red);
		c.fillMapleLeaf (212, 145, 200, 200);
		c.setFont (font);
		c.setColor (new Color (255, 250, 250));
		c.drawString ("WHO WANTS TO BE A", 196, 160);
		c.setFont (font2);
		c.drawString ("MILLIONAIRE", 173, 250);
		c.setColor (new Color (255, 250, 250));
		c.setFont (font);
		c.drawString ("WHO WANTS TO BE A", 195, 340);
		c.setFont (font3);
		c.setColor (new Color (255, 250, 250));
		c.drawString ("WHO WANTS TO BE A MILLIONAIRE", 5, 68);

		//display error message and ask for a new main menu choice from user
		c.println ("\nInvalid response.  Please enter your choice 1-5.");
		choice = c.readInt ();
	    }


	    //evaluates a case depending on which menu number between 1 and 5 the user inputs (ex. input number 1 would go to case 1)
	    switch (choice)
	    {

		    //this case calls upon the name [10], rounds and results methods
		case 1:
		    name ();
		    rounds ();
		    results ();
		    break;

		    //this case calls upon the load, rounds and results methods
		case 2:
		    load ();
		    rounds ();
		    results ();
		    break;

		    //this case calls upon the rules method
		case 3:
		    highScoresMenu ();
		    break;

		    //this case calls upon the rules method
		case 4:
		    rules ();
		    break;
	    }
	}
	while (choice != 5);

	//when user inputs choice 5 the program will end
	c.clear ();
	c.println ("Thank you for playing Who Wants To Be A Millionaire and come back again.");
	highScoreSaver ();
    }


    //this method loads the highScores file
    public static void highScoreLoader () throws IOException
    {
	y = 1;
	String line, line2;

	//reads each highScore's information from txt file
	BufferedReader fileinput = new BufferedReader (new FileReader ("highScores.txt"));
	line = fileinput.readLine ();

	//reads each highScore's information into the program's database
	while (line != null)
	{
	    name [y] = line;
	    line2 = fileinput.readLine ();
	    highScore [y] = Integer.parseInt (line2);
	    line = fileinput.readLine ();
	    y++;
	}
	fileinput.close ();
    }


    //this method asks for users name [10]
    public static void name () throws IOException
    {
	c.clear ();
	c.println ("What is your name?");
	name [10] = c.readLine ();
	c.clear ();
    }


    //this method stores the questions for all the rounds in a 2D array
    public static void questions ()
    {
	questions [1] [0] = "How often should you back up your files?";
	questions [1] [1] = "1. regular basis";
	questions [1] [2] = "2. once a year";
	questions [1] [3] = "3. never";
	questions [1] [4] = "4. once a month";
	questions [1] [5] = questions [1] [1];
	questions [2] [0] = "Where is not a good idea to back up your data?";
	questions [2] [1] = "1. another medium";
	questions [2] [2] = "2. on paper";
	questions [2] [3] = "3. online storage";
	questions [2] [4] = "4. different location";
	questions [2] [5] = questions [2] [2];
	questions [3] [0] = "What is not an example of corruption of file?";
	questions [3] [1] = "1. trojan horse";
	questions [3] [2] = "2. virus";
	questions [3] [3] = "3. infection macros";
	questions [3] [4] = "4. adware";
	questions [3] [5] = questions [3] [3];
	questions [4] [0] = "What does RAM stand for?";
	questions [4] [1] = "1. real addition member";
	questions [4] [2] = "2. ready and making";
	questions [4] [3] = "3. random adder maker";
	questions [4] [4] = "4. random access memory";
	questions [4] [5] = questions [4] [4];
	questions [5] [0] = "What should the temperature be in a workplace?";
	questions [5] [1] = "1. 17 degrees";
	questions [5] [2] = "2. 23 degress";
	questions [5] [3] = "3. 14 degrees";
	questions [5] [4] = "4. doesn't matter";
	questions [5] [5] = questions [5] [1];
	questions [6] [0] = "How many legs should a chair have?";
	questions [6] [1] = "1. 3";
	questions [6] [2] = "2. 5";
	questions [6] [3] = "3. 4";
	questions [6] [4] = "4. 6";
	questions [6] [5] = questions [6] [2];
	questions [7] [0] = "How far should the computer screen be from the eyes?";
	questions [7] [1] = "1. 10cm";
	questions [7] [2] = "2. 30cm";
	questions [7] [3] = "3. 50cm";
	questions [7] [4] = "4. 70cm";
	questions [7] [5] = questions [7] [3];
	questions [8] [0] = "What is a good room colour for a work room?";
	questions [8] [1] = "1. pure white";
	questions [8] [2] = "2. very dark";
	questions [8] [3] = "3. shiny";
	questions [8] [4] = "4. neutral";
	questions [8] [5] = questions [8] [4];
	questions [9] [0] = "What is not a career that involves computers?";
	questions [9] [1] = "1. janitor";
	questions [9] [2] = "2. sales clerk";
	questions [9] [3] = "3. teacher";
	questions [9] [4] = "4. lawyer";
	questions [9] [5] = questions [9] [1];
	questions [10] [0] = "What is a job that needs a good knowledge of computers?";
	questions [10] [1] = "1. sales clerk";
	questions [10] [2] = "2. system analyst";
	questions [10] [3] = "3. techer";
	questions [10] [4] = "4. lawyer";
	questions [10] [5] = questions [10] [2];
	questions [11] [0] = "What does a web page programmer do?";
	questions [11] [1] = "1. manage programmers";
	questions [11] [2] = "2. surf the web";
	questions [11] [3] = "3. design web pages";
	questions [11] [4] = "4. this is not a real job";
	questions [11] [5] = questions [11] [3];
	questions [12] [0] = "What does a programmer do?";
	questions [12] [1] = "1. writes brochures";
	questions [12] [2] = "2. writes plays";
	questions [12] [3] = "3. travels the world";
	questions [12] [4] = "4. creates programs";
	questions [12] [5] = questions [12] [4];
	questions [13] [0] = "When was JAVA created?";
	questions [13] [1] = "1. 1995";
	questions [13] [2] = "2. 1985";
	questions [13] [3] = "3. 1975";
	questions [13] [4] = "4. 2000";
	questions [13] [5] = questions [13] [1];
	questions [14] [0] = "How do you output text in JAVA?";
	questions [14] [1] = "1. System.out.println;";
	questions [14] [2] = "2. System.out.println();";
	questions [14] [3] = "3. system.out.println()";
	questions [14] [4] = "4. System.in.println()";
	questions [14] [5] = questions [14] [2];
	questions [15] [0] = "How do you write a new line in JAVA?";
	questions [15] [1] = "1. \\new";
	questions [15] [2] = "2. /n";
	questions [15] [3] = "3. \\n";
	questions [15] [4] = "4. new line";
	questions [15] [5] = questions [15] [3];
	questions [16] [0] = "What does a double not store?";
	questions [16] [1] = "1. real numbers";
	questions [16] [2] = "2. decimals";
	questions [16] [3] = "3. large range";
	questions [16] [4] = "4. words";
	questions [16] [5] = questions [16] [4];
	questions [17] [0] = "What is the path of communication for two objects?";
	questions [17] [1] = "1. stream";
	questions [17] [2] = "2. path";
	questions [17] [3] = "3. road";
	questions [17] [4] = "4. system";
	questions [17] [5] = questions [17] [1];
	questions [18] [0] = "How do you output something in HSA console?";
	questions [18] [1] = "1. c.write();";
	questions [18] [2] = "2. c.println();";
	questions [18] [3] = "3. c.outline();";
	questions [18] [4] = "4. c.printline();";
	questions [18] [5] = questions [18] [2];
	questions [19] [0] = "How do you read an integer?";
	questions [19] [1] = "1. c.readInteger";
	questions [19] [2] = "2. c.readInt;";
	questions [19] [3] = "3. c.readInt();";
	questions [19] [4] = "4. c.readNumber";
	questions [19] [5] = questions [19] [3];
	questions [20] [0] = "How do you clear the pre-exsisting output?";
	questions [20] [1] = "1. c.remove";
	questions [20] [2] = "2. c.clean";
	questions [20] [3] = "3. c.clear;";
	questions [20] [4] = "4. c.clear();";
	questions [20] [5] = questions [20] [4];
	questions [21] [0] = "What does < mean?";
	questions [21] [1] = "1. less than";
	questions [21] [2] = "2. greater than";
	questions [21] [3] = "3. eat me";
	questions [21] [4] = "4. nothing";
	questions [21] [5] = questions [21] [1];
	questions [22] [0] = "How do repeat a block of code a specified number of times?";
	questions [22] [1] = "1. while";
	questions [22] [2] = "2. for";
	questions [22] [3] = "3. do-while";
	questions [22] [4] = "4. repeat";
	questions [22] [5] = questions [22] [2];
	questions [23] [0] = "What sign do you use for both conditions must be true?";
	questions [23] [1] = "1. %";
	questions [23] [2] = "2. $";
	questions [23] [3] = "3. &";
	questions [23] [4] = "4. |";
	questions [23] [5] = questions [23] [3];
	questions [24] [0] = "Which is not a type of loop?";
	questions [24] [1] = "1. while";
	questions [24] [2] = "2. do-while";
	questions [24] [3] = "3. for";
	questions [24] [4] = "4. loop";
	questions [24] [5] = questions [24] [4];
	questions [25] [0] = "What is not a step in problem solving?";
	questions [25] [1] = "1. writing";
	questions [25] [2] = "2. testing";
	questions [25] [3] = "3. design";
	questions [25] [4] = "4. implementations";
	questions [25] [5] = questions [25] [1];
	questions [26] [0] = "What is not a method of problem definition?";
	questions [26] [1] = "1. interview";
	questions [26] [2] = "2. game";
	questions [26] [3] = "3. questionnaire";
	questions [26] [4] = "4. brainstorming";
	questions [26] [5] = questions [26] [2];
	questions [27] [0] = "What does IPO not stand for?";
	questions [27] [1] = "1. input";
	questions [27] [2] = "2. process";
	questions [27] [3] = "3. intergrade";
	questions [27] [4] = "4. output";
	questions [27] [5] = questions [27] [3];
	questions [28] [0] = "What is another word for PSEUDO code?";
	questions [28] [1] = "1. brainstorming";
	questions [28] [2] = "2. ideas";
	questions [28] [3] = "3. IPO chart";
	questions [28] [4] = "4. false code";
	questions [28] [5] = questions [28] [4];
	questions [29] [0] = "Which is not a function method?";
	questions [29] [1] = "1. c.println (\"Hello World\")";
	questions [29] [2] = "2. Math.sqrt (a);";
	questions [29] [3] = "3. Math.round (b);";
	questions [29] [4] = "4. sentence.length ();";
	questions [29] [5] = questions [29] [1];
	questions [30] [0] = "What would be outputted in \"c.print (Math.pow (5,2));\"?";
	questions [30] [1] = "1. an error would occur";
	questions [30] [2] = "2. 25";
	questions [30] [3] = "3. 10";
	questions [30] [4] = "4. 15";
	questions [30] [5] = questions [30] [2];
	questions [31] [0] = "What would be outputted in \"c.println (10/2);\"?";
	questions [31] [1] = "1. 10/2";
	questions [31] [2] = "2. an error would occur";
	questions [31] [3] = "3. 5";
	questions [31] [4] = "4. 20";
	questions [31] [5] = questions [31] [3];
	questions [32] [0] = "What is an advantage of using a method provided by Oracle?";
	questions [32] [1] = "1. error free";
	questions [32] [2] = "2. easily be called upon";
	questions [32] [3] = "3. provided for the programer";
	questions [32] [4] = "4. all of the above";
	questions [32] [5] = questions [32] [4];
	questions [33] [0] = "What is another name [10] for instance variable?";
	questions [33] [1] = "1. class variable";
	questions [33] [2] = "2. method variable;";
	questions [33] [3] = "3. local variable;";
	questions [33] [4] = "4. none of the above;";
	questions [33] [5] = questions [33] [1];
	questions [34] [0] = "How do you call upon a method called battingAverage?";
	questions [34] [1] = "1. call battingAverage ();";
	questions [34] [2] = "2. batingAverage ();";
	questions [34] [3] = "3. return battingAverage();";
	questions [34] [4] = "4. not possible";
	questions [34] [5] = questions [34] [2];
	questions [35] [0] = "What's the parameter for \"public static int s (int l)\"?";
	questions [35] [1] = "1. public";
	questions [35] [2] = "2. static";
	questions [35] [3] = "3. int l";
	questions [35] [4] = "4. s";
	questions [35] [5] = questions [35] [3];
	questions [36] [0] = "What is not an example of access right for method?";
	questions [36] [1] = "1. public";
	questions [36] [2] = "2. local";
	questions [36] [3] = "3. global";
	questions [36] [4] = "4. both b and c";
	questions [36] [5] = questions [36] [4];
	questions [37] [0] = "What are body methods enclosed by?";
	questions [37] [1] = "1. {}";
	questions [37] [2] = "2. []";
	questions [37] [3] = "3. ()";
	questions [37] [4] = "4. <>";
	questions [37] [5] = questions [37] [1];
	questions [38] [0] = "What is the duration of global variables?";
	questions [38] [1] = "1. one method";
	questions [38] [2] = "2. whole program";
	questions [38] [3] = "3. every program";
	questions [38] [4] = "4. doesn't matter";
	questions [38] [5] = questions [38] [2];
	questions [39] [0] = "How to declare class varaibles?";
	questions [39] [1] = "1. public";
	questions [39] [2] = "2. void";
	questions [39] [3] = "3. static";
	questions [39] [4] = "4. all of the above";
	questions [39] [5] = questions [39] [3];
	questions [40] [0] = "How many elements are in \"int marks[] = new int [4];\"?";
	questions [40] [1] = "1. 3";
	questions [40] [2] = "2. 6";
	questions [40] [3] = "3. 5";
	questions [40] [4] = "4. 4";
	questions [40] [5] = questions [40] [4];
	questions [41] [0] = "What is an array?";
	questions [41] [1] = "1. group of variables";
	questions [41] [2] = "2. regular variables";
	questions [41] [3] = "3. all of the above";
	questions [41] [4] = "4. none of these";
	questions [41] [5] = questions [41] [1];
	questions [42] [0] = "What are individual items in an array called?";
	questions [42] [1] = "1. chapter";
	questions [42] [2] = "2. index";
	questions [42] [3] = "3. string";
	questions [42] [4] = "4. box";
	questions [42] [5] = questions [42] [2];
	questions [43] [0] = "What is an example of a one demension array?";
	questions [43] [1] = "1. int one";
	questions [43] [2] = "2. int one = new int [5][6]";
	questions [43] [3] = "3. int one [] = new int [5]";
	questions [43] [4] = "4. none of these";
	questions [43] [5] = questions [43] [3];
	questions [44] [0] = "What is an example of an array?";
	questions [44] [1] = "1. int one [] = new int [9]";
	questions [44] [2] = "2. int one [] = new int [10]";
	questions [44] [3] = "3. int one [] = new int [11]";
	questions [44] [4] = "4. all of the above";
	questions [44] [5] = questions [44] [4];
	questions [45] [0] = "Which is not a method to sort data list?";
	questions [45] [1] = "1. java sort";
	questions [45] [2] = "2. bubble sort";
	questions [45] [3] = "3. insertion sort";
	questions [45] [4] = "4. merge sort";
	questions [45] [5] = questions [45] [1];
	questions [46] [0] = "Which package is imported to read and write to .txt files?";
	questions [46] [1] = "1. import java.awt.*;";
	questions [46] [2] = "2. import java.io.*;";
	questions [46] [3] = "3. import java.util.*;";
	questions [46] [4] = "4. import java.text.*;";
	questions [46] [5] = questions [46] [2];
	questions [47] [0] = "What is not a step of problem solving?";
	questions [47] [1] = "1. maintenance";
	questions [47] [2] = "2. testing";
	questions [47] [3] = "3. checking";
	questions [47] [4] = "4. problem definition";
	questions [47] [5] = questions [47] [3];
	questions [48] [0] = "What is wrong with \"int age\"?";
	questions [48] [1] = "1. integer not int";
	questions [48] [2] = "2. Age not age";
	questions [48] [3] = "3. int = age";
	questions [48] [4] = "4. add a ; at the end";
	questions [48] [5] = questions [48] [4];
	questions [49] [0] = "What is wrong with \"string name [10];\"?";
	questions [49] [1] = "1. String not string";
	questions [49] [2] = "2. string = name [10]";
	questions [49] [3] = "3. Name not name [10]";
	questions [49] [4] = "4. nothing";
	questions [49] [5] = questions [49] [1];
	questions [50] [0] = "What does % mean in math calculation?";
	questions [50] [1] = "1. average";
	questions [50] [2] = "2. modulus";
	questions [50] [3] = "3. divide";
	questions [50] [4] = "4. multiply";
	questions [50] [5] = questions [50] [2];
	questions [51] [0] = "What is the increment operator?";
	questions [51] [1] = "1. =+";
	questions [51] [2] = "2. --";
	questions [51] [3] = "3. ++";
	questions [51] [4] = "4. +-";
	questions [51] [5] = questions [51] [3];
	questions [52] [0] = "How to change colour for text?";
	questions [52] [1] = "1. c.changeColor";
	questions [52] [2] = "2. c.coulor.blue";
	questions [52] [3] = "3. c.color.changeTo(blue)";
	questions [52] [4] = "4. c.setTextColor(Color.blue)";
	questions [52] [5] = questions [52] [4];
	questions [53] [0] = "How to change colour for drawings?";
	questions [53] [1] = "1. c.setColor(Color.blue)";
	questions [53] [2] = "2. c.colorChange";
	questions [53] [3] = "3. c.changeBackgroundColor";
	questions [53] [4] = "4. c.colorTo(change)";
	questions [53] [5] = questions [53] [1];
	questions [54] [0] = "How to input not equal to condition?";
	questions [54] [1] = "1. %";
	questions [54] [2] = "2. !";
	questions [54] [3] = "3. ^";
	questions [54] [4] = "4. #";
	questions [54] [5] = questions [54] [2];
	questions [55] [0] = "What is not a common logic error?";
	questions [55] [1] = "1. spelling mistake";
	questions [55] [2] = "2. mathematical errors";
	questions [55] [3] = "3. c.prit";
	questions [55] [4] = "4. confusing loop";
	questions [55] [5] = questions [55] [3];
	questions [56] [0] = "What is not a common sytax error?";
	questions [56] [1] = "1. forgetting semi-colon";
	questions [56] [2] = "2. c.prit";
	questions [56] [3] = "3. forgetting {}";
	questions [56] [4] = "4. mathematical error";
	questions [56] [5] = questions [56] [4];
	questions [57] [0] = "Who created JAVA?";
	questions [57] [1] = "1. James Gosling";
	questions [57] [2] = "2. Goerge Izan";
	questions [57] [3] = "3. Bob Java";
	questions [57] [4] = "4. Stanley Forge";
	questions [57] [5] = questions [57] [1];
	questions [58] [0] = "What operating system does JAVA not work with?";
	questions [58] [1] = "1. MAC OS";
	questions [58] [2] = "2. JAVA platform";
	questions [58] [3] = "3. Windows";
	questions [58] [4] = "4. LINUX";
	questions [58] [5] = questions [58] [2];
	questions [59] [0] = "What is the symbol is on the JAVA logo?";
	questions [59] [1] = "1. computer";
	questions [59] [2] = "2. program";
	questions [59] [3] = "3. cofee";
	questions [59] [4] = "4. apple";
	questions [59] [5] = questions [59] [3];
	questions [60] [0] = "Who is the new developer for JAVA?";
	questions [60] [1] = "1. SUN Microsystem";
	questions [60] [2] = "2. Microsoft";
	questions [60] [3] = "3. JAVA Corp";
	questions [60] [4] = "4. Oracle Corp";
	questions [60] [5] = questions [60] [4];
    }


    //this method simulates all the rounds for the millionaire game
    public static void rounds () throws IOException
    {

	//displays round question until the user answers a question wrong or decides to walk with their current money
	do
	{
	    int roundCounter = 0;

	    //evaluates whether or not the inputted user choice is the help line menu
	    if (i != 5)
	    {

		//evaluates whether or not the inputted user choice is the save option
		if (i != 6)
		{

		    //randomly generates a round question number from 4 possible questions
		    x = (int) (Math.ceil (Math.random () * 4));
		    x = x + (counter * 4);

		    //call the question method
		    questions ();
		    counter++;
		}
	    }

	    //display round question and round money until the user inputs a menu choice between 1 and 7
	    do
	    {

		//play the question music
		playQuestion ();
		c.println ("\t\t\t***ROUND " + counter + "***");
		c.print (" ");
		c.println (questions [x] [0], 56);

		//display the round money for each round (e.g. if it is round 1 display the $100 in yellow, $1000 and $25,000 in red and the rest in black)
		if (counter == 1)
		{
		    c.println ("\t\t\t\t\t\t\t\tRound Money");
		    c.println ("\t\t\t\t\t\t\t\t$1,000,000");
		    c.println ("\t\t\t\t\t\t\t\t$500,000");
		    c.println ("\t\t\t\t\t\t\t\t$250,000");
		    c.println ("\t\t\t\t\t\t\t\t$100,000");
		    c.println ("\t\t\t\t\t\t\t\t$50,000");
		    c.setTextColor (Color.red);
		    c.println ("\t\t\t\t\t\t\t\t$25,000");
		    c.setTextColor (Color.black);
		    c.println ("\t\t\t\t\t\t\t\t$16,000");
		    c.println ("\t\t\t\t\t\t\t\t$8,000");
		    c.println ("\t\t\t\t\t\t\t\t$4,000");
		    c.println ("\t\t\t\t\t\t\t\t$2,000");
		    c.setTextColor (Color.red);
		    c.println ("\t\t\t\t\t\t\t\t$1,000");
		    c.setTextColor (Color.black);
		    c.println ("\t\t\t\t\t\t\t\t$500");
		    c.println ("\t\t\t\t\t\t\t\t$300");
		    c.println ("\t\t\t\t\t\t\t\t$200");
		    c.setTextColor (Color.orange);
		    c.println ("\t\t\t\t\t\t\t\t$100");
		}

		if (counter == 2)
		{
		    c.println ("\t\t\t\t\t\t\t\tRound Money");
		    c.println ("\t\t\t\t\t\t\t\t$1,000,000");
		    c.println ("\t\t\t\t\t\t\t\t$500,000");
		    c.println ("\t\t\t\t\t\t\t\t$250,000");
		    c.println ("\t\t\t\t\t\t\t\t$100,000");
		    c.println ("\t\t\t\t\t\t\t\t$50,000");
		    c.setTextColor (Color.red);
		    c.println ("\t\t\t\t\t\t\t\t$25,000");
		    c.setTextColor (Color.black);
		    c.println ("\t\t\t\t\t\t\t\t$16,000");
		    c.println ("\t\t\t\t\t\t\t\t$8,000");
		    c.println ("\t\t\t\t\t\t\t\t$4,000");
		    c.println ("\t\t\t\t\t\t\t\t$2,000");
		    c.setTextColor (Color.red);
		    c.println ("\t\t\t\t\t\t\t\t$1,000");
		    c.setTextColor (Color.black);
		    c.println ("\t\t\t\t\t\t\t\t$500");
		    c.println ("\t\t\t\t\t\t\t\t$300");
		    c.setTextColor (Color.orange);
		    c.println ("\t\t\t\t\t\t\t\t$200");
		    c.setTextColor (Color.green);
		    c.println ("\t\t\t\t\t\t\t\t$100");
		}

		if (counter == 3)
		{
		    c.println ("\t\t\t\t\t\t\t\tRound Money");
		    c.println ("\t\t\t\t\t\t\t\t$1,000,000");
		    c.println ("\t\t\t\t\t\t\t\t$500,000");
		    c.println ("\t\t\t\t\t\t\t\t$250,000");
		    c.println ("\t\t\t\t\t\t\t\t$100,000");
		    c.println ("\t\t\t\t\t\t\t\t$50,000");
		    c.setTextColor (Color.red);
		    c.println ("\t\t\t\t\t\t\t\t$25,000");
		    c.setTextColor (Color.black);
		    c.println ("\t\t\t\t\t\t\t\t$16,000");
		    c.println ("\t\t\t\t\t\t\t\t$8,000");
		    c.println ("\t\t\t\t\t\t\t\t$4,000");
		    c.println ("\t\t\t\t\t\t\t\t$2,000");
		    c.setTextColor (Color.red);
		    c.println ("\t\t\t\t\t\t\t\t$1,000");
		    c.setTextColor (Color.black);
		    c.println ("\t\t\t\t\t\t\t\t$500");
		    c.setTextColor (Color.orange);
		    c.println ("\t\t\t\t\t\t\t\t$300");
		    c.setTextColor (Color.green);
		    c.println ("\t\t\t\t\t\t\t\t$200");
		    c.println ("\t\t\t\t\t\t\t\t$100");
		}

		if (counter == 4)
		{
		    c.println ("\t\t\t\t\t\t\t\tRound Money");
		    c.println ("\t\t\t\t\t\t\t\t$1,000,000");
		    c.println ("\t\t\t\t\t\t\t\t$500,000");
		    c.println ("\t\t\t\t\t\t\t\t$250,000");
		    c.println ("\t\t\t\t\t\t\t\t$100,000");
		    c.println ("\t\t\t\t\t\t\t\t$50,000");
		    c.setTextColor (Color.red);
		    c.println ("\t\t\t\t\t\t\t\t$25,000");
		    c.setTextColor (Color.black);
		    c.println ("\t\t\t\t\t\t\t\t$16,000");
		    c.println ("\t\t\t\t\t\t\t\t$8,000");
		    c.println ("\t\t\t\t\t\t\t\t$4,000");
		    c.println ("\t\t\t\t\t\t\t\t$2,000");
		    c.setTextColor (Color.red);
		    c.println ("\t\t\t\t\t\t\t\t$1,000");
		    c.setTextColor (Color.orange);
		    c.println ("\t\t\t\t\t\t\t\t$500");
		    c.setTextColor (Color.green);
		    c.println ("\t\t\t\t\t\t\t\t$300");
		    c.println ("\t\t\t\t\t\t\t\t$200");
		    c.println ("\t\t\t\t\t\t\t\t$100");
		}

		if (counter == 5)
		{
		    c.println ("\t\t\t\t\t\t\t\tRound Money");
		    c.println ("\t\t\t\t\t\t\t\t$1,000,000");
		    c.println ("\t\t\t\t\t\t\t\t$500,000");
		    c.println ("\t\t\t\t\t\t\t\t$250,000");
		    c.println ("\t\t\t\t\t\t\t\t$100,000");
		    c.println ("\t\t\t\t\t\t\t\t$50,000");
		    c.setTextColor (Color.red);
		    c.println ("\t\t\t\t\t\t\t\t$25,000");
		    c.setTextColor (Color.black);
		    c.println ("\t\t\t\t\t\t\t\t$16,000");
		    c.println ("\t\t\t\t\t\t\t\t$8,000");
		    c.println ("\t\t\t\t\t\t\t\t$4,000");
		    c.println ("\t\t\t\t\t\t\t\t$2,000");
		    c.setTextColor (Color.orange);
		    c.println ("\t\t\t\t\t\t\t\t$1,000");
		    c.setTextColor (Color.green);
		    c.println ("\t\t\t\t\t\t\t\t$500");
		    c.println ("\t\t\t\t\t\t\t\t$300");
		    c.println ("\t\t\t\t\t\t\t\t$200");
		    c.println ("\t\t\t\t\t\t\t\t$100");
		}

		if (counter == 6)
		{
		    c.println ("\t\t\t\t\t\t\t\tRound Money");
		    c.println ("\t\t\t\t\t\t\t\t$1,000,000");
		    c.println ("\t\t\t\t\t\t\t\t$500,000");
		    c.println ("\t\t\t\t\t\t\t\t$250,000");
		    c.println ("\t\t\t\t\t\t\t\t$100,000");
		    c.println ("\t\t\t\t\t\t\t\t$50,000");
		    c.setTextColor (Color.red);
		    c.println ("\t\t\t\t\t\t\t\t$25,000");
		    c.setTextColor (Color.black);
		    c.println ("\t\t\t\t\t\t\t\t$16,000");
		    c.println ("\t\t\t\t\t\t\t\t$8,000");
		    c.println ("\t\t\t\t\t\t\t\t$4,000");
		    c.setTextColor (Color.orange);
		    c.println ("\t\t\t\t\t\t\t\t$2,000");
		    c.setTextColor (Color.red);
		    c.println ("\t\t\t\t\t\t\t\t$1,000");
		    c.setTextColor (Color.green);
		    c.println ("\t\t\t\t\t\t\t\t$500");
		    c.println ("\t\t\t\t\t\t\t\t$300");
		    c.println ("\t\t\t\t\t\t\t\t$200");
		    c.println ("\t\t\t\t\t\t\t\t$100");
		}

		if (counter == 7)
		{
		    c.println ("\t\t\t\t\t\t\t\tRound Money");
		    c.println ("\t\t\t\t\t\t\t\t$1,000,000");
		    c.println ("\t\t\t\t\t\t\t\t$500,000");
		    c.println ("\t\t\t\t\t\t\t\t$250,000");
		    c.println ("\t\t\t\t\t\t\t\t$100,000");
		    c.println ("\t\t\t\t\t\t\t\t$50,000");
		    c.setTextColor (Color.red);
		    c.println ("\t\t\t\t\t\t\t\t$25,000");
		    c.setTextColor (Color.black);
		    c.println ("\t\t\t\t\t\t\t\t$16,000");
		    c.println ("\t\t\t\t\t\t\t\t$8,000");
		    c.setTextColor (Color.orange);
		    c.println ("\t\t\t\t\t\t\t\t$4,000");
		    c.setTextColor (Color.green);
		    c.println ("\t\t\t\t\t\t\t\t$2,000");
		    c.setTextColor (Color.red);
		    c.println ("\t\t\t\t\t\t\t\t$1,000");
		    c.setTextColor (Color.green);
		    c.println ("\t\t\t\t\t\t\t\t$500");
		    c.println ("\t\t\t\t\t\t\t\t$300");
		    c.println ("\t\t\t\t\t\t\t\t$200");
		    c.println ("\t\t\t\t\t\t\t\t$100");
		}

		if (counter == 8)
		{
		    c.println ("\t\t\t\t\t\t\t\tRound Money");
		    c.println ("\t\t\t\t\t\t\t\t$1,000,000");
		    c.println ("\t\t\t\t\t\t\t\t$500,000");
		    c.println ("\t\t\t\t\t\t\t\t$250,000");
		    c.println ("\t\t\t\t\t\t\t\t$100,000");
		    c.println ("\t\t\t\t\t\t\t\t$50,000");
		    c.setTextColor (Color.red);
		    c.println ("\t\t\t\t\t\t\t\t$25,000");
		    c.setTextColor (Color.black);
		    c.println ("\t\t\t\t\t\t\t\t$16,000");
		    c.setTextColor (Color.orange);
		    c.println ("\t\t\t\t\t\t\t\t$8,000");
		    c.setTextColor (Color.green);
		    c.println ("\t\t\t\t\t\t\t\t$4,000");
		    c.println ("\t\t\t\t\t\t\t\t$2,000");
		    c.setTextColor (Color.red);
		    c.println ("\t\t\t\t\t\t\t\t$1,000");
		    c.setTextColor (Color.green);
		    c.println ("\t\t\t\t\t\t\t\t$500");
		    c.println ("\t\t\t\t\t\t\t\t$300");
		    c.println ("\t\t\t\t\t\t\t\t$200");
		    c.println ("\t\t\t\t\t\t\t\t$100");
		}

		if (counter == 9)
		{
		    c.println ("\t\t\t\t\t\t\t\tRound Money");
		    c.println ("\t\t\t\t\t\t\t\t$1,000,000");
		    c.println ("\t\t\t\t\t\t\t\t$500,000");
		    c.println ("\t\t\t\t\t\t\t\t$250,000");
		    c.println ("\t\t\t\t\t\t\t\t$100,000");
		    c.println ("\t\t\t\t\t\t\t\t$50,000");
		    c.setTextColor (Color.red);
		    c.println ("\t\t\t\t\t\t\t\t$25,000");
		    c.setTextColor (Color.orange);
		    c.println ("\t\t\t\t\t\t\t\t$16,000");
		    c.setTextColor (Color.green);
		    c.println ("\t\t\t\t\t\t\t\t$8,000");
		    c.println ("\t\t\t\t\t\t\t\t$4,000");
		    c.println ("\t\t\t\t\t\t\t\t$2,000");
		    c.setTextColor (Color.red);
		    c.println ("\t\t\t\t\t\t\t\t$1,000");
		    c.setTextColor (Color.green);
		    c.println ("\t\t\t\t\t\t\t\t$500");
		    c.println ("\t\t\t\t\t\t\t\t$300");
		    c.println ("\t\t\t\t\t\t\t\t$200");
		    c.println ("\t\t\t\t\t\t\t\t$100");
		}

		if (counter == 10)
		{
		    c.println ("\t\t\t\t\t\t\t\tRound Money");
		    c.println ("\t\t\t\t\t\t\t\t$1,000,000");
		    c.println ("\t\t\t\t\t\t\t\t$500,000");
		    c.println ("\t\t\t\t\t\t\t\t$250,000");
		    c.println ("\t\t\t\t\t\t\t\t$100,000");
		    c.println ("\t\t\t\t\t\t\t\t$50,000");
		    c.setTextColor (Color.orange);
		    c.println ("\t\t\t\t\t\t\t\t$25,000");
		    c.setTextColor (Color.green);
		    c.println ("\t\t\t\t\t\t\t\t$16,000");
		    c.println ("\t\t\t\t\t\t\t\t$8,000");
		    c.println ("\t\t\t\t\t\t\t\t$4,000");
		    c.println ("\t\t\t\t\t\t\t\t$2,000");
		    c.setTextColor (Color.red);
		    c.println ("\t\t\t\t\t\t\t\t$1,000");
		    c.setTextColor (Color.green);
		    c.println ("\t\t\t\t\t\t\t\t$500");
		    c.println ("\t\t\t\t\t\t\t\t$300");
		    c.println ("\t\t\t\t\t\t\t\t$200");
		    c.println ("\t\t\t\t\t\t\t\t$100");
		}

		if (counter == 11)
		{
		    c.println ("\t\t\t\t\t\t\t\tRound Money");
		    c.println ("\t\t\t\t\t\t\t\t$1,000,000");
		    c.println ("\t\t\t\t\t\t\t\t$500,000");
		    c.println ("\t\t\t\t\t\t\t\t$250,000");
		    c.println ("\t\t\t\t\t\t\t\t$100,000");
		    c.setTextColor (Color.orange);
		    c.println ("\t\t\t\t\t\t\t\t$50,000");
		    c.setTextColor (Color.red);
		    c.println ("\t\t\t\t\t\t\t\t$25,000");
		    c.setTextColor (Color.green);
		    c.println ("\t\t\t\t\t\t\t\t$16,000");
		    c.println ("\t\t\t\t\t\t\t\t$8,000");
		    c.println ("\t\t\t\t\t\t\t\t$4,000");
		    c.println ("\t\t\t\t\t\t\t\t$2,000");
		    c.setTextColor (Color.red);
		    c.println ("\t\t\t\t\t\t\t\t$1,000");
		    c.setTextColor (Color.green);
		    c.println ("\t\t\t\t\t\t\t\t$500");
		    c.println ("\t\t\t\t\t\t\t\t$300");
		    c.println ("\t\t\t\t\t\t\t\t$200");
		    c.println ("\t\t\t\t\t\t\t\t$100");
		}

		if (counter == 12)
		{
		    c.println ("\t\t\t\t\t\t\t\tRound Money");
		    c.println ("\t\t\t\t\t\t\t\t$1,000,000");
		    c.println ("\t\t\t\t\t\t\t\t$500,000");
		    c.println ("\t\t\t\t\t\t\t\t$250,000");
		    c.setTextColor (Color.orange);
		    c.println ("\t\t\t\t\t\t\t\t$100,000");
		    c.setTextColor (Color.green);
		    c.println ("\t\t\t\t\t\t\t\t$50,000");
		    c.setTextColor (Color.red);
		    c.println ("\t\t\t\t\t\t\t\t$25,000");
		    c.setTextColor (Color.green);
		    c.println ("\t\t\t\t\t\t\t\t$16,000");
		    c.println ("\t\t\t\t\t\t\t\t$8,000");
		    c.println ("\t\t\t\t\t\t\t\t$4,000");
		    c.println ("\t\t\t\t\t\t\t\t$2,000");
		    c.setTextColor (Color.red);
		    c.println ("\t\t\t\t\t\t\t\t$1,000");
		    c.setTextColor (Color.green);
		    c.println ("\t\t\t\t\t\t\t\t$500");
		    c.println ("\t\t\t\t\t\t\t\t$300");
		    c.println ("\t\t\t\t\t\t\t\t$200");
		    c.println ("\t\t\t\t\t\t\t\t$100");
		}

		if (counter == 13)
		{
		    c.println ("\t\t\t\t\t\t\t\tRound Money");
		    c.println ("\t\t\t\t\t\t\t\t$1,000,000");
		    c.println ("\t\t\t\t\t\t\t\t$500,000");
		    c.setTextColor (Color.orange);
		    c.println ("\t\t\t\t\t\t\t\t$250,000");
		    c.setTextColor (Color.green);
		    c.println ("\t\t\t\t\t\t\t\t$100,000");
		    c.println ("\t\t\t\t\t\t\t\t$50,000");
		    c.setTextColor (Color.red);
		    c.println ("\t\t\t\t\t\t\t\t$25,000");
		    c.setTextColor (Color.green);
		    c.println ("\t\t\t\t\t\t\t\t$16,000");
		    c.println ("\t\t\t\t\t\t\t\t$8,000");
		    c.println ("\t\t\t\t\t\t\t\t$4,000");
		    c.println ("\t\t\t\t\t\t\t\t$2,000");
		    c.setTextColor (Color.red);
		    c.println ("\t\t\t\t\t\t\t\t$1,000");
		    c.setTextColor (Color.green);
		    c.println ("\t\t\t\t\t\t\t\t$500");
		    c.println ("\t\t\t\t\t\t\t\t$300");
		    c.println ("\t\t\t\t\t\t\t\t$200");
		    c.println ("\t\t\t\t\t\t\t\t$100");
		}

		if (counter == 14)
		{
		    c.println ("\t\t\t\t\t\t\t\tRound Money");
		    c.println ("\t\t\t\t\t\t\t\t$1,000,000");
		    c.setTextColor (Color.orange);
		    c.println ("\t\t\t\t\t\t\t\t$500,000");
		    c.setTextColor (Color.green);
		    c.println ("\t\t\t\t\t\t\t\t$250,000");
		    c.println ("\t\t\t\t\t\t\t\t$100,000");
		    c.println ("\t\t\t\t\t\t\t\t$50,000");
		    c.setTextColor (Color.red);
		    c.println ("\t\t\t\t\t\t\t\t$25,000");
		    c.setTextColor (Color.green);
		    c.println ("\t\t\t\t\t\t\t\t$16,000");
		    c.println ("\t\t\t\t\t\t\t\t$8,000");
		    c.println ("\t\t\t\t\t\t\t\t$4,000");
		    c.println ("\t\t\t\t\t\t\t\t$2,000");
		    c.setTextColor (Color.red);
		    c.println ("\t\t\t\t\t\t\t\t$1,000");
		    c.setTextColor (Color.green);
		    c.println ("\t\t\t\t\t\t\t\t$500");
		    c.println ("\t\t\t\t\t\t\t\t$300");
		    c.println ("\t\t\t\t\t\t\t\t$200");
		    c.println ("\t\t\t\t\t\t\t\t$100");
		}

		if (counter == 15)
		{
		    c.println ("\t\t\t\t\t\t\t\tRound Money");
		    c.setTextColor (Color.orange);
		    c.println ("\t\t\t\t\t\t\t\t$1,000,000");
		    c.setTextColor (Color.green);
		    c.println ("\t\t\t\t\t\t\t\t$500,000");
		    c.println ("\t\t\t\t\t\t\t\t$250,000");
		    c.println ("\t\t\t\t\t\t\t\t$100,000");
		    c.println ("\t\t\t\t\t\t\t\t$50,000");
		    c.setTextColor (Color.red);
		    c.println ("\t\t\t\t\t\t\t\t$25,000");
		    c.setTextColor (Color.green);
		    c.println ("\t\t\t\t\t\t\t\t$16,000");
		    c.println ("\t\t\t\t\t\t\t\t$8,000");
		    c.println ("\t\t\t\t\t\t\t\t$4,000");
		    c.println ("\t\t\t\t\t\t\t\t$2,000");
		    c.setTextColor (Color.red);
		    c.println ("\t\t\t\t\t\t\t\t$1,000");
		    c.setTextColor (Color.green);
		    c.println ("\t\t\t\t\t\t\t\t$500");
		    c.println ("\t\t\t\t\t\t\t\t$300");
		    c.println ("\t\t\t\t\t\t\t\t$200");
		    c.println ("\t\t\t\t\t\t\t\t$100");
		}

		//display the possible answers to the question
		c.setTextColor (Color.magenta);
		c.print (" ");
		c.print (questions [x] [1], 30);
		c.print (" ");
		c.println (questions [x] [2], 30);
		c.print (" ");
		c.print (questions [x] [3], 30);
		c.print (" ");
		c.println (questions [x] [4], 30);
		c.setTextColor (Color.black);

		//display the error message if the user does not enter a menu choice between 1 and 7
		if ((i < 1) || (i > 7))
		{
		    c.println ("\nInvalid response.  What is your answer between 1-7 (5 for \nhelp line and 6 is to save progress and 7 is to walk away)");
		    roundCounter++;
		}

		//do not increase counter if it is the first round
		else if (roundCounter == 0)
		{
		    c.println ("\nWhat is your answer between 1-7 (5 for help line and 6 is to \nsave progress and 7 is to walk away)");
		}

		//draw millionaire computer and host
		c.setColor (Color.black);
		c.drawOval (259, 130, 30, 40);
		c.drawOval (389, 130, 30, 40);
		c.drawOval (270, 55, 140, 170);
		c.drawOval (321, 190, 41, 100);
		c.setColor (new Color (255, 239, 213));
		c.fillOval (270, 55, 140, 170);
		c.fillOval (322, 190, 40, 100);
		c.fillOval (260, 130, 30, 40);
		c.fillOval (388, 130, 30, 40);
		c.setColor (Color.red);
		c.fillOval (314, 166, 56, 35);
		c.setColor (new Color (255, 239, 213));
		c.fillArc (314, 166, 56, 35, 0, 180);
		c.setColor (new Color (255, 255, 255));
		c.fillOval (311, 121, 20, 20);
		c.fillOval (351, 121, 20, 20);
		c.setColor (Color.black);
		c.fillArc (275, 55, 130, 115, 0, 180);
		c.fillOval (315, 125, 12, 12);
		c.fillOval (355, 125, 12, 12);
		c.fillOval (335, 162, 5, 5);
		c.fillOval (343, 162, 5, 5);
		c.drawArc (332, 137, 1, 42, 160, 180);
		c.drawArc (350, 137, 1, 42, 160, 180);
		c.setColor (Color.blue);
		c.fillRect (254, 240, 180, 103);
		c.setColor (Color.black);
		c.fillRect (294, 272, 6, 71);
		c.fillRect (388, 272, 6, 71);
		c.fillRect (355, 278, 25, 30);
		c.setColor (Color.lightGray);
		c.fillRect (95, 260, 50, 83);
		c.setColor (Color.gray);
		c.fillRect (35, 140, 170, 120);
		c.setColor (Color.blue);
		c.fillRect (45, 150, 150, 50);
		c.setColor (new Color (106, 90, 205));
		c.fillRect (45, 210, 70, 15);
		c.fillRect (125, 210, 70, 15);
		c.fillRect (45, 230, 70, 15);
		c.fillRect (125, 230, 70, 15);
		c.setColor (Color.black);
		c.drawRect (35, 140, 170, 120);
		c.drawRect (45, 150, 150, 50);
		c.drawRect (45, 210, 70, 15);
		c.drawRect (125, 210, 70, 15);
		c.drawRect (45, 230, 70, 15);
		c.drawRect (125, 230, 70, 15);
		c.drawOval (270, 55, 140, 170);
		c.drawRect (254, 240, 180, 103);
		c.drawRect (95, 260, 50, 83);
		c.drawRect (5, 359, 238, 19);
		c.drawRect (5, 382, 238, 19);
		c.drawRect (247, 359, 238, 19);
		c.drawRect (247, 382, 238, 19);
		c.fillRect (496, 0, 1, 800);
		c.fillRect (190, 18, 110, 2);

		i = c.readInt ();
		c.clear ();
	    }
	    while ((i < 1) || (i > 7));

	    //call upon help line menu if the user inputted the help line menu choice
	    if (i == 5)
	    {
		helpLines ();
	    }

	    //call upon save option if the user inputted the save menu choice
	    else if (i == 6)
	    {
		save ();

	    }

	    //end the rounds if the user decides to walk with their current money
	    else if (i == 7)
	    {
		end = 1;
	    }

	    //check to see if the user inputs the correct answer
	    else if (questions [x] [i] == questions [x] [5])
	    {

		//play clapping sound
		playClapping ();
		c.clear ();
		c.print ("Congratulations, you were correct!  Your current total is ");

		//display the amount of money you have won depending on what round you are on (e.g. if on round 1, display $100)
		if (counter == 1)
		{
		    c.println ("$100.");
		}

		if (counter == 2)
		{
		    c.println ("$200.");
		}

		if (counter == 3)
		{
		    c.println ("$300.");
		}

		if (counter == 4)
		{
		    c.println ("$500.");
		}

		if (counter == 5)
		{
		    c.println ("$1,000.  The least \namount you can walk away with today is $1,000");
		}

		if (counter == 6)
		{
		    c.println ("$2,000.");
		}

		if (counter == 7)
		{
		    c.println ("$4,000.");
		}

		if (counter == 8)
		{
		    c.println ("$8,000.");
		}

		if (counter == 9)
		{
		    c.println ("$16,000.");
		}

		if (counter == 10)
		{
		    c.println ("$25,000.  The least \namount you can walk away with today is $25,000");
		}

		if (counter == 11)
		{
		    c.println ("$50,000.");
		}

		if (counter == 12)
		{
		    c.println ("$100,000.");
		}

		if (counter == 13)
		{
		    c.println ("$250,000.");
		}

		if (counter == 14)
		{
		    c.println ("$500,000.");
		}

		if (counter == 15)
		{
		    playMillionDollars ();
		    c.println ("$1,000,000.");
		    end = 1;
		    millionDollars ();
		}

		//when ready type anything to continue to go to next round
		c.print ("\nPlease type anything when you are ready to go to the ");
		if (counter == 15)
		{
		    c.println ("results page.");
		}
		else
		{
		    c.println ("next round.");
		}
		response = c.readLine ();
		c.clear ();
	    }

	    //if user answered the question incorrectly display losing message
	    else
	    {

		//play buzzer sound
		playBuzzer ();
		c.clear ();
		c.println ("I'm sorry but that is not the correct answer.  The correct answer was " + questions [x] [5] + ".");
		end = 1;

		//when ready type anything to continue to go to results page
		c.println ("\nPlease type anything when you are ready to go to results.");
		response = c.readLine ();
		c.clear ();
	    }
	}
	while (end != 1);
    }


    //this method finds the MillionDollars image and declares it as the variable image
    public Millionaire ()
    {

	//try to find the MillionDollar image
	try
	{
	    File input = new File ("MillionDollars.jpg");
	    image = ImageIO.read (input);
	}

	//if the program cannot find the image display error message
	catch (IOException ie)
	{
	    System.out.println ("Error:" + ie.getMessage ());
	}
    }


    //draws image
    public void paint (Graphics g)
    {
	g.drawImage (image, 0, 0, null);
    }


    //this method dispalys the image when the user wins a million dollars in a j frame
    public static void millionDollars () throws IOException
    {
	JFrame frame = new JFrame ("Congratulations! You've just won a million dollars!");
	Panel panel = new Millionaire ();
	frame.getContentPane ().add (panel);
	frame.setSize (367, 434);
	frame.setVisible (true);
    }


    //this method displays the help lines menu
    public static void helpLines () throws IOException
    {
	c.clear ();

	//menu options
	c.println ("\t\t\t\t***HELP LINE MENU***");
	c.println ("1.   50:50");
	c.println ("2.   Ask the Audiance");
	c.println ("3.   Call a Friend");
	c.println ("4.   Back");
	c.println ("\nWhat is your choice (1-4):");
	choice = c.readInt ();
	c.clear ();

	//if the user does not enter a number between 1 and 4 the error message will be displayed
	while ((choice < 1) || (choice > 4))
	{
	    c.println ("\t\t\t\t***HELP LINE MENU***");
	    c.println ("1.   50:50");
	    c.println ("2.   Ask the Audiance");
	    c.println ("3.   Call a Friend");
	    c.println ("4.   Back");
	    c.println ("\nInvalid response.  Please enter your choice 1-4.");
	    choice = c.readInt ();
	    c.clear ();
	}

	//if the user already used the help line the error message will be displayed
	while (((choice == 1) & (fiftyCounter == 1)) | ((choice == 2) & (audienceCounter == 1)) | ((choice == 3) & (callCounter == 1)))
	{
	    c.println ("\t\t\t\t***HELP LINE MENU***");
	    c.println ("1.   50:50");
	    c.println ("2.   Ask the Audiance");
	    c.println ("3.   Call a Friend");
	    c.println ("4.   Back");
	    c.println ("\nYou have already chosen this help line.  Please chose another help line.");
	    choice = c.readInt ();
	    c.clear ();
	}


	//evaluates a case depending on which menu number between 1 and 4 the user inputs (ex. input number 1 would go to case 1)
	switch (choice)
	{

		//this case calls upon the fifty50 method
	    case 1:
		fifty50 ();
		fiftyCounter++;
		break;

		//this case calls upon the audience method
	    case 2:
		audience ();
		audienceCounter++;
		break;

		//this case calls upon the call method
	    case 3:
		call ();
		callCounter++;
		break;
	}

	//call upon the rounds method
	rounds ();
    }


    //this method eliminates 2 of the wrong answers
    public static void fifty50 ()
    {
	c.clear ();

	//displays the correct answer
	c.print ("The answer can either be " + questions [x] [5] + " or ");

	//display second possible answer (e.g. display possible answer two if possible answer one is the correct answer)
	if (questions [x] [5] == questions [x] [1])
	{
	    c.println (questions [x] [2] + ".\n");
	}


	if (questions [x] [5] == questions [x] [2])
	{
	    c.println (questions [x] [3] + ".\n");
	}


	if (questions [x] [5] == questions [x] [3])
	{
	    c.println (questions [x] [4] + ".\n");
	}


	if (questions [x] [5] == questions [x] [4])
	{
	    c.println (questions [x] [1] + ".\n");
	}

	//when ready type anything to continue to go to next round
	c.println ("Please type anything when you are ready to go to the next round.");
	response = c.readLine ();
	c.clear ();
    }


    //this method asks the audience for their answer to the question and displays the percentage of the audience who selected each option
    public static void audience ()
    {
	c.clear ();
	c.print ("The audience guessed: \n");

	//display the results of the poll with the correct answer as the highest percentage (e.g. if answer 1 is correct, answer one will have highest percentage of audience selection)
	if (questions [x] [5] == questions [x] [1])
	{
	    c.println (questions [x] [1] + " 50%\n" + questions [x] [2] + " 18%\n" + questions [x] [3] + " 16%\n" + questions [x] [4] + " 16%\n");
	}

	if (questions [x] [5] == questions [x] [2])
	{
	    c.println (questions [x] [1] + " 18%\n" + questions [x] [2] + " 50%\n" + questions [x] [3] + " 16%\n" + questions [x] [4] + " 16%\n");
	}


	if (questions [x] [5] == questions [x] [3])
	{
	    c.println (questions [x] [1] + " 16%\n" + questions [x] [2] + " 16%\n" + questions [x] [3] + " 50%\n" + questions [x] [4] + " 18%\n");
	}

	if (questions [x] [5] == questions [x] [4])
	{
	    c.println (questions [x] [1] + " 16%\n" + questions [x] [2] + " 16%\n" + questions [x] [3] + " 18%\n" + questions [x] [4] + " 50%\n");
	}

	//when ready type anything to continue to round method
	c.println ("Please type anything when you are ready to go to the next round.");
	response = c.readLine ();
	c.clear ();
    }


    //this method calls a friend to help answer the question
    public static void call ()
    {
	c.clear ();

	//display friends answer to the question
	c.print ("The person on the line thinks " + questions [x] [5] + " is the answer.\n");

	//when ready type anything to continue to next round
	c.println ("Please type anything when you are ready to go to the next round.");
	response = c.readLine ();
	c.clear ();
    }


    //this method displays the amount of money the highScore won after they have lost, walked away or won one million dollars
    public static void results ()
    {
	c.clear ();

	//displays the amount of money won if the user walked away
	if (i == 7)
	{
	    c.print ("CONGRATULATIONS " + name [10] + "!  Today you get to walk away with ");

	    //display amount of money won depending on round the user walked away on (e.g. if the user walked away on round 1, display $0)
	    if (counter == 1)
	    {
		c.println ("$0.");
		highScore [10] = 0;
	    }

	    if (counter == 2)
	    {
		c.println ("$100.");
		highScore [10] = 100;
	    }

	    if (counter == 3)
	    {
		c.println ("$200.");
		highScore [10] = 200;
	    }

	    if (counter == 4)
	    {
		c.println ("$300.");
		highScore [10] = 300;
	    }

	    if (counter == 5)
	    {
		c.println ("$500.");
		highScore [10] = 500;
	    }

	    if (counter == 6)
	    {
		c.println ("$1,000.");
		highScore [10] = 1000;
	    }

	    if (counter == 7)
	    {
		c.println ("$2,000.");
		highScore [10] = 2000;
	    }

	    if (counter == 8)
	    {
		c.println ("$4,000.");
		highScore [10] = 4000;
	    }

	    if (counter == 9)
	    {
		c.println ("$8,000.");
		highScore [10] = 8000;
	    }

	    if (counter == 10)
	    {
		c.println ("$16,000.");
		highScore [10] = 16000;
	    }

	    if (counter == 11)
	    {
		c.println ("$25,000.");
		highScore [10] = 25000;
	    }

	    if (counter == 12)
	    {
		c.println ("$50,000.");
		highScore [10] = 50000;
	    }

	    if (counter == 13)
	    {
		c.println ("$100,000.");
		highScore [10] = 100000;
	    }

	    if (counter == 14)
	    {
		c.println ("$250,000.");
		highScore [10] = 250000;
	    }

	    if (counter == 15)
	    {
		c.println ("$500,000.");
		highScore [10] = 500000;
	    }
	}

	//display congratulations message when the user wins a million dollars
	else if (counter == 15)
	{
	    c.println ("CONGRATULATIONS " + name [10] + "!  Today you get to walk away with $1,000,000.");
	    highScore [10] = 1000000;
	}

	//display minimum amount of money won if user answers question incorrectly
	else
	{

	    //display minimum amount of money won (e.g. if user loses on round 1 to 4 they won $0)
	    if (counter < 5)
	    {
		c.println ("CONGRATULATIONS " + name [10] + "!  Today you get to walk away with $0");
		highScore [10] = 0;
	    }

	    else if (counter > 10)
	    {
		c.println ("CONGRATULATIONS " + name [10] + "!  Today you get to walk away with $25,000");
		highScore [10] = 25000;
	    }

	    else
	    {
		c.println ("CONGRATULATIONS " + name [10] + "!  Today you get to walk away with $1000");
		highScore [10] = 1000;
	    }
	}

	//organizes high scores from biggest to smallest
	for (y = 1 ; y < 11 ; y++)
	{
	    largest = y;
	    for (int p = y ; p < 11 ; p++)
	    {
		if (highScore [p] > highScore [largest])
		{
		    largest = p;
		}
	    }
	    temp = highScore [y];
	    temp2 = name [y];
	    highScore [y] = highScore [largest];
	    name [y] = name [largest];
	    highScore [largest] = temp;
	    name [largest] = temp2;
	}

	//when ready type anything to continue to main menu
	c.println ("\nPlease type anything when you are ready to go to main menu.");
	response = c.readLine ();
	c.clear ();
    }


    //this method displays the high score menu
    public static void highScoresMenu ()
    {

	//display menu until user enters high score menu choice 6
	do
	{
	    c.clear ();

	    //menu options
	    c.println ("\t\t\t   ***HIGH SCORE MENU***");
	    c.println ("1.   Display");
	    c.println ("2.   Add");
	    c.println ("3.   Edit");
	    c.println ("4.   Remove");
	    c.println ("5.   Search");
	    c.println ("6.   Back");

	    //ask user to input menu option until the user inputs a menu option between 1 and 6
	    do
	    {
		c.println ("\nWhat is your choice (1-6):");
		choice = c.readInt ();

		//display error meeage if the user does not enter a menu choice between 1 and 6
		if ((choice < 1) || (choice > 6))
		{
		    c.println ("Invalid response.  Please enter your choice 1-6.");
		}
	    }
	    while ((choice < 1) || (choice > 6));

	    //evaluate case depending on which menu choice between 1 and 6 the user inputs (ex. input number 1 would go to case 1)
	    switch (choice)
	    {
		case 1:
		    highScores ();
		    break;
		case 2:
		    input ();
		    break;
		case 3:
		    modify ();
		    break;
		case 4:
		    delete ();
		    break;
		case 5:
		    search ();
		    break;
	    }

	    //sort top 9 high scores from largest to smallest
	    for (y = 1 ; y < 11 ; y++)
	    {
		largest = y;

		//cycle through all high scores
		for (int p = y ; p < 11 ; p++)
		{

		    //determine if high score at element p is greater than previous highest score
		    if (highScore [p] > highScore [largest])
		    {
			largest = p;
		    }
		}
		temp = highScore [y];
		temp2 = name [y];
		highScore [y] = highScore [largest];
		name [y] = name [largest];
		highScore [largest] = temp;
		name [largest] = temp2;
	    }
	}
	while (choice != 6);
    }


    //this method allows the user to add high score information
    public static void input ()
    {
	c.clear ();
	//ask the user for high acore information to be added
	c.println ("What is the name of the high score you want to add?");
	name [10] = c.readLine ();
	c.println ("\nWhat is the high score total money?");
	highScore [10] = c.readInt ();
	c.clear ();
    }


    //this method allows the user to modify any high score information previously added
    public static void modify ()
    {
	String modPlayer;
	int flag;
	boolean found;
	flag = 0;
	found = false;
	c.clear ();

	//ask user for high score name they would like to modify
	c.println ("What is the name of the high score you wish to modify?");
	modPlayer = c.readLine ();

	//search through all high score names to see if any of them have the same name as the one the user wants to modify
	for (x = 1 ; x < 11 ; x++)
	{

	    //if there is a matching name with the one the user wants to modify the high score will be flagged
	    if (modPlayer.compareTo (name [x]) == 0)
	    {
		flag = x;
		found = true;
	    }
	}


	//if there is no matching name then the error message will be displayed
	if (found == false)
	{
	    c.println ("There is currently no high score under this name.");
	}


	//if there is a matching name the user will be prompted to enter the new high score information
	else
	{
	    c.clear ();

	    //ask user for information for new high score
	    c.println ("What is the modified name of the high score player?");
	    name [flag] = c.readLine ();
	    c.println ("\nWhat is the modified high score total money?");
	    highScore [flag] = c.readInt ();
	    c.clear ();
	}
    }


    //this method allows the user to delete any high score that was previously added
    public static void delete ()
    {
	String delPlayer;
	int flag = 0;
	boolean found = false;
	c.clear ();
	c.println ("What is the name of the high score you wish to delete?");
	delPlayer = c.readLine ();

	//search through all high score names seeing if any of them have the same name as the one the user wants to delete
	for (x = 1 ; x < 11 ; x++)
	{

	    //if there is a matching name with the one the user wants to delete the high score will be flagged
	    if (delPlayer.compareTo (name [x]) == 0)
	    {
		flag = x;
		found = true;
	    }
	}

	//if there are no matching names then the error message will be displayed
	if (found == false)
	{
	    c.println ("There is currently no high score under this name.");
	}


	//if there is a matching name with the one the user wants to delete, all the other players will be moved up by one position
	else
	{
	    for (x = flag ; x < 11 ; x++)
	    {
		name [x] = name [x + 1];
		highScore [x] = highScore [x + 1];
	    }
	    c.println ("The high score has been deleted.");
	}
    }


    //this method allows the user to search any high score that was previously added
    public static void search ()
    {
	String serPlayer;
	int flag = 0;
	boolean found = false;
	c.clear ();
	c.println ("What is the name of the high score you wish to search?");
	serPlayer = c.readLine ();
	c.clear ();

	//search through all high score names seeing if any of them have the same name as the one the user wants to modify
	for (x = 1 ; x < 11 ; x++)
	{

	    //if there is a matching name with the one the user wants to modify it will flag the high score
	    if (serPlayer.compareTo (name [x]) == 0)
	    {
		flag = x;
		found = true;
	    }
	}


	//if there are no matching names then the error message will be displayed
	if (found == false)
	{
	    c.println ("There is currently no high score under this name.");
	}


	//if there is a matching name with the one the user wants to search, display their high score and name
	else
	{
	    c.println ("Name: " + name [flag]);
	    c.println ("Age: " + highScore [flag]);
	}


	//when ready type anything to continue to high score menu
	c.println ("\nPlease type anything when you are ready to go back to the menu.");
	response = c.readLine ();
	c.clear ();
    }


    //this method displays the top 9 high scores in table form from largest to smallest high score
    public static void highScores ()
    {
	c.clear ();

	//sort top 9 high scores from largest to smallest
	for (y = 1 ; y < 11 ; y++)
	{
	    largest = y;

	    //cycle through all high scores
	    for (int p = y ; p < 11 ; p++)
	    {

		//determine if high score at element p is greater than previous highest score
		if (highScore [p] > highScore [largest])
		{
		    largest = p;
		}
	    }
	    temp = highScore [y];
	    temp2 = name [y];
	    highScore [y] = highScore [largest];
	    name [y] = name [largest];
	    highScore [largest] = temp;
	    name [largest] = temp2;
	}

	//outputs high scores from largest to smallest in table
	c.println ("\t\t\t\t***HIGHSCORES***");
	c.print (("NAME"), 20);
	c.println ("SCORE (IN DOLLARS)");
	for (y = 1 ; y < 10 ; y++)
	{
	    c.print ((name [y]), 20);
	    c.println (highScore [y]);
	}

	//when ready type anything to continue to high score menu
	c.println ("\nPlease type anything when you are ready to go to main menu.");
	response = c.readLine ();
	c.clear ();
    }


    //display the rules on how to play the millionaire game
    public static void rules ()
    {
	c.clear ();
	c.println ("\t\t\t\t***RULES***\n");
	c.println ("The objective of the game is to win one million dollars by answering the");
	c.println ("questions correctly for all fifteen rounds of the game.  Each round comprises of");
	c.println ("a multiple choice question which needs to be answered by the user. The questions");
	c.println ("get harder as the game progresses.  If you correctly answer the question asked ");
	c.println ("you will go to the next round. If you answer the question wrong, you leave with ");
	c.println ("the safe round money.  If you are on rounds one to five the safe round money is ");
	c.println ("$0. If you are on rounds six to ten the safe round money is $1000. If you are on");
	c.println ("rounds eleven to fifteen the safe round money is $25,000.  If you do not know ");
	c.println ("the answer to the question asked, you can either walk with the money you ");
	c.println ("currently won or use one of the help lines.  The three help lines are 50:50 ");
	c.println ("removes two of the incorrect answers), ask the audience (asks the audience what ");
	c.println ("they think the answer is) and call a friend (contact a friend to ask them what ");
	c.println ("they think the answer is).  You can only use each help line once.");

	//when ready type anything to continue to main menu
	c.println ("\nPlease type anything when you are ready to main menu");
	response = c.readLine ();
	c.clear ();
    }


    //save the user progress in game to file
    public static void save () throws IOException
    {

	//ask user which directory they want to save the progress file to
	c.println ("Please enter the path of the file to save your progress to:");
	String toFile = c.readLine ();

	//create print writer to write to file
	PrintWriter pw = new PrintWriter (new FileWriter (toFile));

	//write users name [10], round number, and which help lines are left to file
	pw.println (name [10]);
	pw.println ("Progress: Round " + (counter - 1));
	pw.println (fiftyCounter);
	pw.println (audienceCounter);
	pw.println (callCounter);
	pw.close ();
	c.clear ();
	rounds ();
    }


    //this method loads a previously saved file
    public static void load () throws IOException
    {
	String delPlayer;
	int flag = 0;
	boolean found = false;
	c.clear ();

	//ask user where the saved file is located
	c.println ("Please enter the path of the file to be read:");
	String fromFile = c.readLine ();

	//create a buffered reader to read contents of file
	BufferedReader br = new BufferedReader (new FileReader (fromFile));

	//read the name [10], round number, and which help lines were used by the user in the saved game
	delPlayer = br.readLine ();
	name [10] = delPlayer;
	line = br.readLine ();
	line = line.substring (16);
	counter = Integer.parseInt (line);
	line = br.readLine ();
	fiftyCounter = Integer.parseInt (line);
	line = br.readLine ();
	audienceCounter = Integer.parseInt (line);
	line = br.readLine ();
	callCounter = Integer.parseInt (line);
	c.clear ();

	//search through all highScore names seeing if any of them have the same name as the one the user wants to delete
	for (x = 1 ; x < 10 ; x++)
	{

	    //if there is a matching name with the one the user wants to delete the highScore will be flagged
	    if (delPlayer.compareTo (name [x]) == 0)
	    {
		flag = x;
		found = true;
	    }
	}

	if (found == true)
	{
	    for (x = flag ; x < 10 ; x++)
	    {
		name [x] = name [x + 1];
		highScore [x] = highScore [x + 1];
	    }
	}

    }


    //this method plays intro music
    public static void playMillionaireIntro () throws IOException
    {

	//create audio input stream for Millionaire.wav music file
	AudioStream as = new AudioStream (new FileInputStream ("Millionaire.wav"));

	//play music file
	ap.start (as);

    }


    //this method plays clapping music
    public static void playClapping () throws IOException
    {

	//create audio input stream for Clap.wav music file
	AudioStream as2 = new AudioStream (new FileInputStream ("Clap.wav"));

	//play music file
	ap.start (as2);

    }


    //this method plays buzzer music
    public static void playBuzzer () throws IOException
    {

	//create audio input stream for Buzzer.wav music file
	AudioStream as3 = new AudioStream (new FileInputStream ("Buzzer.wav"));

	//play music file
	ap.start (as3);
    }


    //this method plays question music
    public static void playQuestion () throws IOException
    {

	//create audio input stream for Question.wav music file
	AudioStream as4 = new AudioStream (new FileInputStream ("Question.wav"));

	//play music file
	ap.start (as4);
    }


    //this method plays milllion dollars music
    public static void playMillionDollars () throws IOException
    {

	//create audio input stream for MillionDollars.wav music file
	AudioStream as5 = new AudioStream (new FileInputStream ("MillionDollars.wav"));

	//play music file
	ap.start (as5);
    }


    //saves highScores to highScores file
    public static void highScoreSaver () throws IOException
    {
	int p = y;

	//create print writer to write to file
	PrintWriter pw = new PrintWriter (new FileWriter ("highScores.txt"));

	//write users name and highScore to file
	for (y = 1 ; y < 11 ; y++)
	{
	    pw.println (name [y]);
	    pw.println (highScore [y]);
	}
	pw.close ();
    }
}


