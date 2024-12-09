import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

class Student {
    private String name;
    private String id;
    private String branch;
    private String college;
    private String country;
    private String state;
    private String village;
    private String pincode;

    public Student(String name, String id, String branch, String college, String country, String state, String village, String pincode) {
        this.name = name;
        this.id = id;
        this.branch = branch;
        this.college = college;
        this.country = country;
        this.state = state;
        this.village = village;
        this.pincode = pincode;
    }

    @Override
    public String toString() {
        return String.format("ID: %s, Name: %s, Branch: %s, College: %s, Country: %s, State: %s, Village: %s, Pincode: %s",id, name, branch, college, country, state, village, pincode);
    }
}

public class WinDemo extends JFrame {
    private ArrayList<Student> studentList;
    private JTextField nameField, idField, villageField, pincodeField;
    private JComboBox<String> branchCombo, collegeCombo, countryCombo, stateCombo;
    private JTextArea displayArea;
    private JCheckBox robotCheckBox;

    public WinDemo () {
        studentList = new ArrayList<>();

        // Set up the frame
        setTitle("Student Application Form");
        setSize(500, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create components
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(0, 2)); // 2 columns with gaps

        nameField = new JTextField(10);
        idField = new JTextField(10);
        villageField = new JTextField(10);
        pincodeField = new JTextField(6);

        // Combo boxes for Branch, College, Country, State
        branchCombo = new JComboBox<>(new String[]{"Others", "Computer Science", "Civil"});
        collegeCombo = new JComboBox<>(new String[]{"Others", "ANURAG ENGINEERING College", "ABC Institute", "CHANNAI BHARATHUniversity"});
        countryCombo = new JComboBox<>(new String[]{"Others", "USA", "India", "UK"});
        stateCombo = new JComboBox<>(new String[]{"Others", "TELANGANA", "ANDRAPRADESH", "CHANNAI"});

        JButton submitButton = new JButton("Submit");
        displayArea = new JTextArea();
        displayArea.setEditable(false);

        // Checkbox for "I am not a robot"
        robotCheckBox = new JCheckBox("I am not a robot");

        // Add components to input panel
        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("ID:"));
        inputPanel.add(idField);
        inputPanel.add(new JLabel("Branch:"));
        inputPanel.add(branchCombo);
        inputPanel.add(new JLabel("College:"));
        inputPanel.add(collegeCombo);
        inputPanel.add(new JLabel("Country:"));
        inputPanel.add(countryCombo);
        inputPanel.add(new JLabel("State:"));
        inputPanel.add(stateCombo);
        inputPanel.add(new JLabel("Village:"));
        inputPanel.add(villageField);
        inputPanel.add(new JLabel("Pincode:"));
        inputPanel.add(pincodeField);
        inputPanel.add(robotCheckBox);
        inputPanel.add(submitButton);

        // Add action listener for submit button
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                submitForm();
            }
        });

        // Add panels to frame
        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(displayArea), BorderLayout.CENTER);
    }

    private void submitForm() {
        String name = nameField.getText().trim();
        String id = idField.getText().trim();
        String branch = (String) branchCombo.getSelectedItem();
        String college = (String) collegeCombo.getSelectedItem();
        String country = (String) countryCombo.getSelectedItem();
        String state = (String) stateCombo.getSelectedItem();
        String village = villageField.getText().trim();
        String pincode = pincodeField.getText().trim();

        // Validate input
        if (name.isEmpty() || id.isEmpty() || branch.equals("Select Branch") || college.equals("Select College") || 
            country.equals("Select Country") || state.equals("Select State") || village.isEmpty() || pincode.isEmpty() || 
            !robotCheckBox.isSelected()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields and confirm you are not a robot.");
            return;
        }

        // Check for duplicate ID
        for (Student student : studentList) {
            if (student.toString().contains(id)) {
                JOptionPane.showMessageDialog(this, "ID already exists. Please use a different ID.");
                return;
            }
        }

        // Add student and clear fields
        Student student = new Student(name, id, branch, college, country, state, village, pincode);
        studentList.add(student);
        JOptionPane.showMessageDialog(this, "Application submitted successfully!");

        // Clear fields after submission
        nameField.setText("");
        idField.setText("");
        villageField.setText("");
        pincodeField.setText("");
        branchCombo.setSelectedIndex(0);
        collegeCombo.setSelectedIndex(0);
        countryCombo.setSelectedIndex(0);
        stateCombo.setSelectedIndex(0);
        robotCheckBox.setSelected(true);

        // Optionally, display submitted data
        
        displayArea.append(student.toString() + "\n");
    }

    public static void main(String[] args) {
       // SwingUtilities.invokeLater(() -> {
        	WinDemo   form = new WinDemo  ();
            form.setVisible(true);
       // });
    }
}
