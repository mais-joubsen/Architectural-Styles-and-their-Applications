var express = require('express');
var router = express.Router();
var Course = require('../models/course');

/**
 * Implement the endpoint for creating/adding a student to the list of students of a course.
 * The most important status/response codes such as 500, 404, 201, etc. and their appropriate messages shall be covered.
 * The student object shall exist independently of the course and shall be also found in the list of
 * all students (not just specific to a course).
 */
router.post("/api/courses/:id/students", function (req, res, next) {
    // TODO
});

/**
 * Implement the endpoint for getting the list of all students of a course.
 * The most important status/response codes such as 500, 404, 201, etc. and their appropriate messages shall be covered.
 */
router.get('/api/courses/:id/students', function(req, res, next){
    // TODO
});

router.post('/api/courses', function(req, res, next){
  var course = new Course(req.body);
  course.save()
      .then(result => {
      console.log(result);
      return res.status(201).json({
        message:"Course has been created",
        course: result
      })
      })
      .catch(err => {
       console.log(err);
       return res.status(500).json({
        error: err
       });
      })
  });

router.get('/api/courses', function(req, res, next) {
  Course.find(function(err, courses) {
      if (err) { return res.status(500).send(err); }
      return res.status(200).json({
        courses: courses
    })
  });
});

router.get("/api/courses/:id", function (req, res, next) {
  Course.findOne({ _id: req.params.id })
    .populate("students")
    .populate("staffs")
    .exec(function (err, course) {
      if (err) {
        return res.status(500).send(err);
      }
      if (course === null) {
        return res.status(404).json({'message': 'Course not found!'});
    }
    return res.status(200).json({
      course
 });
    });
});

router.patch('/api/courses/:id', function(req, res,next) {
    var id = req.params.id;
    Course.findById(id, function(err, course) {
        if (err) { return res.status(500).send(err); }
        if (course == null) {
        return res.status(404).json({"message": "Course not found"});
        }
        course.id = (req.body.id || course.id);
        course.name = (req.body.name || course.name);
        course.department = (req.body.department || course.department);
        course.staffs = (req.body.staffs || course.staffs);
        course.students = (req.body.students || course.students);
        course.lectureDates = (req.body.lectureDates || course.lectureDates)
        course.text = (req.body.text || course.text)
        course.save()
        .then(result => {
          console.log(result);
        return res.status(201).json({
          message:"Course has been patched",
          course: result
        })
        })
        .catch(err => {
         console.log(err);
         return res.status(500).json({
          error: err
         });
        })
    });
});

router.put('/api/courses/:id', function(req, res,next) {
    var id = req.params.id;
    Course.findById(id, function(err, course) {
        if (err) { return res.status(500).send(err); }
      if (course == null) {
        return res.status(404).json({"message": "Course not found"});
        }
        course.id = req.body.id;
        course.name = req.body.name;
        course.department = req.body.department;
        course.staffs = (req.body.staffs || course.staffs);
        course.students = (req.body.students || course.students);
       course.save()
       .then(result => {
        console.log(result);
      return res.status(201).json({
        message:"Course has been put",
        course: result
      })
      })
      .catch(err => {
       console.log(err);
       return res.status(500).json({
        error: err
       });
      })
    });
});

router.delete('/api/courses', function(req,res,next){
    Course.deleteMany(function(err, course) {
        if (err) { return res.status(500).send(err); }
        return res.status(200).json({
          course
     });
    });
});

router.delete('/api/courses/:id', function(req, res, next) {
    var id = req.params.id;
    Course.findOneAndDelete({_id: id}, function(err, course) {
        if (err) {return res.status(500).send(err); }
        if (course === null) {
            return res.status(404).json({'message': 'Course not found'});
        }
        return res.status(200).json({
          course
     });
       
    });
});

router.delete("/api/courses/:co_id/students/:student_id", function (req, res, next) {
  Course.findByIdAndUpdate({_id: req.params.co_id})
  .populate("students")
  .exec(function (err, course, student){
    if(err) {
      return res.status(500).send(err);
    }
    if(course === null || student === null){
      return res.status(404).json({message: "Not found!"});
    }
    course.students.pull({_id: req.params.student_id});
    course.save();
    return res.status(200).send(course.students);
  });
});

module.exports = router;