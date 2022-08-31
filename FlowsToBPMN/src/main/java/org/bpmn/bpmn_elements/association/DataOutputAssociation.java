package org.bpmn.bpmn_elements.association;

import org.bpmn.bpmn_elements.dataobject.DataObject;
import org.bpmn.randomidgenerator.RandomIdGenerator;

import org.w3c.dom.Element;

import static org.bpmn.step_one.fillxml.fillXMLStepOneRenew.doc;

public class DataOutputAssociation {

    String id;
    DataObject targetRef;
    Element elementDataOutputAssociation;

    Element elementTarget;

    public DataOutputAssociation(){

        this.id = "DataOutputAssociation_" + RandomIdGenerator.generateRandomUniqueId(6);
        setElementDataOutputAssociation();

    }

    private void setElementDataOutputAssociation() {

        this.elementDataOutputAssociation = doc.createElement("bpmn:dataOutputAssociation");
        this.elementDataOutputAssociation.setAttribute("id", this.id);

        this.elementTarget = doc.createElement("bpmn:targetRef");
        this.elementTarget.setTextContent("TBD");
        this.elementDataOutputAssociation.appendChild(elementTarget);

    }

    public void setTargetRef(DataObject targetRef) {
        this.targetRef = targetRef;
    }

    public void setElementTarget(Element elementTarget) {
        this.elementTarget = elementTarget;
    }

    public Element getElementTarget() {
        return elementTarget;
    }

    public Element getElementDataOutputAssociation() {
        return elementDataOutputAssociation;
    }

    public String getId() {
        return id;
    }
}