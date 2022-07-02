//****LRU CACHE****
//Time compelxity: 0(1) for user functions
//Space complexity:
//leetcode runnable: Y;
//Any doubts: N;

class LRUCache {
    //Defining a doubly linkedlist
    class Node{
        int key;
        int value;
        Node prev;
        Node next;

        public Node(int key, int value){
            this.key=key;
            this.value=value;

        }
    }
    private void removenode(Node node)
    {
        node.next.prev=node.prev;
        node.prev.next=node.next;
    }

    private void addtohead(Node node)
    {
        node.next=head.next;
        node.prev=head;
        head.next=node;
        node.next.prev=node;
    }
    HashMap<Integer, Node> map;
    Node head;
    Node tail;
    int capacity;

    public LRUCache(int capacity) {
        this.head=new Node(-1,-1);
        this.tail=new Node(-1,-1);
        this.head.next=tail;
        this.tail.prev=head;
        this.capacity=capacity;
        this.map=new HashMap<>();


    }

    public int get(int key) {
        if(!map.containsKey(key)) return -1;
        Node node=map.get(key);
        removenode(node);
        addtohead(node);
        return node.value;

    }

    public void put(int key, int val) {
        //Getting the non fresh node
        if(map.containsKey(key))
        {
            Node node=map.get(key);
            node.value=val;
            removenode(node);
            addtohead(node);

        }
        //Getting the fresh node
        else
        {
            if(capacity==map.size())
            {
                //Remove the last node
                Node tailprev=tail.prev;
                removenode(tailprev);
                map.remove(tailprev.key);
            }
            Node newnode= new Node(key, val);
            addtohead(newnode);
            map.put(key, newnode);
        }

    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
/*
ALGORITHM AND THINKING APPROACH
1.netflix, amazon prime can be goo example of LRU
2.LRU stands for least recently used cache. 
3.This means a memory has only certain space to keep the track of recent task that are accessed by the user
4.so whenever the get method is called that means that particular thing is accessed
    4.1 Check if the key value exists in the map
        4.1.1 if the pair esits, make it as the recently visited.
    4.2 if pair is not present in the map simply we need to return -1;
5.If any new task is added into the LRU Cache, that means that is accessed recently and for that 
    5.1 we need to check if that task already exists, 
        5.1.1 if task already exists than we need to update it in the map and bring it                 in the front as that is recently accessed
    5.2 if task doesnt exists in the cache memory
        5.2.1 check for the size of cache if that is full remove the least recent and add this new task and recently accessed.
        5.2.2 if the size is not full and new element can be added as the recently accessed element in the front.
*/


/*
**DATASTRUCTURE TO BE USED AND APPRAOCH
1. we will need a list to maintain the values 
    1.1 what if we use array
        1.1.1 so here if we get any task then basically we need to move it in the                   front and as we move a element in front then we need to further move                   each element indivisually to the next point. Think about you have                     10000000 tasks. This is not a realible way
    1.2 what if we use LinkedList
        1.2.1 if we use a linked list then basically the moving operation would become               surely simple as we will directly remove the current accessed node and                 we can add that node in the front of the linked list. 0(1).
        1.2.2 but if we get a key so we will need to iterate though all the linked                   list to find what is the value of key and then make operations with that               particular node. 
        1.2.3.My thinking goes in the way that while putting we are getting the key                 value and while get is called we are just getting the key and not the                 value so first we need to maintain a map of key value pair when we are                 putting the nodes. So when we get we go through map find the value                     assoaciated with the key and now find that in the linked list.
        1.2.4.For making changes with node we are going 0(1). 
              For finding value assocaited with key we are going for o(1).
              But the issue is with once we have found the value of the key. To get it               in the Linkedlist we are everytime traversing though while Linked list
        1.2.5 So if we maintain the key-node in the map instead of key- value, then we               will not need to iterate through list. As we will have the node                       reference we can directly access the node.
        1.2.6 So now we are able to access the node in 0(1). But now if we want to del               it we need the prev as well but we dont have that so instead of singly                 linked list we can have a doubly linked list so that we would be able to               access the previous as well
              
              
*/
