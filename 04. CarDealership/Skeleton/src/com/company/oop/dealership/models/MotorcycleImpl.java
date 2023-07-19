package com.company.oop.dealership.models;

import com.company.oop.dealership.models.contracts.Comment;
import com.company.oop.dealership.models.contracts.Motorcycle;
import com.company.oop.dealership.models.enums.VehicleType;
import com.company.oop.dealership.utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

public class MotorcycleImpl extends VehicleBase implements Motorcycle {

    public static final int CATEGORY_LEN_MIN = 3;
    public static final int CATEGORY_LEN_MAX = 10;
    private static final String CATEGORY_LEN_ERR = format(
            "Category must be between %d and %d characters long!",
            CATEGORY_LEN_MIN,
            CATEGORY_LEN_MAX);
    private static final String MISSING_COMMENTS = "--NO COMMENTS--";
    private static final String COMMENTS_MESSAGE = "--COMMENTS--";
    public static final int WHEELS = 2;

    private String category;
    private final ArrayList<Comment> comments;
    public MotorcycleImpl(String make, String model, double price, String category)
    {
        super(make, model, price);
        setCategory(category);
        comments = new ArrayList<>();
    }

    private void setCategory(String category)
    {
        validateCategory(category);
        this.category = category;
    }
    protected void validateCategory(String category)
    {
        ValidationHelpers.validateIntRange(category.length(), CATEGORY_LEN_MIN, CATEGORY_LEN_MAX, CATEGORY_LEN_ERR);
    }
    @Override
    public int getWheels()
    {
        return WHEELS;
    }
    @Override
    public String getCategory()
    {
        return this.category;
    }
    @Override
    public VehicleType getType() {
        return VehicleType.MOTORCYCLE;
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
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(super.toString()).append(String.format("Category: %s\n", category));
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
                stringBuilder.append(String.format("User: %s\n",comm.getAuthor()));
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
