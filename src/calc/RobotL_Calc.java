package calc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;

public class RobotL_Calc extends JFrame implements ActionListener, KeyListener {
   

      
      private String[] name = { 
            "%", "CE", "C", "←", 
            "1/x", "x²", "2루트x", "÷",
            "7", "8", "9", "×", 
            "4", "5", "6", "-", 
            "1", "2", "3", "+", 
            "+/-", "0", ".", "="};
      
      private JPanel panelUp;
      private JPanel panelDown;
      
      private JPanel panel1;
      private JPanel panel2;
      private JPanel panel3;
      
      private JButton btn;
      private JButton btn2;
      private JButton[] btn3;
      
      private JTextField tfResult;
      private JTextField tf;

      double num1 = 0;
      double num2 = 0;
      double result = 0;
      String operator = null;
      
      public RobotL_Calc(String title) {
         setTitle(title);
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         setLocation(600, 300);
         setSize(350, 500);
         setLayout(new GridLayout(2, 1));
         
         panelUp();
         panelDown();
         
         setVisible(true);

         btn3[0].requestFocus();   // 처음 실행 시 키보드 이벤트가 제대로 동작하지 않아 btn에 포커스 잡아줌
      }

      private void panelUp() {
         String[] menu = { "MC", "MR", "M+", "M-", "MS", "M▼"};
         
         panelUp = new JPanel(new BorderLayout());
         
         JMenuBar menuBar = new JMenuBar();
         JMenu menub = new JMenu("≡");
         JMenu menus = new JMenu("표준");
         JMenu menud = new JMenu("#");
         menuBar.add(menub);
         menuBar.add(menus);
         menuBar.add(menud);
         setJMenuBar(menuBar);
         
         panel1 = new JPanel();
         panel1.setLayout(new BorderLayout());
         
         panel2 = new JPanel();
         panel2.setLayout(new BorderLayout());

         tfResult = new JTextField(10);
         tfResult.setText("0");
         tfResult.setFont(new Font("a", Font.PLAIN, 50));
         tfResult.setHorizontalAlignment(JTextField.RIGHT);
         tfResult.setEnabled(true);
         tfResult.setEditable(false);
         panel1.add(tfResult);
         
         tf = new JTextField(5);
         tf.setFont(new Font("a", Font.ITALIC, 20));
         tf.setHorizontalAlignment(JTextField.RIGHT);
         tf.setEnabled(false);
         panel2.add(tf);

         panelUp.add(panel1, BorderLayout.CENTER);
         panelUp.add(panel2, BorderLayout.NORTH);
         
         add(panelUp);
      
         panel3 = new JPanel(new GridLayout(1, 6));
         for(int i=0; i<6; i++) {
         btn2 = new JButton(menu[i]);
         if(btn2.getText() == "MC" ||
            btn2.getText() == "MR" ||
            btn2.getText() == "M+" ||
            btn2.getText() == "M-" ||
            btn2.getText() == "MS" ||
            btn2.getText() == "M▼") {
            btn2.setBackground(Color.WHITE);
            btn2.setFont(new Font("돋움", Font.BOLD, 12));
         }
         
         panel3.add(btn2);
         }
         
         
         panelUp.add(panel3, BorderLayout.SOUTH);
      }

