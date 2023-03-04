package sol;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import static org.junit.Assert.assertFalse;

import com.sun.source.tree.Tree;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import src.AttributeSelection;
import src.DecisionTreeCSVParser;
import src.Row;

import java.util.ArrayList;
import java.util.List;

/**
 * A class containing the tests for methods in the TreeGenerator and Dataset classes
 */
public class DecisionTreeTest {
    //TODO: Write more unit and system tests! Some basic guidelines that we will be looking for:
    // 1. Small unit tests on the Dataset class testing the IDataset methods
    // 2. Small unit tests on the TreeGenerator class that test the ITreeGenerator methods
    // 3. Tests on your own small dataset (expect 70% accuracy on testing data, 95% on training data)
    // 4. Test on the villains dataset (expect 70% accuracy on testing data, 95% on training data)
    // 5. Tests on the mushrooms dataset (expect 70% accuracy on testing data, 95% on training data)
    // Feel free to write more unit tests for your own helper methods -- more details can be found in the handout!
    private String trainingPath = "data/mushrooms/training.csv";
    private String targetAttribute = "isPoisonous";

    /**
     * This test shows syntax for a basic assertEquals assertion -- can be deleted
     */
    @Test
    public void testAssertEqual() {
        assertEquals(1 + 1, 2);
    }

    /**
     * This test shows syntax for a basic assertTrue assertion -- can be deleted
     */
    @Test
    public void testAssertTrue() {
        assertTrue(true);
    }

    /**
     * This test shows syntax for a basic assertFalse assertion -- can be deleted
     */
    @Test
    public void testAssertFalse() {
        assertFalse(false);
    }

    @Test
    public void testDatasetConstruction() {
    String filePath = "data/fruits-and-vegetables";
    List<Row> dataObjects = DecisionTreeCSVParser.parse(this.trainingPath);
    List<String> attributeList = new ArrayList<>(dataObjects.get(0).getAttributes());
    Dataset training = new Dataset(attributeList, dataObjects, AttributeSelection.ASCENDING_ALPHABETICAL);
    training.size();
        Assert.assertEquals(7,training.size());
        Assert.assertEquals(4,training.getAttributeList().size());
        Assert.assertEquals(7,training.getDataObjects().size());
    }
    @Test
    public void testUsedAttributes(){
        List<Row> dataObjects = DecisionTreeCSVParser.parse(this.trainingPath);
        List<String> attributeList = new ArrayList<>(dataObjects.get(0).getAttributes());
        Dataset training = new Dataset(attributeList, dataObjects, AttributeSelection.ASCENDING_ALPHABETICAL);
        for (int i = 0; i < training.getAttributeList().size(); i++) {
            System.out.println(training.getAttributeList().size());
            System.out.println("removed: " + training.getAttributeToSplitOn());
            for (String s: training.getAttributeList()) {
                System.out.println("");
                System.out.print(s + " ");
            }
        }
    }
    @Test
    public void generateTreeTest(){
        List<Row> dataObjects = DecisionTreeCSVParser.parse(this.trainingPath);
        List<String> attributeList = new ArrayList<>(dataObjects.get(0).getAttributes());
        Dataset training = new Dataset(attributeList, dataObjects, AttributeSelection.RANDOM);
        TreeGenerator tree = new TreeGenerator();
        tree.generateTree(training, this.targetAttribute);
    }
}
