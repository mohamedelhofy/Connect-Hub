/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GroupClass;

import connect.hub.BackEndContentCreation.*;
import connect.hub.User;
import java.util.List;

/**
 *
 * @author رحمه صبرى
 */
public class Group {

    private String groupName;
    private String groupDescription;
    private String groupPhotoPath;
    private List<String> postsId;
    private List<String> members;
    private String primaryAdmin;
    private List<String> admins;


    public Group(String groupName, String groupDescription, String groupPhotoPath) {
        this.groupName = groupName;
        this.groupDescription = groupDescription;
        this.groupPhotoPath = groupPhotoPath;
    }
    

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupDescription() {
        return groupDescription;
    }

    public void setGroupDescription(String groupDescription) {
        this.groupDescription = groupDescription;
    }

    public String getGroupPhotoPath() {
        return groupPhotoPath;
    }

    public void setGroupPhotoPath(String groupPhotoPath) {
        this.groupPhotoPath = groupPhotoPath;
    }

    public List<String> getPostsId() {
        return postsId;
    }

    public void setPostsId(List<String> postsId) {
        this.postsId = postsId;
    }
    
    public void addToPostId(String newPost){
        this.postsId.add(newPost);
    }

    public List<String> getMembers() {
        return members;
    }

    public String getPrimaryAdmin() {
        return primaryAdmin;
    }

    public List<String> getAdmins() {
        return admins;
    }
    
    
}
