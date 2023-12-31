package list;

/**
 * 랜덤으로 플레이어가 돌린 주사위 수를 저장하는 클래스입니다.
 * 
 * @author MsEmily1020
 * @version 1.0
 */
public class DiceNumberList {
	private static int diceNum1;
	private static int diceNum2;

	/**
	 * 첫번째 주사위 수를 반환하는 메소드입니다.
	 * 
	 * @return int
	 */
	public static int getDiceNum1() {
		return diceNum1;
	}

	/**
	 * 두번째 주사위 수를 반환하는 메소드입니다.
	 * 
	 * @return int
	 */
	public static int getDiceNum2() {
		return diceNum2;
	}

	/**
	 * 첫번째 주사위 수를 저장하는 메소드입니다.
	 * 
	 * @param diceNum1, int - 첫번째 주사위 수
	 */
	public static void setDiceNum1(int diecNum1) {
		DiceNumberList.diceNum1 = diecNum1;
	}

	/**
	 * 두번째 주사위 수를 저장하는 메소드입니다.
	 * 
	 * @param diceNum2, int - 두번째 주사위 수
	 */
	public static void setDiceNum2(int diceNum2) {
		DiceNumberList.diceNum2 = diceNum2;
	}
}
