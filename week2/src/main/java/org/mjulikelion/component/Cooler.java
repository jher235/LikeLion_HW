package org.mjulikelion.component;


import org.mjulikelion.component.type.CoolerType;

public class Cooler extends Component implements OnOff{
    private CoolerType type;

    public Cooler(String name, int price, String company, CoolerType type) {
        super(name,price,company);
        this.type = type;
    }

    public void cool(){
        System.out.println(super.getName()+" 냉각합니다");
    }

    @Override
    public void on() {
        System.out.println("Cooler ON");
    }

    @Override
    public void off() {
        System.out.println("Cooler OFF");
    }
}
