package org.mjulikelion;

import org.mjulikelion.component.*;
import org.mjulikelion.component.type.CoolerType;
import org.mjulikelion.component.type.CpuType;
import org.mjulikelion.component.type.GraphicsCardType;


public class Main {

    public static void main(String[] args) {
        Cpu cpu = new Cpu("Amd",200000,"Amd",132, CpuType.GAMING);
        Ram ram = new Ram("Samsung",5000, "Samsung",25000);
        GraphicsCard graphicsCard = new GraphicsCard("Nvidia3080", 500000, "Nvidia", GraphicsCardType.GAMING);
        PowerSupplier powerSupplier = new PowerSupplier("Thermaltake", 100000,"Thermaltake",25000);
        Cooler cooler = new Cooler("Cooler Master", 50000,"Cooler Master",CoolerType.WATERCOOLING);
        Mouse mouse = new Mouse("Logitech 무소음 마우스",30000,"Logitech");

        Computer computer = new Computer.ComputerBuilder()
                .cpu(cpu)
                .ram(ram)
                .graphicsCard(graphicsCard)
                .powerSupplier(powerSupplier)
                .cooler(cooler)
                .mouse(mouse)
                .build();




        computer.boot();
        computer.run();
        computer.shutdown();
    }

}
