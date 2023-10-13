#include <opencv2/opencv.hpp>
#include <opencv2/highgui/highgui.hpp>
#include <opencv2/imgproc/imgproc.hpp>
#include <chrono>
#include <ctime>
#include <cstdlib>
#include <iostream>
#include <filesystem>
#include "image_compare.cpp"

cv::Mat greyscale_filt(const cv::Mat& input_image) {
    int height = input_image.rows;
    int width = input_image.cols;

    // Create an empty grayscale image with the same dimensions as the input
    cv::Mat grayscale_image(height, width, CV_8UC1);

    // Loop through each pixel in the input image
    for (int y = 0; y < height; y++) {
        for (int x = 0; x < width; x++) {
            // Get the RGB values of the current pixel
            cv::Vec3b pixel = input_image.at<cv::Vec3b>(y, x);

            // Calculate the grayscale value as the average of RGB channels
            uchar gray_value = static_cast<uchar>((pixel[0] + pixel[1] + pixel[2]) / 3);

            // Set the grayscale value for the corresponding pixel in the output image
            grayscale_image.at<uchar>(y, x) = gray_value;
        }
    }

    return grayscale_image;
}