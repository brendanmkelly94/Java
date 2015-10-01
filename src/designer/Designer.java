package designer;

public class Designer {
	//variables of the Designer objects
	private String lastName;
	private String firstName;
	private int age;
	private double salary;
	private char status;
	private String favShow;
	private int seasonNumber;
	
	
	//Default constructor
	public Designer()
	{
	lastName = "Shmoe";
	firstName = "Joe";
	seasonNumber = 1;
	age = 18;
	salary = 20000;
	status = 'a';
	favShow = "Workaholics";
	}
	//Allows file to instantiate Designer objects with appropriate values
	public Designer(String lN, String fN,int s, int ageD, double sal, char statusD, String favShowD )
	{
		lastName = lN;
		firstName = fN;
		seasonNumber = s;
		age = ageD;
		salary = sal;
		status = statusD; 
		favShow = favShowD;
	}
//--Accessor and Mutator Methods
	public String GetLastName()
	{
		return lastName;
	}

	public void SetLastName(String lN)
	{
		lastName = lN;
	}

	public String GetFirstName()
	{
		return firstName;
	}
	
	public void SetFirstName(String fN)
	{
		firstName = fN;
	}

	public int GetSeasonNumber()
	{
		return seasonNumber;
	}
	
	public void SetSeasonNumber(int s)
	{
		seasonNumber = s;
	}
	
	public int GetAge()
	{
		return age;
	}
	
	public void SetAge(int ageD)
	{
		if (ageD >= 16)
		age = ageD;
		else
			System.out.println("The designer must be at least 16!");
	}
	
	public double GetSalary()
	{
		return salary;
	}
	
	public void SetSalary(double sal)
	{
		if(salary >= 100)
			salary = sal;
		else
			System.out.println("That salary is too low, it must be at leat 100.");
	}
	
	public char GetStatus()
	{
		return status;
	}
	
	public void SetStatus(char statusD)
	{
		switch (statusD)
		{
		case 'a':
		case 'p':
			status = statusD;
			break;
		default:
			System.out.println("Invalid entry status can be assigned a or p");
		}
	}
	
	public String GetFavShow()
	{
		return favShow;
	}
	
	public void SetFavShow(String favShowD)
	{
		favShow = favShowD;
	}

//--Display Method
	public void Display()
	{
		System.out.println(lastName +"\t" + firstName +"\t" + age +"\t" + salary
				+"\t" + status +"\t" + favShow +"\t" + seasonNumber);
	}
//--To String Method
	public String toString()
	{
		String designerString = (lastName +" " + firstName +" " + age +" " + salary
				+" " + status +" " + favShow +" " + seasonNumber);
		return designerString;
	}
//--Equals Method
	public boolean equals(Object des)
	{
		if (!(des instanceof Designer ))
		return false;
		else
		{
			Designer desD = (Designer)des;
			if (lastName.equals(desD.GetLastName()) && firstName.equals(desD.GetFirstName())
					&& age == desD.GetAge() && salary == desD.GetSalary() && 
					status == desD.GetStatus() && favShow.equals(desD.GetFavShow()) &&
					seasonNumber == desD.GetSeasonNumber())
				return true;
			else
				return false;
		}
	}

}
