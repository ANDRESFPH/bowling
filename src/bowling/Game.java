package bowling;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class Game {
	private int rounds;
	private int maxStrikes;
	private int pins;
	
	public Game(int rounds, int maxStrikes, int pins) {
		this.maxStrikes = maxStrikes;
		this.pins = pins;
		this.rounds = rounds;
	}
	
	public void start() {
		Round[] rounds = new Round[this.rounds];
		fillRounds(rounds);
		
		// FIRST ROUND
		firstRound(rounds);
		
		// SECOND ROUND UNTIL 9
		for (int i = 1; i < this.rounds - 1; i++) {
			readInput();
			Round round = rounds[i];
			normalRound(round);
			round.setTotalPoints(round.getFirstToss() + round.getSecondToss());
			calculateAccumulatedPoints(rounds, i);
		}
		
		// FINAL ROUND
		finalRound(rounds);
		
		printPoints(rounds);

	}
	
	public void finalRound(Round[] rounds){
		readInput();
		Round round = rounds[this.rounds - 1];
		FinalRound(round);
		int total = round.getFirstToss() + round.getSecondToss() + round.getThirdToss();
		round.setTotalPoints(total);
		calculateAccumulatedPoints(rounds, this.rounds - 1);
	}
	
	public void firstRound(Round[] rounds){
		readInput();
		Round firstRound = rounds[0];
		normalRound(firstRound);
		firstRound.setTotalPoints(firstRound.getFirstToss() + firstRound.getSecondToss());
		firstRound.setAcumulatedPoints(firstRound.getTotalPoints());
	}

	private void printPoints(Round[] rounds) {
		for (int i = 0; i < rounds.length; i++) {
			System.out.println("first "+ rounds[i].getFirstToss() + " second "+ rounds[i].getSecondToss());
			System.out.println("points " + rounds[i].getTotalPoints() + " acumulated " + rounds[i].getAcumulatedPoints());
		}
	}
	
	public void calculateAccumulatedPoints(Round[] rounds, int i){
		Round currentRound = rounds[i];
		Round previousRound = rounds[i - 1];
		int strikes = 0;
		
		for (int j = i; j > i - this.maxStrikes; j--) {
			Round previous = rounds[j];
			if(j < 0 || previous.isStrike() == false){
				break;
			}
			if(previous.isStrike()){
				strikes++;
			}
		}
		
		if(strikes == this.maxStrikes){
			for (int j = i - this.maxStrikes; j < i; j++) {
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
	
	private void fillRounds(Round[] rounds) {
		for (int i = 0; i < rounds.length; i++) {
			rounds[i] = new Round();
		}
		
	}

	public int toss(int pins){
		Random random = new Random();
		int toss = random.nextInt(pins + 1);
		pins -= toss;
		return pins;
	}
	
	public void normalRound(Round round){
		int pins = this.pins;
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
	
	
	
	public void FinalRound(Round round){
		int pins = this.pins;
		int firstToss = toss(pins);
		
		pins -= firstToss;
		round.setFirstToss(firstToss);
		
		if(pins == 0){
			round.setStrike(true);
			pins = this.pins;
		}
		
		int secondToss = toss(pins);
		pins -= secondToss;
		round.setSecondToss(secondToss);
		if(pins == 0){
			pins = this.pins;
			round.setSpare(true);
		}
		int thirdToss = 0;
		
		if(round.isStrike() || round.isSpare()){
			
			thirdToss = toss(pins);
			System.out.println("entro al toss tres " + thirdToss);
			round.setThirdToss(thirdToss);
		}
	}
	
	public void readInput(){
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			System.out.println("press Enter to continue");
			br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getRounds() {
		return rounds;
	}

	public void setRounds(int rounds) {
		this.rounds = rounds;
	}

	public int getMaxStrikes() {
		return maxStrikes;
	}

	public void setMaxStrikes(int maxStrikes) {
		this.maxStrikes = maxStrikes;
	}

	public int getPins() {
		return pins;
	}

	public void setPins(int pins) {
		this.pins = pins;
	}
	
	

}
