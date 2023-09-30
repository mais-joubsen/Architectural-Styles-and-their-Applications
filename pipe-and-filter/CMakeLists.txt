cmake_minimum_required(VERSION 3.2)

project(pipeline)

set(CMAKE_MODULE_PATH "${CMAKE_CURRENT_SOURCE_DIR}" ${CMAKE_MODULE_PATH})

set(CMAKE_CXX_STANDARD 17)
set(CMAKE_CXX_STANDARD_REQUIRED ON)

################################################################################
# Add current build directory as include directory as it contains generated files.
include_directories(SYSTEM ${CMAKE_BINARY_DIR})
include_directories(${CMAKE_CURRENT_SOURCE_DIR}/src)

if(UNIX)
    if(NOT "${CMAKE_SYSTEM_NAME}" STREQUAL "Darwin")
        find_package(LibRT REQUIRED)
        set(LIBRARIES ${LIBRARIES} ${LIBRT_LIBRARIES})
        include_directories(SYSTEM ${LIBRT_INCLUDE_DIR})
    endif()
endif()

find_package(OpenCV REQUIRED core highgui imgproc)
include_directories(SYSTEM ${OpenCV_INCLUDE_DIRS})
set(LIBRARIES ${LIBRARIES} ${OpenCV_LIBS})

add_executable(${PROJECT_NAME} ${CMAKE_CURRENT_SOURCE_DIR}/src/${PROJECT_NAME}.cpp)
target_link_libraries(${PROJECT_NAME} ${LIBRARIES})

################################################################################
# Install executable.
install(TARGETS ${PROJECT_NAME} DESTINATION bin COMPONENT ${PROJECT_NAME})
