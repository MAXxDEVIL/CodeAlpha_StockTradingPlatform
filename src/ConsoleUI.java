import java.util.List;
import java.util.Scanner;

public class ConsoleUI {
    private final User user;
    private final MarketDataService marketService;
    private final TradingService tradingService;
    private final PortfolioService portfolioService;
    private final Scanner scanner;

    public ConsoleUI() {
        this.user = new User("Trader", 10000.00);
        this.marketService = new MarketDataService();
        this.tradingService = new TradingService();
        this.portfolioService = new PortfolioService();
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        boolean running = true;

        while (running) {
            showHeader();
            int choice = getMenuChoice();

            switch (choice) {
                case 1 -> viewMarketData();
                case 2 -> buyStocks();
                case 3 -> sellStocks();
                case 4 -> portfolioService.displayPortfolio(user);
                case 5 -> portfolioService.displayTransactionHistory(user);
                case 6 -> simulateMarketTick();
                case 7 -> {
                    System.out.println("\n  Thank you for trading! Goodbye.\n");
                    running = false;
                }
                default -> System.out.println("\n  Invalid choice. Please try again.\n");
            }
        }

        scanner.close();
    }

    private void showHeader() {
        System.out.println("========================================");
        System.out.println("     INDIAN STOCK TRADING PLATFORM");
        System.out.println("========================================");
        System.out.printf("  Cash Balance: \u20B9%,.2f%n", user.getPortfolio().getCashBalance());
        System.out.println("----------------------------------------");
        System.out.println("  1. View Market Data");
        System.out.println("  2. Buy Stocks");
        System.out.println("  3. Sell Stocks");
        System.out.println("  4. View Portfolio");
        System.out.println("  5. View Transaction History");
        System.out.println("  6. Simulate Market Tick");
        System.out.println("  7. Exit");
        System.out.println("----------------------------------------");
        System.out.print("  Enter choice: ");
    }

    private int getMenuChoice() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private void viewMarketData() {
        List<Stock> stocks = marketService.getStocks();

        System.out.println("\n========================================");
        System.out.println("          MARKET DATA (NSE)");
        System.out.println("========================================");
        System.out.printf("  %-12s %-35s %-14s %s%n", "SYMBOL", "COMPANY", "PRICE", "CHANGE");
        System.out.println("  " + "-".repeat(80));

        for (Stock stock : stocks) {
            String arrow = stock.getChangePercent() >= 0 ? "\u001B[32m\u25B2\u001B[0m" : "\u001B[31m\u25BC\u001B[0m";
            System.out.printf("  %-12s %-35s %-14s %s%.2f%%%n",
                    stock.getSymbol(),
                    stock.getName(),
                    stock.formatPrice(stock.getCurrentPrice()),
                    arrow,
                    Math.abs(stock.getChangePercent()));
        }

        System.out.println("========================================\n");
    }

    private void buyStocks() {
        viewMarketData();
        System.out.print("  Enter stock symbol to buy: ");
        String symbol = scanner.nextLine().trim();

        Stock stock = marketService.getStockBySymbol(symbol);
        if (stock == null) {
            System.out.println("\n  Stock not found!\n");
            return;
        }

        System.out.printf("  %s - %s @ %s%n", stock.getSymbol(), stock.getName(), stock.formatPrice(stock.getCurrentPrice()));
        System.out.printf("  You hold: %d shares%n", user.getPortfolio().getQuantity(stock));
        System.out.print("  Enter quantity to buy: ");

        try {
            int quantity = Integer.parseInt(scanner.nextLine().trim());
            String result = tradingService.buyStock(user, stock, quantity);
            System.out.println("\n  " + result + "\n");
        } catch (NumberFormatException e) {
            System.out.println("\n  Invalid quantity!\n");
        }
    }

    private void sellStocks() {
        var holdings = user.getPortfolio().getHoldings();

        System.out.println("\n========================================");
        System.out.println("          YOUR HOLDINGS");
        System.out.println("========================================");

        if (holdings.isEmpty()) {
            System.out.println("  No stocks to sell.");
            System.out.println("========================================\n");
            return;
        }

        for (var entry : holdings.entrySet()) {
            Stock stock = entry.getKey();
            int qty = entry.getValue();
            System.out.printf("  %-12s Qty: %-6d @ %s%n", stock.getSymbol(), qty, stock.formatPrice(stock.getCurrentPrice()));
        }

        System.out.println("========================================");
        System.out.print("  Enter stock symbol to sell: ");
        String symbol = scanner.nextLine().trim();

        Stock stock = marketService.getStockBySymbol(symbol);
        if (stock == null || !holdings.containsKey(stock)) {
            System.out.println("\n  Stock not found in portfolio!\n");
            return;
        }

        System.out.printf("  You hold %d shares of %s @ %s%n",
                holdings.get(stock), stock.getSymbol(), stock.formatPrice(stock.getCurrentPrice()));
        System.out.print("  Enter quantity to sell: ");

        try {
            int quantity = Integer.parseInt(scanner.nextLine().trim());
            String result = tradingService.sellStock(user, stock, quantity);
            System.out.println("\n  " + result + "\n");
        } catch (NumberFormatException e) {
            System.out.println("\n  Invalid quantity!\n");
        }
    }

    private void simulateMarketTick() {
        marketService.simulateTick();
        System.out.println("\n  Market tick simulated! Prices updated.\n");
        viewMarketData();
    }
}
