package Abstraktion;

public class Rectangle extends Shape {
    private final double height;
    private final double width;

    public Rectangle(double height, double width){
        this.height = height;
        this.width = width;
    }

    @Override
    public double area(){
        return height*width;
    }
}
