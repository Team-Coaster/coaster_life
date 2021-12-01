COASTER LIFE App Design Project - README
===

# COASTER LIFE

## Table of Contents
1. [Overview](#Overview)
1. [Product Spec](#Product-Spec)
1. [Wireframes](#Wireframes)
2. [User Stories & Video Walkthroughs](#user-stories--video-walkthroughs)
2. [Schema](#Schema)

## Overview
### Description
Social media app that allows coaster enthusiast to track their coaster/ride count and allows allows them to take picutres and meet other people. 

### App Evaluation
- **Category:** Social/Travel
- **Mobile:** Camera, location, real time??
- **Story:** Allows our users to track how many different rides they have been on and how many times they rode their favorite ride(s) but also allows them to find new park friends and lets them take pictures for their accounts
- **Market:** The market is pretty niche but has a dedicated community. Coaster enthusiats love to keep a track of their ride count and also talk about their favorite rides. Also with the app they could get their other friends and create a bigger community
- **Habit:** This is not an everyday app, but some people could use it a few times a week, especially those who "live" at theme/amusement parks or those who travel a lot.
- **Scope:** The scope of this project is a little large but I think that it is feasiable to accomplish, especially a stripped down aspect if we just focus on the regional Six Flags park that is "right down the street" for testing purposes.

## Product Spec

### 1. User Stories (Required and Optional)

**Required Must-have Stories**

* User can Login
* User can Register
* User can check in at a park
* User can look at list of rides and marks which ones they have rode/ridden
* look at your personal ride count
* friend finder
* user can take pictures and put them in their profile

**Optional Nice-to-have Stories**

* Look at a leaderboard for amount of individual rides a user has ridden
* leaderboard for individual rides
* Users once in the park can update wait times for rides to let others know
* Can click on a ride and get detailed view of coaster/ride stats
* app badges ie. your first coaster, your first park, etc

### 2. Screen Archetypes

* Login
   * User can Login
* Register 
   * User can create Create a new account
* Settings
    * checking into a park
* Stream
    * List of rides and look at your ride count
* Map
    * Friend finder
* Creation
    * Taking pictures of the park

### 3. Navigation

**Tab Navigation** (Tab to Screen)

* Profile
* Take Picture
* Friend Finder

**Flow Navigation** (Screen to Screen)

* Register Screen
   => Login
* Login Screen
   => Profile
* Profile
   => Pictures and List of rides selection
* Picture creation
   => Goes to Profile Picture Screen 

## Wireframes
<img src="https://imgur.com/0i51TPJ.gif" width=600>

### [BONUS] Digital Wireframes & Mockups
<img src="https://imgur.com/NCVvRvQ.gif" width=600>

### [BONUS] Interactive Prototype

<img src="https://imgur.com/bmEK9KJ.gif" width=250>

## User Stories & Video Walkthroughs


### Sprint 1 User Stories
The following **required** functionality is completed:

- [x] User can Login 
- [x] User can Register


The following **bonus** functionality is completed:
- [x] User Login or Registration is persistent

### Sprint 1 Video Walkthrough
Here's a walkthrough of implemented user stories:

<img src='https://imgur.com/HfWTCU9.gif' title='Video Walkthrough' width='349' height='677' alt='Video Walkthrough 1' />

### Sprint 2 User Stories
The following **required** functionality is completed:

- [x] User can view Current Location
- [x] User can view Profile Screen
- [x] User can view Friend Screen
- [x] User can logout

### Sprint 2 Video Walkthrough
Here's a walkthrough of implemented user stories:

<img src='https://imgur.com/a/eZEdfVh.gif' title='Video Walkthrough' width='349' height='677' alt='Video Walkthrough 2' />



### Sprint 3 User Stories
The following **required** functionality is completed:

- [x] User can view Ride Log
- [x] User can view Photo Feed

### Sprint 3 Video Walkthrough
Here's a walkthrough of implemented user stories:

<img src='' title='Video Walkthrough' width='' alt='Video Walkthrough 3' />


### Sprint 4 User Stories
The following **required** functionality is completed:

- [ ] User can Message other users of the app
- [ ] User can access friends Photos
- [ ] User can access friends Ride Log
- [ ] User can access friends Location
- [ ] User can access friends Profile Screen

The following **bonus** functionality is completed:
- [ ] Consistent Color & Styling across the app
- [ ] Message Screen

### Sprint 4 Video Walkthrough
Here's a walkthrough of implemented user stories:

<img src='' title='Video Walkthrough' width='' alt='Video Walkthrough 4' />


## Schema 

### Models
Profile
| Property | Type  |       Description      |
| --- | --- | ---|
|objectid| String| Unique id for user|
|Image| File| profile image of user|


Friends List
| Property | Type  |       Description      |
| --- | --- | ---|
|objectid| String| Unique id for user|
|Friend| Pointer to User| Friend name
|Friend Photo| Pointer to User| Friend Photo
|Friend online| Pointer to User| Is person online

Ride List
| Property | Type  |       Description      |
| --- | --- | ---|
|objectid| String| Unique id for user|
|Ride name| String| Name of ride
|Has rode| Bool| If they have rode at least once
|Ride count| Number| Amount of times user has rode


Image tab
| Property | Type  |       Description      |
| --- | --- | ---|
|objectid| String| Unique id for user|
|Image| File| images of the rides that a user posted|

Messaging
| Property	| Type | Description |
|-----------|------|----------------|
| Sender ID	| String | The person’s ID who sends the message |
| Receiver ID |	String |The person’s ID who receives the message |
| Conversation ID | String | Unique ID for the conversations between the sender and receiver |
| Message Kind | String | The type of the message |
| Message | String | The message being sent |
| Sent date | String | The date/time of message |
| isRead | Bool | Flag showing the status of message |

### Networking
   - Message Screen
      - (Read/GET) Query all messages in a conversation
      ```swift
          let query = PFQuery(className:"Message")
            query.whereKey("author", equalTo: currentUser)
            query.whereKey("receiver", equalTo: receiverID)
            query.order(byDescending: "createdAt")
            query.findObjectsInBackground { (messages: [PFObject]?, error: Error?) in
          if let error = error { 
              print(error.localizedDescription)
           } else if let posts = posts {
              print("Successfully retrieved \(messages.count) Messages.")
          // TODO: Do something with Messages...
           }
        }

      ```
      - (Create/POST) Create a new message

      - (Read/GET) Query message isRead status
 
* Profile
    * (Read/GET) Query logged in user object
    * (Update/PUT) Update user profile image
* Image
    * (Read/GET) Query logged in users photo objects
    * (Update/PUT)  show the users photos
    * (Delete) Delete a photo
* Friend List
    * (Read/GET) Query logged in users friends
    * (Update/PUT) Users friends photo
    * (Update/PUT) Users friends status
    * (Delete) Delete a friend
* Ride List
    * (Read/GET) Query logged in users ride list data
    * (Update/PUT) number of times user has rode the ride
    
