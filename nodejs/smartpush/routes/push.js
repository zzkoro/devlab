var express = require('express');
var router = express.Router();
var gcm = require('node-gcm');

/* Send Push Data */
router.post('/send', function(req, res) {
    req.accepts('application/json');

    var reqJson = req.body;

    console.log("token:" + reqJson.token);
    console.log("msg:" + reqJson.msg);

    res.json({result:'success'});
});

module.exports = router;
