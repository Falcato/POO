package calculator;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Calculator {

    private String input = "0";
    /*memory should save the number in memory*/
    private float operand1, screen_value, memory;
    private char operation = 'n';
    /*square nedds to be initialized*/
    private JTextField screen,square;
	
    public static final int BORDER = 5;
    public static final int STD_BUTTON_HEIGHT=30;
    public static final int STD_BUTTON_WIDTH=35;
    public static final int LARGE_BUTTON_HEIGHT=30;
    public static final int LARGE_BUTTON_WIDTH=((5*STD_BUTTON_WIDTH+4*BORDER)-2*BORDER)/3;
    public static final Dimension STD_BUTTON_SIZE = new Dimension(STD_BUTTON_WIDTH,STD_BUTTON_HEIGHT);
    public static final Dimension LARGE_BUTTON_SIZE = new Dimension(LARGE_BUTTON_WIDTH,LARGE_BUTTON_HEIGHT); 

    private class ActionNumberButton implements ActionListener {
        int num;
        public ActionNumberButton(int i) {
            num=i;
        }
        public void actionPerformed(ActionEvent e) {
            input = input+Integer.toString(num);
            screen_value = Float.parseFloat(input);
            printScreen(screen_value);
        }
    }
    private class ActionOperation implements ActionListener {
        char op;
        public ActionOperation(char op) {
            this.op = op;
        }
        public void actionPerformed(ActionEvent e) {
            if (operation=='n') {
                operand1 = screen_value;
                operation = op;
            } else {
                screen_value = executeOp(operation,operand1,screen_value);
                operation = op;
                operand1 = screen_value;
                printScreen(screen_value);
            }
            input="0";
        }
    }
    private class ActionEqual implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (operation!='n') {
                screen_value = executeOp(operation,operand1,screen_value);
            }
            operation = 'n';
            input = "0";
            printScreen(screen_value);
        }
    }
    private class ActionDot implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (!input.contains("."))
                input += ".";
        }
    }
    private class ActionBackspace implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (input.length()!=1) { 
                input = input.substring(0,input.length()-1);
                screen_value = Float.parseFloat(input);
                printScreen(screen_value);
            }
        }
    }
    private class ActionC implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            input = "0";
            screen_value = 0;
            operation = 'n';
            printScreen(screen_value);
	}
    }
    private class ActionCE implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            input = "0";
            screen_value = 0;
            printScreen(screen_value);
        }
    }
    private class ActionMC implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            
        /* ... */
        	memory = 0;
        	square.setText("");
        }
    }
    private class ActionMR implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            
        /* ... */
        	screen.setText(Float.toString(memory));
	
        }
    }
    private class ActionMS implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            
        /* ... */
        	memory = Float.parseFloat(screen.getText());
        	square.setText("M");
        }
    }
    private class ActionMP implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            
        /* ... */
        	screen.setText(Float.toString(memory + 
        					Float.parseFloat(screen.getText())));
		}
    }
	
    public float executeOp(char op, float op1, float op2) {
        switch(op) {
            case '+':
                return op1+op2;
            case '-':
                return op1-op2;
            case '*':
                return op1*op2;
            case '/':	
                if (Float.parseFloat(input)!=0)
                    return op1/op2;
                else return 0;
            case 'n':
                return op1;
            default: System.out.println("Operacao nao reconhecida");
        }
        return 0;
    }
	
    public void printScreen(float f) {
        screen.setText(String.valueOf(f));
    }
	
    public JButton createButton(String label, ActionListener al, Dimension dim) {
        JButton button = new JButton(label);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.addActionListener(al);
        button.setPreferredSize(dim);
        button.setMaximumSize(dim);
        button.setMargin(new Insets(3,3,3,3));
        return button;
    }
	
    public JPanel makeCalculator(){
        JTextField lcd;
        JPanel keyboard,mem,clear,panel;
        
        mem = makeMemoryPad();
        lcd = makeScreen();
        keyboard = makeNumberPad();
        clear = makeClearPad();
		
           
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.add(clear);
        panel.add(Box.createRigidArea(new Dimension(BORDER,BORDER)));
        panel.add(keyboard);
        
        JPanel panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1,BoxLayout.X_AXIS));
        panel1.add(mem);
        panel1.add(Box.createRigidArea(new Dimension(BORDER,BORDER)));
        panel1.add(panel);
        
        JPanel panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2,BoxLayout.Y_AXIS));
        panel2.add(lcd);
        panel2.add(Box.createRigidArea(new Dimension(BORDER,BORDER)));
        panel2.add(panel1);
        panel2.setBorder(new EmptyBorder(BORDER,BORDER,BORDER,BORDER));
        
        return panel2;
    }
	
    public JTextField makeScreen() {
        screen = new JTextField();
        screen.setEditable(false);
        screen.setText("");
        screen.setHorizontalAlignment(JTextField.RIGHT);
        return screen;
    }
	
    public JPanel makeClearPad() {
        JPanel panel = new JPanel();
        BoxLayout layout =new BoxLayout(panel,BoxLayout.X_AXIS);
        panel.setLayout(layout);
		
        panel.add(createButton("Backspace", new ActionBackspace(),LARGE_BUTTON_SIZE));
        panel.add(Box.createRigidArea(new Dimension(BORDER,BORDER)));
        panel.add(createButton("CE", new ActionCE(),LARGE_BUTTON_SIZE));
        panel.add(Box.createRigidArea(new Dimension(BORDER,BORDER)));
        panel.add(createButton("C", new ActionC(),LARGE_BUTTON_SIZE));
        panel.add(Box.createRigidArea(new Dimension(BORDER,BORDER)));
		
        return panel;
    }
	
    public JPanel makeMemoryPad() {
		
        JPanel panel = new JPanel();
        BoxLayout layout =new BoxLayout(panel,BoxLayout.Y_AXIS);
        panel.setLayout(layout);
        JButton button;
        JButton[] memButtons = new JButton[4];
        
        
        
	    square = new JTextField();
	    square.setEditable(false);
	    square.setText("");
	    square.setHorizontalAlignment(JTextField.CENTER);
	    square.setMaximumSize(STD_BUTTON_SIZE);
	    square.setPreferredSize(STD_BUTTON_SIZE);
	    panel.add(square);
	    panel.add(Box.createRigidArea(new Dimension(BORDER,BORDER)));
	    memButtons[0] = createButton(("MC"), new ActionMC(),STD_BUTTON_SIZE);
	    panel.add(memButtons[0]);
	    panel.add(Box.createRigidArea(new Dimension(BORDER,BORDER)));
	    memButtons[1] = createButton(("MR"), new ActionMR(),STD_BUTTON_SIZE);
	    panel.add(memButtons[1]);
	    panel.add(Box.createRigidArea(new Dimension(BORDER,BORDER)));
	    memButtons[2] = createButton(("MS"), new ActionMS(),STD_BUTTON_SIZE);
	    panel.add(memButtons[2]);
	    panel.add(Box.createRigidArea(new Dimension(BORDER,BORDER)));
	    memButtons[3] = createButton(("MP"), new ActionMP(),STD_BUTTON_SIZE);
	    panel.add(memButtons[3]);
		
	    return panel;
    }
	
    public JPanel makeNumberPad() {
        GridLayout grid = new GridLayout(4,5,BORDER,BORDER);
        JPanel panel = new JPanel(grid);
        JButton button;
        JButton[] numberButtons = new JButton[10];

        for(int i=1; i<=10; i++){
            numberButtons[i%10]=createButton(Integer.toString(i%10), new ActionNumberButton(i%10),STD_BUTTON_SIZE);
            panel.add(numberButtons[i%10]);
        }
		
        panel.add(createButton("+", new ActionOperation('+'),STD_BUTTON_SIZE));
        panel.add(createButton("-", new ActionOperation('-'),STD_BUTTON_SIZE));
        panel.add(createButton("*", new ActionOperation('*'),STD_BUTTON_SIZE));
        panel.add(createButton("/", new ActionOperation('/'),STD_BUTTON_SIZE));
		
        panel.add(createButton("=", new ActionEqual(),STD_BUTTON_SIZE));
        panel.add(createButton(".", new ActionDot(),STD_BUTTON_SIZE));
		
        return panel;
    }
	
    public static void main(String[] args) {
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("Calculadora");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
        Calculator p = new Calculator();
        frame.setContentPane(p.makeCalculator());
                
        frame.setResizable(false);
        frame.pack(); //has the same effect as: frame.setSize(frame.getPreferredSize());
        frame.setVisible(true);
    }
}