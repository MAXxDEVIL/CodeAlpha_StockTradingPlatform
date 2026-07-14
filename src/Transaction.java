import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    public enum Type { BUY, SELL }

    private final Type type;
    private final Stock stock;
    private final int quantity;
    private final double price;
    private final double totalAmount;
    private final LocalDateTime timestamp;

    public Transaction(Type type, Stock stock, int quantity, double price) {
        this.type = type;
        this.stock = stock;
        this.quantity = quantity;
        this.price = price;
        this.totalAmount = quantity * price;
        this.timestamp = LocalDateTime.now();
    }

    public Type getType() { return type; }
    public Stock getStock() { return stock; }
    public int getQuantity() { return quantity; }
    public double getPrice() { return price; }
    public double getTotalAmount() { return totalAmount; }
    public LocalDateTime getTimestamp() { return timestamp; }

    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return String.format("%-4s | %-12s | Qty: %-4d | Price: \u20B9%,.2f | Total: \u20B9%,.2f | %s",
                type, stock.getSymbol(), quantity, price, totalAmount, timestamp.format(fmt));
    }
}
