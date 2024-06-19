package org.mjulikelion.component;

public class Ram extends Component implements OnOff{

    private int capacity;

    public Ram(String name, int price, String company, int capacity) {
        super(name, price, company);
        this.capacity = capacity;
    }

    public void readData(){
        System.out.println(super.getName()+ " 데이터를 읽습니다.");
    }

    @Override
    public void on() {
        System.out.println("Ram ON");
    }

    @Override
    public void off() {
        System.out.println("Ram OFF");
    }
}
