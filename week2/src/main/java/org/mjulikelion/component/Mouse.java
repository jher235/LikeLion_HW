package org.mjulikelion.component;

public class Mouse extends Component implements OnOff{

    public Mouse(String name, int price, String company) {
        super(name, price, company);
    }

    public void click(){
        System.out.println(super.getName()+" 클릭합니다.");
    }

    @Override
    public void on() {
        System.out.println("마우스 ON");
    }

    @Override
    public void off() {
        System.out.println("마우스 OFF");
    }
}
