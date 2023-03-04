package sol;

import java.util.ArrayList;
import java.util.List;
import src.ITreeNode;
import src.Row;

/**
 * A class representing an inner node in the decision tree.
 */
// TODO: Uncomment this once you've implemented the methods in the ITreeNode interface!
public class AttributeNode implements ITreeNode {
    // TODO: add more fields as needed
    private List<ValueEdge> outgoingEdges;
    private String attributeAtNode;
    private String defaultValue;
    /**
     *
     * @param outgoingEdges
     * @param attributeAtNode
     * @param defaultValue
     */
    public AttributeNode(List<ValueEdge> outgoingEdges, String attributeAtNode, String defaultValue){
        this.outgoingEdges = new ArrayList<>(outgoingEdges);
        this.attributeAtNode = attributeAtNode;
        this.defaultValue = defaultValue;
    }

    @Override
    public String getDecision(Row forDatum) {
        for (int i = 0; i < this.outgoingEdges.size(); i++) {
            if (forDatum.getAttributeValue(this.attributeAtNode).equals(this.outgoingEdges.get(i).getAttributeValue())){
                return this.outgoingEdges.get(i).getChild().getDecision(forDatum);
            }
        }
        return this.defaultValue;
    }
    public List<ValueEdge> getOutgoingEdges(){
        return this.outgoingEdges;
    }

    // TODO: implement the ITreeNode interface
}
