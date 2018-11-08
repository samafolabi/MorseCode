import java.util.Scanner;
import java.util.StringTokenizer;

import javax.sound.sampled.LineUnavailableException;

public class MorseCodeTranslator {

	public static final int WPM = 8; //words per minute
	public static final int DD = 1200/WPM; //dot duration in ms

	public static final int DOT = 1;
	public static final int DASH = 3;
	public static final int SPACE = 1;
	public static final int LETTER = 3;
	public static final int WORD = 7;
	
	private void delay(int length) throws InterruptedException {
		Thread.sleep(DD*length);
	}
	
	private void dot() throws InterruptedException, LineUnavailableException {
		TestBeep.sound((DOT*DD)/1000);
	}
	
	private void dash() throws InterruptedException, LineUnavailableException {
		TestBeep.sound((DASH*DD)/1000);
	}
	
	private void space() throws InterruptedException, LineUnavailableException {
		delay(SPACE*DD);
	}
	
	private void letter() throws InterruptedException, LineUnavailableException {
		delay(LETTER*DD);
	}
	
	private void word() throws InterruptedException, LineUnavailableException {
		delay(WORD*DD);
	}
	
	private char characters[] = {'S', 'O'};
	private String morse[] = {"TSTST", "HTHTH"};
	
	public String translator(String word) {
		String program = "";
		for (int i = 0; i < word.length(); i++) {
			for (int j = 0; j < characters.length; j++) {
				if (word.charAt(i) == characters[j]) {
					program += morse[j];
					break;
				}
			}
			if (i+1!=word.length()) {
				program += "L";
			}
		}
		program += "W";
		return program;
	}
	
	public void execute(String word) throws InterruptedException, LineUnavailableException {
		for (int i = 0; i < word.length(); i++) {
			switch (word.charAt(i)) {
				case 'T':
					dot();
					break;
				case 'H':
					dash();
					break;
				case 'S':
					space();
					break;
				case 'L':
					letter();
					break;
				case 'W':
					word();
					break;
				default:
					System.out.println("Error");
			}
		}
	}

	public static void main(String[] args) {
		try {
			MorseCodeTranslator translator = new MorseCodeTranslator();
			Scanner scanner = new Scanner(System.in);
			String input = "";
			while (true) {
				input = scanner.nextLine();
				String program = "";
				StringTokenizer izer = new StringTokenizer(input, " ");
				while (izer.hasMoreElements()) {
					program += translator.translator(izer.nextToken());
				}
				translator.execute(program);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//enter string
		//split string
		//translate into one huge string
		//execute
		//loop back
	}

}
