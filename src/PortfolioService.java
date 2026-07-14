import java.util.List;
import java.util.Map;

public class PortfolioService {

    public void displayPortfolio(User user) {
        Portfolio portfolio = user.getPortfolio();
        Map<Stock, Integer> holdings = portfolio.getHoldings();

        System.out.println("\n========================================");
        System.out.println("          YOUR PORTFOLIO");
        System.out.println("========================================");
        System.out.printf("  Cash Balance:  \u20B9%,.2f%n", portfolio.getCashBalance());
        System.out.println("----------------------------------------");

        if (holdings.isEmpty()) {
            System.out.println("  No stocks in portfolio.");
        } else {
            System.out.printf("  %-12s %-6s %-14s %-14s %-14s%n",
                    "SYMBOL", "QTY", "AVG BUY", "CUR. PRICE", "P&L");
            System.out.println("  " + "-".repeat(66));

            for (Map.Entry<Stock, Integer> entry : holdings.entrySet()) {
                Stock stock = entry.getKey();
                int qty = entry.getValue();
                double buyPrice = portfolio.getAverageBuyPrice(stock);
                double currentPrice = stock.getCurrentPrice();
                double pnl = (currentPrice - buyPrice) * qty;

                String indicator = pnl >= 0 ? "+" : "";

                System.out.printf("  %-12s %-6d \u20B9%,.2f       \u20B9%,.2f       %s\u20B9%,.2f%n",
                        stock.getSymbol(), qty, buyPrice, currentPrice, indicator, pnl);
            }
        }

        System.out.println("----------------------------------------");
        System.out.printf("  Portfolio Value:  \u20B9%,.2f%n", portfolio.getPortfolioValue());
        System.out.printf("  Total P&L:        \u20B9%,.2f%n", portfolio.getProfitLoss());
        System.out.println("========================================\n");
    }

    public void displayTransactionHistory(User user) {
        List<Transaction> history = user.getTransactionHistory();

        System.out.println("\n========================================");
        System.out.println("       TRANSACTION HISTORY");
        System.out.println("========================================");

        if (history.isEmpty()) {
            System.out.println("  No transactions yet.");
        } else {
            for (Transaction txn : history) {
                System.out.println("  " + txn);
            }
        }

        System.out.println("========================================\n");
    }
}
