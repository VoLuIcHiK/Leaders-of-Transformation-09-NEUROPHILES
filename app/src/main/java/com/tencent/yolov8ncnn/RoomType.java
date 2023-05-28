package com.tencent.yolov8ncnn;

public enum RoomType{
    KITCHEN,
    LIVING,
    SANITARY,
    HALL;

    String toText(){
        switch(this) {
            case KITCHEN : return "Кухня";
            case LIVING : return "Жилая";
            case SANITARY : return "Санузел";
            case HALL : return "Коридор";
        }
        return "";
    }

    public static RoomType toEnum(String room){
        switch(room) {
            case "Кухня" : return KITCHEN;
            case "Жилая" : return LIVING;
            case "Санузел" : return SANITARY;
            case "Коридор" : return HALL;
        }
        return null;
    }
}
