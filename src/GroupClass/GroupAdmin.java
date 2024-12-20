/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GroupClass;

import connect.hub.BackEndContentCreation.Post;
import connect.hub.BackEndContentCreation.RemoveFromJSON;
import connect.hub.BackEndContentCreation.StoreIntoJSON;
import connect.hub.User;
import java.awt.Image;

/**
 *
 * @author Compu City
 */
public class GroupAdmin extends GroupMember {

    public GroupAdmin(String groupName, String groupDescription, String groupPhotoPath) {
        super(groupName, groupDescription, groupPhotoPath);
    }
    public GroupAdmin(){}

    public void removeMember(String userId){
        super.getMembers().remove(userId);
    }
    public void removePost(Post post){
        RemoveFromJSON removeFromJSON =new RemoveFromJSON(post.getAuthorId(),post.getContentId(),post,"Posts.json");
        removeFromJSON.removeFromJSON();
        super.getPostsId().remove(post.getContentId());   
    }
    public void approveMember(String userId){
        if (super.getMemberRequst().contains(userId))
            super.getMembers().add(userId);
        super.getMemberRequst().remove(userId);
    }   
    public void declineMember(String userId){
        super.getMemberRequst().remove(userId);
    }   
    public void editPost(Post post,String contentText, Image image){
        RemoveFromJSON removeFromJSON =new RemoveFromJSON(post.getAuthorId(),post.getContentId(),post,"Posts.json");
        removeFromJSON.removeFromJSON();
        if(image!=null) post.setImage(image);
        if(contentText!=null) post.setContentText(contentText);
        StoreIntoJSON jsonFile = new StoreIntoJSON(post);
        jsonFile.addPostsToJSON();      
    }
    
    public void leaveGroup(Group group, User uer){
        group.getAdmins().remove(uer.getUserId());
    } 
}
