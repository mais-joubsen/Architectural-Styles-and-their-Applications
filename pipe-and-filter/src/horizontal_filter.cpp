#include <opencv2/opencv.hpp>
#include <opencv2/highgui/highgui.hpp>
#include <opencv2/imgproc/imgproc.hpp>
#include <chrono>
#include <ctime>
#include <cstdlib>
#include <iostream>
#include <filesystem>
#include "image_compare.cpp"

cv::Mat horizontal_filt(const cv::Mat& input_image) {
    cv::Mat flipped_image = input_image.clone(); // this is done so that the original input image is not directly manipulated

    int height = input_image.rows;
    int width = input_image.cols;

    // example diagram:
    // ----------- 20 -0 - 1 (to get the index)
    // |         |
    // |         |
    // -----------

    // This goes through each x-position (not the entire column itself)
    for (int x = 0; x < width / 2; x++) {
        // Swap the current column with the corresponding column from the right
        int opposite_x = width - x - 1; // width - x returns the absolute position of the pixel, while - 1 makes it the correct index position
        for (int y = 0; y < height; y++) { // this is what makes the loop go through the entire column (as the y-value changes with each iteration)
            cv::Vec3b temp = flipped_image.at<cv::Vec3b>(y, x);
            flipped_image.at<cv::Vec3b>(y, x) = flipped_image.at<cv::Vec3b>(y, opposite_x);
            flipped_image.at<cv::Vec3b>(y, opposite_x) = temp;
        }
    }

    return flipped_image;
}