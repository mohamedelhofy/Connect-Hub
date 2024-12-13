package connect.hub.UserSearchBackEnd;

import connect.hub.BackEndFriendManagement.FriendshipDatabase;
import connect.hub.User;
import java.util.ArrayList;


public class SearchedListsDatabase {
    private String keyWord;
    FriendshipDatabase friendshipDatabase;

    private ArrayList<User> friendsList;
    private ArrayList<User> receivedPendingList;
    private ArrayList<User> sentPendingList;
    private ArrayList<User> suggestedList;

    private ArrayList<User> searchedFriendsList;
    private ArrayList<User> searchedReceivedPendingList;
    private ArrayList<User> searchedSentPendingList;
    private ArrayList<User> searchedSuggestedList;

    public SearchedListsDatabase(String userId) {
        this.friendshipDatabase = new FriendshipDatabase(userId);
        this.searchedFriendsList = new ArrayList<>();
        this.searchedReceivedPendingList = new ArrayList<>();
        this.searchedSentPendingList = new ArrayList<>();
        this.searchedSuggestedList = new ArrayList<>();
    }

    public ArrayList<User> getSearchedFriendsList(String keyWord){
        friendsList = friendshipDatabase.getFriendList();
        searchedFriendsList.clear();
        updateSearchList(keyWord, friendsList, searchedFriendsList);
        return searchedFriendsList;
    }
    
    public ArrayList<User> getSearchedReceivedPendingList(String keyWord){
        receivedPendingList = friendshipDatabase.getReceivedPendingList();
        searchedReceivedPendingList.clear();
        updateSearchList(keyWord, receivedPendingList, searchedReceivedPendingList);
        return searchedReceivedPendingList;
    }
    
    public ArrayList<User> getSearchedSentPendingList(String keyWord){
        sentPendingList = friendshipDatabase.getSentPendingList();
        searchedSentPendingList.clear();
        updateSearchList(keyWord, sentPendingList, searchedSentPendingList);
        return searchedSentPendingList;
    }
    
    public ArrayList<User> getSearchedSuggestedList(String keyWord){
        suggestedList = friendshipDatabase.getSuggestedList();
        searchedSuggestedList.clear();
        updateSearchList(keyWord, suggestedList, searchedSuggestedList);
        return searchedSuggestedList;
    }
    
    public void updateSearchList(String keyWord, ArrayList<User> list, ArrayList<User> searchedList){
        for(User user : list){
            if(user.getUsername().toLowerCase().contains(keyWord))
                searchedList.add(user);
        }
    }


}