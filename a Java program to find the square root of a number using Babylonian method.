import java.util.*;
public class solution {	
  public static float square_Root(float num) 
    { 
        float a = num; 
        float b = 1; 
        double e = 0.000001; 
        while (a - b > e) { 
            a = (a + b) / 2; 
            b = num / a; 
        } 
        return a; 
    } 
 
   public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Input an integer: ");
        int num = scan.nextInt();
        scan.close(); 
		System.out.println("Square root of a number using Babylonian method: "+square_Root(num));		
		}
 }
