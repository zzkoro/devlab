var mongoose = require('mongoose'),
    paginate = require('./paginate');

var providerModel = function () {

  var providerSchema = mongoose.Schema({
    spId : { type: String, required: true, trim: true },
    spName : { type: String, required: true, trim: true },
    pushServer: {},
    regDt: { type: Date, default: Date.now }, // created
    updDt: { type: Date } // updated
  },{strict: false});

  providerSchema.plugin(paginate);

  return mongoose.model('Provider', providerSchema);
};

module.exports = new providerModel();
