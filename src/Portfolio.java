import java.util.HashMap;
import java.util.Map;

public class Portfolio {
    private final double initialCash;
    private double cashBalance;
    private final Map<Stock, Integer> holdings;
    private final Map<Stock, Double> averageBuyPrice;

    public Portfolio(double initialCash) {
        this.initialCash = initialCash;
        this.cashBalance = initialCash;
        this.holdings = new HashMap<>();
        this.averageBuyPrice = new HashMap<>();
    }

    public double getCashBalance() {
        return cashBalance;
    }

    public Map<Stock, Integer> getHoldings() {
        return new HashMap<>(holdings);
    }

    public int getQuantity(Stock stock) {
        return holdings.getOrDefault(stock, 0);
    }

    public void addCash(double amount) {
        this.cashBalance += amount;
    }

    public boolean deductCash(double amount) {
        if (amount > cashBalance) return false;
        this.cashBalance -= amount;
        return true;
    }

    public double getAverageBuyPrice(Stock stock) {
        return averageBuyPrice.getOrDefault(stock, 0.0);
    }

    public void addStock(Stock stock, int quantity, double buyPrice) {
        int currentQty = getQuantity(stock);
        if (currentQty == 0) {
            holdings.put(stock, quantity);
            averageBuyPrice.put(stock, buyPrice);
        } else {
            double oldAvg = averageBuyPrice.get(stock);
            int newQty = currentQty + quantity;
            double newAvg = ((oldAvg * currentQty) + (buyPrice * quantity)) / newQty;
            holdings.put(stock, newQty);
            averageBuyPrice.put(stock, newAvg);
        }
    }

    public boolean removeStock(Stock stock, int quantity) {
        int current = getQuantity(stock);
        if (quantity > current) return false;
        if (quantity == current) {
            holdings.remove(stock);
            averageBuyPrice.remove(stock);
        } else {
            holdings.put(stock, current - quantity);
        }
        return true;
    }

    public double getPortfolioValue() {
        double total = cashBalance;
        for (Map.Entry<Stock, Integer> entry : holdings.entrySet()) {
            total += entry.getKey().getCurrentPrice() * entry.getValue();
        }
        return total;
    }

    public double getHoldingsCostBasis() {
        double total = 0;
        for (Map.Entry<Stock, Integer> entry : holdings.entrySet()) {
            total += averageBuyPrice.getOrDefault(entry.getKey(), 0.0) * entry.getValue();
        }
        return total;
    }

    public double getProfitLoss() {
        return getPortfolioValue() - initialCash;
    }
}
