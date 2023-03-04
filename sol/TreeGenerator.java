package sol;

import src.ITreeGenerator;
import src.ITreeNode;
import src.Row;

import java.util.List;

/**
 * A class that implements the ITreeGenerator interface used to generate a decision tree
 */
// TODO: Uncomment this once you've implemented the methods in the ITreeGenerator interface!
public class TreeGenerator implements ITreeGenerator<Dataset> {
    // TODO: document this field
    private ITreeNode root;

    public TreeGenerator(){
        this.root = null;
    }

    @Override
    public void generateTree(Dataset trainingData, String targetAttribute) {
        trainingData.getAttributeCopy().remove(targetAttribute);
        trainingData.getAttributeList().remove(targetAttribute);
        this.root = this.generateTreeHelper(trainingData, targetAttribute, trainingData.getAttributeToSplitOn());

    }
    /**
     *
     * @param trainingData
     * @param targetAttribute
     * @param attributeAtNode
     * @return
     */
    public ITreeNode generateTreeHelper(Dataset trainingData, String targetAttribute, String attributeAtNode) {
        if (trainingData.leafPossible(targetAttribute) || trainingData.getAttributeCopy().isEmpty()){
            System.out.println("Decision leaf made at " + trainingData.getDataObjects().get(0).getAttributeValue(attributeAtNode));
            System.out.println("");
            return new DecisionLeaf(trainingData.getDataObjects().get(0).getAttributeValue(targetAttribute));
        } else {
            /**trainingData.getAttributeList().remove(attributeAtNode);*/
            //System.out.println(trainingData.getOutgoingEdges(attributeAtNode).size());
            System.out.println("");
            System.out.println("Attribute node made at " + attributeAtNode);
            AttributeNode newRoot = new AttributeNode(trainingData.getOutgoingEdges(attributeAtNode), attributeAtNode,
                    trainingData.getDefault(targetAttribute));
            //List<String> updatedAttributeList = trainingData.updateAttributeList(attributeAtNode);
            trainingData.updateAttributeList(attributeAtNode);
            String nextSplit = attributeAtNode;
            //System.out.println(trainingData.getAttributeList().size());
            if (!trainingData.getAttributeCopy().isEmpty()) {
                nextSplit = trainingData.getAttributeToSplitOn();
            }
            System.out.println(newRoot.getOutgoingEdges().size() + " value edges");
//            for (String s: trainingData.getAttributeList()) {
//                System.out.print(s + " ");
//            }
            //System.out.println("");
            for (ValueEdge edge : newRoot.getOutgoingEdges()/**trainingData.getOutgoingEdges(attributeAtNode)*/) {
                //System.out.println("wow");
                System.out.println("Value edge at " + edge.getAttributeValue());
                edge.setChild(this.generateTreeHelper(trainingData.splitDataset(attributeAtNode, edge.getAttributeValue()), targetAttribute, nextSplit));
            }
            System.out.println("branch ended");
            return newRoot;
        }
    }

    @Override
    public String getDecision(Row datum) {
        return this.root.getDecision(datum);
    }

    // TODO: Implement methods declared in ITreeGenerator interface!
}
