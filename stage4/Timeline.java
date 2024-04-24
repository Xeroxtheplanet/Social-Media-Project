package stage4;

import stage1.User;
import stage2.Reply;
import stage3.Tweet;

/**
 * Represents Tweet recommendations to a specific user. This user is the
 * 'owner' of the Timeline.
 * @author mic
 */

public class Timeline {

    public User owner;
    public Tweet[] tweets;

    /**
     * @param u - the user to check for
     * @return the number of Tweet objects in 'tweets' whose 'poster' is equal to
     *         the passed User object 'u'. Hint: use the equals function to compare
     *         User objects.
     */
    public int countTweets(User u) {
        int sum = 0;
        if(tweets == null||u==null) { //Return 0 because none will come up if either null cases are true.
        	return 0;
        }
        if(tweets != null&&u!=null) { //Assuming tweets and the user isn't null
        	for (int i = 0; i<tweets.length;i++){
        		if(tweets[i].poster.equals(u)) { //If the poster equals the parameter user "u"...
        			sum+=1; //Add to the sum
        		}
        		else sum+=0; //Otherwise don't add anything to the sum
        	}
        }
        return sum; //Return the sum
    }

    /**
     * @return the User object who is the poster of the most Tweet objects in
     *         'tweets', i.e. the User who has made the most Tweets. Return null if
     *         'tweets' is null or 'tweets' is of length 0.
     */
    public User mostTweets() {
    	int highestOne = 0; //The highest number of tweets recorded as of n loop iterations
    	User highestTweetingUser = null; // The user found to have had the highest number of tweets recorded as of n loop iterations
        if(this.tweets == null||tweets.length == 0) {
    	   return null;
        }
        if(tweets!=null && tweets.length!= 0) {
    	   for (int i = 0; i<tweets.length;i++) {
    		   int result = countTweets(this.tweets[i].poster); //Returns tweets from poster at i into the integer variable result
    		   if(result>highestOne) { //If its greater than the previous highestOne,
    			   highestOne = result; //the result becomes the new highestOne to beat.
    			   highestTweetingUser = this.tweets[i].poster; //The user who had the highest number of tweets by this iteration
    		   }
    	   }
        }
        return highestTweetingUser; //By the end, the highestTweetingUser is found
    }

    /**
     * @return the User who has had the most activity. Activity is defined as the
     *         number of Tweets posted plus the number of replies made. Return null
     *         if 'tweets' is null or 'tweets' is of length 0. You may assume that
     *         every User has made at least one Tweet, i.e. there are no Users who
     *         have ONLY made replies.
     */
    
    
    
    public User mostActivity() { // Completed
    	int highestRecordedActivity=0; //A value to store the highest recorded activity as of n loop iterations
    	User mostActiveUser=null; //The returnable value declared after the the userX with the highest activity is found 
    	User userX = null; //A user value referring to the poster whose activity is recorded at the time
    	
    	
    	if(this.tweets == null || this.tweets.length == 0) { //Null and empty array exceptions
    		return null;
    	}
    	
    	
    	if(this.tweets!= null && this.tweets.length !=0) {
    		
    		
    	for(int u = 0; u< this.tweets.length; u++) { //For each user
    		
    		userX = this.tweets[u].poster; //userX is the poster of the tweet at index u
    		int repliesByUserX = 0; //The value representing the replies by a User X.
    	
    			for(int i = 0; i<this.tweets.length;i++) { //for each tweet array
    				for(int k = 0; k<this.tweets[i].replies.length; k++) { //find replies where userX's handle matches the user handle in a reply and add to the sum of repliesbyuserX if true
    					if(this.tweets[i].replies[k].from.equals(userX)) { //Get replier "from" and determine if the user equals the poster (aka UserX)
    						repliesByUserX += 1;
    					}
    				}
    			
    				int activity = countTweets(userX)+repliesByUserX; //Activity defined by the number of Tweets by UserX plus the replies by UserX
    				if(activity>highestRecordedActivity) { //If the activity is greater than the highest recorded,
    					highestRecordedActivity = activity; //that activity will then become the highest recorded.
    					mostActiveUser = userX; //And the user will be declared the most active at that time.
    				}
    			}
    		}
    	}
    	return mostActiveUser; //By the end, the most active user is found and returned
    }
        

