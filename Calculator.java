import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Calculator extends JFrame implements ActionListener {//Calculator class extends JFrame class and implements ActionListener interface

    JFrame frame;//creating a frame
    JTextField textField;// text field
    JPanel numberPanel, functionPanel;// creating panels which contains the buttons;
    JButton[] numberButton=new JButton[10];//buttons wchich represent the numbers from 0 to 9
    JButton[] functionButton=new JButton[9];// buttons those represent the funci
    JButton addButton, multiButton, subButton, divButton;
    JButton sqButton, dotButton, delButton, clrButton, equalButton;

    double num1,num2;//first and second numbers on which operations to be performed;
    char operator;//which operation to be performed +,-,/,*;
    boolean addingSecondNo=false;//to track whether input for num1 is complete or not;
    String number2="";//stores num2 in the form of stirng;

    Font font=new Font("Arial",Font.BOLD,35);//creating new font for the buttons and textfield

    Calculator(){
        frame=new JFrame("My Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 550);
        frame.setLayout(null);

        // initializing , creating the text field
        textField=new JTextField();
        textField.setFont(font);// 
        textField.setBounds(20,5,750,60);// giving size to textfield
        textField.setEditable(false);//now textfield can't be edited manually

        //initializing the buttons with symbols on top
        addButton=new JButton("+");
        multiButton=new JButton("*");
        subButton=new JButton("-");
        divButton=new JButton("/");
        sqButton=new JButton("x^2");
        dotButton=new JButton(".");
        delButton=new JButton("del");
        clrButton=new JButton("clr");
        equalButton=new JButton("=");

        functionButton[0]=addButton;
        functionButton[1]=subButton;
        functionButton[2]=multiButton;
        functionButton[3]=divButton;
        functionButton[4]=dotButton;
        functionButton[5]=sqButton;
        functionButton[6]=delButton;
        functionButton[7]=clrButton;
        functionButton[8]=equalButton;

        for(int i=0;i<10;i++){//initializing the number buttons and adding function to each number button vis ActionListner
            numberButton[i]=new JButton(Integer.toString(i));
            numberButton[i].addActionListener(this);
            numberButton[i].setFont(font);
        }

        for(int i=0;i<9;i++){
            functionButton[i].addActionListener(this);
            functionButton[i].setFont(font);
        }

        numberPanel=new JPanel();
        numberPanel.setBounds(20,80, 350,350);//x,y, width, height
        numberPanel.setLayout(new GridLayout(3,3,5,5));//rows, column, hgap, vgap


        for(int i=0;i<10;i++){// adding numberbuttons to the panel(which is added on frame);
            numberPanel.add(numberButton[i]);
        }

        functionPanel=new JPanel();//panel to add function keys
        functionPanel.setBounds(380,80, 350,350);// setting the panel size;
        functionPanel.setLayout(new GridLayout(3,3,5,5));//row, columns, horizontal margin, vertical margin // setting layout of the function keys with GridLayout manager;

        for(int i=0;i<9;i++){
            functionPanel.add(functionButton[i]);
        }

        //adding the components to the frame;
        frame.add(textField);
        frame.add(numberPanel);
        frame.add(functionPanel);
        frame.setVisible(true);
    }
    public static void main(String[] args) {//main methood where programme start exicuting..

        new Calculator();
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {//ActionListner class to implement the associated functions to the Buttons
        for(int i=0;i<10;i++){
            if(e.getSource() == numberButton[i]){
                textField.setText(textField.getText().concat(String.valueOf(i)));//modifing the textfield values;
                if(addingSecondNo){//checking whether adding second operand or the first operand
                    number2=number2.concat(String.valueOf(i));
                }
            }
        }

        if(e.getSource()==dotButton){
            textField.setText(textField.getText().concat("."));
            number2=number2.concat(".");
        }

        if(e.getSource()==clrButton){
            textField.setText("");
            num1=0;
            addingSecondNo=false;
            number2="";
        }

        if(e.getSource()==delButton){
            if(textField.getText().length()>=1)
                textField.setText(textField.getText().substring(0,textField.getText().length()-1));
            if(number2.length()>=1){
                number2=number2.substring(0, number2.length());
            }
        }

        if(e.getSource()==sqButton){
            num1=Double.parseDouble(textField.getText());
            num1=num1*num1;
            textField.setText(Double.toString(num1));
            number2="";
            addingSecondNo=true;
        }

        if(e.getSource()==addButton){
            operator='+';
            num1=Double.parseDouble(textField.getText());
            textField.setText(textField.getText().concat("+"));
            number2="";
            addingSecondNo=true;
        }

        if(e.getSource()==divButton){
            operator='/';
            num1=Double.parseDouble(textField.getText());
            textField.setText(textField.getText().concat("/"));
            number2="";
            addingSecondNo=true;
        }

        if(e.getSource()==subButton){
            operator='-';
            num1=Double.parseDouble(textField.getText());
            textField.setText(textField.getText().concat("-"));
            number2="";
            addingSecondNo=true;
        }

        if(e.getSource()==multiButton){
            operator='*';
            num1=Double.parseDouble(textField.getText());
            textField.setText(textField.getText().concat("*"));
            number2="";
            addingSecondNo=true;
        }


        //defining the operations on pressing the equal Button.
        if(e.getSource()==equalButton){
            switch(operator){
                case '+' :{
                    num2=Double.parseDouble(number2);//parsing the stiring value into double type.
                    num1+=num2;
                    textField.setText(Double.toString(num1));
                    break; 
                }
                case '-' :{
                    num2=Double.parseDouble(number2);
                    num1-=num2;
                    textField.setText(Double.toString(num1));
                    break; 
                }
                case '*' :{
                    num2=Double.parseDouble(number2);
                    num1*=num2;
                    textField.setText(Double.toString(num1));
                    break; 
                }
                case '/' :{
                    num2=Double.parseDouble(number2);
                    num1/=num2;
                    textField.setText(Double.toString(num1));
                    break; 
                }

            }
        }

    }
}