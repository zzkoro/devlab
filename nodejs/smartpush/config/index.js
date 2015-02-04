var express = require('express');
var session = require('express-session');
var redis = require('redis');
var RedisStore = require('connect-redis')(session);
var url = require('url');
var utils = require('../util/utils');
var env = require('./env');

var config = {};

module.exports = Config;

function Config(app) {
    try {
        config = require('./config.json');
    } catch (err) {
        console.error('Fail to load config.json: %j', err);
    }

    utils.merge(config, env);

    app.set('config', config);
    app.set('port', config.app.port);

    app.set('sessionStore', new RedisStore({
        port : config.redis.port,
        host : config.redis.host,
        db : config.redis.database,
        ttl : config.redis.ttl     // seconds
        }));


}

