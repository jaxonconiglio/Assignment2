import java.util.StringJoiner;

public class Product {
private static final int NAME_LENGTH = 35;
private static final int DESCRIPTION_LENGTH = 75;
private static final int ID_LENGTH = 6;

private String name;
private String description;
private String id;
private double cost;

public Product() {
}

public Product(String name, String description, String id, double cost) {
    this.name = name;
    this.description = description;
    this.id = id;
    this.cost = cost;
}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    private String padString(String input, int length){
        return String.format("%-" + length + "s" , input);
    }

    private String trimString(String input) {
        return input.trim();
    }

    public String getFormattedRecord() {
        StringJoiner joiner = new StringJoiner(",");
        joiner.add(padString(name, NAME_LENGTH));
        joiner.add(padString(description, DESCRIPTION_LENGTH));
        joiner.add(padString(id,ID_LENGTH));
        joiner.add(Double.toString(cost));
        return joiner.toString();
    }

    public static Product parseRecord(String record){
    String[] fields = record.split(",");
    Product product = new Product();
    product.setName(product.trimString(fields[0]));
    product.setDescription(product.trimString(fields[1]));
    product.setId(product.trimString(fields[2]));
    product.setCost(Double.parseDouble(fields[3]));
    return product;
    }
}