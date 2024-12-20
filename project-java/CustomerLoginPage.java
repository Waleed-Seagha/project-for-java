import javax.swing.*;  
import java.awt.*;  
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;  
import java.io.*;  
import java.util.ArrayList;  
import java.util.List;  

public class CustomerLoginPage extends JFrame {  
    private JTextField usernameField;  
    private JPasswordField passwordField;  
    private JTextArea outputArea;  
    private String fileName = "users.txt";  

    public CustomerLoginPage() {  
        setTitle("Customer Login");  
        setSize(300, 300);  
        setDefaultCloseOperation(EXIT_ON_CLOSE);  
        setLayout(new BorderLayout());  

        JPanel inputPanel = new JPanel();  
        inputPanel.setLayout(new GridLayout(3, 2));  

        inputPanel.add(new JLabel("Username:"));  
        usernameField = new JTextField();  
        inputPanel.add(usernameField);  

        inputPanel.add(new JLabel("Password:"));  
        passwordField = new JPasswordField();  
        inputPanel.add(passwordField);  

        JButton loginButton = new JButton("Login");  
        loginButton.addActionListener(new LoginAction());  
        inputPanel.add(loginButton);  

        JButton registerButton = new JButton("Register");  
        registerButton.addActionListener(new RegisterAction());  
        inputPanel.add(registerButton);  

        outputArea = new JTextArea();  
        outputArea.setEditable(false);  

        add(inputPanel, BorderLayout.NORTH);  
        add(new JScrollPane(outputArea), BorderLayout.CENTER);  
    }  

    private void checkUser(String username, String password) {  
        try {  
            BufferedReader reader = new BufferedReader(new FileReader(fileName));  
            String line;  
            List<String[]> users = new ArrayList<>();  
            boolean found = false;  

            while ((line = reader.readLine()) != null) {  
                String[] parts = line.split(",");  
                users.add(parts);  
            }  
            reader.close();  

            for (String[] user : users) {  
                if (user.length == 2 && user[0].equals(username) && user[1].equals(password)) {  
                    found = true;  
                    break;  
                }  
            }  

            if (found) {  
                outputArea.setText("Login successful!");  
                new FoodMenuPage(username).setVisible(true);  
                dispose();  
            } else {  
                outputArea.setText("Invalid username or password.");  
            }  
        } catch (IOException e) {  
            outputArea.setText("Error reading users file: " + e.getMessage());  
        }  
    }  

    private void registerUser(String username, String password) {  
        try {  
            FileWriter writer = new FileWriter(fileName, true);  
            writer.write(username + "," + password + "\n");  
            writer.close();  
            outputArea.setText("Registration successful!");  
        } catch (IOException e) {  
            outputArea.setText("Error writing to file: " + e.getMessage());  
        }  
    }  

    private class LoginAction implements ActionListener {  
        @Override  
        public void actionPerformed(ActionEvent e) {  
            String username = usernameField.getText();  
            String password = new String(passwordField.getPassword());  
            checkUser(username, password);  
        }  
    }  

    private class RegisterAction implements ActionListener {  
        @Override  
        public void actionPerformed(ActionEvent e) {  
            String username = usernameField.getText();  
            String password = new String(passwordField.getPassword());  
            registerUser(username, password);  
        }  
    }  
   
    public static void main(String[] args) {  
        SwingUtilities.invokeLater(() -> {  
            new CustomerLoginPage().setVisible(true);  
        });  
    }  
}