import java.util.Scanner; 
import java.util.Random; 
import java.util.InputMismatchException; 
import java.io.FileNotFoundException; 
import java.io.IOException; 
import java.io.File; 
import java.io.FileWriter; 
import java.io.FileOutputStream; 
import java.io.FileReader; 
import java.io.BufferedReader; 
import java.nio.file.Paths; 
import java.nio.file.Files; 
import java.lang.StringBuffer; 

/*
8-bit ansi colour code
\033[38;5;<n>m

Console Clearing
System.out.print("\033[H\033[2J");
System.out.flush();
*/

public class ICS3U_Unit7 {

  public static void mainMenu() {
    Scanner input = new Scanner(System.in);
    try{
      System.out.print("\033[38;5;4mWelcome to the Main Menu, please select an option:\n1. Login\n2. Create Account\n3. Exit\n--->\033[38;5;7m");
      int userChoice = input.nextInt();

      System.out.print("\033[H\033[2J");  
      System.out.flush();
      switch (userChoice) {
        case 1:  
          login();
          break;
        case 2:
          createAccount();
          break;
        case 3: 
          System.out.println("Goodbye");
          input.close();
          System.exit(0); 
        default:
          System.out.println("\033[38;5;1mInvalid input, please try again.\n");
          mainMenu();
          break;
      }
    } catch (InputMismatchException ime) {
      System.out.print("\033[H\033[2J");
      System.out.flush();
      
      mainMenu();
    }
    input.close(); 
  }

  public static int attempts = 0; 
  public static String usedUser=""; 
  public static int passLine; 
  public static String password; 
  public static String ZWNJ = "‌"; //zero width non joiner

  public static int reader(String whatYouWantToFind){
    int whatLineAmIOn = 1;
    try{
      File file = new File("accounts.txt");
      Scanner fileInput = new Scanner(file);
      Boolean userFound = false;

      while (fileInput.hasNextLine()){ 
        String line = fileInput.nextLine();
        if (line.equals(whatYouWantToFind)){ 
          userFound = true; 
          passLine = whatLineAmIOn++; 
          try{
            password = Files.readAllLines(Paths.get("accounts.txt")).get(passLine); 
          } catch (IOException e){
            System.out.println("\033[38;5;1mError: IOException");
          }
          break;
        }
        whatLineAmIOn++; 
      }
      if (userFound == false){ 
        System.out.print("\033[H\033[2J"); 
        System.out.flush();
        System.out.println("\033[38;5;1mUsername not found, try again");
        login();
      }
      fileInput.close();
    } catch (FileNotFoundException e){
      System.out.println("\033[38;5;1mError: FileNotFoundException");
    }
    return whatLineAmIOn; 
  }

  public static void login() {
    Scanner input = new Scanner(System.in);
    try{
      if (attempts > 2){ 
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("Too many attempts, please try again.\n");
        attempts = 0; 
        mainMenu();
      }
      System.out.print("\033[38;5;4mEnter Username: \033[38;5;7m");
      String userInput = input.nextLine();
      if (!userInput.equals(usedUser)){ 
        usedUser = userInput; 
        attempts = 0; 
      }
      passLine = reader(userInput);
      System.out.print("\033[38;5;4mEnter Password: \033[38;5;7m");
      String passInput = input.nextLine();
      System.out.print("\033[H\033[2J");  
      System.out.flush();

      try{
        if (passInput.equals(password)){
          int totalAttempts = attempts+1;
          if (totalAttempts == 1){
            System.out.println("\033[38;5;4mLogin Successful. It took you "+totalAttempts+" attempt\n"); 
            loginMenu();
          } else {
            System.out.println("\033[38;5;4mLogin Successful. It took you "+totalAttempts+" attempts\n"); 
            loginMenu();
          }
        } else if (!passInput.equals(password)) {
          System.out.println("\033[38;5;1mInvalid Password\n");
          attempts++;
          login();
        }
      } catch (Exception e){
        System.out.print("\033[H\033[2J");  
        System.out.flush();
        System.out.println("\033[38;5;1mError: Exception\n");
        e.printStackTrace();
      }
      input.close();
    } catch (Exception e){
      System.out.println("\033[38;5;1mError: Exception\n");
      login();
    }
  }

  public static void addaccounts(String newLine) {
    try{
      FileWriter myWriter = new FileWriter("accounts.txt", true); 
      myWriter.write(newLine+"\n");
      myWriter.close();
    } catch (IOException e){
      System.out.print("\033[H\033[2J");  
      System.out.flush();
      System.out.println("\033[38;5;1mError: IOException");
      e.printStackTrace();
    }
  }

