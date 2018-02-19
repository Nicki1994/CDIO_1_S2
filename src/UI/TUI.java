package UI;

import java.io.Console;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TUI implements IUI {

	@Override
	public void clearDisplay() {
		System.out.flush();

	}

	@Override
	public void showMessage(String msg) {
		clearDisplay();
		System.out.println(msg);

	}

	@Override
	public int getInteger(String msg) {
		clearDisplay();
		while(true){
			try(Scanner sc = new Scanner(System.in)){
				System.out.println(msg);
				return sc.nextInt();
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
		clearDisplay();
		while(true){
			try(Scanner sc = new Scanner(System.in)){
				System.out.println(msg);
				return sc.nextLine();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	}

	@Override
	public char[] getPassword(String msg) {
		clearDisplay();
		while(true){
			try{
				Console console = System.console();
				System.out.println(msg);
				return console.readPassword();
			}
			catch(Exception e){
				e.printStackTrace();
			}
			
		}
	}

}
