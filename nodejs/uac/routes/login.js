var express = require('express');
var router = express.Router();

/* GET users listing. */
router.get('/', function(req, res) {
  res.render('login');
});

/* POST users listing. */
router.post('/', function(req, res) {
    var email = req.param('email');
    console.log('email:' + email);
    var result = {'email':email};

    res.render('login');
});


module.exports = router;
