# Task 2 Analysis

## 1. What is the exact cause of ConcurrentModificationException in Java?

ConcurrentModificationException occurs when a collection
such as ArrayList is modified while it is being iterated
using an Iterator or enhanced for-loop.

Examples:
- Removing elements during iteration
- Adding elements during iteration

Java collections use fail-fast iterators that throw this
exception when structural modification is detected.


## 2. What code pattern at line 142 most likely triggered this error?

Most likely code:

```java
for (Transaction t : transactions) {
    if (condition) {
        transactions.remove(t);
    }
}
```

Or:

```java
Iterator<Transaction> itr = transactions.iterator();

while (itr.hasNext()) {
    Transaction t = itr.next();
    transactions.remove(t);
}
```

The collection is modified directly while iteration is in progress.


## 3. Provide the minimal code change that resolves this safely.

Use Iterator.remove():

```java
Iterator<Transaction> itr = transactions.iterator();

while (itr.hasNext()) {

    Transaction t = itr.next();

    if (condition) {
        itr.remove();
    }
}
```