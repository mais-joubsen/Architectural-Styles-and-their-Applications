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
    cv::Mat flipped_image = input_image.clone(); 

    int height = input_image.rows;
    int width = input_image.cols;

    for (int y = 0; y < height; y++) {
        
        for (int x = 0; x < width / 2; x++) {
            
            int opposite_x = width - x - 1;

            cv::Vec3b temp = flipped_image.at<cv::Vec3b>(y, x);
            
            flipped_image.at<cv::Vec3b>(y, x) = flipped_image.at<cv::Vec3b>(y, opposite_x);
            
            flipped_image.at<cv::Vec3b>(y, opposite_x) = temp;
        }
    }

    return flipped_image;
}