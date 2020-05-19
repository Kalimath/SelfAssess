package database;

import java.util.ArrayList;
import java.util.List;

public enum DatabaseStrategies {
    INRAM("RamDatabase","in memory"),INTXT("TxtDatabase","in text path");


    DatabaseStrategies(String className, String name) {
        this.className = className;
        this.name = name;
    }
    private String className,name;

    public static List<String> getAllNames(){
        List<String> names = new ArrayList<>();
        for(Enum s :DatabaseStrategies.values()){
            names.add(s.name());
        }
        return names;
    }

    public String getClassName(){
        return this.className;
    }

    public String getName(){
        return this.name;
    }

    public static boolean containsClassname(String className){
        if(DatabaseStrategies.getAllNames().contains(className)){
            return true;
        }else{
            return false;
        }
    }
}
