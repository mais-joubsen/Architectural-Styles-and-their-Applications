## **Module 1:** Pipe-and-Filter Architecture

  
  

### **Introduction**

  

The following project is comprised of C++ files and represents a small-scale image processing pipeline. Your task in this project, is to complete 3 filters that will be applied to the data (images).

  

  

### **Understanding the files**

  

#### This project has the following files:

  

**You are only expected to create a file per filter and modify the pipeline to make use of them**

  

-  **CMakeLists.txt**: specifies the build instructions for the project.

  

-  **FindLibRT.cmake**: specifies settings for different platforms.

  

-  **cluon-complete.hpp**: library necessary for some project components.(not relevant for the students)

  

-  **pipeline.cpp**: main file that acts as the pipe for the data.

  

  

### **Your task**

  

The following project is a simple image processing pipeline that your company has created to make images more complicated to detect for a new machine learning algorithm.

  

The project already includes the pipeline file, capable of loading images and saving the results of the application of the filters.

  

Your task is to create filter files in the **/src** folder.

  

Each filter file should have a name that indicates what the filter does and the function definition that will take an image and return the filtered image.

  

Once you have completed the filters you have to find a way to make them accessible to the **pipeline.cpp** file and apply these functions to each loaded image. you might find the keyword **#include file.cpp** useful for this task.

  

Keep in mind that you cannot make changes to the CMakeLists.txt file, as this will cause problems for the automated correction system.
  

If your implementation works correctly, your pipeline will create the flipped and grayscale images every time you run the project, which will be visible in your pipe and filter folder.



Once you have completed the code implementation, don't forget to complete the questions and diagram task in the provided document on Canvas. Create a component diagram of the system you have created where you show in detail the different components of your pipe and filter system and how they interface.

  

You are expected to create your own short algorithms to solve the problems instead of the premade functions included in the OpenCV library, such as, but not limited to:

  

-  **cv::cvtColor();**

  

-  **cv::flip();**

  

  

The following resources might be helpful to understand how to implement these filters: [What is RGB?](https://www.chem.purdue.edu/gchelp/cchem/RGBColors/body_rgbcolors.html#:~:text=A%20monitor%20or%20TV%20screen,surrounded%20by%20a%20black%20mask.)

  

  

### Useful methods to use

  

```

  

// method to get a specific pixel (can also be used to edit pixels)

  

cv::Vec3b pixel = image.at<cv::Vec3b>(y, x);

  

  

// how to extract the individual color channels

  

uchar blue = pixel[0]; // Blue channel

  

uchar green = pixel[1]; // Green channel

  

uchar red = pixel[2]; // Red channel

  

  

int width = img.cols; // Width of the image

  

int height = img.rows; // Height of the image

  

```

  

  

### **How to set up**

  

[Clone the repository:](https://docs.gitlab.com/ee/user/ssh.html)

  

```

  

git clone <SSH FROM YOUR REPOSITORY>

  

```

  

This project already contains build instructions, meaning you only need to download some tools and use premade commands to set up and run your project.

  

  

### MacOS

  

1. Install Xcode, HomeBrew, CMake

  

```

  

xcode-select --install

  

  

/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"

  

  

brew install cmake opencv

  

```

  

2. Navigate to the folder `pipe-and-filter/`

  

3. Create the `build` folder and the build:

  

```

  

mkdir build

  

cd build

  

cmake ..

  

make

  

```

  

4. Run the project:

  

```

  

./pipeline

  

```

  

### Linux

  

1. Download OpenCV using the following command:

  

```

  

sudo apt install libopencv-dev

  

```

  

2. Navigate to the folder **pipe-and-filter/**

  

3. Create the build folder and the build:

  

```

  

mkdir build

  

cd build

  

cmake ..

  

make

  

```

  

4. Run the project:

  

```

  

./pipeline

  

```

  

  

### Windows

  
Due to the complicated process to get openCV on windows, we recommend the following course of action:
  

1. Install Multipass and create a [linux development environment](https://multipass.run/docs/installing-on-windows).

  

This command will create a linux virtual environment in your windows device

  

```

  

multipass launch 22.04 --name dit345 --cpus 2 --disk 4G --memory 4G

  

```

  

This command should print the instance you just created

  

```

  

multipass list

  

```
This command will create a mount (a folder accessible in your windows and your linux environment so you can use vscode to edit your code and the linux terminal to build and run the project)
```

  # navigate to your documents folder
  # create a folder called data-exchange-folder
  # in your terminal navigate to documents
  # (or the place where your new folder is located)+
  # run this command
  multipass mount data-exchange-folder dit345:/host



  

```

  

This command will start the interface with your new linux environment

  

```

  

multipass shell dit345

  

```

  

These commands will give you access to basic linux functionalities and some libraries you are going to need

  

```

  

sudo apt-get update

  

sudo apt-get upgrade

  

sudo apt-get update

  

sudo apt-get install build-essential cmake git

  

```

  

2. Now follow the instructions in the linux section

  

  

This guide is an adaptation of the installation guide produced by professor Christian Berger for the course DIT639

  

[License:](https://creativecommons.org/licenses/by-nc-sa/4.0/legalcode)

  

(c) 2023 Christian Berger, [christian.berger@gu.se](mailto:christian.berger@gu.se)

  

  

## Grading criteria

The grading criteria for the modules can be found in the assignment description document in canvas, please read carefully prior to submission.
