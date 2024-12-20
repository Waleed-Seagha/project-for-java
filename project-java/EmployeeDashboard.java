import javax.swing.*;  
import java.awt.*;  
import java.io.BufferedReader;  
import java.io.FileReader;  
import java.io.IOException;  
import java.util.HashMap;  
import java.util.Map;  

public class EmployeeDashboard extends JFrame {  
    private int orderCount;  
    private String mostOrderedMeal;  
    private double dailyRevenue;  
    private String mostFrequentCustomer;  

    public EmployeeDashboard() {  
        setTitle("Employee Dashboard");  
        setSize(400, 300);  
        setDefaultCloseOperation(EXIT_ON_CLOSE);  
        setLayout(new BorderLayout());  

        analyzeOrders();  

        String[] columnNames = {"Order Count", "Most Ordered Meal", "Daily Revenue", "Most Frequent Customer"};  
        String[][] data = {  
            {String.valueOf(orderCount), mostOrderedMeal, String.format("$%.2f", dailyRevenue), mostFrequentCustomer}  
        };  

        JTable table = new JTable(data, columnNames);  
        JScrollPane scrollPane = new JScrollPane(table);  
        table.setFillsViewportHeight(true);  
        add(scrollPane, BorderLayout.CENTER);  
    }  

    private void analyzeOrders() {  
        Map<String, Integer> mealCount = new HashMap<>();  
        Map<String, Double> customerRevenue = new HashMap<>();  
        orderCount = 0;  
        dailyRevenue = 0.0;  

        try (BufferedReader br = new BufferedReader(new FileReader("orders.txt"))) {  
            String line;  
            while ((line = br.readLine()) != null) {  
                if (line.startsWith("Customer: ")) {  
                    String customer = line.substring(10);  
                    String nextLine = br.readLine();  
                    if (nextLine.startsWith("Ordered Items: ")) {  
                        String meal = nextLine.substring(16);  
                        mealCount.put(meal, mealCount.getOrDefault(meal, 0) + 1);  
                    }  
                    nextLine = br.readLine();  
                    if (nextLine.startsWith("Total Cost: $")) {  
                        double cost = Double.parseDouble(nextLine.substring(13));  
                        customerRevenue.put(customer, customerRevenue.getOrDefault(customer, 0.0) + cost);  
                        dailyRevenue += cost;  
                    }  
                    orderCount++;  
                }  
            }  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  

        mostOrderedMeal = mealCount.entrySet().stream()  
                .max(Map.Entry.comparingByValue())  
                .map(Map.Entry::getKey)  
                .orElse("N/A");  

        mostFrequentCustomer = customerRevenue.entrySet().stream()  
                .max(Map.Entry.comparingByValue())  
                .map(Map.Entry::getKey)  
                .orElse("N/A");  
    }  

    public static void main(String[] args) {  
        SwingUtilities.invokeLater(() -> new EmployeeDashboard().setVisible(true));  
    }  
}