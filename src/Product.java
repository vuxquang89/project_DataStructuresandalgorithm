import java.util.Formatter;

public class Product {
    private String id;
    private String title;
    private int quality;
    private double price;

    public Product(String id, String title, int quality, double price) {
        this.id = id;
        this.title = title;
        this.quality = quality;
        this.price = price;
    }

    public Product(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String string(){
        return getId() + " " + getTitle() + " " + getQuality() + " " + getPrice() + " ";
    }

    @Override
    public String toString() {
        Formatter formatter = new Formatter();

        formatter.format("|%6s|%17s|%10s|%15s|", getId(), getTitle(), getQuality(), getPrice());
        return formatter + "";
    }
}
