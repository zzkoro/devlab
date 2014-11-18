var type = require('type-component');

/**
 * Sort Case Insensitive
 * @type {exports}
 */
exports.caseInsensitiveSort = function(a, b) {
    var ret = 0;

    a = a.toLowerCase();
    b = b.toLowerCase();

    if (a > b) ret = 1;
    else if (a < b) ret = -1;

    return ret;
};

/**
 * Merge Object b into a
 * @type {exports}
 */
exports.merge = function(a, b) {
    for (var key in b) {
        if (exports.has.call(b, key) && b[key]) {
            if ('object' === type(b[key])) {
                if ('undefined' === type(a[key])) a[key] = {};
                exports.merge(a[key], b[key]);
            } else {
                a[key] = b[key];
            }
        }
    }
    return a;
};

exports.has = Object.prototype.hasOwnProperty;
