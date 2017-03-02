package bowling;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class Game {
	
	public static final int ROUNDS = 10;
	public static final int MAX_STRIKES_ACUM = 2;
	public static final int PINS_NUMBER = 10;
	
	public static void main(String[] args) {
		Round[] rounds = new Round[ROUNDS];
		fillRounds(rounds);
		
		// FIRST ROUND
		readEnter();
		Round firstRound = rounds[0];
		normalRound(firstRound);
		firstRound.setTotalPoints(firstRound.getFirstToss() + firstRound.getSecondToss());
		firstRound.setAcumulatedPoints(firstRound.getTotalPoints());
		
		// SECOND ROUND UNTIL 9
		for (int i = 1; i < ROUNDS - 1; i++) {
			readEnter();
			Round round = rounds[i];
			normalRound(round);
			round.setTotalPoints(round.getFirstToss() + round.getSecondToss());
			calculateAccumulatedPoints(rounds, i);
		}
		
		// FINAL ROUND
		readEnter();
		Round round = rounds[ROUNDS - 1];
		FinalRound(round);
		int total = round.getFirstToss() + round.getSecondToss() + round.getThirdToss();
		round.setTotalPoints(total);
		calculateAccumulatedPoints(rounds, ROUNDS - 1);
		printPoints(rounds);

	}

	private static void printPoints(Round[] rounds) {
		for (int i = 0; i < rounds.length; i++) {
			System.out.println("first "+ rounds[i].getFirstToss() + " second "+ rounds[i].getSecondToss());
			System.out.println("points " + rounds[i].getTotalPoints() + " acumulated " + rounds[i].getAcumulatedPoints());
		}
	}
	
	public static void calculateAccumulatedPoints(Round[] rounds, int i){
		Round currentRound = rounds[i];
		Round previousRound = rounds[i - 1];
		int strikes = 0;
		
		for (int j = i; j > i - MAX_STRIKES_ACUM; j--) {
			Round previous = rounds[j];
			if(j < 0 || previous.isStrike() == false){
				break;
			}
			if(previous.isStrike()){
				strikes++;
			}
		}
		
		if(strikes == MAX_STRIKES_ACUM){
			for (int j = i - MAX_STRIKES_ACUM; j < i; j++) {
				int acumulated = rounds[j].getAcumulatedPoints();
				rounds[j].setAcumulatedPoints(acumulated + currentRound.getFirstToss() + currentRound.getSecondToss());
			}
		} else {
			int accumulatedPoints = previousRound.getAcumulatedPoints();
			if(previousRound.isStrike()){
				previousRound.setAcumulatedPoints(accumulatedPoints + currentRound.getFirstToss() + currentRound.getSecondToss());
			} else if(previousRound.isSpare()){
				previousRound.setAcumulatedPoints(accumulatedPoints + currentRound.getFirstToss());
			}
			
		}
		
		int previousPoints = previousRound.getAcumulatedPoints();
		currentRound.setAcumulatedPoints(previousPoints + currentRound.getTotalPoints());

				
	}
	
	private static void fillRounds(Round[] rounds) {
		for (int i = 0; i < rounds.length; i++) {
			rounds[i] = new Round();
		}
		
	}

	public static int toss(int pins){
		Random random = new Random();
		int toss = random.nextInt(pins + 1);
		pins -= toss;
		return pins;
	}
	
	public static void normalRound(Round round){
		int pins = PINS_NUMBER;
		int firstToss = toss(pins);
		
		pins -= firstToss;
		round.setFirstToss(firstToss);
		if(pins == 0){
			round.setStrike(true);
		} else {
			int secondToss = toss(pins);
			pins -= secondToss;
			round.setSecondToss(secondToss);
			if(pins == 0){
				round.setSpare(true);
			}
		}
		
	}
	
	
	
	public static void FinalRound(Round round){
		int pins = PINS_NUMBER;
		int firstToss = toss(pins);
		
		pins -= firstToss;
		round.setFirstToss(firstToss);
		
		if(pins == 0){
			round.setStrike(true);
			pins = PINS_NUMBER;
		}
		
		int secondToss = toss(pins);
		pins -= secondToss;
		round.setSecondToss(secondToss);
		if(pins == 0){
			pins = PINS_NUMBER;
			round.setSpare(true);
		}
		int thirdToss = 0;
		
		if(round.isStrike() || round.isSpare()){
			
			thirdToss = toss(pins);
			System.out.println("entro al toss tres " + thirdToss);
			round.setThirdToss(thirdToss);
		}
	}
	
	public static void readEnter(){
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			System.out.println("press Enter to continue");
			br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
