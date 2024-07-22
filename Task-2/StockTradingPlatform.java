import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Stock {
    private String symbol;
    private String name;
    private double price;

    public Stock(String symbol, String name, double price) {
        this.symbol = symbol;
        this.name = name;
        this.price = price;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("%s (%s): ₹%.2f", name, symbol, price);
    }
}

class Portfolio {
    private Map<Stock, Integer> holdings;

    public Portfolio() {
        holdings = new HashMap<>();
    }

    public void buyStock(Stock stock, int quantity) {
        holdings.put(stock, holdings.getOrDefault(stock, 0) + quantity);
    }

    public void sellStock(Stock stock, int quantity) {
        if (holdings.containsKey(stock)) {
            int currentQuantity = holdings.get(stock);
            if (currentQuantity >= quantity) {
                holdings.put(stock, currentQuantity - quantity);
                if (holdings.get(stock) == 0) {
                    holdings.remove(stock);
                }
            } else {
                System.out.println("Not enough stock to sell");
            }
        } else {
            System.out.println("Stock not in portfolio");
        }
    }

    public double getPortfolioValue() {
        return holdings.entrySet().stream().mapToDouble(entry -> entry.getKey().getPrice() * entry.getValue()).sum();
    }

    public void displayPortfolio() {
        if (holdings.isEmpty()) {
            System.out.println("No stocks in portfolio");
        } else {
            holdings.forEach((stock, quantity) -> System.out.println(stock + " - Quantity: " + quantity));
            System.out.printf("Total Portfolio Value: ₹%.2f%n", getPortfolioValue());
        }
    }
}

class TradingPlatform {
    private ArrayList<Stock> market;
    private Portfolio portfolio;

    public TradingPlatform() {
        market = new ArrayList<>();
        portfolio = new Portfolio();
        initializeMarket();
    }

    private void initializeMarket() {
        market.add(new Stock("REL", "Reliance Industries", 2300.00));
        market.add(new Stock("TCS", "Tata Consultancy Services", 3200.00));
        market.add(new Stock("INF", "Infosys", 1500.00));
    }

    public void displayMarket() {
        System.out.println("Market Data:");
        market.forEach(System.out::println);
    }

    public Stock getStock(String symbol) {
        return market.stream().filter(stock -> stock.getSymbol().equalsIgnoreCase(symbol)).findFirst().orElse(null);
    }

    public void simulateTrading() {
        Scanner scanner = new Scanner(System.in);
        String command;
        do {
            System.out.println("\nCommands: VIEW, BUY, SELL, PORTFOLIO, EXIT");
            System.out.print("Enter Command: ");
            command = scanner.nextLine().toUpperCase();

            switch (command) {
                case "VIEW":
                    displayMarket();
                    break;
                case "BUY":
                    System.out.print("Enter stock symbol to buy: ");
                    String buySymbol = scanner.nextLine().toUpperCase();
                    Stock buyStock = getStock(buySymbol);
                    if (buyStock != null) {
                        System.out.print("Enter quantity to buy: ");
                        int buyQuantity = scanner.nextInt();
                        portfolio.buyStock(buyStock, buyQuantity);
                        System.out.println("Bought " + buyQuantity + " of " + buyStock.getName());
                    } else {
                        System.out.println("Stock not found");
                    }
                    scanner.nextLine(); // consume newline
                    break;
                case "SELL":
                    System.out.print("Enter stock symbol to sell: ");
                    String sellSymbol = scanner.nextLine().toUpperCase();
                    Stock sellStock = getStock(sellSymbol);
                    if (sellStock != null) {
                        System.out.print("Enter quantity to sell: ");
                        int sellQuantity = scanner.nextInt();
                        portfolio.sellStock(sellStock, sellQuantity);
                        System.out.println("Sold " + sellQuantity + " of " + sellStock.getName());
                    } else {
                        System.out.println("Stock not found");
                    }
                    scanner.nextLine(); // consume newline
                    break;
                case "PORTFOLIO":
                    portfolio.displayPortfolio();
                    break;
                case "EXIT":
                    System.out.println("Exiting platform");
                    break;
                default:
                    System.out.println("Invalid command");
            }
        } while (!command.equals("EXIT"));
        scanner.close();
    }
}

public class StockTradingPlatform {
    public static void main(String[] args) {
        TradingPlatform platform = new TradingPlatform();
        platform.simulateTrading();
    }
}
