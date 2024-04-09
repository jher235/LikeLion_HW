package org.mjulikelion.component;

import org.mjulikelion.component.type.KeyBoardType;

public class KeyBoard extends Component implements OnOff{
    private KeyBoardType type;

    public KeyBoard(String name, int price, String company, KeyBoardType type) {
        super(name, price, company);
        this.type = type;
    }

    public void type(){
        System.out.println(super.getName()+" 타이핑합니다.");
    }

    @Override
    public void on() {
        System.out.println("키보드 ON");
    }

    @Override
    public void off() {
        System.out.println("키보드 ON");
    }
}
