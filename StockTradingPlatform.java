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
}