      private void panelDown() {
         
         panelDown = new JPanel(new GridLayout(6, 4, 1, 1));
         btn3 = new JButton[name.length];
         
         for(int j=0; j<24; j++) {
            btn3[j] = new JButton(name[j]);
            btn3[j].setFont(new Font("돋움", Font.BOLD, 13));
            btn3[j].addActionListener(this);
            btn3[j].addKeyListener(this);
            panelDown.add(btn3[j]);
            
            if(btn3[j].getText() == "%" ||      // 버튼 색변경
                  btn3[j].getText() == "CE" ||
                  btn3[j].getText() == "C" ||
                  btn3[j].getText() == "←" ||
                  btn3[j].getText() == "÷" ||
                  btn3[j].getText() == "×" ||
                  btn3[j].getText() == "-" ||
                  btn3[j].getText() == "+") {
               btn3[j].setBackground(Color.LIGHT_GRAY);
            } else if(btn3[j].getText() == "=") {
               btn3[j].setBackground(Color.YELLOW);
            } else {
               btn3[j].setBackground(Color.WHITE);
               btn3[j].setFont(new Font("굴림", Font.BOLD, 14));
            }
         }
         add(panelDown);
      }
   public static void main(String[] args) {
	   RobotL_Calc c = new RobotL_Calc("계산기");
   }
      @Override
      public void actionPerformed(ActionEvent e) {
         Object obj = e.getSource();
         
         if(obj == btn3[19]) {         // +연산
            doubleCalculation();
            num1 = Double.parseDouble(tfResult.getText());
            operator = name[19];
            fieldResult();
            tfResult.setText("");
         }else if(obj == btn3[15]) {   // -연산
            doubleCalculation();
            num1 = Double.parseDouble(tfResult.getText());
            operator = name[15];
            fieldResult();
            tfResult.setText("");   
         }else if(obj == btn3[11]) {   // x연산
            doubleCalculation();
            num1 = Double.parseDouble(tfResult.getText());
            operator = name[11];
            fieldResult();
            tfResult.setText("");
         }else if(obj == btn3[7]) {   // /연산
            doubleCalculation();
            num1 = Double.parseDouble(tfResult.getText());
            operator = name[7];
            fieldResult();
            tfResult.setText("");
         }else if(obj == btn3[0]) {   // % 연산
            doubleCalculation();
            num1 = Double.parseDouble(tfResult.getText());
            operator = name[0];
            fieldResult();
            tfResult.setText("");
         }
         
         else if(obj == btn3[20]) {
            if(tfResult.getText().equals("0")) {
               tfResult.setText("");
            }
            tfResult.setText("-" + tfResult.getText());
     
         }
         
         
         else if(obj == btn3[23]) {   // =연산   
            num2 = Double.parseDouble(tfResult.getText());
            if(operator == "+") { 
               result = num1 + num2;
               tf.setText(tf.getText() + num2 + "=");  // ex) 1+2로 표시되도록 함
            }else if(operator == "-") {
               result = num1 - num2;
               tf.setText(tf.getText() + num2 + "=");
            }else if(operator == "×") {
               result = num1 * num2;
               tf.setText(tf.getText() + num2 + "=");
            }else if(operator == "÷") {
               result = num1 / num2;
               tf.setText(tf.getText() + num2 + "=");
            }else if(operator == "%") { 
               result = num1 % num2;
               tf.setText(tf.getText() + num2 + "=");
            }
            String s = String.valueOf(result); //result값 변환
            tfResult.setText(s);   
            operator = name[23];
            //tf.setText("");
            
         }
         else if(obj == btn3[3]) {   // <- 연산
            String value = tfResult.getText();
            
            if(value.length() == 1) {   // 만약 필드에 숫자가 한 자리만 남아있다면 0으로 변경한다.
               tfResult.setText("0");
            }
            else {
               String str = value.substring(0, value.length()-1);
               tfResult.setText(str);
            }
            
         }else if(obj == btn3[1]) { // CE연산
            tfResult.setText("0");

         }else if(obj == btn3[2]) { // C연산
            tfResult.setText("0");
            tf.setText("0");
            num1 = 0;
            num2 = 0;
            result = 0;
            operator = null;
         
         }else {      // else에 왔으면 기호가 아닌 '숫자'이기 때문에 그대로 출력해줌
//            if(operator == "=") {
//               tf.setText("");
//               operator = null;
//            }
            for(int i=0;i<btn3.length;i++) {
               if(obj == btn3[i]) {
                  if(tfResult.getText().equals("0")) {   // 만약 field에 0만 있다면 초기화
                     tfResult.setText("");
                  }
                  tfResult.setText(tfResult.getText() + btn3[i].getText()); // 기존field값에 클릭한 숫자 추가
               }
            }
            
         }   
      }