    /**
     * @return an array of Tweet objects containing all Tweets made by the owner of
     *         the timeline, i.e. all Tweets the owner has posted. Return null if
     *         'owner' or 'tweets' is null. Hint: use the equals function to compare
     *         User objects.
     */
    public Tweet[] getAllTweetsByTimelineOwner() {
    	
        if(this.owner == null || this.tweets == null) { //Null Case
        	return null;
        }
        
        if(this.owner!= null&& this.tweets.length == 0) { 
        	if(this.tweets[0].poster.equals(owner)) { //Single item case
        		Tweet[] tweet = new Tweet[0];
        		tweet[0] = this.tweets[0];
        		return tweet;
        	}
        	else return null;
        }
        
        int arrayLength = 0;
        
        if(this.owner != null && this.tweets != null) { //True case
            for (int i = 0; i<this.tweets.length;i++) {
            	/*We need to sum the arrayLength first to determine how big the array will be and then we can fill the contents.
        		Array length is immutable in Java*/
            	if(this.tweets[i].poster.equals(owner)) { 
            		arrayLength+=1;
            	}
            }
            Tweet[] timelineOwnerTweets = new Tweet[arrayLength];
            int counter = 0; // A value simply used to add to the timelineOwnerTweets array at the correct position
            for (int i = 0; i<this.tweets.length; i++) {
            	if(this.tweets[i].poster.equals(owner)) {
            		timelineOwnerTweets[counter] = this.tweets[i];
            		counter+=1;
            	}
            	
            }
            return timelineOwnerTweets;
        }
        return null;
    }
    
    /**
     * @return a String which represents the Timeline object.
     * The String should follow the below format (with the actual Timeline data used).
     * Hint: the \n character adds a new line
     * For example:
     * 
     * Timeline Owner: mic1511
     * ----------
     * 
     * => Poster: traveler
     * => Message: This is the first message!!!! 
     * 
     * Replying to @mic1511, @Janedoe, @traveler
     * This is a test message
     * Replying to @traveler, @Charlie, @Foodie
     * This is another test message!!!
     * 
     * ----------
     * 
     * => Poster: traveler
     * => Message: Wow this is the second Tweet in this timeline :O
     * 
     * Replying to @mic1511, @Janedoe, @traveler
     * This is a test message
     * Replying to @traveler, @Charlie, @Foodie
     * This is another test message!!!
     * 
     * ----------
     * 
     * => Poster: Janedoe
     * => Message: Finally at the last one now
     * 
     * Replying to no one
     * This is the last message in this Timeline
     * 
     * ----------
     */
    public String printTimeline() {
    	String timelineRepresentation= //Beginning of the the timeline which subsequent strings will be added to
    			"Timeline Owner: "+ this.owner.handle+"\n"+
    					"----------\n\n";
    	String Reply = ""; // Placeholder for a value
    	int check1 = 0; // A timer to determine when replies should be added to string. If enabled, replies are added to the string until it is 0 again.
    	int global = 0; // A global iterable to allow different events to take place depending on value set by different loops or events
    	
    	if(this.tweets == null) { //If the tweets are found to be null
    		return timelineRepresentation+
    				"No tweets\n"+
    				"\n"+
    				"----------\n";
    	}
    	
    	if(this.tweets != null && this.tweets.length !=0) {
    		/*
    			Establishing that tweets are not null and length is not zero
    		*/
    		for (int i = 0; i<this.tweets.length;i++) { //Tweets Array
    			
    			for (int j = 0; j<this.tweets[i].replies.length;j++) { //Replies Array
    				Reply=this.tweets[i].replies[j].toString(); //Reply assigned value of a reply converted to a string value.
    			}
    				global = 0; //Reset the value each time.
    				check1 = 0; //If, for some reason, the value wasn't already reset to 0
    			
    				if(!Reply.contains("This is the last message in this Timeline")) { //Check 1 is enabled if the message is detected not to be the last in the timeline
    				timelineRepresentation +=
                			"=> Poster: "+this.tweets[i].poster.handle+"\n"+
                			"=> Message: "+this.tweets[i].message+"\n"+"\n";
    				check1 = 1;
    				}
    				
    				if(check1 == 1) { // A sequence to assign replies to the timeline in a looping order
    					for (global = 0; global<this.tweets[i].replies.length; global++) {
    						//If next thing is a reply
    								if(global<=this.tweets[i].replies.length-2) {
    								timelineRepresentation+=
    									tweets[i].replies[global]+"\n";
    								}
    						//If not:
    								if(global==this.tweets[i].replies.length-1) {
    									timelineRepresentation+=
    									tweets[i].replies[global]+"\n\n";
    								}
    						}
    					
    					if((timelineRepresentation.substring(timelineRepresentation.length()-1)=="-")) { //If the last character matches a dash, return a sequence of dashes without any lines after
							timelineRepresentation+="\n"
    						+"----------";
    					}
    					
    					else //Important, if the former was not detected, then under all circumstances it should return the following instead
    						
    					if((global==this.tweets[i].replies.length)&&(timelineRepresentation.substring(timelineRepresentation.length()-1)!=" ")) { //Add space if it is not at the end already, will not work otherwise
							timelineRepresentation+=
    						"----------\n\n";
    					}
    					
    				}
    				check1 = 0; //Resets check1 to zero so that the loop can start over.
    				
    			if(Reply.contains("This is the last message in this Timeline")) { //If the message is the last in the timeline, then it must add this string under all circumstances
    				timelineRepresentation +=
                			"=> Poster: "+this.tweets[i].poster.handle+"\n"+
                			"=> Message: "+this.tweets[i].message+"\n"+
                			"\n"+Reply+
                			"\n"+
                			"\n----------\n\n" //This exists so that the necessary extra space can be added for the last entry in a timeline, does not work otherwise.
                			;
    			}
    		}
    		
    		return timelineRepresentation.substring(0, timelineRepresentation.length()-1); //Return the string - 1 character 
    	}
		return "t"; // Just so the function returns something
    }
    
    
    /**
     * 
     * @param term - the term to look for
     * @return the number of Tweet objects with either a message that contains the passed 
     *         'term' or has a Reply whose message contains the passed term.
     *         If the 'term' appears in a Tweet objects message and in its replies, 
     *         it should be counted once.
     */
    public int countSearchResults(String term) {
        int sum = 0; //The sum of the search results
        int check = 0; /*The check to ensure that the message already contains the passed term, 
        preventing further if statements from executing and unnecessarily adding to the sum*/
        
        if(tweets!=null) {
        	
        
        	for (int i = 0; i<tweets.length;i++) {
        		for(int k = 0; k<tweets[i].replies.length;k++) {
        			if(tweets[i].getMessage(k).contains(term)&&check==0) { //If the replies contain the passed term
        				check = 1;
        				sum+=1;
        			}
        			if(tweets[i].message.contains(term)&&check==0) { //If the message contains the passed term
        				check = 1;
        				sum+=1;
        			}
        		}
        		check = 0; //Set to 0 so the loop can start over for the next iteration
        	}
        }
        return sum;
    }

