import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MarketDataService {
    private final List<Stock> stocks;
    private final Random random;

    public MarketDataService() {
        this.stocks = new ArrayList<>();
        this.random = new Random();
        initializeMarket();
    }

    private void initializeMarket() {
        stocks.add(new Stock("RELIANCE", "Reliance Industries Ltd", 2950.00));
        stocks.add(new Stock("TCS", "Tata Consultancy Services Ltd", 3880.00));
        stocks.add(new Stock("HDFCBANK", "HDFC Bank Ltd", 1640.00));
        stocks.add(new Stock("INFY", "Infosys Ltd", 1480.00));
        stocks.add(new Stock("ICICIBANK", "ICICI Bank Ltd", 1050.00));
        stocks.add(new Stock("HINDUNILVR", "Hindustan Unilever Ltd", 2520.00));
        stocks.add(new Stock("SBIN", "State Bank of India", 780.00));
        stocks.add(new Stock("BHARTIARTL", "Bharti Airtel Ltd", 1350.00));
        stocks.add(new Stock("ITC", "ITC Ltd", 460.00));
        stocks.add(new Stock("LT", "Larsen & Toubro Ltd", 3400.00));
        stocks.add(new Stock("WIPRO", "Wipro Ltd", 450.00));
        stocks.add(new Stock("TATAMOTORS", "Tata Motors Ltd", 680.00));
        stocks.add(new Stock("ADANIENT", "Adani Enterprises Ltd", 2800.00));
        stocks.add(new Stock("SUNPHARMA", "Sun Pharmaceutical Industries Ltd", 1200.00));
        stocks.add(new Stock("MARUTI", "Maruti Suzuki India Ltd", 11500.00));
    }

    public List<Stock> getStocks() {
        return new ArrayList<>(stocks);
    }

    public Stock getStockBySymbol(String symbol) {
        for (Stock stock : stocks) {
            if (stock.getSymbol().equalsIgnoreCase(symbol)) {
                return stock;
            }
        }
        return null;
    }

    public void simulateTick() {
        for (Stock stock : stocks) {
            double changePercent = (random.nextDouble() * 4.0 - 2.0);
            double newPrice = stock.getCurrentPrice() * (1 + changePercent / 100.0);
            stock.updatePrice(newPrice);
        }
    }
}