      private void fieldResult() {
         if(num1 % 1 == 0) {
            double a  = Double.parseDouble(tfResult.getText());
            a += 0.0;
            tf.setText(a + operator);
         }else {
            tf.setText(tfResult.getText() + operator);
         }
      }

      private void doubleCalculation() {
         if(operator != null) {
            num2 = Double.parseDouble(tfResult.getText());
            if(operator == "+") { 
               result = num1 + num2;
            }else if(operator == "-") {
               result = num1 - num2;
            }else if(operator == "×") {
               result = num1 * num2;
            }else if(operator == "÷") {
               result = num1 / num2;
            }else if(operator == "%") { 
               result = num1 % num2;
            }
            String s = String.valueOf(result); //result값 변환
            tfResult.setText(s);
            tf.setText(s);
         }
      }

   @Override
   public void keyTyped(KeyEvent e) {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void keyPressed(KeyEvent e) {
      
      // 키보드 누르면 계산
      switch (e.getKeyCode()) {
      case KeyEvent.VK_0:
      case KeyEvent.VK_NUMPAD0:
         btn3[21].doClick();
         break;//0입력
         
      case KeyEvent.VK_1:
      case KeyEvent.VK_NUMPAD1:
         btn3[16].doClick();
         break;//1입력
         
      case KeyEvent.VK_2:
      case KeyEvent.VK_NUMPAD2:
         btn3[17].doClick();
         break;//2입력
         
      case KeyEvent.VK_3:
      case KeyEvent.VK_NUMPAD3:
         btn3[18].doClick();
         break;//3입력
         
      case KeyEvent.VK_4:
      case KeyEvent.VK_NUMPAD4:
         btn3[12].doClick();
         break;//4입력
         
      case KeyEvent.VK_5:
      case KeyEvent.VK_NUMPAD5:
         btn3[13].doClick();
         break;//5입력
         
      case KeyEvent.VK_6:
      case KeyEvent.VK_NUMPAD6:
         btn3[14].doClick();
         break;//6입력
         
      case KeyEvent.VK_7:
      case KeyEvent.VK_NUMPAD7:
         btn3[8].doClick();
         break;//7입력
         
      case KeyEvent.VK_8:
      case KeyEvent.VK_NUMPAD8:
         btn3[9].doClick();
         break;//8입력
         
      case KeyEvent.VK_9:
      case KeyEvent.VK_NUMPAD9:
         btn3[10].doClick();
         break;//9입력
         
      case KeyEvent.VK_PERIOD:
      case KeyEvent.VK_DECIMAL:
         btn3[22].doClick();
         break;//.입력
         
      case KeyEvent.VK_ENTER:
      case KeyEvent.VK_EQUALS:
         btn3[23].doClick();
         break;//=입력
      
      case KeyEvent.VK_MINUS:
      case KeyEvent.VK_SUBTRACT:
         btn3[15].doClick();
         break;//-입력
         
      case KeyEvent.VK_ESCAPE:
         btn3[20].doClick();
         break;//+/-입력-뭔지 몰라서 일단 빈칸 넣어둠
      
      case KeyEvent.VK_ADD:
         btn3[19].doClick();
         break;//+입력   
      
      case KeyEvent.VK_MULTIPLY:
         btn3[11].doClick();
         break;//*입력
         
      case KeyEvent.VK_DIVIDE:
      case KeyEvent.VK_SLASH:
         btn3[7].doClick();
         break;// 나누기 입력
      
      case KeyEvent.VK_BACK_SPACE:
         btn3[3].doClick();
         break;//<- 입력
      
      }
      
   }

   @Override
   public void keyReleased(KeyEvent e) {
      // TODO Auto-generated method stub
      
   }
   }
      
      