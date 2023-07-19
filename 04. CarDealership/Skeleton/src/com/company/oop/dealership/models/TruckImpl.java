package com.company.oop.dealership.models;

import com.company.oop.dealership.models.contracts.Comment;
import com.company.oop.dealership.models.contracts.Truck;
import com.company.oop.dealership.models.enums.VehicleType;
import com.company.oop.dealership.utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

public class TruckImpl extends VehicleBase implements Truck {
    public static final int WEIGHT_CAP_MIN = 1;
    public static final int WEIGHT_CAP_MAX = 100;

    private static final String MISSING_COMMENTS = "--NO COMMENTS--";
    private static final String COMMENTS_MESSAGE = "--COMMENTS--";
    public static final int WHEELS  = 8;
    private static final String WEIGHT_CAP_ERR = format(
            "Weight capacity must be between %d and %d!",
            WEIGHT_CAP_MIN,
            WEIGHT_CAP_MAX);

    private int weightCapacity;
    private final ArrayList<Comment>comments;
    public TruckImpl(String make, String model, double price, int weightCapacity)
    {
        super(make, model, price);
        setWeightCapacity(weightCapacity);
        comments = new ArrayList<>();
    }
    private void setWeightCapacity(int weightCapacity)
    {
        validateWeightCapacity(weightCapacity);
        this.weightCapacity = weightCapacity;
    }
    protected void validateWeightCapacity(int weightCapacity)
    {
        ValidationHelpers.validateIntRange(weightCapacity, WEIGHT_CAP_MIN, WEIGHT_CAP_MAX, WEIGHT_CAP_ERR);
    }
    @Override
    public int getWeightCapacity()
    {
        return this.weightCapacity;
    }
    @Override
    public int getWheels()
    {
        return WHEELS;
    }
    @Override
    public VehicleType getType()
    {
        return VehicleType.TRUCK;
    }
    @Override
    public double getPrice() {
        return super.price;
    }
    @Override
    public String getMake() {
        return super.make;
    }
    @Override
    public String getModel() {
        return super.model;
    }
    @Override
    public List<Comment> getComments()
    {
        return new ArrayList<>(comments);
    }
    @Override
    public void addComment(Comment comment)
    {
        if (comment != null) {
            comments.add(comment);
        }
    }
    @Override
    public void removeComment(Comment comment)
    {
        comments.remove(comment);
    }
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(super.toString()).append(String.format("Weight Capacity: %st\n",this.weightCapacity));
        if (comments.isEmpty())
        {
            stringBuilder.append(MISSING_COMMENTS);
        }
        else
        {
            stringBuilder.append(COMMENTS_MESSAGE).append("\n");
            for (Comment comm : comments)
            {
                stringBuilder.append(dashes()).append("\n");
                stringBuilder.append(comm.getContent()).append("\n");
                stringBuilder.append(String.format("User: %s",comm.getAuthor()));
                stringBuilder.append(dashes());
            }
        }
        return stringBuilder.toString();
    }
    private static String dashes() {
        return "----------";
    }
}
