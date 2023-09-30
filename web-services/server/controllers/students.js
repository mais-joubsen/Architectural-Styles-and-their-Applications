var express = require('express');
var router = express.Router();
var Student = require('../models/student');

router.post('/api/students', function(req, res, next){
    var student = new Student(req.body);
    student.save()
    .then(result => {
        console.log(result);
        return res.status(201).json({
          message:"Student has been created",
          student: result
        })
        })
        .catch(err => {
         console.log(err);
         return res.status(500).json({
          error: err
         });
        })
});

router.get('/api/students', function(req, res, next) {
    Student.find(function(err, students) {
        if (err) { return res.status(500).send(err);  }
        return res.status(200).json({
            students: students
        })
    });
});

router.get('/api/students/:id', function(req, res, next) {
    var id = req.params.id;
    Student.findById(id, function(err, student) {
        if (err) { return res.status(500).send(err);  }
        if (student === null) {
            return res.status(404).json({'message': 'Student not found!'});
        }
        return res.status(200).json({
            student
       });
    });
});


router.patch('/api/students/:id', function(req, res,next) {
    var id = req.params.id;
    Student.findById(id, function(err, student) {
        if (err) { return res.status(500).send(err); }
        if (student == null) {
        return res.status(404).json({"message": "Student not found"});
        }
        student.SSN = (req.body.SSN || student.SSN);
        student.firstName = (req.body.firstName || student.firstName);
        student.lastName = (req.body.lastName || student.lastName);
        student.emailAddress = (req.body.emailAddress || student.emailAddress);
        student.save()
        .then(result => {
            console.log(result);
          return res.status(201).json({
            message:"Student has been patched",
            student: result
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

router.put('/api/students/:id', function(req, res,next) {
    var id = req.params.id;
    Student.findById(id, function(err, student) {
        if (err) { return res.status(500).send(err); }
        if (student == null) {
        return res.status(404).json({"message": "Student not found"});
        }
        
        student.SSN = req.body.SSN;
        student.firstName = req.body.firstName;
        student.lastName = req.body.lastName;
        student.emailAddress = req.body.emailAddress;
        student.save()
        .then(result => {
            console.log(result);
          return res.status(201).json({
            message:"Student has been put",
            student: result
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

router.delete('/api/students', function(req,res,next){
    Student.deleteMany(function(err, students) {
        if (err) { return res.status(500).send(err);  }
        return res.status(200).json({
            students
       });
    });
});

router.delete('/api/students/:id', function(req, res, next) {
    var id = req.params.id;
    Student.findOneAndDelete({_id: id}, function(err, student) {
        if (err) { return res.status(500).send(err);  }
        if (student === null) {
            return res.status(404).json({'message': 'Studnet not found'});
        }
        return res.status(200).json({
            student
       });
         
    });
});

module.exports = router;