# Indian Stock Trading Platform

A console-based stock trading simulator featuring 15 real Indian (NSE) stocks with simulated market prices, portfolio tracking, and transaction history.

## Features

- 15 real Indian stocks (RELIANCE, TCS, HDFCBANK, INFY, and more)
- Buy and sell stocks with virtual cash (₹10,000 starting balance)
- Track portfolio with average buy price and profit/loss
- Simulated market with random price fluctuations (±2% per tick)
- Transaction history with timestamps
- All prices displayed in INR (₹)

## Project Structure

```
src/
├── Main.java              — Entry point
├── Stock.java             — Stock model (symbol, name, price, history)
├── Transaction.java       — Buy/sell trade record
├── Portfolio.java         — Holdings, cash, P&L tracking
├── User.java              — User profile
├── MarketDataService.java — 15 Indian stocks, price simulation
├── TradingService.java    — Buy/sell execution logic
├── PortfolioService.java  — Portfolio and history display
└── ConsoleUI.java         — Interactive CLI menu
```

## How to Compile and Run (Terminal)

### Step-by-step

```bash
# 1. Navigate to the project folder
cd CodeAlpha_StockTradingPlatform

# 2. Compile all Java files
mkdir -p out
javac -d out src/*.java

# 3. Run the application
java -cp out Main
```

### One-liner

```bash
javac -d out src/*.java && java -cp out Main
```

> Requires Java 14 or higher (uses switch expressions).

## How to Play

| Option | Action                  | Description                                      |
|--------|-------------------------|--------------------------------------------------|
| 1      | View Market Data        | See all 15 stocks with current prices and change |
| 2      | Buy Stocks              | Purchase shares using your cash balance          |
| 3      | Sell Stocks             | Sell shares from your portfolio                  |
| 4      | View Portfolio          | See holdings, avg buy price, current price, P&L  |
| 5      | View Transaction History| See all past buy/sell transactions               |
| 6      | Simulate Market Tick    | Update all stock prices randomly                 |
| 7      | Exit                    | Quit the application                             |

### Quick Example Flow

1. Press `6` to simulate a market tick (prices change)
2. Press `1` to view updated market data
3. Press `2`, type `RELIANCE`, enter `2` to buy 2 shares
4. Press `4` to view your portfolio and P&L
5. Press `6` a few more times, then `4` again to see P&L change

## Indian Stocks Included

| Symbol       | Company                              | Base Price |
|--------------|--------------------------------------|------------|
| RELIANCE     | Reliance Industries Ltd              | ₹2,950     |
| TCS          | Tata Consultancy Services Ltd        | ₹3,880     |
| HDFCBANK     | HDFC Bank Ltd                        | ₹1,640     |
| INFY         | Infosys Ltd                          | ₹1,480     |
| ICICIBANK    | ICICI Bank Ltd                       | ₹1,050     |
| HINDUNILVR   | Hindustan Unilever Ltd               | ₹2,520     |
| SBIN         | State Bank of India                  | ₹780       |
| BHARTIARTL   | Bharti Airtel Ltd                    | ₹1,350     |
| ITC          | ITC Ltd                              | ₹460       |
| LT           | Larsen & Toubro Ltd                  | ₹3,400     |
| WIPRO        | Wipro Ltd                            | ₹450       |
| TATAMOTORS   | Tata Motors Ltd                      | ₹680       |
| ADANIENT     | Adani Enterprises Ltd                | ₹2,800     |
| SUNPHARMA    | Sun Pharmaceutical Industries Ltd    | ₹1,200     |
| MARUTI       | Maruti Suzuki India Ltd              | ₹11,500    |

## Technical Details

- **OOP Design**: 4 model classes, 3 service classes, 1 UI class
- **Simulation**: Random walk model — each tick applies ±0-2% change to all stocks
- **Starting Capital**: ₹10,000 virtual cash
- **Persistence**: In-memory only (data resets on exit)
- **Dependencies**: None (pure Java, no external libraries)

