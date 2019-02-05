import javax.swing.*;
import java.awt.event.*;
import java.awt.*;



public class NameFrameGUI extends JFrame {
  private JLabel msg1= new JLabel("Enter name for Player1 :");
  private JLabel msg2= new JLabel("Enter name for Player2 :");
  private JLabel msg3= new JLabel("Enter name for Player3 :");
  private JLabel msg4= new JLabel("Enter name for Player4 :");
  private JPanel content= new JPanel();
  public JTextField entry1= new JTextField(1);
  public JTextField entry2= new JTextField(1);
  public JTextField entry3= new JTextField(1);
  public JTextField entry4= new JTextField(1);

  private JButton btn4= new JButton("ENTER");


  public NameFrameGUI(ActionListener listener){
    super("Monopoly");
    super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    super.setSize(200,250);
    content.setLayout(new BoxLayout(content,BoxLayout.Y_AXIS));
    content.add(msg1);
    content.add(entry1);
    content.add(msg2,entry2);
    content.add(entry2);
    content.add(msg3);
    content.add(entry3);
    content.add(msg4);
    content.add(entry4);
    content.add(btn4);
    btn4.setActionCommand("ENTER");
    btn4.addActionListener(listener);
    super.getContentPane().add(content);
  }

}
