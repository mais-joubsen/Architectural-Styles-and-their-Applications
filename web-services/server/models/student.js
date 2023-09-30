var mongoose = require('mongoose');
var Schema = mongoose.Schema;

var studentSchema = new Schema({
    SSN: { type: String },
    firstName: { type: String },
    lastName: { type: String},
    emailAddress: { type: String }
});

module.exports = mongoose.model('student', studentSchema);