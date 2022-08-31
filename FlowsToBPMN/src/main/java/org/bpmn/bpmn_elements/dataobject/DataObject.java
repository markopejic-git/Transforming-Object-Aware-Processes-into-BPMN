package org.bpmn.bpmn_elements.dataobject;

import org.bpmn.bpmn_elements.task.Task;
import org.bpmn.randomidgenerator.RandomIdGenerator;
import org.w3c.dom.Element;


import static org.bpmn.step_one.fillxml.fillXMLStepOneRenew.doc;

public class DataObject {

    String id;

    String refId;

    Task associatedTask;

    String name;

    String x;

    String y;

    Element elementDataObject;

    public void setX(String x) {
        this.x = x;
    }

    public void setY(String y) {
        this.y = y;
    }

    public String getX() {
        return x;
    }

    public String getY() {
        return y;
    }

    public DataObject(Task task) {
        this.id = "DataObject_" + RandomIdGenerator.generateRandomUniqueId(6);
        this.refId = "DataObjectReference_" + RandomIdGenerator.generateRandomUniqueId(6);
        this.associatedTask = task;
        this.elementDataObject = doc.createElement("bpmn:dataObjectReference");
        setElementDataObject();
    }

    private void setElementDataObject() {

        this.elementDataObject.setAttribute("dataObjectRef", this.id);
        this.elementDataObject.setAttribute("id", this.refId);
        String name = "TBD";
        this.elementDataObject.setAttribute("name", name);

    }

    public Element getElementDataObject() {
        return elementDataObject;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return this.id;
    }

    public String getRefId() {
        return refId;
    }

    public void setAssociatedTask(Task associatedTask) {
        this.associatedTask = associatedTask;
    }

    public Task getAssociatedTask() {
        return associatedTask;
    }

    @Override
    public String toString() {
        return this.id;
    }
}