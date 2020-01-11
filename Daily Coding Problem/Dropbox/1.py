"""
Create a data structure that performs all the following operations in O(1) time:

plus: Add a key with value 1. If the key already exists, increment its value by one.
minus: Decrement the value of a key. If the key's value is currently 1, remove it.
get_max: Return a key with the highest value.
get_min: Return a key with the lowest value.
"""

"""
Answer: https://www.dailycodingproblem.com/solution/358?token=7705e6b4c998a7ace0dd6787027e5d21617b9197f9e6a49f400dcbfcf019d325b32261a9
"""
import random
from collections import defaultdict

class Node:
    def __init__(self, key=None):
        self.keys = set([key]) if key else set([])
        self.prev = None
        self.next = None

    def add(self, key):
        self.keys.add(key)
    
    def remove(self, key):
        self.keys.remove(key)
    
    def empty(self):
        return len(self.keys) == 0

    def select(self):
        if len(self.keys) == 0:
            return None
        return random.sample(self.keys, 1)[0]

class DoublyLinkedList:
    def __init__(self):
        self.head = Node()
        self.tail = Node()
        self.tail.prev = self.head
        self.head.next = self.tail
    
    def insertAfter(self, node, key):
        new_node = Node(key)
        new_node.prev = node
        if node.next:
            node.next.prev = new_node
            new_node.next = node.next
            node.next = new_node
        else:
            node.next = new_node
        return new_node

    def remove(self, node):
        if node.prev:
            node.prev.next = node.next
        if node.next:
            node.next.prev = node.prev
    
    def print(self):
        node = self.head.next
        while node is not self.tail:
            print(node.keys, ", ")
            node = node.next

class FastStore:
    def __init__(self):
        self.dll = DoublyLinkedList()
        self.key2count = defaultdict(int)
        self.count2node = {0:self.dll.head}

    def plus(self, key):
        self.key2count[key] += 1
        count = self.key2count[key]
        prev_count = count-1
        if count in self.count2node:
            self.count2node[count].add(key)
        else:
            prev_node = self.count2node[prev_count]
            self.count2node[count] = self.dll.insertAfter(prev_node, key)
            if prev_count > 0:
                prev_node.remove(key)
                if prev_node.empty():
                    self.dll.remove(prev_node)
                    self.count2node.pop(prev_count)

    def minus(self, key):
        if key not in self.key2count:
            return
        self.key2count[key] -= 1
        count = self.key2count[key]
        if count == 0:
            self.key2count.pop(key)
        else:
            prev_count = count+1
            if count in self.count2node:
                self.count2node[count].add(key)
            else:
                self.count2node[count] = self.dll.insertAfter(self.count2node[prev_count].prev, key)
        prev_node = self.count2node[prev_count]
        prev_node.remove(key)
        if prev_node.empty():
            self.dll.remove(prev_node)
            self.count2node.pop(prev_count)

    def get_max(self):
        return self.dll.tail.prev.select()

    def get_min(self):
        return self.dll.head.next.select()

if __name__ == "__main__":
    store = FastStore()
    for c in 'abcdaab':
        store.plus(c)
        store.dll.print()
    print(store.get_max())
    print(store.get_min())

