package org.mjulikelion.component;


import org.mjulikelion.component.type.CpuType;

public class Cpu extends Component implements OnOff{
    public Cpu(String name, int price, String company, int speed, CpuType type) {
        super(name, price, company);
        this.speed = speed;
        this.type = type;
    }

    private int speed;
    private CpuType type;

    public void processInstruction(){
         System.out.println(super.getName()+" 명령어를 처리합니다.");
    };

    @Override
    public void on() {
        System.out.println("CPU ON");
    }

    @Override
    public void off() {
        System.out.println("CPU OFF");
    }

}