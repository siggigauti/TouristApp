package is.siggigauti.touristapp.model;

import java.util.Date;

public class Trip {

    private final int ID;
    private String title;
    private Date startDate;
    private Date endDate;
    private String description;
    private int minCap;
    private int maxCap;
    private Company company;
    private int price;

    public Trip(int ID, String title, Date startDate, Date endDate, String description, int minCap, int maxCap, Company company, int price) {
        this.ID = ID;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.minCap = minCap;
        this.maxCap = maxCap;
        this.company = company;
        this.price = price;
    }

    public int getID() {
        return ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMinCap() {
        return minCap;
    }

    public void setMinCap(int minCap) {
        this.minCap = minCap;
    }

    public int getMaxCap() {
        return maxCap;
    }

    public void setMaxCap(int maxCap) {
        this.maxCap = maxCap;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
