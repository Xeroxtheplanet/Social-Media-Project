package stage3;

import stage1.User;
import stage2.Reply;

public class Tweet {
    public User poster;
    public String message;
    public Reply[] replies;
    public int views;

    /**
     * @param idx - the idx to retrieve at
     * @return the Reply object from the 'replies' instance variable at position
     *         'idx'. Return null if replies is null, or position 'idx' does not
     *         exist.
     */
    public Reply getReply(int idx) {
    	if (this.replies == null|| idx>=replies.length||idx<0) { // if idx not existing or replies or null, just return null
    		return null;
    	}
    	else
    	return this.replies[idx]; //Return instance replies at the specified index
        
    }

    /**
     * 
     * @param idx - the idx to retrieve at
     * @return the message inside the Reply object from the 'replies' instance
     *         variable at position 'idx'. Return null if replies is null or
     *         position 'idx' does not exist.
     */
    public String getMessage(int idx) {
    	if (this.replies == null|| idx>=replies.length||idx<0) { // Accounting for null and empty exception and invalid idx values
    		return null;
    	}
        return this.replies[idx].message; //Gets the message from the specified index
    }

    /**
     * @return The total number of views from the Tweet calling object and all of
     *         its replies.
     */
    public int totalViews() {
    	int sum = 0; // sum to get replies' views
    	if(this.replies == null) { // null exception
    		return this.views;
    	}
    	else
    	for (int i = 0; i<this.replies.length;i++) {
    		sum += this.replies[i].views; //Sum is given the views of each reply and added sequentially
    	}
    	
        return this.views + sum; //Returns the tweet calling object views added with the sum
    }

    /**
     * @return The reply object in 'replies' with the highest number of views.
     * 		   In case of a tie, the first Reply object with the highest number
     *         of views should be returned.
     *         Return null if 'replies' is null.
     */
    public Reply mostPopularReply() {
    	int highest1 = 0; //The highest found value. If exceeded, the next found value becomes the highest
    	int returnableArrayIndex = 0;
        if (this.replies == null) { //Accounting for null and empty exceptions
        	return null;
        }
        if(this.replies.length == 0) {
        	return null;
        }
        if(this.replies.length == 1) { // Accounting for single item exception
        	return this.replies[0];
        }
        if (this.replies.length!=0||this.replies.length!=1) {
        	highest1 = this.replies[0].views; //Grabbing the first of the replies array's views to compare to later ones.
        	for (int i = 1; i<this.replies.length-1;i++) {
        		if (this.replies[i].views>highest1) {
        			highest1 = this.replies[i].views;
        			returnableArrayIndex = i; //Grabs which index the highest value was found at
        		}
        	}
        return this.replies[returnableArrayIndex];
        }
        return this.replies[returnableArrayIndex];
    }

    /**
     * @param handle - the User handle to check for
     * @return the first Reply object FROM a User with the passed handle. Return
     *         null if no users match the passed handle.
     */
    public Reply findReplyFrom(String handle) {
    	
    	//Null case
    	
    	if(this.replies == null) {
    		return null;
    	}
    	
    	//True case
    	
    	if(this.replies!=null) {
        	for (int i = 0; i<replies.length; i++) {
        		if(this.replies[i].from.handle==handle) { //If the user's handle matches the handle typed in
        			return replies[i];
        		}
        	}
    	}
        return null; //Any other exception will default to this.
    }

    /**
     * @param handle - the User handle to check for
     * @return the first Reply object TO a user with the passed handle. Return null
     *         if no users match the passed handle. Hint: you will need a nested
     *         loop.
     */
    public Reply findReplyTo(String handle) {

    	//Null case
    	
    	if(this.replies == null) { //Accounting for null exception
    		return null;
    	}
    	
    	//True case
    	
    	if(this.replies!=null) {
    		for (int i = 0; i<replies.length; i++) { //Accessing the replies array
    			for(int k = 0; k<this.replies[i].to.length;k++) { //Accessing the to array
    				if(this.replies[i].to[k].handle==handle) { //Should the user in the to array match the handle typed in
        				return replies[i]; //Return the replies
    				}
    			}
    		}
    	}	
        return null; //Any other exception will default to this.
    }

    /**
     * (HD level question)
     * 
     * @return a String array containing all the hashtags in the instance variable
     *         'message'. Return empty array if no hashtags are found. A hashtag is
     *         defined as a sequence of characters starting with a '#' character
     *         followed by one or more LOWERCASE letters and is terminated by a space
     *         character or the end of the string. The hashtag must be at the start
     *         of the message or follow a space. e.g. "#test" is a valid hashtag in
     *         the following strings: "#test message", "not a #test" and "hashtag
     *         #test in the middle".
     */
    public String[] getHashtags() { //Lachlan's note: Coming back to this once all others are finished
        // TODO To be completed
        return null;
    }

    /**
     * DO NOT MODIFY
     * 
     * @return true if the calling object and the passed object are equal, false
     *         otherwise
     */
    public boolean equals(Object other) {
        if (!(other instanceof Tweet))
            return false;
        Tweet otherTweet = (Tweet) other;
        return this.poster.equals(otherTweet.poster)
                && this.message.equals(otherTweet.message)
                && this.replies == otherTweet.replies
                && this.views == otherTweet.views;
    }
    
    /**
     * DO NOT MODIFY
     */
    public Tweet(User poster, String message, Reply[] replies, int views) {
    	this.poster = poster;
    	this.message = message;
    	this.replies = replies;
    	this.views = views;
    }
}
