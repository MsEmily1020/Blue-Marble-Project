package main;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import common.CommonFrame;
import tooltiptext.CountryButtonToolTipText;

/**
 * 메인 게임 보드를 그리는 클래스입니다.
 * 
 * @version 1.0
 * @author MsEmily1020
 *
 */
public class MainBoardFrame extends CommonFrame {
	private JPanel countryDetailPanel = new CountryDetailPanel();

	// BorderLayout으로 각각의 빨강, 주황, 초록, 파랑 라인의 나라 panel을 생성하였습니다.
	public MainBoardFrame() {
		super("부루마불", 900, 900);
		
		setLayout(new BorderLayout());
		
		add(countryDetailPanel);
		add(new RedLineButtonPanel(), "South");
		add(new OrangeLineButtonPanel(), "West");
		add(new GreenLineButtonPanel(), "North");
		add(new BlueLineButtonPanel(), "East");

		new CountryButtonToolTipText();
 	}
	

	public static void main(String[] args) {
		new MainBoardFrame().setVisible(true);
	}

}