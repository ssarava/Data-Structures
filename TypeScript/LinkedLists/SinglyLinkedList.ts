
class MyNode<T> {
    val: T
    next?: MyNode<T>

    constructor(valIn: T) {
        this.val = valIn
        this.next = undefined
    }

    sayHi() {
        console.log("hi!")
    }

}

class SingleLinkedList<Type> {

    static EMPTY_NODE = 0
    static SINGLE_NODE = 1

    head?: MyNode<Type>
    size: number = 0

    constructor(sizeIn: number = 0) {
        this.head = undefined
        this.size = sizeIn
    }

    public toString() {
        // let headPres = this.head?
    }
}

function fib_rec(n: number) : number {
    if (n < 0) {
        throw new RangeError("enter a non-negative integer!")
    }
    if (n == 0 || n == 1) {
        return 1
    }
    return fib_rec(n - 1) + fib_rec(n - 2)
}

function fib_iter(n: number) {
    let prev = 0, curr = 1
    for (let index = 2; index <= n + 1; index ++) {
        let temp = curr
        curr += prev
        prev = temp
    }
    return curr
}

console.log("fib_rec = ", fib_rec(6))
console.log("fib_iter = ", fib_iter(6))