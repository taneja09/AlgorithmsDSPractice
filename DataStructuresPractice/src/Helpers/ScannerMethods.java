package Helpers;

import java.util.*;
import java.util.Scanner;

public class ScannerMethods {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		/************* String and Character Inputs ************/
		//String Input
		String name = sc.nextLine();
		
		// Character input
		char gender = sc.next().charAt(0);
		
		/*********** Numerical Data Input *****************/
		// Integer data input
		int age = sc.nextInt();
		
		// Long data input
		long mobileNo = sc.nextLong();
		
		// Double data input
		double Amount = sc.nextDouble();
		
		
		// Byte data input
		byte packetSize = sc.nextByte();
		
		// Short data input
		short shortSize = sc.nextShort();
		
		/************* Length is given ************/
		
		int length = sc.nextInt();
		
		List<String> list = new ArrayList();
		for(int i = 0; i< length; i++){
			/*  Read you the output and create the according data structure */
			 list.add(sc.nextLine());
		}
	}
}
