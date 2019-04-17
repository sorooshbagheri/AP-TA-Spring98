package json;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

import java.io.IOException;
import java.io.StringReader;

/**
 * Created by sorooshbagheri on 19/04/17.
 */

public class GsonPractice {

    //using material in http://tutorials.jenkov.com/java-json/gson.html
    public static void main(String[] args) {
        //Topics:
        generation();
        prettyPrinting();
        streamReading();
    }

    static void generation(){
        //Creating a Gson Object
        //1st way
        Gson gson = new Gson();

        //2nd way
        GsonBuilder gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.create();

        //from Json
        String json = "{\"door\":{\"name\":\"door\",\"number\":4},\"wheels\":4}";
        Car car = gson.fromJson(json, Car.class);

        System.out.println(car);

        //to Json
        car = new Car(new Door(4));
        json = gson.toJson(car);

        System.out.println(json);
    }

    static void prettyPrinting(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.serializeNulls();
        Gson gson = gsonBuilder.setPrettyPrinting().create();

        //to Json
        Car car = new Car(new Door(4));
        String json = gson.toJson(car);

        System.out.println(json);
    }

    static void streamReading() {
        String json = "{\"door\":{\"name\":\"door\",\"number\":4},\"wheels\":4}";
        JsonReader jsonReader = new JsonReader(new StringReader(json));

        try {
            while (jsonReader.hasNext()) {
                JsonToken nextToken = jsonReader.peek();
                System.out.println(nextToken);

                if (JsonToken.BEGIN_OBJECT.equals(nextToken)) {

                    jsonReader.beginObject();

                } else if (JsonToken.NAME.equals(nextToken)) {

                    String name = jsonReader.nextName();
                    System.out.println(name);

                } else if (JsonToken.STRING.equals(nextToken)) {

                    String value = jsonReader.nextString();
                    System.out.println(value);

                } else if (JsonToken.NUMBER.equals(nextToken)) {

                    long value = jsonReader.nextLong();
                    System.out.println(value);

                }
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }


}
class Vehicle {
    int wheels;
}
class Car extends Vehicle{

    Door door ;
    public Car(Door door) {
        wheels = 4;
        this.door = door;
    }
}
class Door{
    String name = "door";
    int number;

    public Door(int number) {
        this.number = number;
    }
}