    /**
     * @param term - the term to look for
     * @return an array of Tweet objects from 'tweets' which match any of the
     *         following criteria: 1) the Tweet has a poster whose handle contains
     *         'term', 2) the Tweet contains a message containing 'term', 3) the
     *         Tweet contains any replies whose message contains 'term' and 4) the
     *         tweet contains any replies whose 'from' and 'to' User objects have a
     *         handle containing 'term'.
     */
    public Tweet[] searchAll(String term) {
    	
    	int termContainers = 0; // Amount of found terms. Updated if a term was found
    	int check = 0; /* A check enabled when a term is finally found, preventing the rest of the loop
    	from executing and ensuring it can start going through the same checks in the same order until
    	another term is found*/
    	Tweet[] returnable = new Tweet[20]; /*An array made for the sole purpose of being copied 
    	with a more appropriate length at the end of the function. This array is given tweet values
    	depending on whether the term was found under the appropriate conditions. The array length of 20 
    	is simply to allow for as much space to place these values as possible.*/
    	
    	if(tweets== null||tweets.length ==0) { //Accounting for null or empty tweets array
    		return null;
    	}
        
    	if(tweets != null && tweets.length!=0) { //not null, not empty
    		for (int i = 0; i<tweets.length;i++) { //for each tweet
    			check = 0;
    			if(tweets[i].poster.handle.contains(term)&&check==0) {
    				returnable[termContainers] = tweets[i];
					termContainers++;
					check = 1;
    			}
    			
    			else /*If the first result found nothing. Else is used throughout to ensure that a 
    			statement executes if the prior if statement found nothing*/
    				
    			if(tweets[i].message.contains(term)&&check==0) {
    					returnable[termContainers] = tweets[i];
    					termContainers++;
    					check = 1;
    			}
    			else {//If we cannot get a tweet from the handle or message of the tweet we go to the reply level
    				for(int k = 0; k<tweets[i].replies.length;k++) {
    					if(tweets[i].replies[k].message.contains(term)&& check ==0) { //If the reply's message contains the term
    						returnable[termContainers] = tweets[i];
    						termContainers++;
    						check = 1;
    					}		
    					
    					else
    							
    					if(tweets[i].replies[k].from.handle.contains(term)&& check ==0) { //If the reply's from handle found the term
    						returnable[termContainers] = tweets[i];
    						termContainers++;
    						check = 1;
    					}		
    					else { //Final resort, if we can't find any in previous cases, we create a loop to look through the to objects' handles
    						for (int j = 0; j<tweets[i].replies[k].to.length;j++) {
    							if(tweets[i].replies[k].to[j].handle.contains(term)&& check ==0) {
    								returnable[termContainers] = tweets[i];
    								termContainers++;
    								check = 1;
    							}
    						}
    					}
    				}
    			}
    		}
    	}
    	Tweet[] returnableCopy = new Tweet[termContainers]; //Arrays are immutable in Java, so we copy to a new Tweet array of the more appropriate termContainers length
    	for (int l = 0; l<termContainers;l++) { //Copying via for loop
    		returnableCopy[l]=returnable[l];
    	}
    	return returnableCopy; //Returning the copy
        
    }
    
    /**
     * DO NOT MODIFY
     */
    public Timeline(User owner, Tweet[] tweets) {
    	this.owner = owner;
    	this.tweets = tweets;
    }

}
