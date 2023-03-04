package sol;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import src.AttributeSelection;
import src.IDataset;
import src.Row;

/**
 * A class representing a training dataset for the decision tree
 */
// TODO: Uncomment this once you've implemented the methods in the IDataset interface!
public class Dataset implements IDataset {

    /**
     * Constructor for a Dataset object
     * @param attributeList - a list of attributes
     * @param dataObjects -  a list of rows
     * @param attributeSelection - an enum for which way to select attributes
     */
    private List<String> attributeList;
    private List<Row> dataObjects;
    private AttributeSelection selectionType;
    private List<String> attributeCopy;
    public Dataset(List<String> attributeList, List<Row> dataObjects, AttributeSelection attributeSelection) {
        // TODO: implement the constructor! (Hint: take a look at `getAttributeToSplitOn`)
        this.attributeList = new ArrayList<>(attributeList);
        this.attributeCopy = new ArrayList<>(attributeList);
        this.dataObjects = new ArrayList<>(dataObjects);
        this.selectionType = attributeSelection;
    }

    @Override
    public List<String> getAttributeList() {
        return this.attributeList;
    }

    public List<String> getAttributeCopy() {
        return this.attributeCopy;
    }

    @Override
    public List<Row> getDataObjects() {
        return this.dataObjects;
    }

    @Override
    public AttributeSelection getSelectionType() {
        return this.selectionType;
    }

    @Override
    public int size() {
        return this.dataObjects.size();
    }

    //TODO: Uncomment this once you've completed the constructor!
    public String getAttributeToSplitOn() {
        switch (this.selectionType) {
            case ASCENDING_ALPHABETICAL -> {
//                String returnString = this.attributeList.stream().sorted().toList().get(0);
//                this.attributeList/**.stream().sorted().toList() MIGHT BE BROKEN CHECK LATER*/.remove(0);
//                return returnString;//this.attributeList.stream().sorted().toList().get(0);
                return this.attributeCopy.stream().sorted().toList().get(0);
            }
            case DESCENDING_ALPHABETICAL -> {
                //String returnString = this.attributeList.stream().sorted().toList().get(this.attributeList.size() - 1);
                //this.attributeList.remove(this.attributeList.size() - 1);
                return this.attributeCopy.stream().sorted().toList().get(this.attributeCopy.size() - 1);
            }
            case RANDOM -> {
                int num = ((int) (Math.random() * (this.attributeCopy.size()) /**-1 OR NO -1*/));
                //String returnString = this.attributeList.stream().sorted().toList().get(num);
                //this.attributeList.remove(num);
                return this.attributeCopy.stream().sorted().toList().get(num);
            }
        }
        throw new RuntimeException("Non-Exhaustive Switch Case");
    }
    public String getDefault(String targetAttribute){
        String defaultAttribute = null;
        int defaultAttCount = 0;
        for (int i = 0; i < this.dataObjects.size(); i++) {
            String currentAttVal = this.dataObjects.get(i).getAttributeValue(targetAttribute);
            if (defaultAttribute == null || !defaultAttribute.equals(currentAttVal)) {
                int currentAttCount = 0;
                for (int j = i; j < this.dataObjects.size(); j++) {
                    if (this.dataObjects.get(j).getAttributeValue(targetAttribute).equals(currentAttVal)) {
                        currentAttCount++;
                    }
                }
                if (currentAttCount > defaultAttCount) {
                    defaultAttribute = currentAttVal;
                    defaultAttCount = currentAttCount;
                }
            }
        }
        return defaultAttribute;
    }
    public Boolean leafPossible(String targetAttribute){
        String currentAttVal = this.dataObjects.get(0).getAttributeValue(targetAttribute);
        int currentAttCount = 0;
        for (int i = 0; i < this.dataObjects.size(); i++) {
            if (this.dataObjects.get(i).getAttributeValue(targetAttribute).equals(currentAttVal)) {
                currentAttCount++;
            }
        }
        //System.out.println(currentAttCount + " = " + this.dataObjects.size());
        if (currentAttCount == this.dataObjects.size()){
            return true;
        } else {
            return false;
        }
    }
    public List<ValueEdge> getOutgoingEdges(String attribute) {
        List<ValueEdge> listOfValueEdges = new ArrayList<>();
        List<Row> copyDataObjects = new ArrayList<>(this.dataObjects);
        while (!copyDataObjects.isEmpty()) {
            String curAttributeValue = copyDataObjects.get(0).getAttributeValue(attribute);
            listOfValueEdges.add(new ValueEdge(copyDataObjects.get(0).getAttributeValue(attribute)/**, null/**RECURSION*/));
            for (int i = 0; i < copyDataObjects.size(); i++) {
                if (copyDataObjects.get(i).getAttributeValue(attribute).equals(curAttributeValue)){
                    copyDataObjects.remove(i);
                    i--;
                }
            }
        }
        return listOfValueEdges;
    }
    public Dataset splitDataset (String attributeName, String selectedAttributeValue){
        List<Row> splitDataObjects = new ArrayList<>();
        for (Row row : this.dataObjects){
            if (row.getAttributeValue(attributeName).equals(selectedAttributeValue)){
                splitDataObjects.add(row);
            }
        }
//        List<String> updatedAttributeList = new ArrayList<>();
//        for (String s: this.attributeList) {
//            if (!s.equals(attributeName)) {
//                updatedAttributeList.add(s);
//            }
//        }
//            System.out.println("Selectable attributes: ");
//            for(String q: updatedAttributeList){
//                System.out.print(" " + q);
//            }
//        System.out.println("");
        return new Dataset(this.attributeCopy, splitDataObjects, this.selectionType);
    }
    public void updateAttributeList(String attributeAtNode){
        List<String> updatedAttributeList = new ArrayList<>();
        for (String s: this.attributeList) {
            if (!s.equals(attributeAtNode)) {
                updatedAttributeList.add(s);
            }
        }
        System.out.println("Selectable attributes: ");
            for(String q: updatedAttributeList){
                System.out.print(" " + q);
            }
        System.out.println("");
        this.attributeCopy = new ArrayList<>(updatedAttributeList);
    }
}
