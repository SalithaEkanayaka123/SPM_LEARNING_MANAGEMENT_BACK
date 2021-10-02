package lk.spm.learning.management.model;

public class AnnouncementCountData {
    private String count;
    private String className;

    public AnnouncementCountData() {

    }

    public AnnouncementCountData(String count, String className) {
        this.count = count;
        this.className = className;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
