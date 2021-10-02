package lk.spm.learning.management.model;

public class TutorCountData {
    private String count;
    private String className;

    public TutorCountData(){

    }

    public TutorCountData(String count, String className) {
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
