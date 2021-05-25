# image-hoster-app

This Image Hoster app helps in understanding that how the API enpoints can be implemented for different functionalities with the help of Spring framework and observe the results on Swagger - UI.</br>
In this project, the REST API endpoints have been developed in the following controllers:

<h3>Signup-controller:</h3> In this controller, the user will able to sign up for an account.</br>
<h3>Authentication-controller:</h3> After signing up, the user needs to sign in. This controller authenticates the user based on the credentials provided. After authentication, the user will be given an ‘access token’, which will be required to perform any further operation.</br>
<h3>Image-upload-controller:</h3> Using the ‘access token’, the user can upload an image through this controller. But the image is not ‘ACTIVE’ until reviewed by the admin.</br>
<h3>Admin-controller:</h3> The admin needs to review all the images uploaded by users. This controller provides the admin with the details about all the images. Once the admin has reviewed the image, he can update it (if needed) and make it ‘ACTIVE’ through this controller.</br>
