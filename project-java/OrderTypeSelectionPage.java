import javax.swing.*;  
import java.awt.*;  
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;  
import java.io.FileWriter;  
import java.io.IOException;  
import java.util.ArrayList;  

public class OrderTypeSelectionPage extends JFrame {  
    private String customerName;  
    private ArrayList<String> selectedItems;  
    private double totalCost;  
    private JLabel totalLabel;  

    public OrderTypeSelectionPage(String customerName, ArrayList<String> selectedItems, double totalCost) {  
        this.customerName = customerName;  
        this.selectedItems = selectedItems;  
        this.totalCost = totalCost;  

        setTitle("Order Type Selection");  
        setSize(300, 300);  
        setDefaultCloseOperation(EXIT_ON_CLOSE);  
        setLayout(new GridLayout(0, 1));  

        totalLabel = new JLabel("Total Cost: $" + totalCost);  
        add(totalLabel);  

        JButton backButton = new JButton("< Back");  
        backButton.addActionListener(e -> {  
            new CustomerLoginPage().setVisible(true);  
            dispose();  
        });  
        add(backButton);  

        JButton dineInButton = new JButton("Dine In");  
        dineInButton.addActionListener(e -> setTotalCost(totalCost));  
        add(dineInButton);  

        JButton deliveryButton = new JButton("Delivery (+$3)");  
        deliveryButton.addActionListener(e -> setTotalCost(totalCost + 3));  
        add(deliveryButton);  

        JButton addTipButton = new JButton("Add Tip (+$2)");  
        addTipButton.addActionListener(e -> setTotalCost(totalCost + 2));  
        add(addTipButton);  

        JButton confirmButton = new JButton("Confirm Order");  
        confirmButton.addActionListener(new ConfirmOrderAction());  
        add(confirmButton);  

        JButton cancelButton = new JButton("Cancel Order");  
        cancelButton.addActionListener(e -> {  
            new CustomerLoginPage().setVisible(true);  
            dispose();  
        });  
        add(cancelButton);  
    }  

    private void setTotalCost(double newTotalCost) {  
        this.totalCost = newTotalCost;  
        totalLabel.setText("Total Cost: $" + totalCost);  
    }  

    private class ConfirmOrderAction implements ActionListener {  
        @Override  
        public void actionPerformed(ActionEvent e) {  
            try (FileWriter writer = new FileWriter("orders.txt", true)) {  
                writer.write("Customer: " + customerName + "\n");  
                writer.write("Ordered Items: " + selectedItems.toString() + "\n");  
                writer.write("Total Cost: $" + totalCost + "\n\n");  
                JOptionPane.showMessageDialog(OrderTypeSelectionPage.this, "Your order has been placed successfully!");  
            } catch (IOException ex) {  
                JOptionPane.showMessageDialog(OrderTypeSelectionPage.this, "Error saving order: " + ex.getMessage());  
            }  
            dispose();  
        }  
    }  

    public static void main(String[] args) {  
        SwingUtilities.invokeLater(() -> new OrderTypeSelectionPage("Sample Customer", new ArrayList<>(), 0).setVisible(true));  
    }  
}