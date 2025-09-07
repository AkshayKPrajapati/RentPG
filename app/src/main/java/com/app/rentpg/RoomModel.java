package com.app.rentpg;
public class RoomModel {
    private String roomNumber;
    private String hotelName;
    private String price;
    private String description;

    public RoomModel(String roomNumber, String hotelName, String price, String description) {
        this.roomNumber = roomNumber;
        this.hotelName = hotelName;
        this.price = price;
        this.description = description;
    }

    public String getRoomNumber() { return roomNumber; }
    public String getHotelName() { return hotelName; }
    public String getPrice() { return price; }
    public String getDescription() { return description; }
}