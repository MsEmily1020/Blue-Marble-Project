package country;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import common.CommonImage;
import dice.DicePanel;

/**
 * 나라의 자세한 정보(통행료, 대지료 등)를 보여주는 패널 클래스입니다.
 * 
 * @author MsEmily1020
 * @version 1.0
 */
public class CountryDetailPanel extends JPanel {
	// 클래스 수준에서 주사위 패널을 선언
	DicePanel dicePanel;
	
	public static JButton diceClickBtn = new JButton("주사위 돌리기");
	
	/**
	 * 기본 패널은 보드 이미지를 보여줍니다.
	 */
	public CountryDetailPanel() {
		setLayout(new BorderLayout());

		// 이미지 패널 생성
		CommonImage img = new CommonImage(new ImageIcon("./images/부루마불.png").getImage());

		// 이미지 패널을 추가
		add(img, BorderLayout.CENTER);

		// 주사위 돌리기 버튼 생성
		diceClickBtn.setBackground(Color.red);
		diceClickBtn.setForeground(Color.white);
		diceClickBtn.setBounds(210, 470, 150, 50);
		img.add(diceClickBtn);

		// 주사위 돌리기 버튼을 클릭했을 경우
		diceClickBtn.addActionListener(e -> {
		    
			// 아직 dicePanel이 생성되지 않았다면
			if (dicePanel == null) {
		        dicePanel = new DicePanel();	// DicePanel을 새로 생성
		        dicePanel.setOpaque(false); // DicePanel을 투명하게 설정
		        dicePanel.setBounds(100, 210, 400, 200);
		        
		        // 주사위 패널을 이미지 패널 위에 추가
		        img.add(dicePanel);
		    
			} else DicePanel.startDiceRoll(); // 생성이 되었다면 주사위 굴리기
		    
		    diceClickBtn.setVisible(false); // 주사위를 굴릴때는 클릭 버튼 안보이게 하기
		});
	}

	/**
	 * 나라의 인덱스를 생성자에 넣어주면 인덱스에 해당하는 나라의 자세한 정보를 보여줍니다.
	 * 
	 * @param index, int - 해당 나라 인덱스
	 */
	public CountryDetailPanel(int index) {

	}
}
