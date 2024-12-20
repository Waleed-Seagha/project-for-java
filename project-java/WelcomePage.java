import javax.swing.*;  
import java.awt.*;  
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;  

public class WelcomePage extends JFrame {  
    public WelcomePage() {  
        setTitle("Welcome");  
        setSize(300, 200);  
        setDefaultCloseOperation(EXIT_ON_CLOSE);  
        setLayout(new GridLayout(3, 1));  

        JLabel welcomeLabel = new JLabel("Welcome to the Login Application", SwingConstants.CENTER);  
        add(welcomeLabel);  

        JButton employeeLoginButton = new JButton("Login as Employee");  
        employeeLoginButton.addActionListener(new EmployeeLoginAction());  
        add(employeeLoginButton);  

        JButton customerLoginButton = new JButton("Login as Customer");  
        customerLoginButton.addActionListener(new CustomerLoginAction());  
        add(customerLoginButton);  
    }  

    private class EmployeeLoginAction implements ActionListener {  
        @Override  
        public void actionPerformed(ActionEvent e) {  
            new EmployeeLoginPage().setVisible(true);  
            dispose(); // Close the welcome page  
        }  
    }  

    private class CustomerLoginAction implements ActionListener {  
        @Override  
        public void actionPerformed(ActionEvent e) {  
            new CustomerLoginPage().setVisible(true);  
            dispose(); // Close the welcome page  
        }  
    }  

    public static void main(String[] args) {  
        SwingUtilities.invokeLater(() -> {  
            WelcomePage welcomePage = new WelcomePage();  
            welcomePage.setVisible(true);  
        });  
    }  
}