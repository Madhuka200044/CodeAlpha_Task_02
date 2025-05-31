# CodeAlpha_Task_02

# Stock Trading Platform - Java Application

## Overview

This is a Java-based stock trading platform simulation that allows users to:
- Create accounts and log in
- View real-time (simulated) market data
- Buy and sell stocks
- Track their portfolio holdings
- View transaction history

The application demonstrates object-oriented programming principles in Java, including classes, collections, and user input handling.

## Features

- **User Authentication**: Account creation and login system
- **Market Simulation**: Random price fluctuations for realistic trading
- **Portfolio Management**: Track stocks owned and their current values
- **Transaction History**: Detailed record of all buys and sells
- **Interactive Console Interface**: Easy-to-use menu system

## Classes

1. **StockTradingPlatform**: Main class with the application entry point
2. **Stock**: Represents a stock with symbol, company name, and price
3. **Portfolio**: Tracks a user's stock holdings and transactions
4. **Transaction**: Records details of buy/sell operations

## How to Run

1. Ensure you have Java JDK installed
2. Compile the program: `javac StockTradingPlatform.java`
3. Run the program: `java StockTradingPlatform`

## Usage

1. Create an account or log in
2. Use the menu to:
   - View current market data
   - Buy or sell stocks
   - Check your portfolio value
   - Review transaction history
3. Log out when finished

## Implementation Details

- Uses Java Collections Framework (HashMap, List)
- Implements formatted output for financial data
- Includes date/time tracking for transactions
- Simulates market fluctuations with random price changes

## Sample Screens

```
=== Stock Trading Platform ===
1. Login
2. Create Account
3. Exit
```

```
=== Current Market Data ===
Symbol Company              Price      Change     
AAPL   Apple Inc.          $150.25     +1.25      
GOOGL  Alphabet Inc.       $2498.75    -1.25      
MSFT   Microsoft Corp.     $301.50     +1.50
```

## Future Enhancements

- Add user balances and cash management
- Implement more sophisticated market simulation
- Add stock price history charts
- Support for persistent data storage

## Author
D.M.Weerathunga


## License

This project is open source and available under the MIT License.


