# FacebookPhotoGallery

![FacebookPhotoGallery](https://raw.githubusercontent.com/CharlesAE/FacebookPhotoGallery/master/photo_gal.jpg "SR Studios")

Simple Android app which populates a GridView with images from a Facebook Page album, using Facebook's Android SDK, Graph API and Access Tokens


Requires usage of Facebook's Graph API (https://developers.facebook.com/docs/graph-api/overview)
and Android SDK (https://developers.facebook.com/docs/android)


  --- TO USE THE APP IN THIS REPO ---

- You MUST first create an application on facebook via the developer console (https://developers.facebook.com/apps/)
- --- Make note of the Application ID
- Head to the Graph API Explorer Tool (https://developers.facebook.com/tools/explorer/)
- Click Submit on the default query. 
- --- Make note of the User ID "id"
- Select your application from the dropdown box titled "Application".
- Generate a PAGE Access Token.
- Place generated Access Token, Application ID and User ID in their corresponding tags in the strings.xml file


Please note this app is built for getting an album from a Facebook PAGE. To get albums/photos from a USER would require you making your own modifications to the GraphRequest method called in MainActivity 

For more info (https://developers.facebook.com/docs/reference/android/current/class/GraphRequest/)

