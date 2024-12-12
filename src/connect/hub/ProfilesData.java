/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connect.hub;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Compu City
 */
public class ProfilesData {
    private ReadProfile profiles =new ReadProfile("profile.json");
    private List<Map<String, Object>> datalist = profiles.getDataAsListOfMaps() ;
    private Map<String, Object> userData=new HashMap();
    public ProfilesData(String userId ) {
        for(Map<String, Object> data:  datalist){
            if(userId.equals(data.get("userId")))
                    userData=data;
        }
    }

    public Map<String, Object> getData(){
        return userData;
    }

    public void setData(Map<String, Object> user) {
        for (int i = 0; i < datalist.size(); i++) {
            if (user.get("userId").equals(datalist.get(i).get("userId"))) {
                datalist.set(i, user);
                break;
            }
        }
        SaveProfile profiles=new SaveProfile("profile.json",datalist);
        profiles.savaFromJSON();
    }
}

