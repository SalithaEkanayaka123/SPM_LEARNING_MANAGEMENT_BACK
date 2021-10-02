package lk.spm.learning.management.model;

public class ChartData {
    private String count;
    private String category;

    public ChartData() {

    }

    public ChartData(String count, String category) {
        this.count = count;
        this.category = category;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
