var MyNode = /** @class */ (function () {
    function MyNode(valIn) {
        this.val = valIn;
        this.next = undefined;
    }
    MyNode.prototype.sayHi = function () {
        console.log("hi!");
    };
    return MyNode;
}());
var message = "Hello there!";
console.log(message);
var mess = "hi";
var pe = "hi";
function fib_rec(n) {
    if (n < 0) {
        throw new RangeError("enter a non-negative integer!");
    }
    if (n == 0 || n == 1) {
        return 1;
    }
    return fib_rec(n - 1) + fib_rec(n - 2);
}
function fib_iter(n) {
    var prev = 0, curr = 1;
    for (var index = 2; index <= n + 1; index++) {
        var temp = curr;
        curr += prev;
        prev = temp;
    }
    return curr;
}
console.log("fib_rec = ", fib_rec(6));
console.log("fib_iter = ", fib_iter(6));
