import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class cons extends JFrame {
    private JTextField ia1TextField;
    private JTextField ia2TextField;
    private JTextField ia3TextField;
    private JTextField ctaTextField;
    private JTextField seeTextField;
    private JButton calculateButton;

    public cons() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        setTitle("Student Grading System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 2, 10, 10));
        getContentPane().setBackground(new Color(255, 204, 153)); // Set background color

        Font labelFont = new Font("Montserrat", Font.PLAIN, 20); // Create a Font object 
        Font buttonFont = new Font("Arial", Font.PLAIN, 20); // Create a Font object for 

        add(createLabel("IA-1 Marks:", labelFont));
        ia1TextField = new JTextField();
        add(ia1TextField);

        add(createLabel("IA-2 Marks:", labelFont));
        ia2TextField = new JTextField();
        add(ia2TextField);

        add(createLabel("IA-3 Marks:", labelFont));
        ia3TextField = new JTextField();
        add(ia3TextField);

        add(createLabel("CTA Marks:", labelFont));
        ctaTextField = new JTextField();
        add(ctaTextField);

        add(createLabel("SEE Marks:", labelFont));
        seeTextField = new JTextField();
        add(seeTextField);

        calculateButton = new JButton("Calculate");
        calculateButton.setBackground(Color.CYAN); // Set button color to white
        calculateButton.setFont(buttonFont); // Set the font for the button text
        add(calculateButton);

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    calculateGrade();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(cons.this, "Invalid input. Please enter valid   numeric values.");
                }
            }
        });

        setVisible(true);
    }

    private JLabel createLabel(String text, Font font) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        return label;
    }

    private void calculateGrade() {
        int ia1Marks, ia2Marks, ia3Marks, ctaMarks, seeMarks;

        try {
            ia1Marks = Integer.parseInt(ia1TextField.getText());
            ia2Marks = Integer.parseInt(ia2TextField.getText());
            ia3Marks = Integer.parseInt(ia3TextField.getText());
            ctaMarks = Integer.parseInt(ctaTextField.getText());
            seeMarks = Integer.parseInt(seeTextField.getText());
        } catch (NumberFormatException e) {
            throw new NumberFormatException();
        }

        int cie = ia1Marks + ia2Marks + ia3Marks + ctaMarks;
        int see = seeMarks;

        if (cie < 20) {
            JOptionPane.showMessageDialog(this, "Student is detained from taking SEE");
            return;
        }

        if (seeMarks == 38 || seeMarks == 39) {
            see = 40;
        } else if (seeMarks < 38) {
            JOptionPane.showMessageDialog(this, "F Grade");
            return;
        }

        double totalMarks = (cie + see) / 2.0;
        int roundedMarks = (int) Math.round(totalMarks);

        String grade;
        if (roundedMarks >= 90) {
            grade = "S";
        } else if (roundedMarks >= 80) {
            grade = "A";
        } else if (roundedMarks >= 70) {
            grade = "B";
        } else if (roundedMarks >= 60) {
            grade = "C";
        } else if (roundedMarks >= 50) {
            grade = "D";
        } else if (roundedMarks >= 40) {
            grade = "E";
        } else {
            grade = "F";
        }

        JOptionPane.showMessageDialog(this, "Grade: " + grade);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new cons();
            }
        });
    }
}