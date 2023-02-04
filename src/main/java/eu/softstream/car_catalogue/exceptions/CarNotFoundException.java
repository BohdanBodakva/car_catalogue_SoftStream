package eu.softstream.car_catalogue.exceptions;

public class CarNotFoundException extends Exception{
    public CarNotFoundException(String message) {
        super(message);
    }
}
