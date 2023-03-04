package sol;

import src.ITreeNode;
import src.Row;

/**
 * A class representing a leaf in the decision tree.
 */
// TODO: Uncomment this once you've implemented the methods in the ITreeNode interface!
public class DecisionLeaf implements ITreeNode {
    private String decisionAttributeValue;
    public DecisionLeaf (String decisionAttributeValue){
        this.decisionAttributeValue = decisionAttributeValue;
    }
    @Override
    public String getDecision(Row forDatum) {
        return this.decisionAttributeValue;
    }
    // TODO: add fields as needed
    // TODO: Implement the ITreeNode interface
}
