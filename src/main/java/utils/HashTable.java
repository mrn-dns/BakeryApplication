package utils;

import models.Ingredient;
import java.lang.reflect.Array; //TODO figure out what this does!!

public class HashTable<L> {
    private L[] hashTable;

    public HashTable(Class<L> clas, int size){
        hashTable = (L[]) Array.newInstance(clas, size);
    }

    public  L[] getHashTable(){
        return hashTable;
    }

    public void displayHashTable() {
        for(int i=0;i<hashTable.length;i++)
            System.out.println(i+". "+hashTable[i]);
    }

    public int hashFunction(int key) {
        return key%hashTable.length;
    }

    public int hashFunction(String key){
        int count = 0;
        for(int i = 0; i<key.length(); i++){
            count += key.charAt(i);
        }
        return hashFunction(count);
    }

    public int add(L item, int key) {
        int home = hashFunction(key), loc=home;
        return addToTable(item, home);
    }

    public int add(L item, String key) {
        int home = hashFunction(key), loc=home;
        return addToTable(item, home);

    }

    private int addToTable(L item, int home){
        int loc = home;
        do {
            if(hashTable[loc]==null)
            {
                hashTable[loc]=item;
                return loc;
            }
            else {
                loc=(loc+1)%hashTable.length;
            }

        }while(loc!=home);
        return -1;
    }

    public L delete (L item, String key){
        int home = hashFunction(key);
        return deleteItem(item, home);
    }

    public L delete (L item, int key){
        int home = hashFunction(key);
        return deleteItem(item, home);
    }

    private L deleteItem(L item, int key){
        int loc = key;

        do{
            if(hashTable[loc] == item){
                L temp = hashTable[loc];
                hashTable[loc] = null;
                return temp;
            } else{
                loc=(loc+1)%hashTable.length;
            }
        } while(loc!=key);
        return null;
    }
}