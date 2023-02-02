package com.closeplanet2.pandaspigotcore.FileSystem.FileAndDirectory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class ScannerCore {

    public static ScannerCore Create(File objectFile){ return new ScannerCore(objectFile); }

    public ScannerCore(File objectFile){
        try { scanner = new Scanner(objectFile);
        } catch (FileNotFoundException e) { e.printStackTrace(); }
    }

    private Scanner scanner;
    private HashMap<String, List<String>> splitData = new HashMap<>();

    public Scanner getScanner(){ return scanner; }
    public HashMap<String, List<String>> getSplitData(){ return splitData; }
    public List<String> getSplitDataAt(String key){return splitData.get(key);}

    public void SplitData(String... keys){
        for(var key : keys){ if(!splitData.containsKey(key)) splitData.put(key, new ArrayList<>()); }
        while(scanner.hasNext()){
            var line = scanner.nextLine();
            for(var key : keys){ if(line.startsWith(key)) splitData.get(key).add(line); }
        }
        scanner.close();
    }
}
