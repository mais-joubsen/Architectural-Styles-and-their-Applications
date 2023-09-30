## **Module 2:** Web-Services Architecture

<hr>

### **Assignment**

The system provided to you (Brimula) is an incomplete platform inspired by Canvas, tailored for the university staff, primarily the examiners and lecturers. The system's scope has been slimmed to shift the emphasis from coding to software architecture. Currently, the DIT342 program (Web Development) is the sole course that provides instruction on Web Services, making it a crucial influence on this platform's design. Thus, to ensure simplicity and maintain the focus on software architecture, we have incorporated the same structure and technology from DIT342 into this system.
Upon completing this assignment, we expect students to have attained a comprehensive understanding of Web Services Software Architecture and to have witnessed its practical implementation in the context of software architecture.

### **Description**
As mentioned earlier, Brimula is an exclusive staff platform designed specifically for examiners and lecturers. The ideal version of the system aims to provide robust authentication and authorization mechanisms, granting users access to their respective individual courses and data.
In addition to the authentication features, the platform offers a host of other useful instruments to its users. These include dedicated college pages, personalized profiles, and a convenient calendar feature to assist in managing their academic schedules effectively.
The user experience is further enhanced with a user-friendly home page, where individuals can effortlessly access essential information. This includes upcoming lectures, details about their next salary payment, and an overview of the courses they are responsible for.
Within the platform, each course is equipped with four distinct sub-pages, namely the home page, student list, student addition, and file management. These pages are intended to streamline course administration and ensure seamless interaction with students and relevant course materials.
However, it is important to note that the system is currently under development and remains incomplete. As it stands, users will only witness partial functionality, with certain sections of the described system yet to be fully operational.
For this assignment, you are tasked with completing the functions related to the **"Students List"** and **"Adding Students"** pages for each course. The specific areas that require development have been marked with "TODO" tags in both the backend and frontend of the system.
Please bear in mind the individual requirements specified for each method that necessitates implementation. 

You are warmly invited to take the system to the next level by incorporating additional functionalities or implementing those that have been described above but are yet to be realized (such as the staff page, authentication, and more).
Feel free to impress us with your innovative ideas and creative solutions as you enhance the platform's capabilities. Introducing new features or addressing the existing gaps will undoubtedly elevate the user experience and contribute to the system's overall effectiveness.
By embracing this opportunity to extend the system's functionalities, you have the chance to showcase your skills and demonstrate your prowess in developing comprehensive and cutting-edge solutions. Your contributions will be highly valued, and we look forward to witnessing the remarkable enhancements you bring to the Brimula platform.

### **Deliverable**
Create a screencast that lasts 2-5 minutes, showcasing the completed system and demonstrating its functionalities, as well as the error handling implemented for those functionalities as requested. Once the screencast is ready, please submit it through the designated submission portal.
Moreover, you shall answer the questions in the provided document on Canvas. It consists of few questionns that you have to answer and a component diagram of your system that you have to provide. When you are done with all your tasks (the assignment as a whole), follow the submission instructions in the root readme.

<hr>

### **Set up**
### Source Code
1. Clone the repository into your local computer
2. Open the directory in your desired IDE (preferably VS-code or WebStorm)
3. Install node-modules and package-lock.json for the project by running the commands below:

For server:
```
cd web-services
cd server
npm install
```
For client:
```
cd web-services
cd client
npm i --legacy-peer-deps
```
4. Those taking the course DIT342 in parallel, can skip this step since you have already set this up. Otherwise, follow the instructions in this link (for your own OS) to set up your database and starting the mongo shell. Keep in mind that we use MongoDB for this module: https://github.com/joe4dev/dit032-setup/tree/master
5. Open two terminals. One for Client and one for Server
6. Run the commands below to set up your server:
```
cd web-services
cd server
npm run dev
```
```
It should result to:

Express server listening on port 3000, in development mode
Backend: http://localhost:3000/api/
Frontend (production): http://localhost:3000/
Connected to MongoDB with URI: mongodb://localhost:27017/brimula
```

