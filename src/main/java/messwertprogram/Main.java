package messwertprogram;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Controller controller = new Controller();
		Scanner fetch = new Scanner(System.in);
		
		while (true) {
			System.out.println("Messwertprogram Menu: \n"
					+ "1. Werte einlesen\n"
					+ "2. Werte ausgeben \n"
					+ "3. Median berechnen\n"
					+ "4. Program beenden");
			int input = fetch.nextInt();
			if (input < 1 || input > 4) {
				System.out.println("Ungueltige Eingabe! Bitte waehlen Sie eine gueltige Option");
			} else if (input == 1) {
				controller.readTemps();
			} else if (input == 2) {
				//print temp
				controller.printTemps();
			} else if (input == 3) {
				//calculate median
				controller.calculateMedian();
			} else if (input == 4) {
				fetch.close();
				System.exit(0);
			}
		}
		
		

	}

}
