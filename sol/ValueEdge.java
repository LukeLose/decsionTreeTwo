package sol;

import src.ITreeNode;

/**
 * A class that represents the edge of an attribute node in the decision tree
 */
public class ValueEdge {
    // TODO: add more fields if needed
    private ITreeNode child;
    private String attributeValue;
    public ValueEdge (String attributeValue/**, ITreeNode child*/){
        this.attributeValue = attributeValue;
        this.child = null;
        //this.child = child;
    }
    public String getAttributeValue(){
        return this.attributeValue;
    }
    public ITreeNode getChild(){
        return this.child;
    }
    public void setChild(ITreeNode child){
        this.child = child;
    }

}
