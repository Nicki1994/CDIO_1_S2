package UI;

import java.io.Console;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TUI implements IUI {

	Scanner sc = new Scanner(System.in);
	@Override
	public void clearDisplay() {
		System.out.flush();

	}

	@Override
	public void showMessage(String msg) {
		System.out.println(msg);

	}

	@Override
	public int getInteger(String msg) {
		while(true){
			try{
				System.out.println(msg);
				int i = sc.nextInt();
				sc.nextLine();
				return i;
			}
			catch(InputMismatchException e){
				System.out.println("forkert input");
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}


	}

	@Override
	public String getString(String msg) {
		String temp = "";
		while(true){
			try{
				System.out.println(msg);
				temp = sc.nextLine();
				return temp;
			}
		 	catch(Exception e){
				e.printStackTrace();
			}	
		}
		
	}

	@Override
	public char[] getPassword(String msg) {
		char[] kode = {'a','b','e'};
		while(true){
			try{
				//Console console = System.console();
//				System.out.println(msg);
				System.out.println("koden er automatisk indtastet");
				return kode; //console.readPassword();
			}
			catch(Exception e){
				e.printStackTrace();
			}
			
		}
	}

}
