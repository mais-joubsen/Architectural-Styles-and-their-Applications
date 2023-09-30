# Assignment 4 - Architectural Styles and their Applications

## Overview  
A4 consists of three separate types of systems. One system is constructed in a Pipe-And-Filter style architecture (Image Manipulation Filters), the second in a Web-Services style, and the other made in the form of Microservices architectural style.  

## System Descriptions  
[Module 1](pipe-and-filter/README.md) is an image manipulation program written in C++, that is to be added with a variety of filters, that modify the image(s) provided, each with their self-defined purpose. Note that **extensive knowledge of C++ is not expected**, in order to complete the exercises that are part of this module, covering the **Pipe-And-Filter** architectural style.

[Module 2](web-services/README.md) is Brimula, a staff management web platform designed specifically for examiners and lecturers, with front-end and back-end modules. This module is to be designed, applying the **web-services** style of architecture. 

[Module 3](microservices/README.md) is an application storing customer data as a collection of orders, written in Java. It consists of a command-line based user interface, and MySQL database back-end. This module is designed around the **microservices architectural style**, made possible using Java's Remote Method Invocation (RMI).

In the course of the assignment you will be asked to modify the systems by adding/improving features, analyze which system better supported the required modifications and analyze the relative tradeoffs between the different architectures. Please read the steps in each of the modules' README files for specific installation and setup procedures.  

<hr>


## **Packaging and Submitting the Assignment**  




● Once the modifications have been added in each of the module folders, a **tag** must be made, called `"a4-final-group<number>"`. This will mark as the group's submission for this assignment.  
● Additionally, a small screen-recording should be made (one per module), providing a run-down of the system, with the added functionalities, and how they work, by showing examples. 

You must clearly describe how to set up and run your program in a new README file that is included with the team portion of the assignment, **if** the usual instructions for building/running *have changed* upon the additions made to the code.

## **Evaluation & Grading**
In this assignment, teams will be evaluated based on functional tests, a screencast, and responses to software architecture questions. Once a tag is created, tests will automatically run in another repository dedicated to your team, with results accessible under section called "Build". All three projects (pipeline-filter, web-services, microservices) will be included in this testing when the tag is generated. Students have up to nine test attempts before making their final submission. To label testing tags, use the format "test+(a number)" (e.g. test1), while the submission tag should be named `"a4-final-group<number>"`, as specified earlier.

**Note:** Changes on the `.gitlab-ci.yml` file in this repository is not allowed!

### Pipeline results
The outcomes of the activated pipelines during the tag creation process are accessible in another repository called "a4-group-xx-test". This testing repository is a "project" repository connected to your original repository dedicated to testing your project. Upon creating a tag, the testing repository's pipeline gets triggered and you can see the results in the latest pipeline job. In the testing repository, you only have reporter roles. Thus, you can not change any code and can only observe. Nonetheless, this setup can be a very good learning opportunity for you.

**Note**: The tag must be made from the master/main branch in your `"a4-final-group<number>"` repository.
