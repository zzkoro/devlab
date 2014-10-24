var express = require('express');
var router = express.Router();

/* GET login form. */
router.get('/', function(req, res) {
  res.send('respond with a resource');
});


/* GET users listing. */
router.get('/:id', function(req, res) {
    console.log('id:' + req.params.id);
    var result = {'id':req.params.id};
    res.json(result);
    //res.send('respond with a resource');
});


module.exports = router;
