package player;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.border.LineBorder;

import country.CountryDetailPanel;
import dice.DicePanel;
import list.CountryButtonList;
import list.DiceNumberList;
import list.PlayerList;

/**
 * 각각의 플레이어 패널입니다.
 * 
 * @author MsEmily1020
 * @version 1.0
 */
public class PlayerPanel extends JPanel {
	private static JLabel[] label = new JLabel[4];
	public static int playerOrder;
	private static int totalMovePosition;

	public PlayerPanel() {
		setLayout(null);
		setSize(30, 30);
		setOpaque(false);

		createPlayer();
		PlayerView.playerIconLabel[0].setBorder(new LineBorder(Color.black, 20)); // 첫번째 순서, 첫번째 턴 플레이어는 무조건 setBorder 시키기
	}

	/**
	 * 플레이어를 초기화 하는 메소드입니다.
	 */
	public static void createPlayer() {
		int length = label.length;
		for (int i = 0; i < length; i++) {
			Image img = new ImageIcon("./images/플레이어_" + (i + 1) + ".png").getImage().getScaledInstance(110, 120,
					java.awt.Image.SCALE_SMOOTH);
			label[i] = new JLabel(new ImageIcon(img));
			PlayerList.insertPlayer(label[i]);
			CountryButtonList.getCountryButton(0).add(PlayerList.getPlayer(i));
		}
	}

	/**
	 * 플레이어를 주사위 수만큼 이동시키는 메소드입니다. index는 0부터 시작합니다.
	 */
	public static void movePlayer() {

		int position = PlayerList.getPlayerPosition(playerOrder); // 현재 위치

		int diceNum = DiceNumberList.getDiceNum1() + DiceNumberList.getDiceNum2(); // 주사위 수

		totalMovePosition = position + diceNum; // 최종으로 가야 하는 위치

		// 한바퀴(총 24칸) 돌았을 경우 -> 해당 플레이어의 위치는 한바퀴를 뺀 나머지
		totalMovePosition = (totalMovePosition > 23) ? (totalMovePosition - 24) : totalMovePosition;

		Timer playerMoveTimer = new Timer(130, new ActionListener() {
			private int currentPosition = position; // 현재 위치

			@Override
			public void actionPerformed(ActionEvent e) {
				currentPosition++;
				if(currentPosition == 24) currentPosition = 0;
				PlayerList.setPlayerPosition(playerOrder, currentPosition); // 플레이어 위치 업데이트
				
				SwingUtilities.invokeLater(() -> {
					// 이전 위치의 플레이어 이미지 제거
					CountryButtonList.getCountryButton((currentPosition == 0) ? 23 : currentPosition - 1)
							.remove(PlayerList.getPlayer(playerOrder));
					repaintComponent((currentPosition == 0) ? 23 : currentPosition - 1);

					// 현재 위치에 플레이어 이미지 추가
					CountryButtonList.getCountryButton(currentPosition).add(PlayerList.getPlayer(playerOrder));
					repaintComponent(currentPosition);
				});

				if (currentPosition == totalMovePosition) {
					((Timer) e.getSource()).stop(); // 애니메이션 종료
					
					CountryDetailPanel.diceClickBtn.setVisible(true);
				}
			}
		});

		Timer delayTimer = new Timer(1500, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(() -> {
					// 이전 위치의 플레이어 이미지 제거
					CountryButtonList.getCountryButton(position).remove(PlayerList.getPlayer(playerOrder));
					repaintComponent(position);
				});

				playerMoveTimer.start(); // 플레이어 이동 타이머 시작
			}
		});

		delayTimer.setRepeats(false); // 한 번만 실행하도록 설정
		delayTimer.start(); // 지연 타이머 시작
	}

	/**
	 * Component를 새로 그리는 메소드입니다.
	 * 
	 * @param buttonIndex, int - repaint 할 button의 index
	 */
	public static void repaintComponent(int buttonIndex) {
		CountryButtonList.getCountryButton(buttonIndex).revalidate();
		CountryButtonList.getCountryButton(buttonIndex).repaint();
	}
}
