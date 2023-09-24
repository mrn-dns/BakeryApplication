package controllers;

import com.example.ca_2_baking_information_system.BakeryApplication;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;
import javafx.event.ActionEvent;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import models.BakedGood;
import models.Ingredient;
import utils.HashTable;
import utils.Node;
import utils.NodeList;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class BakedGoodController {
    public static BakedGoodController bakedGoodController;
    public ListView<BakedGood> bakedGoodList;
    public void initialize() {
        bakedGoodController=this;
    }

    //---------------------
    // Handling Methods
    //---------------------

    //Method to display all baked goods in the bakedGoodList Listview.
    public void updateBakedListView() {
        bakedGoodList.getItems().clear();
        //The method goes through each entry in the baked good hashtable and if the entry isn't null it is added to the bakedGoodList Listview.
        for (BakedGood b: BakeryApplication.bakedList.getHashTable()) {
            if(b != null){
                bakedGoodList.getItems().add(b);
            }
        }
    }

    //TODO
    //Method to delete all baked goods.
    public void deleteAllBakedGoods() {

    }

    //Method to remove a baked good by the user selecting a baked good from the bakedGoodList Listview.
    public void removeBakedGood(MouseEvent mouseEvent) {
        BakeryApplication.bakedList.delete(bakedGoodList.getSelectionModel().getSelectedItem(), bakedGoodList.getSelectionModel().getSelectedItem().getBakedName());
        //When the baked good is deleted the listview is reloaded.
        BakeryApplication.reloadContents();
    }

    //---------------------
    // View Change Methods
    //---------------------

    //Method to allow the user to return to the home menu from this scene.
    public void home(ActionEvent event) {
        BakeryApplication.primaryStage.setScene(BakeryApplication.scene1);
    }

    //Method to allow the user to return to the baked good scene.
    public void returnBaked(ActionEvent event) {
        BakeryApplication.primaryStage.setScene(BakeryApplication.scene5);
    }

    //Method to allow the user to return to the recipe scene.
    public void returnRecipe(ActionEvent event) {
        BakeryApplication.primaryStage.setScene(BakeryApplication.scene7);
    }

    //Method to allow the user to return to the ingredient scene.
    public void returnIngredient(ActionEvent event) {
        BakeryApplication.primaryStage.setScene(BakeryApplication.scene6);
    }

    //Method to allow the user to change to the add baked good scene.
    public void addBakedPage(MouseEvent mouseEvent) {
        BakeryApplication.primaryStage.setScene(BakeryApplication.scene2);
    }

    //Method to allow the user to change to the update baked good scene.
    public void updateBakedPage(MouseEvent mouseEvent) {
        BakeryApplication.primaryStage.setScene(BakeryApplication.scene9);
    }


    //---------------------
    // Persistence Methods
    //---------------------

    //The load method uses the XStream component to read all the objects from the xml file stored on the hard disk. The read objects are loaded into the associated Hashtable.
    @SuppressWarnings("unchecked")
    public void load() throws Exception {
        //list of classes that you wish to include in the serialisation, separated by a comma
        Class<?>[] classes = new Class[]{BakedGood.class};

        //setting up the XStream object with default security and the above classes
        XStream xstream = new XStream(new DomDriver());
        xstream.addPermission(AnyTypePermission.ANY);
        xstream.allowTypes(classes);

        //doing the actual serialisation to an XML file
        ObjectInputStream in = xstream.createObjectInputStream(new FileReader("bakedGoods.xml"));
        BakeryApplication.bakedList = (HashTable<BakedGood>) in.readObject();
        in.close();
    }

    //The save method uses the XStream component to write all the objects in the Hashtable to the xml file stored on the hard disk.
    public void save() throws Exception {
        try {
            XStream xstream = new XStream(new DomDriver());
            ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("bakedGoods.xml"));
            out.writeObject(BakeryApplication.bakedList);
            out.close();
        } catch (Exception e) {
            System.out.println("Error writing this file : " + e);
        }
    }
}
