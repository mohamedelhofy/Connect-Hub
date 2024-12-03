/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connect.hub.BackEndContentCreation;
import connect.hub.*;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author رحمه صبرى
 */
public class ContentManagment {
    // I need to conect every post or story with it's own user in it's array list 
    SocialContent content;
    List<SocialContent> contentList = new ArrayList<>();

    public ContentManagment(SocialContent content) {
        this.content = content;
    }
    
    public void add(){
       contentList.add(content);
    }
    
    public void remove(){
        contentList.remove(content);
    }
  
}
