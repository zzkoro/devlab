var env = process.env;

module.exports = {
    redisURL : env.REDIS_URL || env.REDISTOGO_URL || "",
    session : {
        secret : env.SESSION_SECRET
    },
    app : {
        port : env.PORT
    }

};

