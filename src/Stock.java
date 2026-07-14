import java.util.ArrayList;
import java.util.List;

public class Stock {
    private final String symbol;
    private final String name;
    private double currentPrice;
    private final List<Double> priceHistory;

    public Stock(String symbol, String name, double currentPrice) {
        this.symbol = symbol;
        this.name = name;
        this.currentPrice = currentPrice;
        this.priceHistory = new ArrayList<>();
        this.priceHistory.add(currentPrice);
    }

    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public List<Double> getPriceHistory() {
        return new ArrayList<>(priceHistory);
    }

    public void updatePrice(double newPrice) {
        this.currentPrice = Math.max(1.0, newPrice);
        this.priceHistory.add(this.currentPrice);
    }

    public double getChangePercent() {
        if (priceHistory.size() < 2) return 0.0;
        double previous = priceHistory.get(priceHistory.size() - 2);
        return ((currentPrice - previous) / previous) * 100;
    }

    public double getOverallChangePercent() {
        if (priceHistory.isEmpty()) return 0.0;
        double first = priceHistory.get(0);
        return ((currentPrice - first) / first) * 100;
    }

    public String formatPrice(double price) {
        return String.format("\u20B9%,.2f", price);
    }

    @Override
    public String toString() {
        String arrow = getChangePercent() >= 0 ? "\u25B2" : "\u25BC";
        return String.format("%-12s %-35s %s  (%s%.2f%%)",
                symbol, name, formatPrice(currentPrice), arrow, Math.abs(getChangePercent()));
    }
}
