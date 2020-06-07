package Telefon.Render;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Render
{
    int iMax = 50;
    int windowSize_X = 300;
    int windowSize_Y = 100;
    public JFrame frame;
    public JPanel panel;
    public JLabel Label[];
    public JButton Button[];
    public JTextField textField[];
    public JList<String> MY_LIST;
    public JScrollPane scrollPane;
    private int currentLineX = 0;
    private int currentLineY = 25;
    boolean pressed = false;
    public Render(String phoneName)
    {
        frame = new JFrame();
        panel = new JPanel();
        Label = new JLabel[51];
        Button = new JButton[50];
        textField = new JTextField[50];
        MY_LIST = new JList<String>(new DefaultListModel<String>());
        for(int i = 0 ; i < iMax; i++)
            textField[i] = new JTextField();
        for(int i = 0 ; i < iMax + 1; i++) {
            Label[i] = new JLabel();
            Label[i].setHorizontalAlignment(JLabel.LEFT);
            Label[i].setVerticalAlignment(JLabel.TOP);
        }
        for(int i = 0 ; i < iMax; i++) {
            Button[i] = new JButton();
        }
        panel.setBorder(BorderFactory.createEmptyBorder(windowSize_X,windowSize_X,windowSize_X,windowSize_Y));
        panel.setLayout(new GridLayout(0,1));
        for(int i = 0 ; i < iMax + 1; i++){
            Label[i].setVisible(false);
            frame.getContentPane().add(Label[i]);
        }
        for(int i = 0 ; i < iMax ; i++) {

            Button[i].setVisible(false);
            frame.getContentPane().add(Button[i]);
        }
        for(int i = 0 ; i < iMax ; i++){

            textField[i].setVisible(false);
            frame.getContentPane().add(textField[i]);
        }

        scrollPane = new JScrollPane();
        scrollPane.setViewportView(this.MY_LIST);
        this.MY_LIST.setLayoutOrientation(JList.VERTICAL);
        scrollPane.setVisible(false);
        frame.getContentPane().add(scrollPane);
        frame.add(panel,BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle(super.getClass().getSimpleName());

        frame.pack();
        frame.setVisible(true);
        setTitle("My Frame");
        frame.setResizable(false);

        Font myFont = frame.getFont();
        Label[iMax].setText(phoneName);
        Label[iMax].setBounds((windowSize_X / 2) + phoneName.length(),5,10 * phoneName.length(), 25);
        Label[iMax].setVisible(true);
    }
    public void SetCurrentLines(int x, int y)
    {
        if(x != -1)
            this.currentLineX = x;
        if(y != -1)
            this.currentLineY = y;
    }
    public void Button(int index,String text, int x,int y,int size_x,int size_y)
    {
        int textSize = text.length();
        Font font = frame.getFont();
        int x_final = x;
        int y_final = y;
        if (x == -1)
            x_final = currentLineX;
        if (y == -1)
            y_final = currentLineY;
        if (x == -2)
            x_final = (windowSize_X / 2) - (textSize * font.getSize() / 6);
        if (y == -2)
            y_final = (windowSize_Y / 2);

        Button[index].setText(text);
        Button[index].setBounds(x_final, y_final, size_x, size_y);
        if (x == -1)
            currentLineX += size_x;
        if (y == -1)
            currentLineY += size_y;
        Button[index].setVisible(true);
    }
    public void ListBox(int index,String[] text, int x,int y,int size_x,int size_y, boolean opaque, Color background)
    {
        int maxLength = 0;
        for(int i = 0 ; i < index; i++)
            if(text[i].length() > maxLength)
                maxLength = text[i].length();
        int textSize = maxLength;
        Font font = frame.getFont();
        int x_final = x;
        int y_final = y;
        if (x == -1)
            x_final = currentLineX;
        if (y == -1)
            y_final = currentLineY;
        if (x == -2)
            x_final = (windowSize_X / 2) - (textSize * font.getSize() / 6);
        if (y == -2)
            y_final = (windowSize_Y / 2);
        scrollPane.setBounds(0, 25, size_x, size_y);
        ((DefaultListModel)MY_LIST.getModel()).clear();
        //JList.setBounds(0, 25, size_x, size_y);
        for(int i = 0 ; i < index; i++)
            ((DefaultListModel)MY_LIST.getModel()).addElement(text[i]);
        MY_LIST.setVisible(true);
        scrollPane.setVisible(true);
    }
    public void Label(int index,String text, int x,int y,int size_x,int size_y, boolean opaque, Color background,Color foreGround) {

        int textSize = text.length();
        Font font = frame.getFont();
        int x_final = x;
        int y_final = y;
        if (x == -1)
            x_final = currentLineX;
        if (y == -1)
            y_final = currentLineY;
        if (x == -2)
            x_final = (windowSize_X / 2) - (textSize * font.getSize() / 6);
        if (y == -2)
            y_final = (windowSize_Y / 2);
        Label[index].setForeground(foreGround);
        Label[index].setText(text);
        Label[index].setBounds(x_final, y_final, size_x, size_y);
        Label[index].setBackground(background);
        Label[index].setOpaque(opaque);
        if (x == -1)
            currentLineX += size_x ;
        if (y == -1)
            currentLineY += size_y;
        Label[index].setVisible(true);
    }
    public void ShutDown()
    {
        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
    }
    public void InputText(int index,String initialText,int x, int y,int size_x,int size_y, boolean opaque,Color background,Color foreGround)
    {
        int textSize = initialText.length();
        Font font = frame.getFont();
        int x_final = x;
        int y_final = y;
        if (x == -1)
            x_final = currentLineX;
        if (y == -1)
            y_final = currentLineY;
        if (x == -2)
            x_final = (windowSize_X / 2) - (textSize * font.getSize() / 6);
        if (y == -2)
            y_final = (windowSize_Y / 2);
        textField[index].setForeground(foreGround);
        textField[index].setText(initialText);
        textField[index].setBounds(x_final, y_final, size_x , size_y);
        textField[index].setBackground(background);
        textField[index].setOpaque(opaque);
        if (x == -1)
            currentLineX += size_x + ((font.getSize() / 2) * textSize);
        if (y == -1)
            currentLineY += size_y;
        textField[index].setVisible(true);
    }
    public void setTitle(String title) {

        frame.setTitle(title);

        Font font = frame.getFont();

        String currentTitle = frame.getTitle().trim();
        FontMetrics fm = frame.getFontMetrics(font);
        int frameWidth = frame.getWidth();
        int titleWidth = fm.stringWidth(currentTitle);
        int spaceWidth = fm.stringWidth(" ");
        int centerPos = (frameWidth / 2) - (titleWidth / 2);
        int spaceCount = centerPos / spaceWidth;
        String pad = "";
        pad = String.format("%" + (spaceCount - 14) + "s", pad);
        frame.setTitle(pad + currentTitle);

    }
    public int GetXY(String title)
    {

        Font font = frame.getFont();

        String currentTitle = frame.getTitle().trim();
        FontMetrics fm = frame.getFontMetrics(font);
        int frameWidth = frame.getWidth();
        int titleWidth = fm.stringWidth(currentTitle);
        int spaceWidth = fm.stringWidth(" ");
        int centerPos = (frameWidth / 2) - (titleWidth / 2);
        int spaceCount = centerPos / spaceWidth;
        return spaceCount;
    }
    public void ResetEverything()
    {

        ((DefaultListModel)MY_LIST.getModel()).clear();
        for(int i = 0 ; i < iMax; i++) {
            if(Button[i].getActionListeners().length > 0) {
                for(int j = 0; j < Button[i].getActionListeners().length; j++) {
                    Button[i].removeActionListener(Button[i].getActionListeners()[j]);
                }
            }
            textField[i].setVisible(false);
            Label[i].setVisible(false);
            Button[i].setVisible(false);
        }
        this.scrollPane.setVisible(false);
    }
}
