import javax.swing.*;  
import java.awt.*;  
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;  
import java.util.ArrayList;  

public class FoodMenuPage extends JFrame {  
    private String customerName;  
    private ArrayList<String> selectedItems;  
    private double totalCost;  

    public FoodMenuPage(String customerName) {  
        this.customerName = customerName;  
        selectedItems = new ArrayList<>();  
        totalCost = 0;  

        setTitle("Food Menu");  
        setSize(400, 400);  
        setDefaultCloseOperation(EXIT_ON_CLOSE);  
        setLayout(new GridLayout(0, 1));  

        JPanel headerPanel = new JPanel(new FlowLayout());  
        JLabel headerLabel = new JLabel("Food Menu");  
        headerPanel.add(headerLabel);  

        JButton proceedToOrderButton = new JButton("Choose Order Type");  
        proceedToOrderButton.addActionListener(e -> {  
            new OrderTypeSelectionPage(customerName, selectedItems, totalCost).setVisible(true);  
            dispose();  
        });  
        headerPanel.add(proceedToOrderButton);  

        add(headerPanel);  

        addMenuItem("Grilled Chicken", "Chicken, Spices, Herbs", 12.99);  
        addMenuItem("Kebab", "Ground Meat, Spices, Onion", 10.99);  
        addMenuItem("Crispy", "Crispy Chicken, Bread Crumbs", 9.99);  
        addMenuItem("Pasta", "Pasta, Tomato Sauce, Cheese", 11.99);  
        addMenuItem("Orange Juice", "Fresh Oranges", 2.99);  
        addMenuItem("Pepsi", "Carbonated Beverage", 1.99);  
    }  

    private void addMenuItem(String itemName, String ingredients, double price) {  
        JPanel panel = new JPanel(new FlowLayout());  
        panel.add(new JLabel(itemName + " - $" + price));  

        JButton ingredientsButton = new JButton("Ingredients");  
        ingredientsButton.addActionListener(new IngredientsAction(ingredients, price));  
        panel.add(ingredientsButton);  

        JButton orderButton = new JButton("Order");  
        orderButton.addActionListener(new OrderAction(itemName, price));  
        panel.add(orderButton);  

        add(panel);  
    }  

    private class IngredientsAction implements ActionListener {  
        private String ingredients;  
        private double price;  

        public IngredientsAction(String ingredients, double price) {  
            this.ingredients = ingredients;  
            this.price = price;  
        }  

        @Override  
        public void actionPerformed(ActionEvent e) {  
            JOptionPane.showMessageDialog(FoodMenuPage.this, "Ingredients: " + ingredients + "\nPrice: $" + price, "Meal Details", JOptionPane.INFORMATION_MESSAGE);  
        }  
    }  

    private class OrderAction implements ActionListener {  
        private String itemName;  
        private double price;  

        public OrderAction(String itemName, double price) {  
            this.itemName = itemName;  
            this.price = price;  
        }  

        @Override  
        public void actionPerformed(ActionEvent e) {  
            selectedItems.add(itemName);  
            totalCost += price;  
            JOptionPane.showMessageDialog(FoodMenuPage.this, "Added " + itemName + " to your order.");  
        }  
    }  

    public static void main(String[] args) {  
        SwingUtilities.invokeLater(() -> new FoodMenuPage("Sample Customer").setVisible(true));  
    }  
    protected JButton createBackButton(JFrame currentFrame, JFrame previousFrame) {  
        JButton backButton = new JButton("< Back");  
        backButton.addActionListener(e -> {  
            previousFrame.setVisible(true);  
            currentFrame.dispose();  
        });  
        return backButton;  
    }
}