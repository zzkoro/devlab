var Provider = require('./model/provider');

/**
 * MongoDB의 data를 저장, 수정, 조회를 하기 위한 module
 * @module
 * @name mongoPerister
 */

/**
 * User를 등록한다.
 * @name registerUser
 * @function 
 * @param {object} input - JSON 형태의 data
 * @param {callback} done - 등록 후 수행할 callback function
 */
exports.registerProvider = function(input, done) {

  var query = { spId: input.spId };

  var data = input;

  Provider.find(query,function(err,doc) {
  	if(err) return done(err);
  	if(doc.length > 0 ){
  		return done('ERR-USER_EXIST');
  	}

    var newProvider = new Provider(data);
      newProvider.save(function(err) {
			if (err) return done(err);
			if (done) done(null);
		});
  });
};


/**
 * Provider 정보 조회
 * @name retrieveProvider
 * @function 
 * @param {object} input - JSON 형태의 data 
 * @param {callback} done - 조회 후 수행할 callback function
 */
exports.retrieveProvider = function(spId, done) {

  var query = { spId: spId };

  // lean 함수를 이용하여 mongoose 객체를 JSON 형태로 변경함
  Provider.findOne(query).lean().exec(function(err, provider) {
    if (err) return done(err);
    return done(null, provider);
  });
};
