var express = require('express');
var router = express.Router();

/* GET main page. */
router.get('/', function(req, res) {

    var session = req.session;
    var userInfo = session.userInfo;

  res.render('dashboard/index', userInfo);
});



module.exports = router;
