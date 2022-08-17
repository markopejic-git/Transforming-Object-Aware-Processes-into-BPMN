package org.bpmn.flowsObjects;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import org.bpmn.step_one.collaboration.participant.flowsobject.AbstractFlowsObject;
import org.bpmn.step_one.collaboration.participant.flowsobject.FlowsObjectJsonDeserializer;
import org.bpmn.step_one.collaboration.participant.flowsobject.FlowsObjectList;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class ConcreteObjectType extends AbstractObjectType {

    public HashMap<String, ArrayList<AbstractObjectType>> ObjectTypeActionLogs;
    static ConcreteObjectType allObjectTypesMap;
    static HashMap<String, ArrayList<AbstractObjectType>> objectTypeObjectsMap = new HashMap<>();
    static HashMap<String, ArrayList<AbstractObjectType>> userTypeObjectsMap = new HashMap<>();

    @Override
    public String toString() {

        String retString = "";

        for (String name : ObjectTypeActionLogs.keySet()) {
            String key = name;
            String value = ObjectTypeActionLogs.get(name).toString();
            retString += key + "= {" + value + "}" + "\n";
        }

        return retString;
    }

    public ConcreteObjectType(String filename) throws FileNotFoundException {
        setAllObjects(filename);
        setObjectAndUserTypeObjectsSeparately(filename);
        //System.out.println(getAllObjects(filename));
        //System.out.println(getObjectTypeObjects(filename));
        //System.out.println(getUserTypeObjects(filename));
    }

    public void setAllObjects(String filename) throws FileNotFoundException {

        Gson gsonFlowsObjectTypeJsonDeserializer = new GsonBuilder().registerTypeAdapter(AbstractObjectType.class, new FlowsObjectTypeJsonDeserializer()).create();

        allObjectTypesMap = gsonFlowsObjectTypeJsonDeserializer.fromJson(new JsonReader(new FileReader(filename)), ConcreteObjectType.class);

    }

    public void setObjectAndUserTypeObjectsSeparately(String filename) throws FileNotFoundException {

        Gson gsonFlowsObjectJsonDeserializer = new GsonBuilder().registerTypeAdapter(AbstractFlowsObject.class, new FlowsObjectJsonDeserializer()).create();

        FlowsObjectList objectTypeObjectsIdList = gsonFlowsObjectJsonDeserializer.fromJson(new JsonReader(new FileReader(filename)), FlowsObjectList.class);

        objectTypeObjectsIdList.getList().removeAll(Collections.singleton(null));

        ArrayList<String> nameIdList = new ArrayList<>();

        for (int i = 0; i < objectTypeObjectsIdList.getList().size(); i++) {
            nameIdList.add(objectTypeObjectsIdList.getList().get(i).getCreatedActorId());
        }

        for (String key : this.getAllObjects(filename).keySet()) {
            if (nameIdList.contains(key)) {
                objectTypeObjectsMap.put(key, this.getAllObjects(filename).get(key));
            } else {
                userTypeObjectsMap.put(key, this.getAllObjects(filename).get(key));
            }
        }
    }

    public HashMap<String, ArrayList<AbstractObjectType>> getAllObjects(String filename) throws FileNotFoundException {
        return allObjectTypesMap.ObjectTypeActionLogs;
    }

    public HashMap<String, ArrayList<AbstractObjectType>> getObjectTypeObjects() throws FileNotFoundException {
        return objectTypeObjectsMap;
    }

    public HashMap<String, ArrayList<AbstractObjectType>> getUserTypeObjects(String filename) throws FileNotFoundException {
        return userTypeObjectsMap;
    }

    public ArrayList<String> getObjectIdsList() {
        ArrayList<String> temp = new ArrayList<>();

        for (String key : objectTypeObjectsMap.keySet()) {
            temp.add(key);
        }

        return temp;
    }
}