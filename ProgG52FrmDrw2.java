package step8_G5;

/****************************************
 * 課題番号　　　：第13回　演習問題G5-2 *
 * ファイル名　　：ProgG52FrmDrw2.java  *
 * 作成年月日　　：2025年12月23日       *
 * 学生番号・氏名：4523223　猩々勇佑　  *
 ****************************************/


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

class ProgG52FrmDrw2 extends JFrame{

	JCheckBox chx1,chx2,chx3,chx4;
	ButtonGroup grp;
	JRadioButton rd1,rd2,rd3,rd4,chxComp;
	JButton btn;
	int red = 0,green = 0, blue = 0;
	MyPanel myPanel;
	int sz = 100;//追加要素
	JTextField txt;//追加要素
	JTextField txtRbg,txtGbg,txtBbg;//追加

	int shape = 0;

	public ProgG52FrmDrw2(String title) {

		setTitle(title);
		setLayout(null);
		setSize(350,800);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		//上部ラベル
		JLabel mainLabel = new JLabel("図形の色と形");
		mainLabel.setOpaque(true);
		mainLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
		mainLabel.setBackground(Color.WHITE);
		mainLabel.setHorizontalAlignment(JLabel.CENTER);
		mainLabel.setBounds(40, 10, 250, 40);
		add(mainLabel);

		//色の指定
		chx1 = new JCheckBox("赤"); chx1.setBounds(10,80,70,40);
		chx1.setFont(new Font("SansSerif", Font.BOLD, 24)); add(chx1);

		chx2 = new JCheckBox("緑"); chx2.setBounds(90,80,70,40);
		chx2.setFont(new Font("SansSerif", Font.BOLD, 24)); add(chx2);

		chx3 = new JCheckBox("青"); chx3.setBounds(170,80,70,40);
		chx3.setFont(new Font("SansSerif", Font.BOLD, 24)); add(chx3);

		//塗り潰しボタン（追加）
		chx4 = new JCheckBox("塗り潰し"); chx4.setBounds(10,295,150,60);
		chx4.setFont(new Font("SansSerif", Font.BOLD,24));  add(chx4);

		//＝＝＝＝＝追加要素＝＝＝＝＝＝＝＝＝＝

		chxComp = new JRadioButton("補色"); chxComp.setBounds(250,80,90,40);
		chxComp.setFont(new Font("SansSerif", Font.BOLD, 24)); add(chxComp);

		//＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝

		grp = new ButtonGroup();

		//図形の指定
		rd1 = new JRadioButton("〇"); grp.add(rd1);
		rd1.setBounds(10,130,80,40); add(rd1);
		rd1.setFont(new Font("SansSerif", Font.BOLD, 24));

		rd2 = new JRadioButton("△"); grp.add(rd2);
		rd2.setBounds(90,130,80,40); add(rd2);
		rd2.setFont(new Font("SansSerif", Font.BOLD, 24));

		rd3 = new JRadioButton("□"); grp.add(rd3);
		rd3.setBounds(170,130,80,40); add(rd3);
		rd3.setFont(new Font("SansSerif", Font.BOLD, 24));

		rd4 = new JRadioButton("☆"); grp.add(rd4);
		rd4.setBounds(250,130,80,40); add(rd4);
		rd4.setFont(new Font("SansSerif", Font.BOLD, 24));

		//図形サイズの選択（追加）
		txt = new JTextField();
		txt.setFont(new Font("SansSerif", Font.BOLD, 24));
		txt.setBounds(80,190,100,40); add(txt);
		JLabel SizeLabel = new JLabel("図形のサイズ　※整数値で入力");
		SizeLabel.setOpaque(true);
		SizeLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
		SizeLabel.setHorizontalAlignment(JLabel.CENTER);
		SizeLabel.setBounds(30, 160, 200, 40);
		add(SizeLabel);
		JLabel percent = new JLabel("%(0~200)");
		percent.setOpaque(true);
		percent.setFont(new Font("SansSerif", Font.BOLD, 24));
		percent.setBounds(180,190,100,40);
		add(percent);

		//実行ボタン
		btn = new JButton("描画実行");
		btn.addActionListener(new MyActionListener());
		btn.setBounds(85,380,150,40);
		btn.setFont(new Font("SansSerif", Font.BOLD, 24)); add(btn);

		//描画パネル
		myPanel = new MyPanel();
		myPanel.setBounds(30,450,250,250);
		myPanel.setBackground(Color.LIGHT_GRAY);
		add(myPanel);

		//下部ラベル
		JLabel nameLabel = new JLabel("4523223　猩々　勇佑");
		nameLabel.setOpaque(true);
		nameLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
		nameLabel.setHorizontalAlignment(JLabel.CENTER);
		nameLabel.setBounds(35, 700, 250, 40);
		add(nameLabel);



		//補色の選択時に他３色のチェック外れる
		ActionListener rgbListener = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(chx1.isSelected() || chx2.isSelected() || chx3.isSelected()){
					chxComp.setSelected(false);
				}
				myPanel.repaint();
			}
		};
		chx1.addActionListener(rgbListener);
		chx2.addActionListener(rgbListener);
		chx3.addActionListener(rgbListener);

		chxComp.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(chxComp.isSelected()){

					chx1.setSelected(false);
					chx2.setSelected(false);
					chx3.setSelected(false);
				}
				myPanel.repaint();
			}
		});


		//＝＝＝＝＝＝＝追加要素＝＝＝＝＝＝＝＝
		//背景色
		txtRbg = new JTextField("240");
		txtRbg.setBounds(20,260,60,30);
		txtRbg.setFont(new Font("SansSerif", Font.BOLD, 24));add(txtRbg);
		txtGbg = new JTextField("240");
		txtGbg.setBounds(100,260,60,30);
		txtGbg.setFont(new Font("SansSerif", Font.BOLD, 24));add(txtGbg);
		txtBbg = new JTextField("240");
		txtBbg.setBounds(180,260,60,30);
		txtBbg.setFont(new Font("SansSerif", Font.BOLD, 24));add(txtBbg);

		JLabel contLabel = new JLabel("背景の色変更ゾーン");
		contLabel.setOpaque(true);
		contLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
		contLabel.setHorizontalAlignment(JLabel.CENTER);
		contLabel.setBounds(10, 230, 250, 40);
		add(contLabel);
		//＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝

		setVisible(true);
	}


	//描画パネルのクラス
	class MyPanel extends JPanel{

		public void paintComponent(Graphics g){
			super.paintComponent(g);
			g.setColor(new Color(red,green,blue));

			//描画パネル内中央寄せ
			int size = (120*sz)/100;//変更
			int x =(getWidth() - size) / 2;
			int y =(getHeight() - size) / 2;


			if (shape == 0){//〇
				//塗り潰し〇（追加）
				if(chx4.isSelected() == true){g.fillOval(x, y, size, size);}
				else{g.drawOval(x, y, size, size);}

			}

			else if (shape == 1){//△
				int x1 = x + size/2;
				int y1 = y;

				int x2 = x;
				int y2 = y+ size;

				int x3 = x + size;
				int y3 = y + size;


				//配列に座標をまとめる（追加）
				int[] xs = {x1,x2,x3};
				int[] ys = {y1,y2,y3};

				//塗り潰し△
				if(chx4.isSelected() == true){g.fillPolygon(xs,ys,3);}
				else{
					g.drawLine(x1, y1, x2, y2);
					g.drawLine(x2, y2, x3, y3);
					g.drawLine(x3, y3, x1, y1);
				}

			}

			else if (shape == 2){//□
				//塗り潰し□（追加）
				if(chx4.isSelected() == true){g.fillRect(x, y, size, size);}
				else{g.drawRect(x, y, size, size);}
			}

			else if(shape == 3){//☆（追加）
				int centerX = x + size/2;
				int centerY = y + size/2;

				int outerR =size/2;
				int innerR =(int)(outerR * 0.38);

				int[] xs =new int[10];
				int[] ys = new int[10];

				for(int i = 0; i < 10; i++){
					double angle = Math.toRadians(-90 + i *36);
					int r = (i % 2 == 0)? outerR:innerR;

					xs[i] = centerX + (int)(Math.cos(angle)*r);
					ys[i] = centerY + (int)(Math.sin(angle)*r);
				}

				if(chx4.isSelected() == true){g.fillPolygon(xs,ys,10);}
				else{g.drawPolygon(xs,ys,10);}


			}
		}
	}
	public static void main(String[] args){

		ProgG52FrmDrw2 frm = new ProgG52FrmDrw2("図形描画");

	}

	public class MyActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e){

			//サイズの選択(追加)
			try{sz = Integer.parseInt(txt.getText());}
			catch(Exception ex){sz = 100;}
			if (sz < 0) sz = 0; if (sz > 200) sz = 200;


			//色の選択
			red = green = blue = 0;
			if(chx1.isSelected() == true)red = 255;
			if(chx2.isSelected() == true)green = 255;
			if(chx3.isSelected() == true)blue = 255;
			myPanel.setForeground(new Color(red,green,blue));

			//＝＝＝＝＝＝＝＝＝追加要素＝＝＝＝＝＝＝＝＝＝＝＝＝＝

			//背景色の指定
			int r = 0,g = 0,b = 0;

			//色の選択
			red = green = blue = 0;

			//RGB数値取得
			try{
				r = Integer.parseInt(txtRbg.getText());
				g = Integer.parseInt(txtGbg.getText());
				b = Integer.parseInt(txtBbg.getText());

				//0～255 に制限
				r = Math.max(0, Math.min(255, r));
				g = Math.max(0, Math.min(255, g));
				b = Math.max(0, Math.min(255, b));

			}catch(NumberFormatException ex){
				r = g = b = 0;

				txtRbg.setText("0");
				txtGbg.setText("0");
				txtBbg.setText("0");
			}

			//補色
			if(chxComp.isSelected() == true){
				red = 255 - r;
				green = 255 - g;
				blue = 255 - b;
			}else{
				if(chx1.isSelected() == true)red = 255;
				if(chx2.isSelected() == true)green = 255;
				if(chx3.isSelected() == true)blue = 255;

			}
			myPanel.setForeground(new Color(red,green,blue));

			//背景色の変更
			myPanel.setBackground(new Color(r, g, b));
			//＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝

			//図形の選択
			if(rd1.isSelected()) shape = 0;
			else if (rd2.isSelected()) shape = 1;
			else if (rd3.isSelected()) shape = 2;
			else if(rd4.isSelected())  shape = 3;
			myPanel.repaint();
		}
	}
}
