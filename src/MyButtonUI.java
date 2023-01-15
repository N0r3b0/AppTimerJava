import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MyButtonUI extends BasicButtonUI implements MouseListener
{
    @Override
    public void installUI(JComponent c) {
        super.installUI(c);
        c.addMouseListener(this);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        JButton button = (JButton) e.getSource();
        button.setBackground(Design.clickedColor);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        JButton button = (JButton) e.getSource();
        button.setBackground(Design.foreColor);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JButton button = (JButton) e.getSource();
        button.setBackground(Design.enteredColor);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        JButton button = (JButton) e.getSource();
        button.setBackground(Design.enteredColor);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        JButton button = (JButton) e.getSource();
        button.setBackground(Design.foreColor);
    }
}