7. Run the commands below to set up your client:
```
cd web-services
cd client
npm run serve
```

It should result to:
```
 DONE  Compiled successfully in 322ms 
  App running at:
  - Local:   http://localhost:8080/ 
  - Network: http://192.168.1.51:8080/ 
```
Now our client and server is all good to go!

#### Known issues
The project should work for all the npm versions. Nonetheless, because of differences in every computer and versions, the setup can be slightly different for each person. 

1) If you faced this error message `Error message "error:0308010C:digital envelope routines::unsupported"`, Follow the instructions in the first answer provided under this post: [stackoverflow](https://stackoverflow.com/questions/69692842/error-message-error0308010cdigital-envelope-routinesunsupported)

### Postman
1. Download/Open [Postman](https://www.postman.com/downloads/)
2. Create a new collection in postman by importing "server.postman_collection.json" from server->tests 

You will find 18 Postman requests that you are encouraged to utilize while working on this assignment. These requests encompass nearly all the CRUD functionalities/requests that one might require, some of which are not necessarily integrated into Brimula. Within these requests, you will notice the presence of certain variables that are currently marked as red because they do not exist. To address this, you have two options: either create the environments/variables manually under the environments tab, or alternatively, input the required IDs directly as needed. Don't forget that each request can be changed based on your needs and the data provided (e.g. the body of a post request) are just place holders for you to put in your own data. Moreover, the collection is not designed to be ran as a whole (collection). Each request is to be ran/sent separately. For more information regarding Postman we recommend taking a look at the [documentation](https://learning.postman.com/docs/sending-requests/requests/) 

#### Database
1. Download/Open [MongoDB compass](https://www.mongodb.com/docs/compass/master/install/) on your computer
2. When installed, on the home page, you will be offered to make a new connection to your localhost (mongodb://localhost:27017). Go ahead and make the connection

Everything should be functioning correctly now. Please proceed and create your first course using the Postman request that has been provided to you. Once you have made the request, check the responses in the server terminal, and also take a look at your MongoDB Compass. You should notice a new collection specifically for courses has been created in the database.

### **Assignment Instructions**
#### 1)
In the client, open "CourseAddStudentPage.vue" file (client->src->components) and implement the functionality for sending a request for creating a student item and adding it to the list of all students under that specific course. The method is called "addStudent()" and can be found marked as TODO. Further requirements for the functionality are stated as comments.
```
addStudent() {
      // TODO
    }
```
#### 2)
In the server under "controllers" open "courses.js" file and implement the endpoint for adding a student under a course. Further requirements for the endpoint are stated as comments.
```
router.post("/api/courses/:id/students", function (req, res, next) {
    // TODO
});
```

#### 3)
In the client, open "CourseStudentListPage.vue" file (client->src->components) and implement the functionality for sending a request for retrieving the list of all students under that specific course. The method is called "getAllStudents()" and can be found marked as TODO. Further requirements for the functionality are stated as comments.
```
getAllStudents() {
      // TODO
    }
```

#### 4)
In the server under "controllers" open "courses.js" file and implement the endpoint for getting all students of a course. Further requirements for the endpoint are stated as comments.
```
router.get('/api/courses/:id/students', function(req, res, next){
    // TODO
});
```
#### 5)
Answer all the questions related to web services architecture in the A4 Questions and Diagrams template on Canvas.

<hr>

Once again, the sky's the limit for you! If you find the current assignment less fulfilling or not as exciting, feel free to explore and expand the system further. Your creativity and enthusiasm are highly encouraged as long as you meet the required deliverables and demonstrate your understanding of Web Services architecture by providing comprehensive answers to the questions. The primary goal is to showcase your comprehension of the concept while delivering the specified components. So, don't hesitate to take the project to new heights and impress us with your innovative ideas!

