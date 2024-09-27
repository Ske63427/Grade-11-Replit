import java.util.Scanner;
public class fourdotcfour {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        double totalMoney;
        System.out.print("Enter how much money you have(do not use $): ");
        totalMoney = input.nextDouble();
        input.close();
        int money = (int) totalMoney;
        int cents = (int) ((totalMoney - money) * 100);
        int a = money / 100;
        int b = money % 100;
        int c = b / 50;
        int d = b % 50;
        int e = d / 20;
        int f = d % 20;
        int g = f / 10;
        int h = f % 10;
        int i = h / 5;
        int j = h % 5;
        int k = j / 2;
        int l = j % 2;
        int m = cents / 25;
        int n = cents % 25;
        int o = n / 10;
        int p = n % 10;
        int q = p / 5;
        int r = p % 5;
      
        if (a != 0) {
            System.out.println(a + " $100 Bill");
        }
        if (c != 0) {
            System.out.println(c + " $50 Bill");
        }
        if (e != 0) {
            System.out.println(e + " $20 Bill");
        }
        if (g != 0) {
            System.out.println(g + " $10 Bill");
        }
        if (i != 0) {
            System.out.println(i + " $5 Bill");
        }
        if (k != 0) {
            System.out.println(k + " Toonie");
        }
        if (l != 0) {
            System.out.println(l + " Loonie");
        }
        if (m != 0) {
            System.out.println(m + " Quarter");
        }
        if (o != 0) {
            System.out.println(o + " Dime");
        }
        if (r >= 3){
          q++;
        }
        if (q != 0) {
          System.out.println(q + " Nickel");
        }
        // if (r != 0 && r > 1) {
        //     System.out.println(r + " Pennies");
        // } else if (r == 1) {
        //     System.out.println("A Solitary Penny");
        // }
    }
}