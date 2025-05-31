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
	
	private static void displayMainMenu() 
	{
        while (currentUser != null) 
		{
            System.out.println("\n=== Main Menu ===");
            System.out.println("1. View Market Data");
            System.out.println("2. Buy Stocks");
            System.out.println("3. Sell Stocks");
            System.out.println("4. View Portfolio");
            System.out.println("5. View Transaction History");
            System.out.println("6. Logout");
            System.out.print("Enter choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) 
			{
                case 1 -> viewMarketData();
                case 2 -> buyStocks();
                case 3 -> sellStocks();
                case 4 -> viewPortfolio();
                case 5 -> viewTransactionHistory();
                case 6 -> 
				{
                    currentUser = null;
                    System.out.println("Logged out successfully.");
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }
    
    private static void viewMarketData() 
	{
        System.out.println("\n=== Current Market Data ===");
        System.out.printf("%-6s %-20s %-10s %-10s%n", "Symbol", "Company", "Price", "Change");
        
        // Simulate price changes
        for (Stock stock : marketStocks.values()) 
		{
            double change = (random.nextDouble() - 0.5) * 10; // Random change between -5 and +5
            stock.updatePrice(change);
            
            String changeStr = change >= 0 ? "+" + df.format(change) : df.format(change);
            System.out.printf("%-6s %-20s $%-9s %-10s%n", 
                stock.getSymbol(), 
                stock.getCompanyName(), 
                df.format(stock.getCurrentPrice()),
                changeStr);
        }
    }
	
	    private static void buyStocks() 
		{
        viewMarketData();
        System.out.print("\nEnter stock symbol to buy: ");
        String symbol = scanner.nextLine().toUpperCase();
        
        if (!marketStocks.containsKey(symbol)) 
		{
            System.out.println("Invalid stock symbol.");
            return;
        }
        
        Stock stock = marketStocks.get(symbol);
        System.out.printf("Current price of %s (%s): $%s%n", 
            stock.getSymbol(), stock.getCompanyName(), df.format(stock.getCurrentPrice()));
        
        System.out.print("Enter quantity to buy: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();
        
        if (quantity <= 0) 
		{
            System.out.println("Quantity must be positive.");
            return;
        }
        
        double totalCost = stock.getCurrentPrice() * quantity;
        System.out.printf("Total cost: $%s%n", df.format(totalCost));
        System.out.print("Confirm purchase (Y/N)? ");
        String confirm = scanner.nextLine();
        
        if (confirm.equalsIgnoreCase("Y")) 
		{
            Portfolio portfolio = userPortfolios.get(currentUser);
            portfolio.addTransaction(new Transaction(
                "BUY", symbol, quantity, stock.getCurrentPrice(), LocalDateTime.now()));
            
            System.out.printf("Successfully purchased %d shares of %s.%n", quantity, symbol);
        } 
		else 
		{
            System.out.println("Purchase cancelled.");
        }
    }
    
    private static void sellStocks() 
	{
        Portfolio portfolio = userPortfolios.get(currentUser);
        if (portfolio.getHoldings().isEmpty()) 
		{
            System.out.println("You don't own any stocks to sell.");
            return;
        }
        
        System.out.println("\nYour Current Holdings:");
        portfolio.displayHoldings();
        
        System.out.print("\nEnter stock symbol to sell: ");
        String symbol = scanner.nextLine().toUpperCase();
        
        if (!marketStocks.containsKey(symbol)) 
		{
            System.out.println("Invalid stock symbol.");
            return;
        }
        
        if (!portfolio.hasStock(symbol)) 
		{
            System.out.printf("You don't own any shares of %s.%n", symbol);
            return;
        }
        
        Stock stock = marketStocks.get(symbol);
        System.out.printf("Current price of %s (%s): $%s%n", 
            stock.getSymbol(), stock.getCompanyName(), df.format(stock.getCurrentPrice()));
        
        System.out.print("Enter quantity to sell: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();
        
        if (quantity <= 0) 
		{
            System.out.println("Quantity must be positive.");
            return;
        }
        
        if (quantity > portfolio.getSharesOwned(symbol)) 
		{
            System.out.printf("You only own %d shares of %s.%n", 
                portfolio.getSharesOwned(symbol), symbol);
            return;
        }
        
        double totalValue = stock.getCurrentPrice() * quantity;
        System.out.printf("Total value: $%s%n", df.format(totalValue));
        System.out.print("Confirm sale (Y/N)? ");
        String confirm = scanner.nextLine();
        
        if (confirm.equalsIgnoreCase("Y")) 
		{
            portfolio.addTransaction(new Transaction(
                "SELL", symbol, quantity, stock.getCurrentPrice(), LocalDateTime.now()));
            
            System.out.printf("Successfully sold %d shares of %s.%n", quantity, symbol);
        } 
		else 
		{
            System.out.println("Sale cancelled.");
        }
    }
	private static void viewPortfolio() {
        Portfolio portfolio = userPortfolios.get(currentUser);
        portfolio.displayHoldings();
        portfolio.displayPortfolioValue(marketStocks);
    }
    
    private static void viewTransactionHistory() {
        userPortfolios.get(currentUser).displayTransactionHistory();
    }
}

class Stock 
{
    private String symbol;
    private String companyName;
    private double currentPrice;
    
    public Stock(String symbol, String companyName, double initialPrice) 
	{
        this.symbol = symbol;
        this.companyName = companyName;
        this.currentPrice = initialPrice;
    }
    
    public String getSymbol() 
	{ 
		return symbol; 
	}
    public String getCompanyName() 
	{ 
		return companyName; 
	}
    public double getCurrentPrice() 
	{ 
		return currentPrice; 
	}
    
    public void updatePrice(double change) 
	{
        this.currentPrice += change;
        if (this.currentPrice < 0) this.currentPrice = 0.01; // Prevent negative prices
    }
}

class Portfolio 
{
    private String username;
    private Map<String, Integer> holdings; // Stock symbol -> quantity
    private List<Transaction> transactionHistory;
    
    public Portfolio(String username) 
	{
        this.username = username;
        this.holdings = new HashMap<>();
        this.transactionHistory = new ArrayList<>();
    }
    
    public void addTransaction(Transaction transaction) 
	{
        String symbol = transaction.getStockSymbol();
        int quantity = transaction.getQuantity();
        
        if (transaction.getType().equals("BUY")) 
		{
            holdings.put(symbol, holdings.getOrDefault(symbol, 0) + quantity);
        } 
		else if (transaction.getType().equals("SELL")) 
		{
            holdings.put(symbol, holdings.get(symbol) - quantity);
            if (holdings.get(symbol) == 0) 
			{
                holdings.remove(symbol);
            }
        }
        
        transactionHistory.add(transaction);
    }
    
    public boolean hasStock(String symbol) 
	{
        return holdings.containsKey(symbol);
    }
    
    public int getSharesOwned(String symbol) 
	{
        return holdings.getOrDefault(symbol, 0);
    }
    
    public Map<String, Integer> getHoldings() 
	{
        return holdings;
    }
    
    public void displayHoldings() 
	{
        if (holdings.isEmpty()) 
		{
            System.out.println("You don't own any stocks.");
            return;
        }
        
        System.out.printf("%-6s %-10s%n", "Symbol", "Quantity");
        System.out.println("--------------");
        holdings.forEach((symbol, quantity) -> 
        System.out.printf("%-6s %-10d%n", symbol, quantity));
    }
	public void displayPortfolioValue(Map<String, Stock> marketStocks) 
	{
        if (holdings.isEmpty()) 
		{
            System.out.println("Portfolio value: $0.00");
            return;
        }
        
        double totalValue = 0;
        System.out.println("\nPortfolio Valuation:");
        System.out.printf("%-6s %-10s %-10s %-10s%n", "Symbol", "Quantity", "Price", "Value");
        System.out.println("----------------------------------");
        
        for (Map.Entry<String, Integer> entry : holdings.entrySet()) 
		{
            String symbol = entry.getKey();
            int quantity = entry.getValue();
            double price = marketStocks.get(symbol).getCurrentPrice();
            double value = quantity * price;
            totalValue += value;
            
            System.out.printf("%-6s %-10d $%-9.2f $%-9.2f%n", 
                symbol, quantity, price, value);
        }
        
        System.out.println("----------------------------------");
        System.out.printf("Total Portfolio Value: $%.2f%n", totalValue);
    }
    
    public void displayTransactionHistory() 
	{
        if (transactionHistory.isEmpty()) 
		{
            System.out.println("No transactions yet.");
            return;
        }
        
        System.out.println("\nTransaction History:");
        System.out.printf("%-10s %-6s %-8s %-10s %-10s %-20s%n", 
            "Type", "Symbol", "Quantity", "Price", "Amount", "Date/Time");
        System.out.println("------------------------------------------------------------");
        
        for (Transaction t : transactionHistory) 
		{
            double amount = t.getPrice() * t.getQuantity();
            String dateTime = t.getDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            
            System.out.printf("%-10s %-6s %-8d $%-9.2f $%-9.2f %-20s%n", 
                t.getType(), t.getStockSymbol(), t.getQuantity(), 
                t.getPrice(), amount, dateTime);
        }
    }
}

class Transaction 
{
    private String type; // BUY or SELL
    private String stockSymbol;
    private int quantity;
    private double price;
    private LocalDateTime dateTime;
    
    public Transaction(String type, String stockSymbol, int quantity, double price, LocalDateTime dateTime) 
	{
        this.type = type;
        this.stockSymbol = stockSymbol;
        this.quantity = quantity;
        this.price = price;
        this.dateTime = dateTime;
    }
    
    public String getType() { return type; }
    public String getStockSymbol() { return stockSymbol; }
    public int getQuantity() { return quantity; }
    public double getPrice() { return price; }
    public LocalDateTime getDateTime() { return dateTime; }
    
}