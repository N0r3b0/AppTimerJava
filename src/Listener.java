//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.MouseEvent;
//import java.awt.event.MouseListener;
//
//public class Listener implements ActionListener, MouseListener
//{
//    Color backColor = new Color(28, 28, 28);
//    Color frontColor = new Color(31, 31, 31);
//    Color fontColor = new Color(214, 214, 214);
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        JButton button = (JButton) e.getSource();
//        button.setBackground(Color.red);
//    }
//
//    @Override
//    public void mouseClicked(MouseEvent e) {
//
//    }
//
//    @Override
//    public void mousePressed(MouseEvent e) {
//        JButton button = (JButton) e.getSource();
//        button.setBackground(Color.blue);
//
//    }
//
//    @Override
//    public void mouseReleased(MouseEvent e) {
//
//    }
//
//    @Override
//    public void mouseEntered(MouseEvent e) {
//        JButton button = (JButton) e.getSource();
//        button.setBackground(frontColor);
//    }
//
//    @Override
//    public void mouseExited(MouseEvent e) {
//        JButton button = (JButton) e.getSource();
//        button.setBackground(backColor);
//    }
//}
