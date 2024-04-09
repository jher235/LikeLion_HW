package org.mjulikelion.component;

public class PowerSupplier extends Component implements OnOff {

    private int power;

    public PowerSupplier(String name, int price, String company, int power) {
        super(name,price,company);
        this.power = power;
    }

    public void supplyPower(){
        System.out.println(super.getName()+" 전원을 공급합니다.");
    }

    @Override
    public void on() {
        System.out.println("POWER Supplier ON");
    }

    @Override
    public void off() {
        System.out.println("POWER Supplier OFF");
    }
}
