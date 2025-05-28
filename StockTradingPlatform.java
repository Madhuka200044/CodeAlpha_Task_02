import java.util.*;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StockTradingPlatform 
{
	private static final DecimalFormat df = new DecimalFormat("0.00");
    private static final Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();
    
    private static Map<String, Stock> marketStocks = new HashMap<>();
    private static Map<String, Portfolio> userPortfolios = new HashMap<>();
    private static String currentUser = null;
	
	public static void main(String[] args) 
	{
        initializeMarket();
        displayWelcomeScreen();
    }
	private static void initializeMarket() 
	{
        // Initialize with some sample stocks
        marketStocks.put("AAPL", new Stock("AAPL", "Apple Inc.", 150.00));
        marketStocks.put("GOOGL", new Stock("GOOGL", "Alphabet Inc.", 2500.00));
        marketStocks.put("MSFT", new Stock("MSFT", "Microsoft Corp.", 300.00));
        marketStocks.put("AMZN", new Stock("AMZN", "Amazon.com Inc.", 3300.00));
        marketStocks.put("TSLA", new Stock("TSLA", "Tesla Inc.", 700.00));
        marketStocks.put("NFLX", new Stock("NFLX", "Netflix Inc.", 500.00));
    }
	
	private static void displayWelcomeScreen() 
	{
        while (true) 
		{
            System.out.println("\n=== Stock Trading Platform ===");
            System.out.println("1. Login");
            System.out.println("2. Create Account");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) 
			{
                case 1 -> login();
                case 2 -> createAccount();
                case 3 -> 
				{
                    System.out.println("Exiting platform. Goodbye!");
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }
	
	private static void login() 
	{
        System.out.print("\nEnter username: ");
        String username = scanner.nextLine();
        
        if (userPortfolios.containsKey(username)) 
		{
            currentUser = username;
            System.out.println("Login successful!");
            displayMainMenu();
        } 
		else 
		{
            System.out.println("User not found. Please create an account.");
        }
    }
	private static void createAccount() 
	{
        System.out.print("\nEnter new username: ");
        String username = scanner.nextLine();
        
        if (userPortfolios.containsKey(username)) 
		{
            System.out.println("Username already exists. Please choose another.");
        } 
		else 
		{
            userPortfolios.put(username, new Portfolio(username));
            System.out.println("Account created successfully! Please login.");
        }
    }
}