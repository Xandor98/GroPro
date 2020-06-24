package de.cae.misc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Table {

    private final Map<String, List<String>> table = new HashMap<>();

    public void addHeader(String header){
        if(!table.containsKey(header)){
            table.put(header, new ArrayList<>());
        }
    }

    public void addValue(String header, String value){
        if(!table.containsKey(header)) throw new IllegalArgumentException("No Header of that type");

        table.get(header).add(value);
    }

    @Override
    public String toString(){
        StringBuilder s = new StringBuilder();

        int size = table.values().stream().mapToInt(List::size).max().orElse(0);

        boolean first = true;
        for (String s1 : table.keySet()) {
            if(first){
                first = false;
            }else{
                s.append(",");
            }
            s.append(s1);
        }
        s.append(System.lineSeparator());

        for(int i = 0; i < size; i++){
            first = true;
            for (String s1 : table.keySet()) {
                if(first){
                    first = false;
                }else{
                    s.append(",");
                }
                String value = i < table.get(s1).size() ? table.get(s1).get(i) : "";
                s.append(value);
            }
            s.append(System.lineSeparator());
        }

        return s.toString();
    }
}
