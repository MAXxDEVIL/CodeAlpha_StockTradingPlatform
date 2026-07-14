public class TradingService {
    public String buyStock(User user, Stock stock, int quantity) {
        if (quantity <= 0) {
            return "Quantity must be greater than 0.";
        }

        double totalCost = stock.getCurrentPrice() * quantity;
        Portfolio portfolio = user.getPortfolio();

        if (totalCost > portfolio.getCashBalance()) {
            return String.format("Insufficient funds! Required: \u20B9%,.2f | Available: \u20B9%,.2f",
                    totalCost, portfolio.getCashBalance());
        }

        if (!portfolio.deductCash(totalCost)) {
            return "Transaction failed \u2014 could not deduct cash.";
        }

        portfolio.addStock(stock, quantity, stock.getCurrentPrice());
        Transaction txn = new Transaction(Transaction.Type.BUY, stock, quantity, stock.getCurrentPrice());
        user.addTransaction(txn);

        return String.format("BUY successful: %d shares of %s at %s | Total: \u20B9%,.2f",
                quantity, stock.getSymbol(), stock.formatPrice(stock.getCurrentPrice()), totalCost);
    }

    public String sellStock(User user, Stock stock, int quantity) {
        if (quantity <= 0) {
            return "Quantity must be greater than 0.";
        }

        Portfolio portfolio = user.getPortfolio();
        int held = portfolio.getQuantity(stock);

        if (quantity > held) {
            return String.format("Insufficient shares! You hold %d shares of %s.", held, stock.getSymbol());
        }

        double totalRevenue = stock.getCurrentPrice() * quantity;
        portfolio.addCash(totalRevenue);
        portfolio.removeStock(stock, quantity);
        Transaction txn = new Transaction(Transaction.Type.SELL, stock, quantity, stock.getCurrentPrice());
        user.addTransaction(txn);

        return String.format("SELL successful: %d shares of %s at %s | Total: \u20B9%,.2f",
                quantity, stock.getSymbol(), stock.formatPrice(stock.getCurrentPrice()), totalRevenue);
    }
}
