/*Brendan Kelly
 *Section 01
 *A20308995
 *
 *This Client program will scan information from the file "runwaycs115.txt, and store that information into 
 *variables of Designer Class Objects. The Designer Class Objects will be assigned to an array where every 
 *Designer is assigned to their own element of that array. After that, the user will be prompted to call the
 *menu or exit the program. When the menu is called, the user will have several options for what method they 
 *want to call. Depending on which method the user calls, the user will be prompted again for input. That input
 *is used to decide how the method is used. If the user input is valid for that method, certain attributes of
 *specified Designer Objects will be printed, and the method returns some value. After returning to the menu,
 *the instance of that method call in the finalStatsArray is incremented and the user is again prompted with
 *the menu, and can continue to call more methods. Once the user is done with the menu,and enters "q", the 
 *program will return to main, and the user can decide to call the menu again, or exit the program. If the
 *user decides to exit, finalStats will be called, and then the program will exit.*/

package designer;
import java.text.DecimalFormat;
import java.util.*;
import java.io.*;

public class KellyBrendan {
	
	public static void main(String [] args) throws IOException
	{
		File inFile = new File("runwaycs115.txt");
		Scanner scan = new Scanner(inFile);
	
		
		String lNM; //lastName of the Designer Object
		int seasonNumberM; //season the Designer Object belongs too.
		String seasonM = "Season:";// used as for a flag to exit loop
		
		//Instantiating an ArrayList that will hold all of the Designer Objects in one season, and
		//grow as each element is added.
		ArrayList<Designer> designersList = new ArrayList<Designer>();
		
		//Instantiating an ArrayList of the number of designers per season growing as more seasons are read in
		ArrayList<Integer> designersPerSeason = new ArrayList<Integer>();
	
		/*Instantiating a two dimensional ArrayList of an ArrayList of Designer Class Objects. Each element of 
		 * the seasonList will hold an ArrayList of Designer Objects from the corresponding season*/
		ArrayList<ArrayList<Designer>> seasonList = new ArrayList<ArrayList<Designer>>();
		
		//Instantiating an array that will be filled with the last and first name of every designer, 
		//allowing for a maximum of 1000 designers. This will be used for the sortList() method.
		ArrayList<String> designerNames = new ArrayList<String>();
		
		//Scans the first line of the file so that the following for loop can start by scanning in the
		//season number. 
		String seasonL = scan.next();
		
		/*While the first character of the first line is '#', the line is a comment line and thus needs 
		to be ignored and scanned into garbage. The first String of the next line is then read. If the 
		first character of that String is '#', the loop will run through another iteration. This will 
		continue until there are no comment lines, and "Season:" will be read in.*/
		while (seasonL.charAt(0) == '#')
		{
			String garbage = scan.nextLine();
			seasonL = scan.next();
		}
		//latestSeason represents the current season that the Designer Objects are being scanned from.
		int latestSeason = 1;
			
		//This for loop is used to assign designersList ArrayLists to the seasonList ArrayList
		for (int i = 0; i < latestSeason;i++)
		{
			//This will always be true unless the line is a comment line or until "ENDOFFILE" is read. 
			if (seasonL.equals(seasonM))
			{
				//The season number is read, and assigned to all Designer Objects following it, until the
				//next iteration of the first for loop. At which point the next season number will be read in 
				//and assigned to the appropriate Designer objects.
				seasonNumberM = scan.nextInt();
			
				//After the season number the next line should be an int that is equal to the number
				//of designers in that season. While the next line is not an int, the line is scanned
				//to garbage because it must be a comment line. This will repeat until the next line
				//starts with an int.
				while (!scan.hasNextInt())
				{	
					String garbage = scan.nextLine();
				}
				
				//Assigning the value of the next integer read after season number, to the element at index i.
				designersPerSeason.add(i, scan.nextInt());				
				//A new designersList is instantiated for each iteration (or season)
				designersList = new ArrayList<Designer>();
				for( int j = 0; j < designersPerSeason.get(i); j++)
				{
					//The first entry of the line should be the last name of the designer. The 
					//first character of the line is assigned to starSingal just in case this is 
					//a comment line
					lNM = scan.next();
					//While the line starts with '#', it is a comment line
					while(lNM.charAt(0) == '#')
					{
						//The line is read to garbage
						String garbage = scan.nextLine();
						//The first String of the next line is read into lNM
						lNM = scan.next();
						//If the line is another comment line, the loop will iterate again, 
						//otherwise the information for the designer will be scanned in.
					}
					//Instantiating a new Designer object
					Designer des = new Designer();
					
					//Assigning the attributes to the Designer object based on the line being read 
					des.SetLastName(lNM); 
					des.SetFirstName(scan.next());  
					des.SetAge(scan.nextInt());
					des.SetSalary(scan.nextDouble());
					des.SetStatus(scan.next().charAt(0));
					des.SetFavShow(scan.nextLine());
					des.SetSeasonNumber(seasonNumberM);
					
					//Adding the current Designer object reference to the designersList for the current season
					designersList.add(des);
					
					//Assigning the last and first name of the Designer object to the designerNames
					//array at the current designerIndex
					designerNames.add(des.GetLastName() + " " + des.GetFirstName());
				}
				//adding the current designersList to the last element in seasonList.
				seasonList.add(designersList);
				
				/*After all necessary data is entered into the Designer Objects of that particular season, the next line
				of input is read into seasonL. If the next line of the file reads "ENDOFFILE", the condition of the initial 
				if statement will evaluate to false. This is needed so that the compiler will not try to read in anymore 
				data, since there is no more data to be read in.*/
				seasonL = scan.next();
				while (seasonL.charAt(0) == '#')
				{
					String garbage = scan.nextLine();
					seasonL = scan.next();	
				}
				//If there is another season to be read, latestSeason will be incremented, and there will be another iteration
				//of the for loop.
				if (seasonL.equals(seasonM))
						latestSeason++;
			}
			
		}	
		scan.close();
		
		//Instantiating an array of integers to store the amount of times the user calls each method
		ArrayList<Integer> finalStatsList = new ArrayList<Integer>();
		finalStatsList.add(0, 0);
		finalStatsList.add(1, 0);
		finalStatsList.add(2, 0);
		finalStatsList.add(3, 0);
		finalStatsList.add(4, 0);
		finalStatsList.add(5, 0);
		finalStatsList.add(6, 0);
		finalStatsList.add(7, 0);
		
		//Initializing the exit flag of the while statement so that it will be true 
		boolean exit = false;
		//while the user is using the program, this will make sure that after returning from menu()
		//main() will not be run from the beginning, and the values for finalStats() will not be lost.
		//Also repeats when the user enters something invalid.
		while(exit == false)
		{
			//Prompts user if they want to access the menu or quit the program
			Scanner scanM = new Scanner(System.in);
			System.out.print("Enter selection (m)enu or (q)uit ");
			String selection = scanM.nextLine().toLowerCase();//the user's input up to the end of the line is stored in selection
			char sel = selection.charAt(0);
			//using the toLowerCase method to make code more compact. 
			switch(sel)
			{
				case ('m'):
					menu(designersPerSeason,seasonList, finalStatsList, designerNames);
					break;
				case('q'):
					finalStats(finalStatsList);
					System.exit(0);
				default: 
					System.out.println("Invalid entry, please try again. ");
					break;
			}	
		}
	}
	
//--Menu items allow user to select what they would like to do
	public static void menu(ArrayList<Integer> dPS, ArrayList<ArrayList<Designer>> sL,
			ArrayList<Integer> fSL, ArrayList<String> dN)
	{
		//The user is prompted to enter one of the menu options.
		Scanner scanMe = new Scanner(System.in);
		System.out.print("Enter selection:"
				+ "'L' or 'l' : List all of the designer data available. "
				+ "\n\t\t'D' or 'd' : Display information about a particular designer. " 
				+ "\n\t\t'S' or 's' : Display designer information for desginers from a "
				+ "particular season. "
				+ "\n\t\t'M' or 'm' : Display salary (money) information. "
				+ "\n\t\t'T' or 't' : Display status information. "
				+ "\n\t\t'O' or 'o' : Display designers aplhabetically by last name."
				+ "\n\t\t'Q' or 'q' : Quit and return to main ");

		String userChoice = scanMe.nextLine().toLowerCase();
		char uC = userChoice.charAt(0);
		//The user's input is used to call whatever method they want to run, or prints an error 
		//message if their choice is not a valid menu option. The elements of the finalStatsArray
		//are incremented based on the user's input.
		switch (uC)
		{
		case 'l':
			listall(dPS, sL);
			fSL.set(0, fSL.get(0)+1);
			menu(dPS, sL, fSL, dN);
			break;
		case 'd':
			designerReport(dPS, sL);
			fSL.set(1, fSL.get(1)+1);
			menu(dPS, sL, fSL, dN);
			break;
		case 's':
			seasonReport(dPS, sL);
			fSL.set(2, fSL.get(2)+1);;
			menu(dPS, sL, fSL, dN);
			break;
		case 'm':
			salaryReport(dPS, sL);
			fSL.set(3, fSL.get(3)+1);;
			menu(dPS, sL, fSL, dN);
			break;
		case 't':
			statusReport(dPS, sL);
			fSL.set(4, fSL.get(4)+1);;
			menu(dPS, sL, fSL, dN);
			break;
		case 'o':
			sortList(dN, dPS, sL);
			fSL.set(5, fSL.get(5)+1);
			menu(dPS, sL, fSL, dN);
			break;
		case 'q':
			fSL.set(6, fSL.get(6)+1);
			break;
		default:
			System.out.println("Invalid menu option, please try again.");
			fSL.set(7, fSL.get(7) +1);
			menu(dPS, sL, fSL, dN);
			break;
		}
	}
	
//--List All Method
	public static void listall(ArrayList<Integer> dPS, ArrayList<ArrayList<Designer>> sL)
	{
		//Decimal format so salaries look right 
		DecimalFormat salaryFormat = new DecimalFormat("$.00");
	
		System.out.print("list (all) designers, (a)mateurs designers, or (p)rofessionals designers ");
		Scanner userIn = new Scanner(System.in);
		String userChoice = userIn.next();//The next String typed is stored in userChoice
		
		//This is used for when the user's input does not yield any designers
		int designersPrinted = 0;
		
		//This for loop will go through each designersList in seasonList
		for (int i = 0; i < sL.size(); i++)
		{
			//If there are any designers in the current season, the season exists and we print information
			//about the designers in that season
			if (dPS.get(i) > 0)
			{	
				//This for loop iterates through each Designer Object in the designersList for the current season
				for (int j = 0; j < dPS.get(i); j++)
				{
					/*If the user entered "all", the current designer in the arrayList will have its
					 * attributes printed. Or if the user entered 'p' and the status of the the current
					 * designer is 'p', the current designer will have its attributes printed. If the 
					 * user entered 'a' and the status of the current designer is 'a', the current designer
					 * will have its attributes printed */ 
					if (userChoice.equalsIgnoreCase("all") || 
							(userChoice.equalsIgnoreCase("p") && sL.get(i).get(j).GetStatus() == 'p')
							|| userChoice.equalsIgnoreCase("a") && sL.get(i).get(j).GetStatus() == 'a')
					{
						System.out.printf("%-15s %-15s %5d %10d %20s %6c %-26s %n", sL.get(i).get(j).GetLastName(), 
								sL.get(i).get(j).GetFirstName(), sL.get(i).get(j).GetSeasonNumber(),
								sL.get(i).get(j).GetAge(), salaryFormat.format(sL.get(i).get(j).GetSalary()),
								sL.get(i).get(j).GetStatus(), sL.get(i).get(j).GetFavShow());
						//A designer has had its attributes printed, and thus the user's entry is valid
						designersPrinted++;
					}
				}
			}
		}
		//If no designers were printed, the user entry must not have been valid.
		if (designersPrinted == 0)
			System.out.println("Sorry \"" + userChoice + "\" is not an option.");
	}
	
//--Designer Report Method
	public static boolean designerReport(ArrayList<Integer> dPS, ArrayList<ArrayList<Designer>> sL)
	{
		//Prompting user for a last name. userEntry is then scanned in.
		System.out.print("Enter the last name of the designer you are looking for: ");
		Scanner scan = new Scanner(System.in);
		String userEntry = scan.next();//The next line typed in is stored in userEntry
		
		//Initializes designersFound to 0 so that the method can return false when none are found.
		int designersFound = 0;
		
		//For all the designersLists in seasonList
		for (int i = 0; i < sL.size(); i++)
		{	
			//If there are any designers in the current season
			if (dPS.get(i) > 0)
			{
				//For every designer in this season
				for (int j = 0; j < dPS.get(i); j++)
				{
					//The last name of a particular Designer object is assigned to the variable designerMatch
					//that will be used to compare to userEntry. If they are the same, information from the 
					//matching Designer object will be printed.
					String designerMatch = sL.get(i).get(j).GetLastName();
					
					if (designerMatch.equals(userEntry))
					{
						System.out.printf("%8d %-15s %-15s %-26s %n", sL.get(i).get(j).GetSeasonNumber(),
								sL.get(i).get(j).GetLastName(), 
								sL.get(i).get(j).GetFirstName(),
								sL.get(i).get(j).GetFavShow());
						designersFound++;
					}
				}
			}		
		}
		//If any designers were found, the method returns true.If no designers were found, the user is 
		//informed and the method returns false. 
		if (designersFound > 0)
			return true;
		else
		{
			System.out.println("No designer found. ");
			return false;
		}
	}
	
//--Season Report Method
	public static boolean seasonReport(ArrayList<Integer> dPS, ArrayList<ArrayList<Designer>> sL)
	{
		//prompting user for season number or 'all' and preparing to scan input
		System.out.print("Enter a season number or 'all' ");
		Scanner scan = new Scanner(System.in);
		
		//Initiating total Salary and creating the decimal format so that it will look neat when printed
		double totalSalary = 0;
		DecimalFormat numberFormat = new DecimalFormat("$.00");
		
		//If the next input is an integer 
		if (scan.hasNextInt())
		{
			//The userIn is scanned in as an int
			int userIn = scan.nextInt();
			//userIn needs to be less than the total number of seasons and greater than 0 to prevent a null pointer exception
			if (userIn < sL.size() && userIn > 0)
			{
				//If there are any designers in the season the user entered
				if (dPS.get(userIn - 1)> 0)
				{	
					//For all the designers in that season
					for (int j = 0; j < dPS.get(userIn - 1); j++)
					{
						//print the attributes of the current Designer Object
						System.out.printf("%-15s %-15s  %10d %20s %n", sL.get(userIn-1).get(j).GetLastName(), 
								sL.get(userIn-1).get(j).GetFirstName(), sL.get(userIn-1).get(j).GetAge(), 
								numberFormat.format(sL.get(userIn-1).get(j).GetSalary()));
						//totalSalary is incremented by the salary of current designer
						totalSalary += sL.get(userIn-1).get(j).GetSalary();
					}
					System.out.printf("Total Salary:" + "%51s %nAverage Salary: %49s", numberFormat.format(totalSalary), 
						 numberFormat.format(totalSalary/dPS.get(userIn-1)) + "\n");
					return true;
				}
				//The user entered an int outside the possible range of seasons
				else 
				{
					System.out.println("Season not found. ");
					return false;
				}
			}
			//The user entered a season with no designers AKA the season doesn't exist (yet?)
			else 
				System.out.println("Season not found. ");
				return false;
		}
		//If the users input equals "all"
		else if (scan.nextLine().equals("all"))
		{	
			//for the number of designersLists in seasonList
			for (int i = 0; i < sL.size(); i++)
			{
				//if there are designers in the current season
				if (dPS.get(i) > 0)
				{	
					//for all the designers in that season
					for (int j = 0; j < dPS.get(i); j++)
					{
						//print attributes of current designer
						System.out.printf("%-15s %-15s  %10d %20s %n", sL.get(i).get(j).GetLastName(), 
								sL.get(i).get(j).GetFirstName(), sL.get(i).get(j).GetAge(), 
								numberFormat.format(sL.get(i).get(j).GetSalary()));
						//totalSalary is incremented by the salary of the current designer
						totalSalary += sL.get(i).get(j).GetSalary();
					}
					System.out.printf("Total Salary:" + "%51s %nAverage Salary: %49s", numberFormat.format(totalSalary), 
						 numberFormat.format(totalSalary/dPS.get(i)) + "\n");
					//Reseting totalSalary for the next season
					totalSalary = 0;
				} 	
			}
			return true;
		}
		//The user input is completely invalid
		else 
		{
			System.out.println("No season found.");
			return false;
		}
	}

//--Salary Report Method
	public static boolean salaryReport(ArrayList<Integer> dPS, ArrayList<ArrayList<Designer>> sL)
	{
		//Prompting user for designer name or "all" and preparing scanner for String input
		System.out.print("Enter the last name of the designer you are looking for or 'all': ");
		Scanner scan = new Scanner(System.in);
		
		//Scanning in user entry as String
		String userEntry = scan.next();
		
		//Decimal format to make salary pretty
		DecimalFormat salaryFormat = new DecimalFormat("$.00");
		
		//Initializes designersFound to 0 so that the method can return false if none are found.
		int designersFound = 0;
		
		//This for loop will iterate through each designersList (or season) in seasonList
		for (int i = 0; i < sL.size(); i++)
		{
			//If there are designers in the current season
			if (dPS.get(i) > 0)
			{
				//This for loop iterates through each designer in the current season
				for (int j = 0; j < dPS.get(i); j++)
				{
					//The last name of a particular Designer object is assigned to the variable designerMatch
					//that will be used to compare to userEntry. If they are the same, information from the 
					//matching Designer object will be printed.
					String designerMatch = sL.get(i).get(j).GetLastName();
					if (designerMatch.equals(userEntry))
					{
						System.out.printf("%-15s %-15s %15s %n",sL.get(i).get(j).GetLastName(), 
								sL.get(i).get(j).GetFirstName(), 
								salaryFormat.format(sL.get(i).get(j).GetSalary()));
						designersFound++;
					}
					//If the user has entered 'all'
					else if (userEntry.equals("all"))
					{
						//The salary information is printed for every designer
						System.out.printf("%-15s %-15s %15s %n", sL.get(i).get(j).GetLastName(), 
								sL.get(i).get(j).GetFirstName(), 
								salaryFormat.format(sL.get(i).get(j).GetSalary()));
						designersFound++;	
					}
				}
			}	
		}
		//If any designers were found, the method returns true. If no designers were found, the user is 
		//informed and the method returns false. 
		if (designersFound > 0)
			return true;
		else
		{
			System.out.println("No designer found. ");
			return false;
		}
	}
	
//--Status Report Method
	public static boolean statusReport(ArrayList<Integer> dPS, ArrayList<ArrayList<Designer>> sL)
	{
		//Prompting user for status or "all"
		System.out.print("What status would you like to search for. (a)mateur, (p)rofessional, "
				+ "or 'all'? ");
		Scanner scan = new Scanner(System.in);
		//Scanning user's input into a string 
		String userString = scan.next();
		
		//If the user entered "all" or "a", the amateur Designer Objects will have their attributes printed
		if (userString.equalsIgnoreCase("all") || userString.equalsIgnoreCase("a"))
		{
			//For all the designersLists in seasonList
			for(int i = 0; i < sL.size(); i++)
			{
				//if there are designers in the current designersList
				if (dPS.get(i) > 0)
				{
					//For all the designers in that designersList
					for (int j = 0; j < dPS.get(i); j++)
					{
						//Prints amateur designer objects
						if (sL.get(i).get(j).GetStatus() == 'a')
						{
							System.out.printf("%-15s %-15s %-20s %n", sL.get(i).get(j).GetLastName(),
									sL.get(i).get(j).GetFirstName(), "CURRENTLY AMATEUR");
						}
					}
				}
			}
			//if userString equals all, we can't yet return, as we need to print the professional Designer Objects
			if (!userString.equalsIgnoreCase("all"))
			return true;
		}
		//If the user entered "all" or "P", the professional Designer Objects will have their attributes printed, along
		//with the potential career earnings 
		if (userString.equalsIgnoreCase("all") || userString.equalsIgnoreCase("p"))
		{	
			//For all the designersLists in seasonList
			for(int k = 0; k < sL.size(); k++)
			{
				//If there are any designers in the current designersList
				if (dPS.get(k) > 0)
				{
					//for all the designers in that designersList
					for (int l = 0; l < dPS.get(k); l++)
					{
						//if they are a professional
						if (sL.get(k).get(l).GetStatus() == 'p')
						{
							//The maximum yearly salary for a typical designer
							final double mYE = 412000;
							//The maximum age of a typical designer
							int maxAge = 75;
							//The minimum age of a designer
							int minAge = 16;
							//The total potential future earnings
							double futureEarnings = (maxAge - sL.get(k).get(l).GetAge()) * mYE;
							//The total potential past earnings
							double pastEarnings = (sL.get(k).get(l).GetAge() - minAge) * sL.get(k).get(l).GetSalary();
							//The total potential career earnings
							double pCE = futureEarnings + pastEarnings;
							DecimalFormat salaryFormat = new DecimalFormat("$.00");
							//if the calculated potential carrer earnings are greater than 10,000,000, potential career earnings
							//is assigned the value of 10,000,000
							if (pCE > 10000000)
								pCE = 10000000;
			
							System.out.printf("%-15s %-15s %17s %20s %n", sL.get(k).get(l).GetLastName(),
									sL.get(k).get(l).GetFirstName(),salaryFormat.format(sL.get(k).get(l).GetSalary()),
									salaryFormat.format(pCE));
						}
					}
				}
			}
			return true;
		}
		//If the program has gotten here, the user input must not match a status or all;
		System.out.println("No status found");
		return false;
	}
	
//--Sort List Method
	public static boolean sortList(ArrayList<String> dN, ArrayList<Integer> dPS,
			ArrayList<ArrayList<Designer>> sL)
	{
		//The total amount of designers is used to decide how many times the sorting and 
		//printing loops need to be iterated.
		int totalDesigners = 0;
		
		//for every season
		for (int h = 0; h < dPS.size(); h++)
		{
			//increment totalDesigners by the number of designers in that season
			totalDesigners += dPS.get(h);
		}
		
		//flag that will signal once all designers names have been rearranged in alphabetical order
		boolean sortFlag = true;
		
		//Declaring a String variable temp that will be used for rearranging elements in the designerNames array.
		String temp;
		
		//while there is sorting that needs be done
		while(sortFlag)
		{
			//If no sorting is done throughout all iterations of the for loop, there is no more
			//sorting to be done, and the loop can exit.
			sortFlag = false;
		
			//For all of the designer names, the name at element i will be compared to the element in 
			//the array. 
			for (int i = 0; i < totalDesigners -1; i++)
			{
				//If the designer name at element i of the designerNames array has a greater 
				//lexicographical value than the next name in the designerNames array, it 
				//goes after that name alphabetically. Otherwise the name at i should come
				//before that name at i + 1, and it does not need to be moved in the array.
				if (dN.get(i).compareToIgnoreCase(dN.get(i+1)) > 0)
				{
					//At this point the designer name at i needs go after the designer name 
					//at i +1 
					
					//temp is assigned the name at the current element
					temp = dN.get(i);
					
					//the designer name at i+1 is moved up to the element at i 
					dN.set(i, dN.get(i+1));
					
					//the designer name that was at element i is moved down to i+1 
					dN.set(i+1, temp);
					
					//there is still sorting to be done so the sortFlag needs to true
					sortFlag = true;
				}
				/*If the name at i was moved down to i + 1, it will be compared to the next name
				in the array. This will be repeated until the name has been moved so that it is
				alphabetically before the name directly after it, or until it is moved to the last
				sport in the array of the array. Since some sorting was done, the while loop will 
				be iterated again, and the for loop will be run from the beginning with the newly
			 	arranged namesArray. This will repeat until every name has been rearranged so that
			 	it comes alphabetically before the name directly after it in the array*/
			}	
		}
		//This for loop simply prints the designer names in their new order
		for (int j = 0; j < totalDesigners; j++)
		{
			System.out.println(dN.get(j));
		}
		return true;
	}
//--Final Stats Method
	public static void finalStats(ArrayList<Integer> fSL)throws IOException
	{
		//The String that the user enters will be used to assign a special name to the output file
		System.out.print("Enter todays date and time in the format mm.dd.yy_00.00 ");
		Scanner scan = new Scanner(System.in);
		String userDate = scan.nextLine();
		
		//All of the information collected in finalStats is printed into formatted columns
		System.out.printf("%-8s %3d %n%-8s %3d %n%-8s %3d %n%-8s %3d %n%-8s %3d %n"
				+ "%-8s %3d %n%-8s %3d %n%-8s %3d %n", "L's:", fSL.get(0), 
				 "D's:", fSL.get(1), "S's:", fSL.get(2),
				"M's:", fSL.get(3), "T's:", fSL.get(4), 
				"O's:", fSL.get(5), "Q's:", fSL.get(6), 
				"others:", fSL.get(7));
		
		
		String fileName = ("FinalStats_"+ userDate + ".txt");
			
		FileOutputStream fos = new FileOutputStream(fileName, false);
		PrintWriter pw = new PrintWriter(fos);
			pw.printf("%-8s %3d %n", "L's:", fSL.get(0));
			pw.printf("%-8s %3d %n", "D's:", fSL.get(1));
			pw.printf("%-8s %3d %n", "S's:", fSL.get(2));
			pw.printf("%-8s %3d %n", "M's:", fSL.get(3));
			pw.printf("%-8s %3d %n", "T's:", fSL.get(4));
			pw.printf("%-8s %3d %n", "O's:", fSL.get(5));
			pw.printf("%-8s %3d %n", "Q's:", fSL.get(6));
			pw.printf("%-8s %3d %n", "others:", fSL.get(7));
			pw.close();
		
		System.out.println("Final stats written to file \"FinalStats_" + userDate + "\".txt");
	}
}
