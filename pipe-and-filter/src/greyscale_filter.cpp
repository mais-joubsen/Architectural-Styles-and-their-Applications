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
    cv::Mat grayscale_image(input_image.size(), CV_8U);

    int height = input_image.rows;
    int width = input_image.cols;

    for (int y = 0; y < height; y++) {
        
        for (int x = 0; x < width; x++) {

            cv::Vec3b pixel = grayscale_image.at<cv::Vec3b>(y, x);

            uchar gray_value = static_cast<uchar>((pixel[0] + pixel[1] + pixel[2]) / 3);

            grayscale_image.at<cv::Vec3b>(y, x) = cv::Vec3b(gray_value, gray_value, gray_value);
        }
    }

    return grayscale_image;
}