  public static void createAccount(){
    Scanner input = new Scanner(System.in);
    System.out.print("\033[38;5;4mEnter a new Username: \033[38;5;7m");
    String newUser = input.nextLine();
    try{
      File file = new File("accounts.txt");
      Scanner fileInput = new Scanner(file);
      while (fileInput.hasNextLine()){
        String line = fileInput.nextLine();
        if (line.equals(newUser)){
          System.out.println("This username already exists, try again");
          createAccount();
        }
      }
      //System.out.println("Unique Username");
      addaccounts(ZWNJ+"\n"+newUser); 
      //System.out.println("Username Added");
      fileInput.close();
    } catch (Exception e){
      System.out.println("Error: Exception");
    }
    System.out.print("\033[38;5;4mEnter a new Password: \033[38;5;7m");
    addaccounts(input.nextLine());
    System.out.print("\033[H\033[2J");  
    System.out.flush(); 
    System.out.println("\033[38;5;4mAccount Created\n");
    mainMenu();
    input.close();
  }

  public static void loginMenu() {
    Scanner input = new Scanner(System.in);
    System.out.print("\033[38;5;4mWelcome to the Logged In Menu, please select an option:\n1. Log out\n2. Change Password\n3. Quadratic Equation Solver\n4. Change Finder\n5. Math\n6. Collatz Conjecture\n7. Exit\n--->\033[38;5;7m");
    int userChoice = input.nextInt();
    switch (userChoice) {
      case 1:
        System.out.print("\033[H\033[2J");  
        System.out.flush(); 
        mainMenu();
        break;
      case 2:
        System.out.print("\033[H\033[2J");  
        System.out.flush(); 
        changePassword();
        break;
      case 3:
        System.out.print("\033[H\033[2J");  
        System.out.flush(); 
        quadraticFomulaSolver();
        break;
      case 4:
        System.out.print("\033[H\033[2J");  
        System.out.flush(); 
        changeFinder();
        break;
      case 5:
        System.out.print("\033[H\033[2J");  
        System.out.flush(); 
        math();
        break;
      case 6:
        System.out.print("\033[H\033[2J");  
        System.out.flush(); 
        collatzConjecture();
        break;
      case 7:
        System.out.print("\033[H\033[2J");  
        System.out.flush(); 
        System.out.println("Goodbye");
        System.exit(0);
      default:
        System.out.println("Invalid input, please try again.");
        loginMenu();
        break;
    }
    input.close();
  }

  public static void changePassword(){
    Scanner input = new Scanner(System.in);
    System.out.println("\033[38;5;4mYou've entered the password changer as "+usedUser);
    try {
        BufferedReader file = new BufferedReader(new FileReader("accounts.txt"));
        StringBuffer inputBuffer = new StringBuffer();
        String line;
        System.out.print("Enter new password: \033[38;5;7m");
        String newPass = input.nextLine();

        while ((line = file.readLine()) != null) {
            inputBuffer.append(line);
            inputBuffer.append('\n');
        }
        file.close();
        String inputStr = inputBuffer.toString();

        inputStr = inputStr.replace(password, newPass); 

        FileOutputStream fileOut = new FileOutputStream("accounts.txt");
        fileOut.write(inputStr.getBytes());
        fileOut.close();
        System.out.print("\033[H\033[2J");  
        System.out.flush();
        System.out.println("\n\033[38;5;4mPassword Succesfully Changed");
        attempts = 0;
        loginMenu();

    } catch (Exception e) {
        System.out.println("\033[38;5;1mError: Exception");
    }
    input.close();
  }

  public static void doYouWantToReturn(){
    Scanner input = new Scanner(System.in);
    try{
      System.out.print("\n\033[38;5;4mDo you want to return to the login menu? (y/n):\033[38;5;7m");
      String x = input.nextLine();
      System.out.print("\033[H\033[2J");
      System.out.flush();
      if (x.equals("y") || x.equals("Y")){
        System.out.print("\033[H\033[2J");
        System.out.flush();
        loginMenu();
      } else {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("\033[38;5;4mGoodbye\033[38;5;7m");
      }
    } catch (Exception e){
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("\033[38;5;1mInvalid input\033[38;5;7m, Goodbye");
    }
    input.close();
  }

  public static void quadraticFomulaSolver(){
    Scanner input = new Scanner(System.in);
    Double a, b, c, d, h, iDontEvenKnowAnymore;
    try{
      System.out.print("\033[38;5;4mEnter a: \033[38;5;7m");
      a = input.nextDouble();
      System.out.print("\033[38;5;4mEnter b: \033[38;5;7m");
      b = input.nextDouble();
      System.out.print("\033[38;5;4mEnter c: \033[38;5;7m");
      c = input.nextDouble();
      d = (Math.pow(b, 2)) - (4.0 * a * c);//discriminant
      h = -(b/(2*a)); //vertex form
      iDontEvenKnowAnymore = ((-c)/b);
      if (d>0 && a!=0){
        double pos = ((-b + Math.sqrt(d)) / (2 * a)*100.0)/100.0;
        double neg = ((-b - Math.sqrt(d)) / (2 * a)*100.0)/100.0;
        System.out.println("\033[38;5;10mYour Roots are "+(String.format("%.2f", pos))+ " and "+(String.format("%.2f", neg)));
      } else if (d==0 && a!=0){
        System.out.println("\033[38;5;10mThe root is " + (String.format("%.2f", h)));
      } else if (a==0){
        System.out.println("\033[38;5;10mThe root is " + iDontEvenKnowAnymore);
      }
      else{
        System.out.println("\033[38;5;10mNo real solutions");
      }
    } catch (Exception e){
      System.out.print("\033[H\033[2J");
      System.out.flush();
      System.out.println("\033[38;5;1mError: Exception\nEnter a valid number\033[38;5;7m");
      quadraticFomulaSolver();
    }
    doYouWantToReturn();
    input.close();
  }

