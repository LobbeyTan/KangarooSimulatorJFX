package kangaroo.simulation.FX;

public class KangarooData {
    private Integer num;
    private String gender;
    private Integer storage;
    private Integer position;

    public KangarooData(int num, String gender, int storage, int position) {
        this.num = num;
        this.gender = gender;
        this.storage = storage;
        this.position = position;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setStorage(int storage) {
        this.storage = storage;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getNum() {
        return num;
    }

    public String getGender() {
        return gender;
    }

    public int getStorage() {
        return storage;
    }

    public int getPosition() {
        return position;
    }
    
    public String export() {
        return String.format("%d %d %s", position, storage, gender);
    }
    
    @Override
    public String toString() {
        return String.format("Number: %d Gender: %s Storage: %d position: Point %d", num, gender, storage, position);
    }
}
