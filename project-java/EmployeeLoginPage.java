import javax.swing.*;  
import java.awt.*;  
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;  

public class EmployeeLoginPage extends JFrame {  
    private JTextField usernameField;  
    private JPasswordField passwordField;  
    private JTextArea outputArea;  

    public EmployeeLoginPage() {  
        setTitle("Employee Login");  
        setSize(300, 200);  
        setDefaultCloseOperation(EXIT_ON_CLOSE);  
        setLayout(new GridLayout(4, 2));  

        add(new JLabel("Username:"));  
        usernameField = new JTextField();  
        add(usernameField);  

        add(new JLabel("Password:"));  
        passwordField = new JPasswordField();  
        add(passwordField);  

        JButton loginButton = new JButton("Login");  
        loginButton.addActionListener(new LoginAction());  
        add(loginButton);  

        outputArea = new JTextArea();  
        outputArea.setEditable(false);  
        add(outputArea);  
    }  

    private class LoginAction implements ActionListener {  
        @Override  
        public void actionPerformed(ActionEvent e) {  
            String username = usernameField.getText();  
            String password = new String(passwordField.getPassword());  

            if ("waleed".equals(username) && "1234".equals(password)) {  
                outputArea.setText("Login successful!");  
                new EmployeeDashboard().setVisible(true);  
                dispose();  
            } else {  
                outputArea.setText("Invalid username or password.");  
            }  
        }  
    }  

    public static void main(String[] args) {  
        SwingUtilities.invokeLater(() -> new EmployeeLoginPage().setVisible(true));  
    }  
}