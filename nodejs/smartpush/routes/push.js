var express = require('express');
var router = express.Router();
var gcm = require('node-gcm');
var mongoDao = require('../dao/mongoDao');


/* Get Provider Info */
router.get('/provider/:spId', function(req, res) {
    req.accepts('application/json');

    var spId = req.params.spId;

    console.log("spId:" + spId);

    mongoDao.retrieveProvider(spId, function(err, provider) {
        if (err) {
            console.error("Retrive provider error:" + err);
            return res.json({result:'false'});
        }
        res.json(provider);
    });
});

/* Post Provider Info */
router.post('/provider', function(req, res) {
    req.accepts('application/json');

    var reqJson = req.body;

    console.log("providerInfo:" + reqJson);

    mongoDao.registerProvider(reqJson, function(err, provider) {
        if (err) {
            console.error("Register provider error:" + err);
            return res.json({result:'false'});
        }
        res.json({result:'success'});
    });
});

/* Send Push Data */
router.post('/send', function(req, res) {
    req.accepts('application/json');

    var reqJson = req.body;

    console.log("token:" + reqJson.token);
    console.log("msg:" + reqJson.msg);

    res.json({result:'success'});
});

module.exports = router;
