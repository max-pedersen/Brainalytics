# MAQuiz - Repository for Brainalytics

This contains the source code for our INFS3634 Android App as part of the group assignment. 

**Our approach:**

The mobile application created enables student to test their knowledge of on Business Intelligence. The structure of the content & quiz are based initially on the course structure of INFS3603. Our core ethos for Brainalytics was a one stop shop to learn, test and compete on Business Analytics. We have achieved this through dedicated content sections in our app, having a live news feed about the latest in business intelligence, having a fully functional quiz function with instant feedback and a leaderboard so you can compete against your friends. 

**Compatibility:**

* Emulator: Pixel XL API 28
* Emulator: Nexus 5X API 27

**Key functionalities:**

* Quiz Component
  * Over 10 different topics to choose from to quiz
  * Library of over 120 questions
  * Question order is randomised
  * Instant feedback after question is answered
  * ProgressBar used as a visual indicator of progress
  * XP Gained depending on questions correct
* News API
  * Fetches the latest news on specific topics of relevance (Artificial Intelligence & Data Visualization)
  * Uses different type scale to identify the different data in the CardView
  * You can click on the card to go the URL that has the whole news article
* Results Component
  * Uses a MPAndroid Library to generate a visual representation of how well you performed
  * Updates from the Quiz Activity to give you a quick summary
  * Use of aethetically pleasing icons to gratify the user
  * Providesa clear path back home
* Content Component
  * Use of dynamic slider view to display large amounts of text
  * Dynamic YouTube FAB to only show when needed
  * YouTube API an extended activity of Content so you don't have to leave the app
  * Gain XP while watching YouTube videos through the API
* Leaderboard Component
  * Live updates the top 10 XP accumulated
  * Uses a clean colour pallete 
  * Provides a layer of compatitiveness to the Brainalytics
  
_Created by The University of New South Wales undergraduate students Alexander Lam (z5164310) & Max Pedersen (z5164270) for Information Systems course INFS3634: Mobile App Development_
