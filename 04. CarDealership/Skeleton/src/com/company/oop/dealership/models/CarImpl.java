package com.company.oop.dealership.models;

import com.company.oop.dealership.models.contracts.Car;
import com.company.oop.dealership.models.contracts.Comment;
import com.company.oop.dealership.models.enums.VehicleType;
import com.company.oop.dealership.utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

public class CarImpl extends VehicleBase implements Car {

    public static final int CAR_SEATS_MIN = 1;
    public static final int CAR_SEATS_MAX = 10;
    private static final String CAR_SEATS_ERR = format(
            "Seats must be between %d and %d!",
            CAR_SEATS_MIN,
            CAR_SEATS_MAX);
    private static final String MISSING_COMMENTS = "--NO COMMENTS--";
    private static final String COMMENTS_MESSAGE = "--COMMENTS--";
    public static final int WHEELS = 4;

    private int seats;

    private final ArrayList<Comment> comments;
    public CarImpl(String make, String model, double price, int seats)
    {
        super(make, model, price);
        setSeats(seats);
        comments = new ArrayList<>();
    }
    private void setSeats(int seats)
    {
        validateSeats(seats);
        this.seats = seats;
    }
    protected void validateSeats(int seats)
    {
        ValidationHelpers.validateIntRange(seats, CAR_SEATS_MIN, CAR_SEATS_MAX, CAR_SEATS_ERR);
    }

    @Override
    public int getWheels()
    {
        return WHEELS;
    }

    @Override
    public int getSeats()
    {
        return this.seats;
    }
    @Override
    public VehicleType getType()
    {
        return VehicleType.CAR;
    }

    @Override
    public String getMake()
    {
        return super.make;
    }

    @Override
    public String getModel() {
        return super.model;
    }
    @Override
    public double getPrice() {
        return super.price;
    }
    @Override
    public void addComment(Comment comment)
    {
        if(comment != null) {
            comments.add(comment);
        }
    }

    @Override
    public void removeComment(Comment comment)
    {
        comments.remove(comment);
    }

    @Override
    public List<Comment> getComments() {
        return new ArrayList<>(comments);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(super.toString());
        stringBuilder.append(String.format("Seats: %d\n", this.seats));
        if (comments.isEmpty())
        {
            stringBuilder.append(MISSING_COMMENTS);
        }
        else {
            stringBuilder.append(COMMENTS_MESSAGE).append("\n");
            for (Comment comm : comments) {
                stringBuilder.append(dashes()).append("\n");
                stringBuilder.append(comm.getContent()).append("\n");
                stringBuilder.append(String.format("User: %s",comm.getAuthor())).append("\n");
                stringBuilder.append(dashes()).append("\n");
            }
            stringBuilder.append(COMMENTS_MESSAGE);
        }
       return stringBuilder.toString();
    }
    private static String dashes() {
        return "----------";
    }

}
