package org.mjulikelion.component;


import org.mjulikelion.component.type.GraphicsCardType;

public class GraphicsCard extends Component implements OnOff{

    private GraphicsCardType type;

    public GraphicsCard(String name, int price, String company, GraphicsCardType type) {
        super(name, price, company);
        this.type = type;
    }

    public void render(){
        System.out.println(super.getName()+" 화면을 렌더링합니다.");
    }

    @Override
    public void on() {
        System.out.println("Graphics Card ON");
    }

    @Override
    public void off() {
        System.out.println("Graphics Card OFF");
    }
}
