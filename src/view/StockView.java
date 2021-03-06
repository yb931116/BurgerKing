package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;

import main.BurgerKing;

public class StockView extends JPanel implements ActionListener {

	JTextField tf_StockNo, tf_StockName, tf_Quantity, tf_ValidPeriod, tf_EnteringDate;
	JButton btn_Import, btn_Export, btn_Home;

	JComboBox com_StockSearch;
	JTextField tf_StockSearch;

	JTable tableStock;

	StockTableModel tb_ModelStock;

	public StockView() {
		addLayout(); // 화면설계
		connectDB(); // db 연결
		eventProc();
	}

	public void addLayout() {
		tf_StockNo = new JTextField();
		tf_StockName = new JTextField();
		tf_ValidPeriod = new JTextField();
		tf_EnteringDate = new JTextField();
		tf_Quantity = new JTextField();

		btn_Import = new JButton("입고");
		btn_Export = new JButton("출고");
		btn_Home = new JButton("홈으로");

		String[] cbStockSearch = { "품명", "제품코드" };
		com_StockSearch = new JComboBox(cbStockSearch);
		tf_StockSearch = new JTextField(15);

		tb_ModelStock = new StockTableModel();

		// *********화면 구성*******************************
		// 맨위쪽
		JPanel p_north = new JPanel();
		p_north.setLayout(new GridLayout(1, 2));

		// 맨위>왼쪽
		JPanel p_north_west = new JPanel();
		p_north_west.setLayout(new GridLayout(3, 4));
		p_north_west.add(new JLabel("제품코드"));
		p_north_west.add(tf_StockNo);
		p_north_west.add(new JLabel("제품명"));
		p_north_west.add(tf_StockName);
		p_north_west.add(new JLabel("유통기한"));
		p_north_west.add(tf_ValidPeriod);
		p_north_west.add(new JLabel("입고일"));
		p_north_west.add(tf_EnteringDate);
		p_north_west.add(new JLabel("수량"));
		p_north_west.add(tf_Quantity);
		p_north_west.setBorder(new TitledBorder("재고 관리"));

		// 맨위>오른쪽
		JPanel p_north_east = new JPanel();
		p_north_east.setLayout(new GridLayout(2, 1));
		// 맨위>오른쪽>위
		JPanel p_north_east_1 = new JPanel();
		p_north_east_1.setLayout(new FlowLayout());
		p_north_east_1.add(com_StockSearch);
		p_north_east_1.add(tf_StockSearch);
		// 맨위>오른쪽>아래
		JPanel p_north_east_2 = new JPanel();
		p_north_east_2.setLayout(new GridLayout(1, 3));
		p_north_east_2.add(btn_Import);
		p_north_east_2.add(btn_Export);
		p_north_east_2.add(btn_Home);
		// 맨위>오른쪽>위아래 합체
		p_north_east.add(p_north_east_1);
		p_north_east.add(p_north_east_2);
		// 위쪽 합체
		p_north.add(p_north_west);
		p_north.add(p_north_east);

		// 아래쪽 (테이블)
		JPanel p_south = new JPanel();
		p_south.setLayout(new BorderLayout());
		p_south.add(new JScrollPane(tableStock));

		// 전체 화면에 위아래 붙이기
		setLayout(new BorderLayout());
		add(p_north, BorderLayout.NORTH);
		add(p_south, BorderLayout.CENTER);

	}

	void connectDB() {

	}

	void eventProc() {
		btn_Import.addActionListener(this);
		btn_Export.addActionListener(this);
		btn_Home.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object evt = e.getSource();
		if (evt == btn_Home) {
			BurgerKing.card.first(BurgerKing.cardPanel);
		}
	}
}

// 화면에 테이블 붙이는 메소드
class StockTableModel extends AbstractTableModel {

	ArrayList data = new ArrayList();
	String[] columnNames = { "제품코드", "제품명", "유통기한", "입고일", "수량" };

	// =============================================================
	// 1. 기본적인 TabelModel 만들기
	// 아래 세 함수는 TabelModel 인터페이스의 추상함수인데
	// AbstractTabelModel에서 구현되지 않았기에...
	// 반드시 사용자 구현 필수!!!!

	public int getColumnCount() {
		return columnNames.length;
	}

	public int getRowCount() {
		return data.size();
	}

	public Object getValueAt(int row, int col) {
		ArrayList temp = (ArrayList) data.get(row);
		return temp.get(col);
	}

	public String getColumnName(int col) {
		return columnNames[col];
	}
}