  public static void changeFinder(){
    Scanner input = new Scanner(System.in);
    double totalMoney;
    int a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r;
    try{
      System.out.print("\033[38;5;4mEnter how much money you have(do not use $): \033[38;5;7m");
      totalMoney = input.nextDouble();
      int money = (int) totalMoney;
      int cents = (int) ((totalMoney - money) * 100);
      a = money / 100;
      b = money % 100;
      c = b / 50;
      d = b % 50;
      e = d / 20;
      f = d % 20;
      g = f / 10;
      h = f % 10;
      i = h / 5;
      j = h % 5;
      k = j / 2;
      l = j % 2;
      m = cents / 25;
      n = cents % 25;
      o = n / 10;
      p = n % 10;
      q = p / 5;
      r = p % 5;

      if (a != 0) {System.out.println("\033[38;5;10m"+a + " $100 Bill");}
      if (c != 0) {System.out.println(c + " $50 Bill");}
      if (e != 0) {System.out.println(e + " $20 Bill");}
      if (g != 0) {System.out.println(g + " $10 Bill");}
      if (i != 0) {System.out.println(i + " $5 Bill");}
      if (k != 0) {System.out.println(k + " Toonie");}
      if (l != 0) {System.out.println(l + " Loonie");}
      if (m != 0) {System.out.println(m + " Quarter");}
      if (o != 0) {System.out.println(o + " Dime");}
      if (r >= 3){q++;}
      if (q != 0) {System.out.println(q + " Nickel");}
    } catch (Exception ex){
      System.out.print("\033[H\033[2J");
      System.out.flush();
      System.out.println("\033[38;5;1mError: Exception\nEnter a valid number\033[38;5;7m");
      changeFinder();
    }
    doYouWantToReturn();
    input.close();
  }

  public static void math(){
    Scanner input = new Scanner(System.in);
    Random rand = new Random();

    int e = 1; //problem number
    int f = 0; //totalAttempts
    int g = 0;//problemAttempts

    for (int i = 1; i <=2; i++) {
      int a = rand.nextInt(19) + 1;
      int b = rand.nextInt(19) + 1;
      int c = a + b;
      int d = 0; //user answer

      while (c != d ) {
        System.out.print("\033[38;5;4mProblem " + e + ": What is " + a + " + " + b + "?: \033[38;5;7m");
        d = input.nextInt();
        f++;
        g++; 
        if (g == 3){
          System.out.println("\033[38;5;1mIncorrect. \033[38;5;4mThe answer is " + c + ".");
          g = 0;
          break;
        } if (c == d){
          System.out.println("\033[38;5;10mCorrect!\033[38;5;4m");
          g = 0;
          break;
        }
      }
      e++;
      System.out.print("\033[38;5;4mDo you want to continue? (y/n):\n-->\033[38;5;7m");
      String answer = input.next();
      if (answer.equals("y")) {
        i--;
        g = 0;
        continue;
      } else if (answer.equals("n")) {
        e--;
        System.out.println("\033[38;5;4mIt took you " + f + " attempt(s) to solve " + e + " problem(s).");
        break;
      } else{
        System.out.println("\033[38;5;1mInvalid input. Exiting program.");
        break;
      }
    }
    doYouWantToReturn();
    input.close();
  }

  public static void collatzConjecture(){
    Scanner input = new Scanner(System.in);
    System.out.println("\033[38;5;4mWelcome to the Collatz Conjecture Program");
    try{
      System.out.print("Enter a positive integer: \033[38;5;7m");
      int a = input.nextInt();
      while(a != 1 && a > 0){
        int b = a%2;
        if(b == 0){
          a = a/2;
          System.out.println("\033[38;5;10m"+a);
        } else{
          a = (a*3)+1;
          System.out.println("\033[38;5;10m"+a);
        }
      }
    } catch (Exception e){
      System.out.println("\033[38;5;1mError: Exception");
    }
    doYouWantToReturn();
    input.close();
  }

  public static void main(String[] args) {
    mainMenu();
  }
}