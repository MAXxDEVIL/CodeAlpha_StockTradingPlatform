import java.util.ArrayList;
import java.util.List;

public class User {
    private final String name;
    private final Portfolio portfolio;
    private final List<Transaction> transactionHistory;

    public User(String name, double initialBalance) {
        this.name = name;
        this.portfolio = new Portfolio(initialBalance);
        this.transactionHistory = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public Portfolio getPortfolio() {
        return portfolio;
    }
    public List<Transaction> getTransactionHistory() {
        return new ArrayList<>(transactionHistory);
    }

    public void addTransaction(Transaction transaction) {
        transactionHistory.add(transaction);
    }
}
