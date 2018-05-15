package intonationeenglish.demo.intonationenlish;

public class Test {
    private  int id ;
    private  String test;
    private  int state;

    public Test(int id, String test, int state) {
        this.id = id;
        this.test = test;
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
