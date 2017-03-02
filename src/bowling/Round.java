package bowling;

public class Round {
	private int firstToss;
	private int secondToss;
	private int totalPoints;
	private int AcumulatedPoints;
	private boolean strike;
	private boolean spare;
	
	public int getFirstToss() {
		return firstToss;
	}
	public void setFirstToss(int firstToss) {
		this.firstToss = firstToss;
	}
	public int getSecondToss() {
		return secondToss;
	}
	public void setSecondToss(int secondToss) {
		this.secondToss = secondToss;
	}
	public int getTotalPoints() {
		return totalPoints;
	}
	public void setTotalPoints(int totalPoints) {
		this.totalPoints = totalPoints;
	}
	public int getAcumulatedPoints() {
		return AcumulatedPoints;
	}
	public void setAcumulatedPoints(int acumulatedPoints) {
		AcumulatedPoints = acumulatedPoints;
	}
	public boolean isStrike() {
		return strike;
	}
	public void setStrike(boolean strike) {
		this.strike = strike;
	}
	public boolean isSpare() {
		return spare;
	}
	public void setSpare(boolean spare) {
		this.spare = spare;
	}
	
}
