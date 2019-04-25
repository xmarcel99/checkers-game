package com.checkers;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SaveAndLoadGameProgress  {

    public static void saveGameProgress(Serializable data, String fileName) throws  Exception{
        try{
            ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(Paths.get(fileName)));
            oos.writeObject(data);
        }finally {
            System.out.println("Saving...");
        }
    }

    public static  Object loadGameProgress(String fileName) throws  Exception {

        try {
            ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(Paths.get(fileName)));
            return  ois.readObject();
        } finally {
            System.out.println("Loading...");
        }

    }
}
