package com.example.lutemonapp;

import android.content.Context;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
public class LutemonStorage implements Serializable {

    private static LutemonStorage instance = null;
    private ArrayList<Lutemon> homeLutemons;
    private ArrayList<Lutemon> battleLutemons;
    private ArrayList<Lutemon> trainingLutemons;
    private ArrayList<Lutemon> allLutemons;


    private LutemonStorage() {
        allLutemons = new ArrayList<>();
        homeLutemons = new ArrayList<>();
        battleLutemons = new ArrayList<>();
        trainingLutemons = new ArrayList<>();
        instance = this;
    }

    public static LutemonStorage getInstance() {
        if (instance == null) {
            instance = new LutemonStorage();
        }
        return instance;
    }

    // Method for removing lutemons from different arraylists
    public void removeLutemon(Lutemon lutemon, String listName) {
        switch (listName) {
            case "home":
                homeLutemons.remove(lutemon);
                break;
            case "battle":
                battleLutemons.remove(lutemon);
                break;
            case "training":
                trainingLutemons.remove(lutemon);
                break;
            case "all":
                allLutemons.remove(lutemon);
            default:
                break;
        }
    }

    // Method for finding lutemons in an arraylist
    public ArrayList<Lutemon> getLutemons(String listName) {
        switch (listName) {
            case "home":
                return homeLutemons;
            case "battle":
                return battleLutemons;
            case "training":
                return trainingLutemons;
            case "all":
                return allLutemons;
            default:
                return null;
        }
    }

    // Method for adding lutemons to an arraylist
    public void addLutemon(Lutemon lutemon, String listName) {
        switch (listName) {
            case "home":
                homeLutemons.add(lutemon);
                break;
            case "battle":
                battleLutemons.add(lutemon);
                break;
            case "training":
                trainingLutemons.add(lutemon);
                break;
            case "all":
                allLutemons.add(lutemon);
                break;
            default:
                break;
        }
    }

    // Method for finding lutemons by their id
    public Lutemon getLutemonByIdWithoutRemove(int id) {
        return homeLutemons.get(id);
    }

    // Method for saving lutemons
    public void saveLutemons(Context context){
        try {
            ObjectOutputStream lutemonWriter = new ObjectOutputStream(context.openFileOutput("lutemons.data", Context.MODE_PRIVATE));
            lutemonWriter.writeObject(homeLutemons);
            lutemonWriter.close();
            System.out.println("Lutemonien tallentaminen onnistui");
        } catch (IOException e){
            System.out.println("Lutemonien tallentaminen ei onnistunut");
        }
    }

    // Method for loading lutemons
    public void loadLutemons(Context context){
        try {
            ObjectInputStream lutemonReader = new ObjectInputStream(context.openFileInput("lutemons.data"));
            homeLutemons = (ArrayList<Lutemon>) lutemonReader.readObject();
            lutemonReader.close();
            if (!allLutemons.isEmpty()){
                allLutemons.clear();
                battleLutemons.clear();
                trainingLutemons.clear();
            }
            for (Lutemon lutemon : homeLutemons) {
                allLutemons.add(lutemon);
            }
            System.out.println("Lutemonien lataaminen onnistui");
        } catch (FileNotFoundException e){
            System.out.println("Lutemonien lataaminen ei onnistunut");
            e.printStackTrace();
        } catch (IOException e){
            System.out.println("Lutemonien lataaminen ei onnistunut");
            e.printStackTrace();
        } catch (ClassNotFoundException e){
            System.out.println("Lutemonien lataaminen ei onnistunut");
            e.printStackTrace();
        }
    }
}
