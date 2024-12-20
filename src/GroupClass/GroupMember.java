/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GroupClass;

import NotificationsBackEnd.NotificationsManager;
import connect.hub.BackEndContentCreation.Post;
import connect.hub.BackEndContentCreation.StoreIntoJSON;
import connect.hub.User;
import java.awt.Image;

/**
 *
 * @author Compu City
 */
public class GroupMember extends Group{
    User user=User.getInstance();

    public GroupMember(String groupName, String groupDescription, String groupPhotoPath) {
        super(groupName, groupDescription, groupPhotoPath);
    }
    public GroupMember(){}
    public void addPost(String contentText, Image image){
        Post newPost = new Post(this.user.getUserId(), contentText,image);
        StoreIntoJSON jsonFile = new StoreIntoJSON(newPost);
        jsonFile.addPostsToJSON();
        super.getPostsId().add(newPost.getContentId());
        NotificationsManager.getInstance().addNewPostNotification(newPost.getContentId(), super.getGroupName());
    }



    public void leaveGroup(Group group, User usr){
        group.getMembers().remove(usr.getUserId());
    }
}
