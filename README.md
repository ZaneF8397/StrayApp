# StrayApp

This app uses firestore in order to store entries into a queryable database.
Images are resized and put into a lower resolution in order to be stored, they are then put into byte arrays then into base 64 to be stored as a string. The reverse is done to get them out of the database.
Other than this everything is rather straightforward and self explanatory.